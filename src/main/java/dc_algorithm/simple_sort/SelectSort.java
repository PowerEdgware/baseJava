package dc_algorithm.simple_sort;

import java.util.Random;

public class SelectSort {
	private long[] arr;
	private int nElems;

	public SelectSort(int size) {
		arr = new long[size];
		nElems = 0;
	}

	public void insert(long value) {
		arr[nElems++] = value;
	}

	public void dispaly() {
		if (arr.length == 0) {
			System.out.println("[]");
			return;
		}
		for (int i = 0; i < nElems; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	static int c = 0;

	public void select_sort() {
		int in, out, min;
		for (out = 0; out < nElems; out++) {
			min = out;
			for (in = out + 1; in < nElems; in++) {
				if (arr[in] < arr[min]) {
					min = in;
				}
			}
			// 交换(当前位置为最小，则不交换)
			if (out != min) {
				c++;
				long temp = arr[out];
				arr[out] = arr[min];
				arr[min] = temp;
			}
		}
		System.out.println("swap count=" + c);
	}
	// TODO 如果数据已经是排序好的(小-->大)，则不再需要交换数据
	// 如果是从大到小，则需要交换n/2次

	public static void main(String[] args) {

		int size = 100000;
		SelectSort selectSort = new SelectSort(size);
		int bound = 1000;
		for (int e = size; e > 0; e--) {
			selectSort.insert(new Random().nextInt(bound));
			// selectSort.insert(e);
		}
		System.out.println("Before sort:");
		// selectSort.dispaly();

		long start = System.currentTimeMillis();
		selectSort.select_sort();
		long end = System.currentTimeMillis();
		System.out.println("select_sort total data=" + size + " cost=" + (end - start) + " ms");
		// swap count=99889
		// select_sort total data=100000 cost=2895 ms

		System.out.println("After sort:");
		// selectSort.dispaly();

		// 时间复杂度 O(n^2)
	}
}
