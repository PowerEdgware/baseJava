package string;

public class StringAndIntern {

	public static void main(String[] args) {
		//StringBuilder
		String a=new String("a");
		a.intern();
		String pureA="a";
		System.out.println(a==pureA);//false
		
		String ab=new String(pureA)+new String("b");
		ab.intern();
		String pureAB="ab";
		System.out.println(ab==pureAB);//true
		
	}
}
