package code;

import java.io.File;

public class NativeHello {

	public static native void hello();
	static{
		 //System.load("D:" + File.separator + "Hello.dll");
	}
	
	public static void main(String[] args) {
		//hello();
		
		//Object
		String paths=System.getProperty("java.library.path");
		for (String path : paths.split("[;]")) {
			System.out.println(path);
		}
	}
}
