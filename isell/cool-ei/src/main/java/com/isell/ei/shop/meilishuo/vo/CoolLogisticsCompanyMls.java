package com.isell.ei.shop.meilishuo.vo;

/**
 * 美丽说物流公司信息表
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-31]
 */
public class CoolLogisticsCompanyMls{
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 物流公司编号
     */
    private Integer companyId;
    /**
     * 物流公司名称
     */
    private String companyName;

    /**
     * 主键id
     */
    public Integer getId(){
        return this.id;
    }

    /**
     * 主键id
     */
    public void setId(Integer id){
        this.id = id;
    }    
    /**
     * 物流公司编号
     */
    public Integer getCompanyId(){
        return this.companyId;
    }

    /**
     * 物流公司编号
     */
    public void setCompanyId(Integer companyId){
        this.companyId = companyId;
    }    
    /**
     * 物流公司名称
     */
    public String getCompanyName(){
        return this.companyName;
    }

    /**
     * 物流公司名称
     */
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }    
}