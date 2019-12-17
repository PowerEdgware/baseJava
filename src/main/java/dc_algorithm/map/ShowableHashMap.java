package dc_algorithm.map;

import java.util.Stack;


public class ShowableHashMap<K, V> extends MockHashMap<K, V> {

	/**
	 * @Fields field:field:{todo}(用一句话描述这个变量表示什么)
	 */

	private static final long serialVersionUID = -8982935409815985385L;

	static final int DEFAULT_INITIAL_CAPACITY = MockHashMap.MIN_TREEIFY_CAPACITY;

	public ShowableHashMap() {
		this(DEFAULT_INITIAL_CAPACITY);
	}

	public ShowableHashMap(int initialCapacity) {
		super(DEFAULT_INITIAL_CAPACITY);
	}

	@Override
	void afterNodeInsertion(boolean evict) {
		super.afterNodeInsertion(evict);
	}

	@Override
	void afterNodeAccess(dc_algorithm.map.MockHashMap.Node<K, V> p) {
		if (p instanceof MockHashMap.TreeNode && isGetMethod()) {
			displayTree((TreeNode<K, V>) p);
		}
	}

	@Override
	public V get(Object key) {
		Node<K, V> e;
		if ((e = getNode(hash(key), key)) == null)
			return null;
		afterNodeAccess(e);
		return e.value;
	}

	boolean isGetMethod() {
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		if (elements == null)
			return false;
		Class<?> clazz = this.getClass();
		for (StackTraceElement ste : elements) {
			String clazzName = ste.getClassName();
			String methodName = ste.getMethodName();
			// System.out.println(Thread.currentThread().getName() + "\t" +
			// clazzName + "\t" + methodName);
			if (clazz.getName().equals(clazzName) && "get".equals(methodName)) {
				return true;
			}
		}
		return false;
	}

	int nblank() {
		int itemSize = this.size;
		if (itemSize == 0)
			return MockHashMap.DEFAULT_INITIAL_CAPACITY;
		int n = itemSize - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return n < MockHashMap.DEFAULT_INITIAL_CAPACITY ? MockHashMap.DEFAULT_INITIAL_CAPACITY : n + 1;
	}

	void displayTree(MockHashMap.TreeNode<K, V> node) {
		if (node == null)
			return;
		Stack<TreeNode<K, V>> globalStack = new Stack<>();
		globalStack.push(node.root());
		int nBlanks = nblank();
		boolean isRowEmpty = false;
		System.out.println("========================nBlanks:" + nBlanks + "===================================");
		while (isRowEmpty == false) {
			Stack<TreeNode<K, V>> localStack = new Stack<>();
			isRowEmpty = true;
			for (int j = 0; j < nBlanks; j++)
				System.out.print(' ');
			while (globalStack.isEmpty() == false) {
				TreeNode<K, V> temp = globalStack.pop();
				if (temp != null) {
					System.out.print(temp.key + (temp.red ? "R" : "B"));
					localStack.push(temp.left);
					localStack.push(temp.right);
					if (temp.left != null || temp.right != null) {
						isRowEmpty = false;
					}
				} else {
					System.out.print("..");
					localStack.push(null);
					localStack.push(null);
				}
				for (int j = 0; j < nBlanks * 2 - 2; j++) {
					System.out.print(' ');
				}
			}
			System.out.println();
			nBlanks /= 2;
			while (localStack.isEmpty() == false)
				globalStack.push(localStack.pop());
		}
		System.out.println("===========================================================");
	}

}
