package dc_algorithm.stack_queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class QueueDemo {

	public static void main(String[] args) throws Exception {

		// TODO Queue<E>
		// DelayQueue<Delayed>
		// PriorityQueue<E>
		// Vector<E>
		// Stack<E>
		// ArrayList
		// ArrayBlockingQueue<E>
		// ArrayBlockingQueue、LinkedBlockingQueue
		// ConcurrentLinkedQueue<E>
		// SynchronousQueue<E>
		// TODO Deque<E> 双端队列
		// ArrayDeque
		List<Integer> list = Arrays.asList(10, 4, 13, 15, 2, 1, 0, 19);
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		queue.addAll(list);
		while (!queue.isEmpty())
			System.out.print(queue.poll() + ",");
		// AtomicInteger
		// Thread.currentThread().interrupt();
		// Thread.interrupted()

		DelayQueue<Doc> delayQueue = new DelayQueue<>();
		Random rnd = new Random();
		Thread putThread = new Thread("put_thread") {
			@Override
			public void run() {
				while (!this.isInterrupted()) {
					LockSupport.parkNanos(TimeUnit.NANOSECONDS.convert(1, TimeUnit.SECONDS));
					delayQueue.put(new Doc(TimeUnit.NANOSECONDS.convert(rnd.nextInt(5) + 1, TimeUnit.SECONDS)));
					System.out.println(this.getName() + " put Doc at:" + System.currentTimeMillis());
				}
				System.out.println(this.getName() + " stopped");
			}
		};
		putThread.start();

		int pollThreadSize = 10;
		List<Thread> pollThreadList = new ArrayList<>(pollThreadSize);
		for (int x = 0; x < pollThreadSize;) {
			Thread pollThread = new Thread("pollThread-" + (x++)) {
				public void run() {
					while (!this.isInterrupted()) {
						long start = System.currentTimeMillis();
						Object o = null;
						try {
							o = delayQueue.take();
						} catch (InterruptedException e) {
							System.out.println(this.getName() + " is interruptted ,ready to stop");
							this.interrupt();
						}
						if (o == null)
							LockSupport.parkNanos(TimeUnit.MILLISECONDS.toMillis(1000));
						else {
							long end = System.currentTimeMillis();
							System.out.println(this.getName() + " takes " + o + " cost=" + (end - start) + " ms");
						}
					}
					System.out.println(this.getName() + " stopped");
				};
			};
			pollThread.start();
			pollThreadList.add(pollThread);
		}

		System.in.read();
		for (Iterator iterator = pollThreadList.iterator(); iterator.hasNext();) {
			Thread pollThread = (Thread) iterator.next();
			System.out.println("stopping " + pollThread.getName());
			pollThread.interrupt();
		}
		System.out.println("stopping " + putThread.getName());
		putThread.interrupt();
		System.out.println("Service stopped");
		
	}
}

class Doc implements Delayed {
	private long timeout;// sec
	private long waitsec = -1;

	public Doc(long timeout) {
		this.timeout = timeout + System.nanoTime();
		this.waitsec = TimeUnit.SECONDS.convert(timeout, TimeUnit.NANOSECONDS);
	}

	@Override
	public int compareTo(Delayed o) {
		return Long.compare(this.getDelay(TimeUnit.NANOSECONDS), o.getDelay(TimeUnit.NANOSECONDS));
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(timeout - System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	public String toString() {
		return "Doc[" + waitsec + " s，timeout=" + timeout + "]";
	}

}
