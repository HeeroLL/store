package com.isell.ei.logistics.dtw.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import org.codehaus.jackson.annotate.JsonProperty;
/**
 * 
 * @author Maoweijie
 * @version [版本号, 2015年12月2日]
 */
public class GainsRequest {
	
	@JsonProperty("PassKey")
	private String key = "58FBC5BB-A8C4-47ED-9FDC-151F4E279B92";
	/*电商企业收货订单号\采购订单号*/
	
	/*电商企业收货订单号\采购订单号*/
	@JsonProperty("Msgid")
	private String msgid;
	/*供应商编码（电商平台下的商家备案编号）*/
	@JsonProperty("Supplier")
	private String supplier;
	/*电商企业编码(电商企业在跨境平台备案编码)*/
	@JsonProperty("eCommerceCode")
	private String eCommerceCode;
	/*电商企业名称*/
	@JsonProperty("eCommerceName")
	private String eCommerceName;
	/*提单号*/
	@JsonProperty("Hawb")
	private String hawb;
	/*主运单号*/
	@JsonProperty("Mawb")
	private String mawb;
	
	@XmlElement(name="items")
	private List<GainsItem> items;
	
	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String geteCommerceCode() {
		return eCommerceCode;
	}

	public void seteCommerceCode(String eCommerceCode) {
		this.eCommerceCode = eCommerceCode;
	}

	public String geteCommerceName() {
		return eCommerceName;
	}

	public void seteCommerceName(String eCommerceName) {
		this.eCommerceName = eCommerceName;
	}

	public String getHawb() {
		return hawb;
	}

	public void setHawb(String hawb) {
		this.hawb = hawb;
	}

	public String getMawb() {
		return mawb;
	}

	public void setMawb(String mawb) {
		this.mawb = mawb;
	}

	public List<GainsItem> getItems() {
		return items;
	}

	public void setItems(List<GainsItem> items) {
		this.items = items;
	}
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}



	
	
}
