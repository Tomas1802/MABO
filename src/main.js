import { Terminal } from '@xterm/xterm';
import { FitAddon } from '@xterm/addon-fit';
import { WebglAddon } from '@xterm/addon-webgl';
import { WebLinksAddon } from '@xterm/addon-web-links';
import { invoke, Channel } from '@tauri-apps/api/core';
import { listen } from '@tauri-apps/api/event';
import { getCurrentWindow, UserAttentionType } from '@tauri-apps/api/window';
import { getCurrentWebview } from '@tauri-apps/api/webview';
import { openUrl, revealItemInDir } from '@tauri-apps/plugin-opener';
import { isPermissionGranted, requestPermission, sendNotification } from '@tauri-apps/plugin-notification';
import '@xterm/xterm/css/xterm.css';
import './style.css';
import { THEMES, loadThemeName, saveThemeName, applyChrome } from './themes.js';
import { attachIntegration, jumpToPrompt, lastOutput } from './integration.js';
import { fileLinkProvider } from './links.js';
import { createDocTab, isMarkdown } from './docview.js';
import { FONTS, DEFAULT_FONT, fontStack, loadFont } from './fonts.js';
import { loadPref, savePref } from './prefs.js';
import { startUpdater } from './updater.js';

const win = getCurrentWindow();
const tabsEl = document.getElementById('tabs');
const termsEl = document.getElementById('terms');
const themeEl = document.getElementById('theme');
const fontEl = document.getElementById('font');

const tabs = [];
let active = null;
// Unique across page reloads, so a stale shell's exit event can't close a new tab.
let nextId = (Date.now() % 1_000_000) * 1000;
let themeName = loadThemeName();
let fontName = loadPref('font', (v) => FONTS.includes(v), DEFAULT_FONT);
let fontSize = Number(loadPref('fontSize', (v) => +v >= 8 && +v <= 32, '14'));
let canNotify = false;

const report = (what) => (e) => console.error(`${what} failed`, e);

// ---------- tabs ----------

function renderTab(tab) {
  tab.tabEl.classList.toggle('active', tab === active);
  tab.tabEl.classList.toggle('attention', tab.attention);
  tab.tabEl.querySelector('.title').textContent = tab.title;
  tab.tabEl.title = tab.cwd ?? tab.path ?? tab.title;
  const bar = tab.tabEl.querySelector('.progress');
  if (tab.progress === 'none') delete tab.tabEl.dataset.progress;
  else tab.tabEl.dataset.progress = tab.progress;
  bar.style.transform = tab.progress === 'indeterminate' ? '' : `scaleX(${tab.percent / 100})`;
}

function activate(tab) {
  const prev = active;
  active = tab;
  tab.attention = false;
  if (prev) {
    prev.el.classList.remove('active');
    renderTab(prev);
  }
  tab.el.classList.add('active');
  renderTab(tab);
  tab.show();
}

function makeTabEl(tab) {
  const el = document.createElement('div');
  el.className = 'tab';
  el.innerHTML = '<span class="glyph"></span><span class="dot"></span><span class="title"></span><button class="close" title="Close (Ctrl+Shift+W)">×</button><span class="progress"></span>';
  el.querySelector('.glyph').textContent = tab.glyph;
  el.addEventListener('mousedown', (e) => {
    if (e.button === 1) closeTab(tab);
    else if (e.button === 0 && !e.target.classList.contains('close')) activate(tab);
  });
  el.querySelector('.close').addEventListener('click', () => closeTab(tab));
  return el;
}

async function newTab() {
  const id = nextId++;
  const el = document.createElement('div');
  el.className = 'term-host';
  termsEl.append(el);

  const term = new Terminal({
    fontFamily: fontStack(fontName),
    fontSize,
    lineHeight: 1.15,
    cursorBlink: true,
    cursorStyle: 'bar',
    scrollback: 10000,
    allowProposedApi: true,
    theme: THEMES[themeName],
    overviewRuler: { width: 8 },
    // OSC 8 hyperlinks (Claude Code prints file:// links this way).
    linkHandler: {
      allowNonHttpProtocols: true,
      activate: (_e, uri) => {
        if (/^file:/i.test(uri)) openPath(decodeURIComponent(uri.replace(/^file:\/\/\/?/i, '')).replace(/\//g, '\\'));
        else if (/^(https?|mailto):/i.test(uri)) openUrl(uri).catch(report('open url'));
      },
    },
  });
  const fit = new FitAddon();
  term.loadAddon(fit);
  term.loadAddon(new WebLinksAddon((_e, uri) => openUrl(uri).catch(report('open url'))));
  term.open(el);
  try {
    const gl = new WebglAddon();
    gl.onContextLoss(() => gl.dispose());
    term.loadAddon(gl);
  } catch (e) {
    report('webgl (falling back to DOM renderer)')(e);
  }

  const tab = {
    kind: 'term', id, term, fit, el, glyph: '❯', title: 'Terminal', cwd: null,
    show() {
      fit.fit();
      term.focus();
    },
    dispose() {
      invoke('pty_kill', { id }).catch(() => {});
      term.dispose();
    },
  };
  addTab(tab, false);

  term.registerLinkProvider(fileLinkProvider(term, () => tab.cwd, openPath));
  attachIntegration(tab, { onNotify, onProgress, onCwd: renderTab });
  term.attachCustomKeyEventHandler((e) => handleKey(e, tab));
  term.onData((data) => invoke('pty_write', { id, data }).catch(() => {}));
  term.onResize(({ cols, rows }) => invoke('pty_resize', { id, cols, rows }).catch(() => {}));
  term.onTitleChange((title) => {
    tab.title = title || 'Terminal';
    renderTab(tab);
  });

  activate(tab);
  const onData = new Channel();
  onData.onmessage = (data) => term.write(data);
  try {
    await invoke('pty_spawn', { id, cols: term.cols, rows: term.rows, onData });
  } catch (e) {
    term.write(`\x1b[31mFailed to start shell: ${e}\x1b[0m\r\n`);
  }
}

/** Registers a tab; `besideActive` inserts it right after the current tab instead of at the end. */
function addTab(tab, besideActive) {
  Object.assign(tab, { attention: false, progress: 'none', percent: 0 });
  tab.tabEl = makeTabEl(tab);
  const at = besideActive && active ? tabs.indexOf(active) + 1 : tabs.length;
  tabsEl.insertBefore(tab.tabEl, tabs[at]?.tabEl ?? null);
  tabs.splice(at, 0, tab);
}

function openPath(path) {
  if (!isMarkdown(path)) {
    revealItemInDir(path).catch(report('reveal'));
    return;
  }
  const existing = tabs.find((t) => t.kind === 'doc' && t.path.toLowerCase() === path.toLowerCase());
  if (existing) return activate(existing);
  const tab = createDocTab(path, openPath);
  termsEl.append(tab.el);
  addTab(tab, true);
  activate(tab);
}

function closeTab(tab) {
  const i = tabs.indexOf(tab);
  if (i < 0) return;
  tabs.splice(i, 1);
  tab.dispose();
  tab.el.remove();
  tab.tabEl.remove();
  if (!tabs.length) return void win.close();
  if (tab === active) activate(tabs[Math.min(i, tabs.length - 1)]);
  syncTaskbar();
}

function cycle(dir) {
  const i = tabs.indexOf(active);
  activate(tabs[(i + dir + tabs.length) % tabs.length]);
}

function nextAttention() {
  const i = tabs.indexOf(active);
  const order = [...tabs.slice(i + 1), ...tabs.slice(0, i)];
  const tab = order.find((t) => t.attention);
  if (tab) activate(tab);
}

// ---------- agent signals ----------

function onNotify(tab, title, body) {
  const focused = document.hasFocus();
  if (tab === active && focused) return;
  tab.attention = true;
  renderTab(tab);
  if (focused) return;
  win.requestUserAttention(UserAttentionType.Informational).catch(() => {});
  // The bell only badges; real notifications (OSC 9/777/99) also toast.
  if (body != null && canNotify) sendNotification({ title: title || tab.title, body: body || 'Needs your attention' });
}

function onProgress(tab, state, percent) {
  tab.progress = state;
  tab.percent = percent;
  renderTab(tab);
  syncTaskbar();
}

const RANK = { none: 0, normal: 1, indeterminate: 2, paused: 3, error: 4 };

function syncTaskbar() {
  // Taskbar shows the most severe progress across all tabs.
  const worst = tabs.reduce((a, t) => (RANK[t.progress] > RANK[a.progress] ? t : a), { progress: 'none', percent: 0 });
  win.setProgressBar({ status: worst.progress, progress: Math.round(worst.percent) }).catch(() => {});
}

// ---------- clipboard & keys ----------

const quote = (p) => (/\s/.test(p) ? `"${p}"` : p);

async function paste(tab) {
  try {
    const r = await invoke('clipboard_paste');
    if (r.kind === 'text') tab.term.paste(r.text);
    else if (r.kind === 'paths') tab.term.paste(r.paths.map(quote).join(' '));
  } catch (e) {
    report('paste')(e);
  }
}

const TYPE_CHUNK = 32;
const TYPE_DELAY_MS = 10;
const sleep = (ms) => new Promise((r) => setTimeout(r, ms));

/**
 * Sends clipboard text as if typed: no bracketed-paste markers, newlines as ESC+CR
 * (Shift+Enter), in small timed chunks so agents don't detect it as a paste.
 */
async function typePaste(tab) {
  try {
    const r = await invoke('clipboard_paste');
    if (r.kind !== 'text') return paste(tab);
    const text = r.text.replace(/\r\n?/g, '\n');
    for (let i = 0; i < text.length; i += TYPE_CHUNK) {
      const data = text.slice(i, i + TYPE_CHUNK).replace(/\n/g, '\x1b\r');
      await invoke('pty_write', { id: tab.id, data });
      await sleep(TYPE_DELAY_MS);
    }
  } catch (e) {
    report('type paste')(e);
  }
}

function copy(text) {
  if (text) invoke('clipboard_copy', { text }).catch(report('copy'));
}

function setFontSize(size) {
  fontSize = Math.min(32, Math.max(8, size));
  savePref('fontSize', fontSize);
  for (const t of tabs.filter((t) => t.term)) {
    t.term.options.fontSize = fontSize;
    t.fit.fit();
  }
}

function navAction(e, k, ctrl, tab) {
  return (
    (ctrl && e.shiftKey && k === 't' && newTab) ||
    (ctrl && e.shiftKey && k === 'w' && (() => closeTab(tab))) ||
    (ctrl && k === 'tab' && (() => cycle(e.shiftKey ? -1 : 1))) ||
    (ctrl && e.shiftKey && k === 'u' && nextAttention)
  );
}

function termAction(e, k, ctrl, tab) {
  return (
    (ctrl && e.shiftKey && k === 'arrowup' && (() => jumpToPrompt(tab, -1))) ||
    (ctrl && e.shiftKey && k === 'arrowdown' && (() => jumpToPrompt(tab, 1))) ||
    (ctrl && e.shiftKey && k === 'o' && (() => copy(lastOutput(tab)))) ||
    (ctrl && k === 'c' && (e.shiftKey || tab.term.hasSelection()) && (() => {
      copy(tab.term.getSelection());
      tab.term.clearSelection();
    })) ||
    (ctrl && k === 'v' && (() => (e.shiftKey ? typePaste(tab) : paste(tab)))) ||
    (ctrl && (k === '=' || k === '+') && (() => setFontSize(fontSize + 1))) ||
    (ctrl && k === '-' && (() => setFontSize(fontSize - 1))) ||
    (ctrl && k === '0' && (() => setFontSize(14))) ||
    // ponytail: xterm.js lacks the kitty keyboard protocol; ESC+CR is what Claude Code etc. read as a newline.
    (e.shiftKey && !e.ctrlKey && !e.altKey && k === 'enter' && (() => invoke('pty_write', { id: tab.id, data: '\x1b\r' })))
  );
}

function handleKey(e, tab) {
  if (e.type !== 'keydown') return true;
  const k = e.key.toLowerCase();
  const ctrl = e.ctrlKey && !e.altKey;
  const action = navAction(e, k, ctrl, tab) || (tab.term && termAction(e, k, ctrl, tab));
  if (!action) return true;
  e.preventDefault();
  action();
  return false;
}

// Doc tabs have no xterm, so their shortcuts come from the document.
document.addEventListener('keydown', (e) => {
  if (active?.kind === 'doc') handleKey(e, active);
});

// ---------- themes ----------

function setTheme(name) {
  themeName = name;
  saveThemeName(name);
  applyChrome(THEMES[name]);
  for (const t of tabs.filter((t) => t.term)) t.term.options.theme = THEMES[name];
}

for (const name of Object.keys(THEMES)) themeEl.add(new Option(name, name));
themeEl.value = themeName;
themeEl.addEventListener('change', () => {
  setTheme(themeEl.value);
  active?.show();
});
applyChrome(THEMES[themeName]);

// ---------- fonts ----------

async function setFont(name) {
  fontName = name;
  savePref('font', name);
  await loadFont(name);
  for (const t of tabs.filter((t) => t.term)) {
    t.term.options.fontFamily = fontStack(name);
    t.term.clearTextureAtlas();
    t.fit.fit();
  }
}

for (const name of FONTS) fontEl.add(new Option(name, name));
fontEl.value = fontName;
fontEl.addEventListener('change', () => {
  setFont(fontEl.value).then(() => active?.show());
});

// ---------- window ----------

document.getElementById('new-tab').addEventListener('click', newTab);
document.getElementById('win-min').addEventListener('click', () => win.minimize());
document.getElementById('win-max').addEventListener('click', () => win.toggleMaximize());
document.getElementById('win-close').addEventListener('click', () => win.close());
document.getElementById('titlebar').addEventListener('dblclick', (e) => {
  if (e.target.hasAttribute('data-tauri-drag-region')) win.toggleMaximize();
});

new ResizeObserver(() => active?.fit?.fit()).observe(termsEl);
window.addEventListener('focus', () => {
  if (active?.attention) activate(active);
});

listen('pty-exit', ({ payload }) => {
  const tab = tabs.find((t) => t.id === payload);
  if (tab) closeTab(tab);
});

getCurrentWebview().onDragDropEvent(({ payload }) => {
  if (payload.type === 'drop' && active?.term && payload.paths.length) {
    active.term.paste(payload.paths.map(quote).join(' '));
    active.term.focus();
  }
});

(async () => {
  canNotify = (await isPermissionGranted()) || (await requestPermission()) === 'granted';
})().catch(report('notification permission'));

loadFont(fontName).then(newTab).catch(report('first tab'));
startUpdater(document.getElementById('update'));
