package protobuf.repeatedPojo;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.binary.Hex;

import com.google.protobuf.ByteString.ByteIterator;

import protobuf.repeatedPojo.RepeatedPojo.MapFieldEntry;

public class PojoTest {

	public static void main(String[] args) {

		String reqId = UUID.randomUUID().toString();
		reqId = "";
		MapFieldEntry m1 = MapFieldEntry.newBuilder().setKey("id").setValue("Idea").build();
		MapFieldEntry m2 = MapFieldEntry.newBuilder().setKey("num").setValue("90").build();
		List<RepeatedPojo.MapFieldEntry> values = Arrays.asList(m2, m1);
		RepeatedPojo.PojoMap pojoMap = RepeatedPojo.PojoMap.newBuilder().setReqId(reqId).addAllParamPojo(values)
				.build();

		System.out.println(pojoMap);

		System.out.println("消息编码：");

		byte[] streamData = pojoMap.toByteArray();
		// System.out.println(new String(streamData));

		System.out.println("RawBytes=" + Arrays.toString(streamData));

		ByteIterator byteIterator = pojoMap.toByteString().iterator();
		while (byteIterator.hasNext()) {
			System.out.print(byte2BinString(byteIterator.nextByte()) + "|");
		}
		System.out.println();
		char[] hexC = Hex.encodeHex(streamData);
		System.out.println("toHexStr:" + hexC.length);
		for (char b : hexC)
			System.out.print(b);
		System.out.println();
		System.out.println("====================");
		System.out.println("编码分析：");
		String encodeReq = new String(hexC);
		System.out.println(encodeReq);
		// TODO toHexStr:46
		// TODO 12090a036e756d12023930120a0a026964120449646561
		
		// 由于在编码后每一个字段的key都是varint类型，key的值是由字段标号和字段类型合成编码所得，其公式如下：
		// field_number << 3 | field_type ==> tag type length value[TTLV]
		int key = 0x12;// variant类型
		int tag = key >> 3;
		int type = key & 0x07;
		System.out.println("tag=" + tag + "\ttype=" + type);
		// map<k,v>元组
		// 依次分别k->key v->key|k->value v->value

		for (int i = 0; ++i < 3; System.out.println("=============")) {
		}
		System.out.println("消息解码：");
		try {

			RepeatedPojo.PojoMap decodeReq = RepeatedPojo.PojoMap.parseFrom(streamData);
			System.out.println(decodeReq);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static String byte2BinString(byte b) {
		return Integer.toBinaryString(b & 0xff);
	}
}
