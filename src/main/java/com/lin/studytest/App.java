package com.lin.studytest;

import com.lin.studytest.spring.human.Chiness;


public class App 
{
	public static volatile int a = 1;
    public static void main( String[] args )
    {
    	Chiness man = new Chiness();
    	a++ ;
    	Thread.interrupted();
    }
}
