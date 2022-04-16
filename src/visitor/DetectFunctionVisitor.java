package visitor;

import java.util.ArrayList;

import ast.Node;
import ast.TypeNode;
import exception.RedeclaredFunction;
import parser.FOOLBaseVisitor;
import parser.FOOLParser;
import parser.FOOLParser.ArgsContext;
import parser.FOOLParser.ClassDefinitionContext;
import parser.FOOLParser.ClassdefContext;
import parser.FOOLParser.FunContext;
import parser.FOOLParser.TypeContext;
import parser.FOOLParser.VarasmContext;
import parser.FOOLParser.VardecContext;
import type.FunctionType;
import type.IType;
import util.Environment;
import util.SemanticError;

public class DetectFunctionVisitor extends FOOLBaseVisitor<Node> {

	private Environment env = new Environment();
	private ArrayList<SemanticError> err = new ArrayList<>();

	@Override
	public Node visitClassdef(ClassdefContext ctx) {// le funzioni all'interno di una classe non devono essere
													// conteggiate

		return null;
	}

	@Override
	public Node visitVardec(VardecContext ctx) {

		return visitType(ctx.type());
	}

	public DetectFunctionVisitor() {
		env.setOffset(-2);
	}

	@Override
	public Node visitVarasm(VarasmContext ctx) {

		env.decOffset();

		return null;
	}

	@Override
	public Node visitType(TypeContext ctx) {

		return new TypeNode(ctx.getText(), ctx);
	}

	@Override
	public Node visitFun(FunContext ctx) {

		TypeNode type = (TypeNode) visit(ctx.type());

		IType retType = type.getType(); // tipo di ritorno della funzione

		ArrayList<IType> paramsType = new ArrayList<>();

		if (ctx.args() != null) {
			for (VardecContext formalparam : ctx.args().vardec()) {

				TypeNode param = (TypeNode) visit(formalparam);

				paramsType.add(param.getType());

			}

		}

		FunctionType funType = new FunctionType(retType, paramsType);

		// aggiungere il fatto di mettere la funzione dentro Enviroment e gestire le
		// eccezioni

		try {

			if (this.env.isEmpty()) {
				this.env.addHashTable();
			}

			env.addEntry(ctx.ID().getSymbol(), funType, env.decOffset());
		}

		catch (RedeclaredFunction e) {

			err.add(new SemanticError(e.msg()));

		}

		return type;

	}

	public Environment getEnv() {

		return this.env;
	}

	public ArrayList<SemanticError> getErrors() {

		return this.err;

	}

}
