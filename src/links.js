// File-path links: tokens that exist on disk become clickable; `onOpen` decides what a click does.
import { invoke } from '@tauri-apps/api/core';

const TOKEN = /[^\s"'`<>|()[\]{},;]+/g;
const looksLikePath = (s) =>
  !s.includes('://') && /\w/.test(s) && (/[\\/]/.test(s) || /\.\w{1,10}(:\d+){0,2}\.?$/.test(s));

export function fileLinkProvider(term, getCwd, onOpen) {
  return {
    async provideLinks(y, callback) {
      const line = term.buffer.active.getLine(y - 1)?.translateToString(true) ?? '';
      const hits = [...line.matchAll(TOKEN)].filter((m) => looksLikePath(m[0]));
      if (!hits.length) return callback(undefined);
      try {
        const resolved = await invoke('resolve_paths', { cwd: getCwd() ?? null, candidates: hits.map((m) => m[0]) });
        const links = hits.flatMap((m, i) =>
          resolved[i]
            ? [{
                text: m[0],
                range: { start: { x: m.index + 1, y }, end: { x: m.index + m[0].length, y } },
                activate: (e) => onOpen(resolved[i], e),
              }]
            : [],
        );
        callback(links.length ? links : undefined);
      } catch (e) {
        console.error('resolve_paths failed', e);
        callback(undefined);
      }
    },
  };
}
