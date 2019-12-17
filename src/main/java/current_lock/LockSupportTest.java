package current_lock;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

	public static void main(String[] args) {
		Thread t=Thread.currentThread();
		new Thread(){
			public void run() {
				try {
					Thread.sleep(500);
//					t.interrupt();
					LockSupport.unpark(t);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
		long start=System.currentTimeMillis();
		LockSupport.park();
		long end=System.currentTimeMillis();
		System.out.println(t.getName()+" cost="+(end-start));
	}
}
