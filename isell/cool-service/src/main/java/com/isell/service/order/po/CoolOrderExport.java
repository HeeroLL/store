package com.isell.service.order.po;

import java.math.BigDecimal;

/**
 * 订单导出类
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-05]
 */
public class CoolOrderExport {
	
	/**
     * 订单编号
     */
    private String orderNo;
    
    /**
     * 订单状态：0：待支付/1：待发货/2：待收货/3：待评价/4：已完成/5：已退款/99：已取消
     */
    private Byte state;
    
    /**
     * 订单状态(显示值)
     */
    private String stateShow;
    
    /**
     * 商品名
     */
    private String name;
    
    /**
     * 订购数量
     */
    private Integer count;
    
    /**
     * 订购单价
     */
    private BigDecimal price;
    
    /**
     * 酷店id
     */
    private String supplier;
    
    /**
     * 酷店名称
     */
    private String supName;
    
    /**
     * 快递公司运单号
     */
    private String psCode;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}

	public String getStateShow() {
		return stateShow;
	}

	public void setStateShow(String stateShow) {
		this.stateShow = stateShow;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getSupName() {
		return supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}

	public String getPsCode() {
		return psCode;
	}

	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}
}
