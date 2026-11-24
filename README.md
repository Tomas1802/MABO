<p align="center">
  <img src="app-icon.svg" width="96" alt="MABO logo" />
</p>

<h1 align="center">MABO</h1>

<p align="center">
  A fast, beautiful, lightweight terminal for Windows — built for working alongside coding agents.
  <br />
  <a href="https://github.com/Tomas1802/MABO/releases/latest"><b>Download for Windows</b></a>
</p>

---

## Why MABO

Agents like Claude Code, Codex and Gemini CLI now live in the terminal. MABO is a general-purpose
terminal that makes that workflow pleasant without locking you into any one tool:

- **Knows when an agent needs you.** Desktop notifications (OSC 9 / 777 / 99) badge the tab, flash the
  taskbar and show a Windows toast. `Ctrl+Shift+U` jumps to the next tab waiting on you.
- **Progress at a glance.** OSC 9;4 progress shows on the tab and on the Windows taskbar icon.
- **Flicker-free.** Synchronized output (mode 2026) keeps agent TUIs from tearing.
- **Command blocks.** Built-in PowerShell integration marks each command; the scrollbar shows green/red
  per exit code, `Ctrl+Shift+↑/↓` jumps between commands and `Ctrl+Shift+O` copies the last output.
- **Clickable everything.** URLs open in the browser; file paths open Explorer at the file; Markdown files
  open rendered in a tab right next to your terminal.
- **Images in, the easy way.** Paste a screenshot or drag files in — they arrive as file paths agents can read.
- **Full paste.** `Ctrl+Shift+V` types the clipboard in instead of pasting, so agents show the whole text
  instead of collapsing it.
- **Looks good.** 9 themes, 10 fonts (7 bundled), a clean prompt with git branch, frameless window.
- **Small and fast.** ~3 MB installer. Rust + system WebView2; no Electron.
- **Updates itself.** New releases download in the background; one click restarts into the new version.

## Install

Download `MABO_x.y.z_x64-setup.exe` from the [latest release](https://github.com/Tomas1802/MABO/releases/latest)
and run it. No admin rights needed.

> Windows SmartScreen may warn about an unrecognized app until the installer is code-signed.
> Choose **More info → Run anyway**.

### Claude Code notifications

To get Claude Code's "needs input" alerts in MABO:

```sh
claude config set --global preferredNotifChannel iterm2
```

## Keyboard shortcuts

| Keys | Action |
| --- | --- |
| `Ctrl+Shift+T` / `Ctrl+Shift+W` | New tab / close tab (middle-click a tab also closes) |
| `Ctrl+Tab` / `Ctrl+Shift+Tab` | Next / previous tab |
| `Ctrl+Shift+U` | Jump to the next tab that needs attention |
| `Ctrl+C` (with selection) / `Ctrl+Shift+C` | Copy |
| `Ctrl+V` | Paste (images and copied files paste as paths) |
| `Ctrl+Shift+V` | Type the clipboard in (no paste collapsing) |
| `Shift+Enter` | Newline without submitting (agent CLIs) |
| `Ctrl+Shift+↑` / `Ctrl+Shift+↓` | Previous / next command |
| `Ctrl+Shift+O` | Copy the last command's output |
| `Ctrl+=` / `Ctrl+-` / `Ctrl+0` | Font size bigger / smaller / reset |

## Build from source

Requirements: [Rust](https://rustup.rs), Node.js 20+, and the Visual Studio C++ Build Tools.

```sh
npm install
npm run tauri dev     # run with hot reload
npm run tauri build   # produce the installer in src-tauri/target/release/bundle/nsis
```

Tests: `cargo test --manifest-path src-tauri/Cargo.toml`

## Releasing

1. Bump the version in `package.json`, `src-tauri/Cargo.toml` and `src-tauri/tauri.conf.json`.
2. Commit, then tag and push: `git tag v2.0.1 && git push origin v2.0.1`.
3. The Release workflow builds, signs and publishes the installer plus `latest.json`;
   installed copies update themselves.

The workflow needs two repository secrets: `TAURI_SIGNING_PRIVATE_KEY` and
`TAURI_SIGNING_PRIVATE_KEY_PASSWORD` (the updater signing key, not a code-signing certificate).

## Project layout

```
src/            UI: tabs, themes, fonts, links, Markdown viewer, updater (vanilla JS + xterm.js)
src-tauri/src/  Rust: PTY management (ConPTY), clipboard, path resolution, shell integration
```

## License

[MIT](LICENSE). The MABO name previously belonged to a Java DSL project; its history and releases
(v1.x) remain in this repository's history.
