package dc_algorithm.simple_sort;

import java.util.Random;

public class InsertSort {

	private long[] arr;
	private int nElems;

	public InsertSort(int size) {
		arr = new long[size];
		nElems = 0;
	}

	public int size() {
		return nElems;
	}

	public void insert(long value) {
		arr[nElems++] = value;
	}

	public void dispaly() {
		if (arr.length == 0) {
			System.out.println("[]");
			return;
		}
		for (int i = 0; i < nElems; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public void insert_sort() {
		int in, out;
		long target;
		for (out = 1; out < nElems; out++) {
			target = arr[out];
			in = out;
			while (in > 0 && arr[in - 1] > target) {
				arr[in] = arr[in - 1];
				in--;
			}
			if (in != out)
				arr[in] = target;
		}
	}

	// TODO EXEC
	// EXEC 3.3 删除有序数组中的重复项(假设数据已有序)
	public void noDups() {
		for (int i = 0; i < nElems - 1; i++) {
			int dup_item_index = i;
			while ((dup_item_index) < nElems - 1 && arr[i] == arr[dup_item_index + 1]) {
				dup_item_index++;
			}
			if (dup_item_index == i)
				continue;
			// move to low by step
			int step = dup_item_index - i;
			for (int k = dup_item_index + 1; k < nElems; k++) {
				arr[k - step] = arr[k];
				arr[k] = 0;// let gc clear it ~~
			}
			nElems -= step;// resize
		}
	}

	static long default_value = -1;

	// TODO EXEC 3.6 假设数据全部非负
	public void noDups_insert_sort() {
		int in, out, dup_num = 0;
		long target;
		for (out = 1; out < nElems; out++) {
			target = arr[out];
			// find dups and set -1
			for (int j = 0; j < out; j++) {
				if (target == arr[j]) {
					target = default_value;
					dup_num++;
				}
			}
			in = out;
			while (in > 0 && arr[in - 1] > target) {
				arr[in] = arr[in - 1];
				in--;
			}
			arr[in] = target;
		}
		System.out.println("dup_num=" + dup_num);
		if (dup_num == 0) {
			return;
		}
		// remove -1 item
		for (int k = dup_num; k < nElems; k++) {
			arr[k - dup_num] = arr[k];
			arr[k] = 0;
		}
		nElems -= dup_num;
	}

	public static void main(String[] args) {

		// int size = 100000;
		int size = 20;
		InsertSort insert = new InsertSort(size);

		for (int e = 0; e < size; e++) {
			insert.insert(new Random().nextInt(size));
			// insert.insert(10);
		}
		System.out.println("Before sort:");
		insert.dispaly();

		// long start = System.currentTimeMillis();
		insert.insert_sort();
		// long end = System.currentTimeMillis();
		// System.out.println("insert_sort total data=" + size + " cost=" + (end
		// - start) + " ms");
		// insert_sort total data=100000 cost=2943 ms
		System.out.println("After sort:" + "arr len=" + insert.size());
		insert.dispaly();

		// 时间复杂度 O(n^2)

		// TODO EXEC 3.3
		System.out.println("=====NODUPS EXEC=====");
		// insert.noDups();
		//
		// System.out.println("After noDups:" + ",arr len=" + insert.size());
		// insert.dispaly();

		// EXEC 3.6
		insert.noDups_insert_sort();
		System.out.println("After noDups_insert_sort:" + ",arr len=" + insert.size());
		insert.dispaly();

	}
}
