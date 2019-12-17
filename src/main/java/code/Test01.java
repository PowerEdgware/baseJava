package code;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class Test01 {

	public static void main(String[] args) {
		byte b = -66;
		System.out.println(b & 0xF0 >>> 4);
		System.out.println(Integer.toBinaryString(b));
		byte[] arr = "@$%#^*".getBytes();
		for (byte c : arr) {
			System.out.print(c + ",");
		}
		System.out.println();
		System.out.println("===============");
		String enStr = Hex.encodeHexString(arr);
		System.out.println("encode=" + enStr);
		try {
			byte[] decArr = Hex.decodeHex(enStr.toCharArray());
			for (byte c : decArr) {
				System.out.print(c + ",");
			}
			System.out.println();
			System.out.println("decode=" + new String(decArr));
		} catch (DecoderException e) {
			e.printStackTrace();
		}
		
		System.out.println(Character.digit(new String("&".getBytes()).toCharArray()[0], 16));
	}
}
