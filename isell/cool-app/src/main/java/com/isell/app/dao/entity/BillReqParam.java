package com.isell.app.dao.entity;

import java.io.Serializable;

/**
 * 账单请求参数定义
 * 
 * @author Administrator
 *
 */
public class BillReqParam implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String tradeNo; // 流水号
	private int totalMin; // 账单金额下限
	private int totalMax; // 账单金额上限
	private String begintime; // 开始时间
	private String endtime; // 结束时间
	private int start = 0; // 开始条数
	private int limit = 10; // 每次最大条数
	private int userid; // 用户ID

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public int getTotalMin() {
		return totalMin;
	}

	public void setTotalMin(int totalMin) {
		this.totalMin = totalMin;
	}

	public int getTotalMax() {
		return totalMax;
	}

	public void setTotalMax(int totalMax) {
		this.totalMax = totalMax;
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

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
}
