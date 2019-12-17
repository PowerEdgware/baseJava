package generic0;

import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;

//泛型类型的表达接口：Type
//Type 子类：
//TypeVariable 描述类型变量   如T extends Comparable < ? super T > 
//WildcardType 描述通配符
//ParameterizedType 参数化类型--描述泛型类或接口类型：eg  Comparable < ? super T >
//GenericArrayType 描述泛型数组 eg:T[]

public class GenericTypeDemo {

	public static void main(String[] args) {
		// 获得泛型类型变量
		System.out.println("-----------Class-----------------");
		TypeVariable types[] = SimpleGenericClass.class.getTypeParameters();
		for (TypeVariable typeVariable : types) {
			System.out.println(typeVariable.getName());// 获得类型变量的名字 T
		}
		System.out.println("--------ParameterizedTypeClass-------");

		Type superType = ParameterizedTypeClass.class.getGenericSuperclass();
		if (superType instanceof ParameterizedType) {
			ParameterizedType pType = (ParameterizedType) superType;
			System.out.println(pType.getRawType());// class
													// generic0.SimpleGenericClasss
			System.out.println(Arrays.toString(pType.getActualTypeArguments()));// [class
																				// generic0.ParamInfo]
		}
		// 获取泛型接口
		Type[] ts = SimpleGenericClass.class.getGenericInterfaces();
		for (Type type : ts) {
			ParameterizedType pType = (ParameterizedType) type;
			System.out.println(type.getClass());// class
												// sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
			System.out.println(Arrays.toString(pType.getActualTypeArguments()));
			System.out.println(type.getTypeName());// generic0.simpleI<T>
		}
		String typeName = SimpleGenericClass.class.getTypeName();
		System.out.println(typeName);// generic0.SimpleGenericClass

		System.out.println("---------method----------------");

		// 获取方法的泛型类型变量
		Method[] methods = GenericMethodClass.class.getDeclaredMethods();

		Method gmethod = null;
		for (Method method : methods) {
			System.out.println("methodName=" + method.getName());
			Class<?>[] clazzes = method.getParameterTypes();// 方法参数
			for (Class<?> class1 : clazzes) {
				System.out.println(class1);
			}
			if (method.getName().equals("gmethod")) {
				gmethod = method;
			}
		}
		System.out.println("-------------------");
		System.out.println(gmethod.getReturnType());// class java.lang.Object
		Type type = gmethod.getGenericReturnType();// 获取泛型返回类型
		System.out.println(type.getTypeName());// T
		System.out.println("---------getTypeParameters-------------");
		TypeVariable<Method>[] typeVariables = gmethod.getTypeParameters();// 获得泛型类型变量
		for (TypeVariable<Method> typeVariable : typeVariables) {
			System.out.println(typeVariable);
		}
		System.out.println("---------getGenericParameterTypes-------------");
		Type[] types2 = gmethod.getGenericParameterTypes();// 获取泛型参数
		for (Type type2 : types2) {
			System.out.println(type2);
		}
		System.out.println("---------getParameters-------------");
		Parameter[] parameters = gmethod.getParameters();
		for (Parameter parameter : parameters) {
			System.out.println(parameter);
		}

		System.out.println("------------complaux test------------");
		TypeVariable<Class<ComplauxClass>>[] variables = ComplauxClass.class.getTypeParameters();
		for (TypeVariable<Class<ComplauxClass>> t : variables) {
			System.out.println(t);
			Type[] types3 = t.getBounds();
			for (Type type2 : types3) {
				System.out.println(type2);
				ParameterizedType typep = (ParameterizedType) type2;
				Type rawType = typep.getRawType();
				System.out.println(rawType + " " + typep);
				Type wildcardType = typep.getActualTypeArguments()[0];
				System.out.println(wildcardType.getClass());// class
															// sun.reflect.generics.reflectiveObjects.WildcardTypeImpl
				WildcardType wType = (WildcardType) wildcardType;
				Type lowType = wType.getLowerBounds()[0];
				System.out.println(lowType.getClass());// T TypeVariableImpls
			}
		}

		System.out.println("------------Test Method Generic---------");
		Method m = gmethod;

		printMethod(m);
	}

	private static void printMethod(Method m) {
		// public <T> T gmethod(String a, T... b)||public static <T extends
		// java.lang.Comparable> generic0.PairV2<T> minmax(T[])
		String name = m.getName();
		String desc = Modifier.toString(m.getModifiers());
		System.out.print(desc + "," + name);
	}
}

interface simpleI<E> {

}

// 简单泛型类
class SimpleGenericClass<T> implements simpleI<T> {

}

// 复杂的
class ComplauxClass<T extends Comparable<? super T>> {

}

// 泛型方法
class GenericMethodClass {
	public <T> T gmethod(String a, T... b) {
		return b[b.length / 2];
	}
}

class ParamInfo {

}

// 参数化的类型
class ParameterizedTypeClass extends SimpleGenericClass<ParamInfo> {

}
