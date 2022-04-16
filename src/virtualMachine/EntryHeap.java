package virtualMachine;

public class EntryHeap {

	private int beginAdd; // indirizzo di inizio dell' oggetto nello heap
	private int size;// dimensione dell' oggetto , rappresenta il numero di argomenti con cui viene
						// instanziato l'oggetto

	public EntryHeap(int beginAdd, int size) {
		this.beginAdd = beginAdd;
		this.size = size;
	}

	public int getBeginAdd() {
		return beginAdd;
	}

	public void setBeginAdd(int beginAdd) {
		this.beginAdd = beginAdd;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
