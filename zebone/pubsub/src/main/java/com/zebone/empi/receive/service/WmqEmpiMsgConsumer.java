package com.zebone.empi.receive.service;

import java.nio.ByteBuffer;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//import com.ibm.mq.constants.CMQC;
//import com.ibm.mq.jmqi.JmqiEnvironment;
//import com.ibm.mq.jmqi.JmqiFactory;
//import com.ibm.mq.jmqi.JmqiMQ;
//import com.ibm.mq.jmqi.JmqiPropertyHandler;
//import com.ibm.mq.jmqi.JmqiThreadPoolFactory;
//import com.ibm.mq.jmqi.MQCNO;
//import com.ibm.mq.jmqi.MQGMO;
//import com.ibm.mq.jmqi.MQMD;
//import com.ibm.mq.jmqi.MQOD;
//import com.ibm.mq.jmqi.handles.Hconn;
//import com.ibm.mq.jmqi.handles.Hobj;
//import com.ibm.mq.jmqi.handles.Phconn;
//import com.ibm.mq.jmqi.handles.Phobj;
//import com.ibm.mq.jmqi.handles.Pint;
//import com.ibm.mq.jmqi.samples.SampleFramework;
//import com.ibm.mq.jmqi.samples.SamplePropertyHandler;
//import com.ibm.mq.jmqi.samples.SampleThreadPoolFactory;
//import com.ibm.mq.jmqi.samples.SampleTraceHandler;

/**
 * 主索引推送信息管理
 * 
 * @author 杨英
 * @version 2014-7-14 下午1:56
 */
@Component("wmqEmpiMsgConsumer")
public class WmqEmpiMsgConsumer 
//extends SampleFramework 
{

	private static final Log log = LogFactory.getLog(WmqEmpiMsgConsumer.class);

	@Resource
	private HandleEmpiMsgService empiMsgService;

	@Value("${receiveQueNameForEmpi}")
	private String queueName;

	@Value("${wmq.queuemanager}")
	private String queueManager;

	public void start() {
		getMessage();
	}

	public void getMessage() {
//		JmqiMQ mq = null;
//		Pint cc  = null;
//		Pint rc = null;
//		Phconn phconn = null;
//		try {
//			// ****************************************************************
//			// * Get the input parameters
//			// ****************************************************************
//			String[] para = new String[] { queueName, queueManager };
//			setOpenOptions(CMQC.MQOO_INPUT_AS_Q_DEF
//					| CMQC.MQOO_FAIL_IF_QUIESCING);
//			parseCommandLineArgs(para, 1, 4);
//			parseSystemProperties();
//
//			// ****************************************************************
//			// * Initialise the Jmqi
//			// ****************************************************************
//			SampleTraceHandler trace = new SampleTraceHandler();
//			JmqiThreadPoolFactory threadPool = new SampleThreadPoolFactory();
//			JmqiPropertyHandler propertyHandler = new SamplePropertyHandler();
//			JmqiEnvironment env = JmqiFactory.getInstance(trace, threadPool,
//					propertyHandler);
//
//			mq = getMQInstance(env);
//
//			cc = env.newPint(0);
//			rc = env.newPint(0);
//            
//			int size = 1024*1024;
//			byte[] buffer = new byte[size];
//			ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
//			int bufferLength = buffer.length;
//			Pint dataLength = env.newPint(0);
//
//			// ****************************************************************
//			// * Connect to queue manager
//			// ****************************************************************
//			phconn = env.newPhconn();
//			String qmname = getQueueManagerName();
//			MQCNO connectOptions = getConnectOptions(env);
//			mq.MQCONNX(qmname, connectOptions, phconn, cc, rc);
//			if (rc.x != CMQC.MQRC_NONE) {
//				throw new Exception("MQCONN ended with reason code " + rc.x);
//			}
//			Hconn hconn = phconn.getHconn();
//
//			// ****************************************************************
//			// * Open the named message queue for input; exclusive or shared
//			// * use of the queue is controlled by the queue definition here
//			// ****************************************************************
//			MQOD mqod = env.newMQOD();
//			mqod.setObjectName(getQueueName());
//			int options = getOpenOptions();
//			Phobj phobj = env.newPhobj();
//			mq.MQOPEN(hconn, mqod, options, phobj, cc, rc);
//			if (rc.x != CMQC.MQRC_NONE) {
//				throw new Exception("MQOPEN ended with reason code " + rc.x);
//			}
//			Hobj hobj = phobj.getHobj();
//
//			// ****************************************************************
//			// * Get messages from the message queue
//			// * Loop until there is a failure
//			// ****************************************************************
//			MQMD mqmd = env.newMQMD();
//			mqmd.setFormat(CMQC.MQFMT_STRING);
//			mqmd.setEncoding(546);
//			mqmd.setCodedCharSetId(1381);
//
//			MQGMO mqgmo = env.newMQGMO();
//			mqgmo.setVersion(CMQC.MQGMO_VERSION_2);
//			mqgmo.setMatchOptions(CMQC.MQMO_NONE);
//			mqgmo.setOptions(CMQC.MQGMO_WAIT | CMQC.MQGMO_CONVERT);
//			mqgmo.setWaitInterval(15000);
//
//			boolean more = true;
//			while (more) {
//				mq.MQGET(hconn, hobj, mqmd, mqgmo, bufferLength, byteBuffer,
//						dataLength, cc, rc);
//				switch (rc.x) {
//				case CMQC.MQRC_NONE:
//					byte[] message = byteBuffer.array();
//					String messageText = new String(message, 0, dataLength.x);
//					empiMsgService.handleEmpiMsg(messageText);
//					break;
//				case CMQC.MQRC_NO_MSG_AVAILABLE:
//					more = true;
//					break;
//				case CMQC.MQCA_NAMELIST_DESC:
//					more = false;
//					break;
//				default:
//					log.error("MQGET ended with reason code " + rc.x);
//				}
//			}
//
//			// ****************************************************************
//			// * Close the source queue (if it was opened)
//			// ****************************************************************
//			options = getCloseOptions();
//			mq.MQCLOSE(hconn, phobj, options, cc, rc);
//			if (rc.x != CMQC.MQRC_NONE) {
//				throw new Exception("MQCLOSE ended with reason code " + rc.x);
//			}
//		}catch(Exception e){
//			log.error(e.getMessage(),e);
//		}finally {
//			// ****************************************************************
//			// * Make sure to disconnect from the Queue Manager
//			// ****************************************************************
//			mq.MQDISC(phconn, cc, rc);
//			if (rc.x != CMQC.MQRC_NONE) {
//				log.error("MQDISC ended with reason code " + rc.x);
//			}
//			start();
//		}

	}
}
