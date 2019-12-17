package unsafe0;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class AllocateMemory {
	private static Unsafe unsafe;

	static {
		try {
			Field unsafeStaticField = Unsafe.class.getDeclaredField("theUnsafe");
			unsafeStaticField.setAccessible(true);
			unsafe = (Unsafe) unsafeStaticField.get(Unsafe.class);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	}

	static Unsafe getUnsafeInstance() {
		return unsafe;
	}
	
	
	public static void main(String[] args) {
//		int _1M=1024*1024;
//		while(true){
//			getUnsafeInstance().allocateMemory(_1M);
//		}
		SingleonObj inObj=SingleonObj.getInstance();
		inObj.do1();
		Unsafe unsafe=getUnsafeInstance();
		try {
			inObj=(SingleonObj) unsafe.allocateInstance(SingleonObj.class);
			inObj.do1();
			String attr=inObj.getAttr();
			System.out.println(attr);
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}
}

class SingleonObj{
	private static final SingleonObj instance=new SingleonObj();
	String attr="attr";
	private SingleonObj(){
		System.out.println(this+" \t"+attr);
	}
	public static SingleonObj getInstance(){
		return instance;
	}
	
	public void do1(){
		System.out.println("do1.instance="+this);
	}
	public String getAttr(){
		return this.attr;
	}
}
