package com.zebone.dnode.engine.empi.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.zebone.dnode.engine.empi.dao.ResidentCardMapper;
import com.zebone.dnode.engine.empi.dao.ResidentInfoMapper;
import com.zebone.dnode.engine.empi.service.EmpiPushService;
import com.zebone.dnode.engine.empi.vo.PushInfo;
import com.zebone.dnode.engine.empi.vo.PushInfo.CardInfo;
import com.zebone.dnode.engine.empi.vo.PushInfo.PersonalInfo;
import com.zebone.dnode.engine.empi.vo.ResidentCard;
import com.zebone.dnode.engine.empi.vo.ResidentInfo;

/**
 * 主索引信息推送接口实现类
 *
 * @author 杨英
 * @version 2014-06-16 上午09:15
 */
@Service("empiPushService")
public class EmpiPushServiceImpl implements EmpiPushService {

    private static Logger log = Logger.getLogger(EmpiPushServiceImpl.class);

    @Resource
    ResidentInfoMapper residentInfoMapper;

    @Resource
    ResidentCardMapper residentCardMapper;

    //队列名称
    @Value("${queueName}")
    private String queueName;

    //机构编码列表
    @Value("${queueOrg}")
    private String queueOrg;

    @Value("${brokerURL}")
    private String brokerURL;
    
    @Value("${iswmq}")
	private String isWmq;
	
	@Value("${wmq.host}")
	private String wmqHost;
	
	@Value("${wmq.port}")
	private int wmqPort;
	
	@Value("${wmq.channel}")
	private String wmqChannel;
	
	@Value("${wmq.queuemanager}")
	private String wmqQueueManager;
	
	@Value("${wmq.transporttype}")
	private String wmqTransportType;


    @Override
    public void pushEmpiInfo() {
        XStream xs = new XStream(new DomDriver("UTF-8", new NoNameCoder()));
        boolean flag = true;
        List<ResidentInfo> residentInfoLic = residentInfoMapper.getPushInfo();
        Map<String, Object> residentInfoMap = new HashMap<String, Object>();
        if (residentInfoLic != null && residentInfoLic.size() > 0) {
            for (ResidentInfo residentInfo : residentInfoLic) {
                String empiNo = residentInfo.getEmpi();
                PushInfo pushInfo = new PushInfo();
                if (residentInfoMap.get(empiNo) == null) {
                    residentInfoMap.put(empiNo, residentInfo);
                    try {
                        //为pushInfo填充人员信息
                        PersonalInfo personalInfo = new PersonalInfo();
                        BeanUtils.copyProperties(personalInfo, residentInfo);
                        personalInfo.setBirthday(formatDate(residentInfo.getBirthday()));
                        pushInfo.setPersonalInfo(personalInfo);

                        //为pushInfo填充卡信息
                        List<CardInfo> cardInfoLic = new ArrayList<CardInfo>();
                        List<ResidentCard> residentCardLic = residentCardMapper.getResidentCardByEmpi(empiNo);
                        for (ResidentCard residentCard : residentCardLic) {
                            CardInfo cardInfo = new CardInfo();
                            BeanUtils.copyProperties(cardInfo, residentCard);
                            cardInfo.setCreateDate(formatDate(residentCard.getCreateDate()));
                            cardInfoLic.add(cardInfo);
                        }
                        pushInfo.getCard().setCardInfoLic(cardInfoLic);

                        xs.processAnnotations(PushInfo.class);
                        String pushXml = xs.toXML(pushInfo);
                        String[] pushOrgLic = getPushOrgList();
                        if (pushOrgLic != null && pushOrgLic.length > 0) {
                            for (String orgCode : pushOrgLic) {
                                String queue = queueName + "." + orgCode;
                                sendMessage(pushXml, queue);
                            }
                        }
                    } catch (Exception e) {
                        flag = false;
                        log.error(e.getMessage(), e);
                    }finally {
                        if(flag){
                            residentInfoMapper.updatePushStatus(empiNo);
                        }
                    }
                }
            }
        }
    }


    /**
     * 信息推送
     *
     * @param pushXml
     */
	public void sendMessage(String pushXml, String queue) {
		if (pushXml == null || pushXml.equals("")) {
			throw new RuntimeException("不能发送空信息！");
		}
		/*
		if ("1".equals(isWmq)) {
			MQQueueManager qmgr  = null;
			MQQueue mqueue = null;
			try{
				MQEnvironment.CCSID = 1381; // default 819
	    		MQEnvironment.channel = wmqChannel;
	    		MQEnvironment.hostname = wmqHost;
	    		MQEnvironment.port = wmqPort;
	    		MQEnvironment.properties.put(MQC.TRANSPORT_PROPERTY,MQC.TRANSPORT_MQSERIES);
	    		
				MQPutMessageOptions pmo = new MQPutMessageOptions();
				qmgr = new MQQueueManager(wmqQueueManager);
				mqueue = qmgr.accessQueue(queue, MQC.MQOO_OUTPUT
						+ MQC.MQOO_FAIL_IF_QUIESCING + MQC.MQOO_BIND_NOT_FIXED);

				MQMessage msg = new MQMessage();

				msg.persistence = MQC.MQPER_PERSISTENT;
				msg.messageId = MQC.MQMI_NONE;
				msg.correlationId = MQC.MQMI_NONE;
				msg.encoding = 546; // default 273
				msg.characterSet = 1381; // default 819
				msg.clearMessage();
				msg.writeString(pushXml);
				mqueue.put(msg, pmo);
			}catch(Exception e){
				throw new RuntimeException(e);
			}finally{
				try{
					mqueue.close();
					qmgr.disconnect();
				}catch(Exception e){
					log.error(e.getMessage(),e);
				}
			}
		} else {
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
				session = connection.createSession(useTransaction,
						Session.AUTO_ACKNOWLEDGE);
				destination = session.createQueue(queue);
				producer = session.createProducer(destination);
				TextMessage msg = session.createTextMessage();
				msg.setText(pushXml);
				producer.send(msg);
			} catch (Exception e) {
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
					log.error(e.getMessage(),e);
				}

			}
		}
		*/
	}

    

    private String formatDate(Date dt) {
        String strDt = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (dt != null) {
            strDt = sdf.format(dt);
        }
        return strDt;
    }

    private String[] getPushOrgList(){
        String[] pushOrgList = new String[0];
        if(queueOrg!=null && !"".equals(queueOrg)){
            pushOrgList = queueOrg.split(",");
        }
        return pushOrgList;
    }
}
