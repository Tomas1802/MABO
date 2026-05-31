// Generated from .idea/grammar/proyecto.g4 by ANTLR 4.13.2

    package gen;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link proyectoParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface proyectoVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link proyectoParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(proyectoParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentencia(proyectoParser.SentenciaContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#importar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportar(proyectoParser.ImportarContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#cambiarDirectorio}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCambiarDirectorio(proyectoParser.CambiarDirectorioContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#mostrarRuta}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMostrarRuta(proyectoParser.MostrarRutaContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#declaracionVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracionVariable(proyectoParser.DeclaracionVariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#asignacionVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacionVariable(proyectoParser.AsignacionVariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncion(proyectoParser.FuncionContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#parametros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametros(proyectoParser.ParametrosContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#llamadaFuncion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLlamadaFuncion(proyectoParser.LlamadaFuncionContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#argumentos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentos(proyectoParser.ArgumentosContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#retornar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRetornar(proyectoParser.RetornarContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#tarea}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTarea(proyectoParser.TareaContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#ejecutarTarea}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEjecutarTarea(proyectoParser.EjecutarTareaContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#listarTareasProgramadas}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListarTareasProgramadas(proyectoParser.ListarTareasProgramadasContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#eliminarTareaProgramada}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEliminarTareaProgramada(proyectoParser.EliminarTareaProgramadaContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#cambiarTareaProgramada}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCambiarTareaProgramada(proyectoParser.CambiarTareaProgramadaContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#programacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramacion(proyectoParser.ProgramacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#tiempo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTiempo(proyectoParser.TiempoContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#condicion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondicion(proyectoParser.CondicionContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#cicloMientras}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCicloMientras(proyectoParser.CicloMientrasContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#cicloParaCada}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCicloParaCada(proyectoParser.CicloParaCadaContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#intentarCapturar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntentarCapturar(proyectoParser.IntentarCapturarContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#bloque}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque(proyectoParser.BloqueContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#simulacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimulacion(proyectoParser.SimulacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#comandoSistema}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComandoSistema(proyectoParser.ComandoSistemaContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#ejecutarPowerShell}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEjecutarPowerShell(proyectoParser.EjecutarPowerShellContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#ejecutarLinux}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEjecutarLinux(proyectoParser.EjecutarLinuxContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#accionArchivo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccionArchivo(proyectoParser.AccionArchivoContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#crearArchivo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCrearArchivo(proyectoParser.CrearArchivoContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#crearCarpeta}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCrearCarpeta(proyectoParser.CrearCarpetaContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#leerArchivo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeerArchivo(proyectoParser.LeerArchivoContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#buscarArchivos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBuscarArchivos(proyectoParser.BuscarArchivosContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#buscarCarpetas}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBuscarCarpetas(proyectoParser.BuscarCarpetasContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#listarContenido}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListarContenido(proyectoParser.ListarContenidoContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#existeArchivo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExisteArchivo(proyectoParser.ExisteArchivoContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#existeCarpeta}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExisteCarpeta(proyectoParser.ExisteCarpetaContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#obtenerTamano}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObtenerTamano(proyectoParser.ObtenerTamanoContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#obtenerCantidadArchivos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObtenerCantidadArchivos(proyectoParser.ObtenerCantidadArchivosContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#obtenerPropiedad}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObtenerPropiedad(proyectoParser.ObtenerPropiedadContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#copiarArchivo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCopiarArchivo(proyectoParser.CopiarArchivoContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#copiarCarpeta}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCopiarCarpeta(proyectoParser.CopiarCarpetaContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#moverArchivo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoverArchivo(proyectoParser.MoverArchivoContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#moverCarpeta}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoverCarpeta(proyectoParser.MoverCarpetaContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#renombrarArchivo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRenombrarArchivo(proyectoParser.RenombrarArchivoContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#renombrarCarpeta}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRenombrarCarpeta(proyectoParser.RenombrarCarpetaContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#escribirArchivo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEscribirArchivo(proyectoParser.EscribirArchivoContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#anexarArchivo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnexarArchivo(proyectoParser.AnexarArchivoContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#cambiarPermisos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCambiarPermisos(proyectoParser.CambiarPermisosContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#eliminarArchivo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEliminarArchivo(proyectoParser.EliminarArchivoContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#eliminarCarpeta}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEliminarCarpeta(proyectoParser.EliminarCarpetaContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#sinConfirmar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSinConfirmar(proyectoParser.SinConfirmarContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#comprimir}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComprimir(proyectoParser.ComprimirContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#descomprimir}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescomprimir(proyectoParser.DescomprimirContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#crearBackup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCrearBackup(proyectoParser.CrearBackupContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#filtros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFiltros(proyectoParser.FiltrosContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#filtro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFiltro(proyectoParser.FiltroContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#unidadTamano}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnidadTamano(proyectoParser.UnidadTamanoContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#mostrar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMostrar(proyectoParser.MostrarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numeroExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeroExpr(proyectoParser.NumeroExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code restaExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRestaExpr(proyectoParser.RestaExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code modExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModExpr(proyectoParser.ModExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mayorExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMayorExpr(proyectoParser.MayorExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code listaExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListaExpr(proyectoParser.ListaExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(proyectoParser.OrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcionExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncionExpr(proyectoParser.FuncionExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cadenaExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCadenaExpr(proyectoParser.CadenaExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code igualExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIgualExpr(proyectoParser.IgualExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultExpr(proyectoParser.MultExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(proyectoParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variableExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableExpr(proyectoParser.VariableExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpr(proyectoParser.NotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sumaExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSumaExpr(proyectoParser.SumaExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegExpr(proyectoParser.NegExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code menorExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMenorExpr(proyectoParser.MenorExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mayorIgualExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMayorIgualExpr(proyectoParser.MayorIgualExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code diferenteExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiferenteExpr(proyectoParser.DiferenteExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code divExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivExpr(proyectoParser.DivExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanoExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanoExpr(proyectoParser.BooleanoExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code menorIgualExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMenorIgualExpr(proyectoParser.MenorIgualExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link proyectoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(proyectoParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#lista}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLista(proyectoParser.ListaContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(proyectoParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#numero}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumero(proyectoParser.NumeroContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#cadena}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCadena(proyectoParser.CadenaContext ctx);
	/**
	 * Visit a parse tree produced by {@link proyectoParser#booleano}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleano(proyectoParser.BooleanoContext ctx);
}