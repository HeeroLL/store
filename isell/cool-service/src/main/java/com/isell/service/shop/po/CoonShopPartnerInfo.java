package com.isell.service.shop.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 *  合伙人奖励
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-30]
 */
public class CoonShopPartnerInfo {
	
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
     * 订单数量
     */
    private Integer count;
    
    /**
     * 订单号
     */
    private String orderNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getoId() {
		return oId;
	}

	public void setoId(Integer oId) {
		this.oId = oId;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public BigDecimal getPartnerAmount() {
		return partnerAmount;
	}

	public void setPartnerAmount(BigDecimal partnerAmount) {
		this.partnerAmount = partnerAmount;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}
