package com.isell.ei.pay.fuiou.service;

import java.util.Map;

/**
 * 富友支付业务层
 * 
 * @author 毛伟杰
 * @version [版本号, 2016年3月8日]
 */
public interface FuiouService {
	
	//测试环境
	public static final String FUIOU_CESHI = "http://www-1.fuiou.com:8888/wg1_run/PayBBCGate.do";
	//正式环境
	public static final String FUIOU_ZHENGSHI = "https://pay.fuiou.com/PayBBCGate.do";
	//提交方式
	public static final String METHOD = "post";
	//商户代码
	public static final String MCHNTCD = "0002900F0006944";
	//支付类型
	public static final String PAYTYPE = "B2C";
	//版本号
	public static final String VER = "1.0.0";
	//电商企业名称
	public static final String PAYMERCHANTNAME = "上海琨铭文化传播有限公司-网关";
	//电商企业编号
	public static final String PAYMERCHANTCODE = "0002900F0283110";
	/**
	 * 富友支付
	 * @param paramMap
	 * @return
	 */
	public  String webPay(Map<String, String> paramMap);
}
