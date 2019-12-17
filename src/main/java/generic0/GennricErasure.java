package generic0;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.core.BridgeMethodResolver;

/*
 * 泛型擦chu   类型参数 （ type parameters )
 */
public class GennricErasure {

	public static void main(String[] args) {
		DateInterval DateInterval = new DateInterval();
		DateInterval.setFirst(LocalDate.now());
		PairE<LocalDate> pair = DateInterval;// OK assignment to superclass
		LocalDate aDate = LocalDate.now();
		DateInterval.setSecond(aDate);// debug调试 调用自己的LocalDate参数的方法
		pair.setSecond(aDate);// 调用子类的桥接方法，setSecond(Object date);目的是保持多态

//		getOrignalMethod(DateInterval.getClass());
		
		getMethodParameterType(DateInterval.getClass());
	}

	private static void getMethodParameterType(Class<? extends DateInterval> class1) {
		Method[]methods=class1.getDeclaredMethods();
		for (Method method : methods) {
			Type[]types=method.getGenericParameterTypes();
			for (Type type : types) {
				System.out.println(type.getTypeName());
			}
			
		}
	}

	static void getOrignalMethod(Class<?> clazz) {
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			Method m = BridgeMethodResolver.findBridgedMethod(method);
			if (!BridgeMethodResolver.isVisibilityBridgeMethodPair(m, method)) {
				System.out.println("orig method=" + m + " is orig=" + !m.isSynthetic());
				System.out.println("Bridge Method=" + method + " isBridge=" + method.isBridge());
			}
		}
	}

}

/**
 * 泛型擦除后反编译源码 class DateInterval extends PairE {
 * 
 * DateInterval() { }
 * 
 * public void setSecond(LocalDate second) {
 * if(second.compareTo((ChronoLocalDate)getFirst()) >= 0)
 * super.setSecond(second); }
 * 
 * public volatile void setSecond(Object obj)//合成后的桥接方法 {
 * setSecond((LocalDate)obj); } }
 * 
 * class PairE {
 * 
 * PairE() { }
 * 
 * public Object getFirst() { return first; }
 * 
 * public void setFirst(Object first) { this.first = first; }
 * 
 * public Object getSecond() { return second; }
 * 
 * public void setSecond(Object second) { this.second = second; }
 * 
 * Object first; Object second; //}
 * 
 *
 */

class DateInterval extends PairE<LocalDate> {

	public void setSecond(LocalDate second) {
		if (second.compareTo(getFirst()) >= 0) {
			super.setSecond(second);
		}
	}
}

class PairE<T> {
	T first;
	T second;

	public T getFirst() {
		return first;
	}

	public void setFirst(T first) {
		this.first = first;
	}

	public T getSecond() {
		return second;
	}

	public void setSecond(T second) {
		this.second = second;
	}

}
