//// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
//// Jad home page: http://www.geocities.com/kpdus/jad.html
//// Decompiler options: packimports(3) 
//
//package enum01;
//
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.reflect.*;
//
//public final class $Proxy0 extends Proxy
//    implements Retention
//{
//
//    public $Proxy0(InvocationHandler invocationhandler)
//    {
//        super(invocationhandler);
//    }
//
//    public final boolean equals(Object obj)
//    {
//        try
//        {
//            return ((Boolean)super.h.invoke(this, m1, new Object[] {
//                obj
//            })).booleanValue();
//        }
//        catch(Error _ex) { }
//        catch(Throwable throwable)
//        {
//            throw new UndeclaredThrowableException(throwable);
//        }
//    }
//
//    public final String toString()
//    {
//        try
//        {
//            return (String)super.h.invoke(this, m2, null);
//        }
//        catch(Error _ex) { }
//        catch(Throwable throwable)
//        {
//            throw new UndeclaredThrowableException(throwable);
//        }
//    }
//
//    public final Class annotationType()
//    {
//        try
//        {
//            return (Class)super.h.invoke(this, m4, null);
//        }
//        catch(Error _ex) { }
//        catch(Throwable throwable)
//        {
//            throw new UndeclaredThrowableException(throwable);
//        }
//    }
//
//    public final int hashCode()
//    {
//        try
//        {
//            return ((Integer)super.h.invoke(this, m0, null)).intValue();
//        }
//        catch(Error _ex) { }
//        catch(Throwable throwable)
//        {
//            throw new UndeclaredThrowableException(throwable);
//        }
//    }
//
//    public final RetentionPolicy value()
//    {
//        try
//        {
//            return (RetentionPolicy)super.h.invoke(this, m3, null);
//        }
//        catch(Error _ex) { }
//        catch(Throwable throwable)
//        {
//            throw new UndeclaredThrowableException(throwable);
//        }
//    }
//
//    private static Method m1;
//    private static Method m2;
//    private static Method m4;
//    private static Method m0;
//    private static Method m3;
//
//    static 
//    {
//        try
//        {
//            m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[] {
//                Class.forName("java.lang.Object")
//            });
//            m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
//            m4 = Class.forName("java.lang.annotation.Retention").getMethod("annotationType", new Class[0]);
//            m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
//            m3 = Class.forName("java.lang.annotation.Retention").getMethod("value", new Class[0]);
//        }
//        catch(NoSuchMethodException nosuchmethodexception)
//        {
//            throw new NoSuchMethodError(nosuchmethodexception.getMessage());
//        }
//        catch(ClassNotFoundException classnotfoundexception)
//        {
//            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
//        }
//    }
//}
