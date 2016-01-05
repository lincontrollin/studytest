package com.lin.studytest.spring.aop.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class TraceAdapter implements MethodInterceptor{
    
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
            throws Throwable {
        System.out.println("my my !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return proxy.invokeSuper(obj, args);
    }

}
