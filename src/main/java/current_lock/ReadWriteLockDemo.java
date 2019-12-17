package current_lock;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

	public static void main(String[] args) {
		CacheContainer container = new CacheContainer();
		AtomicBoolean flag = new AtomicBoolean(true);

		Thread read = new Thread(() -> {
			while (flag.get()) {
				container.readData("");
				System.out.println(Thread.currentThread().getName() + " get at:" + LocalDateTime.now());
				LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2));
			}
		}, "Read-Thread");
		read.start();

		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2));

		Thread write = new Thread(() -> {
			Random rnd = new Random();
			while (flag.get()) {
				//container.writeData(rnd.nextDouble() + "", rnd.toString());
				container.readData("");
				System.out.println(Thread.currentThread().getName() + " set at:" + LocalDateTime.now());
				LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2));
			}
		}, "Write-Thread");
		write.start();

		try {
			int c = System.in.read();
			while (c != 'q') {
				System.out.println("Input 'q' to Exit");
				c = System.in.read();
			}
			flag.set(false);
			read.interrupt();
			write.interrupt();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Server Stopped At:"+LocalDateTime.now());
	}
}

class CacheContainer {
	private Map<String, String> cacheMap;
	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	private ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
	private ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

	public CacheContainer() {
		this.cacheMap = new HashMap<>();
	}

	public String readData(String key) {
		checkNull(key);
		try {
			readLock.lock();
			return cacheMap.get(key);
		} finally {
			readLock.unlock();
		}
	}

	public void writeData(String key, String value) {
		checkNull(key);
		checkNull(value);
		try {
			writeLock.lock();
			cacheMap.put(key, value);
		} finally {
			writeLock.unlock();
		}
	}

	void checkNull(String in) {
		if (in == null)
			throw new NullPointerException();
	}
}
