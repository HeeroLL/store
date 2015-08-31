package com.isell.ei.logistics.sf.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {}) 
public class SCargo {

	/** 货物名称，如果需要生成电子运单，则为必填。 */
	@XmlAttribute
	private String name;
	/** 货物数量 跨境件报关需要填写 */
	@XmlAttribute
	private int count;
	/** 货物单位，如：个、台、本，跨境件报关需要填写。 */
	@XmlAttribute
	private String unit;
	/** 订单货物单位重量，包含子母件，单位千克，精确到小数点后3位跨境件报关需要填写。 */
	@XmlAttribute
	private String weight;
	/** 货物单价，精确到小数点后3位，跨境件报关需要填写。*/
	@XmlAttribute
	private String amount;
	/** 货物单价的币别 */
	@XmlAttribute
	private String currency = "CNY";
	/** 原产地国别，跨境件报关需要填写。 */
	@XmlAttribute(name="source_area")
	private String source_area;
	/** 货物产品国检备案编号 */
	@XmlAttribute(name="product_record_no")
	private String product_record_no;
	/** 商品海关备案号 */
	@XmlAttribute(name="good_prepard_no")
	private String good_prepard_no;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getSource_area() {
		return source_area;
	}
	public void setSource_area(String source_area) {
		this.source_area = source_area;
	}
	public String getProduct_record_no() {
		return product_record_no;
	}
	public void setProduct_record_no(String product_record_no) {
		this.product_record_no = product_record_no;
	}
	public String getGood_prepard_no() {
		return good_prepard_no;
	}
	public void setGood_prepard_no(String good_prepard_no) {
		this.good_prepard_no = good_prepard_no;
	}
	
}
