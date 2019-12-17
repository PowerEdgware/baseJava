import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ReflectDemo {

	public static void main(String[] args) {
		
		A a=new AImpl();
		
		A proxya=(A) Proxy.newProxyInstance(a.getClass().getClassLoader(), a.getClass().getInterfaces(), new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("invoke "+method+" "+Arrays.toString(args));
				return method.invoke(a, args);
			}
		});
		try {
			Method m=proxya.getClass().getDeclaredMethod("doit", String.class);
			Object ret=m.invoke(proxya, "abc");
			System.out.println(ret);
			
			proxya.doit("acb");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static class AImpl implements A{
		@Override
		public void doit(String params) {
			
		}
		
	}
	
	interface A{
		void doit(String params);
	}
}
