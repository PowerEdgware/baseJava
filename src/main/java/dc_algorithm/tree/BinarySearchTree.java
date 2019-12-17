package dc_algorithm.tree;

import java.util.Stack;


/*
 * 二叉查找树（Binary Search Tree），（又：二叉搜索树，二叉排序树）它或者是一棵空树，
 * 或者是具有下列性质的二叉树：
 *  若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
 *  若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
 *  它的左、右子树也分别为二叉排序树。
 *  
 * TODO 属于二叉树的一种，是有顺序的树，一般采用中序遍历(即，按照小-->大顺序输出)
 */
public class BinarySearchTree {
	private Node root;// root of tree

	class Node {
		int iData;
		double dData;
		Node left;
		Node right;

		public void displayNode() {
			System.out.println("{idata:" + iData + ",ddata:" + dData + "}");
		}

		@Override
		public String toString() {
			return "Node[idata:" + iData + ",ddata:" + dData + "]";
		}
	}

	public Node find(int key) {
		Node current = root;
		while (current != null && current.iData != key) {
			if (key < current.iData) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return current;
	}

	public void insert(int i, double d) {
		Node node = new Node();
		node.iData = i;
		node.dData = d;

		Node current = root;
		Node parent = null;
		boolean left = true;
		while (current != null) {
			parent = current;
			if (i < current.iData) {
				left = true;
				current = current.left;
			} else {
				left = false;
				current = current.right;
			}
		}
		if (parent == null) {
			root = node;
		} else if (left) {
			parent.left = node;
		} else {
			parent.right = node;
		}
	}

	public Node root() {
		return this.root;
	}

	/*
	 * 以下分别是：中序遍历，前序遍历，后序遍历 主要是以父节点为参照，分别-左中右；中左右；左右中。其中，中相对应父节点
	 */
	public void displayInOrder() {
		rec_in_order(root);
	}

	public void displayPreOrder() {
		rec_pre_order(root);
	}

	public void displayPostOrder() {
		rec_post_order(root);
	}

	// 中序遍历（一般用于二叉搜索树）
	// 1.调用自身遍历节点左边子树；
	// 2.访问这个节点；
	// 3.调用自身来遍历节点右子树
	void rec_in_order(Node rootNode) {
		if (rootNode != null) {
			rec_in_order(rootNode.left);
			System.out.print(rootNode.iData + " ");

			rec_in_order(rootNode.right);
		}
	}

	// 前序遍历(一般用于解析或分析代数表达式)
	void rec_pre_order(Node rootNode) {
		if (rootNode != null) {
			System.out.print(rootNode.iData + " ");
			rec_pre_order(rootNode.left);

			rec_pre_order(rootNode.right);
		}
	}

	// 后序遍历（一般用于解析或分析代数表达式）
	void rec_post_order(Node rootNode) {
		if (rootNode != null) {
			rec_post_order(rootNode.left);
			rec_post_order(rootNode.right);
			System.out.print(rootNode.iData + " ");
		}
	}

	// 最大最小值
	public Node maxNum() {
		Node current = root;
		Node parent = null;

		while (current != null) {
			parent = current;
			current = current.right;
		}
		return parent;
	}

	public Node minNum() {
		Node current = root, last = null;
		while (current != null) {
			last = current;
			current = current.left;
		}
		return last;
	}

	// TODO 删除节点
	/**
	 * 删除节点较为复杂，一般要考虑以下三种情况 1，该节点是叶子节点（没有子节点）； 2，该节点有一个子节点； 3，该节点有两个子节点
	 */

	public boolean delete(int key) {
		Node current = root;
		Node parent = root;
		boolean left = false;
		while (current != null && current.iData != key) {
			parent = current;
			if (key < current.iData) {
				left = true;
				current = current.left;
			} else {
				left = false;
				current = current.right;
			}
		}
		// root is null OR not found key
		if (parent == null || current == null)
			return false;

		// case 1
		if (current.left == null && current.right == null) {
			if (current == root) {
				root = null;
			} else if (left) {
				parent.left = null;
			} else {
				parent.right = null;
			}
		} else if (current.right == null) {// case 2
			if (current == root)
				root = root.left;
			else if (left)
				parent.left = current.left;
			else
				parent.right = current.left;
		} else if (current.left == null) {// case 2
			if (current == root)
				root = root.right;
			else if (left)
				parent.left = current.right;
			else
				parent.right = current.right;
		} else {// case 3:有两个子节点，到底该让谁继承王位呢^^,所以比case1,2复杂
			// delete's successor
			Node successor = successor(current);
			if (current == root) {
				root = successor;
			} else if (left) {// 待删除元素是左孩子
				parent.left = successor;
			} else {
				parent.right = successor;
			}
			successor.left = current.left;
		}

		return true;
	}
	// 前驱结点(presuccessor)：节点val值小于该节点val值并且值最大的节点 --小于x的集合中最大的元素
	// 后继节点(successor)：节点val值大于该节点val值并且值最小的节点--大于x的集合中最小的元素
	// TODO 在一维坐标上相当于数值X的前后 --v--w--X--y--z--...--

	// 中序遍历的successor
	Node successor(Node delNode) {
		assert delNode != null;// 默认关闭的，打开了assertion功能(vm args -ea)-->if false
								// throw AssertionError
		// assume delNode right is not null;successor cannot have a left child
		Node successorParent = delNode;
		Node successor = delNode.right;
		Node current = delNode.right.left;
		while (current != null) {
			successorParent = successor;
			successor = current;
			current = current.left;
		}
		if (successor != delNode.right) {
			successorParent.left = successor.right;
			successor.right = delNode.right;
		}
		return successor;
	}

	// TODO 获取任意节点的后集结点
	// * 后继节点
	// 1.若一个节点有右子树，那么该节点的后继节点是其右子树中val值最小的节点（也就是右子树中所谓的leftMostNode）
	// 2.若一个节点没有右子树，那么判断该节点和其父节点的关系
	// 2.1 若该节点是其父节点的左边孩子，那么该节点的后继结点即为其父节点
	// 2.2
	// 若该节点是其父节点的右边孩子，那么需要沿着其父亲节点一直向树的顶端寻找，直到找到一个节点P，P节点是其父节点Q的左边孩子（可参考例子2的前驱结点是1），那么Q就是该节点的后继节点
	public Node getSuccessor(Node rndNode) {
		if (rndNode == null)
			return null;
		if (rndNode.right != null) {// case 1
			Node successor = rndNode.right;
			while (successor.left != null) {
				successor = successor.left;
			}
			return successor;
		} else {// case 2
			// 向上查找,假设Node节点存储了parnet节点
//			Node p = rndNode.parent;
//			Node current = rndNode;
//			while (p != null && current == p.right) {
//				current = p;
//				p = p.parent;
//			}
//			return p;
		}
		return null;
	}

	public void displayTree(){
		Stack<Node> globalStack=new Stack<>();
		globalStack.push(root);
		int nBlanks=16;
		boolean isRowEmpty=false;
		System.out.println("===========================================================");
		while(isRowEmpty==false){
			Stack<Node> localStack=new Stack<>();
			isRowEmpty=true;
			for(int j=0;j<nBlanks;j++)
				System.out.print(' ');
			while(globalStack.isEmpty()==false){
				Node temp=globalStack.pop();
				if(temp!=null){
					System.out.print(temp.iData);
					localStack.push(temp.left);
					localStack.push(temp.right);
					if(temp.left!=null || temp.right!=null){
						isRowEmpty=false;
					}
				}else{
					System.out.print("..");
					localStack.push(null);
					localStack.push(null);
				}
				for(int j=0;j<nBlanks*2-2;j++){
					System.out.print(' ');
				}
			}
			System.out.println();
			nBlanks/=2;
			while(localStack.isEmpty()==false)
				globalStack.push(localStack.pop());
		}
		System.out.println("===========================================================");
	}
}
