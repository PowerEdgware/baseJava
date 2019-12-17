package refrect;

import java.util.Random;

public class Man {
	private String name = "All";

	public int work() {
		Random rnd = new Random();
		int steps = rnd.nextInt(100000);
		//System.out.println("Man work " + steps);
		return steps;
	}

	public void speak(String sm) {
	//	System.out.println(name + " speaks:" + sm);
	}
}
