package com.isell.ei.logistics.sf.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * 下订单响应报文
 * @author 一代魔笛
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {}) 
public class SOrderResponse {

	/** 客户订单号 */
	@XmlAttribute
	private String orderid;
	/** 顺丰运单号，一个订单只能有一个母单号，如果是子母单的情况，以半角逗号分隔，主单号在第一个位置，如“755123456789,001123456789,002123456789” ，可用于顺丰电子运单标签打印。 */
	@XmlAttribute
	private String mailno;
	/** 顺丰签回单服务运单号 */
	@XmlAttribute(name="return_tracking_no")
	private String return_tracking_no;
	/** 原寄地区域代码，可用于顺丰电子运单标签打印。 */
	@XmlAttribute
	private String origincode;
	/** 目的地区域代码，可用于顺丰电子运单标签打印。 */
	@XmlAttribute
	private String destcode;
	/** 筛单结果：1：人工确认 2：可收派 3：不可以收派 */
	@XmlAttribute(name="filter_result")
	private String filter_result;
	/** 如果filter_result=3时为必填，不可以收派的原因代码：  1：收方超范围  2：派方超范围  3-：其它原因 */
	@XmlAttribute
	private String remark;
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getMailno() {
		return mailno;
	}
	public void setMailno(String mailno) {
		this.mailno = mailno;
	}
	public String getReturn_tracking_no() {
		return return_tracking_no;
	}
	public void setReturn_tracking_no(String return_tracking_no) {
		this.return_tracking_no = return_tracking_no;
	}
	public String getOrigincode() {
		return origincode;
	}
	public void setOrigincode(String origincode) {
		this.origincode = origincode;
	}
	public String getDestcode() {
		return destcode;
	}
	public void setDestcode(String destcode) {
		this.destcode = destcode;
	}
	public String getFilter_result() {
		return filter_result;
	}
	public void setFilter_result(String filter_result) {
		this.filter_result = filter_result;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
