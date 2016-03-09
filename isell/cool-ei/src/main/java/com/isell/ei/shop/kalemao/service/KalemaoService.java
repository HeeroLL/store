package com.isell.ei.shop.kalemao.service;

import java.util.List;
import java.util.Map;

import com.isell.core.util.JsonData;

/**
 * 卡乐猫业务层接口
 * 
 * @author maoweijie 
 * @version [版本号, 2016年2月25日]
 */
public interface KalemaoService {
	 /**
     * 发送地址(测试)
     */
	public static final String CS_SEND_URL = "http://tt3.ewanse.com/api/haitao-api/up-waybill-no";
    /**
     * 发送地址(正式)
     */
	public static final String ZS_SEND_URL = "http://v.ewanse.com/api/haitao-api/up-waybill-no";
    /**
     * 加密Key
     */
	public static final String AUTHCODE = "1fcd3a412850395eb646f7bc0b0f744b";
	/**
	 * 接入码
	 */
	public static final String ACCESSCODE = "aishou";
	
	
    /**
     * 修改订单物流单号
     * @param list
     */
	JsonData upWayBillNo(List<Map<String, String>> list);
}
