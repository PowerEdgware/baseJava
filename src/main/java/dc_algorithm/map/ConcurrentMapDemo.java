package dc_algorithm.map;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMapDemo {

	private static final int RESIZE_STAMP_BITS = 16;

	public static void main(String[] args) {
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
		map.get("aaa");

		// i * scale
		// ashift=31-scale
		// i* scale + abase;
		// i<< ashift +abase -->i * 2^(ashift) +abase
		// 31 --> 0001 1111

		int c = 1 << 31;
		String bin;
		System.out.println((bin = Integer.toBinaryString(c)) + "\t" + bin.length());
		System.out.println(Integer.numberOfLeadingZeros(c));
		System.out.println(Integer.numberOfLeadingZeros(c) | (1 << 16 - 1));
		System.out.println(Integer.numberOfTrailingZeros(c));
		System.out.println(Integer.numberOfLeadingZeros(16));
		System.out.println(Integer.toBinaryString(1 << 15));
		System.out.println(Integer.toBinaryString(27));
		System.out.println(Integer.toBinaryString(1 << 15 | 27));
		System.out.println(1 << 2);
		System.out.println(spread(-10));
		System.out.println((-10 ^ (-10 >>> 16)) & 15);
		
		int n=32;
		int resizeStamp=resizeStamp(n);
		System.out.println(Integer.toBinaryString(resizeStamp)+"\t"+resizeStamp);
		
		
		
		System.out.println(Integer.numberOfLeadingZeros(64));
		
		System.out.println(Integer.toBinaryString(-1));
		System.out.println(Integer.toBinaryString(1));
		
	}

	static int spread(int h) {
		return (h ^ (h >>> 16)) & Integer.MAX_VALUE;
	}

	static final int resizeStamp(int n) {
		//10101 |10000000000000000000
		return Integer.numberOfLeadingZeros(n) | (1 << (RESIZE_STAMP_BITS - 1));
	}
}
