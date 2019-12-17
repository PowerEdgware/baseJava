package spi.impl;

import spi.Robot;

public class OptimusPrime implements Robot {

	@Override
	public void sayHello(String msg) {
		System.out.println("I am Prime\t"+msg);
	}

}
