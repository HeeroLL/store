package com.zebone.mq;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//import com.tongtech.tlq.base.TlqConnection;
//import com.tongtech.tlq.base.TlqException;
//import com.tongtech.tlq.base.TlqMessage;
//import com.tongtech.tlq.base.TlqMsgOpt;
//import com.tongtech.tlq.base.TlqQCU;

/**
 * 发送数据到队列
 * @author songjunjie
 * 
 */
@Component
public class Sender {
	private static final Log log = LogFactory.getLog(Sender.class);
	/**
	 * qcu的名字，安装tlq的时候默认为qcu1
	 */
	private  String qcuName;
	/**
	 * 远程队列名称
	 */
	private String remoteQueueName;
	
	
	public void setQcuName(String qcuName) {
		this.qcuName = qcuName;
	}

	public void setRemoteQueueName(String remoteQueueName) {
		this.remoteQueueName = remoteQueueName;
	}

	public void send(String xml) {
		//TODO 注释
		/**
		if (xml == null || xml.equals("")) {
			throw new RuntimeException("不能发送空信息！"); 
		}
		TlqConnection tlqConnection = null;
		TlqQCU tlqQcu = null;
		byte[] msgContent = xml.getBytes();
		try {
			tlqConnection = new TlqConnection();
			tlqQcu = tlqConnection.openQCU(qcuName);
			TlqMessage msgInfo = new TlqMessage();
			TlqMsgOpt msgOpt = new TlqMsgOpt();
			msgInfo.MsgType = TlqMessage.BUF_MSG; // 消息类型为bufer消息
			msgInfo.MsgSize = msgContent.length; // 消息大小
			msgInfo.setMsgData(msgContent);

			msgInfo.Persistence = TlqMessage.TLQPER_Y; // 持久性
			msgInfo.Priority = TlqMessage.TLQPRI_NORMAL; // 优先级
			msgInfo.Expiry = -1; // 生命周期
			msgOpt.QueName = remoteQueueName; // 远程队列名
			tlqQcu.putMessage(msgInfo, msgOpt);
		} catch (TlqException e) {
			log.error("",e);
			throw new RuntimeException(e);
		} finally {
			if (tlqQcu != null) {
				try {
					tlqQcu.close();
				} catch (TlqException e) {
					throw new RuntimeException(e);
				}
			}
			if (tlqConnection != null) {
				try {
					tlqConnection.close();
				} catch (TlqException e) {
					throw new RuntimeException(e);
				}
			}
		}
		**/
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int count = 1;
		for (int i = 1; i <= count; i++) {
			String str = i + "_[江苏振邦智慧城市信息系统有限公司]——"
					+ System.currentTimeMillis();

//			Sender.send(str);
		}
	}

}
