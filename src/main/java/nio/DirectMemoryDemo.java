package nio;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import javax.annotation.security.RunAs;

import com.google.common.collect.Queues;

import lombok.SneakyThrows;
import sun.misc.Unsafe;
public class DirectMemoryDemo {

	static ExecutorService es=Executors.newSingleThreadExecutor();
	@SneakyThrows
	public static void main(String[] args)  {
		es.submit(DirectMemoryDemo.allocateDirectMemory());
		
		//main thread remove buf
		
		Thread consumerThread=new Thread(DirectMemoryDemo.consumerThread());
		consumerThread.start();
		//wait
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		stop=true;
		es.shutdownNow();
		System.out.println("Main Stopped");
	}
	
	@SneakyThrows
	static Runnable consumerThread(){
		return ()->{
			while(!stop){
				try {
				ByteBuffer	buf= bufContainer.poll(1, TimeUnit.SECONDS);
				LockSupport.parkNanos(bufContainer, TimeUnit.SECONDS.toNanos(3));
				//lagged gc
				buf.clear();
				buf=null;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
	}
	
	static int _1M=1024*1024;
	static volatile boolean stop=false;
	static LinkedBlockingQueue<ByteBuffer> bufContainer=Queues
			.newLinkedBlockingQueue();
	
	static Callable<String> allocateDirectMemory(){
		return ()->{
			while(!stop){
				ByteBuffer buf=ByteBuffer.allocateDirect(_1M);
				bufContainer.offer(buf);
				LockSupport.parkNanos(bufContainer, TimeUnit.SECONDS.toNanos(1));
			}
			System.out.println(Thread.currentThread().getName()+" stopped");
			return "OK";
		};
	}
}

class UnsafeHolder{

	private static Unsafe unsafe;
	static {
		try {
			Field unsafeStaticField = Unsafe.class.getDeclaredField("theUnsafe");
			unsafeStaticField.setAccessible(true);
			unsafe = (Unsafe) unsafeStaticField.get(Unsafe.class);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	}
	public static Unsafe getUnsafe(){
		return unsafe;
	}
}
