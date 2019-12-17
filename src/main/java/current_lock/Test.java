package current_lock;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

	public static void main(String[] args) throws Exception {
		Test t = new Test();
		Random rnnd = new Random();
		AtomicBoolean stopGet = new AtomicBoolean(false);
		AtomicBoolean stopSet = new AtomicBoolean(false);
		
		int takeThreadNum=5;
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
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			takeThread.start();
		}
//		if(takeThread!=null){
//			takeThread.interrupt();
//		}
		LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(1000));
		Thread setThread = new Thread("set_thread") {
			@Override
			public void run() {
				while (!stopSet.get()) {
					t.set(rnnd.nextInt(100));
					LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(3000));
				}
			}
		};
		//setThread.start();

		System.err.println("Enter to exit");
		System.in.read();
		stopGet.set(true);
		stopSet.set(true);
		t.stopAll();
		System.err.println("Application Stopped");

	}

	ReentrantLock lock = new ReentrantLock();
	Condition c = lock.newCondition();

	List<Integer> list = new LinkedList<>();
	
	Thread t;
	public void setT(Thread t){
		this.t=t;
	}

	public Integer get() throws InterruptedException {
		ReentrantLock lock = this.lock;
		lock.lock();
		try {
			if (list.isEmpty()) {
				System.out.println("list empty," + Thread.currentThread().getName() + " wait.");
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
			if(t!=null){
				t.interrupt();
			}
			System.out.println(Thread.currentThread().getName() + " singal");
		} finally {
			lock.unlock();
		}
	}

	public void stopAll() {
		ReentrantLock lock = this.lock;
		lock.lock();
		try {
			c.signalAll();
			System.out.println(Thread.currentThread().getName() + " singalAll.remainList=" + list.size());
		} finally {
			lock.unlock();
		}
	}
}
