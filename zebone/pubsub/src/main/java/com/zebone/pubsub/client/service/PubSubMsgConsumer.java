package com.zebone.pubsub.client.service;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import com.zebone.mq.activemq.GenActiveMqConPool;

@Component("pubSubMsgConsumer")
public class PubSubMsgConsumer extends Thread implements MessageListener {

	private static final Log log = LogFactory.getLog(PubSubMsgConsumer.class);
    
	@Resource
	private GenActiveMqConPool genActiveMqConPool;
	
	@Resource
	private HandleDataService handleDataService;

	private boolean useTransaction = false;
    
	@Value("${receiveQueueName}")
	private String queueName;
	
	

	public GenActiveMqConPool getGenActiveMqConPool() {
		return genActiveMqConPool;
	}

	public void setGenActiveMqConPool(GenActiveMqConPool genActiveMqConPool) {
		this.genActiveMqConPool = genActiveMqConPool;
	}

	public HandleDataService getHandleDataService() {
		return handleDataService;
	}

	public void setHandleDataService(HandleDataService handleDataService) {
		this.handleDataService = handleDataService;
	}

	public boolean isUseTransaction() {
		return useTransaction;
	}

	public void setUseTransaction(boolean useTransaction) {
		this.useTransaction = useTransaction;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}


	@Override
	public void onMessage(Message msg) {
		// TODO Auto-generated method stub
		try {
			TextMessage textMessage = (TextMessage) msg;
			String text = textMessage.getText();
			handleDataService.handleData(text);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
	}

	public void run() {
		// TODO Auto-generated method stub
		Session session = null;
		MessageConsumer consumer = null;
		try {
			Connection con = genActiveMqConPool.getCon();
			session = con.createSession(useTransaction,
					Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(queueName);
			consumer = session.createConsumer(destination);
			consumer.setMessageListener(this);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
	}

}
