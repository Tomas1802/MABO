// Generated from C:/Users/LauraValentinaPabonC/Downloads/Bash/Bash/.idea/grammar/proyecto.g4 by ANTLR 4.13.2

    package gen;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link proyectoParser}.
 */
public interface proyectoListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link proyectoParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(proyectoParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(proyectoParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterSentencia(proyectoParser.SentenciaContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitSentencia(proyectoParser.SentenciaContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#importar}.
	 * @param ctx the parse tree
	 */
	void enterImportar(proyectoParser.ImportarContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#importar}.
	 * @param ctx the parse tree
	 */
	void exitImportar(proyectoParser.ImportarContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#declaracionVariable}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionVariable(proyectoParser.DeclaracionVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#declaracionVariable}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionVariable(proyectoParser.DeclaracionVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#asignacionVariable}.
	 * @param ctx the parse tree
	 */
	void enterAsignacionVariable(proyectoParser.AsignacionVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#asignacionVariable}.
	 * @param ctx the parse tree
	 */
	void exitAsignacionVariable(proyectoParser.AsignacionVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#funcion}.
	 * @param ctx the parse tree
	 */
	void enterFuncion(proyectoParser.FuncionContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#funcion}.
	 * @param ctx the parse tree
	 */
	void exitFuncion(proyectoParser.FuncionContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#parametros}.
	 * @param ctx the parse tree
	 */
	void enterParametros(proyectoParser.ParametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#parametros}.
	 * @param ctx the parse tree
	 */
	void exitParametros(proyectoParser.ParametrosContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#llamadaFuncion}.
	 * @param ctx the parse tree
	 */
	void enterLlamadaFuncion(proyectoParser.LlamadaFuncionContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#llamadaFuncion}.
	 * @param ctx the parse tree
	 */
	void exitLlamadaFuncion(proyectoParser.LlamadaFuncionContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#argumentos}.
	 * @param ctx the parse tree
	 */
	void enterArgumentos(proyectoParser.ArgumentosContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#argumentos}.
	 * @param ctx the parse tree
	 */
	void exitArgumentos(proyectoParser.ArgumentosContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#retornar}.
	 * @param ctx the parse tree
	 */
	void enterRetornar(proyectoParser.RetornarContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#retornar}.
	 * @param ctx the parse tree
	 */
	void exitRetornar(proyectoParser.RetornarContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#tarea}.
	 * @param ctx the parse tree
	 */
	void enterTarea(proyectoParser.TareaContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#tarea}.
	 * @param ctx the parse tree
	 */
	void exitTarea(proyectoParser.TareaContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#ejecutarTarea}.
	 * @param ctx the parse tree
	 */
	void enterEjecutarTarea(proyectoParser.EjecutarTareaContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#ejecutarTarea}.
	 * @param ctx the parse tree
	 */
	void exitEjecutarTarea(proyectoParser.EjecutarTareaContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#programacion}.
	 * @param ctx the parse tree
	 */
	void enterProgramacion(proyectoParser.ProgramacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#programacion}.
	 * @param ctx the parse tree
	 */
	void exitProgramacion(proyectoParser.ProgramacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#tiempo}.
	 * @param ctx the parse tree
	 */
	void enterTiempo(proyectoParser.TiempoContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#tiempo}.
	 * @param ctx the parse tree
	 */
	void exitTiempo(proyectoParser.TiempoContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#condicion}.
	 * @param ctx the parse tree
	 */
	void enterCondicion(proyectoParser.CondicionContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#condicion}.
	 * @param ctx the parse tree
	 */
	void exitCondicion(proyectoParser.CondicionContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#cicloMientras}.
	 * @param ctx the parse tree
	 */
	void enterCicloMientras(proyectoParser.CicloMientrasContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#cicloMientras}.
	 * @param ctx the parse tree
	 */
	void exitCicloMientras(proyectoParser.CicloMientrasContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#cicloParaCada}.
	 * @param ctx the parse tree
	 */
	void enterCicloParaCada(proyectoParser.CicloParaCadaContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#cicloParaCada}.
	 * @param ctx the parse tree
	 */
	void exitCicloParaCada(proyectoParser.CicloParaCadaContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#intentarCapturar}.
	 * @param ctx the parse tree
	 */
	void enterIntentarCapturar(proyectoParser.IntentarCapturarContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#intentarCapturar}.
	 * @param ctx the parse tree
	 */
	void exitIntentarCapturar(proyectoParser.IntentarCapturarContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#bloque}.
	 * @param ctx the parse tree
	 */
	void enterBloque(proyectoParser.BloqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#bloque}.
	 * @param ctx the parse tree
	 */
	void exitBloque(proyectoParser.BloqueContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#simulacion}.
	 * @param ctx the parse tree
	 */
	void enterSimulacion(proyectoParser.SimulacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#simulacion}.
	 * @param ctx the parse tree
	 */
	void exitSimulacion(proyectoParser.SimulacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#accionArchivo}.
	 * @param ctx the parse tree
	 */
	void enterAccionArchivo(proyectoParser.AccionArchivoContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#accionArchivo}.
	 * @param ctx the parse tree
	 */
	void exitAccionArchivo(proyectoParser.AccionArchivoContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#crearArchivo}.
	 * @param ctx the parse tree
	 */
	void enterCrearArchivo(proyectoParser.CrearArchivoContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#crearArchivo}.
	 * @param ctx the parse tree
	 */
	void exitCrearArchivo(proyectoParser.CrearArchivoContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#crearCarpeta}.
	 * @param ctx the parse tree
	 */
	void enterCrearCarpeta(proyectoParser.CrearCarpetaContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#crearCarpeta}.
	 * @param ctx the parse tree
	 */
	void exitCrearCarpeta(proyectoParser.CrearCarpetaContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#leerArchivo}.
	 * @param ctx the parse tree
	 */
	void enterLeerArchivo(proyectoParser.LeerArchivoContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#leerArchivo}.
	 * @param ctx the parse tree
	 */
	void exitLeerArchivo(proyectoParser.LeerArchivoContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#buscarArchivos}.
	 * @param ctx the parse tree
	 */
	void enterBuscarArchivos(proyectoParser.BuscarArchivosContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#buscarArchivos}.
	 * @param ctx the parse tree
	 */
	void exitBuscarArchivos(proyectoParser.BuscarArchivosContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#buscarCarpetas}.
	 * @param ctx the parse tree
	 */
	void enterBuscarCarpetas(proyectoParser.BuscarCarpetasContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#buscarCarpetas}.
	 * @param ctx the parse tree
	 */
	void exitBuscarCarpetas(proyectoParser.BuscarCarpetasContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#listarContenido}.
	 * @param ctx the parse tree
	 */
	void enterListarContenido(proyectoParser.ListarContenidoContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#listarContenido}.
	 * @param ctx the parse tree
	 */
	void exitListarContenido(proyectoParser.ListarContenidoContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#existeArchivo}.
	 * @param ctx the parse tree
	 */
	void enterExisteArchivo(proyectoParser.ExisteArchivoContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#existeArchivo}.
	 * @param ctx the parse tree
	 */
	void exitExisteArchivo(proyectoParser.ExisteArchivoContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#existeCarpeta}.
	 * @param ctx the parse tree
	 */
	void enterExisteCarpeta(proyectoParser.ExisteCarpetaContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#existeCarpeta}.
	 * @param ctx the parse tree
	 */
	void exitExisteCarpeta(proyectoParser.ExisteCarpetaContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#obtenerTamano}.
	 * @param ctx the parse tree
	 */
	void enterObtenerTamano(proyectoParser.ObtenerTamanoContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#obtenerTamano}.
	 * @param ctx the parse tree
	 */
	void exitObtenerTamano(proyectoParser.ObtenerTamanoContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#obtenerCantidadArchivos}.
	 * @param ctx the parse tree
	 */
	void enterObtenerCantidadArchivos(proyectoParser.ObtenerCantidadArchivosContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#obtenerCantidadArchivos}.
	 * @param ctx the parse tree
	 */
	void exitObtenerCantidadArchivos(proyectoParser.ObtenerCantidadArchivosContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#obtenerPropiedad}.
	 * @param ctx the parse tree
	 */
	void enterObtenerPropiedad(proyectoParser.ObtenerPropiedadContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#obtenerPropiedad}.
	 * @param ctx the parse tree
	 */
	void exitObtenerPropiedad(proyectoParser.ObtenerPropiedadContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#copiarArchivo}.
	 * @param ctx the parse tree
	 */
	void enterCopiarArchivo(proyectoParser.CopiarArchivoContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#copiarArchivo}.
	 * @param ctx the parse tree
	 */
	void exitCopiarArchivo(proyectoParser.CopiarArchivoContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#copiarCarpeta}.
	 * @param ctx the parse tree
	 */
	void enterCopiarCarpeta(proyectoParser.CopiarCarpetaContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#copiarCarpeta}.
	 * @param ctx the parse tree
	 */
	void exitCopiarCarpeta(proyectoParser.CopiarCarpetaContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#moverArchivo}.
	 * @param ctx the parse tree
	 */
	void enterMoverArchivo(proyectoParser.MoverArchivoContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#moverArchivo}.
	 * @param ctx the parse tree
	 */
	void exitMoverArchivo(proyectoParser.MoverArchivoContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#moverCarpeta}.
	 * @param ctx the parse tree
	 */
	void enterMoverCarpeta(proyectoParser.MoverCarpetaContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#moverCarpeta}.
	 * @param ctx the parse tree
	 */
	void exitMoverCarpeta(proyectoParser.MoverCarpetaContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#renombrarArchivo}.
	 * @param ctx the parse tree
	 */
	void enterRenombrarArchivo(proyectoParser.RenombrarArchivoContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#renombrarArchivo}.
	 * @param ctx the parse tree
	 */
	void exitRenombrarArchivo(proyectoParser.RenombrarArchivoContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#renombrarCarpeta}.
	 * @param ctx the parse tree
	 */
	void enterRenombrarCarpeta(proyectoParser.RenombrarCarpetaContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#renombrarCarpeta}.
	 * @param ctx the parse tree
	 */
	void exitRenombrarCarpeta(proyectoParser.RenombrarCarpetaContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#escribirArchivo}.
	 * @param ctx the parse tree
	 */
	void enterEscribirArchivo(proyectoParser.EscribirArchivoContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#escribirArchivo}.
	 * @param ctx the parse tree
	 */
	void exitEscribirArchivo(proyectoParser.EscribirArchivoContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#anexarArchivo}.
	 * @param ctx the parse tree
	 */
	void enterAnexarArchivo(proyectoParser.AnexarArchivoContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#anexarArchivo}.
	 * @param ctx the parse tree
	 */
	void exitAnexarArchivo(proyectoParser.AnexarArchivoContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#cambiarPermisos}.
	 * @param ctx the parse tree
	 */
	void enterCambiarPermisos(proyectoParser.CambiarPermisosContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#cambiarPermisos}.
	 * @param ctx the parse tree
	 */
	void exitCambiarPermisos(proyectoParser.CambiarPermisosContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#eliminarArchivo}.
	 * @param ctx the parse tree
	 */
	void enterEliminarArchivo(proyectoParser.EliminarArchivoContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#eliminarArchivo}.
	 * @param ctx the parse tree
	 */
	void exitEliminarArchivo(proyectoParser.EliminarArchivoContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#eliminarCarpeta}.
	 * @param ctx the parse tree
	 */
	void enterEliminarCarpeta(proyectoParser.EliminarCarpetaContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#eliminarCarpeta}.
	 * @param ctx the parse tree
	 */
	void exitEliminarCarpeta(proyectoParser.EliminarCarpetaContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#comprimir}.
	 * @param ctx the parse tree
	 */
	void enterComprimir(proyectoParser.ComprimirContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#comprimir}.
	 * @param ctx the parse tree
	 */
	void exitComprimir(proyectoParser.ComprimirContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#descomprimir}.
	 * @param ctx the parse tree
	 */
	void enterDescomprimir(proyectoParser.DescomprimirContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#descomprimir}.
	 * @param ctx the parse tree
	 */
	void exitDescomprimir(proyectoParser.DescomprimirContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#crearBackup}.
	 * @param ctx the parse tree
	 */
	void enterCrearBackup(proyectoParser.CrearBackupContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#crearBackup}.
	 * @param ctx the parse tree
	 */
	void exitCrearBackup(proyectoParser.CrearBackupContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#filtros}.
	 * @param ctx the parse tree
	 */
	void enterFiltros(proyectoParser.FiltrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#filtros}.
	 * @param ctx the parse tree
	 */
	void exitFiltros(proyectoParser.FiltrosContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#filtro}.
	 * @param ctx the parse tree
	 */
	void enterFiltro(proyectoParser.FiltroContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#filtro}.
	 * @param ctx the parse tree
	 */
	void exitFiltro(proyectoParser.FiltroContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#mostrar}.
	 * @param ctx the parse tree
	 */
	void enterMostrar(proyectoParser.MostrarContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#mostrar}.
	 * @param ctx the parse tree
	 */
	void exitMostrar(proyectoParser.MostrarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numeroExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterNumeroExpr(proyectoParser.NumeroExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numeroExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitNumeroExpr(proyectoParser.NumeroExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code restaExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterRestaExpr(proyectoParser.RestaExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code restaExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitRestaExpr(proyectoParser.RestaExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code modExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterModExpr(proyectoParser.ModExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code modExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitModExpr(proyectoParser.ModExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mayorExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterMayorExpr(proyectoParser.MayorExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mayorExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitMayorExpr(proyectoParser.MayorExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code listaExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterListaExpr(proyectoParser.ListaExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code listaExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitListaExpr(proyectoParser.ListaExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(proyectoParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(proyectoParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcionExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterFuncionExpr(proyectoParser.FuncionExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcionExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitFuncionExpr(proyectoParser.FuncionExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cadenaExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterCadenaExpr(proyectoParser.CadenaExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cadenaExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitCadenaExpr(proyectoParser.CadenaExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code igualExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterIgualExpr(proyectoParser.IgualExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code igualExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitIgualExpr(proyectoParser.IgualExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterMultExpr(proyectoParser.MultExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitMultExpr(proyectoParser.MultExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(proyectoParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(proyectoParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variableExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterVariableExpr(proyectoParser.VariableExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variableExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitVariableExpr(proyectoParser.VariableExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterNotExpr(proyectoParser.NotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitNotExpr(proyectoParser.NotExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sumaExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterSumaExpr(proyectoParser.SumaExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sumaExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitSumaExpr(proyectoParser.SumaExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code negExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterNegExpr(proyectoParser.NegExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code negExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitNegExpr(proyectoParser.NegExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code menorExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterMenorExpr(proyectoParser.MenorExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code menorExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitMenorExpr(proyectoParser.MenorExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mayorIgualExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterMayorIgualExpr(proyectoParser.MayorIgualExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mayorIgualExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitMayorIgualExpr(proyectoParser.MayorIgualExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code diferenteExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterDiferenteExpr(proyectoParser.DiferenteExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code diferenteExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitDiferenteExpr(proyectoParser.DiferenteExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code divExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterDivExpr(proyectoParser.DivExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code divExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitDivExpr(proyectoParser.DivExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleanoExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterBooleanoExpr(proyectoParser.BooleanoExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanoExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitBooleanoExpr(proyectoParser.BooleanoExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code menorIgualExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterMenorIgualExpr(proyectoParser.MenorIgualExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code menorIgualExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitMenorIgualExpr(proyectoParser.MenorIgualExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(proyectoParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(proyectoParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#lista}.
	 * @param ctx the parse tree
	 */
	void enterLista(proyectoParser.ListaContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#lista}.
	 * @param ctx the parse tree
	 */
	void exitLista(proyectoParser.ListaContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(proyectoParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(proyectoParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#numero}.
	 * @param ctx the parse tree
	 */
	void enterNumero(proyectoParser.NumeroContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#numero}.
	 * @param ctx the parse tree
	 */
	void exitNumero(proyectoParser.NumeroContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#cadena}.
	 * @param ctx the parse tree
	 */
	void enterCadena(proyectoParser.CadenaContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#cadena}.
	 * @param ctx the parse tree
	 */
	void exitCadena(proyectoParser.CadenaContext ctx);
	/**
	 * Enter a parse tree produced by {@link proyectoParser#booleano}.
	 * @param ctx the parse tree
	 */
	void enterBooleano(proyectoParser.BooleanoContext ctx);
	/**
	 * Exit a parse tree produced by {@link proyectoParser#booleano}.
	 * @param ctx the parse tree
	 */
	void exitBooleano(proyectoParser.BooleanoContext ctx);
}