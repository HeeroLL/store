package com.isell.ei.pay.ehking.bean;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

/**
 * 报关买家信息
 * 
 * @author lilin
 * @version [版本号, 2015年11月30日]
 */
@JsonPropertyOrder(value = {"payerName", "idNum", "phoneNum"})
public class EhkingCustomsPayer {
    /**
     * 用户姓名
     */
    private String payerName;
    
    /**
     * 目前只接受18位身份证号码或9位组织机构代码，根据证件类型匹配
     */
    private String idNum;
    
    /**
     * 手机号
     */
    private String phoneNum;

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

}
