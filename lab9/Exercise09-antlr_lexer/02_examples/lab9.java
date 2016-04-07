// Generated from lab9.g4 by ANTLR 4.5.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class lab9 extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OPENELEMENT=1, CLOSEELEMENT=2, EMAIL=3, DATE=4, WS=5;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"LETTER", "DIGIT", "OPENELEMENT", "CLOSEELEMENT", "ELSTART", "ELCHAR", 
		"EMAIL", "LOCAL", "DOMAIN", "DATE", "DAY", "MONTH", "YEAR", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "OPENELEMENT", "CLOSEELEMENT", "EMAIL", "DATE", "WS"
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


	public lab9(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "lab9.g4"; }

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
		case 2:
			OPENELEMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 3:
			CLOSEELEMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 6:
			EMAIL_action((RuleContext)_localctx, actionIndex);
			break;
		case 9:
			DATE_action((RuleContext)_localctx, actionIndex);
			break;
		case 13:
			WS_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void OPENELEMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:

				System.out.println("Opening Element Tag: " + getText());

			break;
		}
	}
	private void CLOSEELEMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:

				System.out.println("Closing Element Tag: " + getText());

			break;
		}
	}
	private void EMAIL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:

				System.out.println("Email: " + getText());

			break;
		}
	}
	private void DATE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:

				System.out.println("Date: " + getText());

			break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4:
			skip();
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\7\u0085\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\3\3\3\3\4\3\4\3\4"+
		"\6\4\'\n\4\r\4\16\4(\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\6\5\63\n\5\r\5\16"+
		"\5\64\3\5\3\5\3\5\3\6\3\6\5\6<\n\6\3\7\3\7\3\7\5\7A\n\7\3\b\3\b\6\bE\n"+
		"\b\r\b\16\bF\3\b\3\b\6\bK\n\b\r\b\16\bL\3\b\3\b\3\t\3\t\3\t\5\tT\n\t\3"+
		"\n\3\n\3\n\5\nY\n\n\3\13\3\13\3\13\6\13^\n\13\r\13\16\13_\3\13\3\13\6"+
		"\13d\n\13\r\13\16\13e\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\fr\n"+
		"\f\3\r\3\r\3\r\3\r\5\rx\n\r\3\16\3\16\3\16\3\16\3\16\3\17\6\17\u0080\n"+
		"\17\r\17\16\17\u0081\3\17\3\17\2\2\20\3\2\5\2\7\3\t\4\13\2\r\2\17\5\21"+
		"\2\23\2\25\6\27\2\31\2\33\2\35\7\3\2\7\4\2C\\c|\3\2/\60\3\2\60\60\t\2"+
		"##&&(\60<=??aa\u0080\u0080\4\2\13\f\17\17\u008d\2\7\3\2\2\2\2\t\3\2\2"+
		"\2\2\17\3\2\2\2\2\25\3\2\2\2\2\35\3\2\2\2\3\37\3\2\2\2\5!\3\2\2\2\7#\3"+
		"\2\2\2\t-\3\2\2\2\13;\3\2\2\2\r@\3\2\2\2\17B\3\2\2\2\21S\3\2\2\2\23X\3"+
		"\2\2\2\25Z\3\2\2\2\27q\3\2\2\2\31w\3\2\2\2\33y\3\2\2\2\35\177\3\2\2\2"+
		"\37 \t\2\2\2 \4\3\2\2\2!\"\4\62;\2\"\6\3\2\2\2#$\7>\2\2$&\5\13\6\2%\'"+
		"\5\r\7\2&%\3\2\2\2\'(\3\2\2\2(&\3\2\2\2()\3\2\2\2)*\3\2\2\2*+\7@\2\2+"+
		",\b\4\2\2,\b\3\2\2\2-.\7>\2\2./\7\61\2\2/\60\3\2\2\2\60\62\5\13\6\2\61"+
		"\63\5\r\7\2\62\61\3\2\2\2\63\64\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65"+
		"\66\3\2\2\2\66\67\7@\2\2\678\b\5\3\28\n\3\2\2\29<\5\3\2\2:<\7a\2\2;9\3"+
		"\2\2\2;:\3\2\2\2<\f\3\2\2\2=A\5\13\6\2>A\5\5\3\2?A\t\3\2\2@=\3\2\2\2@"+
		">\3\2\2\2@?\3\2\2\2A\16\3\2\2\2BD\n\4\2\2CE\5\21\t\2DC\3\2\2\2EF\3\2\2"+
		"\2FD\3\2\2\2FG\3\2\2\2GH\3\2\2\2HJ\7B\2\2IK\5\23\n\2JI\3\2\2\2KL\3\2\2"+
		"\2LJ\3\2\2\2LM\3\2\2\2MN\3\2\2\2NO\b\b\4\2O\20\3\2\2\2PT\5\3\2\2QT\5\5"+
		"\3\2RT\t\5\2\2SP\3\2\2\2SQ\3\2\2\2SR\3\2\2\2T\22\3\2\2\2UY\5\3\2\2VY\5"+
		"\5\3\2WY\t\3\2\2XU\3\2\2\2XV\3\2\2\2XW\3\2\2\2Y\24\3\2\2\2Z[\5\27\f\2"+
		"[]\7\61\2\2\\^\5\31\r\2]\\\3\2\2\2^_\3\2\2\2_]\3\2\2\2_`\3\2\2\2`a\3\2"+
		"\2\2ac\7\61\2\2bd\5\33\16\2cb\3\2\2\2de\3\2\2\2ec\3\2\2\2ef\3\2\2\2fg"+
		"\3\2\2\2gh\b\13\5\2h\26\3\2\2\2ij\7\62\2\2jr\4\63;\2kl\7\63\2\2lr\4\62"+
		";\2mn\7\64\2\2nr\4\62;\2op\7\65\2\2pr\4\62\63\2qi\3\2\2\2qk\3\2\2\2qm"+
		"\3\2\2\2qo\3\2\2\2r\30\3\2\2\2st\7\62\2\2tx\4\63;\2uv\7\63\2\2vx\4\62"+
		"\64\2ws\3\2\2\2wu\3\2\2\2x\32\3\2\2\2yz\7\64\2\2z{\4\62;\2{|\4\62;\2|"+
		"}\4\62;\2}\34\3\2\2\2~\u0080\t\6\2\2\177~\3\2\2\2\u0080\u0081\3\2\2\2"+
		"\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0084"+
		"\b\17\6\2\u0084\36\3\2\2\2\20\2(\64;@FLSX_eqw\u0081\7\3\4\2\3\5\3\3\b"+
		"\4\3\13\5\3\17\6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}