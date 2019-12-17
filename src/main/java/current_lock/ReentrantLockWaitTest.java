package current_lock;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockWaitTest {

	ReentrantLock lock = new ReentrantLock();
	Condition c = lock.newCondition();
	// datalist
	List<Integer> list = new LinkedList<>();

	public Integer get() throws InterruptedException {
		ReentrantLock lock = this.lock;
		try {
			lock.lock();
			Thread.sleep(100);
			int queuedLen = lock.getQueueLength();
			int waitQueueLen = lock.getWaitQueueLength(this.c);
			if (list.isEmpty()) {
				System.out.println("list empty," + Thread.currentThread().getName() + " wait.queuedLen=" + queuedLen
						+ ",waitQueueLen=" + waitQueueLen);
				c.await();
			}
			System.out.println(Thread.currentThread().getName() + " continue");
			if (list.isEmpty())
				return null;
			return list.remove(0);
		} finally {
			lock.unlock();
		}
	}

	public void set(int x) {
		ReentrantLock lock = this.lock;
		lock.lock();
		try {
			list.add(x);
			c.signal();
			System.out.println(Thread.currentThread().getName() + " singal x="+x);
		} finally {
			lock.unlock();
		}
	}

	public void stopAll(int x) throws InterruptedException {
		ReentrantLock lock = this.lock;
		lock.lock();
		try {
			c.signalAll();
			System.out.println(Thread.currentThread().getName() + " singalAll");
		} finally {
			lock.unlock();
		}
	}

	public boolean dataEmpty() {
		return list.isEmpty();
	}

	public int dataSize() {
		return list.size();
	}

	public static void main(String[] args) throws Exception {
		ReentrantLockWaitTest t = ReentrantLockWaitTest.class.newInstance();
		AtomicBoolean stopGet = new AtomicBoolean(false);
		AtomicBoolean stopSet = new AtomicBoolean(false);

		int takeThreadNum = 5;
		Thread takeThread = null;
		for (int x = 0; x < takeThreadNum; x++) {
			takeThread = new Thread("take_thread-" + x) {
				@Override
				public void run() {
					try {
						while (!stopGet.get()) {
							long start = System.currentTimeMillis();
							Integer result = t.get();
							long end = System.currentTimeMillis();
							System.out.println(Thread.currentThread().getName() + " getResult=" + result + ",cost="
									+ (end - start) + " ms");
							LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(2000));
						}
						System.out.println(Thread.currentThread().getName() + " stopped");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			takeThread.start();
		}
		LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(1000));
		// System.out.println("Enter to start set_thread");
		// System.in.read();
		Random rnnd = new Random();
		Thread setThread = new Thread("set_thread") {
			@Override
			public void run() {
				while (!stopSet.get()) {
					t.set(rnnd.nextInt(100));
					LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(500));
				}
				System.out.println(Thread.currentThread().getName() + " stopped,dataSize=" + t.dataSize());
			}
		};
		setThread.start();
		System.out.println("Enter to stop");
		System.in.read();
		stopSet.set(true);
		while (!t.dataEmpty()) {
			System.out.println(Thread.currentThread().getName() + " data is not empty wait...");
			Thread.sleep(1000);
		}
		t.stopAll(0);
		stopGet.set(true);
		System.out.println("Application stopped");

	}
}
