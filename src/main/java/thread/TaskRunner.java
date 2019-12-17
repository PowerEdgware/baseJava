package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class TaskRunner {
	static int default_capacity = 200;
	private BlockingQueue<Task> queue;
	int nThreads = 10;
	ExecutorService eService = Executors.newFixedThreadPool(nThreads);

	public static TaskRunner create() {
		return new TaskRunner();
	}

	private TaskRunner() {
		this.queue = new ArrayBlockingQueue<>(default_capacity);

		doRun();
	}

	private void doRun() {
		for (int i = 0; i < nThreads; i++) {
			Runnable r = new Runnable() {
				@Override
				public void run() {
					Thread current = Thread.currentThread();
					try {
						while (!current.isInterrupted()) {
							Task task = queue.poll(5, TimeUnit.SECONDS);
							try {
								//current.interrupt();
								task.execute();
							} catch (Exception e) {
								current.interrupt();
								System.out.println(current.getName() + " Interrupted=" + current.isInterrupted());
							}
						}
					} catch (InterruptedException e) {
						// Restore the interrupted status
						current.interrupt();
						System.out.println(current.getName() + " InterruptedException");
					}
				}
			};
			eService.submit(r);
		}
	}

	public boolean addtask(Task task) {
		return queue.offer(task);
	}

	public void stop() {
		eService.shutdown();
	}

	public int UnfinishedTaskSize() {
		return queue.size();
	}

	interface Task {
		void execute();
	}

	class TaskImpl implements Task {

		@Override
		public void execute() {
			System.out.println("Task Executing." + Thread.currentThread().getName());
		}
	}

	public static void main(String[] args) throws Exception {
		final TaskRunner runner = TaskRunner.create();

		//
		AtomicBoolean stop = new AtomicBoolean(false);
		new Thread() {
			@Override
			public void run() {
				int offer_c = 0;
				while (!stop.get()) {
					boolean offer = runner.addtask(runner.new TaskImpl());
					try {
						System.out.println(++offer_c + " result=" + offer);
						sleep(1 * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		System.in.read();
		stop.set(true);
		System.out.println("Producer Stopped");
		runner.stop();
		System.out.println("UnfinishedTaskSize="+runner.UnfinishedTaskSize());
	}
}
