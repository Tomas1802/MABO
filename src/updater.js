// Warp-style auto-update: check GitHub Releases on start and every few hours,
// download in the background, then offer a one-click restart.
import { check } from '@tauri-apps/plugin-updater';
import { relaunch } from '@tauri-apps/plugin-process';

const CHECK_EVERY_MS = 6 * 60 * 60 * 1000;
const FIRST_CHECK_DELAY_MS = 10 * 1000;

let ready = null; // downloaded update awaiting install

async function checkForUpdate(button) {
  if (ready) return;
  try {
    const update = await check();
    if (!update) return;
    await update.download();
    ready = update;
    button.textContent = `Update to ${update.version} · Restart`;
    button.hidden = false;
  } catch (e) {
    // Offline or GitHub unreachable: try again on the next interval.
    console.warn('update check failed', e);
  }
}

export function startUpdater(button) {
  if (import.meta.env.DEV) return; // dev builds never self-update
  button.addEventListener('click', async () => {
    if (!ready) return;
    button.disabled = true;
    button.textContent = 'Installing…';
    try {
      await ready.install();
      await relaunch();
    } catch (e) {
      console.error('update install failed', e);
      button.disabled = false;
      button.textContent = 'Update failed · Retry';
    }
  });
  setTimeout(() => checkForUpdate(button), FIRST_CHECK_DELAY_MS);
  setInterval(() => checkForUpdate(button), CHECK_EVERY_MS);
}
