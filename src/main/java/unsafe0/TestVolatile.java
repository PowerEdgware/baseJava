package unsafe0;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

@SuppressWarnings("restriction")
public class TestVolatile {
	
	private volatile int  val;

	private final static Unsafe unsafe;
	private static final long valOffset;

	static {
		try {
			Field unsafeStaticField = Unsafe.class.getDeclaredField("theUnsafe");
			unsafeStaticField.setAccessible(true);
			unsafe = (Unsafe) unsafeStaticField.get(Unsafe.class);
			valOffset=unsafe.objectFieldOffset(TestVolatile.class.getDeclaredField("val"));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			throw new Error(e);
		}
	}
	
	public int get(){
		return unsafe.getIntVolatile(this, valOffset);
	}
	public int getVal(){
		return val;
	}
	
	public boolean compareAndSet(int expect, int update){
		return unsafe.compareAndSwapInt(this, valOffset, expect, update);
	}
	
	
	public static void main(String[] args) {
//		AtomicInteger
		TestVolatile testVolatile=new TestVolatile();
		testVolatile.compareAndSet(0, 10);
		testVolatile.compareAndSet(0, 100);
		int val=testVolatile.get();
		System.out.println(val+"\t"+testVolatile.val);
	}
}
