package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class NIOTest {

	public static void main(String[] args) throws IOException {
		// ServerSocketChannel
		// SocketChannel
		// ByteBuffer
		Selector selector = Selector.open();

		ExecutorService s = Executors.newFixedThreadPool(1);

		s.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + " ready to execute");
				LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
				selector.wakeup();
			}
		});
		selector.select();
		System.out.println(Thread.currentThread().getName() + " stopped");
		s.shutdown();

	}
}
