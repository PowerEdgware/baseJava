package socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class MockClientTimeout {
	static String plainText = "shjsenifw283892hnscjw987432nsnsnieenw".toUpperCase();

	static int clientSize =1;

	public static void main(String[] args) {
		int serverPort = 9908;
		int timeout = 60 * 1000;
		final StringBuffer ip = new StringBuffer("192.168.88.132");
		 ip.replace(0, ip.length(), "192.168.1.192");
		System.out.println(ip.toString());

		AtomicBoolean stop = new AtomicBoolean(false);
		java.util.List<Thread> container = new CopyOnWriteArrayList<>();
		AtomicInteger sucNum = new AtomicInteger(0);

		CountDownLatch latch = new CountDownLatch(clientSize);
		for (; clientSize-- > 0;) {
			Thread startThread = new Thread("multi_client_starter-" + (clientSize + 1)) {
				@Override
				public void run() {
					// while (!stop.get() && clientSize-- > 0) {
					Socket socket = new Socket();
					try {
						InetAddress aInetAddress = InetAddress.getByName(ip.toString());
						SocketAddress endpoint = new InetSocketAddress(aInetAddress, serverPort);
						long start = System.currentTimeMillis();
						socket.connect(endpoint, timeout);
						long end = System.currentTimeMillis();
						System.out.println("Client=" + this.getName() + "\t" + socket + " connected to port="
								+ serverPort + " cost=" + (end - start) + " ms at=" + LocalDateTime.now());
						if (socket.isConnected()) {
							ClientRunnerTimeOut cr = new ClientRunnerTimeOut(socket);
							cr.start();
							container.add(cr);
							sucNum.incrementAndGet();
						}
						// latch.countDown();
					} catch (IOException e) {
						System.out
								.println("client " + this.getName() + "\t" + socket + " connected fail.  reason=" + e);
						e.printStackTrace();
						LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
						// continue;
					} finally {
						latch.countDown();
					}
					// }
					// System.out.println("Client Starter " + this.getName() + "
					// Stopped. at:" + LocalDateTime.now());
				}
			};
			startThread.start();
		}
		try {
			latch.await();
			System.out.println("connected suc num=" + sucNum.intValue());
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			System.in.read();
			stop.set(true);
			container.forEach((t) -> {
				if (t.isAlive())
					t.interrupt();
			});
			System.out.println("APP " + Thread.currentThread().getName() + " Stopped. at:" + LocalDateTime.now());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class ClientRunnerTimeOut extends Thread {
	Socket socket;
	boolean RUNNING = true;
	long count=0;

	public ClientRunnerTimeOut(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		OutputStream out = null;
		// InputStream in=null;
		try {
			out = socket.getOutputStream();
			System.out.println(out.getClass()+"\t"+getName());//SocketOutputStream
			// in=socket.getInputStream();
		} catch (IOException e) {
			halt00();
			e.printStackTrace();
		}
		int count=0;
		while (RUNNING && !isInterrupted()) {
			try {
				if(++count>=3){
					LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(10*1000));
					continue;
				}
				String rawDat = rawData();
				 System.out.println(this.getName() + " Send data '" + rawDat +
				 "' at=" + LocalDateTime.now());
				out.write((this.getName() + ">" + rawDat + "^^").getBytes());
				out.flush();//TODO 立即把程序缓冲区数据写道内核缓冲区而不是输出到文件或socket
				LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(10*1000));
			} catch (Exception e) {
				RUNNING = false;
				System.out.println("cli-" + this.getName() + "\t" + socket + " writeData error " + e.toString());
				// LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(20));
				e.printStackTrace();
			}
		}
		halt00();
		System.out.println(this.getName() + " Stopped at:" + LocalDateTime.now());
	}

	// 53537 53665 53675 53683
	String rawData() {
		Random rnd = new Random();
		int textLen = MockClientTimeout.plainText.length();
		int start = rnd.nextInt(textLen);
		return MockClientTimeout.plainText.substring(start);
	}

	public void halt00() {
		try {
			RUNNING = false;
			if (socket != null)
				socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
