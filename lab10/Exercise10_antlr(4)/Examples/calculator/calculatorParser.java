// Generated from calculator.g4 by ANTLR 4.5.2

    import java.util.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class calculatorParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, OPERATOR=2, ADD=3, SUBTRACT=4, MULTIPLY=5, DIVIDE=6, MOD=7, INT=8, 
		BOOL=9, LT=10, LTEQ=11, EQ=12, NEQ=13, GT=14, GTEQ=15, AND=16, OR=17, 
		NOT=18, WS=19;
	public static final int
		RULE_start = 0, RULE_expr = 1, RULE_operand = 2, RULE_booloperand = 3, 
		RULE_operator = 4;
	public static final String[] ruleNames = {
		"start", "expr", "operand", "booloperand", "operator"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", null, "'+'", null, "'*'", null, "'%'", null, null, "'<'", 
		null, null, null, "'>'", null, null, null, "'!'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, "OPERATOR", "ADD", "SUBTRACT", "MULTIPLY", "DIVIDE", "MOD", 
		"INT", "BOOL", "LT", "LTEQ", "EQ", "NEQ", "GT", "GTEQ", "AND", "OR", "NOT", 
		"WS"
	};
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
	public String getGrammarFileName() { return "calculator.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	  
	 	Stack<Integer> s = new Stack<Integer>();
		Stack<Boolean> s2 = new Stack<Boolean>();
		int a = 0;
		int b = 0;
		boolean boolA = false;
		boolean boolB = false;
		boolean exit = false;
		String op = "";
		String msg = "";
		int count=0;

	public calculatorParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(11); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(10);
				expr();
				}
				}
				setState(13); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPERATOR) | (1L << INT) | (1L << BOOL))) != 0) );

					s = new Stack<Integer>();
					s2 = new Stack<Boolean>();
					a = 0;
					b = 0;
					boolA = false;
					boolB = false;
					exit = false;
					op = "";
					count = 1;
					
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

	public static class ExprContext extends ParserRuleContext {
		public List<OperandContext> operand() {
			return getRuleContexts(OperandContext.class);
		}
		public OperandContext operand(int i) {
			return getRuleContext(OperandContext.class,i);
		}
		public List<BooloperandContext> booloperand() {
			return getRuleContexts(BooloperandContext.class);
		}
		public BooloperandContext booloperand(int i) {
			return getRuleContext(BooloperandContext.class,i);
		}
		public List<OperatorContext> operator() {
			return getRuleContexts(OperatorContext.class);
		}
		public OperatorContext operator(int i) {
			return getRuleContext(OperatorContext.class,i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(20);
				switch (_input.LA(1)) {
				case INT:
					{
					setState(17);
					operand();
					}
					break;
				case BOOL:
					{
					setState(18);
					booloperand();
					}
					break;
				case OPERATOR:
					{
					setState(19);
					operator();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(22); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPERATOR) | (1L << INT) | (1L << BOOL))) != 0) );
			setState(24);
			match(T__0);

					if(!s.empty() && s2.empty()) msg = "Expression " + ++count + ": " + s.pop();
					else if (!s2.empty() && s.empty()) msg = "Expression " + ++count + ": " + s2.pop();
					else {msg = "Error: Unknown syntax error in expression " + ++count + ". Exiting."; exit = true;}
					if(!s.empty() || !s2.empty()) {msg = "Error: Too many operands in expression " + count + ". Exiting."; exit = true;}												 
					System.out.println(msg);
					if(exit) System.exit(0);
					
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

	public static class OperandContext extends ParserRuleContext {
		public Token INT;
		public TerminalNode INT() { return getToken(calculatorParser.INT, 0); }
		public OperandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterOperand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitOperand(this);
		}
	}

	public final OperandContext operand() throws RecognitionException {
		OperandContext _localctx = new OperandContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_operand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			((OperandContext)_localctx).INT = match(INT);
			s.push((((OperandContext)_localctx).INT!=null?Integer.valueOf(((OperandContext)_localctx).INT.getText()):0));
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

	public static class BooloperandContext extends ParserRuleContext {
		public Token BOOL;
		public TerminalNode BOOL() { return getToken(calculatorParser.BOOL, 0); }
		public BooloperandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booloperand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterBooloperand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitBooloperand(this);
		}
	}

	public final BooloperandContext booloperand() throws RecognitionException {
		BooloperandContext _localctx = new BooloperandContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_booloperand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			((BooloperandContext)_localctx).BOOL = match(BOOL);
			s2.push(Boolean.valueOf((((BooloperandContext)_localctx).BOOL!=null?((BooloperandContext)_localctx).BOOL.getText():null)));
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

	public static class OperatorContext extends ParserRuleContext {
		public Token OPERATOR;
		public TerminalNode OPERATOR() { return getToken(calculatorParser.OPERATOR, 0); }
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof calculatorListener ) ((calculatorListener)listener).exitOperator(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_operator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			((OperatorContext)_localctx).OPERATOR = match(OPERATOR);

				
				op = (((OperatorContext)_localctx).OPERATOR!=null?((OperatorContext)_localctx).OPERATOR.getText():null);
				
				switch(op){
					case "+":
						b= s.pop();
						a = s.pop();
						s.push(a+b);
						break;
					case "-":
						b= s.pop();
						a = s.pop();
						s.push(a-b);
						break;
					case "*":
						b= s.pop();
						a = s.pop();	
						s.push(a*b);
						break;
					case "/":
						b= s.pop();
						a = s.pop();
						s.push(a/b);
						break;
					case "%":
						b= s.pop();
						a = s.pop();
						s.push(a%b);
						break;
					case("<"):
						b= s.pop();
						a = s.pop();
						s2.push(a<b);
						break;
					case("<="):
						b= s.pop();
						a = s.pop();
						s2.push(a<=b);
						break;
					case("=="):
						b= s.pop();
						a = s.pop();
						s2.push(a==b);
						break;
					case("!="):
						b= s.pop();
						a = s.pop();
						s2.push(a!=b);
						break;
					case(">"):
						b= s.pop();
						a = s.pop();
						s2.push(a>b);
						break;
					case(">="):
						b = s.pop();
						a = s.pop();
						s2.push(a>=b);
						break;
					case "&&":
						boolB= s2.pop();
						boolA = s2.pop();
						s2.push(boolA&&boolB);
						break;
					case "||":
						boolB= s2.pop();
						boolA = s2.pop();
						s2.push(boolA||boolB);
						break;
					case("!"):
						boolB= s2.pop();
						boolA = s2.pop();
						s2.push(!(boolA||boolB));
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\25\'\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\6\2\16\n\2\r\2\16\2\17\3\2\3\2\3\3\3"+
		"\3\3\3\6\3\27\n\3\r\3\16\3\30\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\6"+
		"\3\6\3\6\3\6\2\2\7\2\4\6\b\n\2\2%\2\r\3\2\2\2\4\26\3\2\2\2\6\35\3\2\2"+
		"\2\b \3\2\2\2\n#\3\2\2\2\f\16\5\4\3\2\r\f\3\2\2\2\16\17\3\2\2\2\17\r\3"+
		"\2\2\2\17\20\3\2\2\2\20\21\3\2\2\2\21\22\b\2\1\2\22\3\3\2\2\2\23\27\5"+
		"\6\4\2\24\27\5\b\5\2\25\27\5\n\6\2\26\23\3\2\2\2\26\24\3\2\2\2\26\25\3"+
		"\2\2\2\27\30\3\2\2\2\30\26\3\2\2\2\30\31\3\2\2\2\31\32\3\2\2\2\32\33\7"+
		"\3\2\2\33\34\b\3\1\2\34\5\3\2\2\2\35\36\7\n\2\2\36\37\b\4\1\2\37\7\3\2"+
		"\2\2 !\7\13\2\2!\"\b\5\1\2\"\t\3\2\2\2#$\7\4\2\2$%\b\6\1\2%\13\3\2\2\2"+
		"\5\17\26\30";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}