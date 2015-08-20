package com.sell.ei.pay.lianlian.service;

import com.sell.ei.pay.lianlian.bean.LianlianPayInfo;
import com.sell.ei.pay.lianlian.bean.LianlianRefundInfo;

/**
 * 连连支付业务层
 * 
 * @author lilin
 * @version [版本号, 2015年8月20日]
 */
public interface LianlianPayService {
    /** 版本号 */
    String VERSION = "1.0";
    
    /** 商户唯一标识 （测试商户号：201401271000001093） */
    String MERCHANT_ID = "201503091000233507";
    
    /** 签名 */
    String SIGN = "RDcd1dseIKLcede323";
    
    /** 签名方式 */
    String SIGN_METHOD = "MD5";
    
    /**
     * 获取连连支付表单参数
     *
     * @param lianlianPayInfo 参数
     * @return 连连支付表单参数
     */
    String getPayParams(LianlianPayInfo lianlianPayInfo);
    
    /**
     * 获取连连退款表单参数
     *
     * @param lianlianRefundInfo 参数
     * @return 连连退款表单参数
     */
    String getRefundParams(LianlianRefundInfo lianlianRefundInfo);
}
