package dc_algorithm.hash;


public class HashApp {

	public static void main(String[] args) {
		//HashMap
		//Hashtable
		//HashSet
		//
		//ConcurrentHashMap
		int a=105;
		//	01111111-11111111-11111111-11111111
		//&	00000000-00000000-00000000-00010000
		System.out.println(a & 0x7FFFFFFF);
		//Integer
		System.out.println(Integer.MAX_VALUE);
		//System.out.println(12%0);//java.lang.ArithmeticException: / by zero
		
		String s1="aaa";
		String s2="aaa";
		System.out.println(s1==s2);
		
		String s3=new String("aaa");
		String s4=new String("aaa");
		System.out.println(s3==s4);
		
		System.out.println(8/(3*1.0)>2.5);
		
	}
}
