package com.lin.studytest.jms.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MQPusher {
	
	ConnectionFactory factory;
	Connection connection ;
	Session session ;
	
	
	
	public MQPusher init()throws Exception{
		this.factory = new ActiveMQConnectionFactory(MQConfigurations.URI);
		connection = factory.createConnection();
		session = connection.createSession(Boolean.TRUE,
                Session.AUTO_ACKNOWLEDGE);
		return this;
	}
	
  public static void sendMessage(Session session, MessageProducer producer)
            throws Exception {
        for (int i = 1; i <= MQConfigurations.SEND_NUMBER; i++) {
            TextMessage message = session
                    .createTextMessage("ActiveMq 发送的消息" + i);
            // 发送消息到目的地方
            System.out.println("发送消息：" + "ActiveMq 发送的消息" + i);
            producer.send(message);
        }
    }
	
	
	public void run()throws Exception{
		try{
			Queue destination = session.createQueue(MQConfigurations.QUEUE_NAME);
			MessageProducer  producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			sendMessage(session, producer);
			session.commit();
		}finally{
			session.close();
			connection.close();
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		 new MQPusher().init().run();
	}
	

}
