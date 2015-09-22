package com.zebone.activemq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.zebone.mq.ISender;

/**
 * activeMq 发送数据到队列
 *
 * @author 杨英
 * @version 2014-6-6 下午04:10
 */
@Component
public class ActiveMqSender implements ISender {
	
    private static final Log log = LogFactory.getLog(ActiveMqSender.class);
    /**
     * qcu的名字，安装tlq的时候默认为qcu1
     */
    private String queueName;


    private String brokerURL;


  
    public String getQueueName() {
		return queueName;
	}


	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}


	public String getBrokerURL() {
		return brokerURL;
	}


	public void setBrokerURL(String brokerURL) {
		this.brokerURL = brokerURL;
	}


	public void send(String xml) {
        if (xml == null || xml.equals("")) {
            throw new RuntimeException("不能发送空信息！");
        }
        ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageProducer producer = null;
        boolean useTransaction = false;

        try {
            connectionFactory = new ActiveMQConnectionFactory(brokerURL);
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(useTransaction, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue(queueName);
            producer = session.createProducer(destination);
            TextMessage msg = session.createTextMessage();
            msg.setText(xml);
            producer.send(msg);
        } catch (JMSException e) {
            log.error("", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (producer != null) {
                    producer.close();
                }
                if (session != null) {
                    session.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
    
    }


}
