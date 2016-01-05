package com.lin.studytest.spring.aop.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.CallbackFilter;

public class TraceFilter implements CallbackFilter{
    
    public int accept(Method method) {
       if(method.getName().contains("contains")){
           return 1;
       }
        return 0;
    }

}
