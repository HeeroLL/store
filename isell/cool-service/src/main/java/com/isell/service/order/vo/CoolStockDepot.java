package com.isell.service.order.vo;
/**
 *
 * @author 
 */
public class CoolStockDepot{
    /**
     * 
     */
    private Integer id;
    /**
     * 仓库名称
     */
    private String name;
    /**
     * 租赁到期日
     */
    private String timeEnd;
    /**
     * 租赁时长
     */
    private Integer timeLength;
    /**
     * 公司id
     */
    private String companyId;
    /**
     * 仓库所属省份
     */
    private String province;

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
     * 仓库名称
     */
    public String getName(){
        return this.name;
    }

    /**
     * 仓库名称
     */
    public void setName(String name){
        this.name = name;
    }    
    /**
     * 租赁到期日
     */
    public String getTimeEnd(){
        return this.timeEnd;
    }

    /**
     * 租赁到期日
     */
    public void setTimeEnd(String timeEnd){
        this.timeEnd = timeEnd;
    }    
    /**
     * 租赁时长
     */
    public Integer getTimeLength(){
        return this.timeLength;
    }

    /**
     * 租赁时长
     */
    public void setTimeLength(Integer timeLength){
        this.timeLength = timeLength;
    }    
    /**
     * 公司id
     */
    public String getCompanyId(){
        return this.companyId;
    }

    /**
     * 公司id
     */
    public void setCompanyId(String companyId){
        this.companyId = companyId;
    }    
    /**
     * 仓库所属省份
     */
    public String getProvince(){
        return this.province;
    }

    /**
     * 仓库所属省份
     */
    public void setProvince(String province){
        this.province = province;
    }    
}