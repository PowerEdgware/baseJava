package concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorsTest {

	private static final int COUNT_BITS = Integer.SIZE - 3;
	private static final int CAPACITY = (1 << COUNT_BITS) - 1;

	// runState is stored in the high-order bits
	private static final int RUNNING = -1 << COUNT_BITS;
	private static final int SHUTDOWN = 0 << COUNT_BITS;
	private static final int STOP = 1 << COUNT_BITS;
	private static final int TIDYING = 2 << COUNT_BITS;
	private static final int TERMINATED = 3 << COUNT_BITS;
	private final static AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));

	private static int workerCountOf(int c) {
		return c & CAPACITY;
	}

	private static int ctlOf(int rs, int wc) {
		return rs | wc;
	}
	 private static int runStateOf(int c)     { return c & ~CAPACITY; }

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// Executors.callable(task)
		// Executor
		// ExecutorService
		// ScheduledExecutorService
		int nThreads = 10;

		ExecutorService executorService = Executors.newCachedThreadPool();// ThreadPoolExecutor
																			// &
																			// SynchronousQueue<Runnable>
		Future<?> f = executorService.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return null;
			}
		});
		System.out.println(f.get());
		executorService.shutdown();
		Executors.newScheduledThreadPool(nThreads);// ScheduledThreadPoolExecutor
													// & DelayedWorkQueue
		Executors.newFixedThreadPool(nThreads);// ThreadPoolExecutor &
												// LinkedBlockingQueue<Runnable>
		Executors.newSingleThreadExecutor();
		Executors.newWorkStealingPool();// ForkJoinPool
		System.out.println((RUNNING | 0) & CAPACITY);
		System.out.println(Integer.toBinaryString(RUNNING | 0));
		System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
		System.out.println(workerCountOf(ctl.get()));
		System.out.println("====================================");
		String runBin = Integer.toBinaryString(RUNNING);
		System.out.println(runBin + " Run len=" + runBin.length());
		String shutBin = Integer.toBinaryString(SHUTDOWN);
		System.out.println(shutBin + " shutBin len=" + shutBin.length());
		String stopBin = Integer.toBinaryString(STOP);
		System.out.println(stopBin + " stopBin len=" + stopBin.length());
		String TIDYINGBin = Integer.toBinaryString(TIDYING);
		System.out.println(TIDYINGBin + " tidy len=" + TIDYINGBin.length());
		String TERMINATEDBin = Integer.toBinaryString(TERMINATED);
		System.out.println(TERMINATEDBin + " TERMINATEDBin len=" + TERMINATEDBin.length());
		System.out.println(Integer.toBinaryString(CAPACITY));
		System.out.println(Integer.toBinaryString(~CAPACITY));
		System.out.println(Integer.toBinaryString(~CAPACITY&TIDYING));
		System.out.println(Integer.toBinaryString(ctl.get()));
		System.out.println(ctl.get());
		ctl.getAndDecrement();
		System.out.println((ctl.get()));
		System.out.println(TimeUnit.MILLISECONDS.toNanos(0));

	}
}
