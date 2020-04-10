package lambda;

public class ClinitDemo {


	
	public static void main(String[] args) {
		Runnable run=()->{
			System.out.println(Thread.currentThread().getName()+" start");
			LoopClass loopClass=new LoopClass();
			System.out.println(Thread.currentThread().getName()+" end");
		};
		
		Thread t1=new Thread(run, "t1");
		Thread t2=new Thread(run, "t2");
		t1.start();
		t2.start();
	}
}

class LoopClass{
	static{
		int a=1;
		if(a>0){
			while(true){
				
			}
		}
	}
}
