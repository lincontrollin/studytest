//package com.lin.studytest;
//
//import org.freebencher.FbJobResult;
//import org.freebencher.FbTarget;
//import org.freebencher.Freebencher;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Unit test for simple App.
// */
//public class AppTest
//{
//
//    @Test
//    public void testApp()
//    {
//    	final List<String> userList = new ArrayList<String>();
//    	final Integer i = 0;
//    	FbJobResult result = Freebencher.benchmark(new FbTarget() {
//    		@Override
//    		public boolean invoke() {
//    			userList.add(i.toString());
//    			return true;
//    		}
//    	}, 10, // concurrency,
//    	1000 // number of tests to run
//    			);
//    	System.out.println(result.report());
//    }
//}
