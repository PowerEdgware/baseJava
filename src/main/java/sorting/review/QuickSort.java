package sorting.review;

import java.util.Arrays;
import java.util.Random;

/**
 * 
 * @ClassName: QuickSort
 * @Description: 快速排序使用分治策略(Divide and Conquer)来把一个序列分为两个子序列。步骤为：
 * 
 *               从序列中挑出一个元素，作为"基准"(pivot).
 *               把所有比基准值小的元素放在基准前面，所有比基准值大的元素放在基准的后面（相同的数可以到任一边），这个称为分区(partition)操作。
 *               对每个分区递归地进行步骤1~2，递归的结束条件是序列的大小是0或1，这时整体已经被排好序了。
 * @author shangcj
 * @date 2018年6月6日
 *
 */
public class QuickSort {

	public static void main(String[] args) {
		int arr[] = { 1, 9, 3, 2, 0, 5, 8, 7, 1, 18, 5 };
		System.out.println("Before sort:");
		System.out.println(Arrays.toString(arr));
		quick_sort(arr);
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
		quick_sort(a1);// sort data=100000 cost=29 ms
		long end = System.currentTimeMillis();
		System.out.println("sort data=" + bound + " cost=" + (end - start) + " ms");
	}

	static void quick_sort(int[] a) {
		rec_quick_sort(a, 0, a.length - 1);
	}

	private static void rec_quick_sort(int[] a, int left, int right) {
		if (left >= right)
			return;
		int l = left, r = right;// 分区左右边界
		// 1.分区
		int target = a[right];// 基准元素
		while (left < right) {
			while (left < right && a[left] < target)// 找出左边第一个大于target的元素（left<right-->防止越界比较,而且在right右边的数据[如果有的话]已经大于target）
				left++;
			if (left < right)
				a[right--] = a[left];// 左起，把首个大于等于target的元素放置到right元素位置，right-1位置开始右边比较
			while (left < right && a[right] > target)// 找出右边第一个小于target的元素（left<right-->防止越界比较）
				right--;
			if (left < right)
				a[left++] = a[right];// 右起，把首个小于等于target的元素放置left元素位置，从left+1位置再开始左边比较
		}
		// 2.分区完毕，把基准元素放入left==right位置
		a[left] = target;

		// 3.以分区位置为标准，对分区左右两边数组分别递归处理
		rec_quick_sort(a, l, left - 1);

		rec_quick_sort(a, left + 1, r);
	}
}
