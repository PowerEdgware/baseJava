package dc_algorithm.heap;

/**
 * TODO 一，堆的特点 1，他是完全二叉树 2，他常用数组实现 3，堆每个节点的关键字都>=该节点子节点的关键字
 * 
 * 完全二叉树(Complete Binary Tree) A Complete Binary Tree （CBT) is a binary tree in
 * which every level, except possibly the last, is completely filled, and all
 * nodes are as far left as possible.
 * 换句话说，完全二叉树从根结点到倒数第二层满足完美二叉树（一个深度为k(>=0)且有2^(k+1) - 1个结点的二叉树称为完美二叉树）
 * ，最后一层可以不完全填充，其叶子结点都靠左对齐。
 * 
 * 特别说明： 其实，理解完全(Complete)二叉树可以借助于栈(stack)的思想。
 * 例如，把图2.6.1中的完美(Perfect)二叉树的所有结点按照编号1, 2, 3, ..., 15依次入栈(push)。
 * 那么，对栈的每一次出栈(pop)操作后，栈里保存的结点集对应到图2.6.1上去都是一棵完全(Complete)二叉树。
 * 
 * TODO 二，优缺点 缺点 --不能顺序遍历 --不能删除指定关键字的数据 优点 --删除最大(最小)数据 --插入效率高 --适合优先级队列
 * 
 * TODO 三，操作(插入和移除-快速移除最大节点和快速插入新节点) 3.1移除（移除完毕还需要保证树的完全性和堆的基本特性）：向下筛选，也叫下沉
 * 移除是指总是移除关键字最大的节点，这个节点是根节点.同时使数组容量大小减一 步骤： 1，移走根 2，把最后一个节点移到根（保证树的完全性）
 * 3，一直向下筛选选这个节点，直到它在大于他的节点之下，小于它的节点之上。（保证堆的特性【堆每个节点的关键字都>=该节点子节点的关键字】） 向下筛选规则：
 * 目标节点和2个子节点最大的一个进行交换(目标节点被交换到他的最大子节点位置)，重复此过程，直到到达树底或者目标节点大于等于他的子节点。
 * 
 * 3.2插入：向上筛选，也叫上浮 步骤： 1，插入到数组最后一个空单元中，同时数组容量大小加一
 * 2，防止插入破坏堆的特点（堆每个节点的关键字都>=该节点子节点的关键字）需要对新插入节点进行向上筛选操作。 向上筛选规则：
 * 目标节点和他的父节点比较后交换，直到到达树根或者目标节点大于等于他的子节点。
 *
 */
public class MockHeap<E extends Comparable<E>> {
	// TODO 模拟大顶堆-有界
	private Object[] queue;
	private int dataSize;// 数据个数
	private int queueSize;

	public MockHeap(int maxSize) {
		if (maxSize <= 0)
			throw new IllegalArgumentException();
		this.queueSize = maxSize;
		this.queue = new Object[queueSize];
	}

	public boolean insert(E e) {
		if (e == null)
			throw new NullPointerException();
		if (dataSize == queueSize)
			return false;
		queue[dataSize] = e;

		shiftUp(dataSize++, e);
		return true;
	}

	public E remove() {
		if (dataSize == 0)
			return null;
		E first = (E) queue[0];

		int lastIndex = --dataSize;
		E last = (E) queue[lastIndex];
		// 删除首元素
		queue[0] = last;// 把last放在首位
		queue[lastIndex] = null;

		if (lastIndex != 0)
			shiftDown(0, last);

		return first;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int size() {
		return dataSize;
	}

	void shiftDown(int index, E e) {// 向下筛选
		while (index < dataSize) {// 此时把首位当成空位，用最后一位和首位的子节点最大的进行比较，把较大的放到首位，index增加，重复此流程
			int child = 2 * index + 1;
			int right = child + 1;
			if (child < dataSize) {
				E largeChild = (E) queue[child];
				if (right < dataSize && largeChild.compareTo((E) queue[right]) < 0) {
					largeChild = (E) queue[right];// 取出和父节点待比较的元素
					child = right;
				}
				// 父节点和较大的子节点比较
				if (e.compareTo(largeChild) >= 0)
					break;
				queue[index] = largeChild;// 继续e和最大元素为父节点的2个子节点再做同样的操作
				index = child;
			} else {
				break;
			}
		}
//		if (index >= dataSize)
//			return;
		queue[index] = e;// 把e放入指定的index位置
	}

	void shiftUp(int index, E e) {// 向上筛选
		while (index > 0) {
			int parentIndex = (index - 1) / 2;
			E p = (E) queue[parentIndex];
			if (e.compareTo(p) <= 0)
				break;
			queue[index] = p;
			index = parentIndex;
		}
		queue[index] = e;
	}

	public void displayHeap() {
		// Array out
		System.out.print("Heap Array:");
		for (int i = 0; i < queue.length; i++) {
			if (queue[i] != null)
				System.out.print(queue[i] + " ");
			else
				System.out.print("--");
		}
		System.out.println();
		// Heap Format
		int nBlanks = 32;
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
