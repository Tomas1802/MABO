// Generated from .idea/grammar/proyecto.g4 by ANTLR 4.13.2

    package gen;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class proyectoParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IMPORTAR=1, VARIABLE=2, EN=3, FUNCION=4, FINFUNCION=5, RETORNAR=6, FIN=7,
		TAREA=8, TAREAS=9, PROGRAMADA=10, PROGRAMADAS=11, PROGRAMACION=12, EJECUTAR=13,
		SI=14, ENTONCES=15, SINO=16, FINSI=17, MIENTRAS=18, FINMIENTRAS=19, PARA=20,
		CADA=21, FINPARA=22, INTENTAR=23, CAPTURAR=24, FININTENTAR=25, SIMULAR=26,
		CREAR=27, LEER=28, ESCRIBIR=29, ANEXAR=30, BUSCAR=31, LISTAR=32, ELIMINAR=33,
		MOVER=34, COPIAR=35, RENOMBRAR=36, OBTENER=37, EXISTE=38, CAMBIAR=39,
		TAMANO=40, CANTIDAD=41, ARCHIVO=42, ARCHIVOS=43, CARPETA=44, CARPETAS=45,
		CONTENIDO=46, BACKUP=47, COMPRESION=48, COMPRIMIR=49, DESCOMPRIMIR=50,
		PROPIEDAD=51, PERMISOS=52, CON=53, DE=54, A=55, EXTENSION=56, NOMBRE=57,
		PREFIJO=58, SUFIJO=59, MAYOR=60, MENOR=61, ANTIGUOS=62, RECIENTES=63,
		QUE=64, RECURSIVAMENTE=65, SIN=66, CONFIRMAR=67, AL=68, INICIAR=69, SISTEMA=70,
		LAS=71, IR=72, RUTA=73, HORAS=74, MINUTOS=75, DIAS=76, BYTES=77, KB=78,
		MB=79, GB=80, POWERSHELL=81, LINUX=82, MOSTRAR=83, VERDADERO=84, FALSO=85,
		AND=86, OR=87, NOT=88, IGUAL=89, IGUAL_IGUAL=90, DIFERENTE=91, MAYOR_QUE=92,
		MENOR_QUE=93, MAYOR_IGUAL=94, MENOR_IGUAL=95, MAS=96, MENOS=97, MULT=98,
		DIV=99, MOD=100, PAR_IZQ=101, PAR_DER=102, CORCH_IZQ=103, CORCH_DER=104,
		COMA=105, DOS_PUNTOS=106, ID=107, ENTERO=108, DECIMAL=109, STRING=110,
		WS=111, COMENTARIO=112;
	public static final int
		RULE_programa = 0, RULE_sentencia = 1, RULE_importar = 2, RULE_cambiarDirectorio = 3,
		RULE_mostrarRuta = 4, RULE_declaracionVariable = 5, RULE_asignacionVariable = 6,
		RULE_funcion = 7, RULE_parametros = 8, RULE_llamadaFuncion = 9, RULE_argumentos = 10,
		RULE_retornar = 11, RULE_tarea = 12, RULE_ejecutarTarea = 13, RULE_ejecutarArchivo = 14,
		RULE_listarTareasProgramadas = 15, RULE_eliminarTareasProgramadas = 16,
		RULE_eliminarTareaProgramada = 17, RULE_cambiarTareaProgramada = 18, RULE_programacion = 19,
		RULE_tiempo = 20, RULE_condicion = 21, RULE_cicloMientras = 22, RULE_cicloParaCada = 23,
		RULE_intentarCapturar = 24, RULE_bloque = 25, RULE_simulacion = 26, RULE_comandoSistema = 27,
		RULE_ejecutarPowerShell = 28, RULE_ejecutarLinux = 29, RULE_accionArchivo = 30,
		RULE_crearArchivo = 31, RULE_crearCarpeta = 32, RULE_leerArchivo = 33,
		RULE_buscarArchivos = 34, RULE_buscarCarpetas = 35, RULE_listarContenido = 36,
		RULE_existeArchivo = 37, RULE_existeCarpeta = 38, RULE_obtenerTamano = 39,
		RULE_obtenerCantidadArchivos = 40, RULE_obtenerPropiedad = 41, RULE_copiarArchivo = 42,
		RULE_copiarCarpeta = 43, RULE_moverArchivo = 44, RULE_moverCarpeta = 45,
		RULE_renombrarArchivo = 46, RULE_renombrarCarpeta = 47, RULE_escribirArchivo = 48,
		RULE_anexarArchivo = 49, RULE_cambiarPermisos = 50, RULE_eliminarArchivo = 51,
		RULE_eliminarCarpeta = 52, RULE_sinConfirmar = 53, RULE_comprimir = 54,
		RULE_descomprimir = 55, RULE_crearBackup = 56, RULE_filtros = 57, RULE_filtro = 58,
		RULE_unidadTamano = 59, RULE_mostrar = 60, RULE_expresion = 61, RULE_lista = 62,
		RULE_variable = 63, RULE_numero = 64, RULE_cadena = 65, RULE_booleano = 66;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "sentencia", "importar", "cambiarDirectorio", "mostrarRuta",
			"declaracionVariable", "asignacionVariable", "funcion", "parametros",
			"llamadaFuncion", "argumentos", "retornar", "tarea", "ejecutarTarea",
			"ejecutarArchivo", "listarTareasProgramadas", "eliminarTareasProgramadas",
			"eliminarTareaProgramada", "cambiarTareaProgramada", "programacion",
			"tiempo", "condicion", "cicloMientras", "cicloParaCada", "intentarCapturar",
			"bloque", "simulacion", "comandoSistema", "ejecutarPowerShell", "ejecutarLinux",
			"accionArchivo", "crearArchivo", "crearCarpeta", "leerArchivo", "buscarArchivos",
			"buscarCarpetas", "listarContenido", "existeArchivo", "existeCarpeta",
			"obtenerTamano", "obtenerCantidadArchivos", "obtenerPropiedad", "copiarArchivo",
			"copiarCarpeta", "moverArchivo", "moverCarpeta", "renombrarArchivo",
			"renombrarCarpeta", "escribirArchivo", "anexarArchivo", "cambiarPermisos",
			"eliminarArchivo", "eliminarCarpeta", "sinConfirmar", "comprimir", "descomprimir",
			"crearBackup", "filtros", "filtro", "unidadTamano", "mostrar", "expresion",
			"lista", "variable", "numero", "cadena", "booleano"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Importar'", "'Variable'", "'En'", null, "'FinFuncion'", "'Retornar'",
			"'Fin'", "'Tarea'", "'Tareas'", "'Programada'", "'Programadas'", null,
			"'Ejecutar'", "'Si'", "'Entonces'", "'Sino'", "'FinSi'", "'Mientras'",
			"'FinMientras'", "'Para'", "'Cada'", "'FinPara'", "'Intentar'", "'Capturar'",
			"'FinIntentar'", "'Simular'", "'Crear'", "'Leer'", "'Escribir'", "'Anexar'",
			"'Buscar'", "'Listar'", "'Eliminar'", "'Mover'", "'Copiar'", "'Renombrar'",
			"'Obtener'", "'Existe'", "'Cambiar'", null, "'Cantidad'", "'Archivo'",
			"'Archivos'", "'Carpeta'", "'Carpetas'", "'Contenido'", "'Backup'", null,
			"'Comprimir'", "'Descomprimir'", "'Propiedad'", "'Permisos'", "'Con'",
			"'De'", "'A'", null, "'Nombre'", "'Prefijo'", "'Sufijo'", "'Mayor'",
			"'Menor'", "'Antiguos'", "'Recientes'", "'Que'", "'Recursivamente'",
			"'Sin'", "'Confirmar'", "'Al'", "'Iniciar'", "'Sistema'", "'Las'", "'Ir'",
			"'Ruta'", "'Horas'", "'Minutos'", null, "'Bytes'", "'KB'", "'MB'", "'GB'",
			"'PowerShell'", "'Linux'", "'Mostrar'", "'Verdadero'", "'Falso'", "'Y'",
			"'O'", "'No'", "'='", "'=='", "'!='", "'>'", "'<'", "'>='", "'<='", null,
			null, null, null, null, "'('", "')'", "'['", "']'", "','", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IMPORTAR", "VARIABLE", "EN", "FUNCION", "FINFUNCION", "RETORNAR",
			"FIN", "TAREA", "TAREAS", "PROGRAMADA", "PROGRAMADAS", "PROGRAMACION",
			"EJECUTAR", "SI", "ENTONCES", "SINO", "FINSI", "MIENTRAS", "FINMIENTRAS",
			"PARA", "CADA", "FINPARA", "INTENTAR", "CAPTURAR", "FININTENTAR", "SIMULAR",
			"CREAR", "LEER", "ESCRIBIR", "ANEXAR", "BUSCAR", "LISTAR", "ELIMINAR",
			"MOVER", "COPIAR", "RENOMBRAR", "OBTENER", "EXISTE", "CAMBIAR", "TAMANO",
			"CANTIDAD", "ARCHIVO", "ARCHIVOS", "CARPETA", "CARPETAS", "CONTENIDO",
			"BACKUP", "COMPRESION", "COMPRIMIR", "DESCOMPRIMIR", "PROPIEDAD", "PERMISOS",
			"CON", "DE", "A", "EXTENSION", "NOMBRE", "PREFIJO", "SUFIJO", "MAYOR",
			"MENOR", "ANTIGUOS", "RECIENTES", "QUE", "RECURSIVAMENTE", "SIN", "CONFIRMAR",
			"AL", "INICIAR", "SISTEMA", "LAS", "IR", "RUTA", "HORAS", "MINUTOS",
			"DIAS", "BYTES", "KB", "MB", "GB", "POWERSHELL", "LINUX", "MOSTRAR",
			"VERDADERO", "FALSO", "AND", "OR", "NOT", "IGUAL", "IGUAL_IGUAL", "DIFERENTE",
			"MAYOR_QUE", "MENOR_QUE", "MAYOR_IGUAL", "MENOR_IGUAL", "MAS", "MENOS",
			"MULT", "DIV", "MOD", "PAR_IZQ", "PAR_DER", "CORCH_IZQ", "CORCH_DER",
			"COMA", "DOS_PUNTOS", "ID", "ENTERO", "DECIMAL", "STRING", "WS", "COMENTARIO"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "proyecto.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public proyectoParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramaContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(proyectoParser.EOF, 0); }
		public List<SentenciaContext> sentencia() {
			return getRuleContexts(SentenciaContext.class);
		}
		public SentenciaContext sentencia(int i) {
			return getRuleContext(SentenciaContext.class,i);
		}
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitPrograma(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitPrograma(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1689949314507094L) != 0) || ((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & 518114064385L) != 0)) {
				{
				{
				setState(134);
				sentencia();
				}
				}
				setState(139);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(140);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SentenciaContext extends ParserRuleContext {
		public DeclaracionVariableContext declaracionVariable() {
			return getRuleContext(DeclaracionVariableContext.class,0);
		}
		public AsignacionVariableContext asignacionVariable() {
			return getRuleContext(AsignacionVariableContext.class,0);
		}
		public MostrarRutaContext mostrarRuta() {
			return getRuleContext(MostrarRutaContext.class,0);
		}
		public CambiarDirectorioContext cambiarDirectorio() {
			return getRuleContext(CambiarDirectorioContext.class,0);
		}
		public MostrarContext mostrar() {
			return getRuleContext(MostrarContext.class,0);
		}
		public CondicionContext condicion() {
			return getRuleContext(CondicionContext.class,0);
		}
		public CicloMientrasContext cicloMientras() {
			return getRuleContext(CicloMientrasContext.class,0);
		}
		public CicloParaCadaContext cicloParaCada() {
			return getRuleContext(CicloParaCadaContext.class,0);
		}
		public FuncionContext funcion() {
			return getRuleContext(FuncionContext.class,0);
		}
		public LlamadaFuncionContext llamadaFuncion() {
			return getRuleContext(LlamadaFuncionContext.class,0);
		}
		public RetornarContext retornar() {
			return getRuleContext(RetornarContext.class,0);
		}
		public TareaContext tarea() {
			return getRuleContext(TareaContext.class,0);
		}
		public EjecutarArchivoContext ejecutarArchivo() {
			return getRuleContext(EjecutarArchivoContext.class,0);
		}
		public EjecutarTareaContext ejecutarTarea() {
			return getRuleContext(EjecutarTareaContext.class,0);
		}
		public ListarTareasProgramadasContext listarTareasProgramadas() {
			return getRuleContext(ListarTareasProgramadasContext.class,0);
		}
		public EliminarTareasProgramadasContext eliminarTareasProgramadas() {
			return getRuleContext(EliminarTareasProgramadasContext.class,0);
		}
		public EliminarTareaProgramadaContext eliminarTareaProgramada() {
			return getRuleContext(EliminarTareaProgramadaContext.class,0);
		}
		public CambiarTareaProgramadaContext cambiarTareaProgramada() {
			return getRuleContext(CambiarTareaProgramadaContext.class,0);
		}
		public ComandoSistemaContext comandoSistema() {
			return getRuleContext(ComandoSistemaContext.class,0);
		}
		public SimulacionContext simulacion() {
			return getRuleContext(SimulacionContext.class,0);
		}
		public IntentarCapturarContext intentarCapturar() {
			return getRuleContext(IntentarCapturarContext.class,0);
		}
		public ImportarContext importar() {
			return getRuleContext(ImportarContext.class,0);
		}
		public AccionArchivoContext accionArchivo() {
			return getRuleContext(AccionArchivoContext.class,0);
		}
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public SentenciaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentencia; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterSentencia(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitSentencia(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitSentencia(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SentenciaContext sentencia() throws RecognitionException {
		SentenciaContext _localctx = new SentenciaContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_sentencia);
		try {
			setState(166);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(142);
				declaracionVariable();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(143);
				asignacionVariable();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(144);
				mostrarRuta();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(145);
				cambiarDirectorio();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(146);
				mostrar();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(147);
				condicion();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(148);
				cicloMientras();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(149);
				cicloParaCada();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(150);
				funcion();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(151);
				llamadaFuncion();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(152);
				retornar();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(153);
				tarea();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(154);
				ejecutarArchivo();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(155);
				ejecutarTarea();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(156);
				listarTareasProgramadas();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(157);
				eliminarTareasProgramadas();
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(158);
				eliminarTareaProgramada();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(159);
				cambiarTareaProgramada();
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(160);
				comandoSistema();
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(161);
				simulacion();
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(162);
				intentarCapturar();
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(163);
				importar();
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(164);
				accionArchivo();
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 24);
				{
				setState(165);
				expresion(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportarContext extends ParserRuleContext {
		public TerminalNode IMPORTAR() { return getToken(proyectoParser.IMPORTAR, 0); }
		public CadenaContext cadena() {
			return getRuleContext(CadenaContext.class,0);
		}
		public ImportarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterImportar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitImportar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitImportar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportarContext importar() throws RecognitionException {
		ImportarContext _localctx = new ImportarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_importar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(IMPORTAR);
			setState(169);
			cadena();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CambiarDirectorioContext extends ParserRuleContext {
		public TerminalNode IR() { return getToken(proyectoParser.IR, 0); }
		public TerminalNode A() { return getToken(proyectoParser.A, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public CambiarDirectorioContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cambiarDirectorio; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterCambiarDirectorio(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitCambiarDirectorio(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitCambiarDirectorio(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CambiarDirectorioContext cambiarDirectorio() throws RecognitionException {
		CambiarDirectorioContext _localctx = new CambiarDirectorioContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_cambiarDirectorio);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			match(IR);
			setState(172);
			match(A);
			setState(173);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MostrarRutaContext extends ParserRuleContext {
		public TerminalNode MOSTRAR() { return getToken(proyectoParser.MOSTRAR, 0); }
		public TerminalNode RUTA() { return getToken(proyectoParser.RUTA, 0); }
		public MostrarRutaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mostrarRuta; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterMostrarRuta(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitMostrarRuta(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitMostrarRuta(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MostrarRutaContext mostrarRuta() throws RecognitionException {
		MostrarRutaContext _localctx = new MostrarRutaContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_mostrarRuta);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(MOSTRAR);
			setState(176);
			match(RUTA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaracionVariableContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(proyectoParser.VARIABLE, 0); }
		public TerminalNode ID() { return getToken(proyectoParser.ID, 0); }
		public TerminalNode IGUAL() { return getToken(proyectoParser.IGUAL, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public DeclaracionVariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracionVariable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterDeclaracionVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitDeclaracionVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitDeclaracionVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclaracionVariableContext declaracionVariable() throws RecognitionException {
		DeclaracionVariableContext _localctx = new DeclaracionVariableContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_declaracionVariable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			match(VARIABLE);
			setState(179);
			match(ID);
			setState(180);
			match(IGUAL);
			setState(181);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AsignacionVariableContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(proyectoParser.ID, 0); }
		public TerminalNode IGUAL() { return getToken(proyectoParser.IGUAL, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public AsignacionVariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_asignacionVariable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterAsignacionVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitAsignacionVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitAsignacionVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AsignacionVariableContext asignacionVariable() throws RecognitionException {
		AsignacionVariableContext _localctx = new AsignacionVariableContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_asignacionVariable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(ID);
			setState(184);
			match(IGUAL);
			setState(185);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncionContext extends ParserRuleContext {
		public TerminalNode FUNCION() { return getToken(proyectoParser.FUNCION, 0); }
		public TerminalNode ID() { return getToken(proyectoParser.ID, 0); }
		public TerminalNode PAR_IZQ() { return getToken(proyectoParser.PAR_IZQ, 0); }
		public TerminalNode PAR_DER() { return getToken(proyectoParser.PAR_DER, 0); }
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public TerminalNode FINFUNCION() { return getToken(proyectoParser.FINFUNCION, 0); }
		public ParametrosContext parametros() {
			return getRuleContext(ParametrosContext.class,0);
		}
		public FuncionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterFuncion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitFuncion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitFuncion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncionContext funcion() throws RecognitionException {
		FuncionContext _localctx = new FuncionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_funcion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			match(FUNCION);
			setState(188);
			match(ID);
			setState(189);
			match(PAR_IZQ);
			setState(191);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(190);
				parametros();
				}
			}

			setState(193);
			match(PAR_DER);
			setState(194);
			bloque();
			setState(195);
			match(FINFUNCION);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParametrosContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(proyectoParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(proyectoParser.ID, i);
		}
		public List<TerminalNode> COMA() { return getTokens(proyectoParser.COMA); }
		public TerminalNode COMA(int i) {
			return getToken(proyectoParser.COMA, i);
		}
		public ParametrosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parametros; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterParametros(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitParametros(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitParametros(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParametrosContext parametros() throws RecognitionException {
		ParametrosContext _localctx = new ParametrosContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_parametros);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			match(ID);
			setState(202);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMA) {
				{
				{
				setState(198);
				match(COMA);
				setState(199);
				match(ID);
				}
				}
				setState(204);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LlamadaFuncionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(proyectoParser.ID, 0); }
		public TerminalNode PAR_IZQ() { return getToken(proyectoParser.PAR_IZQ, 0); }
		public TerminalNode PAR_DER() { return getToken(proyectoParser.PAR_DER, 0); }
		public ArgumentosContext argumentos() {
			return getRuleContext(ArgumentosContext.class,0);
		}
		public LlamadaFuncionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_llamadaFuncion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterLlamadaFuncion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitLlamadaFuncion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitLlamadaFuncion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LlamadaFuncionContext llamadaFuncion() throws RecognitionException {
		LlamadaFuncionContext _localctx = new LlamadaFuncionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_llamadaFuncion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			match(ID);
			setState(206);
			match(PAR_IZQ);
			setState(208);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 84)) & ~0x3f) == 0 && ((1L << (_la - 84)) & 126492691L) != 0)) {
				{
				setState(207);
				argumentos();
				}
			}

			setState(210);
			match(PAR_DER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentosContext extends ParserRuleContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public List<TerminalNode> COMA() { return getTokens(proyectoParser.COMA); }
		public TerminalNode COMA(int i) {
			return getToken(proyectoParser.COMA, i);
		}
		public ArgumentosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterArgumentos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitArgumentos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitArgumentos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentosContext argumentos() throws RecognitionException {
		ArgumentosContext _localctx = new ArgumentosContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_argumentos);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			expresion(0);
			setState(217);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMA) {
				{
				{
				setState(213);
				match(COMA);
				setState(214);
				expresion(0);
				}
				}
				setState(219);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RetornarContext extends ParserRuleContext {
		public TerminalNode RETORNAR() { return getToken(proyectoParser.RETORNAR, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public RetornarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_retornar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterRetornar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitRetornar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitRetornar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RetornarContext retornar() throws RecognitionException {
		RetornarContext _localctx = new RetornarContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_retornar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			match(RETORNAR);
			setState(221);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TareaContext extends ParserRuleContext {
		public TerminalNode TAREA() { return getToken(proyectoParser.TAREA, 0); }
		public TerminalNode ID() { return getToken(proyectoParser.ID, 0); }
		public TerminalNode DOS_PUNTOS() { return getToken(proyectoParser.DOS_PUNTOS, 0); }
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public TerminalNode FIN() { return getToken(proyectoParser.FIN, 0); }
		public TareaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tarea; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterTarea(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitTarea(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitTarea(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TareaContext tarea() throws RecognitionException {
		TareaContext _localctx = new TareaContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_tarea);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			match(TAREA);
			setState(224);
			match(ID);
			setState(225);
			match(DOS_PUNTOS);
			setState(226);
			bloque();
			setState(227);
			match(FIN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EjecutarTareaContext extends ParserRuleContext {
		public TerminalNode EJECUTAR() { return getToken(proyectoParser.EJECUTAR, 0); }
		public TerminalNode ID() { return getToken(proyectoParser.ID, 0); }
		public TerminalNode TAREA() { return getToken(proyectoParser.TAREA, 0); }
		public ProgramacionContext programacion() {
			return getRuleContext(ProgramacionContext.class,0);
		}
		public EjecutarTareaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ejecutarTarea; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterEjecutarTarea(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitEjecutarTarea(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitEjecutarTarea(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EjecutarTareaContext ejecutarTarea() throws RecognitionException {
		EjecutarTareaContext _localctx = new EjecutarTareaContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_ejecutarTarea);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			match(EJECUTAR);
			setState(231);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TAREA) {
				{
				setState(230);
				match(TAREA);
				}
			}

			setState(233);
			match(ID);
			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 21)) & ~0x3f) == 0 && ((1L << (_la - 21)) & 140754668224513L) != 0)) {
				{
				setState(234);
				programacion();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EjecutarArchivoContext extends ParserRuleContext {
		public TerminalNode EJECUTAR() { return getToken(proyectoParser.EJECUTAR, 0); }
		public TerminalNode ARCHIVO() { return getToken(proyectoParser.ARCHIVO, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public ProgramacionContext programacion() {
			return getRuleContext(ProgramacionContext.class,0);
		}
		public EjecutarArchivoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ejecutarArchivo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterEjecutarArchivo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitEjecutarArchivo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitEjecutarArchivo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EjecutarArchivoContext ejecutarArchivo() throws RecognitionException {
		EjecutarArchivoContext _localctx = new EjecutarArchivoContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_ejecutarArchivo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			match(EJECUTAR);
			setState(238);
			match(ARCHIVO);
			setState(239);
			expresion(0);
			setState(241);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 21)) & ~0x3f) == 0 && ((1L << (_la - 21)) & 140754668224513L) != 0)) {
				{
				setState(240);
				programacion();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListarTareasProgramadasContext extends ParserRuleContext {
		public TerminalNode LISTAR() { return getToken(proyectoParser.LISTAR, 0); }
		public TerminalNode TAREAS() { return getToken(proyectoParser.TAREAS, 0); }
		public TerminalNode PROGRAMADAS() { return getToken(proyectoParser.PROGRAMADAS, 0); }
		public ListarTareasProgramadasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listarTareasProgramadas; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterListarTareasProgramadas(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitListarTareasProgramadas(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitListarTareasProgramadas(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListarTareasProgramadasContext listarTareasProgramadas() throws RecognitionException {
		ListarTareasProgramadasContext _localctx = new ListarTareasProgramadasContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_listarTareasProgramadas);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
			match(LISTAR);
			setState(244);
			match(TAREAS);
			setState(245);
			match(PROGRAMADAS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EliminarTareasProgramadasContext extends ParserRuleContext {
		public TerminalNode ELIMINAR() { return getToken(proyectoParser.ELIMINAR, 0); }
		public TerminalNode TAREAS() { return getToken(proyectoParser.TAREAS, 0); }
		public TerminalNode PROGRAMADAS() { return getToken(proyectoParser.PROGRAMADAS, 0); }
		public EliminarTareasProgramadasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eliminarTareasProgramadas; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterEliminarTareasProgramadas(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitEliminarTareasProgramadas(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitEliminarTareasProgramadas(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EliminarTareasProgramadasContext eliminarTareasProgramadas() throws RecognitionException {
		EliminarTareasProgramadasContext _localctx = new EliminarTareasProgramadasContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_eliminarTareasProgramadas);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			match(ELIMINAR);
			setState(248);
			match(TAREAS);
			setState(249);
			match(PROGRAMADAS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EliminarTareaProgramadaContext extends ParserRuleContext {
		public TerminalNode ELIMINAR() { return getToken(proyectoParser.ELIMINAR, 0); }
		public TerminalNode TAREA() { return getToken(proyectoParser.TAREA, 0); }
		public TerminalNode PROGRAMADA() { return getToken(proyectoParser.PROGRAMADA, 0); }
		public TerminalNode ID() { return getToken(proyectoParser.ID, 0); }
		public EliminarTareaProgramadaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eliminarTareaProgramada; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterEliminarTareaProgramada(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitEliminarTareaProgramada(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitEliminarTareaProgramada(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EliminarTareaProgramadaContext eliminarTareaProgramada() throws RecognitionException {
		EliminarTareaProgramadaContext _localctx = new EliminarTareaProgramadaContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_eliminarTareaProgramada);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			match(ELIMINAR);
			setState(252);
			match(TAREA);
			setState(253);
			match(PROGRAMADA);
			setState(254);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CambiarTareaProgramadaContext extends ParserRuleContext {
		public TerminalNode CAMBIAR() { return getToken(proyectoParser.CAMBIAR, 0); }
		public TerminalNode PROGRAMACION() { return getToken(proyectoParser.PROGRAMACION, 0); }
		public TerminalNode DE() { return getToken(proyectoParser.DE, 0); }
		public TerminalNode TAREA() { return getToken(proyectoParser.TAREA, 0); }
		public TerminalNode ID() { return getToken(proyectoParser.ID, 0); }
		public ProgramacionContext programacion() {
			return getRuleContext(ProgramacionContext.class,0);
		}
		public CambiarTareaProgramadaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cambiarTareaProgramada; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterCambiarTareaProgramada(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitCambiarTareaProgramada(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitCambiarTareaProgramada(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CambiarTareaProgramadaContext cambiarTareaProgramada() throws RecognitionException {
		CambiarTareaProgramadaContext _localctx = new CambiarTareaProgramadaContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_cambiarTareaProgramada);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			match(CAMBIAR);
			setState(257);
			match(PROGRAMACION);
			setState(258);
			match(DE);
			setState(259);
			match(TAREA);
			setState(260);
			match(ID);
			setState(261);
			programacion();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramacionContext extends ParserRuleContext {
		public TerminalNode CADA() { return getToken(proyectoParser.CADA, 0); }
		public NumeroContext numero() {
			return getRuleContext(NumeroContext.class,0);
		}
		public TiempoContext tiempo() {
			return getRuleContext(TiempoContext.class,0);
		}
		public TerminalNode AL() { return getToken(proyectoParser.AL, 0); }
		public TerminalNode INICIAR() { return getToken(proyectoParser.INICIAR, 0); }
		public TerminalNode SISTEMA() { return getToken(proyectoParser.SISTEMA, 0); }
		public TerminalNode A() { return getToken(proyectoParser.A, 0); }
		public TerminalNode LAS() { return getToken(proyectoParser.LAS, 0); }
		public CadenaContext cadena() {
			return getRuleContext(CadenaContext.class,0);
		}
		public ProgramacionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programacion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterProgramacion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitProgramacion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitProgramacion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramacionContext programacion() throws RecognitionException {
		ProgramacionContext _localctx = new ProgramacionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_programacion);
		try {
			setState(273);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CADA:
				enterOuterAlt(_localctx, 1);
				{
				setState(263);
				match(CADA);
				setState(264);
				numero();
				setState(265);
				tiempo();
				}
				break;
			case AL:
				enterOuterAlt(_localctx, 2);
				{
				setState(267);
				match(AL);
				setState(268);
				match(INICIAR);
				setState(269);
				match(SISTEMA);
				}
				break;
			case A:
				enterOuterAlt(_localctx, 3);
				{
				setState(270);
				match(A);
				setState(271);
				match(LAS);
				setState(272);
				cadena();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TiempoContext extends ParserRuleContext {
		public TerminalNode HORAS() { return getToken(proyectoParser.HORAS, 0); }
		public TerminalNode MINUTOS() { return getToken(proyectoParser.MINUTOS, 0); }
		public TerminalNode DIAS() { return getToken(proyectoParser.DIAS, 0); }
		public TiempoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tiempo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterTiempo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitTiempo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitTiempo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TiempoContext tiempo() throws RecognitionException {
		TiempoContext _localctx = new TiempoContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_tiempo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			_la = _input.LA(1);
			if ( !(((((_la - 74)) & ~0x3f) == 0 && ((1L << (_la - 74)) & 7L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CondicionContext extends ParserRuleContext {
		public TerminalNode SI() { return getToken(proyectoParser.SI, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode ENTONCES() { return getToken(proyectoParser.ENTONCES, 0); }
		public List<BloqueContext> bloque() {
			return getRuleContexts(BloqueContext.class);
		}
		public BloqueContext bloque(int i) {
			return getRuleContext(BloqueContext.class,i);
		}
		public TerminalNode FINSI() { return getToken(proyectoParser.FINSI, 0); }
		public TerminalNode SINO() { return getToken(proyectoParser.SINO, 0); }
		public CondicionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condicion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterCondicion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitCondicion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitCondicion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CondicionContext condicion() throws RecognitionException {
		CondicionContext _localctx = new CondicionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_condicion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			match(SI);
			setState(278);
			expresion(0);
			setState(279);
			match(ENTONCES);
			setState(280);
			bloque();
			setState(283);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SINO) {
				{
				setState(281);
				match(SINO);
				setState(282);
				bloque();
				}
			}

			setState(285);
			match(FINSI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CicloMientrasContext extends ParserRuleContext {
		public TerminalNode MIENTRAS() { return getToken(proyectoParser.MIENTRAS, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public TerminalNode FINMIENTRAS() { return getToken(proyectoParser.FINMIENTRAS, 0); }
		public CicloMientrasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cicloMientras; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterCicloMientras(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitCicloMientras(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitCicloMientras(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CicloMientrasContext cicloMientras() throws RecognitionException {
		CicloMientrasContext _localctx = new CicloMientrasContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_cicloMientras);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287);
			match(MIENTRAS);
			setState(288);
			expresion(0);
			setState(289);
			bloque();
			setState(290);
			match(FINMIENTRAS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CicloParaCadaContext extends ParserRuleContext {
		public TerminalNode PARA() { return getToken(proyectoParser.PARA, 0); }
		public TerminalNode CADA() { return getToken(proyectoParser.CADA, 0); }
		public TerminalNode ID() { return getToken(proyectoParser.ID, 0); }
		public TerminalNode EN() { return getToken(proyectoParser.EN, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public TerminalNode FINPARA() { return getToken(proyectoParser.FINPARA, 0); }
		public CicloParaCadaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cicloParaCada; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterCicloParaCada(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitCicloParaCada(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitCicloParaCada(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CicloParaCadaContext cicloParaCada() throws RecognitionException {
		CicloParaCadaContext _localctx = new CicloParaCadaContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_cicloParaCada);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292);
			match(PARA);
			setState(293);
			match(CADA);
			setState(294);
			match(ID);
			setState(295);
			match(EN);
			setState(296);
			expresion(0);
			setState(297);
			bloque();
			setState(298);
			match(FINPARA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IntentarCapturarContext extends ParserRuleContext {
		public TerminalNode INTENTAR() { return getToken(proyectoParser.INTENTAR, 0); }
		public List<BloqueContext> bloque() {
			return getRuleContexts(BloqueContext.class);
		}
		public BloqueContext bloque(int i) {
			return getRuleContext(BloqueContext.class,i);
		}
		public TerminalNode CAPTURAR() { return getToken(proyectoParser.CAPTURAR, 0); }
		public TerminalNode FININTENTAR() { return getToken(proyectoParser.FININTENTAR, 0); }
		public IntentarCapturarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intentarCapturar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterIntentarCapturar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitIntentarCapturar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitIntentarCapturar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntentarCapturarContext intentarCapturar() throws RecognitionException {
		IntentarCapturarContext _localctx = new IntentarCapturarContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_intentarCapturar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(300);
			match(INTENTAR);
			setState(301);
			bloque();
			setState(302);
			match(CAPTURAR);
			setState(303);
			bloque();
			setState(304);
			match(FININTENTAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BloqueContext extends ParserRuleContext {
		public List<SentenciaContext> sentencia() {
			return getRuleContexts(SentenciaContext.class);
		}
		public SentenciaContext sentencia(int i) {
			return getRuleContext(SentenciaContext.class,i);
		}
		public BloqueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloque; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterBloque(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitBloque(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitBloque(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BloqueContext bloque() throws RecognitionException {
		BloqueContext _localctx = new BloqueContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_bloque);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1689949314507094L) != 0) || ((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & 518114064385L) != 0)) {
				{
				{
				setState(306);
				sentencia();
				}
				}
				setState(311);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SimulacionContext extends ParserRuleContext {
		public TerminalNode SIMULAR() { return getToken(proyectoParser.SIMULAR, 0); }
		public AccionArchivoContext accionArchivo() {
			return getRuleContext(AccionArchivoContext.class,0);
		}
		public ComandoSistemaContext comandoSistema() {
			return getRuleContext(ComandoSistemaContext.class,0);
		}
		public SimulacionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simulacion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterSimulacion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitSimulacion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitSimulacion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimulacionContext simulacion() throws RecognitionException {
		SimulacionContext _localctx = new SimulacionContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_simulacion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			match(SIMULAR);
			setState(315);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CREAR:
			case LEER:
			case ESCRIBIR:
			case ANEXAR:
			case BUSCAR:
			case LISTAR:
			case ELIMINAR:
			case MOVER:
			case COPIAR:
			case RENOMBRAR:
			case OBTENER:
			case EXISTE:
			case CAMBIAR:
			case COMPRIMIR:
			case DESCOMPRIMIR:
				{
				setState(313);
				accionArchivo();
				}
				break;
			case EJECUTAR:
				{
				setState(314);
				comandoSistema();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComandoSistemaContext extends ParserRuleContext {
		public EjecutarPowerShellContext ejecutarPowerShell() {
			return getRuleContext(EjecutarPowerShellContext.class,0);
		}
		public EjecutarLinuxContext ejecutarLinux() {
			return getRuleContext(EjecutarLinuxContext.class,0);
		}
		public ComandoSistemaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandoSistema; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterComandoSistema(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitComandoSistema(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitComandoSistema(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComandoSistemaContext comandoSistema() throws RecognitionException {
		ComandoSistemaContext _localctx = new ComandoSistemaContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_comandoSistema);
		try {
			setState(319);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(317);
				ejecutarPowerShell();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(318);
				ejecutarLinux();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EjecutarPowerShellContext extends ParserRuleContext {
		public TerminalNode EJECUTAR() { return getToken(proyectoParser.EJECUTAR, 0); }
		public TerminalNode POWERSHELL() { return getToken(proyectoParser.POWERSHELL, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public EjecutarPowerShellContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ejecutarPowerShell; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterEjecutarPowerShell(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitEjecutarPowerShell(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitEjecutarPowerShell(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EjecutarPowerShellContext ejecutarPowerShell() throws RecognitionException {
		EjecutarPowerShellContext _localctx = new EjecutarPowerShellContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_ejecutarPowerShell);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(321);
			match(EJECUTAR);
			setState(322);
			match(POWERSHELL);
			setState(323);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EjecutarLinuxContext extends ParserRuleContext {
		public TerminalNode EJECUTAR() { return getToken(proyectoParser.EJECUTAR, 0); }
		public TerminalNode LINUX() { return getToken(proyectoParser.LINUX, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public EjecutarLinuxContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ejecutarLinux; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterEjecutarLinux(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitEjecutarLinux(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitEjecutarLinux(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EjecutarLinuxContext ejecutarLinux() throws RecognitionException {
		EjecutarLinuxContext _localctx = new EjecutarLinuxContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_ejecutarLinux);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			match(EJECUTAR);
			setState(326);
			match(LINUX);
			setState(327);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AccionArchivoContext extends ParserRuleContext {
		public CrearArchivoContext crearArchivo() {
			return getRuleContext(CrearArchivoContext.class,0);
		}
		public CrearCarpetaContext crearCarpeta() {
			return getRuleContext(CrearCarpetaContext.class,0);
		}
		public EliminarArchivoContext eliminarArchivo() {
			return getRuleContext(EliminarArchivoContext.class,0);
		}
		public EliminarCarpetaContext eliminarCarpeta() {
			return getRuleContext(EliminarCarpetaContext.class,0);
		}
		public CopiarArchivoContext copiarArchivo() {
			return getRuleContext(CopiarArchivoContext.class,0);
		}
		public CopiarCarpetaContext copiarCarpeta() {
			return getRuleContext(CopiarCarpetaContext.class,0);
		}
		public MoverArchivoContext moverArchivo() {
			return getRuleContext(MoverArchivoContext.class,0);
		}
		public MoverCarpetaContext moverCarpeta() {
			return getRuleContext(MoverCarpetaContext.class,0);
		}
		public RenombrarArchivoContext renombrarArchivo() {
			return getRuleContext(RenombrarArchivoContext.class,0);
		}
		public RenombrarCarpetaContext renombrarCarpeta() {
			return getRuleContext(RenombrarCarpetaContext.class,0);
		}
		public LeerArchivoContext leerArchivo() {
			return getRuleContext(LeerArchivoContext.class,0);
		}
		public EscribirArchivoContext escribirArchivo() {
			return getRuleContext(EscribirArchivoContext.class,0);
		}
		public AnexarArchivoContext anexarArchivo() {
			return getRuleContext(AnexarArchivoContext.class,0);
		}
		public BuscarArchivosContext buscarArchivos() {
			return getRuleContext(BuscarArchivosContext.class,0);
		}
		public BuscarCarpetasContext buscarCarpetas() {
			return getRuleContext(BuscarCarpetasContext.class,0);
		}
		public ListarContenidoContext listarContenido() {
			return getRuleContext(ListarContenidoContext.class,0);
		}
		public ExisteArchivoContext existeArchivo() {
			return getRuleContext(ExisteArchivoContext.class,0);
		}
		public ExisteCarpetaContext existeCarpeta() {
			return getRuleContext(ExisteCarpetaContext.class,0);
		}
		public ObtenerTamanoContext obtenerTamano() {
			return getRuleContext(ObtenerTamanoContext.class,0);
		}
		public ObtenerCantidadArchivosContext obtenerCantidadArchivos() {
			return getRuleContext(ObtenerCantidadArchivosContext.class,0);
		}
		public ObtenerPropiedadContext obtenerPropiedad() {
			return getRuleContext(ObtenerPropiedadContext.class,0);
		}
		public ComprimirContext comprimir() {
			return getRuleContext(ComprimirContext.class,0);
		}
		public DescomprimirContext descomprimir() {
			return getRuleContext(DescomprimirContext.class,0);
		}
		public CambiarPermisosContext cambiarPermisos() {
			return getRuleContext(CambiarPermisosContext.class,0);
		}
		public CrearBackupContext crearBackup() {
			return getRuleContext(CrearBackupContext.class,0);
		}
		public AccionArchivoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accionArchivo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterAccionArchivo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitAccionArchivo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitAccionArchivo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AccionArchivoContext accionArchivo() throws RecognitionException {
		AccionArchivoContext _localctx = new AccionArchivoContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_accionArchivo);
		try {
			setState(354);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(329);
				crearArchivo();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(330);
				crearCarpeta();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(331);
				eliminarArchivo();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(332);
				eliminarCarpeta();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(333);
				copiarArchivo();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(334);
				copiarCarpeta();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(335);
				moverArchivo();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(336);
				moverCarpeta();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(337);
				renombrarArchivo();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(338);
				renombrarCarpeta();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(339);
				leerArchivo();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(340);
				escribirArchivo();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(341);
				anexarArchivo();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(342);
				buscarArchivos();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(343);
				buscarCarpetas();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(344);
				listarContenido();
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(345);
				existeArchivo();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(346);
				existeCarpeta();
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(347);
				obtenerTamano();
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(348);
				obtenerCantidadArchivos();
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(349);
				obtenerPropiedad();
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(350);
				comprimir();
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(351);
				descomprimir();
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 24);
				{
				setState(352);
				cambiarPermisos();
				}
				break;
			case 25:
				enterOuterAlt(_localctx, 25);
				{
				setState(353);
				crearBackup();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CrearArchivoContext extends ParserRuleContext {
		public TerminalNode CREAR() { return getToken(proyectoParser.CREAR, 0); }
		public TerminalNode ARCHIVO() { return getToken(proyectoParser.ARCHIVO, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public CrearArchivoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_crearArchivo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterCrearArchivo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitCrearArchivo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitCrearArchivo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CrearArchivoContext crearArchivo() throws RecognitionException {
		CrearArchivoContext _localctx = new CrearArchivoContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_crearArchivo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(356);
			match(CREAR);
			setState(357);
			match(ARCHIVO);
			setState(358);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CrearCarpetaContext extends ParserRuleContext {
		public TerminalNode CREAR() { return getToken(proyectoParser.CREAR, 0); }
		public TerminalNode CARPETA() { return getToken(proyectoParser.CARPETA, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public CrearCarpetaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_crearCarpeta; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterCrearCarpeta(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitCrearCarpeta(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitCrearCarpeta(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CrearCarpetaContext crearCarpeta() throws RecognitionException {
		CrearCarpetaContext _localctx = new CrearCarpetaContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_crearCarpeta);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(360);
			match(CREAR);
			setState(361);
			match(CARPETA);
			setState(362);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LeerArchivoContext extends ParserRuleContext {
		public TerminalNode LEER() { return getToken(proyectoParser.LEER, 0); }
		public TerminalNode ARCHIVO() { return getToken(proyectoParser.ARCHIVO, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public LeerArchivoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leerArchivo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterLeerArchivo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitLeerArchivo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitLeerArchivo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LeerArchivoContext leerArchivo() throws RecognitionException {
		LeerArchivoContext _localctx = new LeerArchivoContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_leerArchivo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(364);
			match(LEER);
			setState(365);
			match(ARCHIVO);
			setState(366);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BuscarArchivosContext extends ParserRuleContext {
		public TerminalNode BUSCAR() { return getToken(proyectoParser.BUSCAR, 0); }
		public TerminalNode ARCHIVOS() { return getToken(proyectoParser.ARCHIVOS, 0); }
		public TerminalNode EN() { return getToken(proyectoParser.EN, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public FiltrosContext filtros() {
			return getRuleContext(FiltrosContext.class,0);
		}
		public BuscarArchivosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_buscarArchivos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterBuscarArchivos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitBuscarArchivos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitBuscarArchivos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BuscarArchivosContext buscarArchivos() throws RecognitionException {
		BuscarArchivosContext _localctx = new BuscarArchivosContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_buscarArchivos);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368);
			match(BUSCAR);
			setState(369);
			match(ARCHIVOS);
			setState(370);
			match(EN);
			setState(371);
			expresion(0);
			setState(373);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CON || _la==MAS) {
				{
				setState(372);
				filtros();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BuscarCarpetasContext extends ParserRuleContext {
		public TerminalNode BUSCAR() { return getToken(proyectoParser.BUSCAR, 0); }
		public TerminalNode CARPETAS() { return getToken(proyectoParser.CARPETAS, 0); }
		public TerminalNode EN() { return getToken(proyectoParser.EN, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public FiltrosContext filtros() {
			return getRuleContext(FiltrosContext.class,0);
		}
		public BuscarCarpetasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_buscarCarpetas; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterBuscarCarpetas(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitBuscarCarpetas(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitBuscarCarpetas(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BuscarCarpetasContext buscarCarpetas() throws RecognitionException {
		BuscarCarpetasContext _localctx = new BuscarCarpetasContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_buscarCarpetas);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(375);
			match(BUSCAR);
			setState(376);
			match(CARPETAS);
			setState(377);
			match(EN);
			setState(378);
			expresion(0);
			setState(380);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CON || _la==MAS) {
				{
				setState(379);
				filtros();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListarContenidoContext extends ParserRuleContext {
		public TerminalNode LISTAR() { return getToken(proyectoParser.LISTAR, 0); }
		public TerminalNode CONTENIDO() { return getToken(proyectoParser.CONTENIDO, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public ListarContenidoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listarContenido; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterListarContenido(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitListarContenido(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitListarContenido(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListarContenidoContext listarContenido() throws RecognitionException {
		ListarContenidoContext _localctx = new ListarContenidoContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_listarContenido);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(382);
			match(LISTAR);
			setState(383);
			match(CONTENIDO);
			setState(385);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(384);
				expresion(0);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExisteArchivoContext extends ParserRuleContext {
		public TerminalNode EXISTE() { return getToken(proyectoParser.EXISTE, 0); }
		public TerminalNode ARCHIVO() { return getToken(proyectoParser.ARCHIVO, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public ExisteArchivoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_existeArchivo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterExisteArchivo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitExisteArchivo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitExisteArchivo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExisteArchivoContext existeArchivo() throws RecognitionException {
		ExisteArchivoContext _localctx = new ExisteArchivoContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_existeArchivo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(387);
			match(EXISTE);
			setState(388);
			match(ARCHIVO);
			setState(389);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExisteCarpetaContext extends ParserRuleContext {
		public TerminalNode EXISTE() { return getToken(proyectoParser.EXISTE, 0); }
		public TerminalNode CARPETA() { return getToken(proyectoParser.CARPETA, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public ExisteCarpetaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_existeCarpeta; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterExisteCarpeta(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitExisteCarpeta(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitExisteCarpeta(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExisteCarpetaContext existeCarpeta() throws RecognitionException {
		ExisteCarpetaContext _localctx = new ExisteCarpetaContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_existeCarpeta);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(391);
			match(EXISTE);
			setState(392);
			match(CARPETA);
			setState(393);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ObtenerTamanoContext extends ParserRuleContext {
		public TerminalNode OBTENER() { return getToken(proyectoParser.OBTENER, 0); }
		public TerminalNode TAMANO() { return getToken(proyectoParser.TAMANO, 0); }
		public TerminalNode ARCHIVO() { return getToken(proyectoParser.ARCHIVO, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode CARPETA() { return getToken(proyectoParser.CARPETA, 0); }
		public ObtenerTamanoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_obtenerTamano; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterObtenerTamano(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitObtenerTamano(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitObtenerTamano(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObtenerTamanoContext obtenerTamano() throws RecognitionException {
		ObtenerTamanoContext _localctx = new ObtenerTamanoContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_obtenerTamano);
		try {
			setState(403);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(395);
				match(OBTENER);
				setState(396);
				match(TAMANO);
				setState(397);
				match(ARCHIVO);
				setState(398);
				expresion(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(399);
				match(OBTENER);
				setState(400);
				match(TAMANO);
				setState(401);
				match(CARPETA);
				setState(402);
				expresion(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ObtenerCantidadArchivosContext extends ParserRuleContext {
		public TerminalNode OBTENER() { return getToken(proyectoParser.OBTENER, 0); }
		public TerminalNode CANTIDAD() { return getToken(proyectoParser.CANTIDAD, 0); }
		public TerminalNode ARCHIVOS() { return getToken(proyectoParser.ARCHIVOS, 0); }
		public TerminalNode EN() { return getToken(proyectoParser.EN, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public ObtenerCantidadArchivosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_obtenerCantidadArchivos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterObtenerCantidadArchivos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitObtenerCantidadArchivos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitObtenerCantidadArchivos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObtenerCantidadArchivosContext obtenerCantidadArchivos() throws RecognitionException {
		ObtenerCantidadArchivosContext _localctx = new ObtenerCantidadArchivosContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_obtenerCantidadArchivos);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(405);
			match(OBTENER);
			setState(406);
			match(CANTIDAD);
			setState(407);
			match(ARCHIVOS);
			setState(408);
			match(EN);
			setState(409);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ObtenerPropiedadContext extends ParserRuleContext {
		public TerminalNode OBTENER() { return getToken(proyectoParser.OBTENER, 0); }
		public TerminalNode PROPIEDAD() { return getToken(proyectoParser.PROPIEDAD, 0); }
		public TerminalNode ID() { return getToken(proyectoParser.ID, 0); }
		public TerminalNode DE() { return getToken(proyectoParser.DE, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public ObtenerPropiedadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_obtenerPropiedad; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterObtenerPropiedad(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitObtenerPropiedad(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitObtenerPropiedad(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObtenerPropiedadContext obtenerPropiedad() throws RecognitionException {
		ObtenerPropiedadContext _localctx = new ObtenerPropiedadContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_obtenerPropiedad);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(411);
			match(OBTENER);
			setState(412);
			match(PROPIEDAD);
			setState(413);
			match(ID);
			setState(414);
			match(DE);
			setState(415);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CopiarArchivoContext extends ParserRuleContext {
		public TerminalNode COPIAR() { return getToken(proyectoParser.COPIAR, 0); }
		public TerminalNode ARCHIVO() { return getToken(proyectoParser.ARCHIVO, 0); }
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode A() { return getToken(proyectoParser.A, 0); }
		public CopiarArchivoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_copiarArchivo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterCopiarArchivo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitCopiarArchivo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitCopiarArchivo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CopiarArchivoContext copiarArchivo() throws RecognitionException {
		CopiarArchivoContext _localctx = new CopiarArchivoContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_copiarArchivo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(417);
			match(COPIAR);
			setState(418);
			match(ARCHIVO);
			setState(419);
			expresion(0);
			setState(420);
			match(A);
			setState(421);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CopiarCarpetaContext extends ParserRuleContext {
		public TerminalNode COPIAR() { return getToken(proyectoParser.COPIAR, 0); }
		public TerminalNode CARPETA() { return getToken(proyectoParser.CARPETA, 0); }
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode A() { return getToken(proyectoParser.A, 0); }
		public CopiarCarpetaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_copiarCarpeta; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterCopiarCarpeta(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitCopiarCarpeta(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitCopiarCarpeta(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CopiarCarpetaContext copiarCarpeta() throws RecognitionException {
		CopiarCarpetaContext _localctx = new CopiarCarpetaContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_copiarCarpeta);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(423);
			match(COPIAR);
			setState(424);
			match(CARPETA);
			setState(425);
			expresion(0);
			setState(426);
			match(A);
			setState(427);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MoverArchivoContext extends ParserRuleContext {
		public TerminalNode MOVER() { return getToken(proyectoParser.MOVER, 0); }
		public TerminalNode ARCHIVO() { return getToken(proyectoParser.ARCHIVO, 0); }
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode A() { return getToken(proyectoParser.A, 0); }
		public MoverArchivoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_moverArchivo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterMoverArchivo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitMoverArchivo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitMoverArchivo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MoverArchivoContext moverArchivo() throws RecognitionException {
		MoverArchivoContext _localctx = new MoverArchivoContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_moverArchivo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(429);
			match(MOVER);
			setState(430);
			match(ARCHIVO);
			setState(431);
			expresion(0);
			setState(432);
			match(A);
			setState(433);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MoverCarpetaContext extends ParserRuleContext {
		public TerminalNode MOVER() { return getToken(proyectoParser.MOVER, 0); }
		public TerminalNode CARPETA() { return getToken(proyectoParser.CARPETA, 0); }
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode A() { return getToken(proyectoParser.A, 0); }
		public MoverCarpetaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_moverCarpeta; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterMoverCarpeta(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitMoverCarpeta(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitMoverCarpeta(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MoverCarpetaContext moverCarpeta() throws RecognitionException {
		MoverCarpetaContext _localctx = new MoverCarpetaContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_moverCarpeta);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(435);
			match(MOVER);
			setState(436);
			match(CARPETA);
			setState(437);
			expresion(0);
			setState(438);
			match(A);
			setState(439);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RenombrarArchivoContext extends ParserRuleContext {
		public TerminalNode RENOMBRAR() { return getToken(proyectoParser.RENOMBRAR, 0); }
		public TerminalNode ARCHIVO() { return getToken(proyectoParser.ARCHIVO, 0); }
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode A() { return getToken(proyectoParser.A, 0); }
		public RenombrarArchivoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_renombrarArchivo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterRenombrarArchivo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitRenombrarArchivo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitRenombrarArchivo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RenombrarArchivoContext renombrarArchivo() throws RecognitionException {
		RenombrarArchivoContext _localctx = new RenombrarArchivoContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_renombrarArchivo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(441);
			match(RENOMBRAR);
			setState(442);
			match(ARCHIVO);
			setState(443);
			expresion(0);
			setState(444);
			match(A);
			setState(445);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RenombrarCarpetaContext extends ParserRuleContext {
		public TerminalNode RENOMBRAR() { return getToken(proyectoParser.RENOMBRAR, 0); }
		public TerminalNode CARPETA() { return getToken(proyectoParser.CARPETA, 0); }
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode A() { return getToken(proyectoParser.A, 0); }
		public RenombrarCarpetaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_renombrarCarpeta; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterRenombrarCarpeta(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitRenombrarCarpeta(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitRenombrarCarpeta(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RenombrarCarpetaContext renombrarCarpeta() throws RecognitionException {
		RenombrarCarpetaContext _localctx = new RenombrarCarpetaContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_renombrarCarpeta);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(447);
			match(RENOMBRAR);
			setState(448);
			match(CARPETA);
			setState(449);
			expresion(0);
			setState(450);
			match(A);
			setState(451);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EscribirArchivoContext extends ParserRuleContext {
		public TerminalNode ESCRIBIR() { return getToken(proyectoParser.ESCRIBIR, 0); }
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode EN() { return getToken(proyectoParser.EN, 0); }
		public EscribirArchivoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_escribirArchivo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterEscribirArchivo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitEscribirArchivo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitEscribirArchivo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EscribirArchivoContext escribirArchivo() throws RecognitionException {
		EscribirArchivoContext _localctx = new EscribirArchivoContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_escribirArchivo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(453);
			match(ESCRIBIR);
			setState(454);
			expresion(0);
			setState(455);
			match(EN);
			setState(456);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnexarArchivoContext extends ParserRuleContext {
		public TerminalNode ANEXAR() { return getToken(proyectoParser.ANEXAR, 0); }
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode EN() { return getToken(proyectoParser.EN, 0); }
		public AnexarArchivoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anexarArchivo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterAnexarArchivo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitAnexarArchivo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitAnexarArchivo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnexarArchivoContext anexarArchivo() throws RecognitionException {
		AnexarArchivoContext _localctx = new AnexarArchivoContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_anexarArchivo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(458);
			match(ANEXAR);
			setState(459);
			expresion(0);
			setState(460);
			match(EN);
			setState(461);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CambiarPermisosContext extends ParserRuleContext {
		public TerminalNode CAMBIAR() { return getToken(proyectoParser.CAMBIAR, 0); }
		public TerminalNode PERMISOS() { return getToken(proyectoParser.PERMISOS, 0); }
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode A() { return getToken(proyectoParser.A, 0); }
		public CambiarPermisosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cambiarPermisos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterCambiarPermisos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitCambiarPermisos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitCambiarPermisos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CambiarPermisosContext cambiarPermisos() throws RecognitionException {
		CambiarPermisosContext _localctx = new CambiarPermisosContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_cambiarPermisos);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(463);
			match(CAMBIAR);
			setState(464);
			match(PERMISOS);
			setState(465);
			expresion(0);
			setState(466);
			match(A);
			setState(467);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EliminarArchivoContext extends ParserRuleContext {
		public TerminalNode ELIMINAR() { return getToken(proyectoParser.ELIMINAR, 0); }
		public TerminalNode ARCHIVO() { return getToken(proyectoParser.ARCHIVO, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public SinConfirmarContext sinConfirmar() {
			return getRuleContext(SinConfirmarContext.class,0);
		}
		public EliminarArchivoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eliminarArchivo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterEliminarArchivo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitEliminarArchivo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitEliminarArchivo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EliminarArchivoContext eliminarArchivo() throws RecognitionException {
		EliminarArchivoContext _localctx = new EliminarArchivoContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_eliminarArchivo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(469);
			match(ELIMINAR);
			setState(470);
			match(ARCHIVO);
			setState(471);
			expresion(0);
			setState(473);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SIN) {
				{
				setState(472);
				sinConfirmar();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EliminarCarpetaContext extends ParserRuleContext {
		public TerminalNode ELIMINAR() { return getToken(proyectoParser.ELIMINAR, 0); }
		public TerminalNode CARPETA() { return getToken(proyectoParser.CARPETA, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode RECURSIVAMENTE() { return getToken(proyectoParser.RECURSIVAMENTE, 0); }
		public SinConfirmarContext sinConfirmar() {
			return getRuleContext(SinConfirmarContext.class,0);
		}
		public EliminarCarpetaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eliminarCarpeta; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterEliminarCarpeta(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitEliminarCarpeta(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitEliminarCarpeta(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EliminarCarpetaContext eliminarCarpeta() throws RecognitionException {
		EliminarCarpetaContext _localctx = new EliminarCarpetaContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_eliminarCarpeta);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(475);
			match(ELIMINAR);
			setState(476);
			match(CARPETA);
			setState(477);
			expresion(0);
			setState(479);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RECURSIVAMENTE) {
				{
				setState(478);
				match(RECURSIVAMENTE);
				}
			}

			setState(482);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SIN) {
				{
				setState(481);
				sinConfirmar();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SinConfirmarContext extends ParserRuleContext {
		public TerminalNode SIN() { return getToken(proyectoParser.SIN, 0); }
		public TerminalNode CONFIRMAR() { return getToken(proyectoParser.CONFIRMAR, 0); }
		public SinConfirmarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sinConfirmar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterSinConfirmar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitSinConfirmar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitSinConfirmar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SinConfirmarContext sinConfirmar() throws RecognitionException {
		SinConfirmarContext _localctx = new SinConfirmarContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_sinConfirmar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(484);
			match(SIN);
			setState(485);
			match(CONFIRMAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComprimirContext extends ParserRuleContext {
		public TerminalNode COMPRIMIR() { return getToken(proyectoParser.COMPRIMIR, 0); }
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode A() { return getToken(proyectoParser.A, 0); }
		public ComprimirContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comprimir; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterComprimir(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitComprimir(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitComprimir(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComprimirContext comprimir() throws RecognitionException {
		ComprimirContext _localctx = new ComprimirContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_comprimir);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(487);
			match(COMPRIMIR);
			setState(488);
			expresion(0);
			setState(489);
			match(A);
			setState(490);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DescomprimirContext extends ParserRuleContext {
		public TerminalNode DESCOMPRIMIR() { return getToken(proyectoParser.DESCOMPRIMIR, 0); }
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode EN() { return getToken(proyectoParser.EN, 0); }
		public DescomprimirContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_descomprimir; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterDescomprimir(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitDescomprimir(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitDescomprimir(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DescomprimirContext descomprimir() throws RecognitionException {
		DescomprimirContext _localctx = new DescomprimirContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_descomprimir);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(492);
			match(DESCOMPRIMIR);
			setState(493);
			expresion(0);
			setState(494);
			match(EN);
			setState(495);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CrearBackupContext extends ParserRuleContext {
		public TerminalNode CREAR() { return getToken(proyectoParser.CREAR, 0); }
		public TerminalNode BACKUP() { return getToken(proyectoParser.BACKUP, 0); }
		public TerminalNode DE() { return getToken(proyectoParser.DE, 0); }
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode EN() { return getToken(proyectoParser.EN, 0); }
		public CrearBackupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_crearBackup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterCrearBackup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitCrearBackup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitCrearBackup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CrearBackupContext crearBackup() throws RecognitionException {
		CrearBackupContext _localctx = new CrearBackupContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_crearBackup);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(497);
			match(CREAR);
			setState(498);
			match(BACKUP);
			setState(499);
			match(DE);
			setState(500);
			expresion(0);
			setState(501);
			match(EN);
			setState(502);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FiltrosContext extends ParserRuleContext {
		public List<FiltroContext> filtro() {
			return getRuleContexts(FiltroContext.class);
		}
		public FiltroContext filtro(int i) {
			return getRuleContext(FiltroContext.class,i);
		}
		public FiltrosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filtros; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterFiltros(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitFiltros(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitFiltros(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FiltrosContext filtros() throws RecognitionException {
		FiltrosContext _localctx = new FiltrosContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_filtros);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(505);
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(504);
				filtro();
				}
				}
				setState(507);
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CON || _la==MAS );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FiltroContext extends ParserRuleContext {
		public TerminalNode CON() { return getToken(proyectoParser.CON, 0); }
		public TerminalNode EXTENSION() { return getToken(proyectoParser.EXTENSION, 0); }
		public CadenaContext cadena() {
			return getRuleContext(CadenaContext.class,0);
		}
		public TerminalNode NOMBRE() { return getToken(proyectoParser.NOMBRE, 0); }
		public TerminalNode PREFIJO() { return getToken(proyectoParser.PREFIJO, 0); }
		public TerminalNode SUFIJO() { return getToken(proyectoParser.SUFIJO, 0); }
		public TerminalNode TAMANO() { return getToken(proyectoParser.TAMANO, 0); }
		public TerminalNode MAYOR() { return getToken(proyectoParser.MAYOR, 0); }
		public TerminalNode QUE() { return getToken(proyectoParser.QUE, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public UnidadTamanoContext unidadTamano() {
			return getRuleContext(UnidadTamanoContext.class,0);
		}
		public TerminalNode MENOR() { return getToken(proyectoParser.MENOR, 0); }
		public TerminalNode MAS() { return getToken(proyectoParser.MAS, 0); }
		public TerminalNode ANTIGUOS() { return getToken(proyectoParser.ANTIGUOS, 0); }
		public TerminalNode DIAS() { return getToken(proyectoParser.DIAS, 0); }
		public TerminalNode RECIENTES() { return getToken(proyectoParser.RECIENTES, 0); }
		public FiltroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filtro; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterFiltro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitFiltro(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitFiltro(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FiltroContext filtro() throws RecognitionException {
		FiltroContext _localctx = new FiltroContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_filtro);
		try {
			setState(547);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(509);
				match(CON);
				setState(510);
				match(EXTENSION);
				setState(511);
				cadena();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(512);
				match(CON);
				setState(513);
				match(NOMBRE);
				setState(514);
				cadena();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(515);
				match(CON);
				setState(516);
				match(PREFIJO);
				setState(517);
				cadena();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(518);
				match(CON);
				setState(519);
				match(SUFIJO);
				setState(520);
				cadena();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(521);
				match(CON);
				setState(522);
				match(TAMANO);
				setState(523);
				match(MAYOR);
				setState(524);
				match(QUE);
				setState(525);
				expresion(0);
				setState(526);
				unidadTamano();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(528);
				match(CON);
				setState(529);
				match(TAMANO);
				setState(530);
				match(MENOR);
				setState(531);
				match(QUE);
				setState(532);
				expresion(0);
				setState(533);
				unidadTamano();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(535);
				match(MAS);
				setState(536);
				match(ANTIGUOS);
				setState(537);
				match(QUE);
				setState(538);
				expresion(0);
				setState(539);
				match(DIAS);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(541);
				match(MAS);
				setState(542);
				match(RECIENTES);
				setState(543);
				match(QUE);
				setState(544);
				expresion(0);
				setState(545);
				match(DIAS);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnidadTamanoContext extends ParserRuleContext {
		public TerminalNode BYTES() { return getToken(proyectoParser.BYTES, 0); }
		public TerminalNode KB() { return getToken(proyectoParser.KB, 0); }
		public TerminalNode MB() { return getToken(proyectoParser.MB, 0); }
		public TerminalNode GB() { return getToken(proyectoParser.GB, 0); }
		public UnidadTamanoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unidadTamano; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterUnidadTamano(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitUnidadTamano(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitUnidadTamano(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnidadTamanoContext unidadTamano() throws RecognitionException {
		UnidadTamanoContext _localctx = new UnidadTamanoContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_unidadTamano);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(549);
			_la = _input.LA(1);
			if ( !(((((_la - 77)) & ~0x3f) == 0 && ((1L << (_la - 77)) & 15L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MostrarContext extends ParserRuleContext {
		public TerminalNode MOSTRAR() { return getToken(proyectoParser.MOSTRAR, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public MostrarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mostrar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterMostrar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitMostrar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitMostrar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MostrarContext mostrar() throws RecognitionException {
		MostrarContext _localctx = new MostrarContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_mostrar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(551);
			match(MOSTRAR);
			setState(552);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpresionContext extends ParserRuleContext {
		public ExpresionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expresion; }

		public ExpresionContext() { }
		public void copyFrom(ExpresionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumeroExprContext extends ExpresionContext {
		public NumeroContext numero() {
			return getRuleContext(NumeroContext.class,0);
		}
		public NumeroExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterNumeroExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitNumeroExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitNumeroExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RestaExprContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode MENOS() { return getToken(proyectoParser.MENOS, 0); }
		public RestaExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterRestaExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitRestaExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitRestaExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ModExprContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode MOD() { return getToken(proyectoParser.MOD, 0); }
		public ModExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterModExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitModExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitModExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MayorExprContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode MAYOR_QUE() { return getToken(proyectoParser.MAYOR_QUE, 0); }
		public MayorExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterMayorExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitMayorExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitMayorExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ListaExprContext extends ExpresionContext {
		public ListaContext lista() {
			return getRuleContext(ListaContext.class,0);
		}
		public ListaExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterListaExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitListaExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitListaExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OrExprContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode OR() { return getToken(proyectoParser.OR, 0); }
		public OrExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitOrExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FuncionExprContext extends ExpresionContext {
		public LlamadaFuncionContext llamadaFuncion() {
			return getRuleContext(LlamadaFuncionContext.class,0);
		}
		public FuncionExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterFuncionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitFuncionExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitFuncionExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CadenaExprContext extends ExpresionContext {
		public CadenaContext cadena() {
			return getRuleContext(CadenaContext.class,0);
		}
		public CadenaExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterCadenaExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitCadenaExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitCadenaExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IgualExprContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode IGUAL_IGUAL() { return getToken(proyectoParser.IGUAL_IGUAL, 0); }
		public IgualExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterIgualExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitIgualExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitIgualExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MultExprContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode MULT() { return getToken(proyectoParser.MULT, 0); }
		public MultExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterMultExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitMultExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitMultExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenExprContext extends ExpresionContext {
		public TerminalNode PAR_IZQ() { return getToken(proyectoParser.PAR_IZQ, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PAR_DER() { return getToken(proyectoParser.PAR_DER, 0); }
		public ParenExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterParenExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitParenExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitParenExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VariableExprContext extends ExpresionContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public VariableExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterVariableExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitVariableExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitVariableExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotExprContext extends ExpresionContext {
		public TerminalNode NOT() { return getToken(proyectoParser.NOT, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public NotExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterNotExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitNotExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitNotExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SumaExprContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode MAS() { return getToken(proyectoParser.MAS, 0); }
		public SumaExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterSumaExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitSumaExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitSumaExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NegExprContext extends ExpresionContext {
		public TerminalNode MENOS() { return getToken(proyectoParser.MENOS, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public NegExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterNegExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitNegExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitNegExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MenorExprContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode MENOR_QUE() { return getToken(proyectoParser.MENOR_QUE, 0); }
		public MenorExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterMenorExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitMenorExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitMenorExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MayorIgualExprContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode MAYOR_IGUAL() { return getToken(proyectoParser.MAYOR_IGUAL, 0); }
		public MayorIgualExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterMayorIgualExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitMayorIgualExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitMayorIgualExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DiferenteExprContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode DIFERENTE() { return getToken(proyectoParser.DIFERENTE, 0); }
		public DiferenteExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterDiferenteExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitDiferenteExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitDiferenteExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DivExprContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode DIV() { return getToken(proyectoParser.DIV, 0); }
		public DivExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterDivExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitDivExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitDivExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BooleanoExprContext extends ExpresionContext {
		public BooleanoContext booleano() {
			return getRuleContext(BooleanoContext.class,0);
		}
		public BooleanoExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterBooleanoExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitBooleanoExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitBooleanoExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MenorIgualExprContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode MENOR_IGUAL() { return getToken(proyectoParser.MENOR_IGUAL, 0); }
		public MenorIgualExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterMenorIgualExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitMenorIgualExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitMenorIgualExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AndExprContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode AND() { return getToken(proyectoParser.AND, 0); }
		public AndExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitAndExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpresionContext expresion() throws RecognitionException {
		return expresion(0);
	}

	private ExpresionContext expresion(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpresionContext _localctx = new ExpresionContext(_ctx, _parentState);
		ExpresionContext _prevctx = _localctx;
		int _startState = 122;
		enterRecursionRule(_localctx, 122, RULE_expresion, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(569);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				_localctx = new NotExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(555);
				match(NOT);
				setState(556);
				expresion(20);
				}
				break;
			case 2:
				{
				_localctx = new NegExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(557);
				match(MENOS);
				setState(558);
				expresion(13);
				}
				break;
			case 3:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(559);
				match(PAR_IZQ);
				setState(560);
				expresion(0);
				setState(561);
				match(PAR_DER);
				}
				break;
			case 4:
				{
				_localctx = new FuncionExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(563);
				llamadaFuncion();
				}
				break;
			case 5:
				{
				_localctx = new ListaExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(564);
				lista();
				}
				break;
			case 6:
				{
				_localctx = new NumeroExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(565);
				numero();
				}
				break;
			case 7:
				{
				_localctx = new CadenaExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(566);
				cadena();
				}
				break;
			case 8:
				{
				_localctx = new BooleanoExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(567);
				booleano();
				}
				break;
			case 9:
				{
				_localctx = new VariableExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(568);
				variable();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(612);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(610);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
					case 1:
						{
						_localctx = new OrExprContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(571);
						if (!(precpred(_ctx, 22))) throw new FailedPredicateException(this, "precpred(_ctx, 22)");
						setState(572);
						match(OR);
						setState(573);
						expresion(23);
						}
						break;
					case 2:
						{
						_localctx = new AndExprContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(574);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(575);
						match(AND);
						setState(576);
						expresion(22);
						}
						break;
					case 3:
						{
						_localctx = new MayorExprContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(577);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(578);
						match(MAYOR_QUE);
						setState(579);
						expresion(20);
						}
						break;
					case 4:
						{
						_localctx = new MenorExprContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(580);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(581);
						match(MENOR_QUE);
						setState(582);
						expresion(19);
						}
						break;
					case 5:
						{
						_localctx = new MayorIgualExprContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(583);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(584);
						match(MAYOR_IGUAL);
						setState(585);
						expresion(18);
						}
						break;
					case 6:
						{
						_localctx = new MenorIgualExprContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(586);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(587);
						match(MENOR_IGUAL);
						setState(588);
						expresion(17);
						}
						break;
					case 7:
						{
						_localctx = new IgualExprContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(589);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(590);
						match(IGUAL_IGUAL);
						setState(591);
						expresion(16);
						}
						break;
					case 8:
						{
						_localctx = new DiferenteExprContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(592);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(593);
						match(DIFERENTE);
						setState(594);
						expresion(15);
						}
						break;
					case 9:
						{
						_localctx = new SumaExprContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(595);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(596);
						match(MAS);
						setState(597);
						expresion(13);
						}
						break;
					case 10:
						{
						_localctx = new RestaExprContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(598);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(599);
						match(MENOS);
						setState(600);
						expresion(12);
						}
						break;
					case 11:
						{
						_localctx = new MultExprContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(601);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(602);
						match(MULT);
						setState(603);
						expresion(11);
						}
						break;
					case 12:
						{
						_localctx = new DivExprContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(604);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(605);
						match(DIV);
						setState(606);
						expresion(10);
						}
						break;
					case 13:
						{
						_localctx = new ModExprContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(607);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(608);
						match(MOD);
						setState(609);
						expresion(9);
						}
						break;
					}
					}
				}
				setState(614);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListaContext extends ParserRuleContext {
		public TerminalNode CORCH_IZQ() { return getToken(proyectoParser.CORCH_IZQ, 0); }
		public TerminalNode CORCH_DER() { return getToken(proyectoParser.CORCH_DER, 0); }
		public ArgumentosContext argumentos() {
			return getRuleContext(ArgumentosContext.class,0);
		}
		public ListaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lista; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterLista(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitLista(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitLista(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListaContext lista() throws RecognitionException {
		ListaContext _localctx = new ListaContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_lista);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(615);
			match(CORCH_IZQ);
			setState(617);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 84)) & ~0x3f) == 0 && ((1L << (_la - 84)) & 126492691L) != 0)) {
				{
				setState(616);
				argumentos();
				}
			}

			setState(619);
			match(CORCH_DER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(proyectoParser.ID, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(621);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumeroContext extends ParserRuleContext {
		public TerminalNode ENTERO() { return getToken(proyectoParser.ENTERO, 0); }
		public TerminalNode DECIMAL() { return getToken(proyectoParser.DECIMAL, 0); }
		public NumeroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numero; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterNumero(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitNumero(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitNumero(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumeroContext numero() throws RecognitionException {
		NumeroContext _localctx = new NumeroContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_numero);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(623);
			_la = _input.LA(1);
			if ( !(_la==ENTERO || _la==DECIMAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CadenaContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(proyectoParser.STRING, 0); }
		public CadenaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cadena; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterCadena(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitCadena(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitCadena(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CadenaContext cadena() throws RecognitionException {
		CadenaContext _localctx = new CadenaContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_cadena);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(625);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BooleanoContext extends ParserRuleContext {
		public TerminalNode VERDADERO() { return getToken(proyectoParser.VERDADERO, 0); }
		public TerminalNode FALSO() { return getToken(proyectoParser.FALSO, 0); }
		public BooleanoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleano; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).enterBooleano(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof proyectoListener ) ((proyectoListener)listener).exitBooleano(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof proyectoVisitor ) return ((proyectoVisitor<? extends T>)visitor).visitBooleano(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BooleanoContext booleano() throws RecognitionException {
		BooleanoContext _localctx = new BooleanoContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_booleano);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(627);
			_la = _input.LA(1);
			if ( !(_la==VERDADERO || _la==FALSO) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 61:
			return expresion_sempred((ExpresionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expresion_sempred(ExpresionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 22);
		case 1:
			return precpred(_ctx, 21);
		case 2:
			return precpred(_ctx, 19);
		case 3:
			return precpred(_ctx, 18);
		case 4:
			return precpred(_ctx, 17);
		case 5:
			return precpred(_ctx, 16);
		case 6:
			return precpred(_ctx, 15);
		case 7:
			return precpred(_ctx, 14);
		case 8:
			return precpred(_ctx, 12);
		case 9:
			return precpred(_ctx, 11);
		case 10:
			return precpred(_ctx, 10);
		case 11:
			return precpred(_ctx, 9);
		case 12:
			return precpred(_ctx, 8);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001p\u0276\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0002"+
		"2\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u00076\u0002"+
		"7\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0002;\u0007;\u0002"+
		"<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007?\u0002@\u0007@\u0002"+
		"A\u0007A\u0002B\u0007B\u0001\u0000\u0005\u0000\u0088\b\u0000\n\u0000\f"+
		"\u0000\u008b\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001\u00a7\b\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00c0"+
		"\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0005\b\u00c9\b\b\n\b\f\b\u00cc\t\b\u0001\t\u0001\t\u0001\t"+
		"\u0003\t\u00d1\b\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0005\n\u00d8"+
		"\b\n\n\n\f\n\u00db\t\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0003\r\u00e8\b\r\u0001"+
		"\r\u0001\r\u0003\r\u00ec\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0003\u000e\u00f2\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u0112\b\u0013\u0001\u0014\u0001"+
		"\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0003\u0015\u011c\b\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0019\u0005\u0019\u0134\b\u0019\n\u0019\f\u0019\u0137\t\u0019\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0003\u001a\u013c\b\u001a\u0001\u001b\u0001\u001b"+
		"\u0003\u001b\u0140\b\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e"+
		"\u0163\b\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001 "+
		"\u0001 \u0001 \u0001 \u0001!\u0001!\u0001!\u0001!\u0001\"\u0001\"\u0001"+
		"\"\u0001\"\u0001\"\u0003\"\u0176\b\"\u0001#\u0001#\u0001#\u0001#\u0001"+
		"#\u0003#\u017d\b#\u0001$\u0001$\u0001$\u0003$\u0182\b$\u0001%\u0001%\u0001"+
		"%\u0001%\u0001&\u0001&\u0001&\u0001&\u0001\'\u0001\'\u0001\'\u0001\'\u0001"+
		"\'\u0001\'\u0001\'\u0001\'\u0003\'\u0194\b\'\u0001(\u0001(\u0001(\u0001"+
		"(\u0001(\u0001(\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001*\u0001"+
		"*\u0001*\u0001*\u0001*\u0001*\u0001+\u0001+\u0001+\u0001+\u0001+\u0001"+
		"+\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001-\u0001-\u0001-\u0001"+
		"-\u0001-\u0001-\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0001/\u0001"+
		"/\u0001/\u0001/\u0001/\u0001/\u00010\u00010\u00010\u00010\u00010\u0001"+
		"1\u00011\u00011\u00011\u00011\u00012\u00012\u00012\u00012\u00012\u0001"+
		"2\u00013\u00013\u00013\u00013\u00033\u01da\b3\u00014\u00014\u00014\u0001"+
		"4\u00034\u01e0\b4\u00014\u00034\u01e3\b4\u00015\u00015\u00015\u00016\u0001"+
		"6\u00016\u00016\u00016\u00017\u00017\u00017\u00017\u00017\u00018\u0001"+
		"8\u00018\u00018\u00018\u00018\u00018\u00019\u00049\u01fa\b9\u000b9\f9"+
		"\u01fb\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:"+
		"\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001"+
		":\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001"+
		":\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0003"+
		":\u0224\b:\u0001;\u0001;\u0001<\u0001<\u0001<\u0001=\u0001=\u0001=\u0001"+
		"=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001"+
		"=\u0001=\u0003=\u023a\b=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001"+
		"=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001"+
		"=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001"+
		"=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001"+
		"=\u0001=\u0001=\u0005=\u0263\b=\n=\f=\u0266\t=\u0001>\u0001>\u0003>\u026a"+
		"\b>\u0001>\u0001>\u0001?\u0001?\u0001@\u0001@\u0001A\u0001A\u0001B\u0001"+
		"B\u0001B\u0000\u0001zC\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012"+
		"\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\"+
		"^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0000\u0004\u0001\u0000JL\u0001\u0000"+
		"MP\u0001\u0000lm\u0001\u0000TU\u0294\u0000\u0089\u0001\u0000\u0000\u0000"+
		"\u0002\u00a6\u0001\u0000\u0000\u0000\u0004\u00a8\u0001\u0000\u0000\u0000"+
		"\u0006\u00ab\u0001\u0000\u0000\u0000\b\u00af\u0001\u0000\u0000\u0000\n"+
		"\u00b2\u0001\u0000\u0000\u0000\f\u00b7\u0001\u0000\u0000\u0000\u000e\u00bb"+
		"\u0001\u0000\u0000\u0000\u0010\u00c5\u0001\u0000\u0000\u0000\u0012\u00cd"+
		"\u0001\u0000\u0000\u0000\u0014\u00d4\u0001\u0000\u0000\u0000\u0016\u00dc"+
		"\u0001\u0000\u0000\u0000\u0018\u00df\u0001\u0000\u0000\u0000\u001a\u00e5"+
		"\u0001\u0000\u0000\u0000\u001c\u00ed\u0001\u0000\u0000\u0000\u001e\u00f3"+
		"\u0001\u0000\u0000\u0000 \u00f7\u0001\u0000\u0000\u0000\"\u00fb\u0001"+
		"\u0000\u0000\u0000$\u0100\u0001\u0000\u0000\u0000&\u0111\u0001\u0000\u0000"+
		"\u0000(\u0113\u0001\u0000\u0000\u0000*\u0115\u0001\u0000\u0000\u0000,"+
		"\u011f\u0001\u0000\u0000\u0000.\u0124\u0001\u0000\u0000\u00000\u012c\u0001"+
		"\u0000\u0000\u00002\u0135\u0001\u0000\u0000\u00004\u0138\u0001\u0000\u0000"+
		"\u00006\u013f\u0001\u0000\u0000\u00008\u0141\u0001\u0000\u0000\u0000:"+
		"\u0145\u0001\u0000\u0000\u0000<\u0162\u0001\u0000\u0000\u0000>\u0164\u0001"+
		"\u0000\u0000\u0000@\u0168\u0001\u0000\u0000\u0000B\u016c\u0001\u0000\u0000"+
		"\u0000D\u0170\u0001\u0000\u0000\u0000F\u0177\u0001\u0000\u0000\u0000H"+
		"\u017e\u0001\u0000\u0000\u0000J\u0183\u0001\u0000\u0000\u0000L\u0187\u0001"+
		"\u0000\u0000\u0000N\u0193\u0001\u0000\u0000\u0000P\u0195\u0001\u0000\u0000"+
		"\u0000R\u019b\u0001\u0000\u0000\u0000T\u01a1\u0001\u0000\u0000\u0000V"+
		"\u01a7\u0001\u0000\u0000\u0000X\u01ad\u0001\u0000\u0000\u0000Z\u01b3\u0001"+
		"\u0000\u0000\u0000\\\u01b9\u0001\u0000\u0000\u0000^\u01bf\u0001\u0000"+
		"\u0000\u0000`\u01c5\u0001\u0000\u0000\u0000b\u01ca\u0001\u0000\u0000\u0000"+
		"d\u01cf\u0001\u0000\u0000\u0000f\u01d5\u0001\u0000\u0000\u0000h\u01db"+
		"\u0001\u0000\u0000\u0000j\u01e4\u0001\u0000\u0000\u0000l\u01e7\u0001\u0000"+
		"\u0000\u0000n\u01ec\u0001\u0000\u0000\u0000p\u01f1\u0001\u0000\u0000\u0000"+
		"r\u01f9\u0001\u0000\u0000\u0000t\u0223\u0001\u0000\u0000\u0000v\u0225"+
		"\u0001\u0000\u0000\u0000x\u0227\u0001\u0000\u0000\u0000z\u0239\u0001\u0000"+
		"\u0000\u0000|\u0267\u0001\u0000\u0000\u0000~\u026d\u0001\u0000\u0000\u0000"+
		"\u0080\u026f\u0001\u0000\u0000\u0000\u0082\u0271\u0001\u0000\u0000\u0000"+
		"\u0084\u0273\u0001\u0000\u0000\u0000\u0086\u0088\u0003\u0002\u0001\u0000"+
		"\u0087\u0086\u0001\u0000\u0000\u0000\u0088\u008b\u0001\u0000\u0000\u0000"+
		"\u0089\u0087\u0001\u0000\u0000\u0000\u0089\u008a\u0001\u0000\u0000\u0000"+
		"\u008a\u008c\u0001\u0000\u0000\u0000\u008b\u0089\u0001\u0000\u0000\u0000"+
		"\u008c\u008d\u0005\u0000\u0000\u0001\u008d\u0001\u0001\u0000\u0000\u0000"+
		"\u008e\u00a7\u0003\n\u0005\u0000\u008f\u00a7\u0003\f\u0006\u0000\u0090"+
		"\u00a7\u0003\b\u0004\u0000\u0091\u00a7\u0003\u0006\u0003\u0000\u0092\u00a7"+
		"\u0003x<\u0000\u0093\u00a7\u0003*\u0015\u0000\u0094\u00a7\u0003,\u0016"+
		"\u0000\u0095\u00a7\u0003.\u0017\u0000\u0096\u00a7\u0003\u000e\u0007\u0000"+
		"\u0097\u00a7\u0003\u0012\t\u0000\u0098\u00a7\u0003\u0016\u000b\u0000\u0099"+
		"\u00a7\u0003\u0018\f\u0000\u009a\u00a7\u0003\u001c\u000e\u0000\u009b\u00a7"+
		"\u0003\u001a\r\u0000\u009c\u00a7\u0003\u001e\u000f\u0000\u009d\u00a7\u0003"+
		" \u0010\u0000\u009e\u00a7\u0003\"\u0011\u0000\u009f\u00a7\u0003$\u0012"+
		"\u0000\u00a0\u00a7\u00036\u001b\u0000\u00a1\u00a7\u00034\u001a\u0000\u00a2"+
		"\u00a7\u00030\u0018\u0000\u00a3\u00a7\u0003\u0004\u0002\u0000\u00a4\u00a7"+
		"\u0003<\u001e\u0000\u00a5\u00a7\u0003z=\u0000\u00a6\u008e\u0001\u0000"+
		"\u0000\u0000\u00a6\u008f\u0001\u0000\u0000\u0000\u00a6\u0090\u0001\u0000"+
		"\u0000\u0000\u00a6\u0091\u0001\u0000\u0000\u0000\u00a6\u0092\u0001\u0000"+
		"\u0000\u0000\u00a6\u0093\u0001\u0000\u0000\u0000\u00a6\u0094\u0001\u0000"+
		"\u0000\u0000\u00a6\u0095\u0001\u0000\u0000\u0000\u00a6\u0096\u0001\u0000"+
		"\u0000\u0000\u00a6\u0097\u0001\u0000\u0000\u0000\u00a6\u0098\u0001\u0000"+
		"\u0000\u0000\u00a6\u0099\u0001\u0000\u0000\u0000\u00a6\u009a\u0001\u0000"+
		"\u0000\u0000\u00a6\u009b\u0001\u0000\u0000\u0000\u00a6\u009c\u0001\u0000"+
		"\u0000\u0000\u00a6\u009d\u0001\u0000\u0000\u0000\u00a6\u009e\u0001\u0000"+
		"\u0000\u0000\u00a6\u009f\u0001\u0000\u0000\u0000\u00a6\u00a0\u0001\u0000"+
		"\u0000\u0000\u00a6\u00a1\u0001\u0000\u0000\u0000\u00a6\u00a2\u0001\u0000"+
		"\u0000\u0000\u00a6\u00a3\u0001\u0000\u0000\u0000\u00a6\u00a4\u0001\u0000"+
		"\u0000\u0000\u00a6\u00a5\u0001\u0000\u0000\u0000\u00a7\u0003\u0001\u0000"+
		"\u0000\u0000\u00a8\u00a9\u0005\u0001\u0000\u0000\u00a9\u00aa\u0003\u0082"+
		"A\u0000\u00aa\u0005\u0001\u0000\u0000\u0000\u00ab\u00ac\u0005H\u0000\u0000"+
		"\u00ac\u00ad\u00057\u0000\u0000\u00ad\u00ae\u0003z=\u0000\u00ae\u0007"+
		"\u0001\u0000\u0000\u0000\u00af\u00b0\u0005S\u0000\u0000\u00b0\u00b1\u0005"+
		"I\u0000\u0000\u00b1\t\u0001\u0000\u0000\u0000\u00b2\u00b3\u0005\u0002"+
		"\u0000\u0000\u00b3\u00b4\u0005k\u0000\u0000\u00b4\u00b5\u0005Y\u0000\u0000"+
		"\u00b5\u00b6\u0003z=\u0000\u00b6\u000b\u0001\u0000\u0000\u0000\u00b7\u00b8"+
		"\u0005k\u0000\u0000\u00b8\u00b9\u0005Y\u0000\u0000\u00b9\u00ba\u0003z"+
		"=\u0000\u00ba\r\u0001\u0000\u0000\u0000\u00bb\u00bc\u0005\u0004\u0000"+
		"\u0000\u00bc\u00bd\u0005k\u0000\u0000\u00bd\u00bf\u0005e\u0000\u0000\u00be"+
		"\u00c0\u0003\u0010\b\u0000\u00bf\u00be\u0001\u0000\u0000\u0000\u00bf\u00c0"+
		"\u0001\u0000\u0000\u0000\u00c0\u00c1\u0001\u0000\u0000\u0000\u00c1\u00c2"+
		"\u0005f\u0000\u0000\u00c2\u00c3\u00032\u0019\u0000\u00c3\u00c4\u0005\u0005"+
		"\u0000\u0000\u00c4\u000f\u0001\u0000\u0000\u0000\u00c5\u00ca\u0005k\u0000"+
		"\u0000\u00c6\u00c7\u0005i\u0000\u0000\u00c7\u00c9\u0005k\u0000\u0000\u00c8"+
		"\u00c6\u0001\u0000\u0000\u0000\u00c9\u00cc\u0001\u0000\u0000\u0000\u00ca"+
		"\u00c8\u0001\u0000\u0000\u0000\u00ca\u00cb\u0001\u0000\u0000\u0000\u00cb"+
		"\u0011\u0001\u0000\u0000\u0000\u00cc\u00ca\u0001\u0000\u0000\u0000\u00cd"+
		"\u00ce\u0005k\u0000\u0000\u00ce\u00d0\u0005e\u0000\u0000\u00cf\u00d1\u0003"+
		"\u0014\n\u0000\u00d0\u00cf\u0001\u0000\u0000\u0000\u00d0\u00d1\u0001\u0000"+
		"\u0000\u0000\u00d1\u00d2\u0001\u0000\u0000\u0000\u00d2\u00d3\u0005f\u0000"+
		"\u0000\u00d3\u0013\u0001\u0000\u0000\u0000\u00d4\u00d9\u0003z=\u0000\u00d5"+
		"\u00d6\u0005i\u0000\u0000\u00d6\u00d8\u0003z=\u0000\u00d7\u00d5\u0001"+
		"\u0000\u0000\u0000\u00d8\u00db\u0001\u0000\u0000\u0000\u00d9\u00d7\u0001"+
		"\u0000\u0000\u0000\u00d9\u00da\u0001\u0000\u0000\u0000\u00da\u0015\u0001"+
		"\u0000\u0000\u0000\u00db\u00d9\u0001\u0000\u0000\u0000\u00dc\u00dd\u0005"+
		"\u0006\u0000\u0000\u00dd\u00de\u0003z=\u0000\u00de\u0017\u0001\u0000\u0000"+
		"\u0000\u00df\u00e0\u0005\b\u0000\u0000\u00e0\u00e1\u0005k\u0000\u0000"+
		"\u00e1\u00e2\u0005j\u0000\u0000\u00e2\u00e3\u00032\u0019\u0000\u00e3\u00e4"+
		"\u0005\u0007\u0000\u0000\u00e4\u0019\u0001\u0000\u0000\u0000\u00e5\u00e7"+
		"\u0005\r\u0000\u0000\u00e6\u00e8\u0005\b\u0000\u0000\u00e7\u00e6\u0001"+
		"\u0000\u0000\u0000\u00e7\u00e8\u0001\u0000\u0000\u0000\u00e8\u00e9\u0001"+
		"\u0000\u0000\u0000\u00e9\u00eb\u0005k\u0000\u0000\u00ea\u00ec\u0003&\u0013"+
		"\u0000\u00eb\u00ea\u0001\u0000\u0000\u0000\u00eb\u00ec\u0001\u0000\u0000"+
		"\u0000\u00ec\u001b\u0001\u0000\u0000\u0000\u00ed\u00ee\u0005\r\u0000\u0000"+
		"\u00ee\u00ef\u0005*\u0000\u0000\u00ef\u00f1\u0003z=\u0000\u00f0\u00f2"+
		"\u0003&\u0013\u0000\u00f1\u00f0\u0001\u0000\u0000\u0000\u00f1\u00f2\u0001"+
		"\u0000\u0000\u0000\u00f2\u001d\u0001\u0000\u0000\u0000\u00f3\u00f4\u0005"+
		" \u0000\u0000\u00f4\u00f5\u0005\t\u0000\u0000\u00f5\u00f6\u0005\u000b"+
		"\u0000\u0000\u00f6\u001f\u0001\u0000\u0000\u0000\u00f7\u00f8\u0005!\u0000"+
		"\u0000\u00f8\u00f9\u0005\t\u0000\u0000\u00f9\u00fa\u0005\u000b\u0000\u0000"+
		"\u00fa!\u0001\u0000\u0000\u0000\u00fb\u00fc\u0005!\u0000\u0000\u00fc\u00fd"+
		"\u0005\b\u0000\u0000\u00fd\u00fe\u0005\n\u0000\u0000\u00fe\u00ff\u0005"+
		"k\u0000\u0000\u00ff#\u0001\u0000\u0000\u0000\u0100\u0101\u0005\'\u0000"+
		"\u0000\u0101\u0102\u0005\f\u0000\u0000\u0102\u0103\u00056\u0000\u0000"+
		"\u0103\u0104\u0005\b\u0000\u0000\u0104\u0105\u0005k\u0000\u0000\u0105"+
		"\u0106\u0003&\u0013\u0000\u0106%\u0001\u0000\u0000\u0000\u0107\u0108\u0005"+
		"\u0015\u0000\u0000\u0108\u0109\u0003\u0080@\u0000\u0109\u010a\u0003(\u0014"+
		"\u0000\u010a\u0112\u0001\u0000\u0000\u0000\u010b\u010c\u0005D\u0000\u0000"+
		"\u010c\u010d\u0005E\u0000\u0000\u010d\u0112\u0005F\u0000\u0000\u010e\u010f"+
		"\u00057\u0000\u0000\u010f\u0110\u0005G\u0000\u0000\u0110\u0112\u0003\u0082"+
		"A\u0000\u0111\u0107\u0001\u0000\u0000\u0000\u0111\u010b\u0001\u0000\u0000"+
		"\u0000\u0111\u010e\u0001\u0000\u0000\u0000\u0112\'\u0001\u0000\u0000\u0000"+
		"\u0113\u0114\u0007\u0000\u0000\u0000\u0114)\u0001\u0000\u0000\u0000\u0115"+
		"\u0116\u0005\u000e\u0000\u0000\u0116\u0117\u0003z=\u0000\u0117\u0118\u0005"+
		"\u000f\u0000\u0000\u0118\u011b\u00032\u0019\u0000\u0119\u011a\u0005\u0010"+
		"\u0000\u0000\u011a\u011c\u00032\u0019\u0000\u011b\u0119\u0001\u0000\u0000"+
		"\u0000\u011b\u011c\u0001\u0000\u0000\u0000\u011c\u011d\u0001\u0000\u0000"+
		"\u0000\u011d\u011e\u0005\u0011\u0000\u0000\u011e+\u0001\u0000\u0000\u0000"+
		"\u011f\u0120\u0005\u0012\u0000\u0000\u0120\u0121\u0003z=\u0000\u0121\u0122"+
		"\u00032\u0019\u0000\u0122\u0123\u0005\u0013\u0000\u0000\u0123-\u0001\u0000"+
		"\u0000\u0000\u0124\u0125\u0005\u0014\u0000\u0000\u0125\u0126\u0005\u0015"+
		"\u0000\u0000\u0126\u0127\u0005k\u0000\u0000\u0127\u0128\u0005\u0003\u0000"+
		"\u0000\u0128\u0129\u0003z=\u0000\u0129\u012a\u00032\u0019\u0000\u012a"+
		"\u012b\u0005\u0016\u0000\u0000\u012b/\u0001\u0000\u0000\u0000\u012c\u012d"+
		"\u0005\u0017\u0000\u0000\u012d\u012e\u00032\u0019\u0000\u012e\u012f\u0005"+
		"\u0018\u0000\u0000\u012f\u0130\u00032\u0019\u0000\u0130\u0131\u0005\u0019"+
		"\u0000\u0000\u01311\u0001\u0000\u0000\u0000\u0132\u0134\u0003\u0002\u0001"+
		"\u0000\u0133\u0132\u0001\u0000\u0000\u0000\u0134\u0137\u0001\u0000\u0000"+
		"\u0000\u0135\u0133\u0001\u0000\u0000\u0000\u0135\u0136\u0001\u0000\u0000"+
		"\u0000\u01363\u0001\u0000\u0000\u0000\u0137\u0135\u0001\u0000\u0000\u0000"+
		"\u0138\u013b\u0005\u001a\u0000\u0000\u0139\u013c\u0003<\u001e\u0000\u013a"+
		"\u013c\u00036\u001b\u0000\u013b\u0139\u0001\u0000\u0000\u0000\u013b\u013a"+
		"\u0001\u0000\u0000\u0000\u013c5\u0001\u0000\u0000\u0000\u013d\u0140\u0003"+
		"8\u001c\u0000\u013e\u0140\u0003:\u001d\u0000\u013f\u013d\u0001\u0000\u0000"+
		"\u0000\u013f\u013e\u0001\u0000\u0000\u0000\u01407\u0001\u0000\u0000\u0000"+
		"\u0141\u0142\u0005\r\u0000\u0000\u0142\u0143\u0005Q\u0000\u0000\u0143"+
		"\u0144\u0003z=\u0000\u01449\u0001\u0000\u0000\u0000\u0145\u0146\u0005"+
		"\r\u0000\u0000\u0146\u0147\u0005R\u0000\u0000\u0147\u0148\u0003z=\u0000"+
		"\u0148;\u0001\u0000\u0000\u0000\u0149\u0163\u0003>\u001f\u0000\u014a\u0163"+
		"\u0003@ \u0000\u014b\u0163\u0003f3\u0000\u014c\u0163\u0003h4\u0000\u014d"+
		"\u0163\u0003T*\u0000\u014e\u0163\u0003V+\u0000\u014f\u0163\u0003X,\u0000"+
		"\u0150\u0163\u0003Z-\u0000\u0151\u0163\u0003\\.\u0000\u0152\u0163\u0003"+
		"^/\u0000\u0153\u0163\u0003B!\u0000\u0154\u0163\u0003`0\u0000\u0155\u0163"+
		"\u0003b1\u0000\u0156\u0163\u0003D\"\u0000\u0157\u0163\u0003F#\u0000\u0158"+
		"\u0163\u0003H$\u0000\u0159\u0163\u0003J%\u0000\u015a\u0163\u0003L&\u0000"+
		"\u015b\u0163\u0003N\'\u0000\u015c\u0163\u0003P(\u0000\u015d\u0163\u0003"+
		"R)\u0000\u015e\u0163\u0003l6\u0000\u015f\u0163\u0003n7\u0000\u0160\u0163"+
		"\u0003d2\u0000\u0161\u0163\u0003p8\u0000\u0162\u0149\u0001\u0000\u0000"+
		"\u0000\u0162\u014a\u0001\u0000\u0000\u0000\u0162\u014b\u0001\u0000\u0000"+
		"\u0000\u0162\u014c\u0001\u0000\u0000\u0000\u0162\u014d\u0001\u0000\u0000"+
		"\u0000\u0162\u014e\u0001\u0000\u0000\u0000\u0162\u014f\u0001\u0000\u0000"+
		"\u0000\u0162\u0150\u0001\u0000\u0000\u0000\u0162\u0151\u0001\u0000\u0000"+
		"\u0000\u0162\u0152\u0001\u0000\u0000\u0000\u0162\u0153\u0001\u0000\u0000"+
		"\u0000\u0162\u0154\u0001\u0000\u0000\u0000\u0162\u0155\u0001\u0000\u0000"+
		"\u0000\u0162\u0156\u0001\u0000\u0000\u0000\u0162\u0157\u0001\u0000\u0000"+
		"\u0000\u0162\u0158\u0001\u0000\u0000\u0000\u0162\u0159\u0001\u0000\u0000"+
		"\u0000\u0162\u015a\u0001\u0000\u0000\u0000\u0162\u015b\u0001\u0000\u0000"+
		"\u0000\u0162\u015c\u0001\u0000\u0000\u0000\u0162\u015d\u0001\u0000\u0000"+
		"\u0000\u0162\u015e\u0001\u0000\u0000\u0000\u0162\u015f\u0001\u0000\u0000"+
		"\u0000\u0162\u0160\u0001\u0000\u0000\u0000\u0162\u0161\u0001\u0000\u0000"+
		"\u0000\u0163=\u0001\u0000\u0000\u0000\u0164\u0165\u0005\u001b\u0000\u0000"+
		"\u0165\u0166\u0005*\u0000\u0000\u0166\u0167\u0003z=\u0000\u0167?\u0001"+
		"\u0000\u0000\u0000\u0168\u0169\u0005\u001b\u0000\u0000\u0169\u016a\u0005"+
		",\u0000\u0000\u016a\u016b\u0003z=\u0000\u016bA\u0001\u0000\u0000\u0000"+
		"\u016c\u016d\u0005\u001c\u0000\u0000\u016d\u016e\u0005*\u0000\u0000\u016e"+
		"\u016f\u0003z=\u0000\u016fC\u0001\u0000\u0000\u0000\u0170\u0171\u0005"+
		"\u001f\u0000\u0000\u0171\u0172\u0005+\u0000\u0000\u0172\u0173\u0005\u0003"+
		"\u0000\u0000\u0173\u0175\u0003z=\u0000\u0174\u0176\u0003r9\u0000\u0175"+
		"\u0174\u0001\u0000\u0000\u0000\u0175\u0176\u0001\u0000\u0000\u0000\u0176"+
		"E\u0001\u0000\u0000\u0000\u0177\u0178\u0005\u001f\u0000\u0000\u0178\u0179"+
		"\u0005-\u0000\u0000\u0179\u017a\u0005\u0003\u0000\u0000\u017a\u017c\u0003"+
		"z=\u0000\u017b\u017d\u0003r9\u0000\u017c\u017b\u0001\u0000\u0000\u0000"+
		"\u017c\u017d\u0001\u0000\u0000\u0000\u017dG\u0001\u0000\u0000\u0000\u017e"+
		"\u017f\u0005 \u0000\u0000\u017f\u0181\u0005.\u0000\u0000\u0180\u0182\u0003"+
		"z=\u0000\u0181\u0180\u0001\u0000\u0000\u0000\u0181\u0182\u0001\u0000\u0000"+
		"\u0000\u0182I\u0001\u0000\u0000\u0000\u0183\u0184\u0005&\u0000\u0000\u0184"+
		"\u0185\u0005*\u0000\u0000\u0185\u0186\u0003z=\u0000\u0186K\u0001\u0000"+
		"\u0000\u0000\u0187\u0188\u0005&\u0000\u0000\u0188\u0189\u0005,\u0000\u0000"+
		"\u0189\u018a\u0003z=\u0000\u018aM\u0001\u0000\u0000\u0000\u018b\u018c"+
		"\u0005%\u0000\u0000\u018c\u018d\u0005(\u0000\u0000\u018d\u018e\u0005*"+
		"\u0000\u0000\u018e\u0194\u0003z=\u0000\u018f\u0190\u0005%\u0000\u0000"+
		"\u0190\u0191\u0005(\u0000\u0000\u0191\u0192\u0005,\u0000\u0000\u0192\u0194"+
		"\u0003z=\u0000\u0193\u018b\u0001\u0000\u0000\u0000\u0193\u018f\u0001\u0000"+
		"\u0000\u0000\u0194O\u0001\u0000\u0000\u0000\u0195\u0196\u0005%\u0000\u0000"+
		"\u0196\u0197\u0005)\u0000\u0000\u0197\u0198\u0005+\u0000\u0000\u0198\u0199"+
		"\u0005\u0003\u0000\u0000\u0199\u019a\u0003z=\u0000\u019aQ\u0001\u0000"+
		"\u0000\u0000\u019b\u019c\u0005%\u0000\u0000\u019c\u019d\u00053\u0000\u0000"+
		"\u019d\u019e\u0005k\u0000\u0000\u019e\u019f\u00056\u0000\u0000\u019f\u01a0"+
		"\u0003z=\u0000\u01a0S\u0001\u0000\u0000\u0000\u01a1\u01a2\u0005#\u0000"+
		"\u0000\u01a2\u01a3\u0005*\u0000\u0000\u01a3\u01a4\u0003z=\u0000\u01a4"+
		"\u01a5\u00057\u0000\u0000\u01a5\u01a6\u0003z=\u0000\u01a6U\u0001\u0000"+
		"\u0000\u0000\u01a7\u01a8\u0005#\u0000\u0000\u01a8\u01a9\u0005,\u0000\u0000"+
		"\u01a9\u01aa\u0003z=\u0000\u01aa\u01ab\u00057\u0000\u0000\u01ab\u01ac"+
		"\u0003z=\u0000\u01acW\u0001\u0000\u0000\u0000\u01ad\u01ae\u0005\"\u0000"+
		"\u0000\u01ae\u01af\u0005*\u0000\u0000\u01af\u01b0\u0003z=\u0000\u01b0"+
		"\u01b1\u00057\u0000\u0000\u01b1\u01b2\u0003z=\u0000\u01b2Y\u0001\u0000"+
		"\u0000\u0000\u01b3\u01b4\u0005\"\u0000\u0000\u01b4\u01b5\u0005,\u0000"+
		"\u0000\u01b5\u01b6\u0003z=\u0000\u01b6\u01b7\u00057\u0000\u0000\u01b7"+
		"\u01b8\u0003z=\u0000\u01b8[\u0001\u0000\u0000\u0000\u01b9\u01ba\u0005"+
		"$\u0000\u0000\u01ba\u01bb\u0005*\u0000\u0000\u01bb\u01bc\u0003z=\u0000"+
		"\u01bc\u01bd\u00057\u0000\u0000\u01bd\u01be\u0003z=\u0000\u01be]\u0001"+
		"\u0000\u0000\u0000\u01bf\u01c0\u0005$\u0000\u0000\u01c0\u01c1\u0005,\u0000"+
		"\u0000\u01c1\u01c2\u0003z=\u0000\u01c2\u01c3\u00057\u0000\u0000\u01c3"+
		"\u01c4\u0003z=\u0000\u01c4_\u0001\u0000\u0000\u0000\u01c5\u01c6\u0005"+
		"\u001d\u0000\u0000\u01c6\u01c7\u0003z=\u0000\u01c7\u01c8\u0005\u0003\u0000"+
		"\u0000\u01c8\u01c9\u0003z=\u0000\u01c9a\u0001\u0000\u0000\u0000\u01ca"+
		"\u01cb\u0005\u001e\u0000\u0000\u01cb\u01cc\u0003z=\u0000\u01cc\u01cd\u0005"+
		"\u0003\u0000\u0000\u01cd\u01ce\u0003z=\u0000\u01cec\u0001\u0000\u0000"+
		"\u0000\u01cf\u01d0\u0005\'\u0000\u0000\u01d0\u01d1\u00054\u0000\u0000"+
		"\u01d1\u01d2\u0003z=\u0000\u01d2\u01d3\u00057\u0000\u0000\u01d3\u01d4"+
		"\u0003z=\u0000\u01d4e\u0001\u0000\u0000\u0000\u01d5\u01d6\u0005!\u0000"+
		"\u0000\u01d6\u01d7\u0005*\u0000\u0000\u01d7\u01d9\u0003z=\u0000\u01d8"+
		"\u01da\u0003j5\u0000\u01d9\u01d8\u0001\u0000\u0000\u0000\u01d9\u01da\u0001"+
		"\u0000\u0000\u0000\u01dag\u0001\u0000\u0000\u0000\u01db\u01dc\u0005!\u0000"+
		"\u0000\u01dc\u01dd\u0005,\u0000\u0000\u01dd\u01df\u0003z=\u0000\u01de"+
		"\u01e0\u0005A\u0000\u0000\u01df\u01de\u0001\u0000\u0000\u0000\u01df\u01e0"+
		"\u0001\u0000\u0000\u0000\u01e0\u01e2\u0001\u0000\u0000\u0000\u01e1\u01e3"+
		"\u0003j5\u0000\u01e2\u01e1\u0001\u0000\u0000\u0000\u01e2\u01e3\u0001\u0000"+
		"\u0000\u0000\u01e3i\u0001\u0000\u0000\u0000\u01e4\u01e5\u0005B\u0000\u0000"+
		"\u01e5\u01e6\u0005C\u0000\u0000\u01e6k\u0001\u0000\u0000\u0000\u01e7\u01e8"+
		"\u00051\u0000\u0000\u01e8\u01e9\u0003z=\u0000\u01e9\u01ea\u00057\u0000"+
		"\u0000\u01ea\u01eb\u0003z=\u0000\u01ebm\u0001\u0000\u0000\u0000\u01ec"+
		"\u01ed\u00052\u0000\u0000\u01ed\u01ee\u0003z=\u0000\u01ee\u01ef\u0005"+
		"\u0003\u0000\u0000\u01ef\u01f0\u0003z=\u0000\u01f0o\u0001\u0000\u0000"+
		"\u0000\u01f1\u01f2\u0005\u001b\u0000\u0000\u01f2\u01f3\u0005/\u0000\u0000"+
		"\u01f3\u01f4\u00056\u0000\u0000\u01f4\u01f5\u0003z=\u0000\u01f5\u01f6"+
		"\u0005\u0003\u0000\u0000\u01f6\u01f7\u0003z=\u0000\u01f7q\u0001\u0000"+
		"\u0000\u0000\u01f8\u01fa\u0003t:\u0000\u01f9\u01f8\u0001\u0000\u0000\u0000"+
		"\u01fa\u01fb\u0001\u0000\u0000\u0000\u01fb\u01f9\u0001\u0000\u0000\u0000"+
		"\u01fb\u01fc\u0001\u0000\u0000\u0000\u01fcs\u0001\u0000\u0000\u0000\u01fd"+
		"\u01fe\u00055\u0000\u0000\u01fe\u01ff\u00058\u0000\u0000\u01ff\u0224\u0003"+
		"\u0082A\u0000\u0200\u0201\u00055\u0000\u0000\u0201\u0202\u00059\u0000"+
		"\u0000\u0202\u0224\u0003\u0082A\u0000\u0203\u0204\u00055\u0000\u0000\u0204"+
		"\u0205\u0005:\u0000\u0000\u0205\u0224\u0003\u0082A\u0000\u0206\u0207\u0005"+
		"5\u0000\u0000\u0207\u0208\u0005;\u0000\u0000\u0208\u0224\u0003\u0082A"+
		"\u0000\u0209\u020a\u00055\u0000\u0000\u020a\u020b\u0005(\u0000\u0000\u020b"+
		"\u020c\u0005<\u0000\u0000\u020c\u020d\u0005@\u0000\u0000\u020d\u020e\u0003"+
		"z=\u0000\u020e\u020f\u0003v;\u0000\u020f\u0224\u0001\u0000\u0000\u0000"+
		"\u0210\u0211\u00055\u0000\u0000\u0211\u0212\u0005(\u0000\u0000\u0212\u0213"+
		"\u0005=\u0000\u0000\u0213\u0214\u0005@\u0000\u0000\u0214\u0215\u0003z"+
		"=\u0000\u0215\u0216\u0003v;\u0000\u0216\u0224\u0001\u0000\u0000\u0000"+
		"\u0217\u0218\u0005`\u0000\u0000\u0218\u0219\u0005>\u0000\u0000\u0219\u021a"+
		"\u0005@\u0000\u0000\u021a\u021b\u0003z=\u0000\u021b\u021c\u0005L\u0000"+
		"\u0000\u021c\u0224\u0001\u0000\u0000\u0000\u021d\u021e\u0005`\u0000\u0000"+
		"\u021e\u021f\u0005?\u0000\u0000\u021f\u0220\u0005@\u0000\u0000\u0220\u0221"+
		"\u0003z=\u0000\u0221\u0222\u0005L\u0000\u0000\u0222\u0224\u0001\u0000"+
		"\u0000\u0000\u0223\u01fd\u0001\u0000\u0000\u0000\u0223\u0200\u0001\u0000"+
		"\u0000\u0000\u0223\u0203\u0001\u0000\u0000\u0000\u0223\u0206\u0001\u0000"+
		"\u0000\u0000\u0223\u0209\u0001\u0000\u0000\u0000\u0223\u0210\u0001\u0000"+
		"\u0000\u0000\u0223\u0217\u0001\u0000\u0000\u0000\u0223\u021d\u0001\u0000"+
		"\u0000\u0000\u0224u\u0001\u0000\u0000\u0000\u0225\u0226\u0007\u0001\u0000"+
		"\u0000\u0226w\u0001\u0000\u0000\u0000\u0227\u0228\u0005S\u0000\u0000\u0228"+
		"\u0229\u0003z=\u0000\u0229y\u0001\u0000\u0000\u0000\u022a\u022b\u0006"+
		"=\uffff\uffff\u0000\u022b\u022c\u0005X\u0000\u0000\u022c\u023a\u0003z"+
		"=\u0014\u022d\u022e\u0005a\u0000\u0000\u022e\u023a\u0003z=\r\u022f\u0230"+
		"\u0005e\u0000\u0000\u0230\u0231\u0003z=\u0000\u0231\u0232\u0005f\u0000"+
		"\u0000\u0232\u023a\u0001\u0000\u0000\u0000\u0233\u023a\u0003\u0012\t\u0000"+
		"\u0234\u023a\u0003|>\u0000\u0235\u023a\u0003\u0080@\u0000\u0236\u023a"+
		"\u0003\u0082A\u0000\u0237\u023a\u0003\u0084B\u0000\u0238\u023a\u0003~"+
		"?\u0000\u0239\u022a\u0001\u0000\u0000\u0000\u0239\u022d\u0001\u0000\u0000"+
		"\u0000\u0239\u022f\u0001\u0000\u0000\u0000\u0239\u0233\u0001\u0000\u0000"+
		"\u0000\u0239\u0234\u0001\u0000\u0000\u0000\u0239\u0235\u0001\u0000\u0000"+
		"\u0000\u0239\u0236\u0001\u0000\u0000\u0000\u0239\u0237\u0001\u0000\u0000"+
		"\u0000\u0239\u0238\u0001\u0000\u0000\u0000\u023a\u0264\u0001\u0000\u0000"+
		"\u0000\u023b\u023c\n\u0016\u0000\u0000\u023c\u023d\u0005W\u0000\u0000"+
		"\u023d\u0263\u0003z=\u0017\u023e\u023f\n\u0015\u0000\u0000\u023f\u0240"+
		"\u0005V\u0000\u0000\u0240\u0263\u0003z=\u0016\u0241\u0242\n\u0013\u0000"+
		"\u0000\u0242\u0243\u0005\\\u0000\u0000\u0243\u0263\u0003z=\u0014\u0244"+
		"\u0245\n\u0012\u0000\u0000\u0245\u0246\u0005]\u0000\u0000\u0246\u0263"+
		"\u0003z=\u0013\u0247\u0248\n\u0011\u0000\u0000\u0248\u0249\u0005^\u0000"+
		"\u0000\u0249\u0263\u0003z=\u0012\u024a\u024b\n\u0010\u0000\u0000\u024b"+
		"\u024c\u0005_\u0000\u0000\u024c\u0263\u0003z=\u0011\u024d\u024e\n\u000f"+
		"\u0000\u0000\u024e\u024f\u0005Z\u0000\u0000\u024f\u0263\u0003z=\u0010"+
		"\u0250\u0251\n\u000e\u0000\u0000\u0251\u0252\u0005[\u0000\u0000\u0252"+
		"\u0263\u0003z=\u000f\u0253\u0254\n\f\u0000\u0000\u0254\u0255\u0005`\u0000"+
		"\u0000\u0255\u0263\u0003z=\r\u0256\u0257\n\u000b\u0000\u0000\u0257\u0258"+
		"\u0005a\u0000\u0000\u0258\u0263\u0003z=\f\u0259\u025a\n\n\u0000\u0000"+
		"\u025a\u025b\u0005b\u0000\u0000\u025b\u0263\u0003z=\u000b\u025c\u025d"+
		"\n\t\u0000\u0000\u025d\u025e\u0005c\u0000\u0000\u025e\u0263\u0003z=\n"+
		"\u025f\u0260\n\b\u0000\u0000\u0260\u0261\u0005d\u0000\u0000\u0261\u0263"+
		"\u0003z=\t\u0262\u023b\u0001\u0000\u0000\u0000\u0262\u023e\u0001\u0000"+
		"\u0000\u0000\u0262\u0241\u0001\u0000\u0000\u0000\u0262\u0244\u0001\u0000"+
		"\u0000\u0000\u0262\u0247\u0001\u0000\u0000\u0000\u0262\u024a\u0001\u0000"+
		"\u0000\u0000\u0262\u024d\u0001\u0000\u0000\u0000\u0262\u0250\u0001\u0000"+
		"\u0000\u0000\u0262\u0253\u0001\u0000\u0000\u0000\u0262\u0256\u0001\u0000"+
		"\u0000\u0000\u0262\u0259\u0001\u0000\u0000\u0000\u0262\u025c\u0001\u0000"+
		"\u0000\u0000\u0262\u025f\u0001\u0000\u0000\u0000\u0263\u0266\u0001\u0000"+
		"\u0000\u0000\u0264\u0262\u0001\u0000\u0000\u0000\u0264\u0265\u0001\u0000"+
		"\u0000\u0000\u0265{\u0001\u0000\u0000\u0000\u0266\u0264\u0001\u0000\u0000"+
		"\u0000\u0267\u0269\u0005g\u0000\u0000\u0268\u026a\u0003\u0014\n\u0000"+
		"\u0269\u0268\u0001\u0000\u0000\u0000\u0269\u026a\u0001\u0000\u0000\u0000"+
		"\u026a\u026b\u0001\u0000\u0000\u0000\u026b\u026c\u0005h\u0000\u0000\u026c"+
		"}\u0001\u0000\u0000\u0000\u026d\u026e\u0005k\u0000\u0000\u026e\u007f\u0001"+
		"\u0000\u0000\u0000\u026f\u0270\u0007\u0002\u0000\u0000\u0270\u0081\u0001"+
		"\u0000\u0000\u0000\u0271\u0272\u0005n\u0000\u0000\u0272\u0083\u0001\u0000"+
		"\u0000\u0000\u0273\u0274\u0007\u0003\u0000\u0000\u0274\u0085\u0001\u0000"+
		"\u0000\u0000\u001c\u0089\u00a6\u00bf\u00ca\u00d0\u00d9\u00e7\u00eb\u00f1"+
		"\u0111\u011b\u0135\u013b\u013f\u0162\u0175\u017c\u0181\u0193\u01d9\u01df"+
		"\u01e2\u01fb\u0223\u0239\u0262\u0264\u0269";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
