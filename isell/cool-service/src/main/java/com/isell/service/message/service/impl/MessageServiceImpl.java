package com.isell.service.message.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.isell.core.util.Record;
import com.isell.service.message.dao.CoolMessageMapper;
import com.isell.service.message.service.MessageService;
import com.isell.service.message.vo.CoolMessage;

/**
 * 消息接口实现类
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-20]
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
	
	/**
	 * 消息mapper
	 */
	@Resource
	private CoolMessageMapper coolMessageMapper;

	/**
	 * 获取消息列表
	 * 
	 * @param message
	 * @return 消息列表
	 */
	@Override
	public Record getMessageList(CoolMessage message) {
		Record record = new Record();
		boolean success = false;
		Integer userId = message.getUserId();
		if(userId != null){
			List<CoolMessage> messageList = coolMessageMapper.findCoolMessageListPage(message.getRowBounds(), message);
			success = true;
			if(CollectionUtils.isNotEmpty(messageList)){
				record.set("messageList", messageList);
			}
		}else{
			record.set("msg", "用户主键不能为空");
		}
		record.set("success", success);
		return record;
	}

}
