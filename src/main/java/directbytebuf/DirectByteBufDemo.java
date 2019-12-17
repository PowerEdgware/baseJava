package directbytebuf;

import java.nio.ByteBuffer;

public class DirectByteBufDemo {

	public static void main(String[] args) {
//		DirectBuffer
		int capacity=128;
		ByteBuffer buf=ByteBuffer.allocateDirect(capacity);
		buf=ByteBuffer.allocate(capacity);
	}
}
