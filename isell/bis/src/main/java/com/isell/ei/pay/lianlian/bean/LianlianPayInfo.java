package com.isell.ei.pay.lianlian.bean;


/**
 * 连连支付工具类
 * 
 * @author 一代魔笛
 * 
 */
public class LianlianPayInfo {
    
    /** 版本号 */
    private String version = "1.0";
    
    /** 商户唯一标识 （艾易售正式商户号：201401271000001093） */
    private String merchant_id = "201503091000233507";
    
    /** 用户ID */
    private String merchant_userid;
    
    /** 商户名称 */
    //private String merchant_name = "杭州艾易售电子商务有限公司";
    
    /** 支付方式 */
    private String pay_method = "RMB Pay";
    
    /** 支付方式明细 */
    private String payment_detail = "";
    
    /** 业务编号 */
    private String biz_code = "111002";
    
    /** 支付结果通知地址 */
    private String url_notify;// 商户接收通知的地址
    
    /** 服务器IP */
    private String req_ip = "121.40.79.3";
    
    /** 支付单有效期 */
    private String bill_validdate;
    
    /** 支付结束回显URL */
    private String url_return;//
    
    /** 会员信息 */
    private String first_name;
    
    private String last_name;
    
    private String email;
    
    private String phone;
    
    private String shipping_address;
    
    /** 订单信息 */
    /** 商品类别 */
    private String goods_type;
    
    /** 商品订单号 */
    private String merchant_orderno;
    
    /** 商品订单交易时间 */
    private String merchant_trans_date;
    
    /** 订单交易金额 单位为厘 */
    private String trans_amt;
    
    /** 订单币种 */
    private String trans_cur = "CNY";
    
    /** 订单地址 */
    private String url_order = "";
    
    /** 订单信息 */
    private String info_order = "";
    
    /** 商品编号 */
    private String goods_no = "";
    
    /** 商品名称 */
    private String goods_name = "";
    
    /** 网关信息 1.IOS 2.Android 3.WAP */
    private String app_request = "";
    
    /** 备注信息 */
    private String remark = "";
    
    /** 风控参数 */
    private String risk_request_json;
    
    /** 分账信息 */
    private String shareing_data = "";
    
    /** 报关口岸 */
    private String extend_field1 = "Zjeport";
    
    /** 申报通知地址 */
    private String declare_notify;
    
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
    
    public String getMerchant_userid() {
        return merchant_userid;
    }
    
    public void setMerchant_userid(String merchant_userid) {
        this.merchant_userid = merchant_userid;
    }
    
    public String getPay_method() {
        return pay_method;
    }
    
    public void setPay_method(String pay_method) {
        this.pay_method = pay_method;
    }
    
    public String getPayment_detail() {
        return payment_detail;
    }
    
    public void setPayment_detail(String payment_detail) {
        this.payment_detail = payment_detail;
    }
    
    public String getBiz_code() {
        return biz_code;
    }
    
    public void setBiz_code(String biz_code) {
        this.biz_code = biz_code;
    }
    
    public String getUrl_notify() {
        return url_notify;
    }
    
    public void setUrl_notify(String url_notify) {
        this.url_notify = url_notify;
    }
    
    public String getReq_ip() {
        return req_ip;
    }
    
    public void setReq_ip(String req_ip) {
        this.req_ip = req_ip;
    }
    
    public String getBill_validdate() {
        return bill_validdate;
    }
    
    public void setBill_validdate(String bill_validdate) {
        this.bill_validdate = bill_validdate;
    }
    
    public String getUrl_return() {
        return url_return;
    }
    
    public void setUrl_return(String url_return) {
        this.url_return = url_return;
    }
    
    public String getFirst_name() {
        return first_name;
    }
    
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    
    public String getLast_name() {
        return last_name;
    }
    
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getShipping_address() {
        return shipping_address;
    }
    
    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }
    
    public String getGoods_type() {
        return goods_type;
    }
    
    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }
    
    public String getMerchant_orderno() {
        return merchant_orderno;
    }
    
    public void setMerchant_orderno(String merchant_orderno) {
        this.merchant_orderno = merchant_orderno;
    }
    
    public String getMerchant_trans_date() {
        return merchant_trans_date;
    }
    
    public void setMerchant_trans_date(String merchant_trans_date) {
        this.merchant_trans_date = merchant_trans_date;
    }
    
    public String getTrans_amt() {
        return trans_amt;
    }
    
    public void setTrans_amt(String trans_amt) {
        this.trans_amt = trans_amt;
    }
    
    public String getTrans_cur() {
        return trans_cur;
    }
    
    public void setTrans_cur(String trans_cur) {
        this.trans_cur = trans_cur;
    }
    
    public String getUrl_order() {
        return url_order;
    }
    
    public void setUrl_order(String url_order) {
        this.url_order = url_order;
    }
    
    public String getInfo_order() {
        return info_order;
    }
    
    public void setInfo_order(String info_order) {
        this.info_order = info_order;
    }
    
    public String getGoods_no() {
        return goods_no;
    }
    
    public void setGoods_no(String goods_no) {
        this.goods_no = goods_no;
    }
    
    public String getGoods_name() {
        return goods_name;
    }
    
    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }
    
    public String getApp_request() {
        return app_request;
    }
    
    public void setApp_request(String app_request) {
        this.app_request = app_request;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public String getRisk_request_json() {
        return risk_request_json;
    }
    
    public void setRisk_request_json(String risk_request_json) {
        this.risk_request_json = risk_request_json;
    }
    
    public String getShareing_data() {
        return shareing_data;
    }
    
    public void setShareing_data(String shareing_data) {
        this.shareing_data = shareing_data;
    }
    
    public String getExtend_field1() {
        return extend_field1;
    }
    
    public void setExtend_field1(String extend_field1) {
        this.extend_field1 = extend_field1;
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

    public String getDeclare_notify() {
        return declare_notify;
    }

    public void setDeclare_notify(String declare_notify) {
        this.declare_notify = declare_notify;
    }
}
