package sockethttps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.lang3.StringUtils;


public class SockHttps {
	
	void readMe(){
//		SSLSocket
	}

	public static void main(String[] args){
		//
		String host="l.sh.189.cn";
		int port=443;
		InetAddress address;
		AtomicBoolean stop=new AtomicBoolean(false);
		Socket socket=new Socket();
		try {
			address = InetAddress.getByName(host);
			System.out.println(address);
			InetSocketAddress endpoint=new InetSocketAddress(address, port);
			socket.connect(endpoint);
			
			threadRead(socket,stop);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			while(Thread.activeCount()>1){
				try {
					System.out.println("Input q to Exit");
					socket.getOutputStream().write("Client Hello".getBytes());
					socket.getOutputStream().flush();
					int ch=System.in.read();
					if(ch=='q'){
						stop.set(true);
					}
					socket.getOutputStream().write(("dat_"+ch+"\r\n").getBytes());
					socket.getOutputStream().flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Server stopped");
		}
		
	}

	private static void threadRead(final Socket socket,AtomicBoolean stop) {
		if(socket==null){
			return;
		}
		new Thread("read-socket"){
			public void run() {
				try {
					System.out.println(getName()+" start");
					
					BufferedReader buf=new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
					while(!stop.get()){
						String data=buf.readLine();
						if(!StringUtils.isBlank(data)){
							System.out.println("Recv From Server.data="+data);
						}
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println(getName()+" stopped");
			};
		}.start();
	}
}
