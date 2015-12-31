package com.lin.studytest.spring.aop.cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class ProxyManager implements MethodInterceptor{
    
    String name;
    
    public ProxyManager(String name) {
        this.name = name;
    }
    public Object intercept(Object paramObject, Method paramMethod, Object[] paramArrayOfObject,
            MethodProxy paramMethodProxy) throws Throwable {
        if(!name.equals("lin")){
            System.out.println("no auth");
            return null;
        }
        paramMethod.invoke(paramObject, paramArrayOfObject);
        return paramMethodProxy.invokeSuper(paramObject, paramArrayOfObject);
    }
    
    
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new ProxyManager("lin2"));
        enhancer.setSuperclass(InfoManager.class);
        InfoManager infoManager = (InfoManager)enhancer.create();
        infoManager.query();
//        KeyFactory keyFactory 
    }
    

}
