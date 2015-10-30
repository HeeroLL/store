package com.isell.ei.pay.yijifu.service;

import java.util.Map;

/**
 * 易极付支付service层接口
 * 
 * @author lilin
 * @version [版本号, 2015年10月28日]
 */
public interface YijifuService {
    /**
     * 合作者身份ID
     */
    String PARTNERID = "20140926020000058373";
    
    /**
     * 支付合作者身份ID
     */
    String PAY_PARTNERID = "20140411020055684571";
    
    /**
     * MD5签名时的密钥
     */
    String KEY = "2af0376a5dc1695aa1ab889384a8ade9";
    
    /**
     * 支付MD5签名密钥
     */
    String PAY_KEY = "c9cef22553af973d4b04a012f9cb8ea8";
    
    /**
     * 网关地址
     */
    String YIJIFU_GATEWAY = "https://openapi.yijifu.net/gateway.html";
    
    /**
     * 字符编码格式 目前支持 utf-8
     */
    String INPUT_CHARSET = "utf-8";
    
    /**
     * 签名方式，MD5
     */
    String SIGN_TYPE = "MD5";
    
    /**
     * 获取封装后的支付信息map
     * 
     * @param paramMap map参数
     * @return 返回的参数字符串
     */
    String getPayParams(Map<String, String> paramMap);
    
    /**
     * 支付单上传
     *
     * @param paramMap map参数
     * @return 易极付返回的结果
     */
    Map<String, String> paymentBillV2Order(Map<String, String> paramMap);
    
    /**
     * 实名查询服务
     *
     * @param paramMap map参数
     * @return 易极付返回的结果
     */
    Map<String, String> realNameQuery(Map<String, String> paramMap);
}
