package thread;

public class PerfectSeqPrint {

	public static void main(String[] args) {
		int maxNumber = 99;

		final Object lock1 = new Object();
		final Object lock2 = new Object();
		final Object lock3 = new Object();

		PerfectSeqThread t1 = new PerfectSeqThread("t1", maxNumber) {
			@Override
			public void run() {
				while (seqNumber < maxNumber) {
					synchronized (lock3) {
						synchronized (lock1) {
							if (seqNumber >= maxNumber) {
								lock1.notify();
								break;
							}
							seqNumber += 1;
							System.out.println(getName() + " seq:" + seqNumber);
							lock1.notify();
						}
						try {
							lock3.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};

		PerfectSeqThread t2 = new PerfectSeqThread("t2", maxNumber) {
			@Override
			public void run() {
				while(seqNumber<maxNumber)
				synchronized (lock1) {
					synchronized (lock2) {
						if(seqNumber>=maxNumber){
							lock2.notify();
							break;
						}
						seqNumber += 1;
						System.out.println(getName() + " seq:" + seqNumber);
						lock2.notify();
					}
					try {
						lock1.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		PerfectSeqThread t3 = new PerfectSeqThread("t3", maxNumber) {
			@Override
			public void run() {
				while (seqNumber < maxNumber) {
					synchronized (lock2) {
						try {
							lock2.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						synchronized (lock3) {
							if(seqNumber>=maxNumber){
								lock3.notify();
								break;
							}
							seqNumber += 1;
							System.out.println(getName() + " seq:" + seqNumber);
							lock3.notify();
						}
					}
				}
			}
		};

		t1.start();
		t2.start();
		t3.start();

	}
}

class PerfectSeqThread extends Thread {
	static int seqNumber = 0;
	private int maxNumber;
	private Object lock;

	public PerfectSeqThread(String name, int maxNumber) {
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
