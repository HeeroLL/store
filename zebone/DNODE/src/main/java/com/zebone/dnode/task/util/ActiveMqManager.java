package com.zebone.dnode.task.util;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * activeMq 操作
 *
 * @author 杨英
 * @version 2014-6-6 下午02:13
 */
public class ActiveMqManager{
	
	
	private static final Log logger = LogFactory.getLog(ActiveMqManager.class);
	
	private String brokerURL;
	
	

    public String getBrokerURL() {
		return brokerURL;
	}


	public void setBrokerURL(String brokerURL) {
		this.brokerURL = brokerURL;
	}


	public static Log getLogger() {
		return logger;
	}


	public ActiveMqManager(String brokerURL) {
		super();
		this.brokerURL = brokerURL;
	}


	/**
     * 获取消息
     *
     * @param qcu
     * @param queName
     * @return
     */
    public String getMessage(String queName) {

        ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageConsumer consumer = null;
        boolean useTransaction = false;
        String msgData = null;
        try {
            connectionFactory = new ActiveMQConnectionFactory(brokerURL);
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(useTransaction, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue(queName);
            consumer = session.createConsumer(destination);
            TextMessage message =  (TextMessage)consumer.receive(1000);
            if(message == null){
            	return null;
            }
            msgData = message.getText();
        } catch (JMSException e) {
        	logger.error(e.getMessage(),e);
        }finally{
        	try {
				session.close();
				connection.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(),e);
			}
        }
        return msgData;
    }
}
