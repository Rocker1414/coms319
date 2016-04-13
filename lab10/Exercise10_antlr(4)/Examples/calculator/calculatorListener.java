// Generated from calculator.g4 by ANTLR 4.5.2

    import java.util.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link calculatorParser}.
 */
public interface calculatorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link calculatorParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(calculatorParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(calculatorParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(calculatorParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(calculatorParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#operand}.
	 * @param ctx the parse tree
	 */
	void enterOperand(calculatorParser.OperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#operand}.
	 * @param ctx the parse tree
	 */
	void exitOperand(calculatorParser.OperandContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#booloperand}.
	 * @param ctx the parse tree
	 */
	void enterBooloperand(calculatorParser.BooloperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#booloperand}.
	 * @param ctx the parse tree
	 */
	void exitBooloperand(calculatorParser.BooloperandContext ctx);
	/**
	 * Enter a parse tree produced by {@link calculatorParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(calculatorParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link calculatorParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(calculatorParser.OperatorContext ctx);
}