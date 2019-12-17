package jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NativeMemoryTest {

	 public static void main(String[] args) {
	        List<TestObject> cases = new ArrayList<TestObject>();
	        NativeMemoryTest heapOutOfMemory = new NativeMemoryTest();
	        int i = 0;
	        while (i <10) {
	            cases.add(heapOutOfMemory.new TestObject());
	            i++;
	        }
	        try {
	            TimeUnit.SECONDS.sleep(10);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    public class TestObject {
	        private double a = 34.53;
	        private Integer b = 9999999;
	    }
}
