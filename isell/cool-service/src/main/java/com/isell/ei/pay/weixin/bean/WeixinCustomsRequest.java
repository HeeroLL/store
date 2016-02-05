package com.isell.ei.pay.weixin.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.isell.ei.pay.weixin.service.WeixinPayService;

/**
 * 财付通报关参数信息
 * 
 * @author lilin
 * @version [版本号, 2015年12月8日]
 */
@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeixinCustomsRequest {
    /**
     * 签名方式
     */
    private String sign_type;
    
    /**
     * 接口版本
     */
    private String service_version;
    
    /**
     * 字符集
     */
    private String input_charset = "UTF-8";
    
    /**
     * 签名
     */
    private String sign;
    
    /**
     * 密钥序号
     */
    private String sign_key_index;
    
    /**
     * 商户号
     */
    private String partner = WeixinPayService.MCH_ID;
    
    /**
     * 商户订单号
     */
    private String out_trade_no;
    
    /**
     * 财付通订单号
     */
    private String transaction_id;
    
    /**
     * <pre>
     * 0 无需上报海关
     * 1广州
     * 2杭州
     * 3宁波
     * 4深圳
     * 5郑州(保税物流中心)
     * 6重庆
     * 7西安
     * 8上海
     * 9 郑州（综保区）
     * </pre>
     */
    private String customs = "9";
    
    /**
     * 商户海关备案号
     */
    private String mch_customs_no = "3117964017"; // 郑州海关备案号
    
    /**
     * 操作类型 1 新增
     */
    private String action_type = "1";
    
    public String getSign() {
        return sign;
    }
    
    public void setSign(String sign) {
        this.sign = sign;
    }
    
    public String getPartner() {
        return partner;
    }
    
    public void setPartner(String partner) {
        this.partner = partner;
    }
    
    public String getOut_trade_no() {
        return out_trade_no;
    }
    
    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
    
    public String getTransaction_id() {
        return transaction_id;
    }
    
    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }
    
    public String getCustoms() {
        return customs;
    }
    
    public void setCustoms(String customs) {
        this.customs = customs;
    }
    
    public String getAction_type() {
        return action_type;
    }
    
    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getService_version() {
        return service_version;
    }

    public void setService_version(String service_version) {
        this.service_version = service_version;
    }

    public String getInput_charset() {
        return input_charset;
    }

    public void setInput_charset(String input_charset) {
        this.input_charset = input_charset;
    }

    public String getSign_key_index() {
        return sign_key_index;
    }

    public void setSign_key_index(String sign_key_index) {
        this.sign_key_index = sign_key_index;
    }

    public String getMch_customs_no() {
        return mch_customs_no;
    }

    public void setMch_customs_no(String mch_customs_no) {
        this.mch_customs_no = mch_customs_no;
    }
}
