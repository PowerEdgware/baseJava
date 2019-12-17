package dc_algorithm.simple_sort;

import java.util.Random;

public class ObjectSort {

	private Person[] arr;
	private int nElems;

	public ObjectSort(int max) {
		this.arr = new Person[max];
		nElems = 0;
	}
	
	public int size(){
		return nElems;
	}

	public void insert(String l, String f, int a) {
		arr[nElems++] = new Person(l, f, a);
	}

	public void display() {
		for (int k=0;k<nElems;k++) {
			System.out.println(arr[k].toString());
		}
	}

	public void insert_sort() {
		int in, out;
		Person target;
		for (out = 1; out < nElems; out++) {
			target = arr[out];
			in = out;
			while (in > 0 && arr[in - 1].getLastName().compareTo(target.getLastName()) > 0) {
				arr[in] = arr[in - 1];
				in--;
			}
			arr[in] = target;
		}
	}
	
	
	public static void main(String[] args) {
		int max=100;
		ObjectSort objectSort=new ObjectSort(max);
		
		for(int e=0;e<10;e++){
			int len=new Random().nextInt(10)+1;
			objectSort.insert(random_str(len), random_str(len), len+2*e);
		}
		System.out.println("Before sort:");
		objectSort.display();
		
		objectSort.insert_sort();
		System.out.println("After sort:");
		objectSort.display();
	}
	
	static String random_str(int len){
		String orig="abcdefghijklmnopqrstuvwxyz1234567890";
		StringBuilder build=new StringBuilder(len);
		Random rnd=new Random();
		char[] cs=orig.toCharArray();
		for(int i=0;i<len;i++){
			build.append(cs[rnd.nextInt(cs.length)]);
		}
		return build.toString();
	}
}

class Person {
	private String lastName;
	private String firstName;
	private int age;

	public Person(String l, String f, int a) {
		this.lastName = l;
		this.firstName = f;
		this.age = a;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [lastName=" + lastName + ", firstName=" + firstName + ", age=" + age + "]";
	}
}
