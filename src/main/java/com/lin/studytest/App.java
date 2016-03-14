package com.lin.studytest;

import org.apache.commons.codec.digest.Md5Crypt;

import com.lin.studytest.spring.human.Chiness;


public class App 
{
	public static volatile int a = 1;
    public static void main( String[] args )
    {
    	String password = "abc_123";
    	System.out.println(Md5Crypt.md5Crypt(password.getBytes()));
    }
}
