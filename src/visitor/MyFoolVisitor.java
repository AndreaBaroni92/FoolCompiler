package visitor;

import java.util.ArrayList;
import org.antlr.v4.runtime.Token;

import ast.*;
import parser.FOOLBaseVisitor;
import parser.FOOLParser;
import parser.FOOLParser.AddminusContext;
import parser.FOOLParser.AndexpContext;
import parser.FOOLParser.ClassDefinitionContext;
import parser.FOOLParser.ClassdefContext;
import parser.FOOLParser.DecContext;
import parser.FOOLParser.ExpContext;
import parser.FOOLParser.FalsexpContext;
import parser.FOOLParser.FieldmodstmContext;
import parser.FOOLParser.FunContext;
import parser.FOOLParser.FuncallexpContext;
import parser.FOOLParser.IdexpContext;
import parser.FOOLParser.IfStmContext;
import parser.FOOLParser.IfexpContext;
import parser.FOOLParser.IntexpContext;
import parser.FOOLParser.LetInExpContext;
import parser.FOOLParser.MetcallContext;
import parser.FOOLParser.MinexpContext;
import parser.FOOLParser.NotexpContext;
import parser.FOOLParser.NullexpContext;
import parser.FOOLParser.ObjinstContext;
import parser.FOOLParser.ParexpContext;
import parser.FOOLParser.PrintStmContext;
import parser.FOOLParser.SingleExpContext;
import parser.FOOLParser.StmAssignmentContext;
import parser.FOOLParser.StmContext;
import parser.FOOLParser.StmsContext;
import parser.FOOLParser.TestopContext;
import parser.FOOLParser.TimedivContext;
import parser.FOOLParser.TruexpContext;
import parser.FOOLParser.TypeContext;
import parser.FOOLParser.VarasmContext;
import parser.FOOLParser.VardecContext;
import parser.FOOLParser.VoidexpContext;
import type.ClassType;

public class MyFoolVisitor extends FOOLBaseVisitor<Node> {

	@Override
	public Node visitFieldmodstm(FieldmodstmContext ctx) {
		
		Node exp = visit(ctx.exp());
		
		return new FieldMod(ctx,exp);
	}

	@Override
	public Node visitMetcall(MetcallContext ctx) {
		ArrayList<Node> actualParam = new ArrayList<>();// lista eventualmente vuota di parametri attuali

		for (ExpContext n : ctx.exp()) {
			actualParam.add(visit(n));
		}

		return new MethodCallNode(ctx, actualParam);
	}

	@Override
	public Node visitObjinst(ObjinstContext ctx) {

		ArrayList<Node> actualParam = new ArrayList<>();

		String nameOfClass = ctx.nameClass.getText();

		if (ctx.exp().isEmpty() == false) {

			for (ExpContext n : ctx.exp()) {
				actualParam.add(visit(n));
			}

		}

		return new NewNode(actualParam, nameOfClass, ctx);
	}

	@Override
	public Node visitClassDefinition(ClassDefinitionContext ctx) {

		ArrayList<ClassNode> listOfClass = new ArrayList<>();

		ArrayList<Node> declaration = new ArrayList<>();

		LetNode let;

		Node exp = null; // nel caso in cui nel body fosse presente un espressione al posto di statement

		ArrayList<Node> stm = new ArrayList<>();

		for (ClassdefContext n : ctx.classdef()) {
			listOfClass.add((ClassNode) visit(n));
		}

		if (ctx.let() != null) {
			for (DecContext n : ctx.let().dec()) {
				declaration.add(visit(n));
			}
		}

		let = new LetNode(declaration);

		if (ctx.exp() != null) {
			exp = visit(ctx.exp());
		}

		else {
			for (StmContext n : ctx.stms().stm()) {
				stm.add(visit(n));
			}
		}

		return new ClassProgNode(listOfClass, let, exp, stm);
	}

	@Override
	public Node visitClassdef(ClassdefContext ctx) {

		Token classTok = ctx.son;

		String parent = null;

		if (ctx.parent != null) {
			parent = ctx.parent.getText();
		}

		ArrayList<FPNode> fields = new ArrayList<>();
		/*
		 * aggiungo all' array fields tutti i campi della classe
		 */
		if (ctx.args() != null) {
			for (VardecContext n : ctx.args().vardec()) {
				fields.add(new FPNode(n, (TypeNode) visit(n.type()), true));
			}
		}

		ArrayList<MethodNode> methodNodes = new ArrayList<>();

		if (ctx.fun() != null) {
			for (FunContext n : ctx.fun()) {

				FunctionNode f = (FunctionNode) visitFun(n);

				MethodNode m = new MethodNode(f);
				methodNodes.add(m);

			}
		}

		ClassType ct = new ClassType(classTok.getText(), classTok);

		return new ClassNode(ct, parent, ctx, fields, methodNodes);
	}

	@Override
	public Node visitPrintStm(PrintStmContext ctx) {

		Node exp = visit(ctx.exp());

		return new PrintNode(ctx, exp);
	}

	@Override
	public Node visitParexp(ParexpContext ctx) {// per gestire il caso ( exp) parentesi
		Node exp = visit(ctx.exp());
		return new ParNode(ctx, exp);

	}

	@Override
	public Node visitNotexp(NotexpContext ctx) {// per gestire il not o ! unario , analogo al caso del segno -

		Node exp = visit(ctx.exp());

		return new NotNode(ctx, exp);
	}

	@Override
	public Node visitIfexp(IfexpContext ctx) {

		Node exp = visit(ctx.cond);
		Node thenExp = visit(ctx.thenBranch);
		Node elseExp = visit(ctx.elseBranch);

		return new IfExpNode(ctx, exp, thenExp, elseExp);
	}

	@Override
	public Node visitIfStm(IfStmContext ctx) {

		Node exp = visit(ctx.conds);

		ArrayList<Node> thenStms = new ArrayList<>();
		ArrayList<Node> elseStms = new ArrayList<>();

		/*
		 * visito nel ramo then tutti gli statements in modo analogo a quanto si fara'
		 * nel ramo else
		 */
		for (StmContext s : ctx.thenBranchs.stm()) {
			thenStms.add(visit(s));
		}

		for (StmContext s : ctx.elseBranchs.stm()) {
			elseStms.add(visit(s));
		}

		return new IfStmsNode(ctx, exp, thenStms, elseStms);
	}

	@Override
	public Node visitAndexp(AndexpContext ctx) {

		Node left = visit(ctx.left);

		Node right = visit(ctx.right);

		return new AndNode(ctx, left, right);
	}

	@Override
	public Node visitMinexp(MinexpContext ctx) {

		Node exp = visit(ctx.exp());

		return new MinNode(exp, ctx);
	}

	@Override
	public Node visitFuncallexp(FuncallexpContext ctx) { // per trattare la chimata di funzione

		ArrayList<Node> p = new ArrayList<>();// lista eventualmente vuota di parametri attuali

		if (ctx.exp() != null) {

			for (ExpContext e : ctx.exp()) {

				p.add(visit(e));

			}

		}

		return new CallNode(ctx.ID().getSymbol(), p, ctx);
	}

	@Override
	public Node visitTruexp(TruexpContext ctx) {

		return new BTNode();
	}

	@Override
	public Node visitFalsexp(FalsexpContext ctx) {

		return new BFNode();
	}

	@Override
	public Node visitVoidexp(VoidexpContext ctx) {

		return new VoidNode();
	}

	@Override
	public Node visitSingleExp(SingleExpContext ctx) {

		Node exp = visit(ctx.exp());

		return new SingleExpNode(exp);
	}

	@Override
	public Node visitStms(StmsContext ctx) {

		return super.visitStms(ctx);
	}

	@Override
	public Node visitIdexp(IdexpContext ctx) {
		return (new IdNode(ctx));
	}

	@Override
	public Node visitStmAssignment(StmAssignmentContext ctx) {

		Node exp = visit(ctx.exp());

		return new AssignmentNode(ctx, exp);

	}
	/*
	 * non impiego piu questa funzione gestisco i parametri formali direttamentro
	 * dentro un array
	 * 
	 * @Override public Node visitArgs(ArgsContext ctx) {
	 * 
	 * ArrayList<Node> ris = new ArrayList<>();
	 * 
	 * if (ctx.vardec() != null) {
	 * 
	 * for (VardecContext dec : ctx.vardec()) {
	 * 
	 * Node par = visitVardec(dec);
	 * 
	 * ris.add(par); }
	 * 
	 * } return new ArgsNode(ris);
	 * 
	 * }
	 */

	@Override
	public Node visitFun(FunContext ctx) {

		// ArgsNode formalArguments = new ArgsNode(new ArrayList<>());

		ArrayList<Node> formalArguments = new ArrayList<>();

		Node exp = null;

		ArrayList<Node> statements = null;

		if (ctx.args() != null) {

			for (VardecContext n : ctx.args().vardec()) {
				formalArguments.add(visit(n));
			}

			// formalArguments = (ArgsNode) visitArgs(ctx.args());
		}

		TypeNode type = (TypeNode) visitType(ctx.type());
		// IType retType = ((TypeNode) type).getType();
		ArrayList<Node> declarations = new ArrayList<>(); // dichiarazioni all'interno della funzione
															// iniziano con let ...

		if (ctx.fbody().letfun() != null) {// non e' possibile dichiarare funzioni all' interno di altre funzioni
			for (VarasmContext vc : ctx.fbody().letfun().varasm()) {
				Node param = visitVarasm(vc);
				declarations.add(param);
			}

		}

		// visito solo le espressioni se ci sono

		if ((ctx.fbody().exp()) != null) {

			exp = visit(ctx.fbody().exp());

		}

		// altrimenti visito gli statements

		else {

			statements = new ArrayList<>();

			for (StmContext s : ctx.fbody().stms().stm()) {

				statements.add(visit(s));

			}

		}

		return new FunctionNode(formalArguments, exp, statements, ctx, declarations, type);
	}

	@Override
	public Node visitTestop(TestopContext ctx) {

		Node left = visit(ctx.left);

		Node right = visit(ctx.right);

		switch (ctx.op.getType()) {

		case FOOLParser.GEQ:
			return new GeqNode(ctx, left, right);

		case FOOLParser.OR:
			return new OrNode(ctx, left, right);

		case FOOLParser.LEQ:
			return new LeqNode(ctx, left, right);

		case FOOLParser.EQ:
			return new EqNode(ctx, left, right);

		default:// non si avrà questo caso in quanto antlr lo evitera'
			return null;

		}

	}

	@Override
	public Node visitAddminus(AddminusContext ctx) {

		Node left = visit(ctx.left);

		Node right = visit(ctx.right);

		if (ctx.op.getType() == FOOLParser.PLUS) {

			return new PlusNode(ctx, left, right);
		}

		else {
			return new MinusNode(ctx, left, right);
		}

	}

	@Override
	public Node visitTimediv(TimedivContext ctx) {

		Node left = visit(ctx.left);

		Node right = visit(ctx.right);

		if (ctx.op.getType() == FOOLParser.TIMES) {

			return new TimesNode(ctx, left, right);
		}

		else {
			return new DivNode(ctx, left, right);
		}

	}

	@Override
	public Node visitLetInExp(LetInExpContext ctx) {

		ProgLetInNode result;

		ArrayList<Node> declarations = new ArrayList<Node>();

		Node exp = null; // espressione

		ArrayList<Node> stm = null; // possibili statement

		LetNode let;

		for (DecContext dc : ctx.let().dec()) {

			declarations.add(visit(dc));

		}

		if (ctx.exp() != null) {
			exp = visit(ctx.exp());
		}

		else {
			stm = new ArrayList<>();

			for (StmContext s : ctx.stms().stm()) {

				stm.add(visit(s));

			}
		}
		let = new LetNode(declarations);

		result = new ProgLetInNode(let, exp, stm);

		return result;
	}

	@Override
	public Node visitVardec(VardecContext ctx) {

		TypeNode type = (TypeNode) visitType(ctx.type());

		// IType realType = type.getType();

		return new FPNode(ctx, type);
	}

	@Override
	public Node visitVarasm(VarasmContext ctx) { // es: int i = espressione
		VarAsmNode result;
		TypeNode type = (TypeNode) visit(ctx.vardec().type());

		// String name = ctx.vardec().ID().getText();

		Node expression = null;

		if (ctx.exp() != null) {
			expression = visit(ctx.exp());
		}

		result = new VarAsmNode(type, expression, ctx);

		return result;

	}

	@Override
	public Node visitType(TypeContext ctx) {

		return new TypeNode(ctx.getText(), ctx);

	}

	@Override
	public Node visitIntexp(IntexpContext ctx) {

		return new IntNode(Integer.parseInt(ctx.INTEGER().getText()), ctx);

	}
	@Override
	public Node visitNullexp(NullexpContext ctx) {
		
		return new NullNode();
	}

}
