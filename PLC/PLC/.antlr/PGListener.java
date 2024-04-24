// Generated from e:/code/PJP/project2VS/PLC/PLC/PG.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PGParser}.
 */
public interface PGListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PGParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(PGParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link PGParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(PGParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declarattion}
	 * labeled alternative in {@link PGParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterDeclarattion(PGParser.DeclarattionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declarattion}
	 * labeled alternative in {@link PGParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitDeclarattion(PGParser.DeclarattionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code emptyExpr}
	 * labeled alternative in {@link PGParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterEmptyExpr(PGParser.EmptyExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code emptyExpr}
	 * labeled alternative in {@link PGParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitEmptyExpr(PGParser.EmptyExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code writeExpr}
	 * labeled alternative in {@link PGParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterWriteExpr(PGParser.WriteExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code writeExpr}
	 * labeled alternative in {@link PGParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitWriteExpr(PGParser.WriteExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code readExpr}
	 * labeled alternative in {@link PGParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterReadExpr(PGParser.ReadExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code readExpr}
	 * labeled alternative in {@link PGParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitReadExpr(PGParser.ReadExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comment}
	 * labeled alternative in {@link PGParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterComment(PGParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comment}
	 * labeled alternative in {@link PGParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitComment(PGParser.CommentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifCon}
	 * labeled alternative in {@link PGParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterIfCon(PGParser.IfConContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifCon}
	 * labeled alternative in {@link PGParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitIfCon(PGParser.IfConContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileLoop}
	 * labeled alternative in {@link PGParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterWhileLoop(PGParser.WhileLoopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileLoop}
	 * labeled alternative in {@link PGParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitWhileLoop(PGParser.WhileLoopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forLoop}
	 * labeled alternative in {@link PGParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterForLoop(PGParser.ForLoopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forLoop}
	 * labeled alternative in {@link PGParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitForLoop(PGParser.ForLoopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blockStat}
	 * labeled alternative in {@link PGParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterBlockStat(PGParser.BlockStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockStat}
	 * labeled alternative in {@link PGParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitBlockStat(PGParser.BlockStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulDivMod}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDivMod(PGParser.MulDivModContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulDivMod}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDivMod(PGParser.MulDivModContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compare}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCompare(PGParser.CompareContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compare}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCompare(PGParser.CompareContext ctx);
	/**
	 * Enter a parse tree produced by the {@code uminus}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUminus(PGParser.UminusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code uminus}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUminus(PGParser.UminusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code string}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterString(PGParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code string}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitString(PGParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addSubConcat}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSubConcat(PGParser.AddSubConcatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addSubConcat}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSubConcat(PGParser.AddSubConcatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code float}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFloat(PGParser.FloatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code float}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFloat(PGParser.FloatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesis}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenthesis(PGParser.ParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesis}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenthesis(PGParser.ParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code int}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInt(PGParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code int}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInt(PGParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code not}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNot(PGParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code not}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNot(PGParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolean}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolean(PGParser.BooleanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolean}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolean(PGParser.BooleanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code id}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterId(PGParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code id}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitId(PGParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logic}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLogic(PGParser.LogicContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logic}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLogic(PGParser.LogicContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ternary}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterTernary(PGParser.TernaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ternary}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitTernary(PGParser.TernaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assign}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAssign(PGParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link PGParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAssign(PGParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link PGParser#ptype}.
	 * @param ctx the parse tree
	 */
	void enterPtype(PGParser.PtypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PGParser#ptype}.
	 * @param ctx the parse tree
	 */
	void exitPtype(PGParser.PtypeContext ctx);
}