package dc_algorithm.stack_queue;

import java.util.concurrent.TimeUnit;

public class TimeUnitTest {

	public static void main(String[] args) {
		System.out.println(TimeUnit.MILLISECONDS.convert(100000000, TimeUnit.NANOSECONDS));//把sourceDuration按照sourceUnit转成MILLISECONDS
	}
}
