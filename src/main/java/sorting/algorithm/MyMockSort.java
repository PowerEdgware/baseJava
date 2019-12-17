package sorting.algorithm;

import java.util.Arrays;

public class MyMockSort {

	public static void main(String[] args) {
		int arr[] = { 1, -1, 0, 3, -5, 1, 4, 2 };
		System.out.println("before sort=" + Arrays.toString(arr));
		// bubble_sort(arr);
		// System.out.println("after bubble_sort=" + Arrays.toString(arr));

		// select_sort(arr);
		// System.out.println("after select_sort=" + Arrays.toString(arr));

		// insert_sort(arr);
//		insert_sort_optimization(arr);
//		System.out.println("after insert_sort=" + Arrays.toString(arr));
		
		quick_sort(arr, 0, arr.length-1);
		System.out.println("after quick_sort=" + Arrays.toString(arr));
	}

	// TODO 冒泡-从前往后推
	static void bubble_sort(int arr[]) {
		int len = arr.length;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
				}
			}
			// 内层循环完毕把最大的放到数组最右边,每次循环如此，形成冒泡趋势
		}
	}
	// 时间复杂度：O(n^2)
	// 外层---> 0 1 2 ... len-1
	// 内层---> len-1 len-2 ... 0
	// 复杂度为：n(A1+An)/2即为：n(n-1)/2 ~ O(N^2)

	static void swap(int[] arr, int low, int high) {
		int tmp = arr[high];
		arr[high] = arr[low];
		arr[low] = tmp;
	}

	// TODO select_sort-每次选择数组中最小的放入指定位置
	static void select_sort(int arr[]) {
		int len = arr.length;
		int min_target_index;
		for (int i = 0; i < len; i++) {
			min_target_index = i;
			for (int j = i + 1; j < len; j++) {
				if (arr[j] < arr[min_target_index]) {
					min_target_index = j;
				}
			}
			// 把最小的放入i位置，同时把i处的值放置到剩余数据指定最小值位置处，用剩下的继续选择比较
			int tmp = arr[i];
			arr[i] = arr[min_target_index];
			arr[min_target_index] = tmp;
		}
	}
	// 时间复杂度：O(n^2)->参照bubble_sort

	// TODO insert_sort:i处，从右往左依次比较，大于target的右移一位，最终把target放置到j的位置即可完成排序
	static void insert_sort(int[] arr) {
		int len = arr.length;
		int j = 0;
		for (int i = 0; i < len; i++) {
			int target = arr[i];
			for (j = i; j > 0;) {
				if (target < arr[j - 1]) {
					arr[j] = arr[j - 1];
					j--;
				} else {
					break;
				}
			}
			arr[j] = target;
		}
	}

	// TODO insert_sort 优化  不稳定
	static void insert_sort_optimization(int[] arr) {
		int i, j;
		for (i = 0; i < arr.length; i++) {
			int target = arr[i];
			j = i;// 每次从i处开始计算
			while (j > 0 && arr[j - 1] > target) {
				arr[j] = arr[j - 1];// 右移
				j--;
			}
			// 一次外层结束，把target插入到j处
			arr[j] = target;
		}
	}
	// 时间复杂度O(n2)
	// 最差情况：反序，需要移动n*(n-1)/2个元素
	// 最好情况：正序，不需要移动元素
	// 数组在已排序或者是“近似排序”时，插入排序效率的最好情况运行时间为O(n)；
	// 插入排序最坏情况运行时间和平均情况运行时间都为O(n2)。
	// 通常，插入排序呈现出二次排序算法中的最佳性能。
	// 对于具有较少元素（如n<=15）的列表来说，二次算法十分有效。
	// 在列表已被排序时，插入排序是线性算法O(n)。
	// 在列表“近似排序”时，插入排序仍然是线性算法。
	// 在列表的许多元素已位于正确的位置上时，就会出现“近似排序”的条件。
	// 通过使用O(nlog2n)效率的算法（如快速排序）对数组进行部分排序，
	// 然后再进行insert排序，某些高级的排序算法就是这样实现的。

	// TODO 快速排序 不稳定
	static void quick_sort(int arr[], int left, int right) {
		if (left < right) {
			int _left = left, _right = right;
			int base = arr[_left];
			while (_left < _right) {
				// 1.从右边_right找比base小的，放入_left位置，填充_left
				while (_left < _right && arr[_right] > base) {
					_right--;
				}
				// 2.判断_right是否自减到_left
				if (_left < _right) {
					arr[_left++] = arr[_right];
				}

				// 3.从左边_left找比base大的，放入_right位置，填充_right位置
				while (_left < _right && arr[_left] < base) {
					_left++;
				}
				// 4.判断_left是否自增到_right
				if (_left < _right) {
					arr[_right--] = arr[_left];
				}
				// 一次找完毕，相当于把首先找到的比base小的站在了base左边，比base大的站在了右边.只做了一次填充
				// 还需要继续比较_left和_right.重复上述流程
			}
			// 5.循环完毕，_left与_right碰头，此时应该把base填充到_left处
			arr[_left] = base;
			
			// 6.此时在base左边的都比base小，右边的比base大。但是左右两边并没有完成顺序排序，所以分别对左右两边的数据进行递归排序，直到有序为止。
			quick_sort(arr, left, _left - 1);
			quick_sort(arr, _left + 1, right);
		}
	}
	//平均时间复杂度为O（n×log（n）），最糟糕时复杂度为O（n^2）
}
