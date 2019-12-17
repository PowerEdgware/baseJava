
public class StringDemo {

	static String A="A";
	public static void main(String[] args) {
		String s1="a";
		String s2="b";
		String s3=s1+s2;//TODO 常量池只有 a 和 b  没有 ab
	//TODO	String s4="aaa"+"bbb";//只有s4存在的情况下，常量池只有拼接后的aaabbb,而没有aaa或bbb。就是说字面量的拼接最终常量池保存的是拼接结果。
	}
}
