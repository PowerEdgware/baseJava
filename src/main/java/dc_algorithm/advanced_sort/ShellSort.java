package dc_algorithm.advanced_sort;

import java.util.Arrays;

/**
 * 希尔排序--基于插入排序
 * 
 * @author yu
 *
 */
public class ShellSort {

	private long arr[];
	private int nElems;

	public ShellSort(int size) {
		this.arr = new long[size];
		this.nElems = 0;
	}

	public int size() {
		return this.nElems;
	}

	public void insert(long value) {
		arr[nElems++] = value;
	}

	public void shell_sort() {
		int inner, outer;
		long temp;

		int h = 1;
		while (h <= nElems / 3) {
			h = 3 * h + 1;
		}
		System.out.println("h=" + h);

		while (h > 0) {
			for (outer = h; outer < nElems; outer++) {
				temp = arr[outer];
				inner = outer;
				while (inner >= h && arr[inner - h] > temp) {
					arr[inner] = arr[inner - h];
					inner -= h;
				}
				arr[inner] = temp;
			}
			h = (h - 1) / 3;
		}
	}

	public void display() {
		if (nElems == 0 || nElems > 100) {
			System.out.println("nElems=" + nElems + ",too big!");
			return;
		}
		for (int lk = 0; lk < nElems; lk++) {
			System.out.print(arr[lk] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int size = 10_0000;
		ShellSort shellSort = new ShellSort(size);

		for (int e = 0; e < size; e++) {
			int n = (int) (Math.random() * 99);
			shellSort.insert(n);
		}
		System.out.println("Before sort:");
		shellSort.display();

		long start = System.currentTimeMillis();
		shellSort.shell_sort();
		long end = System.currentTimeMillis();
		System.out.println("After sort:" + "arr size=" + shellSort.size());
		System.out.println("shell_sort total data=" + size + " cost=" + (end - start) + " ms");
		// shell_sort total data=100000 cost=18 ms
		// TODO shell_sort total data=10000 cost=4 ms|shell_sort total
		// data=10000 cost=16 ms
		// TODO shell_sort total data=10000000 cost=1108 ms
		shellSort.display();

		// ArrayIndexOutOfBoundsException

		System.out.println("-----------my_shell_sort--------------");
		long[] arr = { 0, -1, 3, 7, 12, 6, 0, 5, 2 };
		System.out.println("Before=" + Arrays.toString(arr));
		my_shell_sort(arr);

		System.out.println("After=" + Arrays.toString(arr));
	}

	// TODO my_shell_sort
	static void my_shell_sort(long[] arr) {
		int h = 1;
		while (h <= arr.length / 3) {
			h = 3 * h + 1;
		}

		rec_shell_sort(arr, h);
	}

	static void rec_shell_sort(long[] arr, int h) {
		if (h <= 0) {
			return;
		}
		int inner, outer;
		long insert_val;
		for (outer = h; outer < arr.length; outer++) {
			insert_val = arr[outer];
			inner = outer;
			// ensure inner-h>=0
			while (inner >= h && arr[inner - h] > insert_val) {
				arr[inner] = arr[inner - h];// move to high
				inner -= h;
			}
			arr[inner] = insert_val;
		}
		// cut down h
		h = (h - 1) / 3;

		rec_shell_sort(arr, h);
	}

	// TreeSet
	// TreeMap
}
