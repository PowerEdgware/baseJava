package thread;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class StackThread {

	LinkedList<Object> list=new LinkedList<>();
	
	public synchronized void push(Object o){
		System.out.println(Thread.currentThread().getName()+" push");
		synchronized (list) {
			System.out.println(Thread.currentThread().getName()+" in list");
			list.addLast(o);
			notify();
		}
		System.out.println(Thread.currentThread().getName()+" out list");
	}
	
	public synchronized Object pop()throws Exception{
		System.out.println(Thread.currentThread().getName()+" pop");
		synchronized (list) {
			System.out.println(Thread.currentThread().getName()+" inlist");
			if(list.size()<=0){
				wait();
			}
			System.out.println(Thread.currentThread().getName()+" before remove");
			return list.removeLast();
		}
	}
	
	public static void main(String[] args) {
		
		final StackThread st=new StackThread();
		
		Thread ta=new Thread("t-a"){
			@Override
			public void run() {
				while(true){
					try {
						Object o=st.pop();
						System.out.println(o);
						LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		
		
		Thread tb=new Thread("t-b"){
			@Override
			public void run() {
				
				while(true){
					try {
						st.push(new Object());
						LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		tb.start();
		
		try {
		TimeUnit.MILLISECONDS.sleep(2);
	} catch (InterruptedException e1) {
		e1.printStackTrace();
	}
		
		ta.start();
	}
}
