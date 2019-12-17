package dc_algorithm.link;

public class LinkList {
	class Link {
		int iData;
		double dData;
		Link next;

		Link(int i, double d) {
			iData = i;
			dData = d;
		}

		public void displayLink() {
			System.out.print("{" + iData + ", " + dData + "} ");
		}

		@Override
		public String toString() {
			return "Link >> " + iData + "," + dData;
		}
	}

	private Link first;

	public LinkList() {
		this.first = null;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void insertFirst(int i, double d) {
		Link link = new Link(i, d);
		link.next = first;
		first = link;
	}

	public Link deleteFirst() {
		if (first == null)
			return null;
		Link temp = first;
		first = temp.next;
		return temp;
	}

	public Link find(int key) {
		Link current = first;

		while (current != null) {
			if (current.iData == key)
				return current;
			current = current.next;
		}
		return null;
	}

	// previous --> current -->current.next
	public Link delete(int key) {
		Link current = first;
		Link previous = null;
		while (current != null) {
			if (current.iData == key)
				break;
			previous = current;
			current = current.next;
		}
		Link target = null;
		if (previous == null) {// first may not null
			if (first != null && first.iData == key) {
				target = first;
				first = first.next;
			}
		} else {
			if (current != null) {
				target = current;
				previous.next = current.next;// skip current
			}
		}
		return target;
	}

	public void displayList() {
		System.out.print("List (first- ->last):");
		Link current = first;
		while (current != null) {
			current.displayLink();
			current = current.next;
		}
		System.out.println("");
	}

	// TODO main
	public static void main(String[] args) {
		LinkList linkList = new LinkList();

		int key = -1;
		for (int x = 0; x < 10; x++) {
			double r = Math.random();
			int i = (int) (r * 100);
			linkList.insertFirst(i, r);
			if (x % 5 == 0)
				key = i;
		}

		linkList.displayList();

		// while (!linkList.isEmpty()) {
		// Link l = linkList.deleteFirst();
		// System.out.print("Deleted ");
		// l.displayLink();
		// System.out.println("");
		// }
		Link l = linkList.find(key);
		System.out.println("Found ?=" + l);

		l = linkList.delete(key);
		System.out.println("Deleted?=" + l);

		System.out.println("After Operated:");
		linkList.displayList();

	}

}
