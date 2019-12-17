package dc_algorithm.recursion;

import java.util.Arrays;

public class MergeSort {

	private long[] theArray;
	private int nElems;

	public MergeSort(int size) {
		theArray = new long[size];
		nElems = 0;
	}

	public int size() {
		return nElems;
	}

	public void insert(long value) {
		theArray[nElems++] = value;
	}

	public void display() {
		for (int j = 0; j < nElems; j++) {
			System.out.print(theArray[j] + " ");
		}
		System.out.println();
	}

	public void merge_sort() {
		long workSapce[] = new long[nElems];
		// recMergeSort(workSapce, 0, nElems - 1);
		recMergeSort_detail(workSapce, 0, nElems - 1);
	}

	void recMergeSort(long[] workSapce, int lowBound, int highBound) {
		if (lowBound == highBound) {
			return;
		}
		int mid = (lowBound + highBound) / 2;
		recMergeSort(workSapce, lowBound, mid);

		recMergeSort(workSapce, mid + 1, highBound);

		merge(workSapce, lowBound, mid + 1, highBound);
	}

	void recMergeSort_detail(long[] workSapce, int lowBound, int highBound) {
		// System.out.println("Entering "+lowBound+"-"+highBound);
		if (lowBound == highBound) {
			// System.out.println("Base -Case Return "+lowBound+"-"+highBound);
			return;
		}
		int mid = (lowBound + highBound) / 2;
		// System.out.println("Will sort low half of "+lowBound+"-"+mid);
		recMergeSort_detail(workSapce, lowBound, mid);

		// System.out.println("Will sort high half of "+(mid +
		// 1)+"-"+highBound);
		recMergeSort_detail(workSapce, mid + 1, highBound);

		// System.out.println("Will merge halves into "+lowBound+"-"+highBound);
		merge(workSapce, lowBound, mid + 1, highBound);

		// System.out.println("Returing "+lowBound+"-"+highBound);
	}

	private void merge(long[] workSapce, int lowPtr, int highPtr, int highBound) {
		int j = 0;
		int lowBound = lowPtr;
		int mid = highPtr - 1;
		int n = highBound - lowBound + 1;
		// 边界可达
		while (lowPtr <= mid && highPtr <= highBound) {
			if (theArray[lowPtr] < theArray[highPtr])
				workSapce[j++] = theArray[lowPtr++];
			else
				workSapce[j++] = theArray[highPtr++];
		}
		while (lowPtr <= mid)
			workSapce[j++] = theArray[lowPtr++];

		while (highPtr <= highBound)
			workSapce[j++] = theArray[highPtr++];

		// cp ws elem to theArray
		for (j = 0; j < n; j++)
			theArray[lowBound + j] = workSapce[j];
		
		//System.out.println("inarr:"+Arrays.toString(workSapce));
	}

	public static void main(String[] args) {
		int size = 5;
		MergeSort mergeSort = new MergeSort(size);

		for (int e = 0; e < size; e++) {
			mergeSort.insert((long) (Math.random() * size));
		}
		System.out.println("Before sort:" + mergeSort.size());
		if (size < 100) {
			mergeSort.display();
		}
		long start = System.currentTimeMillis();
		mergeSort.merge_sort();
		long end = System.currentTimeMillis();
		System.out.println("After sort:");
		System.out.println("merge_sort total data=" + size + " cost=" + (end - start) + " ms");
		// merge_sort total data=100000 cost=25 ms

		// TODO merge_sort total data=10000000 cost=1859 ms
		if (size < 100) {
			mergeSort.display();
		}
		System.out.println("--------------------use my_merge_sort-----------------------");
		long[] arr = { -1, 2, 0, 3, 11, 2, 1, 5 };
		System.out.println("before:");
		System.out.println(Arrays.toString(arr));
		my_merge_sort(arr);

		System.out.println("after:");
		System.out.println(Arrays.toString(arr));
	}

	/// TODO mymerge 20170823
	static void my_merge_sort(long arr[]) {
		if (arr.length == 0)
			return;
		long ws[] = new long[arr.length];
		rec_merge_sort(arr, ws, 0, arr.length - 1);
	}

	static void rec_merge_sort(long[] arr, long[] ws, int low, int high) {
		if (low == high) {
			return;
		}
		int mid = (low + high) / 2;

		rec_merge_sort(arr, ws, low, mid);

		rec_merge_sort(arr, ws, mid + 1, high);

		do_merge(arr, ws, low, high);
	}

	static void do_merge(long[] arr, long[] ws, int low, int high) {
		System.out.println("low=" + low + ",high=" + high);
		int mid = (low + high) / 2;
		int split_point = mid + 1;
		int ws_index = 0;
		int orig_low = low;

		while (low <= mid && split_point <= high) {
			if (arr[low] < arr[split_point]) {
				ws[ws_index++] = arr[low++];
			} else {
				ws[ws_index++] = arr[split_point++];
			}
		}
		while (low <= mid) {
			ws[ws_index++] = arr[low++];
		}
		while (split_point <= high) {
			ws[ws_index++] = arr[split_point++];
		}
		// cp ws to arr from arr's orig_low
		for (int j = 0; j < ws_index; j++)
			arr[orig_low + j] = ws[j];
		System.out.println(Arrays.toString(ws));
	}
}
