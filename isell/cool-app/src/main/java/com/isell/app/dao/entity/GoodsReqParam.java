package com.isell.app.dao.entity;

import java.io.Serializable;

/**
 * 商品请求的所有参数定义
 * 
 * @author meson
 *
 */
public class GoodsReqParam implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2507987549598794385L;

	private String goodsName; // 商品名称
	private String begintime; // 开始时间
	private String endtime; // 结束时间
	private int userid; // 用户ID
	private int limit = 15; // 每次获取最大的
	private int start = 0; // 开始获取的条数
	private int orderByColumnType; // 0:金额1:排序
	private int orderByType; // 0:正序1:倒序
	private String imgDomain; // 图片的domain地址 
	private boolean filterGoodsName = true; // 是否过滤商品名称
	private boolean filterPayTime = true; // 是否过滤支付时间

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getOrderByColumnType() {
		return orderByColumnType;
	}

	public void setOrderByColumnType(int orderByColumnType) {
		this.orderByColumnType = orderByColumnType;
	}

	public int getOrderByType() {
		return orderByType;
	}

	public void setOrderByType(int orderByType) {
		this.orderByType = orderByType;
	}

	public String getImgDomain() {
		return imgDomain;
	}

	public void setImgDomain(String imgDomain) {
		this.imgDomain = imgDomain;
	}

	public boolean isFilterGoodsName() {
		return filterGoodsName;
	}

	public void setFilterGoodsName(boolean filterGoodsName) {
		this.filterGoodsName = filterGoodsName;
	}

	public boolean isFilterPayTime() {
		return filterPayTime;
	}

	public void setFilterPayTime(boolean filterPayTime) {
		this.filterPayTime = filterPayTime;
	}
}
