package semantic;

import gen.proyectoParser;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Task;

public class ExecutionContext {
    public final Map<String, Object> variables = new HashMap<>();
    public final Map<String, Task> tareas = new HashMap<>();
    public final Map<String, proyectoParser.FuncionContext> funciones = new HashMap<>();
    public final List<Path> archivosEncontrados = new ArrayList<>();
    public boolean modoSimulacion = false;
    public Object ultimoResultado;

    public void clearFound() { archivosEncontrados.clear(); }
}
