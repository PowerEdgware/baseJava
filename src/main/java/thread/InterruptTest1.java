package thread;

public class InterruptTest1 {

	 public static void main(String[] args) throws Exception {  
	        Thread t = new Thread(new Worker());  
	        t.start();  
	          
	        Thread.sleep(200);  
	        t.interrupt();  
	          
	        System.out.println("Main thread stopped.");  
	    }  
	      
	    public static class Worker implements Runnable {  
	        public void run() {  
	            System.out.println("Worker started.");  
	              
	            try {  
	                Thread.sleep(500);  
	            } catch (InterruptedException e) { 
	            	/**
	            	 * InterruptedException - if any thread has interrupted the current thread. The interrupted status of the current thread is cleared when this exception is thrown.  
	            	 */
	                System.out.println("Worker IsInterrupted: " +   
	                        Thread.currentThread().isInterrupted());  
	            }  
	              
	            System.out.println("Worker stopped.");  
	        }  
	    }  
}
