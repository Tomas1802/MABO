package dsl;

import gen.proyectoLexer;
import gen.proyectoParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserFeatureTest {
    @Test
    void parsesNativeCommandsAndSpecificTimeSchedules() {
        String script = """
                Variable base = "%USERPROFILE%/Documents/DSLDemo"
                Tarea RespaldoProgramado:
                    Mostrar "ok"
                Fin
                Ejecutar RespaldoProgramado A Las "23:00"
                Simular Ejecutar PowerShell "Write-Output 'hola'"
                Simular Ejecutar Linux "echo hola"
                """;

        proyectoLexer lexer = new proyectoLexer(CharStreams.fromString(script));
        proyectoParser parser = new proyectoParser(new CommonTokenStream(lexer));

        parser.programa();

        assertEquals(0, parser.getNumberOfSyntaxErrors());
    }

    @Test
    void rejectsOldGuardarVariableSyntax() {
        proyectoLexer lexer = new proyectoLexer(CharStreams.fromString("Guardar 10 En numero"));
        proyectoParser parser = new proyectoParser(new CommonTokenStream(lexer));

        parser.programa();

        assertEquals(1, parser.getNumberOfSyntaxErrors());
    }

    @Test
    void parsesSimplifiedFileWriteListAndExplicitSizeFilters() {
        String script = """
                Variable base = "%USERPROFILE%/Documents/DSLDemo"
                Ir A base
                Mostrar Ruta
                Escribir "Mi primera nota" En base + "/notas.txt"
                Anexar "\\nOtra nota" En base + "/notas.txt"
                Listar Contenido
                Listar Contenido base
                Buscar Archivos En base Con Tamano Menor Que 5 MB
                Buscar Archivos En base Con Tamano Mayor Que 100 KB
                Buscar Archivos En base Con Tamano Menor Que 1024 Bytes
                Buscar Archivos En base Con Tamano Mayor Que 1 GB
                Eliminar Archivo base + "/notas.txt" Sin Confirmar
                Eliminar Carpeta base + "/tmp" Recursivamente Sin Confirmar
                """;

        proyectoLexer lexer = new proyectoLexer(CharStreams.fromString(script));
        proyectoParser parser = new proyectoParser(new CommonTokenStream(lexer));

        parser.programa();

        assertEquals(0, parser.getNumberOfSyntaxErrors());
    }

    @Test
    void rejectsRemovedFileListAndAmbiguousSizeSyntax() {
        String[] removedForms = {
                "Escribir \"nota\" En Archivo \"notas.txt\"",
                "Anexar \"nota\" En Archivo \"notas.txt\"",
                "Listar Contenido En \"ruta\"",
                "Buscar Archivos En \"ruta\" Menores Que 5",
                "Buscar Archivos En \"ruta\" Con Tamano Menor Que 5"
        };

        for (String script : removedForms) {
            proyectoLexer lexer = new proyectoLexer(CharStreams.fromString(script));
            proyectoParser parser = new proyectoParser(new CommonTokenStream(lexer));

            parser.programa();

            org.junit.jupiter.api.Assertions.assertTrue(
                    parser.getNumberOfSyntaxErrors() > 0,
                    "Expected syntax error for: " + script
            );
        }
    }
}
