package spi;

import java.util.ServiceLoader;

public class JavaSPITest {

	public static void main(String[] args) {
		ServiceLoader<Robot> loader=ServiceLoader.load(Robot.class);
		System.out.println("JAVA SPI");
		loader.forEach((ele)->{
			ele.sayHello("abc");
		});
	}
}
