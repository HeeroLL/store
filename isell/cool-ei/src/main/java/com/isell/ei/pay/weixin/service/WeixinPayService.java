package com.isell.ei.pay.weixin.service;

import java.util.TreeMap;

import com.isell.ei.pay.weixin.bean.WeixinCustomsResponse;
import com.isell.ei.pay.weixin.bean.WeixinPayResultInfo;

/**
 * 微信支付接口服务层
 * 
 * @author lilin
 * @version [版本号, 2015年7月23日]
 */
public interface WeixinPayService {
    
    /**
     * 微信公众账号ID
     */
    String APPID = "wx13b5278aee454058"; // old = wxe863e749f59e9d7f
    
    /**
     * 商户号
     */
    String MCH_ID = "1274723201"; // old = 1259762301
    
    /**
     * 微信支付设置的密钥
     */
    String KEY = "b1fa6a777455436cb5988f7752377994"; // old = "5ec7962c67c34e229f1e74e74aa5a5d6";
    
    /**
     * 微信支付的URL
     */
    String URL = "https://api.mch.weixin.qq.com/pay";
    
    /**
     * 统一下单接口
     */
    String UNIFIEDORDER = URL + "/unifiedorder";
    
    /**
     * 查询订单接口
     */
    String ORDERQUERY = URL + "/orderquery";
    
    /**
     * 关闭订单接口
     */
    String CLOSEORDER = URL + "/closeorder";
    
    /**
     * 财付通海关申报接口
     */
    String SENDORDER = "http://mch.tenpay.com/cgi-bin/mch_custom_declare.cgi";
    
    /**
     * 财付通海关申报接口
     */
    String CUSTOMQUERY = "http://mch.tenpay.com/cgi-bin/mch_custom_query.cgi";
    
    /**
     * 财付通海关申报接口
     */
    String DOWNLOADBILL = "http://mch.tenpay.com/cgi-bin/mch_custom_download_bill.cgi";
    
    /**
     * 统一下单服务(JSAPI)
     * 
     * @param paramMap map参数
     * @return 返回Map
     */
    TreeMap<String, Object> unifiedorder(TreeMap<String, Object> paramMap);
    
    /**
     * 查询订单服务
     * 
     * @param paramMap map参数
     * @return 返回Map
     */
    TreeMap<String, Object> orderquery(TreeMap<String, Object> paramMap);
    
    /**
     * 关闭订单服务
     * 
     * @param paramMap map参数
     * @return 返回Map
     */
    TreeMap<String, Object> closeorder(TreeMap<String, Object> paramMap);
    
    /**
     * 支付结果通知接口<br>
     * 该接口是通过【统一下单API】中提交的参数notify_url设置，如果链接无法访问，商户将无法接收到微信通知。
     * 
     * @param payResultInfo 请求参数
     * @return 返回响应
     */
    WeixinPayResultInfo sendPayResult(WeixinPayResultInfo payResultInfo);
    
    /**
     * 财付通报关
     *
     * @param paramMap 财付通报关参数
     * @return 财付通报关响应
     */
    WeixinCustomsResponse sendOrder(TreeMap<String, String> paramMap);
    
    /**
     * 支付单申报海关状态查询
     *
     * @param paramMap 查询参数
     * @return 响应信息
     */
    TreeMap<String, Object> customQuery(TreeMap<String, String> paramMap);
    
    /**
     * 支付单申报结果下载
     *
     * @param paramMap 查询参数
     * @return 响应信息
     */
    String downloadBill(TreeMap<String, String> paramMap);
}
