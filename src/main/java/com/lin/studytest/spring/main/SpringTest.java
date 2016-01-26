package com.lin.studytest.spring.main;

import sun.misc.Unsafe;
import sun.reflect.Reflection;
import net.sf.cglib.beans.BeanGenerator;


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
//        FileSystemXmlApplicationContext applicationContext = 
//                new FileSystemXmlApplicationContext("C:/Users/Administrator/git/studytest/src/main/java/com/lin/studytest/spring/applications.xml");
//        Human chiness = (Human)applicationContext.getBean("proxyFactoryBean");
//        chiness.eat();
//        ProxyFactoryBean bean = new ProxyFactoryBean();
    	BeanGenerator beanGenerator = new BeanGenerator();
//    	beanGenerator.generateClass(v);
    	beanGenerator.addProperty("name", String.class);
    	beanGenerator.addProperty("sex", Integer.class);
    	Object obj = beanGenerator.create();
    	
    	System.out.println(obj);
    	SpringTest springTest = new SpringTest();
    	
    	A a = springTest.new A();
    	a.setName("linwanqi");
    	a.setSex(1);
    	System.out.println(a);
    	System.out.println(Reflection.getCallerClass());
    	
    	Unsafe unsafe = Unsafe.getUnsafe();
    	address = unsafe.objectFieldOffset(A.class.getDeclaredField("sex"));
    	unsafe.compareAndSwapInt(a, address, 1, 3);
    	System.out.println(a);
    	
    }
    
    

}
