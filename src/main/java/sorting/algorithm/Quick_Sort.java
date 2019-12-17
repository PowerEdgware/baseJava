package sorting.algorithm;

import java.util.Arrays;

public class Quick_Sort {
	static int loop = 0;

	public static void quick_sort(int arr[], int low, int high) {
		if (low < high) {
			int base = arr[low];
			int left = low, right = high;
			while (left < right) {
				loop++;
				while (left < right && arr[right] > base) {
					right--;
				}
				if (left < right) {
					arr[left] = arr[right];
					left++;
				}
				while (left < right && arr[left] < base) {
					left++;
				}
				if (left < right) {
					arr[right] = arr[left];
					right--;
				}
			}
			System.out.println(loop + ",left=" + left + ",right=" + right);
			arr[left] = base;
			System.out.println(Arrays.toString(arr));
			quick_sort(arr, low, left - 1);
			quick_sort(arr, left + 1, high);
		}
	}

	public static void main(String[] args) {

		int arr[] = { 1, 2, 1, 3, -1, 5, 0 };
		 // arr= { 1, 2,4, 5 };
		System.out.println("before quick_sort=" + Arrays.toString(arr));
		Quick_Sort quick_Sort = new Quick_Sort();

		quick_Sort.quick_sort(arr, 0, arr.length - 1);

		System.out.println("after quick_sort=" + Arrays.toString(arr));
	}
}
