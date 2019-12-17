package dc_algorithm.stack_queue;

public class ArrayLoopQueueApp {

	public static void main(String[] args) {
		int size = 15;
		ArrayLoopQueue<String> queue = new ArrayLoopQueue<>(size);
		System.out.println("In queue:");
		for (int x = 0; x < size + 5; x++) {
			boolean r = queue.insert(String.valueOf(x));
			System.out.println("ele=" + x + " insert ?" + r);
		}
		System.out.println(queue.toString());
		System.out.println("OUT queue:");
		int index = 0;
		while (index++ < 5) {
			System.out.print(queue.remove() + ",");
		}
		System.out.println();
		System.out.println("reIN:");
		for (int x = 0; x < size - 4; x++) {
			boolean r = queue.insert(String.valueOf(x));
			System.out.println("ele=" + x + " insert ?" + r);
		}
		System.out.println(queue.toString());

	}
}
