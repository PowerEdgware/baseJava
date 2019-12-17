package dc_algorithm.recursion;

public class TriangleApp {

	public static void main(String[] args) {

		int enterNum = 5;

		int answer = triangle(enterNum);
		System.out.println("the answer=" + answer);
		/**
		 * Entering n=5 ------->triangle(5) Entering n=4 ------->triangle(4)
		 * Entering n=3 ------->triangle(3) Entering n=2 ------->triangle(2)
		 * Entering n=1 ------->triangle(1) Returing 1 returning 3 returning 6
		 * returning 10 returning 15 the answer=15
		 * 
		 */
		System.out.println("factorial="+factorial(enterNum));
	}

	// 相当于入栈出栈，在返回1之前，同一时刻有n个不同的triangle()方法实例存在
	static int triangle(int n) {
		System.out.println("Entering n=" + n);
		if (n == 1) {
			System.out.println("Returing 1");
			return 1;
		}
		int temp = n + triangle(n - 1);
		System.out.println("returning " + temp);
		return temp;
	}

	//其实这种情况循环的效率更高，而非递归
	static long factorial(int n) {
		if (n == 0) {
			return 1;
		}
		return n * factorial(n - 1);
	}
}
