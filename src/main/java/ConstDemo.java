import org.springframework.beans.BeanWrapperImpl;

public class ConstDemo {

	static final String ABC = "ABC";

	public static void main(String[] args) {

//		String s3 = "a" + "C";
		String s1 = new String("aC");
		s1.intern();
		String s2 = "aC";
		//Integer
		
		System.out.println(s1 == s2);
		// String s4="aC";
		// System.out.println(s4==s1);
		// System.out.println(s4==s2);
		// System.out.println(s1 == s2);
		// System.out.println(s2 == s3);

		// String _s1 = "ABC";
		// String _s2 = new String("ABC");
		// System.out.println(_s1 == _s2);
		// System.out.println(_s3 == _s2);
		String _s3 = new String("ABC");
		String __s3 = _s3.intern();
		System.out.println(__s3 == ABC);
		BeanWrapperImpl wrapperImpl=new BeanWrapperImpl(ABean.class);
		wrapperImpl.setPropertyValue("a", "100");
		System.out.println(wrapperImpl.getWrappedInstance());
	}
}

class ABean{
	String a;
	String b;
	public ABean(){
		System.out.println(this);
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	@Override
	public String toString() {
		return "ABean [a=" + a + ", b=" + b + "]";
	}
	
}
