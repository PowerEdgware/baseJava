import java.util.Properties;

import javax.management.MBeanServer;

public class PropTest {

	public static void main(String[] args) {
		Properties props=System.getProperties();
		props.forEach((k,v)->{
			System.out.println(k+"=="+v);
		});
		
//		MBeanServer
	}
}
