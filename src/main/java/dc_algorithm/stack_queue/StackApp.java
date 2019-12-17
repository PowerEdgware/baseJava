package dc_algorithm.stack_queue;

public class StackApp {

	public static void main(String[] args) {
		StackX stackX = new StackX(5);
		System.out.println("IN stackX:");
		for (int i = 0; i < 17; i++) {
			stackX.push(i);
			System.out.print(i+".");
		}
		System.out.println("Arraysize="+stackX.Arraysize());
		System.out.println("out stackX:");
		for (; stackX.size() > 0;) {
			System.out.print(stackX.pop() + ".");
		}
		System.out.println();
		System.out.println("size=" + stackX.size());
		
	}
}
