/**
 * 
 */
package dc_algorithm.hash;

import java.util.Random;

/**
 * 自动扩表
 */
public class HashExec11_4 {
	private DataItem[] hashArray;
	private int arraySize;
	private int nItems;

	public HashExec11_4(int initsize) {
		this.hashArray = new DataItem[initsize];
		this.arraySize = initsize;
		nItems = 0;
	}

	// 允许重复
	public void insert(DataItem item) {
		int key = item.getKey();
		int hashIndex = hashFunc(key);
		double loadFactor = nItems / (arraySize * 1.0);
		if (loadFactor > 0.5) {
			resize_copy();
		}

		while (hashArray[hashIndex] != null) {
			hashIndex++;
			hashIndex %= arraySize;// wrap around if need
		}
		hashArray[hashIndex] = item;
		nItems++;
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
				--nItems;
				return target;
			}
			++hashIndex;
			hashIndex = hashFunc(hashIndex);
		}
		return null;
	}

	synchronized void resize_copy() {
		if (nItems == 0) {
			return;
		}
		DataItem[] oldArray = hashArray;
		int newArraySize = getNewArraySize();
		DataItem[] newArray = new DataItem[newArraySize];
		// cp old data to new array;
		int oldSize = arraySize;
		for (int j = 0; j < oldSize; j++) {
			if (oldArray[j] != null) {
				int key = oldArray[j].getKey();
				int newHashIndex = key % newArraySize;

				while (newArray[newHashIndex] != null) {
					newHashIndex++;
					newHashIndex = newHashIndex % newArraySize;
				}
				newArray[newHashIndex] = oldArray[j];
				oldArray[j] = null;
			}
		}
		this.arraySize = newArraySize;
		hashArray = newArray;
	}

	public int size() {
		return this.nItems;
	}

	public int tableSize() {
		return arraySize;
	}

	// TODO 不少于旧的数组大小的2倍
	int getNewArraySize() {
		int newSize = arraySize * 2;
		return getPrime(newSize);
	}

	// 获取质数
	int getPrime(int newSize) {
		for (int j = newSize;; j++) {
			if (isPrime(j))
				return j;
		}
	}

	boolean isPrime(int n) {
		for (int x = 2; x < n / 2; x++) {
			if (n % x == 0)
				return false;
		}
		return true;
	}

	public int rndKey() {
		for (int x = 0;;) {
			Random r = new Random();
			x = r.nextInt(arraySize);
			if (hashArray[x] != null)
				return hashArray[x].getKey();
		}
	}

	public void displayTable() {
		for (int j = 0; j < arraySize; j++) {
			if (hashArray[j] != null)
				System.out.print(hashArray[j].getKey() + " ");
			else
				System.out.print(" ** ");
		}
		System.out.println();
	}

	int hashFunc(int key) {
		return key % this.arraySize;
	}

	public static void main(String[] args) {

		int initsize = 11;
		HashExec11_4 mockHashtable = new HashExec11_4(initsize);

		for (int k = 0; k < 3; k++) {
			DataItem item = new DataItem((int) (Math.random() * 99));
			mockHashtable.insert(item);
		}
		System.out.println("list table,before resize:");
		mockHashtable.displayTable();
		
		System.out.println("re_insert:");
		
		for (int k = 0; k < 8; k++) {
			DataItem item = new DataItem((int) (Math.random() * 99));
			mockHashtable.insert(item);
		}

		System.out.println("after operated:");
		mockHashtable.displayTable();

		System.out.println("table size="+mockHashtable.tableSize());
		System.out.println("items size="+mockHashtable.size());
	}

	static boolean _isPrime(int n) {
		for (int x = 2; x < n / 2; x++) {
			if (n % x == 0)
				return false;
		}
		return true;
	}

}
