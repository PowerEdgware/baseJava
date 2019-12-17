package current_lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class CountDownTest {

	public static void main(String[] args) {
		//count=0,无效
		int count=1;
		CountDownLatch countDownLatch=new CountDownLatch(1);
		for(;count-->0;)
		new Thread(){
			public void run() {
				System.out.println(getName()+" ready to countDown...");
				LockSupport.park(this);
				countDownLatch.countDown();
				System.out.println(getName()+" countDown");
			};
		}.start();
		
		long start=System.currentTimeMillis();
		System.out.println(Thread.currentThread().getName()+" before wait:"+start);
		try {
			countDownLatch.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end=System.currentTimeMillis();
		System.out.println(Thread.currentThread().getName()+" after wait:"+end+" cost="+(end-start));
	}
}
