package test;

public class VariMask {

	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(-127 & 0xff));
		System.out.println(-1 & 0xff);
		System.out.println(Integer.MIN_VALUE == -Integer.MIN_VALUE);
		Integer c = Integer.parseInt("184");
		Integer d = Integer.parseInt("184");
		System.out.println(c==d);
		System.out.println(-189/10);
	}
}
