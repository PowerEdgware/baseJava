package dc_algorithm.heap;

import java.util.Random;

/**
 * 堆排序
 *
 */
public class HeapSort {

	public static void main(String[] args) {

		int size = 1_000_000;//Heap Sort cost=5475 ms for size=10000000
		long arr[] = new long[size];
		Random rnd = new Random();
		for (int x = 0; x < size; x++) {
			arr[x] = rnd.nextInt(size*2-1);
		}
		HeapSort heapSort = new HeapSort();
		// heapSort.build_max_heap(arr);
		System.out.println("Before Sort:");
		// heapSort.displayHeapArr(arr);
		System.out.println("After Sort:");
		long start = System.currentTimeMillis();
		heapSort.heap_sort(arr);
		long end = System.currentTimeMillis();
		System.out.println("Heap Sort cost=" + (end - start) + " ms for size=" + size);
//		heapSort.displayHeapArr(arr);

	}

	void heap_sort(long[] arr) {
		// 构建大顶堆
		this.build_max_heap(arr);

		int size = arr.length;
		while (size > 0) {
			// 交换首元素和末元素
			long max = arr[0];
			long e = arr[--size];
			arr[size] = max;

			// 下沉首元素
			shiftDown(0, e, arr, size);
		}
	}

	/**
	 * 构建大顶堆
	 * 
	 * @param arr
	 */
	void build_max_heap(long arr[]) {
		int size = arr.length;
		// 去除一半数据的叶节点，从最后一个包含叶节点的节点开始下沉(shiftDown)
		for (int index = size / 2 - 1; index >= 0; index--) {
			// 以下是下沉操作
			long e = arr[index];
			int startPos = index;
			while (startPos < size) {
				int compIndex = 2 * startPos + 1;
				if (compIndex >= size)
					break;
				int right = compIndex + 1;
				long largest = arr[compIndex];
				if (right < size && arr[compIndex] < arr[right]) {
					largest = arr[compIndex = right];
				}
				if (e >= largest)
					break;
				arr[startPos] = largest;
				startPos = compIndex;
			}
			arr[startPos] = e;
		}
	}

	void shiftDown(int ePos, long e, long arr[], int size) {
		// 下沉元素e
		int half = size / 2;// 非叶子节点最多只有一半
		while (ePos < half) {
			int child = 2 * ePos + 1;// left

			int right = child + 1;
			long largest = arr[child];
			if (right < size && largest < arr[right])
				largest = arr[child = right];
			if (e >= largest)
				break;
			arr[ePos] = largest;
			ePos = child;
		}
		arr[ePos] = e;
	}

	public void displayHeapArr(long queue[]) {
		// Array out
		System.out.print("Heap Array:");
		if (queue.length < 50)
			for (int i = 0; i < queue.length; i++) {
				System.out.print(queue[i] + " ");
			}
		int dataSize = queue.length;
		System.out.println();
		// Heap Format
		int nBlanks = 1;
		while (nBlanks < dataSize) {
			nBlanks *= 2;
		}
		int itemPerRow = 1;
		int colunm = 0, j = 0;
		String dots = "----------------------------------";
		System.out.println(dots + dots);
		while (dataSize > 0) {
			if (colunm == 0) {
				for (int k = 0; k < nBlanks; k++) {
					System.out.print(' ');
				}
			}
			System.out.print(queue[j]);
			if (++j == dataSize)
				break;
			if (++colunm == itemPerRow) {
				nBlanks /= 2;
				itemPerRow *= 2;
				colunm = 0;
				System.out.println();
			} else {
				for (int k = 0; k < nBlanks * 2 - 2; k++) {
					System.out.print(' ');
				}
			}
		}
		System.out.println("\n" + dots + dots);
	}
}
