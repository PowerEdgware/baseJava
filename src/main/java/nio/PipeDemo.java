package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class PipeDemo {

	public static void main(String[] args) {
		try {
			boolean block = false;
			PipeDemo pipeDemo = new PipeDemo();
			pipeDemo.startRead(block);
			LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
			pipeDemo.startWrite(block);

			System.in.read();
			pipeDemo.stopAll();
			System.out.println("server stopped");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	Pipe pipe = null;
	Thread writeThr;
	Thread readThr;

	public PipeDemo() throws IOException {
		pipe = Pipe.open();
	}

	public void startRead(boolean blocking) throws IOException {
		if (readThr == null) {
			readThr = new ReaderThread(pipe.source(), blocking);
			readThr.start();
		}
	}

	public void startWrite(boolean blocking) throws IOException {
		if (writeThr == null) {
			writeThr = new WriterThread(pipe.sink(), blocking);
			writeThr.start();
		}
	}

	public void stopAll() {
		((ReaderThread) readThr).close();
		((WriterThread) writeThr).close();
	}
}

class WriterThread extends Thread {
	private Pipe.SinkChannel writeChn;
	boolean stop;
	int count = 0;

	public WriterThread(Pipe.SinkChannel writeChn) throws IOException {
		this(writeChn, false);
	}

	public WriterThread(Pipe.SinkChannel writeChn, boolean blocking) throws IOException {
		this.writeChn = writeChn;
		this.writeChn.configureBlocking(blocking);
		setName("Writer-thr");
	}

	@Override
	public void run() {
		System.out.println(getName() + "\t" + "Started");
		while (!stop) {
			String newData = "data to pipe..." + System.currentTimeMillis() + " seq=" + count++ + "\t";
			ByteBuffer buf = ByteBuffer.allocate(newData.length());
			buf.clear();
			buf.put(newData.getBytes());
			buf.flip();
			try {
				while (buf.hasRemaining()) {
					writeChn.write(buf);
				}
				System.out.println(getName() + " Write Msg:" + newData);
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
		System.out.println(getName() + "\t" + "Stopped");
	}

	public void close() {
		stop = true;
		this.interrupt();
		if (writeChn != null) {
			try {
				writeChn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}

class ReaderThread extends Thread {
	static final Charset charset = Charset.forName("UTF-8");
	Pipe.SourceChannel sourceChannel;
	boolean stop;

	ReaderThread(Pipe.SourceChannel sourceChannel) throws IOException {
		this(sourceChannel, false);
	}

	ReaderThread(Pipe.SourceChannel sourceChannel, boolean boolking) throws IOException {
		this.sourceChannel = sourceChannel;
		sourceChannel.configureBlocking(boolking);
		setName("Reader-thr");
	}

	@Override
	public void run() {
		System.out.println(getName() + "\t" + "Started");
		while (!stop) {
			ByteBuffer buf = ByteBuffer.allocate(16);
			try {
				StringBuilder b = new StringBuilder();
				int len = sourceChannel.read(buf);
				while (len > 0) {
					buf.flip();// 切换成读模式
					byte[] bb = new byte[len];
					buf.get(bb);
					// buf.wrap(array)
					b.append(new String(bb));
					buf.clear();
					// b.append(charset.decode(buf));
					len = sourceChannel.read(buf);
				}
				if (b.length() > 0) {
					System.out.println(getName() + " Read Msg:" + b.toString());
				}
				TimeUnit.SECONDS.sleep(2);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
		System.out.println(getName() + "\t" + "Stopped");
	}

	public void close() {
		stop = true;
		this.interrupt();
		if (sourceChannel != null) {
			try {
				sourceChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
