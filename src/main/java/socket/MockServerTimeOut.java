package socket;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

public class MockServerTimeOut {

	static int DEFAULT_THREAD_SIZE = Runtime.getRuntime().availableProcessors() * 2;

	public static void main(String[] args) throws IOException {
		int port = 9908;
		int backlog = 128;
		boolean doAccept = true;
		int threadSize = 16;
		if (threadSize <= 0) {
			threadSize = DEFAULT_THREAD_SIZE;
		}

		System.out.println(
				"server port=" + port + ",backlog=" + backlog + ",doAccept=" + doAccept + ",threadSize=" + threadSize);
		java.util.List<TaskRunnerTimeout> container = new CopyOnWriteArrayList<>();

		InetAddress address = InetAddress.getByName("192.168.1.192");
		ServerSocket serverSocket = new ServerSocket(port, backlog, address);
		AtomicBoolean stop = new AtomicBoolean(false);
		// ExecutorService service = Executors.newCachedThreadPool();
		ExecutorService service = Executors.newFixedThreadPool(threadSize);
		Thread startThread = new Thread("start_thread") {
			public void run() {
				while (!stop.get()) {
					try {
						if (doAccept) {
							Socket socket = serverSocket.accept();
							System.out.println("Incoming clientSocket:" + socket + " time=" + LocalDateTime.now());
							TaskRunnerTimeout tr = new TaskRunnerTimeout(socket);
							service.execute(tr);
							container.add(tr);
						}
						// LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(3));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Server Stopped." + this.getName() + "\tAt:" + LocalDateTime.now());
			};
		};
		startThread.start();
		System.out.println("Server Start At:" + LocalDateTime.now() + " port=" + port + " doAcp=" + doAccept);
		try {
			System.in.read();
			stop.set(true);
			serverSocket.close();
			// stop executing task
			container.forEach((tr) -> {
				tr.stop();
			});

			boolean suc = true;
			service.shutdown();
			// Wait a while for existing tasks to terminate
			while (!service.awaitTermination(20, TimeUnit.SECONDS)) {
				List<Runnable> remained = service.shutdownNow();// Cancel
																// currently
																// executing
																// tasks
				System.out.println("queued=" + remained);
				if (remained != null)
					remained.forEach((r) -> {
						if (r instanceof TaskRunnerTimeout) {
							TaskRunnerTimeout t = (TaskRunnerTimeout) r;
							t.stop();
						}
					});
				// Wait a while for tasks to respond to being cancelled
				if (!service.awaitTermination(20, TimeUnit.SECONDS)) {
					suc = false;
					System.err.println("Pool did not terminate." + service);
					break;
				}
			}
			System.out.println("App Stopped." + Thread.currentThread().getName() + " pool shuwdown suc?" + suc);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class TaskRunnerTimeout implements Runnable {
	Socket socket;
	boolean RUNNING = true;

	public TaskRunnerTimeout(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			socket.setSoTimeout(3 * 1000);
			InputStream in = socket.getInputStream();
			// BufferedReader
			BufferedInputStream buf = new BufferedInputStream(in);
			byte b[] = new byte[512];
			int s = -1;
			try {
				s = buf.read(b);
			} catch (Exception e) {
				e.printStackTrace();
			}
			while (RUNNING && s>0) {
				if (s > 0) {
					String recv = new String(b, 0, s);
					System.out.println("Client " + Thread.currentThread().getName() + " Recv:" + "len=" + recv.length()
							+ " dat=" + recv + " time=" + LocalDateTime.now());
				}
				if(socket.isClosed()||!socket.isConnected()||socket.isInputShutdown()){
					System.out.println("socket "+socket+" invalid");
					break;
				}
				try {
					s = buf.read(b);
				} catch (Exception e) {
					s=-1;
					System.out.println(e.toString()+"\t"+"xxxx");
				}
			}
		} catch (IOException e) {
			RUNNING = false;
			e.printStackTrace();
		}
		stop();
		System.out.println("Client " + Thread.currentThread().getName() + " Stopped At:" + LocalDateTime.now());
	}

	public void stop() {
		try {
			RUNNING = false;
			if (socket != null)
				socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
