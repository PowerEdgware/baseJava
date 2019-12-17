package baseJava;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectDemo {

	public static void main(String[] args) {
		ReflectDemo demo = new ReflectDemo();
		Method[] methods = ReflectDemo.class.getDeclaredMethods();
		Object[] aargs=new Object[]{"aa",2,"33"};
		for (Method method : methods) {
			if ("aa".equals(method.getName())) {
				try {
					method.invoke(demo,aargs);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}

	void aa(String a, int b, Object c) {
		System.out.println("suc");
	}
}
