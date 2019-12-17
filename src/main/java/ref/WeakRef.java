package ref;

import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class WeakRef {

	private static final class Lock{}
	static Lock lock=new Lock();
	public static void main(String[] args) {
		///SoftReference
		WeakReference<String> ref=new WeakReference<String>(new String("abc"));
		System.out.println(ref.get());
		System.gc();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(ref.get());
		System.out.println("------------");
		
		ReferenceQueue<String> queue = new ReferenceQueue<String>();
        PhantomReference<String> pr = new PhantomReference<String>(new String("hello"), queue);
        System.out.println(pr.get());
        
        LockSupport.parkNanos(lock, TimeUnit.SECONDS.toNanos(2));
        PhantomReference<String> cleanedRef=(PhantomReference<String>) queue.poll();
        while(cleanedRef!=null){
        	System.out.println(cleanedRef);
        	cleanedRef=(PhantomReference<String>) queue.poll();
        }
       try {
		System.in.read();
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
	
	 static class A{
		static final String a="";
		String b;
		
	}
}
