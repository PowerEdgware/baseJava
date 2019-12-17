package nio.demo;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Acceptor implements Handler {
	static final Charset charset = Charset.forName("UTF-8");

	private ServerSocketChannel serverChannel;

	private Selector selector;

	public Acceptor(ServerSocketChannel serverChannel, Selector selector) {
		this.serverChannel = serverChannel;
		this.selector = selector;
	}

	@Override
	public void handle(SelectionKey sk) throws IOException {
		System.out.println("[event]connect");
		// 建立连接
		SocketChannel socketChannel = serverChannel.accept();
		System.out.println("[new client connected] client:" + socketChannel.getRemoteAddress());
		// 设置为非阻塞
		socketChannel.configureBlocking(false);
		// 创建Handler,专门处理该连接后续发生的OP_READ和OP_WRITE事件
		new EventHandler(socketChannel, this.selector);
		// 发送欢迎语
		socketChannel.write(charset.encode("welcome my client."));
	}

}
