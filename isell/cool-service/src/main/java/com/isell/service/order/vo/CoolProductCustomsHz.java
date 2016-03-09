package com.isell.service.order.vo;
/**
 *
 * @author 
 */
public class CoolProductCustomsHz{
    /**
     * 
     */
    private Integer id;
    /**
     * 
     */
    private Integer pId;
    /**
     * 
     */
    private Integer gId;
    /**
     * 币别
     */
    private String currency;
    /**
     * 行邮税号
     */
    private String codeTs;
    /**
     * 起运国三字代码
     */
    private String tradeCountry;

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
     * 
     */
    public Integer getpId(){
        return this.pId;
    }

    /**
     * 
     */
    public void setpId(Integer pId){
        this.pId = pId;
    }    
    /**
     * 
     */
    public Integer getgId(){
        return this.gId;
    }

    /**
     * 
     */
    public void setgId(Integer gId){
        this.gId = gId;
    }    
    /**
     * 币别
     */
    public String getCurrency(){
        return this.currency;
    }

    /**
     * 币别
     */
    public void setCurrency(String currency){
        this.currency = currency;
    }    
    /**
     * 行邮税号
     */
    public String getCodeTs(){
        return this.codeTs;
    }

    /**
     * 行邮税号
     */
    public void setCodeTs(String codeTs){
        this.codeTs = codeTs;
    }    
    /**
     * 起运国三字代码
     */
    public String getTradeCountry(){
        return this.tradeCountry;
    }

    /**
     * 起运国三字代码
     */
    public void setTradeCountry(String tradeCountry){
        this.tradeCountry = tradeCountry;
    }    
}