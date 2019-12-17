package dc_algorithm.simple_sort;

import java.util.Random;

public class BubbleSort {
	private long[] arr;
	private int nElems;

	public BubbleSort(int size) {
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

	public void bubble_sort() {
		int in, out;
		for (out = nElems - 1; out > 0; out--) {
			for (in = 0; in < out; in++) {
				if (arr[in] > arr[in + 1]) {
					long tmp = arr[in];
					arr[in] = arr[in + 1];
					arr[in + 1] = tmp;
				}
			}
		}
	}

	// EXEC3.1 双向移动的冒泡排序
	void prior_bubble_sort() {
		int in, out, mark = 0;
		for (out = nElems - 1; out > 0;) {
			for (in = mark; in < out; in++) {
				if (arr[in] > arr[in + 1]) {
					long temp = arr[in];
					arr[in] = arr[in + 1];
					arr[in + 1] = temp;
				}
			}
			out--;
			for (int back = out; back > mark; back--) {
				if (arr[back] < arr[back - 1]) {
					long temp = arr[back];
					arr[back] = arr[back - 1];
					arr[back - 1] = temp;
				}
			}
			mark++;
			if (out <= mark) {
				break;
			}
		}
	}

	public static void main(String[] args) {
		// java.lang.NegativeArraySizeException
		// BubbleSort bubbleSort=new BubbleSort(-10);

		int size = 100_000;
		BubbleSort bubbleSort = new BubbleSort(size);

		for (int e = 0; e < size; e++) {
			// if e>size:ArrayIndexOutOfBoundsException
			bubbleSort.insert(new Random().nextInt(size));
		}
		System.out.println("Before sort:");
//		 bubbleSort.dispaly();
		long start = System.currentTimeMillis();
		bubbleSort.bubble_sort();
		long end = System.currentTimeMillis();
		System.out.println("bubble_sort total data=" + size + " cost=" + (end - start) + " ms");
		// bubble_sort total data=100000 cost=14128 ms
		System.out.println("After sort:");
//		 bubbleSort.dispaly();

		// 时间复杂度 O(n^2)

		// TODO EXEC3.1
		System.out.println("Before prior sort:");
		start = System.currentTimeMillis();
		bubbleSort.prior_bubble_sort();
		end = System.currentTimeMillis();
		System.out.println("prior_bubble_sort total data=" + size + " cost=" + (end - start) + " ms");
		//prior_bubble_sort total data=100000 cost=2357 ms
		System.out.println("After prior sort:");
//		 bubbleSort.dispaly();
	}
}
