// Generated from calculator.g4 by ANTLR 4.5.2

    import java.util.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class calculatorLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, OPERATOR=2, ADD=3, SUBTRACT=4, MULTIPLY=5, DIVIDE=6, MOD=7, INT=8, 
		BOOL=9, LT=10, LTEQ=11, EQ=12, NEQ=13, GT=14, GTEQ=15, AND=16, OR=17, 
		NOT=18, WS=19;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "OPERATOR", "ADD", "SUBTRACT", "MULTIPLY", "DIVIDE", "MOD", "INT", 
		"BOOL", "LT", "LTEQ", "EQ", "NEQ", "GT", "GTEQ", "AND", "OR", "NOT", "WS"
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


	public calculatorLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "calculator.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 18:
			WS_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			skip();
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\25t\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\5\3:\n\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\6"+
		"\tG\n\t\r\t\16\tH\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\nT\n\n\3\13\3"+
		"\13\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3"+
		"\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\24\6\24o\n\24\r\24\16\24p\3\24"+
		"\3\24\2\2\25\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\20\37\21!\22#\23%\24\'\25\3\2\3\5\2\13\f\17\17\"\"\u0083\2\3"+
		"\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2"+
		"\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31"+
		"\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2"+
		"\2%\3\2\2\2\2\'\3\2\2\2\3)\3\2\2\2\59\3\2\2\2\7;\3\2\2\2\t=\3\2\2\2\13"+
		"?\3\2\2\2\rA\3\2\2\2\17C\3\2\2\2\21F\3\2\2\2\23S\3\2\2\2\25U\3\2\2\2\27"+
		"W\3\2\2\2\31Z\3\2\2\2\33]\3\2\2\2\35`\3\2\2\2\37b\3\2\2\2!e\3\2\2\2#h"+
		"\3\2\2\2%k\3\2\2\2\'n\3\2\2\2)*\7=\2\2*\4\3\2\2\2+:\5\7\4\2,:\5\t\5\2"+
		"-:\5\13\6\2.:\5\r\7\2/:\5\17\b\2\60:\5\25\13\2\61:\5\27\f\2\62:\5\31\r"+
		"\2\63:\5\33\16\2\64:\5\37\20\2\65:\5\35\17\2\66:\5!\21\2\67:\5#\22\28"+
		":\5%\23\29+\3\2\2\29,\3\2\2\29-\3\2\2\29.\3\2\2\29/\3\2\2\29\60\3\2\2"+
		"\29\61\3\2\2\29\62\3\2\2\29\63\3\2\2\29\64\3\2\2\29\65\3\2\2\29\66\3\2"+
		"\2\29\67\3\2\2\298\3\2\2\2:\6\3\2\2\2;<\7-\2\2<\b\3\2\2\2=>\7/\2\2>\n"+
		"\3\2\2\2?@\7,\2\2@\f\3\2\2\2AB\7/\2\2B\16\3\2\2\2CD\7\'\2\2D\20\3\2\2"+
		"\2EG\4\62;\2FE\3\2\2\2GH\3\2\2\2HF\3\2\2\2HI\3\2\2\2I\22\3\2\2\2JK\7v"+
		"\2\2KL\7t\2\2LM\7w\2\2MT\7g\2\2NO\7h\2\2OP\7c\2\2PQ\7n\2\2QR\7u\2\2RT"+
		"\7g\2\2SJ\3\2\2\2SN\3\2\2\2T\24\3\2\2\2UV\7>\2\2V\26\3\2\2\2WX\7>\2\2"+
		"XY\7?\2\2Y\30\3\2\2\2Z[\7?\2\2[\\\7?\2\2\\\32\3\2\2\2]^\7#\2\2^_\7?\2"+
		"\2_\34\3\2\2\2`a\7@\2\2a\36\3\2\2\2bc\7@\2\2cd\7?\2\2d \3\2\2\2ef\7(\2"+
		"\2fg\7(\2\2g\"\3\2\2\2hi\7~\2\2ij\7~\2\2j$\3\2\2\2kl\7#\2\2l&\3\2\2\2"+
		"mo\t\2\2\2nm\3\2\2\2op\3\2\2\2pn\3\2\2\2pq\3\2\2\2qr\3\2\2\2rs\b\24\2"+
		"\2s(\3\2\2\2\7\29HSp\3\3\24\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}