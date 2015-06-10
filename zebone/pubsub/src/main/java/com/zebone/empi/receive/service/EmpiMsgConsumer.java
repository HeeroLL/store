package com.zebone.empi.receive.service;

import com.zebone.mq.activemq.GenActiveMqConPool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * 主索引推送信息管理
 * @author 杨英
 * @version 2014-7-14 下午1:56
 */
@Component("empiMsgConsumer")
public class EmpiMsgConsumer extends Thread implements MessageListener {

    private static final Log log = LogFactory.getLog(EmpiMsgConsumer.class);

   /* @Resource
    private GenActiveMqConPool genActiveMqConPool;
*/
    @Resource
    private HandleEmpiMsgService empiMsgService;

    private boolean useTransaction = false;

    @Value("${receiveQueNameForEmpi}")
    private String queueName;

  /*  public GenActiveMqConPool getGenActiveMqConPool() {
        return genActiveMqConPool;
    }

    public void setGenActiveMqConPool(GenActiveMqConPool genActiveMqConPool) {
        this.genActiveMqConPool = genActiveMqConPool;
    }*/

    public HandleEmpiMsgService getEmpiMsgService() {
        return empiMsgService;
    }

    public void setEmpiMsgService(HandleEmpiMsgService empiMsgService) {
        this.empiMsgService = empiMsgService;
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
            empiMsgService.handleEmpiMsg(text);
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
