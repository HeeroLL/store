package com.isell.ei.pay.lianlian.service;

import com.isell.ei.pay.lianlian.bean.LianlianPayInfo;
import com.isell.ei.pay.lianlian.bean.LianlianRefundInfo;
import com.isell.ei.pay.lianlian.bean.LianlianRefundResult;

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
    
    /** 退款URL */
    String REFUND_URL = "https://yintong.com.cn/globalpay/cashier/refund.htm";
    
    /**
     * 获取连连支付表单参数
     * 
     * @param lianlianPayInfo 参数
     * @return 连连支付表单参数
     */
    String getPayParams(LianlianPayInfo lianlianPayInfo);
    
    /**
     * 调用连连退款接口
     * 
     * @param lianlianRefundInfo 参数
     * @return 退款结果
     */
    LianlianRefundResult refund(LianlianRefundInfo lianlianRefundInfo);
}
