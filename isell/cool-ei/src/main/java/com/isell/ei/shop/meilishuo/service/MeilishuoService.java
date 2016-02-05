package com.isell.ei.shop.meilishuo.service;

import java.util.Map;

import com.isell.core.util.Record;
import com.isell.ei.shop.meilishuo.bean.MeilishuoTokenResult;

/**
 * 美丽说业务层接口
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-31]
 */
public interface MeilishuoService {
	
	/** 发送地址 */
	String HOST = "http://open.higo.meilishuo.com/oauth2/api";
	
	/** 获取token地址 */
	String TOKEN_URL = "http://open.higo.meilishuo.com/oauth2/token";
	
	/** 测试账号 */
	String MLS_APPKEY = "8510959007";
	
	/** 测试账号 */
	String MLS_APPSECRET = "p0stgzqja5fhpdkxzpa2p2qxixwflu";
	
	/** 回调地址(测试) */
	String REDIRECT_URL = "http://service.i-cooltest.com/meilishuo/higo";
	
	/** state */
	String STATE = "123456";
	
	String MLS_CODE = " c981444ac76fbab818dd3391f73b103eddf7c06b";
	
	/** 访问令牌(测试账号所有) */
	String ACCESS_TOKEN = "c981444ac76fbab818dd3391f73b103eddf7c06b";
	
	/**
	 * 调用HIGO登录授权界面
	 * 
	 * @param paramMap
	 */
	void login(Map<String, String> paramMap);
	
	/**
	 * 下载美丽说订单
	 * 
	 * @param paramMap
	 * @return
	 */
	Record getMeilishuoOrder(Map<String, String> paramMap);
	
	/**
	 * 获取access_token
	 * @param Map<String, String> paramMap
	 * @return
	 */
	String getAccessToken(Map<String, String> paramMap);
	
	/**
	 * 刷新access_token
	 * @param code
	 * @return
	 */
	MeilishuoTokenResult getRefreshToken(String token);
	
	/**
	 * 获取美丽说物流公司信息
	 */
	void getMeilishuoLogisticsCompanies();

}
