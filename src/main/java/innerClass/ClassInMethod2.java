package innerClass;

public class ClassInMethod2 {

	public B getMethodInnerClass(){
		class InMethodClass implements B{
			@Override
			public void aa() {
				//ClassInMethod.this.notify();
			}
		}
		return new InMethodClass();
	}
	
	public B getInnerBWithoutName(){
		return ()->{
			
		};
	}
	public B getInnerBWithoutNameV2(){
		return new B(){
			@Override
			public void aa() {
			}
		};
	}
	
	public static void main(String[] args) {
		ClassInMethod2 classInMethod=new ClassInMethod2();
		B a=classInMethod.getMethodInnerClass();
		System.out.println(a.getClass());
	}
	
}

interface B{
	void aa();
}

