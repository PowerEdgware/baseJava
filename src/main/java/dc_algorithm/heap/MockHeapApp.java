package dc_algorithm.heap;

//import java.util.PriorityQueue;
import java.util.Random;

public class MockHeapApp {

	public static void main(String[] args) {
		int maxSize = 30;
		MockHeap<Integer> heap = new MockHeap<>(maxSize);
		Random rnd = new Random();
		for (int x = 0; x < maxSize+3; x++) {
			int rndNum = rnd.nextInt(100);
			System.out.println("Insert rndNum=" + rndNum + " suc?" + heap.insert(rndNum));
		}
		System.out.println("Display Heap:");
		heap.displayHeap();
		/**
		 * 
		 * Display Heap:
Heap Array:92 46 83 42 43 29 
--------------------------------------------------------------------
                                92
                46                              83
        42              43              29
--------------------------------------------------------------------
		 */

//		PriorityQueue<String> queue = new PriorityQueue<>();
//		queue.add("S");
//		queue.add("S");
//		queue.add("W");
//		for (; !queue.isEmpty();)
//			System.out.print(queue.poll() + " ");
		System.out.println("Before delete.size="+heap.size());
		System.out.println("Delete Heap:");
		int delete=10;
		while(++delete<maxSize){
			System.out.println(heap.remove()+" deleted");
			System.out.println("Display:");
			heap.displayHeap();
		}
		System.out.println("Detele complete:");
		
		heap.displayHeap();
	}
}
