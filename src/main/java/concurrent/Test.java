package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class Test {

	public static void main(String[] args) {
		AtomicInteger a=new AtomicInteger(10);
		int c=-1;
		c=a.getAndIncrement();
		changeA(a);
		while(true){
			System.out.println("After c="+c);
			LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
		}
		
	}

	private static void changeA(final AtomicInteger a) {
		ExecutorService mService=Executors.newFixedThreadPool(1);
		mService.execute(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					int aa= a.decrementAndGet();
					System.out.println("After decre aa="+aa);
					LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
				}
			}
		});
	}
}
