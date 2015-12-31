package com.lin.studytest.spring.aop.aopjdk;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class MyAdvice implements MethodBeforeAdvice{
	
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		System.out.println("before method="+method.getName());
	}

}
