package com.isell.service.shop.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 酷店合伙人佣金vo
 * 
 * @author wangpegn
 * @version [版本号, 2015-10-21]
 */
public class CoonShopPartner{
    /**
     * 主键id
     */
    private String id;
    /**
     * 订单id
     */
    private Integer oId;
    /**
     * 店铺ID
     */
    private String supplier;
    /**
     * 合伙人（店铺）id
     */
    private String partnerId;
    /**
     * 合伙人（店铺）名称
     */
    private String partnerName;
    /**
     * 推荐获得佣金
     */
    private BigDecimal partnerAmount;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 订单金额
     */
    private BigDecimal orderAmount;

    /**
     * 主键id
     */
    public String getId(){
        return this.id;
    }

    /**
     * 主键id
     */
    public void setId(String id){
        this.id = id;
    }    
    /**
     * 订单id
     */
    public Integer getoId(){
        return this.oId;
    }

    /**
     * 订单id
     */
    public void setoId(Integer oId){
        this.oId = oId;
    }    
    /**
     * 店铺ID
     */
    public String getSupplier(){
        return this.supplier;
    }

    /**
     * 店铺ID
     */
    public void setSupplier(String supplier){
        this.supplier = supplier;
    }    
    /**
     * 合伙人（店铺）id
     */
    public String getPartnerId(){
        return this.partnerId;
    }

    /**
     * 合伙人（店铺）id
     */
    public void setPartnerId(String partnerId){
        this.partnerId = partnerId;
    }    
    /**
     * 合伙人（店铺）名称
     */
    public String getPartnerName(){
        return this.partnerName;
    }

    /**
     * 合伙人（店铺）名称
     */
    public void setPartnerName(String partnerName){
        this.partnerName = partnerName;
    }    
    /**
     * 推荐获得佣金
     */
    public BigDecimal getPartnerAmount(){
        return this.partnerAmount;
    }

    /**
     * 推荐获得佣金
     */
    public void setPartnerAmount(BigDecimal partnerAmount){
        this.partnerAmount = partnerAmount;
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

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}    
}