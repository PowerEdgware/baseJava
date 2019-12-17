package sorting.review;

import java.util.Arrays;
import java.util.Random;

/**
 * 
 * 选择排序也是一种简单直观的排序算法。它的工作原理很容易理解：初始时在序列中找到最小（大）元素，放到序列的起始位置作为已排序序列；然后，再从剩余未排序元素中继续寻找最小（大）元素，放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 * 
 * 注意选择排序与冒泡排序的区别：冒泡排序通过依次交换相邻两个顺序不合法的元素位置，从而将当前最小（大）元素放到合适的位置；而选择排序每遍历一次都记住了当前最小（大）元素的位置，最后仅需一次交换操作即可将其放到合适的位置。
 * 
 * @author yu
 *
 */
public class SelectSort {

	public static void main(String[] args) {
		int arr[] = { 9, 3, 2, 0, 5, 8, 7, 1, 9 };
		System.out.println("Before sort:");
		System.out.println(Arrays.toString(arr));
		select_sort(arr);
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
		select_sort(a);//sort data=100000 cost=3027 ms
		long end = System.currentTimeMillis();
		System.out.println("sort data=" + bound + " cost=" + (end - start) + " ms");
	}

	static void select_sort(int a[]) {
		int length = a.length;
		for (int i = 0; i < length; i++) {
			int target = a[i];
			int max = i;
			for (int j = i; j < length; j++) {
				if (a[j] > a[max]) {
					max = j;
				}
			}
			if (max != i) {
				a[i] = a[max];
				a[max] = target;
			}

		}
	}

	static void Swap(int A[], int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}
}
