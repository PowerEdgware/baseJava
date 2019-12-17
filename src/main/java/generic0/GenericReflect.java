package generic0;

import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;

public class GenericReflect implements Serializable {

	/**
	 * @Fields field:field:{todo}(用一句话描述这个变量表示什么)
	 */

	private static final long serialVersionUID = 1L;

	// GenericReflect.class.getClasses()
	public class Demo {

	}

	// getDeclaredClasses()
	class Demo1 {

	}

	////////////////////////////////////////////////
	static void printClass(Class<?> cl) {
		System.out.print(cl);
		printTypes(cl.getTypeParameters(), "<", ", ", ">", true);

		Type sc = cl.getGenericSuperclass();
		if (sc != null) {
			System.out.print(" extends ");
			printType(sc, false);
		}
		printTypes(cl.getGenericInterfaces(), " implements ", ", ", "", false);
		System.out.println();
	}

	static void printMethod(Method m) {
		String name = m.getName();
		System.out.print(Modifier.toString(m.getModifiers()));
		System.out.print(" ");
		printTypes(m.getTypeParameters(), "<", ", ", "> ", true);

		printType(m.getGenericReturnType(), false);
		System.out.print(" ");
		System.out.print(name);
		System.out.print("(");
		printTypes(m.getGenericParameterTypes(), "", ", ", "", false);
		System.out.print(")");
		System.out.println();
	}

	static void printTypes(Type[] typeParameters, String pre, String sep, String suf, boolean isDefinition) {
		if (pre.equals(" extends ") && Arrays.equals(typeParameters, new Type[] { Object.class }))
			return;
		if (typeParameters.length > 0)
			System.out.print(pre);
		for (int i = 0; i < typeParameters.length; i++) {
			if (i > 0)
				System.out.print(sep);
			printType(typeParameters[i], isDefinition);
		}
		if (typeParameters.length > 0)
			System.out.print(suf);
	}

	static void printType(Type type, boolean isDefinition) {
		if (type instanceof Class) {
			System.out.print(((Class) type).getName());
		} else if (type instanceof TypeVariable) {
			TypeVariable<?> t = (TypeVariable) type;
			System.out.print(t.getName());
			if (isDefinition)
				printTypes(t.getBounds(), " extends ", " & ", "", false);
		} else if (type instanceof WildcardType) {
			WildcardType t = (WildcardType) type;
			System.out.print(" ? ");
			printTypes(t.getUpperBounds(), " extends ", " & ", "", false);
			printTypes(t.getLowerBounds(), " super ", " & ", "", false);
		} else if (type instanceof ParameterizedType) {
			ParameterizedType t = (ParameterizedType) type;
			Type owner = t.getOwnerType();
			if (owner != null) {
				printType(owner, false);
				System.out.print(".");
			}
			printType(t.getRawType(), false);
			printTypes(t.getActualTypeArguments(), "<", ", ", ">", false);
		} else if (type instanceof GenericArrayType) {
			GenericArrayType t = (GenericArrayType) type;
			System.out.print("");
			printType(t.getGenericComponentType(), isDefinition);
			System.out.print("[]");
		}
	}

	public static void main(String[] args) {
		// Class
		// Constructor<T>
		// Object
		System.out.println(Arrays.toString(GenericReflect.class.getClasses()));// [class
																				// generic0.GenericReflect$Demo]
		System.out.println(Arrays.toString(PairV2.class.getGenericInterfaces()));// [generic0.B<T>]
		System.out.println(Arrays.toString(PairV2.class.getInterfaces()));// [interface
																			// generic0.B]
		System.out.println(Arrays.toString(PairV2.class.getTypeParameters()));// [T]
		System.out.println(Arrays.toString(PairV2.class.getInterfaces()[0].getGenericInterfaces()));// [generic0.A<T,
																									// java.lang.Long>]
		ParameterizedType apType = (ParameterizedType) PairV2.class.getInterfaces()[0].getGenericInterfaces()[0];//
		System.out.println(apType);// generic0.A<T, java.lang.Long>
		System.out.println(apType.getRawType());// interface generic0.A
		System.out.println(Arrays.toString(apType.getActualTypeArguments()));// [T,
																				// class
																				// java.lang.Long]
		// TODO 把T 换成具体类型
		TestPair spair = new TestPair();
		apType = (ParameterizedType) spair.getClass().getGenericInterfaces()[0];
		System.out.println(apType.getActualTypeArguments()[0].toString());// class
																			// java.lang.String
		System.out.println(apType.getActualTypeArguments()[1].getTypeName());// java.lang.Long
		System.out.println("---------------------------");

		System.out.println("----PairV2.class.getTypeParameters()-------");
		TypeVariable<Class<PairV2>>[] typeVariables = PairV2.class.getTypeParameters();
		for (TypeVariable<Class<PairV2>> typeVariable : typeVariables) {
			System.out.println(typeVariable.getName() + "," + Arrays.toString(typeVariable.getBounds()));
		}

		System.out.println("---------end---------------");
		ParameterizedType ptype = (ParameterizedType) PairV2.class.getGenericSuperclass();// generic0.Pair<T>
		System.out.println(ptype);

		String name = PairV2.class.getName();

		name = ArrayAlg.class.getName();
		try {
			Class<?> cl = Class.forName(name);
			printClass(cl);
			for (Method m : cl.getDeclaredMethods()) {
				printMethod(m);
				System.out.println(Arrays.toString(m.getParameterTypes()));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

//class Pair0<T> {
//	T first;
//	T second;
//}

class TestPair extends Pair<String> implements A<String, Long> {

}

interface A<E, T> {

}

interface B<T> extends A<T, Long> {

}

class PairV2<T> extends Pair<T> implements B<T> {
	T first;
	T second;

	public PairV2(T first, T second) {
		this.first = first;
		this.second = second;
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

	public void setSecond(T second) {
		this.second = second;
	}
}

class ArrayAlg {

	public static <T extends Comparable> PairV2<T> minmax(T[] a) {
		T min = a[0];
		T max = a[0];
		for (T t : a) {
			if (min.compareTo(t) > 0)
				min = t;
			if (max.compareTo(t) < 0)
				max = t;
		}
		return new PairV2<>(min, max);
	}

}
