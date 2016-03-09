package com.isell.app.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class CenterOrder implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3772922997256154090L;
	private String shopid;
	private String shopcode;
	private String shopname;
	private int orderid;
	private String order_no;
	private String shopimg;
	private String paytime;
	private String total;
	private String postfee;
	private int state;
	private String imgdomain;
	private Integer refundState;
	private String createtime;
	
	
	
	public String getCreatetime() {
		if(createtime==null)
		{
			createtime="";
		}
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public Integer getRefundState() {
		return refundState;
	}
	public void setRefundState(Integer refundState) {
		this.refundState = refundState;
	}
	private List<CenterOrderItem>orderItemlist;
	private String taxfee;
	
	
	public String getTaxfee() {
		if(taxfee==null)
		{
			taxfee="0";
		}
		return taxfee;
	}
	public void setTaxfee(String taxfee) {
		this.taxfee = taxfee;
	}
	public String getImgdomain() {
		return imgdomain;
	}
	public void setImgdomain(String imgdomain) {
		this.imgdomain = imgdomain;
	}
	public List<CenterOrderItem> getOrderItemlist() {
		return orderItemlist;
	}
	public void setOrderItemlist(List<CenterOrderItem> orderItemlist) {
		this.orderItemlist = orderItemlist;
	}
	public String getPaytime() {
		if(paytime==null)
		{
			paytime="";
		}
		return paytime;
	}
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	 
	public String getTotal() {
		if(total==null)
		{
			total="0";
		}
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	 
	 
	public String getPostfee() {
		if(postfee==null)
		{
			postfee="0";
		}
		return postfee;
	}
	public void setPostfee(String postfee) {
		this.postfee = postfee;
	}
	public String getOrder_no() {
		if(order_no==null)
		{
			order_no="";
		}
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getShopimg() {
		return shopimg;
	}
	public void setShopimg(String shopimg) {
		this.shopimg = shopimg;
	}
	 
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getShopid() {
		return shopid;
	}
	public void setShopid(String shopid) {
		this.shopid = shopid;
	}
	 
	public String getShopcode() {
		return shopcode;
	}
	public void setShopcode(String shopcode) {
		this.shopcode = shopcode;
	}
	 
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	 
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	
}
