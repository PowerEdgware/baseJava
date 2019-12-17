package code;

public class HexByteTest {

	private static final char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };

	public static void main(String[] args) {
		String m="shangclswonns";
		byte[] mb=m.getBytes();
		for (byte b : mb) {
			System.out.print(b+",");
		}
		System.out.println();
		String s=byteArr2HexStr(mb);
		System.out.println(s);
		System.out.println("还原="+new String(hexStr2ByteArr(s)));
		System.out.println();
		String oril="402425235E2A";
		byte[] hexByte=hexStr2ByteArr(oril);
		for (byte b : hexByte) {
			System.out.print(b+",");
		}
		System.out.println();
		System.out.println(byteArr2HexStr(hexByte));
	}

	static String byteArr2HexStr(byte[] arr) {
		int len = arr.length;
		char[] temp = new char[len << 1];// double size of len
		for (int i = 0, j = 0; i < len; i++) {
			temp[j++] = DIGITS_UPPER[(arr[i] & 0xF0) >> 4];// high 4 bit
			temp[j++] = DIGITS_UPPER[arr[i] & 0x0F];// low 4 bit
		}

		return new String(temp);
	}
	
	static byte[] hexStr2ByteArr(String hexString) {
		// hexStr must double size of tow
		int hexLen = hexString.length();
		if ((hexLen & 0x01) != 0)
			throw new IllegalArgumentException("hexStr must be power of tow");
		char[] hexCh = hexString.toUpperCase().toCharArray();

		byte bArr[] = new byte[hexLen >> 1];
		for (int i = 0; i < bArr.length; i++) {
			int pos = 2 * i;
			//TODO assume indexOf(hexCh(pos))!=-1;
			bArr[i] = (byte) (indexOf(hexCh[pos]) << 4 | indexOf(hexCh[pos + 1]));
		}

		return bArr;
	}

	static int indexOf(char ch) {
		for (int index = 0; index < DIGITS_UPPER.length;) {
			if (DIGITS_UPPER[index] == ch)
				return index;
			++index;
		}
		return -1;
	}
}
