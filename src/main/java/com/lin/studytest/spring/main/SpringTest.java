package com.lin.studytest.spring.main;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lin.studytest.spring.human.Chiness;
import com.lin.studytest.spring.human.Human;

public class SpringTest {
    
    public static void main(String[] args) {
        FileSystemXmlApplicationContext applicationContext = 
                new FileSystemXmlApplicationContext("C:/Users/Administrator/git/studytest/src/main/java/com/lin/studytest/spring/applications.xml");
        Human chiness = (Human)applicationContext.getBean("proxyFactoryBean");
        chiness.eat();
//        ProxyFactoryBean bean = new ProxyFactoryBean();
    }
    
    

}
