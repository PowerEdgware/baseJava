package socket;

import java.util.Properties;

public class Test {

	public static void main(String[] args) {
		//System.out.println(LocalDateTime.now());
		//DualStackPlainSocketImpl
		String ss=System.getProperty("line.separator");
		System.out.println(ss.equals("\r\n"));//true
		char[] sysLineSpe=ss.toCharArray();
		System.out.println(sysLineSpe.length);
		for (char c : sysLineSpe) {
			System.out.println(c=='\r');
			System.out.println(c=='\n');
		}
		System.out.print("start"+"\r"+"sbc");
//		System.out.print('\r');
//		System.out.print('\n');
		System.out.print("end");
//		Properties props=System.getProperties();
//		props.forEach((k,v)->{
//			System.out.println(k+"->"+v);
//		});
//		System.out.println("==============List envs:===================");
//		System.getenv().forEach((k,v)->{
//			System.out.println(k+"->"+v);
//		});
	}
}
