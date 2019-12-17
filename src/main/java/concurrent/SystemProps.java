package concurrent;

import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.function.BiConsumer;

public class SystemProps {

	public static void main(String[] args) {
		Properties p = System.getProperties();
		Enumeration<?> em = p.propertyNames();
		while (em.hasMoreElements()) {
			Object key = em.nextElement();
			// ByteBuffer
			System.out.println(key + "==>" + p.get(key));
		}
		System.out.println("=====================");
		Map<String, String> envs = System.getenv();
		envs.forEach(new BiConsumer<String, String>() {
			@Override
			public void accept(String t, String u) {
				System.out.println(t.toLowerCase() + "==>" + u);
			}
		});

	}
}
