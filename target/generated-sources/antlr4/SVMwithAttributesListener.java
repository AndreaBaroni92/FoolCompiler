// Generated from SVMwithAttributes.g4 by ANTLR 4.7.1

import java.util.HashMap;
import virtualMachine.ExecuteVM;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SVMwithAttributesParser}.
 */
public interface SVMwithAttributesListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SVMwithAttributesParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterAssembly(SVMwithAttributesParser.AssemblyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMwithAttributesParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitAssembly(SVMwithAttributesParser.AssemblyContext ctx);
}