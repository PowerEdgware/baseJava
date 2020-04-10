package lambda;
//@FunctionalInterface
//ClassLoader
public class LambdaDemo {

	public static void main(String[] args) {
		LambdaDemo demo=new LambdaDemo();
		new LoaderTest();
		
		Thread t=new Thread(LambdaDemo::static_doRun);
		Thread t2=new Thread(demo::method_doRun);
		
		t.start();
		t2.start();
		
		while(Thread.activeCount()>1){
			Thread.yield();
		}
		
	}
	
	public static void static_doRun(){
		System.out.println("static_doRun ");
	}
	public void method_doRun(){
		System.out.println("method_doRun ");
	}
}

class LoaderTest{
	
}
