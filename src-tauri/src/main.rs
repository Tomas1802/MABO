#![cfg_attr(not(debug_assertions), windows_subsystem = "windows")]

mod util;

use portable_pty::{native_pty_system, ChildKiller, CommandBuilder, MasterPty, PtySize};
use serde::Serialize;
use std::collections::HashMap;
use std::io::{BufWriter, Read, Write};
use std::path::PathBuf;
use std::sync::Mutex;
use std::time::{SystemTime, UNIX_EPOCH};
use tauri::ipc::Channel;
use tauri::{AppHandle, Emitter, Manager, State};

const INTEGRATION_PS1: &str = include_str!("integration.ps1");

struct Pty {
    writer: Box<dyn Write + Send>,
    master: Box<dyn MasterPty + Send>,
    killer: Box<dyn ChildKiller + Send + Sync>,
}

#[derive(Default)]
struct Ptys(Mutex<HashMap<u32, Pty>>);

fn err(e: impl std::fmt::Display) -> String {
    e.to_string()
}

fn find_in_path(exe: &str) -> Option<PathBuf> {
    std::env::split_paths(&std::env::var_os("PATH")?)
        .map(|d| d.join(exe))
        .find(|p| p.is_file())
}

fn shell_command(cwd: Option<&str>) -> Result<CommandBuilder, String> {
    let shell = find_in_path("pwsh.exe").unwrap_or_else(|| "powershell.exe".into());
    let script = std::env::temp_dir().join("mabo-integration.ps1");
    // BOM so Windows PowerShell 5.1 reads the script's Unicode glyphs as UTF-8.
    std::fs::write(&script, format!("\u{feff}{INTEGRATION_PS1}")).map_err(err)?;
    let mut cmd = CommandBuilder::new(shell);
    cmd.args(["-NoLogo", "-NoExit", "-Command", &format!(". '{}'", script.display())]);
    match cwd.filter(|d| std::path::Path::new(d).is_dir()) {
        Some(dir) => cmd.cwd(dir),
        None => {
            if let Some(home) = std::env::var_os("USERPROFILE") {
                cmd.cwd(home);
            }
        }
    }
    // Don't leak a parent agent session (e.g. when launched from Claude Code) into new shells;
    // a nested `claude` would otherwise act as a child session (wrong --resume list, etc).
    for (key, _) in std::env::vars_os() {
        let k = key.to_string_lossy().to_ascii_uppercase();
        // Session markers only; user config such as CLAUDE_CONFIG_DIR is kept.
        if k == "CLAUDECODE" || k == "CLAUDE_PID" || k == "CLAUDE_EFFORT" || k.starts_with("CLAUDE_CODE_") {
            cmd.env_remove(key);
        }
    }
    cmd.env("TERM", "xterm-256color");
    cmd.env("COLORTERM", "truecolor");
    cmd.env("TERM_PROGRAM", "mabo");
    Ok(cmd)
}

#[tauri::command]
fn pty_spawn(
    app: AppHandle,
    ptys: State<Ptys>,
    id: u32,
    cols: u16,
    rows: u16,
    cwd: Option<String>,
    on_data: Channel<String>,
) -> Result<(), String> {
    let pair = native_pty_system()
        .openpty(PtySize { rows, cols, pixel_width: 0, pixel_height: 0 })
        .map_err(err)?;
    let mut child = pair.slave.spawn_command(shell_command(cwd.as_deref())?).map_err(err)?;
    drop(pair.slave);
    let mut reader = pair.master.try_clone_reader().map_err(err)?;
    let writer = pair.master.take_writer().map_err(err)?;
    let killer = child.clone_killer();
    ptys.0.lock().unwrap().insert(id, Pty { writer, master: pair.master, killer });

    std::thread::spawn(move || {
        let mut buf = [0u8; 16 * 1024];
        let mut carry = Vec::new();
        while let Ok(n @ 1..) = reader.read(&mut buf) {
            let text = util::decode_utf8(&mut carry, &buf[..n]);
            if !text.is_empty() && on_data.send(text).is_err() {
                break;
            }
        }
    });
    // ConPTY keeps the reader open after the shell exits, so watch the child instead
    // and drop the master (closing the pseudoconsole) once it's gone.
    std::thread::spawn(move || {
        let _ = child.wait();
        app.state::<Ptys>().0.lock().unwrap().remove(&id);
        let _ = app.emit("pty-exit", id);
    });
    Ok(())
}

#[tauri::command]
fn pty_write(ptys: State<Ptys>, id: u32, data: String) -> Result<(), String> {
    let mut map = ptys.0.lock().unwrap();
    let pty = map.get_mut(&id).ok_or("no such terminal")?;
    pty.writer.write_all(data.as_bytes()).map_err(err)
}

#[tauri::command]
fn pty_resize(ptys: State<Ptys>, id: u32, cols: u16, rows: u16) -> Result<(), String> {
    let map = ptys.0.lock().unwrap();
    let pty = map.get(&id).ok_or("no such terminal")?;
    pty.master
        .resize(PtySize { rows, cols, pixel_width: 0, pixel_height: 0 })
        .map_err(err)
}

#[tauri::command]
fn pty_kill(ptys: State<Ptys>, id: u32) {
    if let Some(mut pty) = ptys.0.lock().unwrap().remove(&id) {
        let _ = pty.killer.kill();
    }
}

#[derive(Serialize)]
#[serde(tag = "kind", rename_all = "lowercase")]
enum Paste {
    Text { text: String },
    Paths { paths: Vec<String> },
    Empty,
}

fn save_png(img: arboard::ImageData) -> Result<String, String> {
    // ponytail: files pile up in %TEMP%\mabo-paste; Windows' temp cleanup handles them.
    let dir = std::env::temp_dir().join("mabo-paste");
    std::fs::create_dir_all(&dir).map_err(err)?;
    let ms = SystemTime::now().duration_since(UNIX_EPOCH).map_err(err)?.as_millis();
    let path = dir.join(format!("paste-{ms}.png"));
    let file = BufWriter::new(std::fs::File::create(&path).map_err(err)?);
    let mut enc = png::Encoder::new(file, img.width as u32, img.height as u32);
    enc.set_color(png::ColorType::Rgba);
    enc.set_depth(png::BitDepth::Eight);
    enc.write_header().map_err(err)?.write_image_data(&img.bytes).map_err(err)?;
    Ok(path.to_string_lossy().into_owned())
}

/// Text pastes as text; an image becomes a temp PNG path; copied files paste as paths.
#[tauri::command]
fn clipboard_paste() -> Result<Paste, String> {
    let mut cb = arboard::Clipboard::new().map_err(err)?;
    if let Ok(files) = cb.get().file_list() {
        if !files.is_empty() {
            let paths = files.iter().map(|p| p.to_string_lossy().into_owned()).collect();
            return Ok(Paste::Paths { paths });
        }
    }
    if let Ok(text) = cb.get_text() {
        if !text.is_empty() {
            return Ok(Paste::Text { text });
        }
    }
    match cb.get_image() {
        Ok(img) => Ok(Paste::Paths { paths: vec![save_png(img)?] }),
        Err(_) => Ok(Paste::Empty),
    }
}

#[tauri::command]
fn clipboard_copy(text: String) -> Result<(), String> {
    arboard::Clipboard::new().map_err(err)?.set_text(text).map_err(err)
}

const MAX_DOC_BYTES: u64 = 5 * 1024 * 1024;

#[tauri::command]
fn read_text(path: String) -> Result<String, String> {
    let len = std::fs::metadata(&path).map_err(err)?.len();
    if len > MAX_DOC_BYTES {
        return Err(format!("file is {} MB; limit is 5 MB", len / 1024 / 1024));
    }
    let bytes = std::fs::read(&path).map_err(err)?;
    Ok(String::from_utf8_lossy(&bytes).into_owned())
}

#[tauri::command]
fn resolve_paths(cwd: Option<String>, candidates: Vec<String>) -> Vec<Option<String>> {
    candidates
        .iter()
        .map(|c| util::resolve_path(cwd.as_deref(), c).map(|p| p.to_string_lossy().into_owned()))
        .collect()
}

/// Folder passed on the command line (`mabo --cwd <dir>`, used by the Explorer menu).
#[tauri::command]
fn initial_cwd() -> Option<String> {
    util::cwd_from_args(std::env::args())
}

fn main() {
    tauri::Builder::default()
        // Must be first: a second launch (e.g. "Open MABO here") opens a tab in the running window.
        .plugin(tauri_plugin_single_instance::init(|app, argv, _cwd| {
            let _ = app.emit("open-tab", util::cwd_from_args(argv.into_iter()));
            if let Some(win) = app.get_webview_window("main") {
                let _ = win.unminimize();
                let _ = win.set_focus();
            }
        }))
        .plugin(tauri_plugin_opener::init())
        .plugin(tauri_plugin_notification::init())
        .plugin(tauri_plugin_updater::Builder::new().build())
        .plugin(tauri_plugin_process::init())
        .manage(Ptys::default())
        .invoke_handler(tauri::generate_handler![
            pty_spawn,
            pty_write,
            pty_resize,
            pty_kill,
            clipboard_paste,
            clipboard_copy,
            read_text,
            initial_cwd,
            resolve_paths
        ])
        .run(tauri::generate_context!())
        .expect("error while running MABO");
}
