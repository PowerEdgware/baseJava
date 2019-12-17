package juc.threadpool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class ThreadPool {

	static ExecutorService es = Executors.newFixedThreadPool(1);

	public static void main(String[] args) {

		es.execute(() -> {
			while (true) {
				System.out.println("err");
				throw new RuntimeException("error." + Thread.currentThread().getName());
			}
		});
		LockSupport.parkNanos(es, TimeUnit.SECONDS.toNanos(2));
		for (int i = 0;; i++) {
			final int out = i;
			es.execute(() -> {
				System.out.println(Thread.currentThread().getName() + " out=" + out);
				throw new RuntimeException("error." + Thread.currentThread().getName());
			});
			if (i > 500) {
				break;
			}
			LockSupport.parkNanos(es, TimeUnit.MILLISECONDS.toNanos(5));
		}
		// 再次执行
		es.execute(() -> {
			System.out.println("again " + Thread.currentThread().getName());
		});

		int c = -1;
		try {
			while ((c = System.in.read()) != 'q') {
				Thread.yield();
			}
			es.shutdown();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
