package sorting.review;

import java.util.Arrays;
import java.util.Random;

public class MergeSort {

	public static void main(String[] args) {
		// Arrays
		// Arrays.sort(a);
		int[] a = { 1, 3 };
		int b[] = { 2, 5, 8 };
		int[] c = new int[a.length + b.length];
		merge_dual_arr(a, b, c);
		System.out.println(Arrays.toString(c));

		int arr[] = { 9, 3, 2, 0, 5, 8, 7, 1, 18 };
		System.out.println("Before sort:");
		System.out.println(Arrays.toString(arr));
		merge_sort(arr);
		System.out.println("After sort:");
		System.out.println(Arrays.toString(arr));

		// TODO 大数据排序时间测试
		int datasize = 1_000_00;
		int[] a1 = new int[datasize];
		int bound = datasize;
		Random rnd = new Random();
		for (; datasize-- > 0;) {
			a1[datasize] = rnd.nextInt(bound);
		}
		long start = System.currentTimeMillis();
		merge_sort(a1);// sort data=100000 cost=34 ms
		long end = System.currentTimeMillis();
		System.out.println("sort data=" + bound + " cost=" + (end - start) + " ms");
	}

	// 归并两个已经有序的数组
	static void merge_dual_arr(int[] a, int[] b, int[] newc) {
		int al = a.length, bl = b.length, i = 0, j = 0, k = 0;
		while (i < al && j < bl)
			if (a[i] < b[j])
				newc[k++] = a[i++];
			else
				newc[k++] = b[j++];

		while (i < al)// b is empty
			newc[k++] = a[i++];
		while (j < bl)// a is empty
			newc[k++] = b[j++];
	}

	/**
	 * 归并排序
	 * 
	 * @param a
	 */
	static void merge_sort(int[] a) {
		if (a == null || a.length <= 1)
			return;
		int ws[] = new int[a.length];
		merge(0, a.length - 1, ws, a);
	}

	private static void merge(int low, int high, int[] dest, int[] src) {
		//System.out.println("IN low=" + low + ",high=" + high);
		if (low >= high)
			return;
		int mid = (low + high) / 2;
		merge(low, mid, dest, src);
		int midRight = mid + 1;
		merge(midRight, high, dest, src);

		// TODO do_merge
		int destIndex = low, leftIndex = low, rightIndex = midRight;
		while (leftIndex <= mid && rightIndex <= high) {
			if (src[leftIndex] < src[rightIndex])
				dest[destIndex++] = src[leftIndex++];
			else
				dest[destIndex++] = src[rightIndex++];
		}
		while (leftIndex <= mid)
			dest[destIndex++] = src[leftIndex++];

		while (rightIndex <= high)
			dest[destIndex++] = src[rightIndex++];

		// cp ordered dest to src
		for (int i = low; i <= high; i++)
			src[i] = dest[i];

		// System.out.println(
		//		Arrays.toString(dest) + "\tlow=" + low + ",mid=" + mid + ",high=" + high + "\t" + Arrays.toString(src));
	}
}
