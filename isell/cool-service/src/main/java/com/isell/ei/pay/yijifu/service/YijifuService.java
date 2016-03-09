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
    String PARTNERID = "20160108020009913755";
    
    /**
     * 支付合作者身份ID
     */
    String PAY_PARTNERID = "20160108020009913795";
    
    /**
     * MD5签名时的密钥
     */
    String KEY = "988c53b769f0df0734dba9b836e5481e";
    
    /**
     * 支付MD5签名密钥
     */
    String PAY_KEY = "cf285160020d889a3871588e8de835ff";
    
    /**
     * 测试环境KEY
     */
    String TEST_KEY = "2af0376a5dc1695aa1ab889384a8ade9";
    
    /**
     * 测试环境的合作者ID
     */
    String TEST_PARTNERID = "20140926020000058373";
    
    /**
     * 测试网关地址
     */
    String TEST_GATEWAY = "https://openapi.yijifu.net/gateway.html";
    
    /**
     * 支付网关地址
     */
    String YIJIFU_GATEWAY = "https://openapi.yiji.com/gateway.html";
    
    /**
     * 支付单上传网关地址
     */
    String YIJIFUBG_GATEWAY = "https://openapiglobal.yiji.com/gateway.html";
    
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
    @Deprecated
    Map<String, String> paymentBillV2Order(Map<String, String> paramMap);
    
    /**
     * 实名查询服务
     *
     * @param paramMap map参数
     * @return 易极付返回的结果
     */
    Map<String, String> realNameQuery(Map<String, String> paramMap);
    
    /**
     * 合并支付
     *
     * @param paramMap map参数
     * @return 返回参数字符串
     */
    String unionCashierWebPay(Map<String, String> paramMap);
    
    /**
     * 上传支付信息V3.0
     *
     * @param paramMap map参数
     * @return 易极付返回的结果
     */
    Map<String, String> singlePaymentUpload(Map<String, String> paramMap);
    
    /**
     * 交易退款
     *
     * @param paramMap map参数
     * @return 易极付返回的结果
     */
    Map<String, String> tradeRefund(Map<String, String> paramMap);
    
    /**
     * 跨境订单信息同步
     *
     * @param remittranceBatchNo 跨境付款批次号
     * @param orderNos 订单号集合
     * @return 易极付返回的结果
     */
    Map<String, String> corderRemittanceSynOrder(String remittranceBatchNo, String... orderNos);
    
    /**
     * 跨境汇款申请
     *
     * @param paramMap map参数
     * @return 返回参数字符串
     */
    String applyRemittranceWithSynOrder(Map<String, String> paramMap);
}
