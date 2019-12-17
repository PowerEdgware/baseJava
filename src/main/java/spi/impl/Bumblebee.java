package spi.impl;

import spi.Robot;

public class Bumblebee implements Robot {

	@Override
	public void sayHello(String msg) {
		System.out.println("I am Bumblebee\t"+msg);
	}

}
