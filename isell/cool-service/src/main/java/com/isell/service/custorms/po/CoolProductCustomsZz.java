package com.isell.service.custorms.po;

/**
 * 郑州商品海关备案信息
 * 
 * @author lilin
 * @version [版本号, 2015年12月10日]
 */
public class CoolProductCustomsZz {
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
     * 关联 H2010 项号
     */
    private String gno;
    
    /**
     * 行邮税号
     */
    private String taxId;
    
    /**
     * HS编码
     */
    private String barcode;
    
    /**
     * 计量单位（海）
     */
    private String unit;
    
    /**
     * 计量单位（检）
     */
    private String unitinsp;
    
    /**
     * 海关备案商品编号
     */
    private String itemno;
    
    /**
     * 商品上架品名
     */
    private String shelfgoodsname;
    
    /**
     * 商品货号
     */
    private String goodId;
    
    /**
     * 型号规格
     */
    private String specifications;
    
    /**
     * 检验检疫商品备案编号
     */
    private String goodIdinsp;
    
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
     * 关联 H2010 项号
     */
    public String getGno() {
        return this.gno;
    }
    
    /**
     * 关联 H2010 项号
     */
    public void setGno(String gno) {
        this.gno = gno;
    }
    
    /**
     * 行邮税号
     */
    public String getTaxId() {
        return this.taxId;
    }
    
    /**
     * 行邮税号
     */
    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }
    
    /**
     * HS编码
     */
    public String getBarcode() {
        return this.barcode;
    }
    
    /**
     * HS编码
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    
    /**
     * 计量单位（海）
     */
    public String getUnit() {
        return this.unit;
    }
    
    /**
     * 计量单位（海）
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    /**
     * 计量单位（检）
     */
    public String getUnitinsp() {
        return this.unitinsp;
    }
    
    /**
     * 计量单位（检）
     */
    public void setUnitinsp(String unitinsp) {
        this.unitinsp = unitinsp;
    }
    
    /**
     * 海关备案商品编号
     */
    public String getItemno() {
        return this.itemno;
    }
    
    /**
     * 海关备案商品编号
     */
    public void setItemno(String itemno) {
        this.itemno = itemno;
    }
    
    /**
     * 商品上架品名
     */
    public String getShelfgoodsname() {
        return this.shelfgoodsname;
    }
    
    /**
     * 商品上架品名
     */
    public void setShelfgoodsname(String shelfgoodsname) {
        this.shelfgoodsname = shelfgoodsname;
    }
    
    /**
     * 商品货号
     */
    public String getGoodId() {
        return this.goodId;
    }
    
    /**
     * 商品货号
     */
    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }
    
    /**
     * 型号规格
     */
    public String getSpecifications() {
        return this.specifications;
    }
    
    /**
     * 型号规格
     */
    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }
    
    /**
     * 检验检疫商品备案编号
     */
    public String getGoodIdinsp() {
        return this.goodIdinsp;
    }
    
    /**
     * 检验检疫商品备案编号
     */
    public void setGoodIdinsp(String goodIdinsp) {
        this.goodIdinsp = goodIdinsp;
    }
}