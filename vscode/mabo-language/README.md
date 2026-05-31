# MABO para VS Code

Extension simple para trabajar con archivos `.mabo` en VS Code.

## Que incluye

- Colores de sintaxis para archivos `.mabo`.
- Comentarios con `#`.
- Pares de parentesis, corchetes y comillas.
- Comando `MABO: Ejecutar archivo actual`.
- Comando `MABO: Validar archivo actual`.

## Instalar

1. Instala MABO y confirma que `mabo` funciona en PowerShell.
2. Abre `https://github.com/Tomas1802/MABO/releases/latest`.
3. Busca la seccion `Assets` al final de la release.
4. Descarga `mabo-language-0.1.0.vsix`. Si no ves la lista, presiona `Assets` para desplegarla.
5. Abre VS Code.
6. Ve a Extensions.
7. Abre el menu de tres puntos.
8. Elige `Install from VSIX...`.
9. Selecciona `mabo-language-0.1.0.vsix`.
10. Abre un archivo `.mabo`.

Si clonaste el repositorio, primero crea el archivo `.vsix`:

```powershell
cd vscode/mabo-language
npx --yes @vscode/vsce package
code --install-extension mabo-language-0.1.0.vsix
```

Tambien puedes instalar desde PowerShell:

```powershell
code --install-extension "C:\ruta\mabo-language-0.1.0.vsix"
```

## Usar

Abre un archivo `.mabo`, presiona `Ctrl+Shift+P`, escribe `MABO` y elige:

- `MABO: Ejecutar archivo actual`: ejecuta el archivo.
- `MABO: Validar archivo actual`: valida sintaxis y semantica sin ejecutar acciones.

## Configurar la ruta de MABO

Si `mabo` no esta en PATH, configura `mabo.executable` en VS Code:

```json
{
  "mabo.executable": "C:\\Users\\tu_usuario\\AppData\\Local\\MABO\\bin\\mabo.bat"
}
```
