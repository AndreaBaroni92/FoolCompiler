// Generated from SVM.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SVMParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PUSH=1, POP=2, ADD=3, SUB=4, MULT=5, DIV=6, STOREW=7, LOADW=8, BRANCH=9, 
		BRANCHEQ=10, BRANCHLESSEQ=11, JS=12, LOADRA=13, STORERA=14, LOADRV=15, 
		STORERV=16, LOADFP=17, STOREFP=18, COPYFP=19, LOADHP=20, STOREHP=21, PRINT=22, 
		HALT=23, NEW=24, LAM=25, CFV=26, COL=27, LABEL=28, NUMBER=29, WHITESP=30, 
		ERR=31;
	public static final int
		RULE_program = 0, RULE_assembly = 1;
	public static final String[] ruleNames = {
		"program", "assembly"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'push'", "'pop'", "'add'", "'sub'", "'mult'", "'div'", "'sw'", 
		"'lw'", "'b'", "'beq'", "'bleq'", "'js'", "'lra'", "'sra'", "'lrv'", "'srv'", 
		"'lfp'", "'sfp'", "'cfp'", "'lhp'", "'shp'", "'print'", "'halt'", "'new'", 
		"'lam'", "'cfv'", "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "PUSH", "POP", "ADD", "SUB", "MULT", "DIV", "STOREW", "LOADW", "BRANCH", 
		"BRANCHEQ", "BRANCHLESSEQ", "JS", "LOADRA", "STORERA", "LOADRV", "STORERV", 
		"LOADFP", "STOREFP", "COPYFP", "LOADHP", "STOREHP", "PRINT", "HALT", "NEW", 
		"LAM", "CFV", "COL", "LABEL", "NUMBER", "WHITESP", "ERR"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "SVM.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SVMParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public List<AssemblyContext> assembly() {
			return getRuleContexts(AssemblyContext.class);
		}
		public AssemblyContext assembly(int i) {
			return getRuleContext(AssemblyContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(7);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PUSH) | (1L << POP) | (1L << ADD) | (1L << SUB) | (1L << MULT) | (1L << DIV) | (1L << STOREW) | (1L << LOADW) | (1L << BRANCH) | (1L << BRANCHEQ) | (1L << BRANCHLESSEQ) | (1L << JS) | (1L << LOADRA) | (1L << STORERA) | (1L << LOADRV) | (1L << STORERV) | (1L << LOADFP) | (1L << STOREFP) | (1L << COPYFP) | (1L << LOADHP) | (1L << STOREHP) | (1L << PRINT) | (1L << HALT) | (1L << NEW) | (1L << LAM) | (1L << CFV) | (1L << LABEL))) != 0)) {
				{
				{
				setState(4);
				assembly();
				}
				}
				setState(9);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssemblyContext extends ParserRuleContext {
		public AssemblyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assembly; }
	 
		public AssemblyContext() { }
		public void copyFrom(AssemblyContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class LamInstrContext extends AssemblyContext {
		public TerminalNode LAM() { return getToken(SVMParser.LAM, 0); }
		public LamInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterLamInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitLamInstr(this);
		}
	}
	public static class HaltInstrContext extends AssemblyContext {
		public TerminalNode HALT() { return getToken(SVMParser.HALT, 0); }
		public HaltInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterHaltInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitHaltInstr(this);
		}
	}
	public static class MultInstrContext extends AssemblyContext {
		public TerminalNode MULT() { return getToken(SVMParser.MULT, 0); }
		public MultInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterMultInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitMultInstr(this);
		}
	}
	public static class LrvInstrContext extends AssemblyContext {
		public TerminalNode LOADRV() { return getToken(SVMParser.LOADRV, 0); }
		public LrvInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterLrvInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitLrvInstr(this);
		}
	}
	public static class SraInstrContext extends AssemblyContext {
		public TerminalNode STORERA() { return getToken(SVMParser.STORERA, 0); }
		public SraInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterSraInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitSraInstr(this);
		}
	}
	public static class LhpInstrContext extends AssemblyContext {
		public TerminalNode LOADHP() { return getToken(SVMParser.LOADHP, 0); }
		public LhpInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterLhpInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitLhpInstr(this);
		}
	}
	public static class LfpInstrContext extends AssemblyContext {
		public TerminalNode LOADFP() { return getToken(SVMParser.LOADFP, 0); }
		public LfpInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterLfpInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitLfpInstr(this);
		}
	}
	public static class PushLabInstrContext extends AssemblyContext {
		public Token l;
		public TerminalNode PUSH() { return getToken(SVMParser.PUSH, 0); }
		public TerminalNode LABEL() { return getToken(SVMParser.LABEL, 0); }
		public PushLabInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterPushLabInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitPushLabInstr(this);
		}
	}
	public static class LwInstrContext extends AssemblyContext {
		public TerminalNode LOADW() { return getToken(SVMParser.LOADW, 0); }
		public LwInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterLwInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitLwInstr(this);
		}
	}
	public static class PrintInstContext extends AssemblyContext {
		public TerminalNode PRINT() { return getToken(SVMParser.PRINT, 0); }
		public PrintInstContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterPrintInst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitPrintInst(this);
		}
	}
	public static class LraInstrContext extends AssemblyContext {
		public TerminalNode LOADRA() { return getToken(SVMParser.LOADRA, 0); }
		public LraInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterLraInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitLraInstr(this);
		}
	}
	public static class AddInstrContext extends AssemblyContext {
		public TerminalNode ADD() { return getToken(SVMParser.ADD, 0); }
		public AddInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterAddInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitAddInstr(this);
		}
	}
	public static class SfpInstrContext extends AssemblyContext {
		public TerminalNode STOREFP() { return getToken(SVMParser.STOREFP, 0); }
		public SfpInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterSfpInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitSfpInstr(this);
		}
	}
	public static class CfpInstrContext extends AssemblyContext {
		public TerminalNode COPYFP() { return getToken(SVMParser.COPYFP, 0); }
		public CfpInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterCfpInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitCfpInstr(this);
		}
	}
	public static class SwInstrContext extends AssemblyContext {
		public TerminalNode STOREW() { return getToken(SVMParser.STOREW, 0); }
		public SwInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterSwInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitSwInstr(this);
		}
	}
	public static class CfvInstrContext extends AssemblyContext {
		public TerminalNode CFV() { return getToken(SVMParser.CFV, 0); }
		public CfvInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterCfvInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitCfvInstr(this);
		}
	}
	public static class BrancLEqInstrContext extends AssemblyContext {
		public Token l;
		public TerminalNode BRANCHLESSEQ() { return getToken(SVMParser.BRANCHLESSEQ, 0); }
		public TerminalNode LABEL() { return getToken(SVMParser.LABEL, 0); }
		public BrancLEqInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterBrancLEqInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitBrancLEqInstr(this);
		}
	}
	public static class SubInstrContext extends AssemblyContext {
		public TerminalNode SUB() { return getToken(SVMParser.SUB, 0); }
		public SubInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterSubInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitSubInstr(this);
		}
	}
	public static class DivInstrContext extends AssemblyContext {
		public TerminalNode DIV() { return getToken(SVMParser.DIV, 0); }
		public DivInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterDivInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitDivInstr(this);
		}
	}
	public static class PushNumbInstrContext extends AssemblyContext {
		public Token n;
		public TerminalNode PUSH() { return getToken(SVMParser.PUSH, 0); }
		public TerminalNode NUMBER() { return getToken(SVMParser.NUMBER, 0); }
		public PushNumbInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterPushNumbInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitPushNumbInstr(this);
		}
	}
	public static class JsInstrContext extends AssemblyContext {
		public TerminalNode JS() { return getToken(SVMParser.JS, 0); }
		public JsInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterJsInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitJsInstr(this);
		}
	}
	public static class OnlyLabelInstrContext extends AssemblyContext {
		public Token l;
		public TerminalNode LABEL() { return getToken(SVMParser.LABEL, 0); }
		public OnlyLabelInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterOnlyLabelInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitOnlyLabelInstr(this);
		}
	}
	public static class LabelInstrContext extends AssemblyContext {
		public Token l;
		public TerminalNode COL() { return getToken(SVMParser.COL, 0); }
		public TerminalNode LABEL() { return getToken(SVMParser.LABEL, 0); }
		public LabelInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterLabelInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitLabelInstr(this);
		}
	}
	public static class BranchLabInstrContext extends AssemblyContext {
		public Token l;
		public TerminalNode BRANCH() { return getToken(SVMParser.BRANCH, 0); }
		public TerminalNode LABEL() { return getToken(SVMParser.LABEL, 0); }
		public BranchLabInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterBranchLabInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitBranchLabInstr(this);
		}
	}
	public static class NewInstrContext extends AssemblyContext {
		public TerminalNode NEW() { return getToken(SVMParser.NEW, 0); }
		public NewInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterNewInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitNewInstr(this);
		}
	}
	public static class SrvInstrContext extends AssemblyContext {
		public TerminalNode STORERV() { return getToken(SVMParser.STORERV, 0); }
		public SrvInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterSrvInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitSrvInstr(this);
		}
	}
	public static class PopInstrContext extends AssemblyContext {
		public TerminalNode POP() { return getToken(SVMParser.POP, 0); }
		public PopInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterPopInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitPopInstr(this);
		}
	}
	public static class ShpInstrContext extends AssemblyContext {
		public TerminalNode STOREHP() { return getToken(SVMParser.STOREHP, 0); }
		public ShpInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterShpInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitShpInstr(this);
		}
	}
	public static class BrancEqInstrContext extends AssemblyContext {
		public Token l;
		public TerminalNode BRANCHEQ() { return getToken(SVMParser.BRANCHEQ, 0); }
		public TerminalNode LABEL() { return getToken(SVMParser.LABEL, 0); }
		public BrancEqInstrContext(AssemblyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).enterBrancEqInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SVMListener ) ((SVMListener)listener).exitBrancEqInstr(this);
		}
	}

	public final AssemblyContext assembly() throws RecognitionException {
		AssemblyContext _localctx = new AssemblyContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_assembly);
		try {
			setState(45);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				_localctx = new PushNumbInstrContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(10);
				match(PUSH);
				setState(11);
				((PushNumbInstrContext)_localctx).n = match(NUMBER);
				}
				break;
			case 2:
				_localctx = new PushLabInstrContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(12);
				match(PUSH);
				setState(13);
				((PushLabInstrContext)_localctx).l = match(LABEL);
				}
				break;
			case 3:
				_localctx = new PopInstrContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(14);
				match(POP);
				}
				break;
			case 4:
				_localctx = new AddInstrContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(15);
				match(ADD);
				}
				break;
			case 5:
				_localctx = new SubInstrContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(16);
				match(SUB);
				}
				break;
			case 6:
				_localctx = new MultInstrContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(17);
				match(MULT);
				}
				break;
			case 7:
				_localctx = new DivInstrContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(18);
				match(DIV);
				}
				break;
			case 8:
				_localctx = new SwInstrContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(19);
				match(STOREW);
				}
				break;
			case 9:
				_localctx = new LwInstrContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(20);
				match(LOADW);
				}
				break;
			case 10:
				_localctx = new LabelInstrContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(21);
				((LabelInstrContext)_localctx).l = match(LABEL);
				setState(22);
				match(COL);
				}
				break;
			case 11:
				_localctx = new OnlyLabelInstrContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(23);
				((OnlyLabelInstrContext)_localctx).l = match(LABEL);
				}
				break;
			case 12:
				_localctx = new BranchLabInstrContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(24);
				match(BRANCH);
				setState(25);
				((BranchLabInstrContext)_localctx).l = match(LABEL);
				}
				break;
			case 13:
				_localctx = new BrancEqInstrContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(26);
				match(BRANCHEQ);
				setState(27);
				((BrancEqInstrContext)_localctx).l = match(LABEL);
				}
				break;
			case 14:
				_localctx = new BrancLEqInstrContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(28);
				match(BRANCHLESSEQ);
				setState(29);
				((BrancLEqInstrContext)_localctx).l = match(LABEL);
				}
				break;
			case 15:
				_localctx = new JsInstrContext(_localctx);
				enterOuterAlt(_localctx, 15);
				{
				setState(30);
				match(JS);
				}
				break;
			case 16:
				_localctx = new LraInstrContext(_localctx);
				enterOuterAlt(_localctx, 16);
				{
				setState(31);
				match(LOADRA);
				}
				break;
			case 17:
				_localctx = new SraInstrContext(_localctx);
				enterOuterAlt(_localctx, 17);
				{
				setState(32);
				match(STORERA);
				}
				break;
			case 18:
				_localctx = new LrvInstrContext(_localctx);
				enterOuterAlt(_localctx, 18);
				{
				setState(33);
				match(LOADRV);
				}
				break;
			case 19:
				_localctx = new SrvInstrContext(_localctx);
				enterOuterAlt(_localctx, 19);
				{
				setState(34);
				match(STORERV);
				}
				break;
			case 20:
				_localctx = new LfpInstrContext(_localctx);
				enterOuterAlt(_localctx, 20);
				{
				setState(35);
				match(LOADFP);
				}
				break;
			case 21:
				_localctx = new SfpInstrContext(_localctx);
				enterOuterAlt(_localctx, 21);
				{
				setState(36);
				match(STOREFP);
				}
				break;
			case 22:
				_localctx = new CfpInstrContext(_localctx);
				enterOuterAlt(_localctx, 22);
				{
				setState(37);
				match(COPYFP);
				}
				break;
			case 23:
				_localctx = new LhpInstrContext(_localctx);
				enterOuterAlt(_localctx, 23);
				{
				setState(38);
				match(LOADHP);
				}
				break;
			case 24:
				_localctx = new ShpInstrContext(_localctx);
				enterOuterAlt(_localctx, 24);
				{
				setState(39);
				match(STOREHP);
				}
				break;
			case 25:
				_localctx = new PrintInstContext(_localctx);
				enterOuterAlt(_localctx, 25);
				{
				setState(40);
				match(PRINT);
				}
				break;
			case 26:
				_localctx = new HaltInstrContext(_localctx);
				enterOuterAlt(_localctx, 26);
				{
				setState(41);
				match(HALT);
				}
				break;
			case 27:
				_localctx = new NewInstrContext(_localctx);
				enterOuterAlt(_localctx, 27);
				{
				setState(42);
				match(NEW);
				}
				break;
			case 28:
				_localctx = new LamInstrContext(_localctx);
				enterOuterAlt(_localctx, 28);
				{
				setState(43);
				match(LAM);
				}
				break;
			case 29:
				_localctx = new CfvInstrContext(_localctx);
				enterOuterAlt(_localctx, 29);
				{
				setState(44);
				match(CFV);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!\62\4\2\t\2\4\3\t"+
		"\3\3\2\7\2\b\n\2\f\2\16\2\13\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\60\n\3\3\3\2\2\4\2\4\2\2\2L\2\t"+
		"\3\2\2\2\4/\3\2\2\2\6\b\5\4\3\2\7\6\3\2\2\2\b\13\3\2\2\2\t\7\3\2\2\2\t"+
		"\n\3\2\2\2\n\3\3\2\2\2\13\t\3\2\2\2\f\r\7\3\2\2\r\60\7\37\2\2\16\17\7"+
		"\3\2\2\17\60\7\36\2\2\20\60\7\4\2\2\21\60\7\5\2\2\22\60\7\6\2\2\23\60"+
		"\7\7\2\2\24\60\7\b\2\2\25\60\7\t\2\2\26\60\7\n\2\2\27\30\7\36\2\2\30\60"+
		"\7\35\2\2\31\60\7\36\2\2\32\33\7\13\2\2\33\60\7\36\2\2\34\35\7\f\2\2\35"+
		"\60\7\36\2\2\36\37\7\r\2\2\37\60\7\36\2\2 \60\7\16\2\2!\60\7\17\2\2\""+
		"\60\7\20\2\2#\60\7\21\2\2$\60\7\22\2\2%\60\7\23\2\2&\60\7\24\2\2\'\60"+
		"\7\25\2\2(\60\7\26\2\2)\60\7\27\2\2*\60\7\30\2\2+\60\7\31\2\2,\60\7\32"+
		"\2\2-\60\7\33\2\2.\60\7\34\2\2/\f\3\2\2\2/\16\3\2\2\2/\20\3\2\2\2/\21"+
		"\3\2\2\2/\22\3\2\2\2/\23\3\2\2\2/\24\3\2\2\2/\25\3\2\2\2/\26\3\2\2\2/"+
		"\27\3\2\2\2/\31\3\2\2\2/\32\3\2\2\2/\34\3\2\2\2/\36\3\2\2\2/ \3\2\2\2"+
		"/!\3\2\2\2/\"\3\2\2\2/#\3\2\2\2/$\3\2\2\2/%\3\2\2\2/&\3\2\2\2/\'\3\2\2"+
		"\2/(\3\2\2\2/)\3\2\2\2/*\3\2\2\2/+\3\2\2\2/,\3\2\2\2/-\3\2\2\2/.\3\2\2"+
		"\2\60\5\3\2\2\2\4\t/";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}