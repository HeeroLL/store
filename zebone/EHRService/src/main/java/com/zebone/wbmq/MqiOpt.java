package com.zebone.wbmq;

import java.nio.ByteBuffer;


//import com.ibm.mq.jmqi.JmqiEnvironment;
//import com.ibm.mq.jmqi.JmqiFactory;
//import com.ibm.mq.jmqi.JmqiMQ;
//import com.ibm.mq.jmqi.JmqiPropertyHandler;
//import com.ibm.mq.jmqi.JmqiThreadPoolFactory;
//import com.ibm.mq.jmqi.MQCNO;
//import com.ibm.mq.jmqi.MQGMO;
//import com.ibm.mq.jmqi.MQMD;
//import com.ibm.mq.jmqi.MQOD;
//import com.ibm.mq.jmqi.MQPMO;
//import com.ibm.mq.jmqi.handles.Hconn;
//import com.ibm.mq.jmqi.handles.Hobj;
//import com.ibm.mq.jmqi.handles.Phconn;
//import com.ibm.mq.jmqi.handles.Phobj;
//import com.ibm.mq.jmqi.handles.Pint;
//import com.ibm.mq.jmqi.samples.SampleFramework;
//import com.ibm.mq.jmqi.samples.SamplePropertyHandler;
//import com.ibm.mq.jmqi.samples.SampleThreadPoolFactory;
//import com.ibm.mq.jmqi.samples.SampleTraceHandler;
import com.zebone.mq.ISender;

//public class MqiOpt extends SampleFramework implements ISender {
public class MqiOpt{
	
	private String queueManager;
	
	private String queue;

	public void getMsg() throws Exception {
		// ****************************************************************
		// * Get the input parameters
		// ****************************************************************
		/**
		String[] para = new String[]{queue,queueManager};
		setOpenOptions(CMQC.MQOO_INPUT_AS_Q_DEF | CMQC.MQOO_FAIL_IF_QUIESCING);
		parseCommandLineArgs(para, 1, 4);
		parseSystemProperties();

		// ****************************************************************
		// * Initialise the Jmqi
		// ****************************************************************
		SampleTraceHandler trace = new SampleTraceHandler();
		JmqiThreadPoolFactory threadPool = new SampleThreadPoolFactory();
		JmqiPropertyHandler propertyHandler = new SamplePropertyHandler();
		JmqiEnvironment env = JmqiFactory.getInstance(trace, threadPool,
				propertyHandler);

		JmqiMQ mq = getMQInstance(env);

		Pint cc = env.newPint(0);
		Pint rc = env.newPint(0);

		byte[] buffer = new byte[4096];
		ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
		int bufferLength = buffer.length;
		Pint dataLength = env.newPint(0);

		// ****************************************************************
		// * Connect to queue manager
		// ****************************************************************
		Phconn phconn = env.newPhconn();
		String qmname = getQueueManagerName();
		MQCNO connectOptions = getConnectOptions(env);
		mq.MQCONNX(qmname, connectOptions, phconn, cc, rc);
		if (rc.x != CMQC.MQRC_NONE) {
			throw new Exception("MQCONN ended with reason code " + rc.x);
		}
		Hconn hconn = phconn.getHconn();

		try {
			// ****************************************************************
			// * Open the named message queue for input; exclusive or shared
			// * use of the queue is controlled by the queue definition here
			// ****************************************************************
			MQOD mqod = env.newMQOD();
			mqod.setObjectName(getQueueName());
			int options = getOpenOptions();
			Phobj phobj = env.newPhobj();
			mq.MQOPEN(hconn, mqod, options, phobj, cc, rc);
			if (rc.x != CMQC.MQRC_NONE) {
				throw new Exception("MQOPEN ended with reason code " + rc.x);
			}
			Hobj hobj = phobj.getHobj();

			// ****************************************************************
			// * Get messages from the message queue
			// * Loop until there is a failure
			// ****************************************************************
			MQMD mqmd = env.newMQMD();
			mqmd.setFormat(CMQC.MQFMT_STRING);
			mqmd.setEncoding(546);
			mqmd.setCodedCharSetId(1381);

			MQGMO mqgmo = env.newMQGMO();
			mqgmo.setVersion(CMQC.MQGMO_VERSION_2);
			mqgmo.setMatchOptions(CMQC.MQMO_NONE);
			mqgmo.setOptions(CMQC.MQGMO_WAIT | CMQC.MQGMO_CONVERT);
			mqgmo.setWaitInterval(15000);

			boolean more = true;
			while (more) {
				mq.MQGET(hconn, hobj, mqmd, mqgmo, bufferLength, byteBuffer,
						dataLength, cc, rc);
				switch (rc.x) {
				case CMQC.MQRC_NONE:
					byte[] message = byteBuffer.array();
					String messageText = new String(message, 0, dataLength.x);
					System.out.println("message <" + messageText + ">");
					break;
				case CMQC.MQRC_NO_MSG_AVAILABLE:
					System.out.println("no more messages");
					more = false;
					break;
				default:
					throw new Exception("MQGET ended with reason code " + rc.x);
				}
			}

			// ****************************************************************
			// * Close the source queue (if it was opened)
			// ****************************************************************
			options = getCloseOptions();
			mq.MQCLOSE(hconn, phobj, options, cc, rc);
			if (rc.x != CMQC.MQRC_NONE) {
				throw new Exception("MQCLOSE ended with reason code " + rc.x);
			}
		} finally {
			// ****************************************************************
			// * Make sure to disconnect from the Queue Manager
			// ****************************************************************
			mq.MQDISC(phconn, cc, rc);
			if (rc.x != CMQC.MQRC_NONE) {
				throw new Exception("MQDISC ended with reason code " + rc.x);
			}
		}
		
		**/

	}

	public void putMessage(String message) throws Exception {
		/**
		String[] para = new String[]{queue,queueManager};
		// ****************************************************************
		// * Get the input parameters
		// ****************************************************************
		setOpenOptions(CMQC.MQOO_OUTPUT | CMQC.MQOO_FAIL_IF_QUIESCING);
		parseCommandLineArgs(para, 1, 6);
		parseSystemProperties();

		// ****************************************************************
		// * Initialise the Jmqi
		// ****************************************************************
		SampleTraceHandler trace = new SampleTraceHandler();
		JmqiThreadPoolFactory threadPool = new SampleThreadPoolFactory();
		JmqiPropertyHandler propertyHandler = new SamplePropertyHandler();
		JmqiEnvironment env = JmqiFactory.getInstance(trace, threadPool,
				propertyHandler);
	

		JmqiMQ mq = getMQInstance(env);

		Pint cc = env.newPint(0);
		Pint rc = env.newPint(0);

		// ****************************************************************
		// * Connect to queue manager
		// ****************************************************************
		Phconn phconn = env.newPhconn();
		String qmname = getQueueManagerName();
		MQCNO connectOptions = getConnectOptions(env);
		mq.MQCONNX(qmname, connectOptions, phconn, cc, rc);
		if (rc.x != CMQC.MQRC_NONE) {
			throw new Exception("MQCONN ended with reason code " + rc.x);
		}
		Hconn hconn = phconn.getHconn();

		try {
			// ****************************************************************
			// * Open the target message queue for output
			// ****************************************************************
			MQOD mqod = env.newMQOD();
			mqod.setObjectName(getQueueName());
			mqod.setObjectQMgrName(getTargetQueueManagerName());
			mqod.setDynamicQName(getDynamicQueueName());
			int options = getOpenOptions();
			Phobj phobj = env.newPhobj();
			mq.MQOPEN(hconn, mqod, options, phobj, cc, rc);
			if (rc.x != CMQC.MQRC_NONE) {
				throw new Exception("MQOPEN ended with reason code " + rc.x);
			}
			Hobj hobj = phobj.getHobj();

			// ****************************************************************
			// Read lines from the file and put them to the message queue
			// Loop until null line or end of file, or there is a failure
			// ****************************************************************
			MQMD mqmd = env.newMQMD();
			mqmd.setFormat(CMQC.MQFMT_STRING);
			mqmd.setEncoding(546);
			mqmd.setCodedCharSetId(1381);

			MQPMO mqpmo = env.newMQPMO();

			byte[] buffer = message.getBytes();
			ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
			int bufferLength = buffer.length;
			mq.MQPUT(hconn, hobj, mqmd, mqpmo, bufferLength, byteBuffer, cc, rc);
			if (rc.x != CMQC.MQRC_NONE) {
				throw new Exception("Could not put the message to the Queue");
			}

			// ****************************************************************
			// * Close the source queue (if it was opened)
			// ****************************************************************
			options = getCloseOptions();
			mq.MQCLOSE(hconn, phobj, options, cc, rc);
			if (rc.x != CMQC.MQRC_NONE) {
				throw new Exception("MQCLOSE ended with reason code " + rc.x);
			}
		} finally {
			// ****************************************************************
			// * Make sure to disconnect from the Queue Manager
			// ****************************************************************
			mq.MQDISC(phconn, cc, rc);
			if (rc.x != CMQC.MQRC_NONE) {
				throw new Exception("MQDISC ended with reason code " + rc.x);
			}
		}
		**/

	}



	public void setQueueManager(String queueManager) {
		this.queueManager = queueManager;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

	/**
	@Override
	public void send(String xml) throws Exception {
		// TODO Auto-generated method stub
		putMessage(xml);
	}
	**/

	
	
	public static void main(String[] args) {

	
	}
}
