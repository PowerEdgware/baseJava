package socket;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class MockServerV2 {

	static int DEFAULT_THREAD_SIZE = Runtime.getRuntime().availableProcessors() * 2;
	static ExecutorService service;
	static java.util.List<TaskRunnerV2> container = new CopyOnWriteArrayList<>();

	public static void main(String[] args) throws IOException {
		int argsLen = 4;
		if (args.length != argsLen) {
			System.out.println("args length must be " + argsLen);
			return;
		}
		int port = Integer.parseInt(args[0]);
		int backlog = Integer.valueOf(args[1]);
		boolean doAccept = Integer.parseInt(args[2]) > 0 ? true : false;
		int threadSize = Integer.valueOf(args[3]);
		if (threadSize <= 0) {
			threadSize = DEFAULT_THREAD_SIZE;
		}

		System.out.println(
				"server port=" + port + ",backlog=" + backlog + ",doAccept=" + doAccept + ",threadSize=" + threadSize);

		ServerSocket serverSocket = new ServerSocket(port, backlog);
		AtomicBoolean stop = new AtomicBoolean(false);
		// ExecutorService service = Executors.newCachedThreadPool();
		service = Executors.newFixedThreadPool(threadSize);

		Runnable run = () -> {
			doAcceptAndRun(stop, doAccept, serverSocket);
		};

		Future<?> f=service.submit(run);
		
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
						if (r instanceof TaskRunner) {
							TaskRunner t = (TaskRunner) r;
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

	private static void doAcceptAndRun(AtomicBoolean stop, boolean doAccept, ServerSocket serverSocket) {
		while (!stop.get()) {
			try {
				if (doAccept) {
					Socket socket = serverSocket.accept();
					System.out.println("Incoming clientSocket:" + socket + " time=" + LocalDateTime.now());
					TaskRunnerV2 tr = new TaskRunnerV2(socket);
					service.execute(tr);
					container.add(tr);
				}
				// LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(3));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Server Stopped." + Thread.currentThread().getName() + "\tAt:" + LocalDateTime.now());
	}
}

class TaskRunnerV2 implements Runnable {
	Socket socket;
	boolean RUNNING = true;

	public TaskRunnerV2(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			// socket.setSoTimeout(60*1000);
			InputStream in = socket.getInputStream();
			BufferedInputStream buf = new BufferedInputStream(in);
			byte b[] = new byte[1024];
			int s = buf.read(b);
			while (RUNNING && s != -1) {
				System.out.println("Client " + Thread.currentThread().getName() + " Recv:" + new String(b, 0, s)
						+ " time=" + LocalDateTime.now());
				s = in.read(b);
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
