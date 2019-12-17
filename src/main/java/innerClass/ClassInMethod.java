package innerClass;

public class ClassInMethod {

	public A getMethodInnerClass(){
		class InMethodClass implements A{
			@Override
			public void aa() {
				//ClassInMethod.this.notify();
			}
		}
		return new InMethodClass();
	}
	
	public static void main(String[] args) {
		ClassInMethod classInMethod=new ClassInMethod();
		A a=classInMethod.getMethodInnerClass();
		System.out.println(a.getClass());
	}
	
}

interface A{
	void aa();
}

