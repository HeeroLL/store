package com.isell.ei.pay.ehking.bean;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

/**
 * 支付单申报信息
 * 
 * @author lilin
 * @version [版本号, 2015年11月30日]
 */
@JsonPropertyOrder(value = {"name", "phoneNum", "email", "idType", "idNum", "bankCardNum"})
public class EhkingPayer {
    /**
     * 姓名或机构名称 若以机构名义申报，需要填写已备案的机构名称，否则请填写用户姓名
     */
    private String name;
    
    /**
     * 手机号码
     */
    private String phoneNum;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 身份证IDCARD, 组织机构ORG
     */
    private String idType;
    
    /**
     * 目前只接受18位身份证号码或9位组织机构代码，根据证件类型匹配
     */
    private String idNum;
    
    /**
     * 银行卡号
     */
    private String bankCardNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getBankCardNum() {
        return bankCardNum;
    }

    public void setBankCardNum(String bankCardNum) {
        this.bankCardNum = bankCardNum;
    }
}
