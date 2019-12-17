package sorting.review;

import java.util.Arrays;
import java.util.Random;

public class ShellSort {

	public static void main(String[] args) {
		int arr[] = { 9, 3, 2, 0, 5, 8, 7, 1, 9 };
		System.out.println("Before sort:");
		System.out.println(Arrays.toString(arr));
		shell_sort(arr);
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
		shell_sort(a1);// sort data=100000 cost=22 ms
		long end = System.currentTimeMillis();
		System.out.println("sort data=" + bound + " cost=" + (end - start) + " ms");
	}

	static void shell_sort(int[] A) {
		int n = A.length;
		int h = 0;
		while (h <= n/3) // 生成初始增量
		{
			h = 3 * h + 1;
		}
		while (h >= 1) {
			for (int i = h; i < n; i++) {
				int j = i - h;
				int get = A[i];
				while (j >= 0 && A[j] > get) {
					A[j + h] = A[j];
					j = j - h;
				}
				A[j + h] = get;
			}
			h = (h - 1) / 3; // 递减增量
		}
	}
}
