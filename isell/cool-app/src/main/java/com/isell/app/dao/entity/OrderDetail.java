package com.isell.app.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class OrderDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4665593308826922933L;
	private String  orderno;
	private List<Logistics>loglist;
	
	private String linkman;
	private String phone;
	private String address;
	/*private String goodimg;
	private String goodname;
	private String gname;*/
	private BigDecimal price;
	private String paytype;//支付方式
	private String posttype;//配送方式
	private BigDecimal total;//总付款
	private BigDecimal postfee;//邮费
	private BigDecimal goodsfee;//商品总额
	private String paytime;//支付时间
	private String pscode;//物流编号
	private String mobile;
	private List<OrderGoods>orderGoods;
	
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public List<OrderGoods> getOrderGoods() {
		return orderGoods;
	}
	public void setOrderGoods(List<OrderGoods> orderGoods) {
		this.orderGoods = orderGoods;
	}
	public String getPscode() {
		return pscode;
	}
	public void setPscode(String pscode) {
		this.pscode = pscode;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public List<Logistics> getLoglist() {
		return loglist;
	}
	public void setLoglist(List<Logistics> loglist) {
		this.loglist = loglist;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	 
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public String getPosttype() {
		return posttype;
	}
	public void setPosttype(String posttype) {
		this.posttype = posttype;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getPostfee() {
		return postfee;
	}
	public void setPostfee(BigDecimal postfee) {
		this.postfee = postfee;
	}
	public BigDecimal getGoodsfee() {
		return goodsfee;
	}
	public void setGoodsfee(BigDecimal goodsfee) {
		this.goodsfee = goodsfee;
	}
	public String getPaytime() {
		return paytime;
	}
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	
	
	
}
