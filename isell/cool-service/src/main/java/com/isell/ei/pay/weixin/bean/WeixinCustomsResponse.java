package com.isell.ei.pay.weixin.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 财付通报关响应信息
 * 
 * @author lilin
 * @version [版本号, 2015年12月8日]
 */
@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeixinCustomsResponse {
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
    private String input_charset;
    
    /**
     * 签名
     */
    private String sign;
    
    /**
     * 密钥序号
     */
    private String sign_key_index;
    
    /**
     * 返回状态码
     */
    private String retcode;
    
    /**
     * 返回信息
     */
    private String retmsg;
    
    /**
     * 状态码
     */
    private String state;
    
    /**
     * 商户号
     */
    private String partner;
    
    /**
     * 商户订单号
     */
    private String out_trade_no;
    
    /**
     * 财付通订单号
     */
    private String transaction_id;
    
    /**
     * 商户子订单号
     */
    private String sub_order_no;
    
    /**
     * 财付通子订单号
     */
    private String sub_order_id;
    
    /**
     * 最后更新时间
     */
    private String modify_time;
    
    /**
     * 业务类型
     */
    private String business_type;
    
    /**
     * 申报结果说明
     */
    private String explanation;

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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign_key_index() {
        return sign_key_index;
    }

    public void setSign_key_index(String sign_key_index) {
        this.sign_key_index = sign_key_index;
    }

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public String getRetmsg() {
        return retmsg;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getSub_order_no() {
        return sub_order_no;
    }

    public void setSub_order_no(String sub_order_no) {
        this.sub_order_no = sub_order_no;
    }

    public String getSub_order_id() {
        return sub_order_id;
    }

    public void setSub_order_id(String sub_order_id) {
        this.sub_order_id = sub_order_id;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

    public String getBusiness_type() {
        return business_type;
    }

    public void setBusiness_type(String business_type) {
        this.business_type = business_type;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
