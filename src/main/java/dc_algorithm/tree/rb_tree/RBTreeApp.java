package dc_algorithm.tree.rb_tree;

import java.util.Random;
import java.util.TreeMap;

public class RBTreeApp {

	public static void main(String[] args) {
		// TreeMap<K, V>

		RBTree<Double> rbTree = new RBTree<>();
		String fullabc = "abcdefghijklmnopqrstuvwxyz";
		char[] cs = fullabc.toCharArray();
		Random r = new Random();
		System.out.println("Insert order:");
		for (int k = 0; k < 50; k++) {
			double c = Math.random() * 100;
			String ic = cs[r.nextInt(cs.length)] + "" + k % 7 + cs[r.nextInt(cs.length)];
			System.out.print(ic + " ");
			rbTree.put(ic, c);
		}
		System.out.println();
		System.out.println("in_order display tree:size=" + rbTree.size());
		rbTree.displayInOrder();
		System.out.println();
		rbTree.displayTree();

	}
}
