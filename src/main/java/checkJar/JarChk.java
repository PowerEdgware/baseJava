package checkJar;

public class JarChk {

	public static void main(String[] args) {
		double a=0.39;
		String x=Double.toHexString(a);
		System.out.println(x+"\t "+a);
		System.out.println(0.1+0.2);
		System.out.println(12.0f-11.9f);
		long ax=Double.doubleToLongBits(a);
		System.out.println(Long.toBinaryString(ax));
	}
}
