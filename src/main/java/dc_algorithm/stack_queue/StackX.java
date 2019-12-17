package dc_algorithm.stack_queue;


public class StackX {

	private Long[] data;
	private int nItems;

	public StackX(int size) {
		if (size <= 0)
			throw new IllegalArgumentException("init size:" + size);
		this.data = new Long[size];
		this.nItems = 0;
	}

	public synchronized void push(long item) {
		ensureSize();
		data[nItems++] = item;
	}

	public synchronized long pop() {
		int size = size();
		if (size == 0)
			throw new StackEmptyException();
		long item = peek();
		simpleRemoveItem(size - 1);
		return item;
	}

	public synchronized long peek() {
		int len = size();
		if (len == 0)
			throw new StackEmptyException();
		return data[len - 1];
	}

	public synchronized int size() {
		return nItems;
	}
	
	public synchronized int Arraysize() {
		return data.length;
	}


	public synchronized boolean isEmpty(){
		return size()==0;
	}

	private void simpleRemoveItem(int itemIndex) {
		if (itemIndex < 0 || itemIndex > nItems) {
			return;
		}
		data[itemIndex] = null;
		nItems--;
	}

	void ensureSize() {
		if (nItems - data.length >= 0) {
			int newSize = data.length + (data.length >> 1);
			if (nItems > newSize)
				newSize = nItems;
			Long[] newData = new Long[newSize];
			for (int i = 0; i < data.length; i++) {
				newData[i] = data[i];
			}
			data=newData;
		}
	}

	class StackEmptyException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	}

	public static void main(String[] args) {
		int a[] = new int[2];
		System.out.println(a.length);
	}
}
