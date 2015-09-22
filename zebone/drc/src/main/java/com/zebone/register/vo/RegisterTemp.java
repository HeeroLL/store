package com.zebone.register.vo;

/**
 * 注册系统主索引匹配失败对象
 *
 * @author 杨英
 * @version 2013-10-22 下午03:22
 */
public class RegisterTemp {
    private String id;

    //文档编号
    private String docNo;

    //注册时间
    private String registerTime;

    //卡号
    private String cardNo;

    //卡类型
    private String cardType;

    //姓名
    private String name;

    //发卡机构
    private String cardOrg;

    //系统编码
    private String systemCode;

    //服务url
    private String webUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardOrg() {
        return cardOrg;
    }

    public void setCardOrg(String cardOrg) {
        this.cardOrg = cardOrg;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
}
