# DSL de Automatización Segura de Archivos

Un lenguaje de dominio específico (DSL) en español para automatizar operaciones de archivos y carpetas de forma segura, construido con Java y ANTLR4.

---

## Autores

| Nombre |
|--------|
| Diana Valentina Chicuasuque Rodriguez |
| Tomás Parra Monroy |
| Laura Valentina Pabón Cabezas |

Universidad Nacional de Colombia

---

## Descripción

Este proyecto implementa un lenguaje de scripting en español que permite automatizar tareas del sistema de archivos con validaciones de seguridad, modo de simulación, registro de auditoría y programación de tareas. El lenguaje es interpretado mediante un pipeline de dos fases: análisis semántico y ejecución, ambos construidos sobre un árbol de parseo generado por ANTLR4.

---

## Tecnologías

- **Java 17+** para ejecutar MABO
- **ANTLR 4.13.1** — generación de lexer/parser desde gramática `.g4`
- **Java NIO** — operaciones de archivos (`Files`, `Path`, `Paths`)
- **java.util.zip** — compresión y descompresión ZIP
- **ScheduledExecutorService** — programación de tareas periódicas
- **Gradle** — compilación, pruebas y regeneración de gramática
- **IntelliJ IDEA** — entorno de desarrollo recomendado

---

## Estructura del Proyecto

```
src/
├── Main.java                        # Punto de entrada
├── gen/                             # Código generado por ANTLR (no editar)
│   ├── proyectoLexer.java
│   ├── proyectoParser.java
│   ├── proyectoVisitor.java
│   └── proyectoBaseVisitor.java
├── visitors/
│   ├── SemanticVisitor.java         # Fase 1: validación semántica
│   └── ExecutionVisitor.java        # Fase 2: ejecución del DSL
├── filesystem/
│   └── FileSystemService.java       # Operaciones de archivos/carpetas y ZIP
├── security/
│   └── SecurityValidator.java       # Lista blanca de rutas permitidas
├── semantic/
│   └── ExecutionContext.java        # Variables, funciones y tareas en memoria
├── scheduler/
│   ├── SchedulerService.java        # Ejecución periódica de tareas
│   └── ScheduleStore.java           # Persistencia de tareas programadas
├── logs/
│   ├── LoggerService.java           # Log de ejecución (app.log)
│   ├── AuditService.java            # Log de auditoría NDJSON
│   └── TaskLogService.java          # Logs detallados por tarea
├── model/
│   └── Task.java                    # Modelo de tarea DSL
├── utils/
│   ├── FilterUtils.java             # Predicados de búsqueda de archivos
│   └── ConfirmationService.java     # Confirmación interactiva por consola
├── exceptions/
│   ├── ExecutionException.java
│   ├── SemanticException.java
│   ├── SecurityException.java
│   └── ReturnException.java         # Control de flujo para retorno de funciones
└── test/java/                       # Pruebas automatizadas
examples/
├── limpieza.mabo                    # Ejemplo de limpieza de archivos
└── organizar-pdf.mabo               # Ejemplo de organización de PDF
docs/
└── index.html                       # Documentación de usuario
scripts/
├── install-windows.ps1              # Instalador local/remoto
├── uninstall-windows.ps1            # Desinstalador
├── package-release.ps1              # Empaquetado de release
└── publish-release.ps1              # Publicación con GitHub CLI
vscode/mabo-language/                # Extensión simple para VS Code
.idea/grammar/
└── proyecto.g4                      # Gramática ANTLR del DSL
```

---

## Cómo Ejecutar

### Requisitos

1. JDK 17 o superior. En Windows puedes instalarlo con `winget install EclipseAdoptium.Temurin.21.JDK`.
2. Gradle para compilar desde el codigo fuente.
3. IntelliJ IDEA (recomendado)

### Desde IntelliJ IDEA

1. Abrir el proyecto en IntelliJ
2. Agregar `antlr-runtime-4.13.1.jar` en **File → Project Structure → Modules → Dependencies**
3. Si se modifica la gramática `proyecto.g4`: clic derecho sobre el archivo → *Generate ANTLR Recognizer* → reconstruir proyecto (**Build → Rebuild Project**)
4. Ejecutar `Main.java` con el path de un script MABO como argumento de programa:

```
examples/limpieza.mabo
```

### Desde Gradle

```bash
gradle clean test
gradle run --args="examples/limpieza.mabo"
```

Las pruebas automatizadas viven en `src/test/java`. Si modificas la gramática, ejecuta `gradle regenerateGrammar` para actualizar los archivos generados en `src/gen`.

### Instalar en Windows

#### Instalación para usuarios

Cuando exista una release publicada en GitHub, instala MABO con:

```powershell
powershell -NoProfile -ExecutionPolicy Bypass -Command "irm https://github.com/Tomas1802/MABO/releases/latest/download/install-windows.ps1 | iex"
```

Para instalar una versión concreta:

```powershell
powershell -NoProfile -ExecutionPolicy Bypass -Command "irm https://github.com/Tomas1802/MABO/releases/download/v1.0.0/install-windows.ps1 | iex"
```

El instalador descarga `mabo-windows.zip`, instala en `%LOCALAPPDATA%\MABO` y agrega `%LOCALAPPDATA%\MABO\bin` al `PATH` del usuario.

MABO requiere Java 17 o superior. Si al ejecutar `mabo` ves un error como `UnsupportedClassVersionError` o `class file version`, significa que Windows esta usando una version antigua de Java. Ejecuta:

```powershell
java -version
winget install EclipseAdoptium.Temurin.21.JDK
```

Despues de instalar Java, cierra y abre PowerShell. `java -version` debe mostrar 17 o superior.

#### Instalación desde el código fuente

Desde PowerShell, en la raíz del proyecto:

```powershell
.\scripts\install-windows.ps1
```

Cuando se ejecuta desde el repositorio, el instalador compila la distribución con Gradle, la instala en `%LOCALAPPDATA%\MABO` y agrega `%LOCALAPPDATA%\MABO\bin` al `PATH` de usuario. Después de abrir una nueva terminal se puede ejecutar:

```powershell
mabo
mabo examples/limpieza.mabo
mabo --check examples/limpieza.mabo
```

`mabo` sin argumentos abre el modo interactivo para escribir comandos directamente:

```text
mabo> Variable base = "%USERPROFILE%/Documents/MABO"
mabo> Ir A base
mabo> Mostrar Ruta
mabo> Crear Carpeta base + "/prueba"
mabo> Listar Contenido
mabo> salir
```

En este ejemplo, `base` es una variable que guarda la ruta principal de trabajo. `Ir A base` cambia la carpeta actual de MABO, `Mostrar Ruta` muestra esa carpeta y el operador `+` une esa ruta con otro texto, por ejemplo `base + "/prueba"`.

Al iniciar, MABO muestra tambien la carpeta de logs de tareas. En Windows queda normalmente en:

```text
%LOCALAPPDATA%\MABO\logs\tasks
```

También se puede ejecutar una instrucción corta con `-c`. En Windows es mejor reservarlo para comandos sin comillas internas; para rutas y textos usa el modo interactivo.

```powershell
mabo -c "Mostrar 123"
```

Los archivos propios de MABO usan la extensión `.mabo`. `mabo archivo.mabo` ejecuta el archivo. `mabo --check archivo.mabo` o `mabo validar archivo.mabo` valida sintaxis y semántica sin ejecutar acciones.

La extensión simple de VS Code permite abrir archivos `.mabo` con resaltado de sintaxis y comandos para ejecutar o validar el archivo actual.

Instalacion para usuarios:

1. Instala MABO y confirma que `mabo` funciona en PowerShell.
2. Abre `https://github.com/Tomas1802/MABO/releases/latest`.
3. Busca la seccion **Assets** al final de la release.
4. Descarga `mabo-language-0.1.0.vsix`. Si no ves la lista, presiona **Assets** para desplegarla.
5. Abre VS Code.
6. Ve a Extensions.
7. Abre el menu de tres puntos.
8. Elige **Install from VSIX...**.
9. Selecciona `mabo-language-0.1.0.vsix`.
10. Abre un archivo `.mabo`.

Si clonaste el repositorio, la carpeta `vscode/mabo-language` contiene el codigo de la extension, pero VS Code no la instala automaticamente desde esa carpeta. Primero crea el `.vsix` y luego instalalo:

```powershell
cd vscode/mabo-language
npx --yes @vscode/vsce package
code --install-extension mabo-language-0.1.0.vsix
```

Tambien puedes instalarla desde PowerShell si tienes el comando `code` habilitado:

```powershell
code --install-extension "C:\ruta\mabo-language-0.1.0.vsix"
```

Para usarla, abre un archivo `.mabo`, presiona `Ctrl+Shift+P`, escribe `MABO` y elige uno de estos comandos:

```text
MABO: Ejecutar archivo actual
MABO: Validar archivo actual
```

`Ejecutar archivo actual` corre el archivo con MABO. `Validar archivo actual` revisa sintaxis y semantica sin ejecutar acciones.

Si `mabo` no está en PATH, configura la ruta en VS Code:

```json
{
  "mabo.executable": "C:\\Users\\tu_usuario\\AppData\\Local\\MABO\\bin\\mabo.bat"
}
```

Para desinstalar:

```powershell
.\scripts\uninstall-windows.ps1
```

#### Publicar una release

Con GitHub CLI autenticado (`gh auth login`), crea y sube los artefactos de release:

```powershell
.\scripts\publish-release.ps1 -Tag v1.0.0
```

Esto ejecuta pruebas, genera `build/release/mabo-windows.zip`, copia `install-windows.ps1` y crea una GitHub Release con ambos archivos. También se puede publicar automáticamente empujando un tag:

```powershell
git tag v1.0.0
git push origin v1.0.0
```

### Desde línea de comandos manual

```bash
gradle run --args="examples/limpieza.mabo"
```

---

## Flujo de Ejecución

```
Script .mabo
    │
    ▼
proyectoLexer  →  proyectoParser  →  ParseTree
                                          │
                               ┌──────────┴──────────┐
                               ▼                     ▼
                        SemanticVisitor       ExecutionVisitor
                     (valida variables,      (ejecuta acciones
                      funciones y tareas)     reales del DSL)
                                                     │
                                          ┌──────────┴──────────┐
                                          ▼                     ▼
                                   FileSystemService       logs/app.log
                                  (archivos, ZIP, backup)
```

---

## Sintaxis del Lenguaje

### Variables

```
Variable numero = 10
Variable mensaje = "Hola mundo"
Variable ruta = "%USERPROFILE%/Documents/MiCarpeta"
```

### Operadores Aritméticos

| Palabra DSL | Símbolo equivalente | Operación |
|-------------|---------------------|-----------|
| `+` (`Mas`) | `+` | Suma / concatenación |
| `Menos` | `-` | Resta |
| `Por` | `*` | Multiplicación |
| `Entre` | `/` | División |
| `Modulo` | `%` | Módulo |

```
Mostrar "10 + 3 = " + (a + b)
Mostrar "10 - 3 = " + (a Menos b)
Mostrar "10 * 3 = " + (a Por b)
Mostrar "10 / 3 = " + (a Entre b)
Mostrar "10 % 3 = " + (a Modulo b)
```

La guia recomienda usar `+` porque es mas familiar. La palabra `Mas` sigue funcionando como alias del operador, y tambien aparece en filtros como `Mas Antiguos Que 30 Dias`, donde forma parte de la frase del filtro.

### Operadores de Comparación y Lógicos

```
a > b       a < b       a >= b      a <= b
a == b      a != b

Verdadero Y Verdadero
Verdadero O Falso
No Verdadero
```

### Mostrar

```
Mostrar "Hola, mundo!"
Mostrar "Resultado: " + (a + b)
Mostrar "Ruta: " + ruta
```

### Condiciones

```
Si nota >= 9 Entonces
    Mostrar "Excelente"
Sino
    Si nota >= 6 Entonces
        Mostrar "Aprobado"
    Sino
        Mostrar "Reprobado"
    FinSi
FinSi
```

### Ciclos

```
# Ciclo Mientras
Variable i = 1
Mientras i <= 5
    Mostrar i
    i = i + 1
FinMientras

# Ciclo Para Cada (sobre lista literal)
Para Cada color En ["rojo", "verde", "azul"]
    Mostrar color
FinPara

# Ciclo Para Cada (sobre resultado de búsqueda)
Buscar Archivos En "%USERPROFILE%/Documents" Con Extension ".txt"
Para Cada archivo En ultimoResultado
    Mostrar "Encontrado: " + archivo
FinPara
```

### Funciones

```
Funcion Sumar(x, y)
    Retornar x + y
FinFuncion

Funcion Factorial(n)
    Si n <= 1 Entonces
        Retornar 1
    Sino
        Retornar n Por Factorial(n Menos 1)
    FinSi
FinFuncion

Variable resultado = Sumar(10, 5)
Mostrar "Factorial de 5: " + Factorial(5)
```

Las funciones soportan:
- Parámetros múltiples
- Recursividad
- `Retornar` con cualquier expresión
- Alcance léxico (las variables externas se preservan)

### Manejo de Errores

```
Intentar
    Leer Archivo "ruta/que/no/existe.txt"
    Mostrar "No deberia llegar aqui"
Capturar
    Mostrar "Error capturado correctamente"
FinIntentar
```

### Tareas

```
Tarea LimpiarTemp:
    Eliminar Carpeta "%USERPROFILE%/Documents/temp" Recursivamente
    Mostrar "Limpieza completada"
Fin

Ejecutar Tarea LimpiarTemp
```

### Importar Scripts Externos

```
Importar "otro_script.txt"
```

### Comandos Nativos PowerShell / Linux

El DSL puede delegar acciones no soportadas a PowerShell o a un shell Linux. Los comandos se ejecutan desde un directorio seguro (`%USERPROFILE%/Documents/MABO`), se auditan y se bloquean patrones peligrosos.

```
Ejecutar PowerShell "Get-ChildItem | Select-Object Name"
Ejecutar Linux "ls -la"

Simular Ejecutar PowerShell "Write-Output 'prueba'"
Simular Ejecutar Linux "echo prueba"
```

La simulación registra la intención sin ejecutar el comando. La ejecución real de PowerShell está habilitada en Windows y la de Linux en Linux/Unix.

---

## Operaciones de Archivos y Carpetas

### Navegacion

```
Mostrar Ruta
Ir A "%USERPROFILE%/Documents"
Ir A "C:\Users\tparr\Documents\TOMAS"
Ir A "TOMAS"
Ir A ".."
Listar Contenido
```

`Ir A` cambia la carpeta actual de MABO. Las rutas relativas se resuelven desde esa carpeta y el modo interactivo la muestra en el prompt.

### Crear

```
Crear Archivo "ruta/archivo.txt"
Crear Carpeta "ruta/carpeta"
```

MABO acepta rutas con `/` y con `\`, por lo que estas dos formas son validas en Windows:

```
Ir A "C:/Users/tparr/Documents/TOMAS"
Ir A "C:\Users\tparr\Documents\TOMAS"
```

### Leer y Escribir

```
Leer Archivo "ruta/archivo.txt"
Escribir "contenido inicial" En "ruta/archivo.txt"
Anexar "\nlinea adicional" En "ruta/archivo.txt"
```

### Copiar, Mover y Renombrar

```
Copiar Archivo "origen.txt" A "destino.txt"
Copiar Carpeta "origen/" A "destino/"
Mover Archivo "origen.txt" A "destino/archivo.txt"
Mover Carpeta "origen/" A "destino/"
Renombrar Archivo "viejo.txt" A "nuevo.txt"
Renombrar Carpeta "vieja/" A "nueva/"
```

### Eliminar

```
Eliminar Archivo "ruta/archivo.txt"
Eliminar Archivo "ruta/archivo.txt" Sin Confirmar
Eliminar Carpeta "ruta/carpeta"
Eliminar Carpeta "ruta/carpeta" Recursivamente
Eliminar Carpeta "ruta/carpeta" Recursivamente Sin Confirmar
```

`Sin Confirmar` omite la pregunta interactiva de seguridad. Usalo solo en rutinas revisadas o junto con `Simular` durante pruebas.

### Búsqueda con Filtros

```
# Por extensión
Buscar Archivos En "ruta/" Con Extension ".txt"

# Por nombre (contiene)
Buscar Archivos En "ruta/" Con Nombre "informe"

# Por prefijo
Buscar Archivos En "ruta/" Con Prefijo "backup"

# Por sufijo del nombre (sin extensión)
Buscar Archivos En "ruta/" Con Sufijo "final"

# Por tamaño (en MB)
Buscar Archivos En "ruta/" Con Tamano Menor Que 5 MB
Buscar Archivos En "ruta/" Con Tamano Mayor Que 10 MB

# Por antigüedad (en días)
Buscar Archivos En "ruta/" Mas Antiguos Que 30 Dias
Buscar Archivos En "ruta/" Mas Recientes Que 7 Dias

# Carpetas
Buscar Carpetas En "ruta/" Con Nombre "docs"
```

### Consultas

```
# Listar contenido de la carpeta actual
Listar Contenido

# Listar contenido directo de otra carpeta
Listar Contenido "ruta/"

# Verificar existencia
Existe Archivo "ruta/archivo.txt"
Si ultimoResultado Entonces
    Mostrar "El archivo existe"
FinSi

Existe Carpeta "ruta/carpeta"
Si ultimoResultado Entonces
    Mostrar "La carpeta existe"
FinSi

# Tamaño en bytes
Obtener Tamano Archivo "ruta/archivo.txt"
Mostrar "Tamaño: " + ultimoResultado

# Cantidad de archivos (recursivo)
Obtener Cantidad Archivos En "ruta/"
Mostrar "Total: " + ultimoResultado

# Propiedades de un archivo
Obtener Propiedad nombre De "ruta/archivo.txt"
Obtener Propiedad extension De "ruta/archivo.txt"
Obtener Propiedad tamano De "ruta/archivo.txt"
Obtener Propiedad existe De "ruta/archivo.txt"
Obtener Propiedad esArchivo De "ruta/archivo.txt"
Obtener Propiedad esCarpeta De "ruta/carpeta"
```

### Compresión

```
# Comprimir archivo o carpeta a ZIP
Comprimir "ruta/carpeta" A "ruta/destino"

# Descomprimir ZIP
Descomprimir "ruta/archivo.zip" En "ruta/destino/"
```

### Backup

```
Crear Backup De "ruta/origen" En "ruta/respaldo"
```

### Permisos

En sistemas con permisos POSIX se puede cambiar permisos usando formato octal o simbólico:

```
Cambiar Permisos "ruta/script.sh" A "755"
Cambiar Permisos "ruta/archivo.txt" A "rw-r-----"
```

En Windows la operación falla con un mensaje claro porque los permisos POSIX no están disponibles.

---

## Modo Simulación

Cualquier operación de archivos puede ejecutarse en modo simulación: registra la acción en el log de auditoría sin modificar el sistema de archivos real.

```
Simular Eliminar Archivo "ruta/importante.txt"
Simular Copiar Carpeta "origen/" A "destino/"
Simular Comprimir "carpeta/" A "archivo"
Simular Crear Archivo "ruta/nuevo.txt"
```

---

## Tareas Programadas

```
Tarea VerificacionPeriodica:
    Existe Carpeta "%USERPROFILE%/Documents/MiCarpeta"
    Si ultimoResultado Entonces
        Mostrar "[OK] Carpeta activa"
    Sino
        Mostrar "[ALERTA] Carpeta no encontrada"
    FinSi
Fin

# Programar con intervalo
Ejecutar VerificacionPeriodica Cada 1 Minutos
Ejecutar VerificacionPeriodica Cada 2 Horas
Ejecutar VerificacionPeriodica Cada 1 Dias
Ejecutar Tarea VerificacionPeriodica Cada 2 Horas

# Ejecutar al iniciar el sistema
Ejecutar VerificacionPeriodica Al Iniciar Sistema

# Ejecutar una sola vez a una hora específica
Ejecutar VerificacionPeriodica A Las "23:00"
Ejecutar Tarea VerificacionPeriodica A Las "23:00"

# Ejecutar y programar archivos .mabo
Ejecutar Archivo "C:\Users\tparr\Documents\TAREAS\LimpiarXML.mabo"
Ejecutar Archivo "C:\Users\tparr\Documents\TAREAS\LimpiarXML.mabo" A Las "23:00"
Ejecutar Archivo "C:\Users\tparr\Documents\TAREAS\LimpiarXML.mabo" Cada 24 Horas
Ejecutar Archivo "C:\Users\tparr\Documents\TAREAS\LimpiarXML.mabo" Al Iniciar Sistema
```

La palabra `Tarea` es opcional al ejecutar o programar tareas. Estas dos instrucciones son equivalentes: `Ejecutar VerificacionPeriodica A Las "23:00"` y `Ejecutar Tarea VerificacionPeriodica A Las "23:00"`.

Para rutinas persistentes suele ser más simple programar el archivo `.mabo` completo. Por ejemplo, un archivo de limpieza puede contener solo las acciones:

```mabo
Buscar Archivos En "C:\Users\tparr\Downloads" Con Extension ".xml"

Para Cada archivo En ultimoResultado
    Eliminar Archivo archivo Sin Confirmar
FinPara
```

Y luego se programa desde el modo interactivo:

```mabo
Ejecutar Archivo "C:\Users\tparr\Documents\TAREAS\LimpiarXML.mabo" Al Iniciar Sistema
```

Las tareas programadas se guardan en:

```text
%USERPROFILE%/Documents/MABO/schedules.ndjson
```

Si vienes de una version anterior, MABO intenta copiar las programaciones antiguas a la nueva carpeta la primera vez que necesita leerlas y la nueva ruta aun no existe.

`A Las "HH:mm"` crea una programación de una sola ejecución. Cuando la tarea o archivo se ejecuta correctamente, MABO la elimina de `schedules.ndjson`.

`Cada X Minutos/Horas/Dias` ejecuta inmediatamente y luego repite mientras el proceso siga activo. La programación queda guardada para restaurarla.

`Al Iniciar Sistema` solo registra el arranque; no ejecuta inmediatamente. Permanece guardada hasta que la elimines.

> El proceso permanece activo mientras haya tareas programadas en memoria. `Al Iniciar Sistema` intenta registrar una tarea nativa del sistema operativo: Task Scheduler en Windows y autostart de usuario en Linux. Si el registro nativo falla, se conserva el registro del DSL y se informa en logs. Para tareas por nombre, la definición debe existir en el script o sesión actual. Para archivos, MABO guarda la ruta del `.mabo` y lo ejecuta directamente.

### Administrar tareas programadas

```
Listar Tareas Programadas
Eliminar Tarea Programada VerificacionPeriodica
Eliminar Tareas Programadas
Cambiar Programacion De Tarea VerificacionPeriodica Cada 2 Horas
Cambiar Programacion De Tarea VerificacionPeriodica A Las "23:30"
```

`Listar Tareas Programadas` muestra las programaciones guardadas, incluidas las de archivos `.mabo`.

`Eliminar Tarea Programada Nombre` cancela una programación de tarea por nombre, la borra del archivo y elimina el registro nativo de inicio si aplica.

`Eliminar Tareas Programadas` elimina todas las programaciones guardadas, incluidas las de archivos `.mabo` y las entradas de arranque creadas por MABO.

`Cambiar Programacion De Tarea` reemplaza la programación anterior de una tarea con nombre.

Cada ejecución de una tarea genera un log propio en `%LOCALAPPDATA%\MABO\logs\tasks` en Windows. El archivo se llama con el nombre de la tarea y la fecha, por ejemplo `LimpiarArchivosXML-2026-05-31.log`. Allí se registran inicio, fin, errores, búsquedas, listados y operaciones de archivos como crear, mover, copiar, escribir, eliminar, comprimir y ejecutar comandos nativos.

También existe `scheduler.log` en esa misma carpeta para ver cuándo se programó o restauró una tarea y si una ejecución programada falló. Si una tarea programada falla, el scheduler conserva la programación y escribe el error en el log.

En Windows, las programaciones `Al Iniciar Sistema` intentan usar Task Scheduler. Si Windows responde `Acceso denegado`, MABO usa como respaldo la carpeta Startup del usuario: `%APPDATA%\Microsoft\Windows\Start Menu\Programs\Startup`. En ambos casos crea un wrapper en `%LOCALAPPDATA%\MABO\startup` y escribe salida de arranque en `%LOCALAPPDATA%\MABO\logs\startup`. Esto permite diagnosticar errores que ocurren antes de que MABO alcance a crear logs de tarea.

Para verificar una programación de arranque en Windows:

```powershell
Get-ChildItem "$env:APPDATA\Microsoft\Windows\Start Menu\Programs\Startup" | Where-Object Name -like "MABO_*"
Get-ChildItem "$env:LOCALAPPDATA\MABO\startup" | Where-Object Name -like "MABO_*"
```

Si Windows permitió Task Scheduler, también puede aparecer como `MABO_<nombre>`:

```powershell
schtasks /Query /FO LIST /V | findstr MABO
```

---

## Variables de Entorno en Rutas

Las rutas soportan expansión de variables de entorno en formato Windows y Unix:

```
# Windows
Variable base = "%USERPROFILE%/Documents/MiProyecto"
Variable config = "%APPDATA%/MiApp"
# Unix / cross-platform
Variable base2 = "${HOME}/documentos"
Variable base3 = "~/documentos"
```

---

## `ultimoResultado`

La variable reservada `ultimoResultado` almacena automáticamente el resultado de la última operación ejecutada. Se usa para encadenar operaciones:

```
Buscar Archivos En "ruta/" Con Extension ".log"
Para Cada archivo En ultimoResultado
    Eliminar Archivo archivo Sin Confirmar
FinPara

Existe Archivo "ruta/config.txt"
Si ultimoResultado Entonces
    Leer Archivo "ruta/config.txt"
FinSi

Obtener Cantidad Archivos En "ruta/"
Mostrar "Total archivos: " + ultimoResultado
```

---

## Seguridad

El DSL implementa una lista blanca de rutas permitidas (`SecurityValidator`). Por defecto se permite trabajar dentro del perfil del usuario y dentro de la carpeta actual del proyecto. Tambien se registran como raices validas las rutas guardadas con `Variable nombre = <ruta>`. Las rutas criticas del sistema operativo (System32, Program Files, raices del disco) estan bloqueadas.

Los comandos nativos agregan una segunda capa de seguridad: validación de shell por plataforma, bloqueo de patrones peligrosos, confirmación para comandos potencialmente destructivos, soporte de simulación y auditoría de salida/código de retorno.

---

## Logs y Auditoría

- **`logs/app.log`** — log de ejecución en texto plano con timestamp. Registra inicio, acciones, variables, errores y fin.
- **Auditoría NDJSON** — cada operación de archivo genera una entrada con: operación, origen, destino, éxito/fallo y detalle.
- **Logs de tareas** — en Windows se guardan en `%LOCALAPPDATA%\MABO\logs\tasks`. Cada tarea tiene un archivo por día, por ejemplo `BackupDiario-2026-05-31.log`, y el scheduler usa `scheduler.log`.
- **Logs de arranque** — en Windows se guardan en `%LOCALAPPDATA%\MABO\logs\startup` cuando se usa `Al Iniciar Sistema`.
- **Programaciones guardadas** — se guardan en `%USERPROFILE%/Documents/MABO/schedules.ndjson`.

---

## Ejemplo Completo

```
Variable base = "%USERPROFILE%/Documents/MiProyecto"
Mostrar "Trabajando en: " + base

# Crear estructura
Crear Carpeta base
Crear Archivo base + "\datos.txt"
Escribir "Linea 1" En base + "\datos.txt"
Anexar "\nLinea 2" En base + "\datos.txt"

# Leer
Leer Archivo base + "\datos.txt"

# Buscar y recorrer
Buscar Archivos En base Con Extension ".txt"
Para Cada archivo En ultimoResultado
    Mostrar "Encontrado: " + archivo
FinPara

# Manejo de errores
Intentar
    Leer Archivo base + "\no_existe.txt"
Capturar
    Mostrar "Archivo no encontrado, continuando..."
FinIntentar

# Comprimir todo
Comprimir base A base + "\respaldo"
Mostrar "Backup comprimido creado"

# Tarea programada
Tarea Verificar:
    Existe Carpeta base
    Si ultimoResultado Entonces
        Mostrar "Proyecto activo"
    FinSi
Fin

Ejecutar Verificar Cada 1 Horas
```

---

## Archivos de Ejemplo y Pruebas

Los ejemplos de uso para usuarios están en `examples/`:

- `examples/limpieza.mabo`
- `examples/organizar-pdf.mabo`

Las pruebas automatizadas están en `src/test/java` y cubren parser, ejecución, validación, seguridad, archivos, shell y comandos de verificación.
