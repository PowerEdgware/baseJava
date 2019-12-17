package set;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Test01 {

	public static void main(String[] args) {
		//LinkedHashSet
		Set<Demo> dset=new HashSet<>();
		Demo d1=new Demo();
		d1.demoName="demo1";
		d1.id=10;
		dset.add(d1);
		System.out.println(dset);
		d1.demoName="tomcat";
		System.out.println(dset.contains(d1));
		System.out.println(dset.add(d1));
		System.out.println(dset);
	}
}
class Demo{
	int id;
	String demoName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDemoName() {
		return demoName;
	}
	public void setDemoName(String demoName) {
		this.demoName = demoName;
	}
	@Override
	public String toString() {
		return "Demo [id=" + id + ", demoName=" + demoName + "]";
	}
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((demoName == null) ? 0 : demoName.hashCode());
//		result = prime * result + id;
//		return result;
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Demo other = (Demo) obj;
//		if (demoName == null) {
//			if (other.demoName != null)
//				return false;
//		} else if (!demoName.equals(other.demoName))
//			return false;
//		if (id != other.id)
//			return false;
//		return true;
//	}
}
