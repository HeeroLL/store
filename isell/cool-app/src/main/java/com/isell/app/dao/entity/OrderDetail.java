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
	private String address;//详细地址
	private String location_p;//省份
	private String location_c;//城市
	private String location_a;//区县
	private String comments;//备注
	
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
	private String createtime;//创建时间
	private String pscode;//物流编号
	private String mobile;
	private List<OrderGoods>orderGoods;
	private String psfs;//邮寄方式
	private int state;//
	private String order_no;//订单号
	private String trade_no;//交易编号
	private String supplier;//店铺id
	private String idcard;//身份证号
	private String source;// 账单来源
	private int arrears; // 是否欠费
	
	
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getLocation_p() {
		return location_p;
	}
	public void setLocation_p(String location_p) {
		this.location_p = location_p;
	}
	public String getLocation_c() {
		return location_c;
	}
	public void setLocation_c(String location_c) {
		this.location_c = location_c;
	}
	public String getLocation_a() {
		return location_a;
	}
	public void setLocation_a(String location_a) {
		this.location_a = location_a;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getPsfs() {
		if(psfs==null)
		{
			psfs="";
		}
		return psfs;
	}
	public void setPsfs(String psfs) {
		this.psfs = psfs;
	}
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
		if(pscode==null)
		{
			pscode="";
		}
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
		if(paytype==null)
		{
			paytype="";
		}
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public String getPosttype() {
		if(posttype==null)
		{
			posttype="";
		}
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
		if(paytime==null)
		{
			paytime="";
		}
		if("0000-00-00 00:00:00".equals(paytime))
		{
			paytime="";
		}
		return paytime;
	}
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getArrears() {
		return arrears;
	}
	public void setArrears(int arrears) {
		this.arrears = arrears;
	}
}
