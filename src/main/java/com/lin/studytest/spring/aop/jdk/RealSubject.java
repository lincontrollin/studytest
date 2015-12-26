package com.lin.studytest.spring.aop.jdk;

public class RealSubject implements Subject {
    public void name() {
       System.out.println("my name is subject!!!");
    }
}
