package jdk7orUpper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("A", "C", "B", "1", "-1");

		Collections.sort(list, (String a, String b) -> b.compareTo(a));
		System.out.println(list);

	}
}
