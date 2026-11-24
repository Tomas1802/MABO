// Per-user UI preferences in localStorage; always falls back cleanly if storage is unavailable.

export function loadPref(key, isValid, fallback) {
  try {
    const value = localStorage.getItem(`mabo.${key}`);
    if (value !== null && isValid(value)) return value;
  } catch { /* storage unavailable: use fallback */ }
  return fallback;
}

export function savePref(key, value) {
  try { localStorage.setItem(`mabo.${key}`, String(value)); } catch { /* non-critical */ }
}
