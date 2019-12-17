package dc_algorithm.tree;

import dc_algorithm.tree.BinarySearchTree.Node;

public class BinarySearchTreeApp {

	public static void main(String[] args) {

		BinarySearchTree binarySearchTree = new BinarySearchTree();

		int key = -1;
		System.out.println("Insert order:");
		for (int k = 0; k < 15; k++) {
			double c = Math.random() * 100;
			int ic = (int) c;
			binarySearchTree.insert(ic, c);
			if (ic % 3 == 0 && k/2==0) {
				key = ic;
			}
			System.out.print(ic + " ");
		}
		System.out.println();
		System.out.println("in_order display tree:");
		binarySearchTree.displayInOrder();
		System.out.println();

		Node node = binarySearchTree.find(key);
		System.out.println("key=" + key + " Found?=" + node);

		node = binarySearchTree.root();
		System.out.println("root=" + node);
		
		System.out.println("pre_order display tree:");
		binarySearchTree.displayPreOrder();
		System.out.println();
		
		System.out.println("post_order display tree:");
		binarySearchTree.displayPostOrder();
		System.out.println();

		Node min=binarySearchTree.minNum();
		System.out.println("minNode="+min);
		
		Node max=binarySearchTree.maxNum();
		System.out.println("maxNode="+max);

		System.out.println("Test delete:");
		boolean deleted=binarySearchTree.delete(key);
		System.out.println("Node="+key+",deleted?"+deleted);
		System.out.println("after deleted");
		binarySearchTree.displayInOrder();
		System.out.println();
		binarySearchTree.displayTree();
	}
}
