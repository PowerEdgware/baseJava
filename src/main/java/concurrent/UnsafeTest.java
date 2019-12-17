package concurrent;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

@SuppressWarnings("restriction")
public class UnsafeTest {

	
	public static void main(String[] args)throws Exception {
		Field field=Unsafe.class.getDeclaredField("theUnsafe");
		field.setAccessible(true);
		Unsafe unsafe=(Unsafe) field.get(null);
		long m=unsafe.allocateMemory(1024*1024*1024*1024);
		System.out.println("m="+m);
		long a=unsafe.getAddress(m);
		System.out.println("a="+a);
	}
}
