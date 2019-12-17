package dc_algorithm.hash;


/**
 * 
 * @author yu 链地址法
 */
public class MockHashChain {

	private SortedList[] hashArray;
	private int arraySize;

	public MockHashChain(int size) {
		arraySize = size;
		hashArray = new SortedList[arraySize];
		init();
	}

	void init() {
		for (int j = 0; j < arraySize; j++)
			hashArray[j] = new SortedList();
	}

	public void insert(Link l) {
		int key = l.getKey();
		int hashIndex = hashFunc(key);
		hashArray[hashIndex].insert(l);
	}

	public void delete(int key) {
		int hashIndex = hashFunc(key);
		hashArray[hashIndex].delete(key);
	}

	public boolean delete_mine(int key) {
		int hashIndex = hashFunc(key);
		return hashArray[hashIndex].delete_mine(key);
	}

	public Link find(int key) {
		int hashIndex = hashFunc(key);
		return hashArray[hashIndex].find(key);
	}

	public void displayTable() {
		for (int j = 0; j < arraySize; j++) {
			System.out.print("Hash Bucket["+j + "] → ");// display table index
			hashArray[j].displayList();// display table sortedList
			if(j<arraySize-1)
			System.out.println("   	  ↓");
		}
	}

	int hashFunc(int key) {
		return key % arraySize;
	}

	// testtesttesttest
	//TODO 
	public static void main(String[] args) throws Exception {
		int size = 25;
		MockHashChain hashChain = new MockHashChain(size);

		Link link=null;
		int targetKey=0;
		for(int x=0;x<2*size;x++){
			targetKey=(int) (Math.random() * 99);
			 link=new Link(targetKey);
			hashChain.insert(link);
		}
		link=new Link(110);
		System.out.println(link);
		hashChain.insert(link);//TODO 插入同一个对象，迭代时造成死循环
		
		try {
//			System.out.println("Enter enter continue");
//			System.in.read();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.err.println("out table: ");
		Thread.sleep(500);
		hashChain.displayTable();
		
		link=hashChain.find(targetKey);
		System.out.println("Found ?="+link);
		
		try {
			int notExistKey=120;
//			hashChain.delete(notExistKey); possible java.lang.NullPointerException if key not exist
			boolean rst=hashChain.delete_mine(notExistKey);
			System.out.println("key="+notExistKey+",deleted?"+rst);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.err.println("---------------after operated:----------------- ");
		Thread.sleep(500);
		System.out.println();
		hashChain.displayTable();
	}

}

/**
 * 
 * 有序链表 LinkedList
 *
 */
class SortedList {
	private Link first;

	SortedList() {
		this.first = null;
	}

	//TODO 注意去除 相等的对象
	void insert(Link link) {
		int key = link.getKey();
		Link current = first;
		Link previous = null;
		
		while (current != null) {
			if (current.getKey() < key) {
				previous = current;
				current = current.next;
			} else
				break;
		}
		// 前面没有数据，新入元素就是首数据项，否则，previous的next就是新入元素，新入元素的下一项就是current
		// ... previous-> ^ -> current ....
		// |
		// newLink
		if (previous == null) {
			first = link;
		} else {
			previous.next = link;
		}
		link.next = current;
	}

	void delete(int key) {
		Link current = first;
		Link previous = null;
		while (current != null && current.getKey() != key) {
			previous = current;
			current = current.next;
		}
		if (previous == null)
			first = first.next;
		else
			previous.next = current.next;
	}

	boolean delete_mine(int key) {
		Link current = first;
		Link previous = null;
		while (current != null && current.getKey() < key) {
			previous = current;
			current = current.next;
		}
		if (previous == null) {
			if (first != null && first.getKey() == key) {
				first = first.next;
				return true;
			}
		} else {
			if (current != null && current.getKey() == key) {
				previous.next = current.next;
				return true;
			}
		}

		return false;
	}

	Link find(int key) {
		Link current = first;

		while (current != null && current.getKey() <= key) {
			if (current.getKey() == key)
				return current;
			current = current.next;
		}
		return null;
	}

	void displayList() {
		System.out.print(" SortList (first---->last):");
		Link current = first;
		while (current != null) {
			current.displayLink();
			current = current.next;
		}
		System.out.println();
	}

}

/// LINK CLASS
class Link {
	private int iData;
	Link next;

	public Link(int i) {
		this.iData = i;
		this.next = null;
	}

	int getKey() {
		return this.iData;
	}

	void displayLink() {
		System.out.print(" Link →" + this.iData + " ");
	}
	@Override
	public String toString() {
		return "toString Link:" + this.iData + " ";
	}
}
