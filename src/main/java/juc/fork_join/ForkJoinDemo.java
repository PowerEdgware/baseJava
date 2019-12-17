package juc.fork_join;

import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import juc.JDefaultThreadFactory;

public class ForkJoinDemo {

	void init() {
		// ForkJoinPool
		//ThreadPoolExecutor
	}

	public static void main(String[] args) throws InterruptedException {
		int nThreads = Runtime.getRuntime().availableProcessors();
		System.out.println(nThreads);
		ExecutorService es = Executors.newFixedThreadPool(1);

		UncaughtExceptionHandler eh = (Thread t, Throwable e) -> {
			System.err.println(t + " err occur,errtype=" + e);
			e.printStackTrace(System.err);
		};

		ThreadFactory threadFactory = new JDefaultThreadFactory(eh);

		es = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(128), threadFactory,
				new ThreadPoolExecutor.AbortPolicy());
		Thread t = new Thread(() -> {
			while (true) {
				int x = 0;
				System.out.println(Thread.currentThread().getUncaughtExceptionHandler() + " thread="
						+ Thread.currentThread().getName());
				LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2));
				if (x == 0) {
					throw new ThreadDeath();
				}
			}
		}, "ErrorThread");

		t.setUncaughtExceptionHandler(eh);
		t.start();
		// System.out.println(t.getThreadGroup());
		// System.out.println(t.getThreadGroup().getParent());
		System.out.println(t.getUncaughtExceptionHandler() + " isAlive=" + t.isAlive());
		t.join();
		System.out.println(t.getUncaughtExceptionHandler() + " isAlive=" + t.isAlive());
		es.submit(() -> {
			while (true) {
				int x = 0;
				System.out.println(Thread.currentThread().getUncaughtExceptionHandler() + " thread="
						+ Thread.currentThread().getName());
				LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2));
				if (x == 0) {
					throw new Error("err_occur");
				}
			}
		});

		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(5));

		es.submit(() -> {
			System.out.println("ssss____SSSS");
		});
		try {
			System.in.read();
			es.shutdown();

			while (!es.isTerminated()) {
				Thread.yield();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
