package com.lin.studytest.spring.aop;

import net.sf.cglib.beans.BeanMap;
import net.sf.cglib.core.DebuggingClassWriter;

import org.springframework.aop.framework.ProxyFactoryBean;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;


public class HumanAspect{
	static class bean{
		public int num=1;

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}
		
	}
    
    public static void main(String[] args) {
//        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
////        proxyFactoryBean.setProxyInterfaces("");
//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "C:\\Users\\Administrator\\Desktop\\cglib-beanmap");  
//        BeanMap map = BeanMap.create(new bean());
//        
//        bean b = new bean();
//        b.num=1;
//        map.setBean(b);
//        System.out.println(map.get("num"));
//        map.keySet();
//        
        Jedis jedis = new Jedis("116.31.122.23", 6379);
        JedisPubSub jedisPubSub =  new JedisPubSub() {
        	@Override
        	public void onMessage(String channel, String message) {
        		if("lin".equals(channel)){
        			System.out.println(" i get lin message:"+message);
        		}
        	}
		};
        jedis.subscribe(jedisPubSub, "lin");
        
        
        
    }

}
