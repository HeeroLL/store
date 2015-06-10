package com.zebone.btp.app.sms.dao;

import com.zebone.btp.app.sms.pojo.SmsReceiver;

public interface SmsReceiverMapper {
	int deleteById(String smsReceiverId);

	int insert(SmsReceiver record);

	SmsReceiver findById(String smsReceiverId);

	int updateById(SmsReceiver record);
}