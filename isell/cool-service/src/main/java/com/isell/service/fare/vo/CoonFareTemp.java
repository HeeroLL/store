package com.isell.service.fare.vo;

import java.util.Date;

/**
 * 
 * 快递模板VO
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-04]
 *
 */
public class CoonFareTemp{
    /**
     * ID主键
     */
    private String id;
    /**
     * 模板名称
     */
    private String tempName;
    /**
     * 是否包邮
     */
    private Boolean parcel;
    /**
     * 计价方式(1.重量 2.件数 3.体积)
     */
    private Integer pricing;
    /**
     * 快递公司代码
     */
    private String expressCode;
    /**
     * 是否默认
     */
    private Boolean acquiesce;
    /**
     * 创建时间
     */
    private Date createtime;

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
     * 模板名称
     */
    public String getTempName(){
        return this.tempName;
    }

    /**
     * 模板名称
     */
    public void setTempName(String tempName){
        this.tempName = tempName;
    }    
    /**
     * 是否包邮
     */
    public Boolean getParcel(){
        return this.parcel;
    }

    /**
     * 是否包邮
     */
    public void setParcel(Boolean parcel){
        this.parcel = parcel;
    }    
    /**
     * 计价方式(1.重量 2.件数 3.体积)
     */
    public Integer getPricing(){
        return this.pricing;
    }

    /**
     * 计价方式(1.重量 2.件数 3.体积)
     */
    public void setPricing(Integer pricing){
        this.pricing = pricing;
    }    
    /**
     * 快递公司代码
     */
    public String getExpressCode(){
        return this.expressCode;
    }

    /**
     * 快递公司代码
     */
    public void setExpressCode(String expressCode){
        this.expressCode = expressCode;
    }    
    /**
     * 是否默认
     */
    public Boolean getAcquiesce(){
        return this.acquiesce;
    }

    /**
     * 是否默认
     */
    public void setAcquiesce(Boolean acquiesce){
        this.acquiesce = acquiesce;
    }    
    /**
     * 创建时间
     */
    public Date getCreatetime(){
        return this.createtime;
    }

    /**
     * 创建时间
     */
    public void setCreatetime(Date createtime){
        this.createtime = createtime;
    }    
}