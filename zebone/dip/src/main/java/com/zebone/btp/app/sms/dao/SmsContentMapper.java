package com.zebone.btp.app.sms.dao;

import com.zebone.btp.app.sms.pojo.SmsContent;

public interface SmsContentMapper {
	int deleteById(String smsContentId);

	int insert(SmsContent record);

	SmsContent findById(String smsContentId);

	int updateById(SmsContent record);
}