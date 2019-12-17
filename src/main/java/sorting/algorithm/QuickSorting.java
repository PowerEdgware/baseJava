package sorting.algorithm;

import java.util.Arrays;

public class QuickSorting {

	public void quick_sort(int arr[], int start, int end) {
		System.out.println("start="+start+",end="+end);
		if (start < end) {
			int base = arr[start];

			int left = start, right = end;

			while (left < right) {
				int loop = 0;
				while (left < right && arr[right] > base) {
					right--;
					loop++;
				}
				System.out.println(arr[right] + "," + base + ",loop=" + loop);
				if (left < right) {
					arr[left] = arr[right];
					left++;
				}
				while (left < right && arr[left] <= base) {
					left++;
				}
				if (left < right) {
					arr[right] = arr[left];
					right--;
				}
			}
			arr[left] = base;

			quick_sort(arr, start, left - 1);
			quick_sort(arr, left + 1, end);
		}
	}

	public static void main(String[] args) {
		int arr[] = { 72, 34, 57, 72, 88, 88, 42, 83, 72, 85, 85 };
//		int arr[]={72,73,73,73,73};
		// int xy=1000;
		// int arr1[]=new int[xy];

		// for(int x=1;x<xy;x+=2){
		// //arr1[x--]=x;
		// }
		System.out.println("before sort=" + Arrays.toString(arr));
		long start = System.currentTimeMillis();
		QuickSorting quickSorting = new QuickSorting();
		quickSorting.quick_sort(arr, 0, arr.length - 1);

		long end = System.currentTimeMillis();
		System.out.println("after sort=" + Arrays.toString(arr) + ",cost=" + (end - start));
	}
}
