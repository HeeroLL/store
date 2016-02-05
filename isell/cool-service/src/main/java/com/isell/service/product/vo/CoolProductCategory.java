package com.isell.service.product.vo;

/**
 * 
 * 商品分类VO
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-17]
 */
public class CoolProductCategory{
    /**
     * 
     */
    private Integer id;
    /**
     * 
     */
    private String name;
    /**
     * 
     */
    private String remark;
    /**
     * 
     */
    private Integer orderby;
    /**
     * 
     */
    private Integer parentId;
    /**
     * 
     */
    private String parents;
    /**
     * 
     */
    private Boolean delFlag;
    /**
     * 
     */
    private String code;
    /**
     * 
     */
    private String tag;

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
    public String getName(){
        return this.name;
    }

    /**
     * 
     */
    public void setName(String name){
        this.name = name;
    }    
    /**
     * 
     */
    public String getRemark(){
        return this.remark;
    }

    /**
     * 
     */
    public void setRemark(String remark){
        this.remark = remark;
    }    
    /**
     * 
     */
    public Integer getOrderby(){
        return this.orderby;
    }

    /**
     * 
     */
    public void setOrderby(Integer orderby){
        this.orderby = orderby;
    }    
    /**
     * 
     */
    public Integer getParentId(){
        return this.parentId;
    }

    /**
     * 
     */
    public void setParentId(Integer parentId){
        this.parentId = parentId;
    }    
    /**
     * 
     */
    public String getParents(){
        return this.parents;
    }

    /**
     * 
     */
    public void setParents(String parents){
        this.parents = parents;
    }    
    /**
     * 
     */
    public Boolean getDelFlag(){
        return this.delFlag;
    }

    /**
     * 
     */
    public void setDelFlag(Boolean delFlag){
        this.delFlag = delFlag;
    }    
    /**
     * 
     */
    public String getCode(){
        return this.code;
    }

    /**
     * 
     */
    public void setCode(String code){
        this.code = code;
    }    
    /**
     * 
     */
    public String getTag(){
        return this.tag;
    }

    /**
     * 
     */
    public void setTag(String tag){
        this.tag = tag;
    }    
}