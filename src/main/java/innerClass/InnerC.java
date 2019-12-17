package innerClass;

public class InnerC {
	static boolean xxx;
	private Object[] items;

	// 保存有外部类的引用
	class Inner {
		private int cur;

		public boolean hasNext() {
			return this.cur < items.length;
		}

		public InnerC getOutInstance() {
			return InnerC.this;
		}

		class InnerInner{
			
		}
		// static class InnerStaticInner{
		// The member type InnerInner cannot be declared static; static types
		// can only be declared in static or top level types
		// }
	}

	static void changeXXX(boolean xxx) {
		InnerC.xxx = xxx;
	}

	// mei 有外部类的引用,所以无法访问 外部类非静态属性/方法/对象等
	static class StaticInner {
		static int static_cur = 10;
		private int cur = 10;

		public int getValue() {
			return cur;
		}

		public static void visitItems() {
			changeXXX(false);
		}
		
		class StaticInnerInner{
			
		}
	}
	
	public static void main(String[] args) {
		InnerC c=new InnerC();
		InnerC.Inner inner=c.new Inner();
		InnerC.StaticInner staticInner=new InnerC.StaticInner();
		System.out.println(inner.getClass());
		System.out.println(staticInner.getClass());
	}
}
