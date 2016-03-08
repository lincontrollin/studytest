package com.lin.studytest.jms.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MQConsumers {
	
	ConnectionFactory factory;
	Connection connection ;
	Session session ;
	
	
	
	public MQConsumers init()throws Exception{
		this.factory = new ActiveMQConnectionFactory(MQConfigurations.URI);
		connection = factory.createConnection();
		connection.start();
		session = connection.createSession(Boolean.TRUE,
                Session.AUTO_ACKNOWLEDGE);
		return this;
	}
	
	public void run()throws Exception{
		try{
			Queue destination = session.createQueue(MQConfigurations.QUEUE_NAME);
			
			MessageConsumer consumer = session.createConsumer(destination);
			
			while (true) {
                // 接收数据的时间（等待） 100 s
                Message message = consumer.receive(1000);
                
                TextMessage text = (TextMessage) message;
                if (text != null) {
                    System.out.println("接收：" + text.getText());
                } else {
                    break;
                }
            }
			session.commit();//将操作提交到服务器，这样子，就可以改变了
		}finally{
			session.close();
			connection.close();
			
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		new MQConsumers().init().run();
	}

}
