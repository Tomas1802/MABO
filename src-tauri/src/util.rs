use std::path::{Path, PathBuf};

/// Decodes a PTY chunk as UTF-8, keeping an incomplete trailing sequence in `carry`
/// so multi-byte characters split across reads aren't mangled.
pub fn decode_utf8(carry: &mut Vec<u8>, chunk: &[u8]) -> String {
    carry.extend_from_slice(chunk);
    let cut = match std::str::from_utf8(carry) {
        Err(e) if e.error_len().is_none() => e.valid_up_to(),
        _ => carry.len(),
    };
    let tail = carry.split_off(cut);
    let text = String::from_utf8_lossy(carry).into_owned();
    *carry = tail;
    text
}

/// Strips up to two trailing `:N` suffixes (`file.rs:12:5`) without touching a drive colon.
fn strip_line_col(s: &str) -> &str {
    let mut s = s;
    for _ in 0..2 {
        match s.rfind(':') {
            Some(i) if i > 1 && i + 1 < s.len() && s[i + 1..].bytes().all(|b| b.is_ascii_digit()) => {
                s = &s[..i]
            }
            _ => break,
        }
    }
    s
}

/// Turns a token seen in terminal output into an existing path, relative to `cwd` if needed.
pub fn resolve_path(cwd: Option<&str>, raw: &str) -> Option<PathBuf> {
    let t = raw
        .trim_start_matches(|c: char| "\"'`([{<".contains(c))
        .trim_end_matches(|c: char| "\"'`)]}>,;.:".contains(c));
    let t = strip_line_col(t);
    if t.is_empty() {
        return None;
    }
    let p = Path::new(t);
    let full = if p.is_absolute() { p.to_path_buf() } else { Path::new(cwd?).join(p) };
    full.exists().then_some(full)
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn utf8_split_across_chunks() {
        let bytes = "añ✓".as_bytes();
        let mut carry = Vec::new();
        let a = decode_utf8(&mut carry, &bytes[..2]); // 'a' + first byte of 'ñ'
        let b = decode_utf8(&mut carry, &bytes[2..]);
        assert_eq!(a, "a");
        assert_eq!(b, "ñ✓");
        assert!(carry.is_empty());
    }

    #[test]
    fn utf8_invalid_bytes_do_not_stall() {
        let mut carry = Vec::new();
        assert_eq!(decode_utf8(&mut carry, &[b'x', 0xff, b'y']), "x\u{fffd}y");
        assert!(carry.is_empty());
    }

    #[test]
    fn line_col_stripping() {
        assert_eq!(strip_line_col("src/main.rs:12:5"), "src/main.rs");
        assert_eq!(strip_line_col("src/main.rs:12"), "src/main.rs");
        assert_eq!(strip_line_col("C:\\x\\y.rs"), "C:\\x\\y.rs");
        assert_eq!(strip_line_col("C:"), "C:");
    }

    #[test]
    fn resolves_relative_and_absolute() {
        let dir = std::env::temp_dir().join("mabo-test");
        std::fs::create_dir_all(dir.join("sub")).unwrap();
        std::fs::write(dir.join("sub").join("f.txt"), "x").unwrap();
        let cwd = dir.to_str();
        assert_eq!(resolve_path(cwd, "sub/f.txt:3:1"), Some(dir.join("sub/f.txt")));
        assert_eq!(resolve_path(cwd, "(sub/f.txt)."), Some(dir.join("sub/f.txt")));
        let abs = dir.join("sub").join("f.txt");
        assert_eq!(resolve_path(None, abs.to_str().unwrap()), Some(abs.clone()));
        assert_eq!(resolve_path(cwd, "nope.txt"), None);
        assert_eq!(resolve_path(None, "sub/f.txt"), None);
    }
}
