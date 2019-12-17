package innerClass;

public class InterfaceInnerClass {

}

//接口的内部类
interface ClassInInterface{
	void doSomething();
	
	class Test implements ClassInInterface{
		@Override
		public void doSomething() {
		}
	}
}
