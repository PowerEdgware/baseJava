package generic0;

import java.awt.Component;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.function.Supplier;

import javax.swing.JSlider;

public class GenericTest {

	public static <T> T getMiddle(T... ts) {
		return ts[ts.length / 2];
	}

	// GenericClass must put into first
	public static <T extends GenericClass & Serializable & Comparable<T>> T min(T... a) {
		T smallest = a[0];
		for (T t : a) {
			if (smallest.compareTo(t) > 0) {
				smallest = t;
			}
		}
		return smallest;
	}

	public static void main(String[] args) throws Exception {
		// Type mismatch: cannot convert from Object&Serializable&Comparable<?>
		// to String
		// String aa=getMiddle("111",010,null);
		LocalDate aDate = LocalDate.now();
		DateInteral interal = new DateInteral();
		BasePair<LocalDate> pair = interal;
		// pair.setSecond(aDate);

		// TODO 利用反射查找桥接方法|分别调用父类/子类 setSecond(Object o)方法
		BasePair pair2 = new BasePair();
		Method[] methods = BasePair.class.getDeclaredMethods();
		for (Method method : methods) {
			System.out.println(method + "\t" + method.isBridge() + "\t" + method.isSynthetic());
			if ("setSecond".equals(method.getName()))
				method.invoke(pair2, aDate);
		}
		System.out.println("Sub Start");
		methods = pair.getClass().getDeclaredMethods();
		// public void generic0.DateInteral.setSecond(java.time.LocalDate) false
		// false
		// public void generic0.DateInteral.setSecond(java.lang.Object) true
		// true

		for (Method method : methods) {
			System.out.println(method + "\t" + method.isBridge() + "\t" + method.isSynthetic());
			if ("setSecond".equals(method.getName()))
				try {
					method.invoke(interal, "abc");
				} catch (Exception e) {
					e.printStackTrace();
				}
		}

		// TODO
		JSlider slider = new JSlider();
		Dictionary<Integer, Component> d = slider.getLabelTable();

		d = new Hashtable<>();
		slider.setLabelTable(d);

		BasePair<String> base[] = new BasePair[10];
		Object[] as = base;
		// java.lang.ArrayStoreException: java.lang.String
		try {
			as[0] = "abc";
		} catch (Exception e) {
			e.printStackTrace();
		}

		as[1] = new BasePair<GenericClass>();

		// Varargs warning
		Collection<Pair<String>> col = Collections.synchronizedList(new ArrayList<>());
		Pair<String> pair_1 = new Pair<>();
		Pair<String> pair_2 = new Pair<>();

		// Type safety: A generic array of Pair<String> is created for a varargs
		// parameter
		addAll(col, pair_1, pair_2);
		System.out.println(col);

		// makePair
		Pair<String> p = Pair.makePair((Supplier<String>)null);
		p=Pair.makePair(String.class);
		
		//ArrayList<E>

	}

	// Type safety: Potential heap pollution via varargs parameter ts
	@SafeVarargs
	public static <T> void addAll(Collection<T> col, T... ts) {
		for (T t : ts) {
			col.add(t);
		}
	}
}

abstract class GenericClass {

}

class BasePair<T> {
	T first;
	T second;

	public void setSecond(T s) {
		System.out.println("pair Set second." + s);
		this.second = s;
	}

	public T getFirst() {
		return first;
	}

	public void setFirst(T first) {
		this.first = first;
	}

	public T getSecond() {
		return second;
	}
}

class DateInteral extends BasePair<LocalDate> {

	@Override
	public void setSecond(LocalDate s) {
		System.out.println("set second." + s);
		if (s.compareTo(getFirst()) >= 0) {
			super.setSecond(s);
		}
	}

	// TODO 合成的桥接方法 虚拟机生成，避免类型擦除和多态发生冲突。debug
	// public void setSecond(Object s){
	// setSecond(s);
	// }
}

class Pair<T> {
	T first;
	T second;

	private Pair(T first, T second) {
		this.first = first;
		this.second = second;

	}

	public Pair() {
		// this.first=new T();Cannot instantiate the type T
	}

	public static <T> Pair<T> makePair(Supplier<T> cons) {
		return new Pair<>(cons.get(), cons.get());
	}

	public static <T> Pair<T> makePair(Class<T> clazz) {
		try {
			return new Pair<>(clazz.newInstance(), clazz.newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}

//The generic class Problem<T> may not subclass java.lang.Throwable
class Problem extends Exception{
	
	public static <T extends Throwable> void doWork(T t) throws T{
		try {
			//do someThing
		} catch (Exception e) {//Cannot use the type parameter T in a catch block
			t.initCause(e);
			throw t;
		}
	}
}
