package protobuf.demo;

import java.util.Arrays;

import org.apache.commons.codec.binary.Hex;

import com.google.protobuf.InvalidProtocolBufferException;

import protobuf.demo.DemoLogin.DemoReq;

import com.google.protobuf.ByteString.ByteIterator;

public class ProtoTest {

	public static void main(String[] args) {
		int accID = 1;
		String name = "";
		boolean suc = false;
		String email = "all@189.cn";
		DemoReq demoReq = DemoReq.newBuilder().setAcctID(accID).setName(name).setSuc(suc).setEmail(email).build();

		//demoReq.AcctID=255经过protobuf编码
		System.out.println(demoReq);

		System.out.println("消息编码：");
		//编码后的byte数组：RawBytes=[8, -1, 1]
		byte[] arr = demoReq.toByteArray();
		System.out.println("RawBytes=" + Arrays.toString(arr));

		//对应二进制：1000|11111111|1|
		ByteIterator byteIterator = demoReq.toByteString().iterator();
		while (byteIterator.hasNext()) {
			System.out.print(byte2BinString(byteIterator.nextByte()) + "|");
		}
		System.out.println();
		//转成十六进制：08ff01 对应的二进制：key:00001000 value=11111111 00000001 
		char[] hexC = Hex.encodeHex(arr);
		System.out.println("toHexStr:" + hexC.length);
		for (char b : hexC)
			System.out.print(b);
		System.out.println();
		System.out.println("====================");
		System.out.println("编码分析：");
		int key = 0x22;//00001000
		System.out.println("key=" + key);
		//key，去掉最高位，右移三位即为字段标号
		int field_number = key >> 3;
		//key最低三位：字段类型
		int field_type = key & 0x07;
		//field_number=1,field_type=0 |0==Varint类型
		System.out.println("field_number=" + field_number + ",field_type=" + field_type);
		//fieldType=0 即为Varint类型 编码后value=11111111 00000001 
		//把value按照varints还原
		//1，去除每个字节高位：1111111 0000001 
		//2，翻转第一行的两个字节：0000001 1111111
		//3，将翻转后的两个字节直接连接并去除高位0：11111111。值=255
		
		System.out.println("====================");
		System.out.println("消息解码:");
		try {
			DemoReq dr = DemoLogin.DemoReq.parseFrom(arr);
			System.out.println(dr);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		System.out.println(Integer.toBinaryString(255));
		
		byte bb=(byte) 255;
		System.out.println(0^(bb<<7));
		
		String s=null;
		System.out.println((s=Integer.toBinaryString(-1))+"\t"+s.length());
		
		//Varint编码格式的缺点:
		//第一，不利于表示大数。
		//第二，不适合表示负数
		//TODO int32 int64针对-1
		//1.-1的二进制编码 Integer.toBinaryString(-1);
		//11111111 11111111 11111111 11111111
		//int 32
		//11111111 11111111 11111111 11111111  00001111
		//0001111 1111111 1111111 1111111 1111111 
		//还原
		//11111111 11111111 11111111 11111111
		
	}

	static String byte2BinString(byte b) {
		return Integer.toBinaryString(b & 0xff);
	}

	/**
	 * byte数组转换为二进制字符串,每个字节以","隔开
	 **/
	public static String conver2HexStr(byte[] b) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			result.append(Long.toString(b[i] & 0xff, 2) + ",");
		}
		return result.toString().substring(0, result.length() - 1);
	}

}
