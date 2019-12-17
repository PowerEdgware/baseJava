package rpc.rim.api;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDemoService extends Remote{

	public String sayHello(String msg)throws RemoteException;
}
