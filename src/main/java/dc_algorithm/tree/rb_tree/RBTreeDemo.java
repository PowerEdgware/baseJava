package dc_algorithm.tree.rb_tree;

import java.util.Random;
import java.util.TreeMap;

public class RBTreeDemo {

	public static void main(String[] args) {
		TreeMap<String, Double> a=new TreeMap<>();
		
		String fullabc = "abcdefghijklmnopqrstuvwxyz";
		char[] cs = fullabc.toCharArray();
		Random r = new Random();
		System.out.println("Insert order:");
		for (int k = 0; k <30; k++) {
			double c = Math.random() * 100;
			String ic = cs[r.nextInt(cs.length)]+""+k%2;
			System.out.print(ic + " ");
			a.put(ic, c);
		}
		System.out.println();
		System.out.println(a);
		
		System.out.println("==========================");
		a.clear();
		
		
		a.put("1", 1d);
		a.put("2", 1d);
		a.put("5", 1d);
		a.put("3", 1d);
		a.put("8", 1d);
		a.put("7", 1d);
		System.out.println(a);
	}
}
