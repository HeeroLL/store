package com.zebone.mq.activemq;


import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;


public class ActiveMqMsgUtil {

	public static void sendMsg(Connection con,String queueName,String msg) throws JMSException{
		Session session = con.createSession(false,Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue(queueName);
		MessageProducer producer = session.createProducer(destination);
		Message message = null;
        message = session.createTextMessage(msg);
		producer.send(message);
		producer.close();
		session.close();
	}
	
}
