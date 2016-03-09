package com.isell.ei.idcard.service;

import com.isell.core.util.Record;

/**
 * 身份证验证接口
 * 
 * @author wangpeng
 * @version [版本号, 2016年3月3日]
 */
public interface IdcardService {
	
	/** 接口地址 */
	String API_STROE_HTTP_URL = "http://apis.baidu.com/apistore/idservice/id";
	
	/** API密钥（测试用有效期60天 2016-03-03开始） */
	String API_STROE_API_KEY = "a4408ad1e918962b712d7612f572b875";
	
	/**
	 * 获取身份证信息
	 * 
	 * @param idCard
	 * @return
	 */
	Record getIdcardInfo(String idCard);

}
