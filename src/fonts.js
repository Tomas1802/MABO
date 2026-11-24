// Terminal fonts. Bundled ones ship inside the app (Latin subset, regular + bold);
// Windows ones are always present on Windows 10/11.
import '@fontsource/jetbrains-mono/latin-400.css';
import '@fontsource/jetbrains-mono/latin-700.css';
import '@fontsource/fira-code/latin-400.css';
import '@fontsource/fira-code/latin-700.css';
import '@fontsource/ibm-plex-mono/latin-400.css';
import '@fontsource/ibm-plex-mono/latin-700.css';
import '@fontsource/source-code-pro/latin-400.css';
import '@fontsource/source-code-pro/latin-700.css';
import '@fontsource/geist-mono/latin-400.css';
import '@fontsource/geist-mono/latin-700.css';
import '@fontsource/victor-mono/latin-400.css';
import '@fontsource/victor-mono/latin-700.css';
import '@fontsource/space-mono/latin-400.css';
import '@fontsource/space-mono/latin-700.css';

export const FONTS = [
  'Cascadia Mono',
  'Cascadia Code',
  'JetBrains Mono',
  'Fira Code',
  'Geist Mono',
  'IBM Plex Mono',
  'Source Code Pro',
  'Victor Mono',
  'Space Mono',
  'Consolas',
];

export const DEFAULT_FONT = 'Cascadia Mono';

// Fallbacks cover glyphs missing from the Latin subsets (box drawing, symbols, ❯ …).
export const fontStack = (name) => `'${name}', 'Cascadia Mono', Consolas, monospace`;

/** Resolves once the font's faces are ready, so xterm measures cells with the right metrics. */
export async function loadFont(name) {
  try {
    await Promise.all([document.fonts.load(`14px '${name}'`), document.fonts.load(`bold 14px '${name}'`)]);
  } catch (e) {
    console.error(`font ${name} failed to load`, e);
  }
}
