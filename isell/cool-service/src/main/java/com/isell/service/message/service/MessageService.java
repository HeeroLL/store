package com.isell.service.message.service;

import com.isell.core.util.Record;
import com.isell.service.message.vo.CoolMessage;

/**
 * 消息接口
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-20]
 */
public interface MessageService {
	
	/**
	 * 获取消息列表
	 * 
	 * @param message
	 * @return 消息列表
	 */
	Record getMessageList(CoolMessage message);

}
