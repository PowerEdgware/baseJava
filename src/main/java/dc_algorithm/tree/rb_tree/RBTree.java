package dc_algorithm.tree.rb_tree;

import java.util.Objects;
import java.util.Stack;

/*
 * 任意节点的左右后代(子节点)数大致相同
 * 红-黑规则： TODO 每个叶节点（NIL节点，空节点）是黑色的。
 * 1，每一个节点不是红色就是黑色；
 * 2，根节点总是黑色；
 * 3，如果节点是红色的，则他的子节点必须是黑色的；
 * 4，从根到叶节点或空子节点的每条路径，必须包含相同数目的黑色节点。
 * TODO 不允许有重复的关键字,否则复杂。
 * 修正违规的措施：
 * 1，改变节点的颜色；
 * 2，执行旋转操作。
 * ------------------------------------------------------
 * 一，旋转rotate:
 * 1.1简单旋转：
 * --选择一个顶端节点，右旋转->这个顶端节点将会向下和向右移动到他的右边子节点的位置，他的左子节点移到他原来的位置(右转必须保证有左子节点；左转必须保证有右子节点)
 * 1.2奇异的横向移动节点：
 * --节点如果是顶端节点的内侧子孙(祖孙不在同一侧)，对于内侧子孙节点而言，如果他是上移节点的子节点，他总是要断开和父节点的链接并且重新链接到他以前的祖父节点上，好像成为自己的叔叔一样。
 * 祖父节点右移动，则内侧孙子节点链接到祖父节点的左侧，成为祖父节点的左孩子；反之翼然。
 * 1.3移动子树：
 * --旋转不会改变每颗子树节点之间的关系，整棵子树作为一个单元整体移动，不论一棵子树有多少节点，在旋转过程中他们都作为整体一起移动。(规则和子节点移动一致)
 * -----------------------------------------------------
 * 二，插入一个新节点
 *2.1 注意：
 * 插入的新节点总是红色的(除了根)比插入黑色的节点违背红-黑规则可能性小。
 * 因为把红色节点连接到黑色节点上，不会违背规则。它不会造成2红在一起的情况(rule3),也不会改变任何路径上的黑色高度。
 * 当然，如果吧新的红色节点连接到红色节点上，会违背规则3，但是只有一半的几率发生这种情况。反之，如果加入新的黑色节点，总会改变
 * 这条路径上的黑色高度，违背规则4.另外，违背规则3比违背规则4更容易修正。
 * 2.2插入细节
 * 2.2.1在下行路途中的颜色变换
 * --规则：每当查找插入点过程中遇到有两个红色节点的黑色节点时，必须把子节点变成黑色，而把父节点变成红色(除非父节点是根节点，因为根节点总是黑色的)
 * 颜色变换对红-黑规则的影响：
 * ---不改变黑色高度，即不违背规则4；
 * ---可能违背规则3.
 * 2.2.2插入节点之后的旋转
 * --新插入节点称为X，它总是红色的；X的父节点为P，祖父节点为G。
 * --X分为G的内侧子孙和外侧子孙
 * ---TODO 内外侧子孙：X在P的一侧与P在G的一侧相同，或者说X，P，G在同一条直线上，则称X是G的外侧子孙；否则就是内侧子孙。
 * --恢复红-黑规则所采取的措施是由颜色以及X和他的亲属布局决定的，下面是三种可能性：
 * ---P是黑色的；
 * TODO 直接插入新增红色节点X即可，因为这种情况不会违背颜色规则，也不会增加黑色节点数目。
 * 
 * ---P是红色的，X是G的一个外侧子孙节点；
 * TODO 需要颜色变化和一次旋转，具体如下：
 * ----改变X祖父节点G的颜色；
 * ----改变X父节点P的颜色；
 * ----以X的祖父节点为顶端，向X上升的方向进行旋转(可能是右旋转，可能是左旋转，需要根据X是左子孙节点还是右子孙节点。[前提X是外侧子孙节点])。
 * 
 * ---P是红色的，X是G的一个内侧子孙节点。
 * TODO 需要2次旋转和一些颜色变化
 * ----改变X的祖父节点G的颜色；
 * ----改变X的颜色；
 * ----用X的父节点P为顶端旋转，向X上升的方向旋转；(把X由内侧子孙节点变成外侧子孙节点)
 * ----再以X的祖父节点G为顶端，向X上升的方向旋转。
 *TODO  NOTE：其实先改变颜色再旋转和先旋转再改变颜色没有关系，只是前者方便些。
 * ---其他情况（可能导致旋转复杂化和对上面插入情况的可能性是否包含所有情况的）
 * ----假设X有一个兄弟节点S，他是P的另一个子节点。
 * 如果P是黑色，则插入X没有问题(此时存在S，则必是红色)；
 * 如果P是红色，根据规则3那么S必定是黑色，这是矛盾的，因为红色节点的两个子节点必须都是黑色(防止违背规则3，且新插入的X是红色)
 * 所以这种情况红色父节点P不可能有一个单独的黑色子节点S，即X不可能有一个兄弟节点。(除非P是黑色)
 * ----P的父节点G有一个子节点U，U是P的兄弟节点和X的叔父节点
 * 如果P是黑色的，插入X时不需要旋转
 * 如果P是红色的，那么U也必须是红色的，否则，从G到P的黑色高度和从G到U的黑色高度就不同了。
 * TODO 这种情况需要改变P，U的颜色，同时改变X的祖父节点G的颜色(假设G不是根节点)，如果G的父节点也是红色，则把G当作新插入节点递归处理。
 * 
 * 2.2.3在下行路途中的旋转
 */
public class RBTree<V> {
	private Node<V> root;
	private int size;

	public RBTree() {

	}

	public V put(String key, V value) {
		Node<V> t = root;
		if (t == null) {
			root = new Node<>(key, value, null);
			size++;
			return null;
		}
		int comp = 0;
		Node<V> parent = null;

		while (t != null) {
			parent = t;
			comp = key.compareTo(t.key);
			if (comp < 0)
				t = t.left;
			else if (comp > 0)
				t = t.right;
			else
				return t.setValue(value);
		}
		Node<V> e = new Node<>(key, value, parent);

		if (comp > 0)
			parent.right = e;
		else
			parent.left = e;
		// fixAfterInsertion(e);
		dofixAfterInsertion(e);
		size++;
		return null;
	}
	public int size(){
		return this.size;
	}
	
	int blank(){
		int nblanks=1;
		while(nblanks<size){
			nblanks=nblanks*2;
		}
//		if(nblanks>1)
//			nblanks/=2;
		return nblanks;
	}

	public void displayTree() {
		Stack<Node<V>> globalStack = new Stack<>();
		globalStack.push(root);
		int nBlanks = blank();
		int dotNum=nBlanks;
		System.out.println("nBlanks="+nBlanks);
		boolean isRowEmpty = false;
		for(int k=0;k<dotNum;k++){
			System.out.print("==");
		}
		System.out.println();
		while (isRowEmpty == false) {
			Stack<Node<V>> localStack = new Stack<>();
			isRowEmpty = true;
			for (int j = 0; j < nBlanks; j++)
				System.out.print(' ');
			while (globalStack.isEmpty() == false) {
				Node<V> temp = globalStack.pop();
				if (temp != null) {
					System.out.print(temp.key + (temp.color == RED ? "R" : "B"));
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
		System.out.println();
		for(int k=0;k<dotNum;k++){
			System.out.print("==");
		}
		System.out.println();
	}

	public void displayInOrder() {
		rec_in_order(root);
	}

	// 中序遍历（一般用于二叉搜索树）
	// 1.调用自身遍历节点左边子树；
	// 2.访问这个节点；
	// 3.调用自身来遍历节点右子树
	void rec_in_order(Node<V> rootNode) {
		if (rootNode != null) {
			rec_in_order(rootNode.left);
			System.out.print(rootNode.key + " ");

			rec_in_order(rootNode.right);
		}
	}

	private void fixAfterInsertion(Node<V> e) {
		e.color = RED;
		while (e != null && e != root && e.parent.color == RED) {
			// TODO 父节点是红色
			// 父节点是祖父节点的左孩子
			if (parentOf(e) == leftOf(parentOf(parentOf(e)))) {
				Node<V> u = rightOf(parentOf(parentOf(e)));
				// 父节点和叔父节点都是红色:分别把e的父节点和叔父节点设置成黑色，把e的祖父节点设置成红色
				if (colorOf(u) == RED) {
					setColor(parentOf(e), BLACK);
					setColor(u, BLACK);
					// if (parentOf(parentOf(e)) != root) {
					setColor(parentOf(parentOf(e)), RED);
					e = parentOf(parentOf(e));// 把e的祖父当作新增节点递归处理
					// fixAfterInsertion(e);
					// }
				} else {
					// 父节点是红色，叔父节点是黑色或者不存在：检查e是内侧子孙还是外侧子孙
					// 父节点是祖父节点的左孩子，e是父节点的有孩子，此时e是内侧子孙，需要改变祖父节点和e的颜色同时以父节点左旋转,再以祖父节点右转
					if (e == rightOf(parentOf(e))) {
						e = parentOf(e);
						// 以e的父节点左旋转
						rotateLeft(e);
					}
					setColor(parentOf(e), BLACK);
					setColor(parentOf(parentOf(e)), RED);
					rotateRight(parentOf(parentOf(e)));
				}
			} else {
				// 父节点是组父节点的右侧孩子
				Node<V> u = leftOf(parentOf(parentOf(e)));
				if (colorOf(u) == RED) {
					setColor(parentOf(e), BLACK);
					setColor(u, BLACK);
					// if (parentOf(parentOf(e)) != root) {
					setColor(parentOf(parentOf(e)), RED);
					e = parentOf(parentOf(e));// 把e的祖父当作新增节点递归处理
					// fixAfterInsertion(e);
					// }
				} else {
					// 检测e是内侧子孙还是外侧子孙
					// TODO 内测子孙，需要以父节点右旋转
					if (e == leftOf(parentOf(e))) {
						// setColor(e, BLACK);
						// setColor(parentOf(parentOf(e)), RED);
						e = parentOf(e);
						rotateRight(e);
					}
					setColor(parentOf(e), BLACK);
					setColor(parentOf(parentOf(e)), RED);
					rotateLeft(parentOf(parentOf(e)));
				}
			}
		}
		root.color = BLACK;
	}

	private void dofixAfterInsertion(Node<V> e) {
		e.color = RED;

		rec_fixAfterInsertion(e);

		root.color = BLACK;
	}

	void rec_fixAfterInsertion(Node<V> e) {
		if (e == null || e == root || e.parent.color != RED)
			return;
		// TODO 父节点是红色
		// 父节点是祖父节点的左孩子
		if (parentOf(e) == leftOf(parentOf(parentOf(e)))) {
			Node<V> u = rightOf(parentOf(parentOf(e)));
			// 父节点和叔父节点都是红色:分别把e的父节点和叔父节点设置成黑色，把e的祖父节点设置成红色
			if (colorOf(u) == RED) {
				setColor(parentOf(e), BLACK);
				setColor(u, BLACK);
				setColor(parentOf(parentOf(e)), RED);
				e = parentOf(parentOf(e));// 把e的祖父当作新增节点递归处理
				rec_fixAfterInsertion(e);
			} else {
				// 父节点是红色，叔父节点是黑色或者不存在：检查e是内侧子孙还是外侧子孙
				// 父节点是祖父节点的左孩子，e是父节点的有孩子，此时e是内侧子孙，需要改变祖父节点和e的颜色同时以父节点左旋转,再以祖父节点右转
				if (e == rightOf(parentOf(e))) {
					setColor(e, BLACK);
					setColor(parentOf(parentOf(e)), RED);
					e = parentOf(e);
					// 以e的父节点左旋转
					rotateLeft(e);
				} else {
					setColor(parentOf(e), BLACK);
					setColor(parentOf(parentOf(e)), RED);
				}
				rotateRight(parentOf(parentOf(e)));
			}
		} else {
			// 父节点是组父节点的右侧孩子
			Node<V> u = leftOf(parentOf(parentOf(e)));
			if (colorOf(u) == RED) {
				setColor(parentOf(e), BLACK);
				setColor(u, BLACK);
				setColor(parentOf(parentOf(e)), RED);
				e = parentOf(parentOf(e));// 把e的祖父当作新增节点递归处理
				rec_fixAfterInsertion(e);
			} else {
				// 检测e是内侧子孙还是外侧子孙
				// TODO 内测子孙，需要以父节点右旋转
				if (e == leftOf(parentOf(e))) {
					setColor(e, BLACK);
					setColor(parentOf(parentOf(e)), RED);
					e = parentOf(e);
					rotateRight(e);
				} else {
					setColor(parentOf(e), BLACK);
					setColor(parentOf(parentOf(e)), RED);
				}
				rotateLeft(parentOf(parentOf(e)));
			}
		}
	}

	// FROM CLR center-left-right
	private void rotateLeft(Node<V> x) {
		if (x != null) {
			Node<V> r = x.right;
			x.right = r.left;
			if (r.left != null)
				r.left.parent = x;
			r.parent = x.parent;
			if (x.parent == null)
				root = r;
			else if (x.parent.left == x)
				x.parent.left = r;
			else
				x.parent.right = r;

			r.left = x;
			x.parent = r;
		}
	}

	private void rotateRight(Node<V> x) {
		if (x != null) {
			Node<V> l = x.left;
			x.left = l.right;
			if (l.right != null)
				l.right.parent = x;
			l.parent = x.parent;
			if (x.parent == null)
				root = l;
			else if (x.parent.right == x)
				x.parent.right = l;
			else
				x.parent.left = l;
			l.right = x;
			x.parent = l;
		}
	}

	static <V> Node<V> parentOf(Node<V> x) {
		return x == null ? null : x.parent;
	}

	static <V> boolean colorOf(Node<V> x) {
		return x == null ? BLACK : x.color;
	}

	static <V> void setColor(Node<V> x, boolean c) {
		if (x != null)
			x.color = c;
	}

	static <V> Node<V> leftOf(Node<V> x) {
		return x == null ? null : x.left;
	}

	static <V> Node<V> rightOf(Node<V> x) {
		return x == null ? null : x.right;
	}

	static void checkNull(Object o) {
		if (Objects.isNull(o))
			throw new NullPointerException();
	}

	// Red-black mechanics
	private static final boolean RED = false;
	private static final boolean BLACK = true;

	static final boolean valEquals(Object o1, Object o2) {
		return (o1 == null ? o2 == null : o1.equals(o2));
	}

	static final class Node<V> {
		String key;
		V value;
		Node<V> left;
		Node<V> right;
		Node<V> parent;
		boolean color = BLACK;

		Node(String key, V value, Node<V> parnet) {
			this.key = key;
			this.value = value;
			this.parent = parnet;
		}

		public String getKey() {
			return this.key;
		}

		public V getValue() {
			return value;
		}

		public V setValue(V value) {
			V oldValue = this.value;
			this.value = value;
			return oldValue;
		}

		public boolean equals(Object o) {
			if (!(o instanceof Node))
				return false;
			Node<?> e = (Node<?>) o;

			return valEquals(key, e.getKey()) && valEquals(value, e.getValue());
		}

		public int hashCode() {
			int keyHash = (key == null ? 0 : key.hashCode());
			int valueHash = (value == null ? 0 : value.hashCode());
			return keyHash ^ valueHash;
		}

		public String toString() {
			return key + "=" + value;
		}
	}
}
