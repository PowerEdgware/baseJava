package nio.demo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by zhangyiwen on 16/11/7. 服务端,会先发欢迎语,后续会将客户端发来的消息转成大写后返回
 */
public class NioServer {

	private InetAddress hostAddress;

	private int port;

	private Selector selector;

	private ServerSocketChannel serverChannel;

	private AtomicBoolean stopped = new AtomicBoolean(false);
	CountDownLatch cLatch = new CountDownLatch(1);

	public NioServer(InetAddress hostAddress, int port) throws IOException {
		this.hostAddress = hostAddress;
		this.port = port;

		// 初始化selector.绑定服务端监听套接字,感兴趣事件,对应的handler
		initSelector();
	}

	public static void main(String[] args) throws InterruptedException {
		try {
			// 启动服务器
			NioServer server = new NioServer(null, 9090);

			new Thread() {
				public void run() {
					server.start();
				};
			}.start();
			System.out.println(Thread.currentThread().getName()+" Waiting for start up...");
			server.waitForStart();
			System.out.println(Thread.currentThread().getName()+" Server started");
			try {
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"\tServer Stopping");
			server.stop();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化selector,绑定服务端监听套接字、感兴趣事件及对应的handler
	 * 
	 * @return
	 * @throws IOException
	 */
	private void initSelector() throws IOException {
		// 创建一个selector
		selector = SelectorProvider.provider().openSelector();
		// 创建并打开ServerSocketChannel
		serverChannel = ServerSocketChannel.open();
		// 设置为非阻塞
		serverChannel.configureBlocking(false);
		// 绑定端口
		serverChannel.socket().bind(new InetSocketAddress(hostAddress, port));
		// 用selector注册套接字，并返回对应的SelectionKey，同时设置Key的interest set为监听客户端连接事件
		SelectionKey selectionKey = serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		// 绑定handler
		selectionKey.attach(new Acceptor(serverChannel, selector));
	}

	public void start() {
		System.out.println(Thread.currentThread().getName()+" Server ready to start...");
		cLatch.countDown();
		while (!stopped.get()) {
			/*
			 * 选择事件已经ready的selectionKey,该方法是阻塞的.
			 * 只有当至少存在selectionKey,或者wakeup方法被调用,或者当前线程被中断,才会返回.
			 */
			try {
				selector.select();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (!selector.isOpen())
				continue;

			// 循环处理每一个事件
			Iterator<SelectionKey> items = selector.selectedKeys().iterator();
			while (items.hasNext()) {
				SelectionKey key = items.next();
				items.remove();
				if (!key.isValid()) {
					continue;
				}
				// 事件处理分发
				dispatch(key);
			}
		}
		doClose();
		System.out.println(Thread.currentThread().getName()+" Server stopped");
	}

	public void waitForStart() throws InterruptedException {
		cLatch.await();
	}

	void doClose() {
		if (serverChannel.isOpen())
			try {
				serverChannel.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		if (selector.isOpen())
			try {
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void stop() {
		if (stopped.get()) {
			return;
		}
		stopped.set(true);
		selector.wakeup();
	}

	/**
	 * 事件处理分发
	 * 
	 * @param sk
	 *            已经ready的selectionKey
	 */
	private void dispatch(SelectionKey sk) {
		// 获取绑定的handler
		Handler handler = (Handler) sk.attachment();
		try {
			if (handler != null) {
				handler.handle(sk);
			}
		} catch (IOException e) {
			e.printStackTrace();
			sk.channel();
			try {
				if (sk.channel() != null) {
					sk.channel().close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
