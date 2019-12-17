package dc_algorithm.simple_sort;

/**
 * 练习
 * 
 * @author yu
 *
 */
public class ExecDemo {

	public static void main(String[] args) {
		long arr[] = { 1, 3, 0, -1, 7, 0, 9, 3, 1 ,9,9,03,7,3,5,8,10,-2,3};
		System.out.println("before bubble:");
		display(arr);
		
		prior_bubble_sort(arr);
		System.out.println("after bubble:");
		display(arr);
	}

	//EXEC3.1 双向移动的冒泡排序
	static void prior_bubble_sort(long[] arr) {

		int in, out, mark = 0;
		int len = arr.length;
		for (out = len - 1; out > 0;) {
			for (in = mark; in < out; in++) {
				if (arr[in] > arr[in + 1]) {
					long temp = arr[in];
					arr[in] = arr[in + 1];
					arr[in + 1] = temp;
				}
			}
			out--;

			for (int back = out; back > mark; back--) {
				if (arr[back] < arr[back - 1]) {
					long temp = arr[back];
					arr[back] = arr[back-1];
					arr[back-1] = temp;
				}
			}
			mark++;
			if (out <= mark) {
				break;
			}
		}
	}

	static void display(long arr[]) {
		if (arr.length == 0) {
			System.out.println("[]");
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
