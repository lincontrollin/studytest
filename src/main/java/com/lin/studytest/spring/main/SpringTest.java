package com.lin.studytest.spring.main;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lin.studytest.spring.human.Chiness;

public class SpringTest {
    
    public static void main(String[] args) {
        FileSystemXmlApplicationContext applicationContext = 
                new FileSystemXmlApplicationContext("C:/Users/Administrator/git/studytest/src/main/java/com/lin/studytest/springapplications.xml");
        Chiness chiness = (Chiness)applicationContext.getBean("chiness");
        chiness.eat();
//        ProxyFactoryBean bean = new ProxyFactoryBean();
    }
    
    

}
