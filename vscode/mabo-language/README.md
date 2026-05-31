# MABO Language

Simple VS Code support for `.mabo` files.

## Features

- Syntax highlighting.
- Line comments with `#`.
- Bracket and quote pairs.
- `MABO: Run Current File`.
- `MABO: Check Current File`.

Both commands call the installed `mabo` executable. If MABO is not on PATH, set `mabo.executable` in VS Code settings.

## Local development

From this folder:

```powershell
code .
```

Then press F5 to open an Extension Development Host.

## Install from VSIX

After packaging the extension as a `.vsix`, install it with:

```powershell
code --install-extension mabo-language-0.1.0.vsix
```

You can also use VS Code's Extensions view and choose `Install from VSIX...`.

## Configure MABO path

If `mabo` is not available in PATH, set the executable path in VS Code settings:

```json
{
  "mabo.executable": "C:\\Users\\tu_usuario\\AppData\\Local\\MABO\\bin\\mabo.bat"
}
```
