package concurrent;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

public class SynchronousQueueDebugTest {
	private SynchronousQueue<Task> tasks;

	public SynchronousQueueDebugTest() {
		this.tasks = new SynchronousQueue<>();
	}

	public boolean putTask(Task t) {
		return tasks.offer(t);
	}

	public Task takeTask() throws InterruptedException {
		return tasks.take();
	}

	public static void main(String[] args) throws Exception {
		SynchronousQueueDebugTest debugTest = new SynchronousQueueDebugTest();
		AtomicBoolean takeStop = new AtomicBoolean(false);
		Thread takeThread = new Thread("take_thread-0") {
			public void run() {
				while (!takeStop.get()) {
					try {
						Task t = debugTest.takeTask();
						System.out.println(t + "\t" + System.currentTimeMillis());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(getName() + " stopped");
			};
		};
		takeThread.start();

		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
		for (int x = 0; x < 10; x++) {
			System.out.println(debugTest.putTask(new Task()));// 不能提交太快
			LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(30));
		}
		System.in.read();
		takeStop.set(true);
		takeThread.interrupt();
	}
}

class Task {

}
