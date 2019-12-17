import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class TestGabageCollect {

	public static void main(String[] args) {
		List<GarbageCollectorMXBean> beans = ManagementFactory.getGarbageCollectorMXBeans();
		for (GarbageCollectorMXBean bean : beans) {
			System.out.println(bean.getName());
		}

	}
}
