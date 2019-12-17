package test;

public class BitTest {

	public static void main(String[] args) {
		int x = -1;
		System.out.println(Integer.toBinaryString(x));
		System.out.println(Integer.toBinaryString(x << 31));
		System.out.println(x << 31);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.toBinaryString(255));
		System.out.println(Integer.MIN_VALUE);
		// 0000 0001 0010 1100
		// 0010 1100 0000 0001 反转
		// 1010 1100 0000 0001 加高位

		// 0000 0000 1111 1111
		// 1111 1111
		// 0000 0001 1111 1111
		// 000 0001 111 1111
		// 111 1111 000 0001
		System.out.println("======================");
		System.out.println(18 >> 3);
		// x<<3|y=12
		//
		System.out.println(18 & 0x07);
		System.out.println(Integer.toHexString(18));
		System.out.println(Integer.toBinaryString(0x07));
		System.out.println("=============================");

		String hexStr = "22";
		int num = Integer.parseInt(hexStr, 16);
		// field_number << 3 | field_type
		System.out.println("字段标号：" + (num >>3 ));
		System.out.println("字段类型type：" + (num & 0x07));
		
		byte bb=-1;
		System.out.println(Integer.toBinaryString(bb));
		System.out.println(bb&0xff);
		System.out.println(Integer.toBinaryString(bb&0xff));
	

	}

}
