package com.isell.task.product.vo;

import java.util.Date;

/**
 * 保税仓库存查询配置类
 *
 * @author 
 */
public class WhProductCustomsStockCon{
    /**
     * 
     */
    private Integer id;
    /**
     * 保税仓 1：宁波优贝  2：宁波艾购 3:郑州
     */
    private String customsType;
    /**
     * 最新的获取库存的时间
     */
    private Date updatetime;

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
     * 保税仓 1：宁波优贝  2：宁波艾购 3:郑州
     */
    public String getCustomsType(){
        return this.customsType;
    }

    /**
     * 保税仓 1：宁波优贝  2：宁波艾购 3:郑州
     */
    public void setCustomsType(String customsType){
        this.customsType = customsType;
    }    
    /**
     * 最新的获取库存的时间
     */
    public Date getUpdatetime(){
        return this.updatetime;
    }

    /**
     * 最新的获取库存的时间
     */
    public void setUpdatetime(Date updatetime){
        this.updatetime = updatetime;
    }    
}