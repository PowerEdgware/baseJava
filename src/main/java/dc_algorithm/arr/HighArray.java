package dc_algorithm.arr;

import java.util.Random;

public class HighArray {

	private long[] a;
	private int nElems;

	public HighArray(int maxsize) {
		a = new long[maxsize];
		nElems = 0;
	}

	public int find(long searchKey) {
		int j;
		for (j = 0; j < this.nElems; j++)
			if (a[j] == searchKey)
				break;
		if (j == nElems)
			return -1;// can't find it
		else
			return j;
	}

	public void insert(long value) {
		a[nElems++] = value;
	}

	public long rndvalue() {
		return a[new Random().nextInt(nElems)];
	}

	public boolean delete(long value) {
		int j;
		for (j = 0; j < nElems; j++)
			if (a[j] == value)
				break;
		if (j == nElems)
			return false;
		else {
			for (int k = j; k < nElems; k++) {
				a[k] = a[k + 1];// move high ones down
			}
			nElems--;
			return true;
		}
	}

	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	// 二分查找-数组必须有序
	public int binarySearch(long value) {
		int left = 0;
		int right = nElems - 1;

		while (left <= right) {
			int mid = (left + right) / 2;
			if (a[mid] == value)
				return mid;
			if (a[mid] < value) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return -1;
	}

	public void insertSort() {
		for (int i = 0; i < nElems; i++) {
			long cur = a[i];
			int j = i;
			while (j > 0 && a[j - 1] > cur) {
				a[j] = a[j - 1];
				j--;
			}
			a[j] = cur;
		}
	}

	// TODO EXEC ==假设数组无序且元素全部>0
	public long getMax() {
		long tmp = -1;
		for (int i = 0; i < nElems; i++) {
			if (tmp < a[i]) {
				tmp = a[i];
			}
		}
		return tmp;
	}

	public long removeMax() {
		int i;
		long max = -1;
		int max_index = -1;
		for (i = 0; i < nElems; i++) {
			if (max < a[i]) {
				max = a[i];
				max_index = i;
			}
		}
		// move to low
		for (int j = max_index; j >= 0 && j < nElems; j++) {
			a[j] = a[j + 1];
		}
		nElems--;
		return max;
	}

	public int size() {
		return nElems;
	}

	public boolean isEmpty() {
		return nElems <= 0;
	}

	// removed duplicated element
	public void noDup() {
		if (nElems < 000_000)
			return;
		for (int i = 0; i < nElems; i++) {
			for (int j = i + 1; j < nElems; j++) {
				if (a[i] == a[j]) {
					// System.out.println("elem="+a[j]+" del="+delete(a[j]));
					for (int k = i; k < nElems; k++) {
						a[k] = a[k + 1];// move to low
					}
					nElems--;
				}
			}
		}
	}
	// TODO end exec

	public static void main(String[] args) {
		int maxsize = 100;
		HighArray highArray = new HighArray(maxsize);
		Random rnd = new Random();
		int searchKey = -1;
		for (int index = 0; index < 10; index++) {
			searchKey = rnd.nextInt(maxsize);
			highArray.insert(searchKey);
		}
		highArray.display();

		if (highArray.find(searchKey) >= 0) {
			System.out.println("Found " + searchKey);
		} else {
			System.out.println("Can't find " + searchKey);
		}

		long s = highArray.rndvalue();
		System.out.println("rndvalue " + s);
		// highArray.delete(s);
		highArray.insert(s);

		highArray.display();

		// TODO 排序先
		// highArray.insertSort();
		// System.out.println("After sort :");
		// highArray.display();
		// long searched = highArray.rndvalue();
		// System.out.println("Searching value " + searched);
		// int index = highArray.binarySearch(searched);
		// System.out.println(index);

		// TODO exec
		// 2.1
		long max = highArray.getMax();
		System.out.println("Max =" + max);

		// 2.2
		// max = highArray.removeMax();
		// System.out.println("After removed:"+max);
		// highArray.display();
		// 2.3
		// long[] b = new long[maxsize];
		// int index = 0;
		// while ((max = highArray.removeMax()) > 0) {
		// b[index++] = max;
		// }
		// print_arr(b);

		// 2.6
		highArray.noDup();

		highArray.display();
		
		System.out.println(highArray.size());

	}

	static void print_arr(long[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] > 0 ? arr[i] + " " : "");
		}
		System.out.println();
	}
}
