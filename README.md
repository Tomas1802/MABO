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

### Desde línea de comandos

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
Guardar 10 En numero
Guardar "Hola mundo" En mensaje
Guardar "%USERPROFILE%/Documents/MiCarpeta" En ruta
```

### Operadores Aritméticos

| Palabra DSL | Símbolo equivalente | Operación |
|-------------|---------------------|-----------|
| `Mas` | `+` | Suma / concatenación |
| `Menos` | `-` | Resta |
| `Por` | `*` | Multiplicación |
| `Entre` | `/` | División |
| `Modulo` | `%` | Módulo |

```
Mostrar "10 + 3 = " Mas (a Mas b)
Mostrar "10 - 3 = " Mas (a Menos b)
Mostrar "10 * 3 = " Mas (a Por b)
Mostrar "10 / 3 = " Mas (a Entre b)
Mostrar "10 % 3 = " Mas (a Modulo b)
```

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
Mostrar "Resultado: " Mas (a Mas b)
Mostrar "Ruta: " Mas ruta
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
Guardar 1 En i
Mientras i <= 5
    Mostrar i
    i = i Mas 1
FinMientras

# Ciclo Para Cada (sobre lista literal)
Para Cada color En ["rojo", "verde", "azul"]
    Mostrar color
FinPara

# Ciclo Para Cada (sobre resultado de búsqueda)
Buscar Archivos En "%USERPROFILE%/Documents" Con Extension ".txt"
Para Cada archivo En ultimoResultado
    Mostrar "Encontrado: " Mas archivo
FinPara
```

### Funciones

```
Funcion Sumar(x, y)
    Retornar x Mas y
FinFuncion

Funcion Factorial(n)
    Si n <= 1 Entonces
        Retornar 1
    Sino
        Retornar n Por Factorial(n Menos 1)
    FinSi
FinFuncion

Guardar Sumar(10, 5) En resultado
Mostrar "Factorial de 5: " Mas Factorial(5)
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
Escribir "contenido inicial" En Archivo "ruta/archivo.txt"
Anexar "\nlinea adicional" En Archivo "ruta/archivo.txt"
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
Buscar Archivos En "ruta/" Menores Que 5
Buscar Archivos En "ruta/" Mayores Que 10

# Por antigüedad (en días)
Buscar Archivos En "ruta/" Mas Antiguos Que 30 Dias
Buscar Archivos En "ruta/" Mas Recientes Que 7 Dias

# Carpetas
Buscar Carpetas En "ruta/" Con Nombre "docs"
```

### Consultas

```
# Listar contenido directo de una carpeta
Listar Contenido En "ruta/"

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
Mostrar "Tamaño: " Mas ultimoResultado

# Cantidad de archivos (recursivo)
Obtener Cantidad Archivos En "ruta/"
Mostrar "Total: " Mas ultimoResultado

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
```

> El proceso permanece activo mientras haya tareas programadas. Presionar `Ctrl+C` para detenerlo.

---

## Variables de Entorno en Rutas

Las rutas soportan expansión de variables de entorno en formato Windows y Unix:

```
# Windows
Guardar "%USERPROFILE%/Documents/MiProyecto" En base
Guardar "%APPDATA%/MiApp" En config

# Unix / cross-platform
Guardar "${HOME}/documentos" En base2
Guardar "~/documentos" En base3
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
Mostrar "Total archivos: " Mas ultimoResultado
```

---

## Seguridad

El DSL implementa una lista blanca de rutas permitidas (`SecurityValidator`). Solo se pueden operar rutas que hayan sido registradas como raíces válidas, lo cual ocurre automáticamente al usar `Guardar <ruta> En variable`. Las rutas críticas del sistema operativo (System32, Program Files, raíces del disco) están bloqueadas.

---

## Logs y Auditoría

- **`logs/app.log`** — log de ejecución en texto plano con timestamp. Registra inicio, acciones, variables, errores y fin.
- **Auditoría NDJSON** — cada operación de archivo genera una entrada con: operación, origen, destino, éxito/fallo y detalle.

---

## Ejemplo Completo

```
Guardar "%USERPROFILE%/Documents/MiProyecto" En base
Mostrar "Trabajando en: " Mas base

# Crear estructura
Crear Carpeta base
Crear Archivo base Mas "\datos.txt"
Escribir "Linea 1" En Archivo base Mas "\datos.txt"
Anexar "\nLinea 2" En Archivo base Mas "\datos.txt"

# Leer
Leer Archivo base Mas "\datos.txt"

# Buscar y recorrer
Buscar Archivos En base Con Extension ".txt"
Para Cada archivo En ultimoResultado
    Mostrar "Encontrado: " Mas archivo
FinPara

# Manejo de errores
Intentar
    Leer Archivo base Mas "\no_existe.txt"
Capturar
    Mostrar "Archivo no encontrado, continuando..."
FinIntentar

# Comprimir todo
Comprimir base A base Mas "\respaldo"
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
