package rpc.rim.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

import rpc.rim.api.IDemoService;
import rpc.rim.impl.DemoServiceImpl;

public class DemoServer {

	public static void main(String[] args) {
		try {
			int serverPort=2443;
			IDemoService demoService=new DemoServiceImpl(serverPort);
			Registry registry=LocateRegistry.createRegistry(2033);
			System.out.println(registry.getClass());
			String [] os=registry.list();
			System.out.println(Arrays.toString(os));
			//registry.rebind(name, obj);
			Naming.rebind("rmi://127.0.0.1:2033/demoService", demoService);
			os=registry.list();
			System.out.println(Arrays.toString(os));
			System.out.println("Service started");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
}
