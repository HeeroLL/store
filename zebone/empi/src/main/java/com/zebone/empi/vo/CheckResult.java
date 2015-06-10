package com.zebone.empi.vo;

import java.util.List;

/**
 * 校验结果
 * @author YinCm
 * @version 2013-8-13 下午10:15:20
 */
public class CheckResult {
	
	//结果代码
	private int resultCode;
	//需要更新的residentCardList(未被他人注册过的card)
	private List<ResidentCard> residentCardListUpdate;
	//需要插入的residentCardList(未被他人注册过的card)
	private List<ResidentCard> residentCardListInsert;
	
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public List<ResidentCard> getResidentCardListUpdate() {
		return residentCardListUpdate;
	}
	public void setResidentCardListUpdate(List<ResidentCard> residentCardListUpdate) {
		this.residentCardListUpdate = residentCardListUpdate;
	}
	public List<ResidentCard> getResidentCardListInsert() {
		return residentCardListInsert;
	}
	public void setResidentCardListInsert(List<ResidentCard> residentCardListInsert) {
		this.residentCardListInsert = residentCardListInsert;
	}
	
	
}
