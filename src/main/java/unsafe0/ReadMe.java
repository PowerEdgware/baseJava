package unsafe0;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

@SuppressWarnings("restriction")
public class ReadMe {

	private static Unsafe unsafe;

	static {
		try {
			Field unsafeStaticField = Unsafe.class.getDeclaredField("theUnsafe");
			unsafeStaticField.setAccessible(true);
			unsafe = (Unsafe) unsafeStaticField.get(Unsafe.class);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	}

	static Unsafe getUnsafeInstance() {
		return unsafe;
	}
	
	static int demoUse(){
		long addr=unsafe.allocateMemory(100);
		int page=unsafe.pageSize();
		System.out.println(addr+"\t page="+page);
		return 0;
	}

	public static void main(String[] args) throws Exception {
		if(demoUse()>0){
			return;
		}

		Unsafe u = getUnsafeInstance();
		//u.getInt(paramLong)
		
		// 1.修改和读取数组中的值
		final int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int b = u.arrayBaseOffset(arr.getClass());// arrayBaseOffset:
													// 返回当前数组第一个元素地址相对于数组起始地址的偏移值,16
		int s = u.arrayIndexScale(arr.getClass());// arrayIndexScale:
													// 返回当前数组一个元素占用的字节数,在本例中返回4。
		System.out.println(b + "\t" + s);
		u.putInt(arr, (long) b + s * 9, 1);// putInt(obj,offset,intval):设置对象paramObject起始地址为
											// paramLong位置的值为paramInt
		for (int i = 0; i < 10; i++) {
			int v = u.getInt(arr, (long) b + s * i);// getInt(obj,offset):
													// 获取数组对象obj的起始地址，加上偏移值，得到对应元素的地址，从而获得元素的值。
			System.out.print(v + " ");// 偏移值: 数组元素偏移值 = arrayBaseOffset +
										// arrayIndexScalse * i。
		}
		System.out.println("============");
		// 2.获取对象实例
		/// ** Allocate an instance but do not run any constructor.
		// Initializes the class if it has not yet been. */
		// public native Object allocateInstance(Class cls) throws
		// InstantiationException;
		Test t = (Test) u.allocateInstance(Test.class);
		System.out.println(t.intfield);// 0

		// 3.修改静态变量和实例变量的值
		long fieldOffSet = u.objectFieldOffset(t.getClass().getDeclaredField("intfield"));// 获取对象某个属性的地址偏移值。
		System.out.println("before:" + t.intfield);
		u.putInt(t, fieldOffSet, 2);
		System.out.println("after:" + t.intfield);
		Field staticIntField = Test.class.getDeclaredField("staticIntField");
		Object o = u.staticFieldBase(staticIntField);
		System.out.println(o == Test.class);
		long staticFieldOffset = u.staticFieldOffset(staticIntField);
		u.putInt(o, staticFieldOffset, 10);
		System.out.println(Test.staticIntField);

		String s2 = new String("abc");// 同时会生成堆中的对象 以及常量池中1的对象，但是此时s1是指向堆中的对象的
		// String s3=s2.intern();// 常量池中的已经存在
		s2.intern();
		String s1 = "abc";
		System.out.println(s1 == s2);// false
		// /System.out.println(s3==s1);//true

		String ss0 = "ab";
		String ss1 = ss0 + "cd";
		// // 如果调用ss1.intern 则最终返回true
		String ss2 = "abcd";
		System.out.println(ss1 == ss2);// false

		String ss_0 = "ab" + "cd";
		System.out.println(ss_0 == ss2);// true

		String s3 = new String("1") + new String("1"); // 此时生成了四个对象 常量池中的"1" +
														// 2个堆中的"1" +
														// s3指向的堆中的对象（注此时常量池不会生成"11"）
		s3.intern(); // jdk1.7之后，常量池不仅仅可以存储对象，还可以存储对象的引用，会直接将s3的地址存储在常量池
		String s4 = "11"; // jdk1.7之后，常量池中的地址其实就是s3的地址
		System.out.println(s3 == s4); // jdk1.7之前false， jdk1.7之后true
	}
}

class Test {
	public int intfield = -1;
	public static int staticIntField;
	public static int[] arr;

	private Test() {
		this.intfield = 5;
		System.out.println("constructor called");
	}
}
