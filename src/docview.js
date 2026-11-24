// Markdown document tabs: rendered, sanitized, re-read from disk whenever shown.
import { invoke } from '@tauri-apps/api/core';
import { openUrl, revealItemInDir } from '@tauri-apps/plugin-opener';
import { marked } from 'marked';
import DOMPurify from 'dompurify';

export const isMarkdown = (path) => /\.(md|markdown|mdx)$/i.test(path);

const dirOf = (path) => path.replace(/[\\/][^\\/]*$/, '');
const baseName = (path) => path.split(/[\\/]/).pop();

/**
 * @param {string} path absolute file path
 * @param {(path: string) => void} onOpenPath called for local links inside the document
 */
export function createDocTab(path, onOpenPath) {
  const el = document.createElement('div');
  el.className = 'doc-host';
  el.tabIndex = -1;
  el.innerHTML = `
    <div class="doc-bar">
      <span class="doc-path"></span>
      <button class="doc-btn" data-act="reload" title="Reload">&#xE72C;</button>
      <button class="doc-btn wide" data-act="reveal">Show in Explorer</button>
    </div>
    <article class="md"></article>`;
  el.querySelector('.doc-path').textContent = path;
  const article = el.querySelector('.md');

  async function load() {
    try {
      const text = await invoke('read_text', { path });
      // Sanitize: this page can call into the app, so file content must never run script.
      article.innerHTML = DOMPurify.sanitize(marked.parse(text, { gfm: true }));
    } catch (e) {
      article.textContent = `Could not read file: ${e}`;
    }
  }

  el.querySelector('[data-act="reload"]').addEventListener('click', load);
  el.querySelector('[data-act="reveal"]').addEventListener('click', () =>
    revealItemInDir(path).catch((e) => console.error('reveal failed', e)),
  );

  article.addEventListener('click', async (e) => {
    const a = e.target.closest('a[href]');
    if (!a) return;
    e.preventDefault();
    const href = a.getAttribute('href');
    if (href.startsWith('#')) {
      article.querySelector(`[id="${CSS.escape(href.slice(1))}"]`)?.scrollIntoView({ behavior: 'smooth' });
    } else if (/^(https?|mailto):/i.test(href)) {
      openUrl(href).catch((err) => console.error('open url failed', err));
    } else {
      const local = decodeURIComponent(href.replace(/^file:\/\/\/?/i, '').replace(/#.*$/, ''));
      const [resolved] = await invoke('resolve_paths', { cwd: dirOf(path), candidates: [local] });
      if (resolved) onOpenPath(resolved);
    }
  });

  return {
    kind: 'doc',
    path,
    el,
    title: baseName(path),
    glyph: '#',
    show() {
      load();
      el.focus();
    },
    dispose() {},
  };
}
