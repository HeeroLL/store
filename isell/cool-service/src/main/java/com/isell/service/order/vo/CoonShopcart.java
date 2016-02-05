package com.isell.service.order.vo;

/**
 * 购物车表VO
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-01]
 */
public class CoonShopcart{
    /**
     * 
     */
    private String id;
    /**
     * 
     */
    private String userId;
    /**
     * 
     */
    private String pId;
    /**
     * 
     */
    private String gId;
    /**
     * 
     */
    private Integer quantity;
    /**
     * 
     */
    private String sId;
    
    /**
     * 订单主键，以","分隔
     */
    private String ids;
    
    /**
     * 商品名称
     */
    private String pName;

    /**
     * 
     */
    public String getId(){
        return this.id;
    }

    /**
     * 
     */
    public void setId(String id){
        this.id = id;
    }    
    /**
     * 
     */
    public String getUserId(){
        return this.userId;
    }

    /**
     * 
     */
    public void setUserId(String userId){
        this.userId = userId;
    }    
    /**
     * 
     */
    public String getpId(){
        return this.pId;
    }

    /**
     * 
     */
    public void setpId(String pId){
        this.pId = pId;
    }    
    /**
     * 
     */
    public String getgId(){
        return this.gId;
    }

    /**
     * 
     */
    public void setgId(String gId){
        this.gId = gId;
    }    
    /**
     * 
     */
    public Integer getQuantity(){
        return this.quantity;
    }

    /**
     * 
     */
    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }    
    /**
     * 
     */
    public String getsId(){
        return this.sId;
    }

    /**
     * 
     */
    public void setsId(String sId){
        this.sId = sId;
    }

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}    
}