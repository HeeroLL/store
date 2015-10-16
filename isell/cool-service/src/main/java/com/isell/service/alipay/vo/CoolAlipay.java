package com.isell.service.alipay.vo;
/**
 * 支付宝信息vo
 *
 * @author wangpeng
 */
public class CoolAlipay{
    /**
     * 
     */
    private Integer id;
    /**
     * 支付宝名称
     */
    private String alipayname;
    /**
     * 合作身份者id
     */
    private String partner;
    /**
     * 安全检验码
     */
    private String key;
    /**
     * 
     */
    private Integer bId;

    /**
     * 
     */
    public Integer getId(){
        return this.id;
    }

    /**
     * 
     */
    public void setId(Integer id){
        this.id = id;
    }    
    /**
     * 支付宝名称
     */
    public String getAlipayname(){
        return this.alipayname;
    }

    /**
     * 支付宝名称
     */
    public void setAlipayname(String alipayname){
        this.alipayname = alipayname;
    }    
    /**
     * 合作身份者id
     */
    public String getPartner(){
        return this.partner;
    }

    /**
     * 合作身份者id
     */
    public void setPartner(String partner){
        this.partner = partner;
    }    
    /**
     * 安全检验码
     */
    public String getKey(){
        return this.key;
    }

    /**
     * 安全检验码
     */
    public void setKey(String key){
        this.key = key;
    }    
    /**
     * 
     */
    public Integer getbId(){
        return this.bId;
    }

    /**
     * 
     */
    public void setbId(Integer bId){
        this.bId = bId;
    }    
}