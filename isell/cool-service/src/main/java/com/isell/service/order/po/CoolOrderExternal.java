package com.isell.service.order.po;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/**
 * 订单信息
 * 
 * @author maowejie
 * @version [版本号, 2016-1-21]
 */
public class CoolOrderExternal {
      private Integer id;
      private String locationP;
      private String locationC;
      private String locationA;
      private String address;
      private String linkman;
      private String mobile;
      private String tel;
      private String psfs;
      private String psPrice;
      private Integer zffs;
      private BigDecimal total;
      private Byte state;
      private String orderNo;
      private String remark;
      private String psCode;
      private Date payTime;
      private String tradeNo;
      private String zipcode;
      private Date finishTime;
      private Byte payState;
      private Byte orderType;
      private String bigpen;
      private String idcard;
      private BigDecimal taxPrice;
      private Byte fhfs;
      private Integer refundState;
      private Byte oType;
      private List<CoolOrderItemExternal> items;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLocationP() {
		return locationP;
	}
	public void setLocationP(String locationP) {
		this.locationP = locationP;
	}
	public String getLocationC() {
		return locationC;
	}
	public void setLocationC(String locationC) {
		this.locationC = locationC;
	}
	public String getLocationA() {
		return locationA;
	}
	public void setLocationA(String locationA) {
		this.locationA = locationA;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPsfs() {
		return psfs;
	}
	public void setPsfs(String psfs) {
		this.psfs = psfs;
	}
	public String getPsPrice() {
		return psPrice;
	}
	public void setPsPrice(String psPrice) {
		this.psPrice = psPrice;
	}
	public Integer getZffs() {
		return zffs;
	}
	public void setZffs(Integer zffs) {
		this.zffs = zffs;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public Byte getState() {
		return state;
	}
	public void setState(Byte state) {
		this.state = state;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPsCode() {
		return psCode;
	}
	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public Byte getPayState() {
		return payState;
	}
	public void setPayState(Byte payState) {
		this.payState = payState;
	}
	public Byte getOrderType() {
		return orderType;
	}
	public void setOrderType(Byte orderType) {
		this.orderType = orderType;
	}
	public String getBigpen() {
		return bigpen;
	}
	public void setBigpen(String bigpen) {
		this.bigpen = bigpen;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public BigDecimal getTaxPrice() {
		return taxPrice;
	}
	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}
	public Byte getFhfs() {
		return fhfs;
	}
	public void setFhfs(Byte fhfs) {
		this.fhfs = fhfs;
	}
	public Integer getRefundState() {
		return refundState;
	}
	public void setRefundState(Integer refundState) {
		this.refundState = refundState;
	}
	public Byte getoType() {
		return oType;
	}
	public void setoType(Byte oType) {
		this.oType = oType;
	}
	public List<CoolOrderItemExternal> getItems() {
		return items;
	}
	public void setItems(List<CoolOrderItemExternal> items) {
		this.items = items;
	}
      
}
