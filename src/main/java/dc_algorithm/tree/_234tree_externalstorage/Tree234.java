package dc_algorithm.tree._234tree_externalstorage;

import java.util.Arrays;
/**
 * 
 * @author shangcj
 * date:2017-10-18
 * @param <E>
 * //TODO 2-3-4树的删除再议
 */

public class Tree234<E> {

	private NodeT<E> root;
	private int size;

	public Tree234() {
	}

	public int size() {
		return this.size;
	}

	public E find(E e) {
		NodeT<E> p = root;
		while (p != null) {
			int index = p.elementIndex(e);
			if (index > 0)
				return p.getElement(index);
			p = nextChlid(p, e);
		}
		return null;
	}

	public boolean insert(E e) {
		assert e == null;
		if (root == null) {
			root = new NodeT<>();
			size++;
			return root.insertElement(e);
		}
		NodeT<E> curNode = root;
		while (curNode != null) {
			if (curNode.isFull()) {
				splitNode(curNode);
				curNode = nextChlid(curNode.getParent(), e);
			} else if (curNode.isLeaf()) {
				boolean result=curNode.insertElement(e);
				if(result)
					size++;
				return result;
			} else {
				curNode = nextChlid(curNode, e);// can't be null
			}
		}
		return false;
	}

	void splitNode(NodeT<E> nodeT) {
		NodeT<E> parent = nodeT.getParent(), child2, child3;
		// 获取数据项B，C并从当前节点中移除
		E elementB = nodeT.getElement(1), elementC = nodeT.getElement(2);
		nodeT.removeElement(elementB);
		nodeT.removeElement(elementC);

		// 获取当前节点的2个右边孩子并移除
		child2 = nodeT.disconnectChild(2);
		child3 = nodeT.disconnectChild(3);

		// 创建新的右边节点，该节点是nodeT右边兄弟节点
		NodeT<E> newRight = new NodeT<>();
		if (parent == null) {// nodeT is root
			parent = new NodeT<>();
			root = parent;
			root.connectChild(0, nodeT);
		} else {// nodeT 不是root
				// TODO
		}
		parent.insertElement(elementB);
		newRight.insertElement(elementC);

		newRight.connectChild(0, child2);
		newRight.connectChild(1, child3);

		// 获取新增B的位置，并把parent中B右边的child往右挪动位置
		int indexB = parent.elementIndex(elementB);// 或者在插入时返回新增数据的index
		int numItems = parent.getItemNums();

		for (int x = numItems - 1; x > indexB; x--) {
			NodeT<E> temp = parent.disconnectChild(x);
			parent.connectChild(x + 1, temp);
		}
		// 把新节点连到parent
		parent.connectChild(indexB + 1, newRight);

	}

	NodeT<E> nextChlid(NodeT<E> p, E e) {
		if (p == null || p.isLeaf())
			return null;
		int pos;
		Comparable<? super E> eT = (Comparable<? super E>) e;
		for (pos = 0; pos < p.numItems; pos++) {
			E ele = p.getElement(pos);
			if (eT.compareTo(ele) < 0)
				return p.getChildNode(pos);
		}
		return p.getChildNode(pos);
	}

	public void displayTree() {
		rec_dispaly(root, 0, 0);
	}

	void rec_dispaly(NodeT<E> nodeT, int level, int childNum) {
		System.out.print("level=" + level + " child=" + childNum + " ");
		nodeT.dispalyNode();

		int childLen = nodeT.childNodes.length;
		for (int j = 0; j < childLen; j++) {
			NodeT<E> nextNode = nodeT.getChildNode(j);
			if (nextNode != null) {
				rec_dispaly(nextNode, level + 1, j);
			} else {
				return;
			}
		}
	}
	//TODO 2-3-4树的删除再议

	static final class NodeT<E> {
		private static final int ORDER = 4;

		private NodeT<E> parent;
		private int numItems;// 数据项个数
		private Object[] elementData = new Object[ORDER - 1];// ordered
																// dataArray
		private NodeT<?>[] childNodes = new NodeT[ORDER];// childArray

		public int getItemNums() {
			return this.numItems;
		}

		public NodeT<E> disconnectChild(int childNum) {
			NodeT<E> temp = (NodeT<E>) this.childNodes[childNum];
			childNodes[childNum] = null;
			return temp;
		}

		public void connectChild(int childNum, NodeT<E> node) {
			childNodes[childNum] = node;
			if (node != null)
				node.parent = this;
		}

		@SuppressWarnings("unchecked")
		public NodeT<E> getChildNode(int index) {
			return (NodeT<E>) childNodes[index];
		}

		public NodeT<E> getParent() {
			return this.parent;
		}

		@SuppressWarnings("unchecked")
		public E getElement(int index) {
			if (index < 0 || index >= numItems)
				throw new IndexOutOfBoundsException("index=" + index + " out of bound");
			return (E) elementData[index];
		}

		@SuppressWarnings("unchecked")
		public int elementIndex(E e) {
			for (int i = 0; i < elementData.length; i++) {
				Object objectT = elementData[i];
				if (objectT == null)
					break;
				Comparable<? super E> eT = (Comparable<? super E>) objectT;
				if (eT.compareTo(e) == 0)
					return i;
			}
			return -1;
		}

		public boolean isLeaf() {
			return childNodes[0] == null ? true : false;
		}

		public boolean isFull() {
			return numItems == elementData.length ? true : false;
		}

		boolean insertElement(E e) {
			if (e == null)
				throw new NullPointerException();
			if (isFull()) {
				throw new IllegalArgumentException("Node is Full");
			}
			int pos;
			for (pos = 0; pos < numItems; pos++) {
				Object objectT = elementData[pos];
				if (objectT != null) {
					Comparable<? super E> eT = (Comparable<? super E>) objectT;
					int comp;
					if ((comp = eT.compareTo(e)) > 0)
						break;
					else if (comp == 0)
						return false;// no duplicated
				}
			}

			for (int j = numItems; j > pos; j--) {
				elementData[j] = elementData[j - 1];// move low to high
			}
			elementData[pos] = e;
			numItems++;
			return true;
		}

		public boolean removeElement(E e) {
			assert e == null;
			boolean matched = false;
			int pos;
			for (pos = 0; pos < numItems; pos++) {
				Object objectT = elementData[pos];
				Comparable<? super E> eT = (Comparable<? super E>) objectT;
				if (eT.compareTo(e) == 0) {
					matched = true;
					break;
				}
			}
			if (!matched)
				return false;
			for (int k = pos; k < numItems; k++)
				if (k == numItems - 1)// avoid
										// java.lang.ArrayIndexOutOfBoundsException
					elementData[k] = null;
				else
					elementData[k] = elementData[k + 1];

			numItems--;
			return true;
		}

		public void dispalyNode() {
			for (int j = 0; j < numItems; j++) {
				System.out.print("/" + elementData[j].toString());
			}
			System.out.println("/");
		}
		
		public String toString(){
			return "parentNull="+(parent==null)+",dataNum="+numItems+",datas="+Arrays.toString(elementData);
		}
	}

	public static void main(String[] args) {
		int x[] = new int[3];// java.lang.NegativeArraySizeException
		System.out.println(x.length);
		System.out.println(x[3]);
	}

}
