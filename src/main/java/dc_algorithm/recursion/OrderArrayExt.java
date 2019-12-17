package dc_algorithm.recursion;

import java.util.Random;

public class OrderArrayExt {

	private long[] arr;
	private int nElems;

	public OrderArrayExt(int size) {
		arr = new long[size];
		nElems = 0;
	}

	public int size() {
		return this.nElems;
	}

	// binary search
	public int find(long searchKey) {
		int low = 0;
		int high = nElems - 1;
		int mid;
		while (true) {
			mid = (low + high) / 2;
			if (arr[mid] == searchKey)
				return mid;
			else if (low > high)
				return -1;
			else {
				if (arr[mid] < searchKey)
					low = mid + 1;
				else
					high = mid - 1;
			}
		}
	}

	public void insert(long value) {
		int j;
		for (j = 0; j < nElems; j++) {
			if (arr[j] > value)
				break;
		}
		for (int k = nElems; k > j; k--) {
			arr[k] = arr[k - 1];
		}
		arr[j] = value;
		nElems++;
	}

	public boolean delete(long value) {
		int index = find(value);
		if (index < 0)
			return false;

		for (int j = index; j < nElems; j++)
			arr[j] = arr[j + 1];

		nElems--;
		return true;
	}

	public long rnd_value() {
		return arr[new Random().nextInt(nElems)];
	}

	public void display() {
		if (nElems == 0) {
			System.out.println("[]");
			return;
		}
		for (int i = 0; i < nElems; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// TODO EXEC
	public void binary_insert(long value) {
		int low = 0, high = nElems - 1;
		int mid = 0;

		while (low <= high) {
			mid = (low + high) / 2;
			if (arr[mid] == value) {
				break;
			} else if (arr[mid] < value) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		for (int k = nElems; k > low; k--)
			arr[k] = arr[k - 1];// move low to high
		arr[low] = value;
		nElems++;
	}

	public void merge(long[] a) {
		if (a == null || a.length == 0)
			return;
		for (long elem : a) {
			binary_insert(elem);
		}
	}
	// TODO end exec

	// recursion find
	public int rec_find(long searchKey) {
		return recFind(searchKey, 0, nElems - 1);
	}

	private int recFind(long key, int lowBound, int highBound) {
		int mid = (lowBound + highBound) / 2;
		if (arr[mid] == key) {
			return mid;
		} else if (lowBound > highBound) {
			return -1;
		}
		if (arr[mid] < key) {
			return recFind(key, mid + 1, highBound);
		}
		return recFind(key, lowBound, mid - 1);
	}

	public static void main(String[] args) {
		int size=100;
		OrderArrayExt array=new OrderArrayExt(size);
		for(int k=0;k<10;k++){
			array.insert((long)(Math.random()*size));
		}
		array.display();
		long searchKey=array.rnd_value();
//		 searchKey=25;
		
		int index=array.rec_find(searchKey);
		System.out.println("key="+searchKey+",found_index="+index);
	}
}
