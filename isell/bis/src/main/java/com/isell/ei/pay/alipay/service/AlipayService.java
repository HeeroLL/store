package com.isell.ei.pay.alipay.service;

import java.util.Map;

/**
 * 支付宝支付service层接口
 * 
 * @author lilin
 * @version [版本号, 2015年7月26日]
 */
public interface AlipayService {
    /**
     * 合作者身份ID
     */
    // String PARTNER = "2088711787113554";
    String PARTNER = "2088021829532240";
    
    /**
     * 付款方支付宝账户姓名
     */
    String ACCOUNT_NAME = "杭州艾易售电子商务有限公司‍";// "magic.wang@i-coolshop.cn";
    
    /**
     * 付款方支付宝账户
     */
    String EMAIL = "magic.wang@i-coolshop.cn‍";// "marilyn.ma@i-sell.cn";
    
    /**
     * MD5签名时的密钥
     */
    // String KEY = "6jmeztqlek89dxucy9pphsa5mfk2i7f4";
    String KEY = "utjn3sfouen3ynpesrv227xx91yllha8";
    
    /**
     * 支付宝网关地址
     */
    String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do";
    
    /**
     * 字符编码格式 目前支持 utf-8
     */
    String INPUT_CHARSET = "utf-8";
    
    /**
     * 签名方式，选择项：0001(RSA)、MD5
     */
    String SIGN_TYPE = "MD5";
    
    /**
     * 以什么方式提交表单
     */
    String METHOD = "POST";
    
    /**
     * 支付类型。仅支持：1（商品购买）
     */
    String PAYMENT_TYPE = "1";
    
    /**
     * 获取封装后的支付信息map
     * 
     * @param paramMap map参数
     * @return 返回的参数字符串
     */
    String getPayParams(Map<String, String> paramMap);
    
    /**
     * 获取封装后的打款信息map
     * 
     * @param paramMap map参数
     * @return 返回的参数字符串
     */
    String getBatchTransParams(Map<String, String> paramMap);
}
