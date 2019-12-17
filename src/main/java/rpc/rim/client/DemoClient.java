package rpc.rim.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import rpc.rim.api.IDemoService;

public class DemoClient {
	public static void main(String[] args) {
		try {
			System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
			IDemoService demoService=(IDemoService) Naming.lookup("rmi://127.0.0.1:2033/demoService");
			String result=demoService.sayHello("allger");
			System.out.println(result);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
}
