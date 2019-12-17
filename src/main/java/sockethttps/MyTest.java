package sockethttps;

import java.util.HashMap;
import java.util.Map;

import net.unitedcloud.chang.util.http.HTTPUtil;

public class MyTest {

	public static void main(String[] args) {
		String param="GIVE-1-15396582782231733";
		//222.68.185.231
		String url="https://l.sh.189.cn/illbeta/wxbuildingnotifyOrder?orderId="+param+"&errorCode=0001";
		Map<String, String> headerParams=new HashMap<>();
		headerParams.put("x-real-ip", "192.169.0.12");
		headerParams.put("ip", "192.169.0.12");
		
		String rst=HTTPUtil.httpGet(url, headerParams);
		System.out.println(rst);
	}
}
