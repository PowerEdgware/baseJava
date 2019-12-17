package nio.demo;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class EventHandler implements Handler {

	SocketChannel socketChannel;
	SelectionKey selectionKey;

	ByteBuffer readBuffer = ByteBuffer.allocate(MAXIN);
	ByteBuffer outBuffer = ByteBuffer.allocate(MAXOUT);

	static final int MAXIN = 256 * 1024;
	static final int MAXOUT = 256 * 1024;
	static final Charset charset = Charset.forName("UTF-8");

	EventHandler(SocketChannel socketChannel, Selector selector) throws IOException {
		this.socketChannel = socketChannel;
		// 用selector注册套接字，并返回对应的SelectionKey，同时设置Key的interest set为监听该连接上得read事件
		this.selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
		// 绑定handler
		this.selectionKey.attach(this);
	}

	@Override
	public void handle(SelectionKey sk) throws IOException {
		if (sk.isReadable()) {
			System.out.println("[event]read");
			read();
		} else if (sk.isWritable()) {
			System.out.println("[event]write");
			write();
		}
	}

	/**
	 * 处理read事件
	 * 
	 * @throws IOException
	 */
	private void read() throws IOException {
		// 读取数据
		readBuffer.clear();
		StringBuilder content = new StringBuilder();
		int readNum = socketChannel.read(readBuffer);
		if (readNum == 0) {
			return;
		} else if (readNum < 0) {
			throw new IOException("exception.");
		} else {
			readBuffer.flip();
			content.append(charset.decode(readBuffer)); // decode
		}
		while (socketChannel.read(readBuffer) > 0) {
			readBuffer.flip();
			content.append(charset.decode(readBuffer)); // decode
		}
		// 处理数据
		process(content.toString());
		//
		selectionKey.interestOps(SelectionKey.OP_WRITE);
	}

	/**
	 * 处理客户端请求数据
	 * 
	 * @param content
	 */
	private void process(String content) throws IOException {
		System.out.println(
				"[receive from client] -> client:" + socketChannel.getRemoteAddress() + ", content: " + content);
		outBuffer = ByteBuffer.wrap(content.toUpperCase().getBytes());
	}

	/**
	 * 处理write事件
	 * 
	 * @throws IOException
	 */
	private void write() throws IOException {
		// 写数据
		socketChannel.write(outBuffer);
		if (outBuffer.remaining() > 0) {
			return;
		}
		//
		selectionKey.interestOps(SelectionKey.OP_READ);
	}
}
