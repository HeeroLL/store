package com.zebone.empi.receive.service;

import com.zebone.mq.activemq.GenActiveMqConPool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * 文档订阅信息接收管理
 * @author 杨英
 * @version 2014-8-13 上午8:32
 */
@Component("docSubConsumer")
public class DocSubConsumer extends Thread implements MessageListener {
    private static final Log log = LogFactory.getLog(DocSubConsumer.class);

  /*  @Resource
    private GenActiveMqConPool genActiveMqConPool;*/

    @Resource
    private HandleSubMsgService subMsgService;

    private boolean useTransaction = false;

    @Value("${receiveQueNameForDocSub}")
    private String queueName;

/*    public GenActiveMqConPool getGenActiveMqConPool() {
        return genActiveMqConPool;
    }

    public void setGenActiveMqConPool(GenActiveMqConPool genActiveMqConPool) {
        this.genActiveMqConPool = genActiveMqConPool;
    }*/

    public HandleSubMsgService getSubMsgService() {
        return subMsgService;
    }

    public void setSubMsgService(HandleSubMsgService subMsgService) {
        this.subMsgService = subMsgService;
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
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            subMsgService.handleSubMsg(text);
        } catch (JMSException e) {
            e.printStackTrace();
            log.error(e.getMessage(),e);
        }
    }

    public void run() {/*
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
            e.printStackTrace();
            log.error(e.getMessage(), e);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
    */}
}
