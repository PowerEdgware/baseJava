package sorting.review;

import java.util.Arrays;
import java.util.Random;

/**
 * 冒泡排序算法的运作如下：
 * 
 * 1，比较相邻的元素，如果前一个比后一个大，就把它们两个调换位置。
 * 2，对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。 3，针对所有的元素重复以上的步骤，除了最后一个。
 * 4，持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 * 
 * @author yu
 *
 */
public class BubbleSort {

	public static void main(String[] args) {
		int arr[] = { 9, 3, 2, 0, 5, 8, 7, 1, 9 };
		System.out.println("Before sort:");
		System.out.println(Arrays.toString(arr));
		// bubble_sort(arr);
		// bubble_sort_Cocktail(arr);
		bubble_sort_other(arr);
		System.out.println("After sort:");
		System.out.println(Arrays.toString(arr));

		// TODO 大数据排序时间测试
		int datasize = 1_000_00;
		int[] a = new int[datasize];
		int bound = datasize;
		Random rnd = new Random();
		for (; datasize-- > 0;) {
			a[datasize] = rnd.nextInt(bound);
		}
		long start = System.currentTimeMillis();
		bubble_sort(a);//sort data=100000 cost=14049 ms
		long end = System.currentTimeMillis();
		System.out.println("sort data=" + bound + " cost=" + (end - start) + " ms");

	}

	/**
	 * 
	 * 
	 * @param arr
	 */
	static void bubble_sort(int[] arr) {
		int length = arr.length;

		for (int i = 0; i < length - 1; i++) {
			for (int j = 0; j < length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * 冒泡排序的改进：鸡尾酒排序
	 * 
	 * @param a
	 */
	static void bubble_sort_Cocktail(int a[]) {
		int length = a.length;
		for (int i = 0; i < length; i++) {
			int j, k;
			for (j = i; j < length - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					Swap(a, j, j + 1);
				}
			}

			for (k = j; k > i; k--) {
				if (a[k] < a[k - 1]) {
					Swap(a, k, k - 1);
				}
			}
			if (j == k)
				break;
		}
	}

	static void bubble_sort_other(int[] a) {// 鸡尾酒排序-优化
		int left = 0, right = a.length - 1;
		while (left < right) {
			for (int i = left; i < right; i++) {
				if (a[i] > a[i + 1]) {
					Swap(a, i, i + 1);
				}
			}
			right--;

			for (int j = right; j > left; j--) {
				if (a[j] < a[j - 1]) {
					Swap(a, j, j - 1);
				}
			}
			left++;
		}
	}

	static void Swap(int A[], int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}
}
