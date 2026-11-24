import { loadPref, savePref } from './prefs.js';

// Each theme is an xterm ITheme; the window chrome derives its colors from it.
const t = (bg, fg, sel, cursor, n, b = n) => ({
  background: bg,
  foreground: fg,
  cursor,
  cursorAccent: bg,
  selectionBackground: sel,
  black: n[0], red: n[1], green: n[2], yellow: n[3],
  blue: n[4], magenta: n[5], cyan: n[6], white: n[7],
  brightBlack: b[8] ?? n[8], brightRed: b[1], brightGreen: b[2], brightYellow: b[3],
  brightBlue: b[4], brightMagenta: b[5], brightCyan: b[6], brightWhite: b[7],
});

const brights = (n, black, white) => [...n.slice(0, 7), white, black];

const tokyo = ['#15161e', '#f7768e', '#9ece6a', '#e0af68', '#7aa2f7', '#bb9af7', '#7dcfff', '#a9b1d6', '#414868'];
const mocha = ['#45475a', '#f38ba8', '#a6e3a1', '#f9e2af', '#89b4fa', '#f5c2e7', '#94e2d5', '#bac2de', '#585b70'];
const dracula = ['#21222c', '#ff5555', '#50fa7b', '#f1fa8c', '#bd93f9', '#ff79c6', '#8be9fd', '#f8f8f2', '#6272a4'];
const draculaB = ['#21222c', '#ff6e6e', '#69ff94', '#ffffa5', '#d6acff', '#ff92df', '#a4ffff', '#ffffff', '#6272a4'];
const nord = ['#3b4252', '#bf616a', '#a3be8c', '#ebcb8b', '#81a1c1', '#b48ead', '#88c0d0', '#e5e9f0', '#4c566a'];
const gruv = ['#282828', '#cc241d', '#98971a', '#d79921', '#458588', '#b16286', '#689d6a', '#a89984', '#928374'];
const gruvB = ['#282828', '#fb4934', '#b8bb26', '#fabd2f', '#83a598', '#d3869b', '#8ec07c', '#ebdbb2', '#928374'];
const onedark = ['#282c34', '#e06c75', '#98c379', '#e5c07b', '#61afef', '#c678dd', '#56b6c2', '#abb2bf', '#5c6370'];
const rose = ['#26233a', '#eb6f92', '#31748f', '#f6c177', '#9ccfd8', '#c4a7e7', '#ebbcba', '#e0def4', '#6e6a86'];
const solar = ['#073642', '#dc322f', '#859900', '#b58900', '#268bd2', '#d33682', '#2aa198', '#eee8d5', '#002b36'];
const solarB = ['#073642', '#cb4b16', '#586e75', '#657b83', '#839496', '#6c71c4', '#93a1a1', '#fdf6e3', '#002b36'];
const gh = ['#24292f', '#cf222e', '#116329', '#4d2d00', '#0969da', '#8250df', '#1b7c83', '#6e7781', '#57606a'];
const ghB = ['#24292f', '#a40e26', '#1a7f37', '#633c01', '#218bff', '#a475f9', '#3192aa', '#8c959f', '#57606a'];

export const THEMES = {
  'Tokyo Night': t('#1a1b26', '#c0caf5', '#33467c', '#c0caf5', tokyo, brights(tokyo, '#414868', '#c0caf5')),
  'Catppuccin Mocha': t('#1e1e2e', '#cdd6f4', '#585b70', '#f5e0dc', mocha, brights(mocha, '#585b70', '#a6adc8')),
  Dracula: t('#282a36', '#f8f8f2', '#44475a', '#f8f8f2', dracula, draculaB),
  Nord: t('#2e3440', '#d8dee9', '#434c5e', '#d8dee9', nord, brights(nord, '#4c566a', '#eceff4')),
  'Gruvbox Dark': t('#282828', '#ebdbb2', '#504945', '#ebdbb2', gruv, gruvB),
  'One Dark': t('#282c34', '#abb2bf', '#3e4451', '#528bff', onedark, brights(onedark, '#5c6370', '#ffffff')),
  'Rosé Pine': t('#191724', '#e0def4', '#403d52', '#524f67', rose, brights(rose, '#6e6a86', '#e0def4')),
  'Solarized Light': t('#fdf6e3', '#657b83', '#eee8d5', '#586e75', solar, solarB),
  'GitHub Light': t('#ffffff', '#24292f', '#b6e3ff', '#0969da', gh, ghB),
};

export const loadThemeName = () => loadPref('theme', (v) => v in THEMES, 'Tokyo Night');
export const saveThemeName = (name) => savePref('theme', name);

export function applyChrome(theme) {
  const s = document.documentElement.style;
  s.setProperty('--bg', theme.background);
  s.setProperty('--fg', theme.foreground);
  s.setProperty('--accent', theme.blue);
  s.setProperty('--accent2', theme.magenta);
  s.setProperty('--muted', theme.brightBlack);
  s.setProperty('--warn', theme.yellow);
  s.setProperty('--error', theme.red);
}
