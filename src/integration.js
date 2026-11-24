// Escape-sequence integration: OSC 133 command blocks, OSC 9;9 cwd,
// OSC 9 / 777 / 99 notifications, OSC 9;4 progress, and the bell.

const PROGRESS = ['none', 'normal', 'error', 'indeterminate', 'paused'];

export function attachIntegration(tab, { onNotify, onProgress, onCwd }) {
  const term = tab.term;
  tab.blocks = []; // { prompt: IMarker, input?: IMarker, exit?: number }

  term.parser.registerOscHandler(133, (data) => {
    const [kind, arg] = data.split(';');
    const last = tab.blocks.at(-1);
    if (kind === 'A') {
      const prompt = term.registerMarker(0);
      if (prompt) tab.blocks.push({ prompt });
    } else if (kind === 'B' && last) {
      last.input = term.registerMarker(0);
    } else if (kind === 'D' && last && last.exit === undefined) {
      last.exit = Number(arg ?? 0) || 0;
      term.registerDecoration({
        marker: last.prompt,
        overviewRulerOptions: {
          color: last.exit === 0 ? term.options.theme.green : term.options.theme.red,
          position: 'left',
        },
      });
    }
    tab.blocks = tab.blocks.filter((b) => !b.prompt.isDisposed);
    return true;
  });

  term.parser.registerOscHandler(9, (data) => {
    if (data.startsWith('4;')) {
      const [, state, pct] = data.split(';');
      onProgress(tab, PROGRESS[Number(state)] ?? 'none', Number(pct) || 0);
    } else if (data.startsWith('9;')) {
      tab.cwd = data.slice(2).replace(/^"|"$/g, '');
      onCwd(tab);
    } else {
      onNotify(tab, null, data);
    }
    return true;
  });

  term.parser.registerOscHandler(777, (data) => {
    const [cmd, title, ...body] = data.split(';');
    if (cmd === 'notify') onNotify(tab, title, body.join(';'));
    return true;
  });

  // ponytail: kitty OSC 99 reduced to "payload = body"; multi-part chunks not merged.
  term.parser.registerOscHandler(99, (data) => {
    onNotify(tab, null, data.slice(data.indexOf(';') + 1));
    return true;
  });

  term.onBell(() => onNotify(tab, null, null));
}

export function jumpToPrompt(tab, dir) {
  const term = tab.term;
  const top = term.buffer.active.viewportY;
  const lines = tab.blocks.map((b) => b.prompt.line).filter((l) => l >= 0);
  const target = dir < 0 ? lines.filter((l) => l < top).at(-1) : lines.find((l) => l > top);
  if (target !== undefined) term.scrollToLine(target);
}

function readLines(term, from, to) {
  const buf = term.buffer.active;
  let out = '';
  for (let y = from; y <= to; y++) {
    const line = buf.getLine(y);
    if (!line) continue;
    out += (y > from && !line.isWrapped ? '\n' : '') + line.translateToString(true);
  }
  return out.replace(/\s+$/, '');
}

/** Output of the most recent finished command, or null. */
export function lastOutput(tab) {
  const done = tab.blocks.findLastIndex((b) => b.exit !== undefined && b.input && !b.input.isDisposed);
  if (done < 0) return null;
  const next = tab.blocks[done + 1];
  const end = next ? next.prompt.line - 1 : tab.term.buffer.active.length - 1;
  return readLines(tab.term, tab.blocks[done].input.line + 1, end);
}
