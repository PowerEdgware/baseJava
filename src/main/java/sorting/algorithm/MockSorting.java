package sorting.algorithm;

import java.util.Arrays;

public class MockSorting {

	public static void main(String[] args) throws InterruptedException {
		int arr[] = { 3, 20, 11, 5, 6, 9, 20 };
		System.out.println("before sort=" + Arrays.toString(arr));
		// 冒泡排序
		long start = System.currentTimeMillis();
		// bubble_sort(arr);
		// Thread.sleep(100);
		long end = System.currentTimeMillis();
		// System.out.println("after bubble_sort=" + Arrays.toString(arr) +
		// ",cost=" + (end - start));
		// // 选择排序
		// start = System.currentTimeMillis();
		// select_sort(arr);
		// Thread.sleep(100);
		// end = System.currentTimeMillis();
		// System.out.println("after select_sort=" + Arrays.toString(arr) +
		// ",cost=" + (end - start));
		// // quick sort
		// start = System.currentTimeMillis();
		// quickSort(arr, 0, arr.length - 1);
		// Thread.sleep(100);
		// end = System.currentTimeMillis();
		// System.out.println("after quick_sort=" + Arrays.toString(arr) +
		// ",cost=" + (end - start));

		start = System.currentTimeMillis();
		insert_sort(arr);

		end = System.currentTimeMillis();
		System.out.println("after insert_sort=" + Arrays.toString(arr) + ",cost=" + (end - start));
	}

	static void bubble_sort(int[] arr) {
		int length = arr.length;
		for (int i = 0; i < length - 1; i++) {
			for (int j = 0; j < length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int tmp = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = tmp;
				}
			}
		}
	}

	static void select_sort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int min = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[min] > arr[j]) {
					min = j;
				}
			}
			int temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp;
		}
	}

	static int select_min(int arr[]) {
		int min = arr[0];
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] < min) {
				min = arr[i];
			}
		}
		return min;
	}

	// TODO bug
	static void quickSort(int arr[], int _left, int _right) {
		int left = _left;
		int right = _right;
		int temp = 0;
		if (left <= right) { // 待排序的元素至少有两个的情况
			temp = arr[left]; // 待排序的第一个元素作为基准元素
			while (left != right) { // 从左右两边交替扫描，直到left = right
				while (right > left && arr[right] >= temp)
					right--; // 从右往左扫描，找到第一个比基准元素小的元素
				arr[left] = arr[right]; // 找到这种元素arr[right]后与arr[left]交换

				while (left < right && arr[left] <= temp)
					left++; // 从左往右扫描，找到第一个比基准元素大的元素
				arr[right] = arr[left]; // 找到这种元素arr[left]后，与arr[right]交换

			}
			arr[right] = temp; // 基准元素归位
			quickSort(arr, _left, left - 1); // 对基准元素左边的元素进行递归排序
			quickSort(arr, right + 1, _right); // 对基准元素右边的进行递归排序
		}
	}

	static void insert_sort(int arr[]) {
		int i, j;
		for (i = 0; i < arr.length; i++) {
			int target = arr[i];
			j = i;//
			while (j > 0 && target < arr[j - 1]) {
				arr[j] = arr[j - 1];
				j--;
			}
			arr[j] = target;
		}

	}
}
