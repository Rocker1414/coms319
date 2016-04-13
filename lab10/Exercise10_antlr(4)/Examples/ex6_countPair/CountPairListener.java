// Generated from CountPair.g4 by ANTLR 4.5.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CountPairParser}.
 */
public interface CountPairListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CountPairParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(CountPairParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link CountPairParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(CountPairParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link CountPairParser#pair}.
	 * @param ctx the parse tree
	 */
	void enterPair(CountPairParser.PairContext ctx);
	/**
	 * Exit a parse tree produced by {@link CountPairParser#pair}.
	 * @param ctx the parse tree
	 */
	void exitPair(CountPairParser.PairContext ctx);
	/**
	 * Enter a parse tree produced by {@link CountPairParser#word}.
	 * @param ctx the parse tree
	 */
	void enterWord(CountPairParser.WordContext ctx);
	/**
	 * Exit a parse tree produced by {@link CountPairParser#word}.
	 * @param ctx the parse tree
	 */
	void exitWord(CountPairParser.WordContext ctx);
	/**
	 * Enter a parse tree produced by {@link CountPairParser#integer}.
	 * @param ctx the parse tree
	 */
	void enterInteger(CountPairParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CountPairParser#integer}.
	 * @param ctx the parse tree
	 */
	void exitInteger(CountPairParser.IntegerContext ctx);
}