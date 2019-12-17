package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class ThreadSeqPrint {

	public static void main(String[] args) throws InterruptedException {
		// t1 t2 t3
		int maxNumber = 99;
		SeqThread t1 = new SeqThread("t1", maxNumber);
		SeqThread t2 = new SeqThread("t2", maxNumber);
		SeqThread t3 = new SeqThread("t3", maxNumber);
		t1.setLock(t3);
		t2.setLock(t1);
		t3.setLock(t2);
		
		t1.start();
		t1.join(10);
		t2.start();
		t2.join(10);
		t3.start();
		//获取锁流程：
		//t1锁获取t3,锁住t1，执行逻辑
		//t2 获取t1阻塞 
		//t3t2,获取t3阻塞
		//=========顺序执行==========
		//t1执行完打印，释放t1,通知等待t1的线程t2,wait等待t3，释放t3
		//t2获取 t1成功，获取t2，打印完毕，释放t2,wait等待，释放t1
		//t3 获取t2 ，获取t3 执行打印，通知wait在t3上的t1
	}
}

class SeqThread extends Thread {
	static int seqNumber = 0;
	private int maxNumber;
	private Object lock;

	public SeqThread(String name, int maxNumber) {
		super(name);
		this.maxNumber = maxNumber;
	}

	public void setLock(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		while (seqNumber < maxNumber) {
			synchronized (lock) {
				synchronized (this) {
					if (seqNumber >= maxNumber) {
						this.notify();
						break;
					}
					seqNumber += 1;
					System.out.println(getName() + " seq:" + seqNumber);
					this.notify();
				}

				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
