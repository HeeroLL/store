package com.isell.app.dao.entity;

public class OrderRecv {
	private int mId;
	private String content;
	private int score;//服务态度评分
	private int g_id;//商品id
	private int score_p;//描述相符评分
	private int score_b;//发货速度
	private int gg_id;
	private String shopid;
	private int o_id;//订单id
	private String order_no;
	
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public int getO_id() {
		return o_id;
	}
	public void setO_id(int o_id) {
		this.o_id = o_id;
	}
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getG_id() {
		return g_id;
	}
	public void setG_id(int g_id) {
		this.g_id = g_id;
	}
	public int getScore_p() {
		return score_p;
	}
	public void setScore_p(int score_p) {
		this.score_p = score_p;
	}
	public int getScore_b() {
		return score_b;
	}
	public void setScore_b(int score_b) {
		this.score_b = score_b;
	}
	public int getGg_id() {
		return gg_id;
	}
	public void setGg_id(int gg_id) {
		this.gg_id = gg_id;
	}
	public String getShopid() {
		return shopid;
	}
	public void setShopid(String shopid) {
		this.shopid = shopid;
	}
	
	
	
}
