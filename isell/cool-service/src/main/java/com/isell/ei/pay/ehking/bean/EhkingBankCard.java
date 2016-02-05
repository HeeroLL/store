package com.isell.ei.pay.ehking.bean;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

/**
 * 快捷支付信息
 * 
 * @author lilin
 * @version [版本号, 2015年11月30日]
 */
@JsonPropertyOrder(value = {"name", "cardNo", "cvv2", "idNo", "expiryDate", "mobileNo"})
public class EhkingBankCard {
    /**
     * 付款方姓名
     */
    private String name;
    
    /**
     * 付款方银行卡号
     */
    private String cardNo;
    
    /**
     * 信用卡背面的cvv2码
     */
    private String cvv2;
    
    /**
     * 付款方身份证号
     */
    private String idNo;
    
    /**
     * 信用卡有效期年月
     */
    private String expiryDate;
    
    /**
     * 付款方手机号码
     */
    private String mobileNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
