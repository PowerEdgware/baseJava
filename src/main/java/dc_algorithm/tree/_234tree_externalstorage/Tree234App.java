package dc_algorithm.tree._234tree_externalstorage;

import java.util.Random;

public class Tree234App {

	public static void main(String[] args) {
		
		Tree234<String> tree234=new Tree234<>();
		String fullabc = "abcdefghijklmnopqrstuvwxyz";
		char[] cs = fullabc.toCharArray();
		Random r = new Random();
		
		int total=30;
		System.out.println("Insert order:total="+total);
		for (int k = 0; k <total; k++) {
			String ic = cs[r.nextInt(cs.length)]+""+cs[r.nextInt(cs.length)]+""+k%2;
			System.out.print(ic + " ");
			System.out.println("success saved?="+tree234.insert(ic));
		}
		System.out.println();
		System.out.println("tree size="+tree234.size());
		System.out.println("pre_order display :");
		tree234.displayTree();
	}
	
	static char[] chars={'a','b','c','d'};
}
