package com.lin.studytest.spring.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyInvocationHandler implements InvocationHandler {
    
    private Object target;
    
    public ProxyInvocationHandler(Object target) {
       this.target = target;
    }

    /* 
     * proxy 代理对象  method 执行方法  args 执行方法的形参
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before subject");
        Object ret = method.invoke(target, args);
        System.out.println("after subject");
        return ret;
    }
   
    public static void main(String[] args) throws Exception{
        Subject subject = new RealSubject();
        ProxyInvocationHandler handler = new ProxyInvocationHandler(subject);
        Subject proxy = (Subject)Proxy.newProxyInstance(handler.getClass().getClassLoader(), subject.getClass().getInterfaces(), handler);
        proxy.name();
//        ProxyGeneratorUtils.writeProxy(proxy.getClass().getName(), "C:\\Users\\Administrator\\Desktop\\"+proxy.getClass().getSimpleName()+".class");
    }

}
