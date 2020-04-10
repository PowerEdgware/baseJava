package lambda;

public class MutexDemo {

//	static Thread t=new Thread(OutRunner::doRun);//没问题
	static Thread t=new Thread(MutexDemo::new);//同类中函数式接口会有问题
	public MutexDemo(){
		System.out.println(Thread.currentThread().getName()+" enter");
	}
	static void doRun(){
		
	}
	
	static{
		t.start();
		
		try {
			t.join(3*1000);
		} catch (InterruptedException e) {
		}
	}
	public static void main(String[] args) {
		System.out.println("abc");
	}
}

class OutRunner{
	static void doRun(){
		
	}
}
