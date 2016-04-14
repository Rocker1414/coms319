// Generated from simple.g4 by ANTLR 4.5.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link simpleParser}.
 */
public interface simpleListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link simpleParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(simpleParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(simpleParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(simpleParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(simpleParser.StatContext ctx);
}