package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import ast.Node;
import errorlistener.UnderlineListener;
import exception.LexicalException;
import exception.SVMException;
import exception.SemanticException;
import exception.TypeException;
import parser.FOOLLexer;
import parser.FOOLParser;
import parser.FOOLVisitor;
import parser.SVMLexer;
import parser.SVMParser;
import type.IType;
import util.ConcatSemanticErrorMsgs;
import util.Environment;
import util.SemanticError;
import virtualMachine.ExecuteVM;
import visitor.DetectFunctionVisitor;
import visitor.MyFoolVisitor;
import visitor.MySvmVisitor;

public class Main {

	public static void main(String[] args) {
		String input = "code.fool";
		try {
			FOOLLexer lexer = new FOOLLexer(CharStreams.fromFileName(input));
			Environment env = new Environment();
			CommonTokenStream token = new CommonTokenStream(lexer);
			FOOLParser parser = new FOOLParser(token);
			parser.removeErrorListeners(); // remove ConsoleErrorListener
			parser.addErrorListener(new UnderlineListener());
			parser.setBuildParseTree(true);
			ParseTree tree = parser.prog();

			if (lexer.errors.size() > 0) {
				String total = String.join(" ", lexer.errors);
				throw new LexicalException(total);
			}

			DetectFunctionVisitor funvisitor = new DetectFunctionVisitor();// visitor che consente di individuare
			// tutte le dichiarazioni di funzione per permettere che una funzione richiami
			// se stessa

			funvisitor.visit(tree);

			if (funvisitor.getErrors().size() > 0) {

				throw new SemanticException(new ConcatSemanticErrorMsgs(funvisitor.getErrors()).getConcatErrorMsgs());

			}

			env = funvisitor.getEnv();

			FOOLVisitor<Node> visitor = new MyFoolVisitor();

			Node ast = visitor.visit(tree);
//analisi semantica

			// inizio analisi semantica
			ArrayList<SemanticError> errors = ast.checkSemantics(env);

			if (errors.size() > 0) {

				throw new SemanticException(new ConcatSemanticErrorMsgs(errors).getConcatErrorMsgs());

			}
			System.out.println("Semantic analysis completed without errors");
			//fine analisi semantica

			//inizio type checking
			IType n = ast.typeCheck();
			System.out.println("Type checking completed without errors");//fine type checking

			String code = ast.codeGeneration();
			System.out.println("--Assembly code generated");
			System.out.println(code);
			BufferedWriter out = new BufferedWriter(new FileWriter(input + ".asm"));
			out.write(code);
			out.close();

			SVMLexer lexerVM = new SVMLexer(CharStreams.fromFileName(input + ".asm"));
			CommonTokenStream tokenVM = new CommonTokenStream(lexerVM);
			SVMParser parserSVM = new SVMParser(tokenVM);
			parserSVM.setBuildParseTree(true);
			ParseTree treeSVM = parserSVM.program();
			MySvmVisitor visitorVM = new MySvmVisitor();
			visitorVM.visit(treeSVM);
			ExecuteVM virMac = new ExecuteVM(visitorVM.getCode());
			virMac.cpu();// esegue il codice
		}

		catch (SemanticException e) {
			System.out.println("A semantic error has occured , an exception was thrown");
			System.out.println(e.getMessage());
		}

		catch (TypeException e) {
			System.out.println("A type error has occured , an exception was thrown");
			System.out.println(e.getMessage());
		}

		catch (LexicalException e) {
			System.out.println("A lexical error has occured , an exception was thrown");
			System.out.println(e.getMessage());
		}

		catch (SVMException e) {
			System.out.println("An error has occured during the execution of bytecode ");
			System.out.println(e.getMessage());
		}

		catch (Exception e) {

			System.err.println("An exception was thrown: ");
			// e.toString();
			e.printStackTrace();
			// System.err.println(e.getMessage());
		}

	}

}
