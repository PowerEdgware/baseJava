package baseJava;

import java.util.HashMap;
import java.util.Map;

import net.unitedcloud.chang.util.http.HTTPUtil;

public class CloudBusrefreshTest {

	public static void main(String[] args) {
		String url="http://localhost:8886/actuator/bus-refresh";
		Map<String, String> headMap=new HashMap<>();
		headMap.put("Content-Type", "application/json;charset=ISO-8859-1");
		Map rst=HTTPUtil.httpPostWithResponseMap(url, "", headMap, null);
		System.out.println("rst="+rst);
	}
}
