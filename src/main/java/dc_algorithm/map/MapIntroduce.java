package dc_algorithm.map;

import java.lang.reflect.Field;

public class MapIntroduce {
	static final int MAXIMUM_CAPACITY = 1 << 30;

	public static void main(String[] args) {
		// HashMap
		// WeakHashMap
		// LinkedHashMap
		// TreeMap
		// Object
		// ConcurrentHashMap

		int cap = 16;
		int sizefor = tableSizeFor(cap);
		System.out.println(sizefor);

		// TODO test final
		_1 c_1 = new _1(110);
		System.out.println(c_1.getHash());

		try {
			Field f = c_1.getClass().getDeclaredField("hash");
			f.setInt(c_1, 112);
			System.out.println("newHash=" + c_1.getHash());// java.lang.IllegalAccessException:
															// Can not set final
															// int field
															// dc_algorithm.map._1.hash
															// to (int)112
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		System.out.println(Integer.toBinaryString(HASH_BITS));
		
		int scale=4;///00000100
		int result=Integer.numberOfLeadingZeros(scale);
		System.out.println(result);

	}
	static final int HASH_BITS = 0x7fffffff;

	static final int tableSizeFor(int cap) {
		int n = cap - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
	}
}

class _1 {
	final int hash;

	public _1(int hash) {
		this.hash = hash;
	}

	public int getHash() {
		return this.hash;
	}

	// public void setHash(int newHash){
	// this.hash=newHash;//The final field _1.hash cannot be assigned
	// }
}
