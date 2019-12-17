package dc_algorithm.map;

import java.util.Random;

public class ShowableDemo {

	static String Null = "NULL";

	public static void main(String[] args) {

		int treeSize = 20;
		String fullabc = "abcdefghijklmnopqrstuvwxyz";
		char[] cs = fullabc.toCharArray();
		Random rnd = new Random();
		ShowableHashMap<ShowableEntity, String> showableMap = new ShowableHashMap<>();
		for (int x = 0; x < treeSize; x++) {
			showableMap.put(new ShowableEntity(cs[rnd.nextInt(cs.length)]), Null);
		}
		int size = showableMap.size();
		System.out.println("Current size=" + size);
		String rst = showableMap.get(new ShowableEntity(cs[rnd.nextInt(cs.length)]));
		System.out.println(rst);
	}
}

class ShowableEntity {
	char ch;

	public ShowableEntity(char c) {
		this.ch = c;
	}

	@Override
	public int hashCode() {
		// final int prime = 31;
		// int result = 1;
		// result = prime * result + ch;
		// return result;
		//TODO for test map red-black_tree node
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShowableEntity other = (ShowableEntity) obj;
		if (ch != other.ch)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.ch + "@";
	}

}
