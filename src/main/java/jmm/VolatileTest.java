package jmm;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

@SuppressWarnings("restriction")
public class VolatileTest {

	public volatile int inc = 0;
	private static  Unsafe unsafe;

	private static long incOffset;
	static {
		try {
			Field unsafeStaticField = Unsafe.class.getDeclaredField("theUnsafe");
			unsafeStaticField.setAccessible(true);
			unsafe = (Unsafe) unsafeStaticField.get(Unsafe.class);
			incOffset=unsafe.objectFieldOffset(VolatileTest.class.getDeclaredField("inc"));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	}

	public void safeIncrease() {
		unsafe.getAndAddInt(this, incOffset, 1);
	}
	public void increase() {
		inc++;
	}

	public static void main(String[] args) {
		//AtomicInteger
		final VolatileTest test = new VolatileTest();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 1000; j++)
						test.increase();
				};
			}.start();
		}

		while (Thread.activeCount() > 1) // 保证前面的线程都执行完
			Thread.yield();
		System.out.println(test.inc);
	}
}
