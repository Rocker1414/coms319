// Generated from SimpleBoolean.g4 by ANTLR 4.5.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SimpleBooleanParser}.
 */
public interface SimpleBooleanListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SimpleBooleanParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(SimpleBooleanParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleBooleanParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(SimpleBooleanParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleBooleanParser#andexpression}.
	 * @param ctx the parse tree
	 */
	void enterAndexpression(SimpleBooleanParser.AndexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleBooleanParser#andexpression}.
	 * @param ctx the parse tree
	 */
	void exitAndexpression(SimpleBooleanParser.AndexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleBooleanParser#orexpression}.
	 * @param ctx the parse tree
	 */
	void enterOrexpression(SimpleBooleanParser.OrexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleBooleanParser#orexpression}.
	 * @param ctx the parse tree
	 */
	void exitOrexpression(SimpleBooleanParser.OrexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleBooleanParser#notexpression}.
	 * @param ctx the parse tree
	 */
	void enterNotexpression(SimpleBooleanParser.NotexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleBooleanParser#notexpression}.
	 * @param ctx the parse tree
	 */
	void exitNotexpression(SimpleBooleanParser.NotexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleBooleanParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(SimpleBooleanParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleBooleanParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(SimpleBooleanParser.AtomContext ctx);
}