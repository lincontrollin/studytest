package com.lin.studytest.spring.main;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lin.studytest.spring.food.Food;
import com.lin.studytest.spring.human.Human;


public class SpringTest {
	class A {
		private String name;
		private int sex;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getSex() {
			return sex;
		}
		public void setSex(int sex) {
			this.sex = sex;
		}
		@Override
		public String toString() {
			return "A [name=" + name + ", sex=" + sex + "]";
		}
	}
	
	public static  long address;
    
    public static void main(String[] args) throws Exception{
        FileSystemXmlApplicationContext applicationContext = 
                new FileSystemXmlApplicationContext("G:/workspace/eclipse_web/livespace/studytest/src/main/java/com/lin/studytest/spring/applications.xml");
        Food chiness = (Food)applicationContext.getBean("proxyFactoryBean");
        System.out.println(chiness.getFoodName());
//    	System.out.println(Human.class.isAssignableFrom(Chiness.class));
    }
    
    

}
