package virtualMachine;

import java.util.HashMap;

import exception.SVMException;
import parser.SVMParser;
import util.VmInfo;

public class ExecuteVM {

	public static final int CODESIZE = VmInfo.codesize;
	public static final int MEMSIZE = VmInfo.memsize;

	private int[] code;
	private int[] memory = new int[MEMSIZE];
	private HashMap<Integer, EntryHeap> heapMemory;

	private int ip = 0;
	private int sp = MEMSIZE;

	private int hp = 0;
	private int fp = MEMSIZE;
	private int ra;
	private int rv;

	public ExecuteVM(int[] code) {
		this.code = code;

		hp = 0;

		heapMemory = new HashMap<>();

	}

	public void cpu() throws SVMException {

		while (true) {
			int bytecode = code[ip++]; // fetch
			// System.out.println(bytecode);
			int v1, v2;
			int address;

			switch (bytecode) {
			case SVMParser.PUSH:
				push(code[ip++]);
				break;
			case SVMParser.POP:
				pop();
				break;
			case SVMParser.ADD:
				v1 = pop();
				v2 = pop();
				push(v2 + v1);
				break;
			case SVMParser.MULT:
				v1 = pop();
				v2 = pop();
				push(v2 * v1);
				break;
			case SVMParser.DIV:
				v1 = pop();
				v2 = pop();
				push(v2 / v1);
				break;
			case SVMParser.SUB:
				v1 = pop();
				v2 = pop();
				push(v2 - v1);
				break;
			case SVMParser.STOREW: //
				address = pop();
				controlla(address);// controllo per eventuali acccessi illegali alla memoria 
				memory[address] = pop();
				break;
			case SVMParser.LOADW: //
				int op = pop();
				controlla(op);
				push(memory[op]);
				break;
			case SVMParser.BRANCH:
				address = code[ip];
				ip = address;
				break;
			case SVMParser.BRANCHEQ: //
				address = code[ip++];
				v1 = pop();
				v2 = pop();
				if (v2 == v1)
					ip = address;
				break;
			case SVMParser.BRANCHLESSEQ:
				address = code[ip++];
				v1 = pop();
				v2 = pop();
				if (v2 <= v1)
					ip = address;
				break;
			case SVMParser.JS: //
				address = pop();
				ra = ip;
				ip = address;

				break;
			case SVMParser.STORERA: //
				ra = pop();
				break;
			case SVMParser.LOADRA: //
				push(ra);
				break;
			case SVMParser.STORERV: //
				rv = pop();
				break;
			case SVMParser.LOADRV: //
				push(rv);
				break;
			case SVMParser.LOADFP: //
				push(fp);
				break;
			case SVMParser.STOREFP: //
				fp = pop();
				break;
			case SVMParser.COPYFP: //

				fp = sp;

				break;
			case SVMParser.STOREHP: //
				hp = pop();
				break;
			case SVMParser.LOADHP: //
				push(hp);
				break;
			case SVMParser.PRINT:
				/*
				 * if(sp < MEMSIZE) { v1 = pop(); System.out.println("Value from print: "+v1); }
				 * 
				 * else { System.out.println("Empty stack!"); }
				 */

				System.out.println((sp < MEMSIZE) ? "Value from print: " + memory[sp] : "Empty stack!");
				break;
			case SVMParser.NEW:
				// indirizzo della dispatch table della classe che si vuole instanziare
				int addressDispatchTable = pop();
				// numero parametri attuali

				int numberActualParam = pop();

				int[] actualArguments = new int[numberActualParam + 1]; // nel caso si abbiano 0 paramtri attuali faccio
																		// +1 per eitare di inizializzare un array con 0
																		// elementi

				for (int i = numberActualParam - 1; i >= 0; i--) {
					actualArguments[i] = pop();
				}

				EntryHeap eh = memoryAllocation(numberActualParam, actualArguments, addressDispatchTable);
				push(eh.getBeginAdd());
				break;

			case SVMParser.LAM:// carica l'indirizzo del metodo

				push(code[pop()]);
				break;

			case SVMParser.CFV:// change field value per modificare il valore di un campo di una classe

				int valore = pop();
				int objAdd = pop();
				controlla(objAdd);
				int biasField = pop();
				int actualAdd = objAdd + biasField + 1; // +\ perchè il primo e' l'indirizzo della classe
				memory[actualAdd] = valore;

				break;
			case SVMParser.HALT:

				System.out.println((sp < CODESIZE) ? "Value at the top of stack: " + memory[sp] : "Empty stack!");
				return;
			}
		}
	}

	private int pop() throws SVMException {
		if (sp + 1 > MEMSIZE) {
			throw new SVMException(" error on pop, sp + 1 is greater than the memory size");
		}
		return memory[sp++];
	}

	private void push(int v) throws SVMException {
		// lo sp cresce verso il basso
		if (((sp - 1) < 0) || (sp - 1) < hp) {
			throw new SVMException("Error on push:  there is no more space available int the stack");
		}

		memory[--sp] = v;
	}

	private EntryHeap memoryAllocation(int numbActualParam, int[] actualArgs, int addDt) throws SVMException {

		// +1 perche' si deve contare anche l'indirizzo della dispatch table
		if (hp + 1 + numbActualParam > sp) {

			throw new SVMException(
					"There is no more space available in the memory , the program will stop immediately");
		}

		EntryHeap entryToinsert = new EntryHeap(hp, numbActualParam);
		heapMemory.put(hp, entryToinsert);

		int i, j;

		memory[hp] = addDt;// inserisco nel primo indice libero che trovo nello heap l'indirizzo della
							// dispatch table della classe che intendo instanziare

		for (i = hp + 1, j = 0; i < hp + 1 + numbActualParam; i++, j++) {

			memory[i] = actualArgs[j];

		}

		hp = hp + numbActualParam + 1;

		return entryToinsert;
	}

	void controlla(int add) throws SVMException {
		if (add == Integer.MAX_VALUE) {
			throw new SVMException(
					"An illegal memeory access was encountered: you canno't access to an object instantiated with null");
		}
	}

}