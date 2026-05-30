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
}
