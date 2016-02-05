package com.isell.ei.pay.lianlian.bean;

/**
 * 连连支付退款信息
 * 
 * @author lilin
 * @version [版本号, 2015年8月20日]
 */
public class LianlianRefundInfo {
    /** 版本号 */
    private String version = "1.0";
    
    /** 商户唯一标识 （艾易售正式商户号：201401271000001093） */
    private String merchant_id = "201503091000233507";
    
    /**
     * 支付单号
     */
    private String oid_billno;
    
    /**
     * 原收款方
     */
    private String col_custid = "201503091000233507";
    
    /**
     * 退款金额(单位：厘)
     */
    private String col_amt_refund;
    
    /**
     * 退款币种
     */
    private String col_cur_code = "CNY";
    
    /**
     * 通知地址
     */
    private String url_notify;
    
    /** 签名 */
    private String sign = "RDcd1dseIKLcede323";
    
    /** 签名方式 */
    private String sign_method = "MD5";
    
    public String getVersion() {
        return version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    public String getMerchant_id() {
        return merchant_id;
    }
    
    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }
    
    public String getOid_billno() {
        return oid_billno;
    }
    
    public void setOid_billno(String oid_billno) {
        this.oid_billno = oid_billno;
    }
    
    public String getCol_custid() {
        return col_custid;
    }
    
    public void setCol_custid(String col_custid) {
        this.col_custid = col_custid;
    }
    
    public String getCol_amt_refund() {
        return col_amt_refund;
    }
    
    public void setCol_amt_refund(String col_amt_refund) {
        this.col_amt_refund = col_amt_refund;
    }
    
    public String getCol_cur_code() {
        return col_cur_code;
    }
    
    public void setCol_cur_code(String col_cur_code) {
        this.col_cur_code = col_cur_code;
    }
    
    public String getUrl_notify() {
        return url_notify;
    }
    
    public void setUrl_notify(String url_notify) {
        this.url_notify = url_notify;
    }
    
    public String getSign() {
        return sign;
    }
    
    public void setSign(String sign) {
        this.sign = sign;
    }
    
    public String getSign_method() {
        return sign_method;
    }
    
    public void setSign_method(String sign_method) {
        this.sign_method = sign_method;
    }
}
