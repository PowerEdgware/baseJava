/**
 * 
 */
package dc_algorithm.hash;

import java.util.Objects;
import java.util.Random;

/**
 * @author yu 开放地址法--数据发生冲突时，找到下一个数组空位，填入。不需要再用哈希函数得到数组的下标。 1.线性探测-原始聚集
 *         2.二次探测-二次聚集 3.再哈希发
 *         1.2的探测序列不依赖关键字，都是在初始映射位置上加或二次相乘探测序列。探测序列步长总是+1或者+1,4,9,16...N^2
 *         3.把key使用不同的hash函数再哈希化，此结果作为步长，对指定的关键字，步长不变，但不同的关键字步长不一样。 TODO
 *         再哈希化函数和第一个hash函数不同，且不回返0(否则将陷入死循环)。
 */
public class MockHashtable {
	static int deletedItemKey = -1;
	private DataItem[] hashArray;
	private int size;
	// private DataItem noItem;// for deleted item

	public MockHashtable(int initsize) {
		this.hashArray = new DataItem[initsize];
		this.size = initsize;
		// this.noItem = new DataItem(deletedItemKey);
	}

	// 允许重复
	public void insert(DataItem item) {
		// assume data table not full
		int key = item.getKey();
		int hashIndex = hashFunc(key);

		while (hashArray[hashIndex] != null) {
			hashIndex++;
			hashIndex %= size;// wrap around if need
		}
		hashArray[hashIndex] = item;
	}

	public DataItem find(int key) {
		int hashIndex = hashFunc(key);
		// 不同的key相同的hashIndex
		while (hashArray[hashIndex] != null) {
			if (hashArray[hashIndex].getKey() == key)
				return hashArray[hashIndex];
			hashIndex++;
			hashIndex = hashFunc(hashIndex);
		}
		return null;
	}

	public DataItem delete(int key) {
		int hashIndex = hashFunc(key);

		while (hashArray[hashIndex] != null) {
			if (hashArray[hashIndex].getKey() == key) {
				DataItem target = hashArray[hashIndex];
				hashArray[hashIndex] = null;
				return target;
			}
			++hashIndex;
			hashIndex = hashFunc(hashIndex);
		}
		return null;
	}

	public int rndKey() throws NoSuchElementException {
		for (int x = 0;;) {
			Random r = new Random();
			x = r.nextInt(size);
			if (hashArray[x] != null)
				return hashArray[x].getKey();
		}
		// throw new NoSuchElementException("table empty!");
	}

	public void displayTable() {
		for (int j = 0; j < size; j++) {
			if (hashArray[j] != null)
				System.out.print(hashArray[j].getKey() + " ");
			else
				System.out.print(" ** ");
		}
		System.out.println();
	}

	int hashFunc(int key) {
		return key % this.size;
	}

	////////////////////// 再哈希化////////////////////////
	// 再哈希化函数
	int re_hashFunc(int key) {
		// no zero ,less than array size,different from hashFunc,
		// array size must be relatively prime to 5,4,3,and 2
		// TODO 组数大小必须分别和 ...互质,constant 是质数，且小于array size
		// return constant -(key% constant)
		// sample
		return 5 - (key % 5);
	}

	public void rehash_insert(DataItem item) {
		if (Objects.isNull(item)) {
			throw new NullPointerException();
		}
		// TODO assume array not full
		int key = item.getKey();
		int hashIndex = hashFunc(key);
		int step = re_hashFunc(key);

		while (hashArray[hashIndex] != null) {
			hashIndex += step;
			hashIndex %= size;
		}
		hashArray[hashIndex] = item;
	}

	public DataItem rehash_delete(int key) {
		int hashIndex = hashFunc(key);
		int step = re_hashFunc(key);
		while (hashArray[hashIndex] != null) {
			if (hashArray[hashIndex].getKey() == key) {
				DataItem dataItem = hashArray[hashIndex];
				hashArray[hashIndex] = null;
				return dataItem;
			}
			hashIndex += step;
			hashIndex %= size;
		}
		return null;
	}

	public DataItem rehash_find(int key) {
		int hashIndex = hashFunc(key);
		int step = re_hashFunc(key);
		while (hashArray[hashIndex] != null) {
			if (hashArray[hashIndex].getKey() == key) {
				return hashArray[hashIndex];
			}
			hashIndex += step;
			hashIndex %= size;
		}
		return null;
	}

	public static void main(String[] args) {

		int initsize = 17;
		MockHashtable mockHashtable = new MockHashtable(initsize);

		for (int k = 0; k < 10; k++) {
			DataItem item = new DataItem((int) (Math.random() * 99));
			mockHashtable.rehash_insert(item);
		}
		System.out.println("list table:");
		mockHashtable.displayTable();

		try {
			int key = mockHashtable.rndKey();

			DataItem item;
			item = mockHashtable.rehash_find(key);
			System.out.println("Found ?=" + item);

			item = mockHashtable.rehash_delete(key);
			System.out.println("Deleted Item=" + item);

			item = mockHashtable.rehash_find(key);
			System.out.println("Found ?=" + item);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}

		System.out.println("after operated:");
		mockHashtable.displayTable();
	}

}

class DataItem {
	private int iData;

	DataItem(int iData) {
		this.iData = iData;
	}

	public int getKey() {
		return this.iData;
	}

	@Override
	public String toString() {
		return "DataItem >> " + iData;
	}
}

class NoSuchElementException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1112752239077654652L;

	public NoSuchElementException() {
		super();
	}

	public NoSuchElementException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoSuchElementException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchElementException(String message) {
		super(message);
	}

	public NoSuchElementException(Throwable cause) {
		super(cause);
	}
}
