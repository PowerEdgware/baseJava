package threadLocal0;

import java.util.concurrent.locks.LockSupport;

public class ThreadLocalDemo {

	static ThreadLocal<Integer> local = new ThreadLocal<Integer>() {
		protected Integer initialValue() {
			return 0;
		};
	};
	
	static ThreadLocal<Object> local2 = new ThreadLocal<Object>() {
		protected Object initialValue() {
			return null;
		};
	};
	
    private static final int HASH_INCREMENT = 0x61c88647;

	public static void main(String[] args) {
		// ThreadLocalRandom
		// ThreadLocal<T> InheritableThreadLocal
		// Reference Java的四种引用方式
		// ReferenceQueue
		// SoftReference
		// WeakReference
		// PhantomReference
		// WeakHashMap

		Thread[] threads = new Thread[5];

		for (int i = 0; i < 5; i++) {
			threads[i] = new
			Thread(() -> {
				int num = local.get().intValue();
				num += 5;
				local.set(num);
				
				local2.set("1234");

				System.out.println(Thread.currentThread().getName() + " parked");
				LockSupport.park();
				System.out.println(Thread.currentThread().getName() + " : " + local.get());

			}, "Thread-" + i);

		}

		for (Thread thread : threads) {
			thread.start();
		}
		System.out.println(HASH_INCREMENT);
		
		for (Thread thread : threads) {
			thread.interrupt();
		}
		System.gc();
		
	}
	
	
	ThreadLocalDemo.A a=null;
	
	class A{
		
	}
}
