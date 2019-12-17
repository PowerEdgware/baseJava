package dc_algorithm.stack_queue;

/**
 * 什么是队列 队列是数据结构中比较重要的一种类型，它支持 FIFO，尾部添加、头部删除（先进队列的元素先出队列），跟我们生活中的排队类似。 队列有两种：
 * 单队列 循环队列 单队列就是常见的队列, 每次添加元素时，都是添加到队尾
 *
 */
// 实现循环队列
public class ArrayLoopQueue<E> {

	private int DEFAULT_SIZE = 10;
	private int capacity = DEFAULT_SIZE;

	private Object[] data;
	private int front;// 头部
	private int rear;// 尾部

	public ArrayLoopQueue() {
		this.data = new Object[capacity];
	}

	public ArrayLoopQueue(int initSize) {
		if (initSize <= 0 || initSize > Integer.MAX_VALUE)
			throw new IllegalArgumentException("init size:" + initSize);
		this.capacity = initSize;
		this.data = new Object[capacity];
	}

	public boolean insert(E e) {// 插入尾部
		if (e == null)
			throw new NullPointerException();
		if (isFull())
			return false;
		data[rear++] = e;
		rear = rear == capacity ? 0 : rear;
		return true;
	}

	public E remove() {// 移除头部
		if (isEmpty())
			throw new QueueOperateException("queue empty");
		E oldValue = (E) data[front];
		////释放队列的front端的元素  
		data[front++]=null;
		front = front == capacity ? 0 : front;
		return oldValue;
	}

	// 返回队列顶元素，但不删除队列顶元素
	public E element() {
		if (isEmpty()) {
			throw new QueueOperateException("空队列异常");
		}
		return (E) data[front];
	}

	boolean isFull() {
		return rear == front && data[rear] != null;
	}

	boolean isEmpty() {
		return front == rear && data[rear] == null;
	}

	public String toString() {
		if (isEmpty()) {
			return "[]";
		}
		StringBuffer b = new StringBuffer("[");
		if (front < rear) {// 如果front < rear，有效元素就是front到rear之间的元素
			for (int x = front;; x++) {
				if (x == rear - 1)
					return b.append(data[x] + "]").toString();
				b.append(data[x] + ",");
			}
		} else {
			// 数据段分为2部分
			// 1,front->capacity
			// 2,0->rear-1
			for (int x = front; x < capacity; x++)
				b.append(data[x] + ",");

			for (int y = 0;y<rear; y++) 
				b.append(data[y] + ",");
			int len = b.length();  
	        return b.delete(len - 1, len).append("]").toString();  
		}
	}

}

class QueueOperateException extends RuntimeException {

	public QueueOperateException() {
		super();
	}

	public QueueOperateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public QueueOperateException(String message, Throwable cause) {
		super(message, cause);
	}

	public QueueOperateException(String message) {
		super(message);
	}

	public QueueOperateException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
