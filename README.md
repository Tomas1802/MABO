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

- **Java 21+** (compatible con JDK 25)
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
│   └── AuditService.java            # Log de auditoría NDJSON
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
├── input_prueba_completa.txt        # Script de prueba con los 16 bloques
└── .idea/grammar/
    └── proyecto.g4                  # Gramática ANTLR del DSL
```

---

## Cómo Ejecutar

### Requisitos

1. JDK 21 o superior
2. `antlr-runtime-4.13.1.jar` en el classpath
3. IntelliJ IDEA (recomendado)

### Desde IntelliJ IDEA

1. Abrir el proyecto en IntelliJ
2. Agregar `antlr-runtime-4.13.1.jar` en **File → Project Structure → Modules → Dependencies**
3. Si se modifica la gramática `proyecto.g4`: clic derecho sobre el archivo → *Generate ANTLR Recognizer* → reconstruir proyecto (**Build → Rebuild Project**)
4. Ejecutar `Main.java` con el path del script DSL como argumento de programa:

```
src/input_prueba_completa.txt
```

### Desde Gradle

```bash
gradle clean test
gradle run --args="src/input_prueba_completa.txt"
```

Gradle regenera el parser desde `.idea/grammar/proyecto.g4` usando ANTLR y ejecuta las pruebas automatizadas.

### Instalar en Windows

Desde PowerShell, en la raíz del proyecto:

```powershell
.\scripts\install-windows.ps1
```

Esto compila la distribución con Gradle, la instala en `%LOCALAPPDATA%\MABO` y agrega `%LOCALAPPDATA%\MABO\bin` al `PATH` de usuario. Después de abrir una nueva terminal se puede ejecutar:

```powershell
mabo
mabo src/input_prueba_completa.txt
mabo examples/automatizacion_combinada.dsl
```

`mabo` sin argumentos abre el modo interactivo para escribir comandos directamente:

```text
mabo> Variable base = "%USERPROFILE%/Documents/DSLDemo"
mabo> Crear Carpeta base + "/prueba"
mabo> Listar Contenido base
mabo> salir
```

En este ejemplo, `base` es una variable que guarda la ruta principal de trabajo. El operador `+` une esa ruta con otro texto, por ejemplo `base + "/prueba"`.

También se puede ejecutar una instrucción corta con `-c`. En Windows es mejor reservarlo para comandos sin comillas internas; para rutas y textos usa el modo interactivo.

```powershell
mabo -c "Mostrar 123"
```

Para desinstalar:

```powershell
.\scripts\uninstall-windows.ps1
```

### Desde línea de comandos manual

```bash
java -classpath "out/production/Bash;antlr-runtime-4.13.1.jar" Main src/input_prueba_completa.txt
```

---

## Flujo de Ejecución

```
Script .txt
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

El DSL puede delegar acciones no soportadas a PowerShell o a un shell Linux. Los comandos se ejecutan desde un directorio seguro (`%USERPROFILE%/Documents/DSLDemo`), se auditan y se bloquean patrones peligrosos.

```
Ejecutar PowerShell "Get-ChildItem | Select-Object Name"
Ejecutar Linux "ls -la"

Simular Ejecutar PowerShell "Write-Output 'prueba'"
Simular Ejecutar Linux "echo prueba"
```

La simulación registra la intención sin ejecutar el comando. La ejecución real de PowerShell está habilitada en Windows y la de Linux en Linux/Unix.

---

## Operaciones de Archivos y Carpetas

### Crear

```
Crear Archivo "ruta/archivo.txt"
Crear Carpeta "ruta/carpeta"
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
Eliminar Carpeta "ruta/carpeta"
Eliminar Carpeta "ruta/carpeta" Recursivamente
```

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
# Listar contenido directo de una carpeta
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

# Ejecutar al iniciar el sistema
Ejecutar VerificacionPeriodica Al Iniciar Sistema

# Ejecutar todos los días a una hora específica
Ejecutar VerificacionPeriodica A Las "23:00"
```

> El proceso permanece activo mientras haya tareas programadas en memoria. `Al Iniciar Sistema` intenta registrar una tarea nativa del sistema operativo: Task Scheduler en Windows y autostart de usuario en Linux. Si el registro nativo falla, se conserva el registro del DSL y se informa en logs.

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
    Eliminar Archivo archivo
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

## Archivo de Prueba Incluido

El proyecto incluye `src/input_prueba_completa.txt` con **16 bloques de prueba** que cubren todas las funcionalidades:

| Bloque | Contenido |
|--------|-----------|
| 1 | Variables y operadores aritméticos |
| 2 | Comparaciones y operadores lógicos |
| 3 | Funciones con parámetros, retorno y recursividad |
| 4 | Condiciones Si / Sino / FinSi |
| 5 | Ciclos Mientras y Para Cada |
| 6 | Crear estructura de archivos y carpetas |
| 7 | Leer, copiar, mover y renombrar |
| 8 | Búsqueda con todos los filtros disponibles |
| 9 | Consultas: Existe, Tamaño, Cantidad, Propiedad |
| 10 | Para Cada sobre resultado de búsqueda |
| 11 | Modo simulación |
| 12 | Intentar / Capturar |
| 13 | Backup de carpeta |
| 14 | Función que usa operaciones de archivos |
| 15 | Tarea programada con scheduler |
| 16 | Compresión y descompresión ZIP |
