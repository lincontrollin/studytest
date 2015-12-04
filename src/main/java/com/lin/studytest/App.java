package com.lin.studytest;

import org.ehcache.CacheManager;
import org.ehcache.CacheManagerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.NumberUtils;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//		CacheManager cacheManager = CacheManagerBuilder.newCacheManager();
    	String url="hello";
    	long startTime = System.nanoTime();
    	url="888";
    	long time = System.nanoTime() - startTime;
    	System.out.println(time);
    }
}
