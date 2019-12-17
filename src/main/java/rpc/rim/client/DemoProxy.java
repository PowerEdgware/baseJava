package rpc.rim.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import rpc.rim.api.IDemoService;

/**
 * 生成的DemoService 代理对象
 * @author yu
 *
 */
// public final class $Proxy0
public final class DemoProxy extends Proxy implements Remote, IDemoService {
	private static Method m1;
	private static Method m3;
	private static Method m2;
	private static Method m0;
	
	static {
		try {
			m1 = Class.forName("java.lang.Object").getMethod("equals",
					new Class[] { Class.forName("java.lang.Object") });
			m3 = Class.forName("rpc.rim.api.IDemoService").getMethod("sayHello",
					new Class[] { Class.forName("java.lang.String") });
			m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
			m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
		} catch (NoSuchMethodException localNoSuchMethodException) {
			throw new NoSuchMethodError(localNoSuchMethodException.getMessage());
		} catch (ClassNotFoundException localClassNotFoundException) {
			throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
		}
	}

	//TODO RemoteObjectInvocationHandler[UnicastRef [liveRef: [endpoint:[192.168.88.118:65531](remote),objID:[-3474fe02:167fee3be1e:-7fff, 102819881833472128]]]]
	public DemoProxy(InvocationHandler paramInvocationHandler) throws Error {
		super(paramInvocationHandler);
	}

	public final boolean equals(Object paramObject) throws Error {
		try {
			return ((Boolean) this.h.invoke(this, m1, new Object[] { paramObject })).booleanValue();
		} catch (Error | RuntimeException localError) {
			throw localError;
		} catch (Throwable localThrowable) {
			throw new UndeclaredThrowableException(localThrowable);
		}
	}

	public final String sayHello(String paramString) throws RemoteException {
		try {
			return (String) this.h.invoke(this, m3, new Object[] { paramString });
		} catch (Error | RuntimeException | RemoteException localError) {
			throw localError;
		} catch (Throwable localThrowable) {
			throw new UndeclaredThrowableException(localThrowable);
		}
	}

	public final String toString() throws Error {
		try {
			return (String) this.h.invoke(this, m2, null);
		} catch (Error | RuntimeException localError) {
			throw localError;
		} catch (Throwable localThrowable) {
			throw new UndeclaredThrowableException(localThrowable);
		}
	}

	public final int hashCode() throws Error {
		try {
			return ((Integer) this.h.invoke(this, m0, null)).intValue();
		} catch (Error | RuntimeException localError) {
			throw localError;
		} catch (Throwable localThrowable) {
			throw new UndeclaredThrowableException(localThrowable);
		}
	}
}
