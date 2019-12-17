package dc_algorithm.eliminate_recursion;

class Params {
	public int n;
	public int returnAddress;

	public Params(int n, int address) {
		this.n = n;
		this.returnAddress = address;
	}
}

class StackX {
	private int maxSize;
	private Params[] stackArray;
	private int top;

	public StackX(int size) {
		this.maxSize = size;
		stackArray = new Params[maxSize];
		this.top = -1;
	}

	public void push(Params params) {
		stackArray[++top] = params;
	}

	public Params pop() {
		return stackArray[top--];
	}

	public Params peek() {
		return stackArray[top];
	}
}

public class StackTriangelApp {
	static int number;
	static int answer;
	static StackX theStack;
	static int codePart;
	static Params theseParams;
	static StringBuilder builder = new StringBuilder();

	public static void main(String[] args) {
		number = 10;
		recTriangel();
		System.out.println("answer=" + answer);
	}

	static void recTriangel() {
		theStack = new StackX(1000);
		codePart = 1;
		while (step() == false) {
			System.out.println("continue...codePart=" + codePart);
		}
	}

	static boolean step() {
		switch (codePart) {
		case 1:
			theseParams = new Params(number, 6);
			theStack.push(theseParams);
			codePart = 2;
			break;
		case 2:
			theseParams = theStack.peek();
			if (theseParams.n == 1) {
				answer = 1;
				codePart = 5;
			} else
				codePart = 3;
			break;
		case 3:
			Params newParams = new Params(theseParams.n - 1, 4);
			theStack.push(newParams);
			codePart = 2;
			break;
		case 4:
			theseParams = theStack.peek();
			if (answer == 1)
				builder.append(answer + "+" + theseParams.n);
			else
				builder.append("+" + theseParams.n);
			System.out.println(builder);
			answer += theseParams.n;
			codePart = 5;
			break;
		case 5:
			theseParams = theStack.peek();
			codePart = theseParams.returnAddress;
			theStack.pop();
			break;
		case 6:
			return true;
		}
		return false;
	}
}
