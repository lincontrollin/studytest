package com.lin.studytest;

import org.springframework.util.NumberUtils;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		org.apache.log4j.Logger logger2 = org.apache.log4j.Logger.getLogger(App.class);
		
		try{
			NumberUtils.parseNumber("121.344", Integer.class);
		}catch(Exception e){
			logger2.error("error", e);
		}
    }
}
