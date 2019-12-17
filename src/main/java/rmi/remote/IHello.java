package rmi.remote;

import java.rmi.Remote;


/* 
 *　在Java中，只要一个类extends了java.rmi.Remote接口，即可成为存在于服务器端的远程对象， 
 * 供客户端访问并提供一定的服务。JavaDoc描述：Remote 接口用于标识其方法可以从非本地虚拟机上 
 * 调用的接口。任何远程对象都必须直接或间接实现此接口。只有在“远程接口” 
 * （扩展 java.rmi.Remote 的接口）中指定的这些方法才可被远程调用。 
*/
public interface IHello extends Remote {

	/* extends了Remote接口的类或者其他接口中的方法若是声明抛出了RemoteException异常，
　　　　* 则表明该方法可被客户端远程访问调用。
	*/
	public String sayHello(String name) throws java.rmi.RemoteException;
}
