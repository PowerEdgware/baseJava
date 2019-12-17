package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 提前总结：

interrupt() 向当前调用者线程发出中断信号(设置中断信号)

isinterrupted() 查看当前中断信号是true还是false

interrupted() 是静态方法，查看当前中断信号是true还是false并且清除中断信号，顾名思义interrupted为已经处理中断信号。

注：interrupt()方法发出的中断信号只能被wait() sleep() join()这三个方法捕捉到并产生中断。（目前我所知）
 *
 */
public class InterruptTest {

	public static void main(String[] args) throws Exception {  
        Thread t = new Thread(new Worker());  
        t.start();  
          
        Thread.sleep(200);  
        t.interrupt();  
        //System.out.println(t.isInterrupted());
          
        System.out.println("Main thread stopped.");  
    }  
	
	 public static class Worker implements Runnable {  
	        public void run() {  
	            System.out.println("Worker started.");  
	            Thread curr = Thread.currentThread();  
	            try {  
//	                Thread.sleep(500);  
	            	LockSupport.parkNanos(TimeUnit.NANOSECONDS.convert(500, TimeUnit.MILLISECONDS));
	                /**
	                 * interrupt方法是用于中断线程的，调用该方法的线程的状态将被置为"中断"状态。注意：线程中断仅仅是设置线程的中断状态位，不会停止线程。
	                 * 需要用户自己去监视线程的状态为并做处理。支持线程中断的方法（也就是线程中断后会抛出InterruptedException的方法，
	                 * 比如这里的sleep，以及Object.wait等方法）就是在监视线程的中断状态，一旦线程的中断状态被置为“中断状态”，就会抛出中断异常。
	                 */
	            	System.out.println(curr.isInterrupted());
	            } catch (Exception e) {  
	            	/**
	            	 * InterruptedException - if any thread has interrupted the current thread. The interrupted status of the current thread is cleared when this exception is thrown.  
	            	 */
	                
	                //再次调用interrupt方法中断自己，将中断状态设置为“中断”  
	                curr.interrupt();  
	                System.out.println("Worker IsInterrupted: " + curr.isInterrupted());  
	                System.out.println("Worker IsInterrupted: " + curr.isInterrupted());  
	                System.out.println("Static Call: " + Thread.interrupted());//clear status  
	                System.out.println("---------After Interrupt Status Cleared----------");  
	                System.out.println("Static Call: " + Thread.interrupted());  
	                System.out.println("Worker IsInterrupted: " + curr.isInterrupted());  
	                System.out.println("Worker IsInterrupted: " + curr.isInterrupted());  
	            }  
	              
	            System.out.println("Worker stopped.");  
	        }  
	    }  
}
