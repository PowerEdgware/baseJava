package sockethttps;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Iptest {

	public static void main(String[] args) throws UnknownHostException {
//		AccessController
//		SecurityManager
//		InetAddress
//		Inet4Address
//		Inet6Address
//		InetSocketAddress.createUnresolved(host, port)
		String host="l.sh.189.cn";
		InetAddress[] inetAddresses=InetAddress.getAllByName(host);
		for (InetAddress inetAddress : inetAddresses) {
			String ip=inetAddress.getHostAddress();
			byte[] ipbyte=inetAddress.getAddress();
			System.out.println(ip+","+Arrays.toString(ipbyte));
			System.out.println(byte2Text(inetAddress.getAddress()));
		}
	}
	static String byte2Text(byte[] ipByte){
		StringBuilder bf=new StringBuilder();
		for (byte b : ipByte) {
			bf.append(b & 0xff).append(".");
		}
		return bf.deleteCharAt(bf.length()-1).toString();
	}
}
