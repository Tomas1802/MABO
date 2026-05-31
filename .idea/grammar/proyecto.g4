grammar proyecto;

@header {
    package gen;
}

/* =========================================================================
   PARSER RULES
   ========================================================================= */

programa
    : sentencia* EOF
    ;

sentencia
    : declaracionVariable
    | asignacionVariable
    | mostrarRuta
    | cambiarDirectorio
    | mostrar
    | condicion
    | cicloMientras
    | cicloParaCada
    | funcion
    | llamadaFuncion
    | retornar
    | tarea
    | ejecutarTarea
    | listarTareasProgramadas
    | eliminarTareaProgramada
    | cambiarTareaProgramada
    | comandoSistema
    | simulacion
    | intentarCapturar
    | importar
    | accionArchivo
    | expresion
    ;


/* =========================================================================
   IMPORTS
   ========================================================================= */

importar
    : IMPORTAR cadena
    ;


/* =========================================================================
   NAVEGACION
   ========================================================================= */

cambiarDirectorio
    : IR A expresion
    ;

mostrarRuta
    : MOSTRAR RUTA
    ;


/* =========================================================================
   VARIABLES
   ========================================================================= */

declaracionVariable
    : VARIABLE ID IGUAL expresion
    ;

asignacionVariable
    : ID IGUAL expresion
    ;


/* =========================================================================
   FUNCIONES
   ========================================================================= */

funcion
    : FUNCION ID
      PAR_IZQ parametros? PAR_DER
      bloque
      FINFUNCION
    ;

parametros
    : ID (COMA ID)*
    ;

llamadaFuncion
    : ID PAR_IZQ argumentos? PAR_DER
    ;

argumentos
    : expresion (COMA expresion)*
    ;

retornar
    : RETORNAR expresion
    ;


/* =========================================================================
   TAREAS
   ========================================================================= */

tarea
    : TAREA ID DOS_PUNTOS
      bloque
      FIN
    ;

ejecutarTarea
    : EJECUTAR TAREA? ID programacion?
    ;

listarTareasProgramadas
    : LISTAR TAREAS PROGRAMADAS
    ;

eliminarTareaProgramada
    : ELIMINAR TAREA PROGRAMADA ID
    ;

cambiarTareaProgramada
    : CAMBIAR PROGRAMACION DE TAREA ID programacion
    ;


/* =========================================================================
   PROGRAMACION
   ========================================================================= */

programacion
    : CADA numero tiempo
    | AL INICIAR SISTEMA
    | A LAS cadena
    ;

tiempo
    : HORAS
    | MINUTOS
    | DIAS
    ;


/* =========================================================================
   CONDICIONES
   ========================================================================= */

condicion
    : SI expresion ENTONCES
        bloque
      (SINO
        bloque)?
      FINSI
    ;


/* =========================================================================
   CICLOS
   ========================================================================= */

cicloMientras
    : MIENTRAS expresion
      bloque
      FINMIENTRAS
    ;

cicloParaCada
    : PARA CADA ID EN expresion
      bloque
      FINPARA
    ;


/* =========================================================================
   TRY CATCH
   ========================================================================= */

intentarCapturar
    : INTENTAR
      bloque
      CAPTURAR
      bloque
      FININTENTAR
    ;


/* =========================================================================
   BLOQUES
   ========================================================================= */

bloque
    : sentencia*
    ;


/* =========================================================================
   SIMULACION
   ========================================================================= */

simulacion
    : SIMULAR (accionArchivo | comandoSistema)
    ;


/* =========================================================================
   COMANDOS NATIVOS
   ========================================================================= */

comandoSistema
    : ejecutarPowerShell
    | ejecutarLinux
    ;

ejecutarPowerShell
    : EJECUTAR POWERSHELL expresion
    ;

ejecutarLinux
    : EJECUTAR LINUX expresion
    ;


/* =========================================================================
   ACCIONES ARCHIVOS / CARPETAS
   ========================================================================= */

accionArchivo
    : crearArchivo
    | crearCarpeta
    | eliminarArchivo
    | eliminarCarpeta
    | copiarArchivo
    | copiarCarpeta
    | moverArchivo
    | moverCarpeta
    | renombrarArchivo
    | renombrarCarpeta
    | leerArchivo
    | escribirArchivo
    | anexarArchivo
    | buscarArchivos
    | buscarCarpetas
    | listarContenido
    | existeArchivo
    | existeCarpeta
    | obtenerTamano
    | obtenerCantidadArchivos
    | obtenerPropiedad
    | comprimir
    | descomprimir
    | cambiarPermisos
    | crearBackup
    ;


/* =========================================================================
   CREATE
   ========================================================================= */

crearArchivo
    : CREAR ARCHIVO expresion
    ;

crearCarpeta
    : CREAR CARPETA expresion
    ;


/* =========================================================================
   READ
   ========================================================================= */

leerArchivo
    : LEER ARCHIVO expresion
    ;

buscarArchivos
    : BUSCAR ARCHIVOS EN expresion filtros?
    ;

buscarCarpetas
    : BUSCAR CARPETAS EN expresion filtros?
    ;

listarContenido
    : LISTAR CONTENIDO expresion?
    ;

existeArchivo
    : EXISTE ARCHIVO expresion
    ;

existeCarpeta
    : EXISTE CARPETA expresion
    ;

obtenerTamano
    : OBTENER TAMANO ARCHIVO expresion
    | OBTENER TAMANO CARPETA expresion
    ;

obtenerCantidadArchivos
    : OBTENER CANTIDAD ARCHIVOS EN expresion
    ;

obtenerPropiedad
    : OBTENER PROPIEDAD ID DE expresion
    ;


/* =========================================================================
   UPDATE
   ========================================================================= */

copiarArchivo
    : COPIAR ARCHIVO expresion A expresion
    ;

copiarCarpeta
    : COPIAR CARPETA expresion A expresion
    ;

moverArchivo
    : MOVER ARCHIVO expresion A expresion
    ;

moverCarpeta
    : MOVER CARPETA expresion A expresion
    ;

renombrarArchivo
    : RENOMBRAR ARCHIVO expresion A expresion
    ;

renombrarCarpeta
    : RENOMBRAR CARPETA expresion A expresion
    ;

escribirArchivo
    : ESCRIBIR expresion EN expresion
    ;

anexarArchivo
    : ANEXAR expresion EN expresion
    ;

cambiarPermisos
    : CAMBIAR PERMISOS expresion A expresion
    ;


/* =========================================================================
   DELETE
   ========================================================================= */

eliminarArchivo
    : ELIMINAR ARCHIVO expresion sinConfirmar?
    ;

eliminarCarpeta
    : ELIMINAR CARPETA expresion RECURSIVAMENTE? sinConfirmar?
    ;

sinConfirmar
    : SIN CONFIRMAR
    ;


/* =========================================================================
   COMPRESION / BACKUP
   ========================================================================= */

comprimir
    : COMPRIMIR expresion A expresion
    ;

descomprimir
    : DESCOMPRIMIR expresion EN expresion
    ;

crearBackup
    : CREAR BACKUP DE expresion EN expresion
    ;


/* =========================================================================
   FILTROS
   ========================================================================= */

filtros
    : filtro+
    ;

filtro
    : CON EXTENSION cadena
    | CON NOMBRE cadena
    | CON PREFIJO cadena
    | CON SUFIJO cadena
    | CON TAMANO MAYOR QUE expresion unidadTamano
    | CON TAMANO MENOR QUE expresion unidadTamano
    | MAS ANTIGUOS QUE expresion DIAS
    | MAS RECIENTES QUE expresion DIAS
    ;

unidadTamano
    : BYTES
    | KB
    | MB
    | GB
    ;


/* =========================================================================
   OUTPUT
   ========================================================================= */

mostrar
    : MOSTRAR expresion
    ;


/* =========================================================================
   EXPRESIONES
   ========================================================================= */

expresion
    : expresion OR expresion                         #orExpr
    | expresion AND expresion                        #andExpr
    | NOT expresion                                  #notExpr

    | expresion MAYOR_QUE expresion                  #mayorExpr
    | expresion MENOR_QUE expresion                  #menorExpr
    | expresion MAYOR_IGUAL expresion                #mayorIgualExpr
    | expresion MENOR_IGUAL expresion                #menorIgualExpr
    | expresion IGUAL_IGUAL expresion                #igualExpr
    | expresion DIFERENTE expresion                  #diferenteExpr

    | MENOS expresion                                #negExpr

    | expresion MAS expresion                        #sumaExpr
    | expresion MENOS expresion                      #restaExpr
    | expresion MULT expresion                       #multExpr
    | expresion DIV expresion                        #divExpr
    | expresion MOD expresion                        #modExpr

    | PAR_IZQ expresion PAR_DER                      #parenExpr

    | llamadaFuncion                                 #funcionExpr

    | lista                                          #listaExpr

    | numero                                         #numeroExpr
    | cadena                                         #cadenaExpr
    | booleano                                       #booleanoExpr
    | variable                                       #variableExpr
    ;


/* =========================================================================
   LISTAS
   ========================================================================= */

lista
    : CORCH_IZQ argumentos? CORCH_DER
    ;


/* =========================================================================
   TERMINALES SEMANTICOS
   ========================================================================= */

variable
    : ID
    ;

numero
    : ENTERO
    | DECIMAL
    ;

cadena
    : STRING
    ;

booleano
    : VERDADERO
    | FALSO
    ;


/* =========================================================================
   KEYWORDS
   ========================================================================= */

IMPORTAR           : 'Importar';

VARIABLE           : 'Variable';
EN                  : 'En';

FUNCION            : 'Funcion';
FINFUNCION         : 'FinFuncion';
RETORNAR           : 'Retornar';

FIN                : 'Fin';

TAREA              : 'Tarea';
TAREAS             : 'Tareas';
PROGRAMADA         : 'Programada';
PROGRAMADAS        : 'Programadas';
PROGRAMACION       : 'Programacion';
EJECUTAR           : 'Ejecutar';

SI                  : 'Si';
ENTONCES           : 'Entonces';
SINO                : 'Sino';
FINSI              : 'FinSi';

MIENTRAS           : 'Mientras';
FINMIENTRAS        : 'FinMientras';

PARA               : 'Para';
CADA               : 'Cada';
FINPARA            : 'FinPara';

INTENTAR           : 'Intentar';
CAPTURAR           : 'Capturar';
FININTENTAR        : 'FinIntentar';

SIMULAR            : 'Simular';

CREAR              : 'Crear';
LEER               : 'Leer';
ESCRIBIR           : 'Escribir';
ANEXAR             : 'Anexar';
BUSCAR             : 'Buscar';
LISTAR             : 'Listar';
ELIMINAR           : 'Eliminar';
MOVER              : 'Mover';
COPIAR             : 'Copiar';
RENOMBRAR          : 'Renombrar';
OBTENER            : 'Obtener';
EXISTE             : 'Existe';
CAMBIAR            : 'Cambiar';

TAMANO             : 'Tamano';
CANTIDAD           : 'Cantidad';

ARCHIVO            : 'Archivo';
ARCHIVOS           : 'Archivos';

CARPETA            : 'Carpeta';
CARPETAS           : 'Carpetas';

CONTENIDO          : 'Contenido';

BACKUP             : 'Backup';

COMPRESION         : 'Compresion';
COMPRIMIR          : 'Comprimir';
DESCOMPRIMIR       : 'Descomprimir';

PROPIEDAD          : 'Propiedad';
PERMISOS           : 'Permisos';

CON                : 'Con';
DE                  : 'De';
A                   : 'A';

EXTENSION          : 'Extension';
NOMBRE             : 'Nombre';
PREFIJO            : 'Prefijo';
SUFIJO             : 'Sufijo';

MAYOR              : 'Mayor';
MENOR              : 'Menor';
ANTIGUOS           : 'Antiguos';
RECIENTES          : 'Recientes';

QUE                : 'Que';

RECURSIVAMENTE     : 'Recursivamente';
SIN                : 'Sin';
CONFIRMAR          : 'Confirmar';

AL                 : 'Al';
INICIAR            : 'Iniciar';
SISTEMA            : 'Sistema';
LAS                : 'Las';
IR                 : 'Ir';
RUTA               : 'Ruta';

HORAS              : 'Horas';
MINUTOS            : 'Minutos';
DIAS               : 'Dias';
BYTES              : 'Bytes';
KB                 : 'KB';
MB                 : 'MB';
GB                 : 'GB';
POWERSHELL         : 'PowerShell';
LINUX              : 'Linux';

MOSTRAR            : 'Mostrar';

VERDADERO          : 'Verdadero';
FALSO              : 'Falso';

AND                : 'Y';
OR                 : 'O';
NOT                : 'No';


/* =========================================================================
   OPERADORES
   ========================================================================= */

IGUAL              : '=';
IGUAL_IGUAL        : '==';
DIFERENTE          : '!=';

MAYOR_QUE          : '>';
MENOR_QUE          : '<';

MAYOR_IGUAL        : '>=';
MENOR_IGUAL        : '<=';

MAS                : 'Mas' | '+';
MENOS              : 'Menos' | '-';
MULT               : 'Por' | '*';
DIV                : 'Entre' | '/';
MOD                : 'Modulo' | '%';


/* =========================================================================
   SIMBOLOS
   ========================================================================= */

PAR_IZQ            : '(';
PAR_DER            : ')';

CORCH_IZQ          : '[';
CORCH_DER          : ']';

COMA               : ',';

DOS_PUNTOS         : ':';


/* =========================================================================
   TOKENS BASE
   ========================================================================= */

ID
    : [a-zA-Z_][a-zA-Z0-9_]*
    ;

ENTERO
    : [0-9]+
    ;

DECIMAL
    : [0-9]+ '.' [0-9]+
    ;

STRING
    : '"' (~["\\] | '\\' .)* '"'
    ;

WS
    : [ \t\r\n]+ -> skip
    ;

COMENTARIO
    : '#' ~[\r\n]* -> skip
    ;
