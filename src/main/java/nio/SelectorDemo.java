package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class SelectorDemo {

	static void startDamonThread(Selector selector, Thread main) {
		new Thread("selector-wakeUp-Thread") {
			public void run() {
				Scanner scan = new Scanner(System.in);//BufferedInputStream
				while (true) {
					System.out.println("Input w to wakeUp mainThread,i for interrupt mainThread ,q for quit");
					String l = scan.nextLine();
					if ("w".equals(l)) {
						selector.wakeup();
					} else if ("q".equals(l)) {
						if(main.isAlive()){
							System.out.println("Input i to teminate mainThread");
						}else{
							break;
						}
					} else if ("i".equals(l)) {
						main.interrupt();
					}else if("f".equals(l)){
						main.interrupt();
						break;
					}
				}
				scan.close();
				System.out.println(getName() + " stopped");
			};
		}.start();
	}

	public static void main(String[] args) {
		try {
			Selector selector = Selector.open();// SelectorProvider.provider().openSelector();
			Thread main = Thread.currentThread();

			startDamonThread(selector, main);

			// channel
			// SocketChannel.open();
			int port = 9901;
			SocketAddress socketAddress = new InetSocketAddress(port);
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.bind(socketAddress, 128);
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			// BitSet
			while (!Thread.currentThread().isInterrupted()) {
				int keysize = selector.select();
				System.out.println("select return eventSize=" + keysize);
				if (keysize <= 0)
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
					// dispatch(key);
				}
			}
			System.out.println(Thread.currentThread().getName() + " stopped");
			// Pipe 管道
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void someClass() {
		// EPollArrayWrapper
	}
}
