package com.isell.service.order.vo;

import java.math.BigDecimal;

/**
 *
 * @author 
 */
public class CoonFareTempProByGetFare{
    /**
     * ID主键
     */
    private String id;
    /**
     * 模板ID
     */
    private String tempId;
    /**
     * 省份
     */
    private String province;
    /**
     * 首重单位(kg)
     */
    private BigDecimal firstWei;
    /**
     * 首重价格
     */
    private BigDecimal firstPri;
    /**
     *  续重单位(kg)
     */
    private BigDecimal extentWei;
    /**
     * 续重价格
     */
    private BigDecimal extentPri;
    /**
     * 震荣时效
     */
    private String timeLength;
    /**
     * 运输类型 1.汽运2.航空
     */
    private Integer type;

    /**
     * ID主键
     */
    public String getId(){
        return this.id;
    }

    /**
     * ID主键
     */
    public void setId(String id){
        this.id = id;
    }    
    /**
     * 模板ID
     */
    public String getTempId(){
        return this.tempId;
    }

    /**
     * 模板ID
     */
    public void setTempId(String tempId){
        this.tempId = tempId;
    }    
    /**
     * 省份
     */
    public String getProvince(){
        return this.province;
    }

    /**
     * 省份
     */
    public void setProvince(String province){
        this.province = province;
    }    
    /**
     * 首重单位(kg)
     */
    public BigDecimal getFirstWei(){
        return this.firstWei;
    }

    /**
     * 首重单位(kg)
     */
    public void setFirstWei(BigDecimal firstWei){
        this.firstWei = firstWei;
    }    
    /**
     * 首重价格
     */
    public BigDecimal getFirstPri(){
        return this.firstPri;
    }

    /**
     * 首重价格
     */
    public void setFirstPri(BigDecimal firstPri){
        this.firstPri = firstPri;
    }    
    /**
     *  续重单位(kg)
     */
    public BigDecimal getExtentWei(){
        return this.extentWei;
    }

    /**
     *  续重单位(kg)
     */
    public void setExtentWei(BigDecimal extentWei){
        this.extentWei = extentWei;
    }    
    /**
     * 续重价格
     */
    public BigDecimal getExtentPri(){
        return this.extentPri;
    }

    /**
     * 续重价格
     */
    public void setExtentPri(BigDecimal extentPri){
        this.extentPri = extentPri;
    }    
    /**
     * 震荣时效
     */
    public String getTimeLength(){
        return this.timeLength;
    }

    /**
     * 震荣时效
     */
    public void setTimeLength(String timeLength){
        this.timeLength = timeLength;
    }    
    /**
     * 运输类型 1.汽运2.航空
     */
    public Integer getType(){
        return this.type;
    }

    /**
     * 运输类型 1.汽运2.航空
     */
    public void setType(Integer type){
        this.type = type;
    }    
}