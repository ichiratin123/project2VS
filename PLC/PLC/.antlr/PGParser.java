// Generated from e:/code/PJP/project2VS/PLC/PLC/PG.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class PGParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, INT_KW=8, FLOAT_KW=9, 
		STRING_KW=10, BOOL_KW=11, WRITE_KW=12, READ_KW=13, WHILE_KW=14, FOR_KW=15, 
		IF_KW=16, ELSE_KW=17, SEMI=18, COMMA=19, MUL=20, DIV=21, ADD=22, SUB=23, 
		MOD=24, GT=25, LT=26, EQ=27, AND=28, OR=29, NOT=30, NEQ=31, CONCAT=32, 
		STRING=33, BOOL=34, ID=35, FLOAT=36, INT=37, WS=38, COMMENT=39;
	public static final int
		RULE_program = 0, RULE_stat = 1, RULE_expr = 2, RULE_ptype = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "stat", "expr", "ptype"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'{'", "'}'", "'?'", "':'", "'='", "'int'", "'float'", 
			"'string'", "'bool'", "'write'", "'read'", "'while'", "'for'", "'if'", 
			"'else'", "';'", "','", "'*'", "'/'", "'+'", "'-'", "'%'", "'>'", "'<'", 
			"'=='", "'&&'", "'||'", "'!'", "'!='", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, "INT_KW", "FLOAT_KW", 
			"STRING_KW", "BOOL_KW", "WRITE_KW", "READ_KW", "WHILE_KW", "FOR_KW", 
			"IF_KW", "ELSE_KW", "SEMI", "COMMA", "MUL", "DIV", "ADD", "SUB", "MOD", 
			"GT", "LT", "EQ", "AND", "OR", "NOT", "NEQ", "CONCAT", "STRING", "BOOL", 
			"ID", "FLOAT", "INT", "WS", "COMMENT"
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
	public String getGrammarFileName() { return "PG.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PGParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(PGParser.EOF, 0); }
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(9); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(8);
				stat();
				}
				}
				setState(11); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 817126047498L) != 0) );
			setState(13);
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
	public static class StatContext extends ParserRuleContext {
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	 
		public StatContext() { }
		public void copyFrom(StatContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BlockStatContext extends StatContext {
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public BlockStatContext(StatContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WriteExprContext extends StatContext {
		public TerminalNode WRITE_KW() { return getToken(PGParser.WRITE_KW, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PGParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PGParser.COMMA, i);
		}
		public List<TerminalNode> SEMI() { return getTokens(PGParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(PGParser.SEMI, i);
		}
		public WriteExprContext(StatContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IfConContext extends StatContext {
		public TerminalNode IF_KW() { return getToken(PGParser.IF_KW, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public TerminalNode ELSE_KW() { return getToken(PGParser.ELSE_KW, 0); }
		public IfConContext(StatContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WhileLoopContext extends StatContext {
		public TerminalNode WHILE_KW() { return getToken(PGParser.WHILE_KW, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public WhileLoopContext(StatContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EmptyExprContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<TerminalNode> SEMI() { return getTokens(PGParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(PGParser.SEMI, i);
		}
		public EmptyExprContext(StatContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ReadExprContext extends StatContext {
		public TerminalNode READ_KW() { return getToken(PGParser.READ_KW, 0); }
		public List<TerminalNode> ID() { return getTokens(PGParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PGParser.ID, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PGParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PGParser.COMMA, i);
		}
		public List<TerminalNode> SEMI() { return getTokens(PGParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(PGParser.SEMI, i);
		}
		public ReadExprContext(StatContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CommentContext extends StatContext {
		public TerminalNode COMMENT() { return getToken(PGParser.COMMENT, 0); }
		public CommentContext(StatContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ForLoopContext extends StatContext {
		public TerminalNode FOR_KW() { return getToken(PGParser.FOR_KW, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(PGParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(PGParser.SEMI, i);
		}
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public ForLoopContext(StatContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeclarattionContext extends StatContext {
		public PtypeContext ptype() {
			return getRuleContext(PtypeContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(PGParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PGParser.ID, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PGParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PGParser.COMMA, i);
		}
		public List<TerminalNode> SEMI() { return getTokens(PGParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(PGParser.SEMI, i);
		}
		public DeclarattionContext(StatContext ctx) { copyFrom(ctx); }
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stat);
		int _la;
		try {
			setState(97);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT_KW:
			case FLOAT_KW:
			case STRING_KW:
			case BOOL_KW:
				_localctx = new DeclarattionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(15);
				ptype();
				setState(16);
				match(ID);
				setState(21);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(17);
					match(COMMA);
					setState(18);
					match(ID);
					}
					}
					setState(23);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(25); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(24);
					match(SEMI);
					}
					}
					setState(27); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==SEMI );
				}
				break;
			case T__0:
			case SUB:
			case NOT:
			case STRING:
			case BOOL:
			case ID:
			case FLOAT:
			case INT:
				_localctx = new EmptyExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(29);
				expr(0);
				setState(31); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(30);
					match(SEMI);
					}
					}
					setState(33); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==SEMI );
				}
				break;
			case WRITE_KW:
				_localctx = new WriteExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(35);
				match(WRITE_KW);
				setState(36);
				expr(0);
				setState(41);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(37);
					match(COMMA);
					setState(38);
					expr(0);
					}
					}
					setState(43);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(45); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(44);
					match(SEMI);
					}
					}
					setState(47); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==SEMI );
				}
				break;
			case READ_KW:
				_localctx = new ReadExprContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(49);
				match(READ_KW);
				setState(50);
				match(ID);
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(51);
					match(COMMA);
					setState(52);
					match(ID);
					}
					}
					setState(57);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(59); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(58);
					match(SEMI);
					}
					}
					setState(61); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==SEMI );
				}
				break;
			case COMMENT:
				_localctx = new CommentContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(63);
				match(COMMENT);
				}
				break;
			case IF_KW:
				_localctx = new IfConContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(64);
				match(IF_KW);
				setState(65);
				match(T__0);
				setState(66);
				expr(0);
				setState(67);
				match(T__1);
				setState(68);
				stat();
				setState(71);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					{
					setState(69);
					match(ELSE_KW);
					setState(70);
					stat();
					}
					break;
				}
				}
				break;
			case WHILE_KW:
				_localctx = new WhileLoopContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(73);
				match(WHILE_KW);
				setState(74);
				match(T__0);
				setState(75);
				expr(0);
				setState(76);
				match(T__1);
				setState(77);
				stat();
				}
				break;
			case FOR_KW:
				_localctx = new ForLoopContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(79);
				match(FOR_KW);
				setState(80);
				match(T__0);
				setState(81);
				expr(0);
				setState(82);
				match(SEMI);
				setState(83);
				expr(0);
				setState(84);
				match(SEMI);
				setState(85);
				expr(0);
				setState(86);
				match(T__1);
				setState(87);
				stat();
				}
				break;
			case T__2:
				_localctx = new BlockStatContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(89);
				match(T__2);
				setState(91); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(90);
					stat();
					}
					}
					setState(93); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 817126047498L) != 0) );
				setState(95);
				match(T__3);
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
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MulDivModContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MUL() { return getToken(PGParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(PGParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(PGParser.MOD, 0); }
		public MulDivModContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CompareContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LT() { return getToken(PGParser.LT, 0); }
		public TerminalNode GT() { return getToken(PGParser.GT, 0); }
		public TerminalNode EQ() { return getToken(PGParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(PGParser.NEQ, 0); }
		public CompareContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UminusContext extends ExprContext {
		public TerminalNode SUB() { return getToken(PGParser.SUB, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public UminusContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringContext extends ExprContext {
		public TerminalNode STRING() { return getToken(PGParser.STRING, 0); }
		public StringContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AddSubConcatContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode ADD() { return getToken(PGParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(PGParser.SUB, 0); }
		public TerminalNode CONCAT() { return getToken(PGParser.CONCAT, 0); }
		public AddSubConcatContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LogicOrContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OR() { return getToken(PGParser.OR, 0); }
		public LogicOrContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FloatContext extends ExprContext {
		public TerminalNode FLOAT() { return getToken(PGParser.FLOAT, 0); }
		public FloatContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenthesisContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParenthesisContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IntContext extends ExprContext {
		public TerminalNode INT() { return getToken(PGParser.INT, 0); }
		public IntContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotContext extends ExprContext {
		public TerminalNode NOT() { return getToken(PGParser.NOT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NotContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LogicAndContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode AND() { return getToken(PGParser.AND, 0); }
		public LogicAndContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BooleanContext extends ExprContext {
		public TerminalNode BOOL() { return getToken(PGParser.BOOL, 0); }
		public BooleanContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdContext extends ExprContext {
		public TerminalNode ID() { return getToken(PGParser.ID, 0); }
		public IdContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TernaryContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TernaryContext(ExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignContext extends ExprContext {
		public TerminalNode ID() { return getToken(PGParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignContext(ExprContext ctx) { copyFrom(ctx); }
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				_localctx = new UminusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(100);
				match(SUB);
				setState(101);
				expr(9);
				}
				break;
			case 2:
				{
				_localctx = new NotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(102);
				match(NOT);
				setState(103);
				expr(8);
				}
				break;
			case 3:
				{
				_localctx = new StringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(104);
				match(STRING);
				}
				break;
			case 4:
				{
				_localctx = new IntContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(105);
				match(INT);
				}
				break;
			case 5:
				{
				_localctx = new IdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(106);
				match(ID);
				}
				break;
			case 6:
				{
				_localctx = new BooleanContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(107);
				match(BOOL);
				}
				break;
			case 7:
				{
				_localctx = new FloatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(108);
				match(FLOAT);
				}
				break;
			case 8:
				{
				_localctx = new ParenthesisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(109);
				match(T__0);
				setState(110);
				expr(0);
				setState(111);
				match(T__1);
				}
				break;
			case 9:
				{
				_localctx = new AssignContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(113);
				match(ID);
				setState(114);
				match(T__6);
				setState(115);
				expr(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(141);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(139);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new MulDivModContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(118);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(119);
						((MulDivModContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 19922944L) != 0)) ) {
							((MulDivModContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(120);
						expr(16);
						}
						break;
					case 2:
						{
						_localctx = new AddSubConcatContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(121);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(122);
						((AddSubConcatContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4307550208L) != 0)) ) {
							((AddSubConcatContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(123);
						expr(15);
						}
						break;
					case 3:
						{
						_localctx = new CompareContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(124);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(125);
						((CompareContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2382364672L) != 0)) ) {
							((CompareContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(126);
						expr(14);
						}
						break;
					case 4:
						{
						_localctx = new LogicAndContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(127);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(128);
						((LogicAndContext)_localctx).op = match(AND);
						setState(129);
						expr(13);
						}
						break;
					case 5:
						{
						_localctx = new LogicOrContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(130);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(131);
						((LogicOrContext)_localctx).op = match(OR);
						setState(132);
						expr(12);
						}
						break;
					case 6:
						{
						_localctx = new TernaryContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(133);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(134);
						match(T__4);
						setState(135);
						expr(0);
						setState(136);
						match(T__5);
						setState(137);
						expr(11);
						}
						break;
					}
					} 
				}
				setState(143);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
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
	public static class PtypeContext extends ParserRuleContext {
		public Token type;
		public TerminalNode INT_KW() { return getToken(PGParser.INT_KW, 0); }
		public TerminalNode FLOAT_KW() { return getToken(PGParser.FLOAT_KW, 0); }
		public TerminalNode BOOL_KW() { return getToken(PGParser.BOOL_KW, 0); }
		public TerminalNode STRING_KW() { return getToken(PGParser.STRING_KW, 0); }
		public PtypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ptype; }
	}

	public final PtypeContext ptype() throws RecognitionException {
		PtypeContext _localctx = new PtypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_ptype);
		try {
			setState(148);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT_KW:
				enterOuterAlt(_localctx, 1);
				{
				setState(144);
				((PtypeContext)_localctx).type = match(INT_KW);
				}
				break;
			case FLOAT_KW:
				enterOuterAlt(_localctx, 2);
				{
				setState(145);
				((PtypeContext)_localctx).type = match(FLOAT_KW);
				}
				break;
			case BOOL_KW:
				enterOuterAlt(_localctx, 3);
				{
				setState(146);
				((PtypeContext)_localctx).type = match(BOOL_KW);
				}
				break;
			case STRING_KW:
				enterOuterAlt(_localctx, 4);
				{
				setState(147);
				((PtypeContext)_localctx).type = match(STRING_KW);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 15);
		case 1:
			return precpred(_ctx, 14);
		case 2:
			return precpred(_ctx, 13);
		case 3:
			return precpred(_ctx, 12);
		case 4:
			return precpred(_ctx, 11);
		case 5:
			return precpred(_ctx, 10);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\'\u0097\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0001\u0000\u0004\u0000\n\b"+
		"\u0000\u000b\u0000\f\u0000\u000b\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0005\u0001\u0014\b\u0001\n\u0001\f\u0001"+
		"\u0017\t\u0001\u0001\u0001\u0004\u0001\u001a\b\u0001\u000b\u0001\f\u0001"+
		"\u001b\u0001\u0001\u0001\u0001\u0004\u0001 \b\u0001\u000b\u0001\f\u0001"+
		"!\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001(\b\u0001"+
		"\n\u0001\f\u0001+\t\u0001\u0001\u0001\u0004\u0001.\b\u0001\u000b\u0001"+
		"\f\u0001/\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001"+
		"6\b\u0001\n\u0001\f\u00019\t\u0001\u0001\u0001\u0004\u0001<\b\u0001\u000b"+
		"\u0001\f\u0001=\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001H\b\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0004"+
		"\u0001\\\b\u0001\u000b\u0001\f\u0001]\u0001\u0001\u0001\u0001\u0003\u0001"+
		"b\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002u\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002"+
		"\u008c\b\u0002\n\u0002\f\u0002\u008f\t\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0003\u0003\u0095\b\u0003\u0001\u0003\u0000\u0001\u0004"+
		"\u0004\u0000\u0002\u0004\u0006\u0000\u0003\u0002\u0000\u0014\u0015\u0018"+
		"\u0018\u0002\u0000\u0016\u0017  \u0002\u0000\u0019\u001b\u001f\u001f\u00b5"+
		"\u0000\t\u0001\u0000\u0000\u0000\u0002a\u0001\u0000\u0000\u0000\u0004"+
		"t\u0001\u0000\u0000\u0000\u0006\u0094\u0001\u0000\u0000\u0000\b\n\u0003"+
		"\u0002\u0001\u0000\t\b\u0001\u0000\u0000\u0000\n\u000b\u0001\u0000\u0000"+
		"\u0000\u000b\t\u0001\u0000\u0000\u0000\u000b\f\u0001\u0000\u0000\u0000"+
		"\f\r\u0001\u0000\u0000\u0000\r\u000e\u0005\u0000\u0000\u0001\u000e\u0001"+
		"\u0001\u0000\u0000\u0000\u000f\u0010\u0003\u0006\u0003\u0000\u0010\u0015"+
		"\u0005#\u0000\u0000\u0011\u0012\u0005\u0013\u0000\u0000\u0012\u0014\u0005"+
		"#\u0000\u0000\u0013\u0011\u0001\u0000\u0000\u0000\u0014\u0017\u0001\u0000"+
		"\u0000\u0000\u0015\u0013\u0001\u0000\u0000\u0000\u0015\u0016\u0001\u0000"+
		"\u0000\u0000\u0016\u0019\u0001\u0000\u0000\u0000\u0017\u0015\u0001\u0000"+
		"\u0000\u0000\u0018\u001a\u0005\u0012\u0000\u0000\u0019\u0018\u0001\u0000"+
		"\u0000\u0000\u001a\u001b\u0001\u0000\u0000\u0000\u001b\u0019\u0001\u0000"+
		"\u0000\u0000\u001b\u001c\u0001\u0000\u0000\u0000\u001cb\u0001\u0000\u0000"+
		"\u0000\u001d\u001f\u0003\u0004\u0002\u0000\u001e \u0005\u0012\u0000\u0000"+
		"\u001f\u001e\u0001\u0000\u0000\u0000 !\u0001\u0000\u0000\u0000!\u001f"+
		"\u0001\u0000\u0000\u0000!\"\u0001\u0000\u0000\u0000\"b\u0001\u0000\u0000"+
		"\u0000#$\u0005\f\u0000\u0000$)\u0003\u0004\u0002\u0000%&\u0005\u0013\u0000"+
		"\u0000&(\u0003\u0004\u0002\u0000\'%\u0001\u0000\u0000\u0000(+\u0001\u0000"+
		"\u0000\u0000)\'\u0001\u0000\u0000\u0000)*\u0001\u0000\u0000\u0000*-\u0001"+
		"\u0000\u0000\u0000+)\u0001\u0000\u0000\u0000,.\u0005\u0012\u0000\u0000"+
		"-,\u0001\u0000\u0000\u0000./\u0001\u0000\u0000\u0000/-\u0001\u0000\u0000"+
		"\u0000/0\u0001\u0000\u0000\u00000b\u0001\u0000\u0000\u000012\u0005\r\u0000"+
		"\u000027\u0005#\u0000\u000034\u0005\u0013\u0000\u000046\u0005#\u0000\u0000"+
		"53\u0001\u0000\u0000\u000069\u0001\u0000\u0000\u000075\u0001\u0000\u0000"+
		"\u000078\u0001\u0000\u0000\u00008;\u0001\u0000\u0000\u000097\u0001\u0000"+
		"\u0000\u0000:<\u0005\u0012\u0000\u0000;:\u0001\u0000\u0000\u0000<=\u0001"+
		"\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000"+
		">b\u0001\u0000\u0000\u0000?b\u0005\'\u0000\u0000@A\u0005\u0010\u0000\u0000"+
		"AB\u0005\u0001\u0000\u0000BC\u0003\u0004\u0002\u0000CD\u0005\u0002\u0000"+
		"\u0000DG\u0003\u0002\u0001\u0000EF\u0005\u0011\u0000\u0000FH\u0003\u0002"+
		"\u0001\u0000GE\u0001\u0000\u0000\u0000GH\u0001\u0000\u0000\u0000Hb\u0001"+
		"\u0000\u0000\u0000IJ\u0005\u000e\u0000\u0000JK\u0005\u0001\u0000\u0000"+
		"KL\u0003\u0004\u0002\u0000LM\u0005\u0002\u0000\u0000MN\u0003\u0002\u0001"+
		"\u0000Nb\u0001\u0000\u0000\u0000OP\u0005\u000f\u0000\u0000PQ\u0005\u0001"+
		"\u0000\u0000QR\u0003\u0004\u0002\u0000RS\u0005\u0012\u0000\u0000ST\u0003"+
		"\u0004\u0002\u0000TU\u0005\u0012\u0000\u0000UV\u0003\u0004\u0002\u0000"+
		"VW\u0005\u0002\u0000\u0000WX\u0003\u0002\u0001\u0000Xb\u0001\u0000\u0000"+
		"\u0000Y[\u0005\u0003\u0000\u0000Z\\\u0003\u0002\u0001\u0000[Z\u0001\u0000"+
		"\u0000\u0000\\]\u0001\u0000\u0000\u0000][\u0001\u0000\u0000\u0000]^\u0001"+
		"\u0000\u0000\u0000^_\u0001\u0000\u0000\u0000_`\u0005\u0004\u0000\u0000"+
		"`b\u0001\u0000\u0000\u0000a\u000f\u0001\u0000\u0000\u0000a\u001d\u0001"+
		"\u0000\u0000\u0000a#\u0001\u0000\u0000\u0000a1\u0001\u0000\u0000\u0000"+
		"a?\u0001\u0000\u0000\u0000a@\u0001\u0000\u0000\u0000aI\u0001\u0000\u0000"+
		"\u0000aO\u0001\u0000\u0000\u0000aY\u0001\u0000\u0000\u0000b\u0003\u0001"+
		"\u0000\u0000\u0000cd\u0006\u0002\uffff\uffff\u0000de\u0005\u0017\u0000"+
		"\u0000eu\u0003\u0004\u0002\tfg\u0005\u001e\u0000\u0000gu\u0003\u0004\u0002"+
		"\bhu\u0005!\u0000\u0000iu\u0005%\u0000\u0000ju\u0005#\u0000\u0000ku\u0005"+
		"\"\u0000\u0000lu\u0005$\u0000\u0000mn\u0005\u0001\u0000\u0000no\u0003"+
		"\u0004\u0002\u0000op\u0005\u0002\u0000\u0000pu\u0001\u0000\u0000\u0000"+
		"qr\u0005#\u0000\u0000rs\u0005\u0007\u0000\u0000su\u0003\u0004\u0002\u0001"+
		"tc\u0001\u0000\u0000\u0000tf\u0001\u0000\u0000\u0000th\u0001\u0000\u0000"+
		"\u0000ti\u0001\u0000\u0000\u0000tj\u0001\u0000\u0000\u0000tk\u0001\u0000"+
		"\u0000\u0000tl\u0001\u0000\u0000\u0000tm\u0001\u0000\u0000\u0000tq\u0001"+
		"\u0000\u0000\u0000u\u008d\u0001\u0000\u0000\u0000vw\n\u000f\u0000\u0000"+
		"wx\u0007\u0000\u0000\u0000x\u008c\u0003\u0004\u0002\u0010yz\n\u000e\u0000"+
		"\u0000z{\u0007\u0001\u0000\u0000{\u008c\u0003\u0004\u0002\u000f|}\n\r"+
		"\u0000\u0000}~\u0007\u0002\u0000\u0000~\u008c\u0003\u0004\u0002\u000e"+
		"\u007f\u0080\n\f\u0000\u0000\u0080\u0081\u0005\u001c\u0000\u0000\u0081"+
		"\u008c\u0003\u0004\u0002\r\u0082\u0083\n\u000b\u0000\u0000\u0083\u0084"+
		"\u0005\u001d\u0000\u0000\u0084\u008c\u0003\u0004\u0002\f\u0085\u0086\n"+
		"\n\u0000\u0000\u0086\u0087\u0005\u0005\u0000\u0000\u0087\u0088\u0003\u0004"+
		"\u0002\u0000\u0088\u0089\u0005\u0006\u0000\u0000\u0089\u008a\u0003\u0004"+
		"\u0002\u000b\u008a\u008c\u0001\u0000\u0000\u0000\u008bv\u0001\u0000\u0000"+
		"\u0000\u008by\u0001\u0000\u0000\u0000\u008b|\u0001\u0000\u0000\u0000\u008b"+
		"\u007f\u0001\u0000\u0000\u0000\u008b\u0082\u0001\u0000\u0000\u0000\u008b"+
		"\u0085\u0001\u0000\u0000\u0000\u008c\u008f\u0001\u0000\u0000\u0000\u008d"+
		"\u008b\u0001\u0000\u0000\u0000\u008d\u008e\u0001\u0000\u0000\u0000\u008e"+
		"\u0005\u0001\u0000\u0000\u0000\u008f\u008d\u0001\u0000\u0000\u0000\u0090"+
		"\u0095\u0005\b\u0000\u0000\u0091\u0095\u0005\t\u0000\u0000\u0092\u0095"+
		"\u0005\u000b\u0000\u0000\u0093\u0095\u0005\n\u0000\u0000\u0094\u0090\u0001"+
		"\u0000\u0000\u0000\u0094\u0091\u0001\u0000\u0000\u0000\u0094\u0092\u0001"+
		"\u0000\u0000\u0000\u0094\u0093\u0001\u0000\u0000\u0000\u0095\u0007\u0001"+
		"\u0000\u0000\u0000\u000f\u000b\u0015\u001b!)/7=G]at\u008b\u008d\u0094";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}