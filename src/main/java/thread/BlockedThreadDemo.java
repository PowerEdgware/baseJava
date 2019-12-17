package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class BlockedThreadDemo {

	public static void main(String[] args) {
		
		Thread thread=new Thread("thread-block-1"){
			public void run() {
				synchronized(this){
					System.out.println(this.getName());
					try {
						TimeUnit.SECONDS.sleep(60);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		};
		thread.start();
		
		synchronized (thread) {
			System.out.println(Thread.currentThread().getName());
			try {
				LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(60));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
