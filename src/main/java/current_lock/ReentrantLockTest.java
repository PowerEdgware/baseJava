package current_lock;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantLockTest {

	ReentrantLock lock = new ReentrantLock();
	Random rnd = new Random();
//	ReentrantReadWriteLock

	int rnd(int bound) {
		return rnd.nextInt(bound);
	}

	public void doSomeThing(Thread outThread) throws InterruptedException {
		final ReentrantLock lock = this.lock;
		lock.lock();
		try {
			int count = lock.getQueueLength();
			boolean isQueued=lock.hasQueuedThread(outThread);
			// lock.getWaitQueueLength(condition)
			
			System.out.println(Thread.currentThread().getName() + ",getQueueLength=" + count+",isQueued="+isQueued);

			if(!outThread.getName().contains("pool"))
			Thread.sleep(5);
			else
				Thread.sleep(1);
				
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) throws Exception {
		// 核线程占据
		final ReentrantLockTest lockTest = new ReentrantLockTest();
		lockTest.doSomeThing(Thread.currentThread());
		
		// Thread.sleep(5);
		for (int i = 65; i < 80; i++) {
			new Thread("Thread-" + ((char) i)) {
				public void run() {
					try {
						lockTest.doSomeThing(this);
					} catch (InterruptedException e) {
						System.out.println(Thread.currentThread().getName() + " Interrupted " + e);
					}
				};
			}.start();
			// Thread.sleep(lockTest.rnd(3));
		}

		 Thread.sleep(3);
		// 抢占lock
		int fixSize = 1000;
		ExecutorService mservice = Executors.newFixedThreadPool(fixSize);
		final AtomicBoolean mServiceHalt = new AtomicBoolean(false);
		new Thread("mservice_start_thread") {
			public void run() {
				try {
					for (int x = 0;; x++) {
						Runnable r = new Runnable() {
							@Override
							public void run() {
								try {
									lockTest.doSomeThing(Thread.currentThread());
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						};
						if (!mServiceHalt.get()) {
							Thread.sleep(1);
						} else {
							break;
						}
						mservice.submit(r);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					mservice.shutdown();
					while (!mservice.isTerminated()) {
					}
					System.out.println("Thread pool mservice shutDown!");
				}
			};
		}.start();

	
		System.out.println("Enter to quit");
		System.in.read();
		System.out.println("mServiceHalt to true");
		mServiceHalt.set(true);
	}
}
