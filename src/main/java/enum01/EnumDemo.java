package enum01;
//
//import java.lang.annotation.Annotation;
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//import java.lang.reflect.Constructor;
//
//public class EnumDemo {
//
//	@MyTest("abc")
//	public EnumDemo() {
//
//	}
//
//	@SuppressWarnings("rawtypes")
//	public static void main(String[] args) throws Exception {
//		//保存生成的代理对象字节码
//		System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
//		Color[] cs = Color.values();
//		for (Color color : cs) {
//			System.out.println(color.toString());
//		}
//		// -----------------
//
//		// Annotation sun.reflect.annotation.AnnotationInvocationHandler
//		Constructor[] constructors = EnumDemo.class.getDeclaredConstructors();
//		for (Constructor c : constructors) {
//			if (c.isAnnotationPresent(MyTest.class)) {
//				Annotation[] annotations = c.getAnnotations();
//				for (Annotation annotation : annotations) {
//					if (annotation instanceof MyTest) {
//						MyTest t = (MyTest) annotation;
//						System.out.println("myTest annotation." + t.value());
//					} else {
//						System.err.println(annotation.equals(annotation));
//						System.out.println(annotation.annotationType().getName() + "\t" + annotation.getClass());
//					}
//				}
//			}
//		}
//		// Override
//
//	}
//
//}
//

//
//@Target(value = ElementType.CONSTRUCTOR)
//@Retention(RetentionPolicy.RUNTIME)
//@interface MyTest {
//	String value() default "";
//
//	String name() default "all";
//}
//
//@Target(value = ElementType.CONSTRUCTOR)
//@Retention(RetentionPolicy.RUNTIME)
//@interface MyTest2 {
//	String value() default "";
//}
