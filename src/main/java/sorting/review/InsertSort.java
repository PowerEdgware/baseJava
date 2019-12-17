package sorting.review;

import java.util.Arrays;
import java.util.Random;

/**
 * 具体算法描述如下：

从第一个元素开始，该元素可以认为已经被排序
取出下一个元素，在已经排序的元素序列中从后向前扫描
如果该元素（已排序）大于新元素，将该元素移到下一位置
重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
将新元素插入到该位置后
重复步骤2~5
 * @author 
 *
 */
public class InsertSort {
	
	public static void main(String[] args) {
		int arr[] = { 9, 3, 2, 0, 5, 8, 7, 1, 9 };
		System.out.println("Before sort:");
		System.out.println(Arrays.toString(arr));
		insert_sort(arr);
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
		insert_sort(a);//sort data=100000 cost=3193 ms
		long end = System.currentTimeMillis();
		System.out.println("sort data=" + bound + " cost=" + (end - start) + " ms");
		
	}

	static void insert_sort(int[] a) {
		int l = a.length;
		for (int i = 0; i < l - 1; i++) {
			int target = a[i + 1];
			int j = i;
			while (j >= 0 && a[j] > target) {
				a[j + 1] = a[j];
				j--;
			}
			a[j+1] = target;
		}
	}
	
}
