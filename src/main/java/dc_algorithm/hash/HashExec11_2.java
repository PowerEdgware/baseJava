package dc_algorithm.hash;

import java.util.Random;

/**
 * 线性探测哈希表字符串存储，假设全部小写.(字符串key转成数组坐标)
 * 
 * @author yu
 *
 */
public class HashExec11_2 {

	private DataItem[] hashArray;
	private int arraySize;

	public HashExec11_2(int size) {
		this.hashArray = new DataItem[size];
		arraySize = size;
	}

	public void insert(DataItem item) {
		int hashIndex = hashFunc(item.data);
		while (hashArray[hashIndex] != null) {
			if (hashArray[hashIndex].data.equals(item.data)) {
				break;
			}
			hashIndex++;
			hashIndex = hashIndex % arraySize;
		}
		hashArray[hashIndex] = item;
	}

	int hashFunc(String key) {
		int hashIndex = 0;
		for (int k = 0; k < key.length(); k++) {
			int letter = key.charAt(k) - 'a';
			hashIndex = (hashIndex * 26 + letter) % arraySize;
		}
		return hashIndex;
	}

	public DataItem remove(String key) {
		int hashIndex = hashFunc(key);
		while (hashArray[hashIndex] != null) {
			DataItem item = hashArray[hashIndex];
			if (item.data.equals(key)) {
				hashArray[hashIndex] = null;
				return item;
			}
			hashIndex++;
			hashIndex = hashIndex % arraySize;
		}
		return null;
	}

	public DataItem find(String key) {
		int hashIndex = hashFunc(key);
		while (hashArray[hashIndex] != null) {
			DataItem item = hashArray[hashIndex];
			if (item.data.equals(key)) {
				return item;
			}
			hashIndex++;
			hashIndex = hashIndex % arraySize;
		}
		return null;
	}

	public void displayTable() {
		for (int j = 0; j < arraySize; j++) {
			if (hashArray[j] != null)
				System.out.print(hashArray[j].getData() + " ");
			else
				System.out.print(" ** ");
		}
		System.out.println();
	}
	
	public DataItem rndData() {
		for (int x = 0;;) {
			Random r = new Random();
			x = r.nextInt(arraySize);
			if (hashArray[x] != null)
				return hashArray[x];
		}
	}

	class DataItem {
		String data;

		public DataItem(String data) {
			this.data = data;
		}

		public String getData() {
			return this.data;
		}

		@Override
		public String toString() {
			return this.getClass().getSimpleName() + " >> data=" + data;
		}
	}

	public static void main(String[] args) {
		int size = 20;
		HashExec11_2 hashExec = new HashExec11_2(size);
		for (int i = 0; i < 10; i++) {
			DataItem dataItem = hashExec.new DataItem(random_str(5));
			hashExec.insert(dataItem);
		}
		
		hashExec.displayTable();
		
		DataItem item=hashExec.rndData();
		DataItem dataItem=hashExec.remove(item.getData());
		System.out.println("deleted?"+dataItem);
		
		hashExec.displayTable();
		
		item=hashExec.find("aaa");
		System.out.println("Found?="+item);

	}

	static String random_str(int len) {
		String orig = "abcdefghijklmnopqrstuvwxyz";
		StringBuilder build = new StringBuilder(len);
		Random rnd = new Random();
		char[] cs = orig.toCharArray();
		for (int i = 0; i < len; i++) {
			build.append(cs[rnd.nextInt(cs.length)]);
		}
		return build.toString();
	}
}
