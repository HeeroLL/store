package com.zebone.btp.app.sms.service;

import java.util.List;

/**
 * 短信息服务
 * @author 宋俊杰
 *
 */

public interface SmsService {
	
	/**
	 * 发送手机短信
	 * @param content 短信内容
	 * @param mobileNo 手机号码。多个手机号码用","分割
	 * @param senderId 发送人ID，如果不传入此参数表示为系统发送。
	 */
	public void send(String content,String mobileNo,String senderId);
	
	/**
	 * 发送手机短信
	 * @param content 短信内容
	 * @param receiverId 短信接收人ID。
	 * @param senderId 发送人ID，如果不传入此参数表示为系统发送。
	 */
	public void send(String content,List<String> receiverId,String senderId);
	
	
	
}
