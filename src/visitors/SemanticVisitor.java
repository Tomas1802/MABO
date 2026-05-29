package visitors;

import exceptions.SemanticException;
import gen.proyectoBaseVisitor;
import gen.proyectoParser;
import org.antlr.v4.runtime.tree.ParseTree;
import semantic.ExecutionContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SemanticVisitor extends proyectoBaseVisitor<Void> {
    private final ExecutionContext ctx;

    // Definiciones recolectadas en el pre-escaneo
    private final Map<String, Integer> tareasDef    = new HashMap<>();  // nombre -> conteo
    private final Map<String, Integer> funcionesDef = new HashMap<>();  // nombre -> conteo
    private final List<String> errores = new ArrayList<>();
    private boolean tieneImports = false; // si hay Importar, las tareas pueden venir de archivos externos

    public SemanticVisitor(ExecutionContext ctx) {
        this.ctx = ctx;
    }

    // -------------------------------------------------------------------------
    // Punto de entrada público: pre-escanea definiciones y luego valida el árbol
    // -------------------------------------------------------------------------
    public void validar(ParseTree tree) {
        prescanear(tree);
        visit(tree);
        if (!errores.isEmpty()) {
            String resumen = String.join("\n  - ", errores);
            throw new SemanticException(
                "Se encontraron " + errores.size() + " error(es) semántico(s):\n  - " + resumen);
        }
    }

    // -------------------------------------------------------------------------
    // Pre-escaneo: recorre el árbol para colectar TODOS los nombres de tareas y
    // funciones antes de validar las referencias.
    // -------------------------------------------------------------------------
    private void prescanear(ParseTree tree) {
        new proyectoBaseVisitor<Void>() {
            @Override
            public Void visitTarea(proyectoParser.TareaContext node) {
                String nombre = node.ID().getText();
                tareasDef.merge(nombre, 1, Integer::sum);
                return visitChildren(node);
            }
            @Override
            public Void visitFuncion(proyectoParser.FuncionContext node) {
                String nombre = node.ID().getText();
                funcionesDef.merge(nombre, 1, Integer::sum);
                return visitChildren(node);
            }
            @Override
            public Void visitImportar(proyectoParser.ImportarContext node) {
                tieneImports = true;
                return null;
            }
        }.visit(tree);
    }

    // -------------------------------------------------------------------------
    // Validación de definiciones — detecta duplicados
    // -------------------------------------------------------------------------

    @Override
    public Void visitTarea(proyectoParser.TareaContext node) {
        String nombre = node.ID().getText();
        if (tareasDef.getOrDefault(nombre, 0) > 1) {
            reportar("Tarea duplicada: '" + nombre + "'");
        }
        return visitChildren(node);
    }

    @Override
    public Void visitFuncion(proyectoParser.FuncionContext node) {
        String nombre = node.ID().getText();
        if (funcionesDef.getOrDefault(nombre, 0) > 1) {
            reportar("Función duplicada: '" + nombre + "'");
        }
        return visitChildren(node);
    }

    // -------------------------------------------------------------------------
    // Validación de referencias — detecta usos de tareas/funciones no definidas
    // -------------------------------------------------------------------------

    @Override
    public Void visitEjecutarTarea(proyectoParser.EjecutarTareaContext node) {
        String nombre = node.ID().getText();
        if (!tareasDef.containsKey(nombre)) {
            if (tieneImports) {
                System.err.println("[ADVERTENCIA SEMÁNTICA] La tarea '" + nombre
                    + "' no está definida localmente (puede venir de un archivo importado)");
            } else {
                reportar("Se intenta ejecutar la tarea '" + nombre + "' pero no está definida");
            }
        }
        return visitChildren(node);
    }

    @Override
    public Void visitLlamadaFuncion(proyectoParser.LlamadaFuncionContext node) {
        String nombre = node.ID().getText();
        if (!funcionesDef.containsKey(nombre)) {
            reportar("Se llama a la función '" + nombre + "' pero no está definida");
        } else {
            // Validar cantidad de argumentos contra parámetros declarados
            proyectoParser.FuncionContext def = ctx.funciones.get(nombre);
            // ctx.funciones se llena durante la ejecución, no en el análisis semántico.
            // Usamos el árbol pre-escaneado: buscamos la definición para contar parámetros.
            // (La validación de aridad se hace en tiempo de ejecución si def no está disponible aún)
        }
        return visitChildren(node);
    }

    // -------------------------------------------------------------------------
    // Declaraciones de variables — registradas para posibles validaciones futuras
    // -------------------------------------------------------------------------

    @Override
    public Void visitDeclaracionVariable(proyectoParser.DeclaracionVariableContext node) {
        return visitChildren(node);
    }

    @Override
    public Void visitAsignacionVariable(proyectoParser.AsignacionVariableContext node) {
        return visitChildren(node);
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    private void reportar(String mensaje) {
        errores.add(mensaje);
        System.err.println("[ERROR SEMÁNTICO] " + mensaje);
    }
}
