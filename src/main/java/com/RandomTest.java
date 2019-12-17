package com;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class RandomTest {

	public static void main(String[] args) {
		
		//Random
		//SecureRandom
		long seed=1<<20;
		Random rnd1=new Random(seed);
		Random rnd2=new Random(seed);
		for(int x=0;x<5;x++){
			int r1=rnd1.nextInt(48);
			int r2=rnd2.nextInt(48);
			System.out.println(r1+"\t"+r2);
		}
		
		Random number = new Random(123L);
		// ...
		for (int i = 0; i < 5; i++) {
		// Generate another random integer in the range [0, 20]
		int n = number.nextInt(21);
		System.out.println(n);
		}
		System.out.println("=============");
		try {
			SecureRandom snumber = SecureRandom.getInstance("SHA1PRNG");
			// Generate 20 integers 0..20
			for (int i = 0; i < 5; i++) {
			System.out.println(snumber.nextInt(21));
			}
			} catch (NoSuchAlgorithmException nsae) {
			// Forward to handler
			}
	}
}
