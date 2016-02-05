package com.isell.app.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderParam implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8899197549392338960L;
	private int receiveid;//收货地址id
	//收货地址
	private String province;
	private String city;
	private String region;
	private String address;
	private String postcode;
	private String linkname;
	private String phone;
	private int userid;
	private int memberid;
	private String idcard;
	
	private int goodid;
	private int ggid;
	private String shopCode;
	private String shopid;
	
	private int paytype;
	private int ordertype;
	private String remark;
	private int quality;
	private String orderseq;
	
	//
	private int isFreePost;
	private BigDecimal total;
	private String goodname;
	private String comment;
	private String imgdomain;
	private String order_limit;
	
	
	
	public String getOrder_limit() {
		return order_limit;
	}
	public void setOrder_limit(String order_limit) {
		this.order_limit = order_limit;
	}
	public String getImgdomain() {
		return imgdomain;
	}
	public void setImgdomain(String imgdomain) {
		this.imgdomain = imgdomain;
	}
	public String getShopCode() {
		return shopCode;
	}
	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}
	public String getGoodname() {
		return goodname;
	}
	public void setGoodname(String goodname) {
		this.goodname = goodname;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getIsFreePost() {
		return isFreePost;
	}
	public void setIsFreePost(int isFreePost) {
		this.isFreePost = isFreePost;
	}
	 
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getOrderseq() {
		return orderseq;
	}
	public void setOrderseq(String orderseq) {
		this.orderseq = orderseq;
	}
	public int getGoodid() {
		return goodid;
	}
	public void setGoodid(int goodid) {
		this.goodid = goodid;
	}
	public int getGgid() {
		return ggid;
	}
	public void setGgid(int ggid) {
		this.ggid = ggid;
	}
	public String getShopid() {
		return shopid;
	}
	public void setShopid(String shopid) {
		this.shopid = shopid;
	}
	public int getPaytype() {
		return paytype;
	}
	public void setPaytype(int paytype) {
		this.paytype = paytype;
	}
	public int getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(int ordertype) {
		this.ordertype = ordertype;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}
	public int getReceiveid() {
		return receiveid;
	}
	public void setReceiveid(int receiveid) {
		this.receiveid = receiveid;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getLinkname() {
		return linkname;
	}
	public void setLinkname(String linkname) {
		this.linkname = linkname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	
	
	
}
