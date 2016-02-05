package com.isell.task.product.vo;
/**
 * 保税仓库存类
 *
 * @author 
 */
public class WhProductCustomsStock{
	
	/**
	 * 保税仓 1：宁波优贝
	 */
	public static final String CUSTOMS_TYPE_1 = "1";
	
	/**
	 * 保税仓 2：宁波艾购
	 */
	public static final String CUSTOMS_TYPE_2 = "2";
	
	/**
	 * 保税仓 3:郑州
	 */
	public static final String CUSTOMS_TYPE_3 = "3";
	
    /**
     * 
     */
    private Integer id;
    /**
     * 保税仓 1：宁波优贝  2：宁波艾购 3:郑州
     */
    private String customsType;
    /**
     * 商品主键
     */
    private Integer pId;
    /**
     * 规格主键
     */
    private Integer gId;
    /**
     * 商品编码
     */
    private String productCode;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 库存
     */
    private Integer stock;

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
     * 商品主键
     */
    public Integer getpId(){
        return this.pId;
    }

    /**
     * 商品主键
     */
    public void setpId(Integer pId){
        this.pId = pId;
    }    
    /**
     * 规格主键
     */
    public Integer getgId(){
        return this.gId;
    }

    /**
     * 规格主键
     */
    public void setgId(Integer gId){
        this.gId = gId;
    }    
    /**
     * 商品编码
     */
    public String getProductCode(){
        return this.productCode;
    }

    /**
     * 商品编码
     */
    public void setProductCode(String productCode){
        this.productCode = productCode;
    }    
    /**
     * 商品名称
     */
    public String getProductName(){
        return this.productName;
    }

    /**
     * 商品名称
     */
    public void setProductName(String productName){
        this.productName = productName;
    }    
    /**
     * 库存
     */
    public Integer getStock(){
        return this.stock;
    }

    /**
     * 库存
     */
    public void setStock(Integer stock){
        this.stock = stock;
    }    
}