package juc.lock;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.LockSupport;

public class LockDemo {

	static Object blocker = new Object();

	void readme() {
		// ReentrantLock
		// ReentrantReadWriteLock
		// LockSupport
		// Executors
		// Thread.yield();
		// FutureTask<V>
	}

	public static void main(String[] args) {
		final Object params = new Object();
		Callable<String> callable = () -> {
			LockSupport.parkNanos(blocker, TimeUnit.MILLISECONDS.convert(500, TimeUnit.NANOSECONDS));
			return doBiss(params);
		};
		FutureTask<String> futureTask = new FutureTask<>(callable);

		Thread workerThread = new Thread(futureTask);
		workerThread.start();
		try {
			String result = futureTask.get(10, TimeUnit.SECONDS);
			System.out.println("Execute result=" + result);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
	}

	private static String doBiss(Object params) {

		return "OK";
	}
}
