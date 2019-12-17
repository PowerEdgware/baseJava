package rpc.rim.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rpc.rim.api.IDemoService;

public class DemoServiceImpl extends UnicastRemoteObject implements IDemoService{

	public DemoServiceImpl() throws RemoteException {
		super();
	}
	public DemoServiceImpl(int port) throws RemoteException {
		super(port);
	}

	@Override
	public String sayHello(String msg) {
		return "Hello,"+msg;
	}

}
