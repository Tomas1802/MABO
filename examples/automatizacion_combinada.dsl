Guardar "%USERPROFILE%/Documents/DSLDemo" En base
Guardar base Mas "/Descargas" En descargas
Guardar base Mas "/Documentos/PDF" En pdfs

Crear Carpeta descargas
Crear Carpeta pdfs

Tarea OrganizarPDF:
    Buscar Archivos En descargas Con Extension ".pdf"
    Para Cada archivo En ultimoResultado
        Obtener Propiedad nombre De archivo
        Mover Archivo archivo A pdfs Mas "/" Mas ultimoResultado
    FinPara
    Mostrar "PDF organizados"
Fin

Tarea LimpiarTemporales:
    Buscar Archivos En descargas Con Extension ".tmp" Mas Antiguos Que 30 Dias
    Para Cada archivo En ultimoResultado
        Simular Eliminar Archivo archivo
    FinPara
    Mostrar "Simulacion de limpieza completada"
Fin

Tarea DiagnosticoNativo:
    Simular Ejecutar PowerShell "Get-ChildItem"
    Simular Ejecutar Linux "ls -la"
Fin

Ejecutar Tarea OrganizarPDF
Ejecutar Tarea LimpiarTemporales
Ejecutar DiagnosticoNativo Cada 24 Horas
