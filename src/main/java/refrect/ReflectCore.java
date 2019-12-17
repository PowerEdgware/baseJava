package refrect;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class ReflectCore {

	void readme() {
		//Method
		// MethodAccessorGenerator
		//--MagicAccessorImpl
		// ClassDefiner
		//ReflectionFactory
		//DelegatingClassLoader
		//PropertyDescriptor
	}
	
	void classLoad(){
//		[Loaded refrect.Man from file:/E:/eclipse/ws/baseJava/target/classes/]
//		[Loaded sun.reflect.NativeMethodAccessorImpl from D:\Java\jdk1.8.0_77\jre\lib\rt.jar]
//		[Loaded sun.reflect.DelegatingMethodAccessorImpl from D:\Java\jdk1.8.0_77\jre\lib\rt.jar]
	}

	public static void main(String[] args) throws Exception {
		System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");//
		//reflect nocache
		//sun.reflect.noCaches
		System.clearProperty("sun.reflect.noCaches");
		String rst=System.setProperty("sun.reflect.noCaches", "true");//
		// 一些例子
		//-XX:+TraceClassLoading
		Class<?> manClazz = Class.forName("refrect.Man");
		Object instanceMan = manClazz.newInstance();

		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			Method speakMethod = manClazz.getMethod("speak", String.class);
			Method workMethod = manClazz.getMethod("work");
			speakMethod.invoke(instanceMan, String.valueOf(i));
			workMethod.invoke(instanceMan);
			LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(100));
		}

		testRootMethod();
	}
	
	static void testRootMethod()throws Exception{
		Class<?> manClazz = Class.forName("refrect.Man");
		Method speakMethod1 = manClazz.getMethod("speak", String.class);
		Method speakMethod2 = manClazz.getMethod("speak", String.class);
		
		System.out.println(speakMethod1==speakMethod2);//false
	}
}
