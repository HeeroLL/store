package com.isell.service.custorms.po;

/**
 * 宁波优贝商品海关备案信息
 * 
 * @author lilin
 * @version [版本号, 2015年12月17日]
 */
public class CoolProductCustomsNbyb {
    /**
     * 主键id
     */
    private Integer id;
    
    /**
     * 商品id
     */
    private Integer pId;
    
    /**
     * 规格id
     */
    private Integer gId;
    
    /**
     * 商品海关编码
     */
    private String productCode;
    
    /**
     * 商品名称
     */
    private String productName;
    
    /**
     * 单位
     */
    private String unit;
    
    /**
     * 主键id
     */
    public Integer getId() {
        return this.id;
    }
    
    /**
     * 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * 商品id
     */
    public Integer getpId() {
        return this.pId;
    }
    
    /**
     * 商品id
     */
    public void setpId(Integer pId) {
        this.pId = pId;
    }
    
    /**
     * 规格id
     */
    public Integer getgId() {
        return this.gId;
    }
    
    /**
     * 规格id
     */
    public void setgId(Integer gId) {
        this.gId = gId;
    }
    
    /**
     * 商品海关编码
     */
    public String getProductCode() {
        return this.productCode;
    }
    
    /**
     * 商品海关编码
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    
    /**
     * 商品名称
     */
    public String getProductName() {
        return this.productName;
    }
    
    /**
     * 商品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    /**
     * 单位
     */
    public String getUnit() {
        return this.unit;
    }
    
    /**
     * 单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }
}