package errorlistener;

import java.util.ArrayList;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class UnderlineListener extends BaseErrorListener {
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) throws ParseCancellationException {

		String ris = new String();

		ris = ris.concat("line " + line + ":" + charPositionInLine + " " + msg).concat("\n")
				.concat(underlineError(recognizer, (Token) offendingSymbol, line, charPositionInLine));

		throw new ParseCancellationException(ris);

		// System.out.println("line "+line+":"+charPositionInLine+" "+msg);
		// underlineError(recognizer,(Token)offendingSymbol,
		// line, charPositionInLine);
	}

	protected String underlineError(Recognizer recognizer, Token offendingToken, int line, int charPositionInLine) {

		String ris = new String();
		CommonTokenStream tokens = (CommonTokenStream) recognizer.getInputStream();
		String input = tokens.getTokenSource().getInputStream().toString();
		String[] lines = input.split("\n");
		String errorLine = lines[line - 1];
		// System.out.println(errorLine);
		//ris= ris.concat("\n");
		ris = ris.concat(errorLine);
		ris= ris.concat("\n");
		for (int i = 0; i < charPositionInLine; i++) {
			// System.out.print(" ");
			ris = ris.concat(" ");
		}
		int start = offendingToken.getStartIndex();
		int stop = offendingToken.getStopIndex();
		if (start >= 0 && stop >= 0) {
			for (int i = start; i <= stop; i++) {
				// System.out.print("^");
				ris = ris.concat("^");
			}

		}

		// System.out.println();
		ris = ris.concat("\n");
		return ris;
	}
}