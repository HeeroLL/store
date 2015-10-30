package com.isell.ei.logistics.youxinda.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 订单信息 创建订单
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-27]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "OrderInfo")
public class OrderInfo {
	
	/**
	 * 订单模型:  0:备货模式  1:集货模式
	 */
	private int orderModel;
	
	/**
	 * 交货仓库 (GZBYWLCK)
	 */
	private String warehouseCode;
	
	/**
	 * 目的仓库
	 */
	private String toWarehouseCode;
	
	/**
	 * 收件人国家
	 */
	private String oabCounty;
	
	/**
	 * 收件人省份
	 */
	private String oabStateName;
	
	/**
	 * 收件人城市
	 */
	private String oabCity;
	
	/**
	 * 收件人区或者县
	 */
	private String oabDistrict;
	
	/**
	 * 运输方式代码 (CNEMS  NEUB   HKSTO  CNAM  YTO   SF)
	 */
	private String smCode;
	
	/**
	 * 交易订单号
	 */
	private String referenceNo;
	
	/**
	 * 
	 */
	private String oabLastname;
	
	/**
	 * 
	 */
	private String oabFirstname;
	
	/**
	 * 收件人姓名
	 */
	private String oabName;
	
	/**
	 * 收件人公司名
	 */
	private String oabCompany;
	
	/**
	 * 收件人邮编
	 */
	private String oabPostcode; 
	
	/**
	 * 收件人地址1(如果地址1 字符串超长,请将地址1 截取成地址1+地址2,把超长的部分存储到地址2)
	 */
	private String oabStreetAddress1;
	
	/**
	 * 收件人地址2
	 */
	private String oabStreetAddress2;
	
	/**
	 * 收件人电话
	 */
	private String oabPhone;
	
	/**
	 * 电子邮件
	 */
	private String oabEmail;
	
	/**
	 * 毛重 单位kg
	 */
	private String grossWt;
	
	/**
	 * 币种 默认RMB
	 */
	private String currencyCode;
	
	/**
	 * 证件类型 1:身份证 2:护照 3:其它
	 */
	private int idType;
	
	/**
	 * 证件号码
	 */
	private String idNumber;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 订单创建时状态   1,草稿;  2,确认;  4,已提交;
	 */
	private int orderStatus;
	
	/**
	 * 运费
	 */
	private Float shippingFeeEstimate;
	
	/**
	 * 发件人国家
	 */
	private String shipperCountry;
	
	/**
	 * 发件人电话
	 */
	private String shipperPhone;
	
	/**
	 * 发件人地址
	 */
	private String shipperAddress;
	
	/**
	 * 订单产品详情
	 */
    @XmlElement(name ="orderProduct")
	private List<ProductDeatil> orderProducts;

	public int getOrderModel() {
		return orderModel;
	}

	public void setOrderModel(int orderModel) {
		this.orderModel = orderModel;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getToWarehouseCode() {
		return toWarehouseCode;
	}

	public void setToWarehouseCode(String toWarehouseCode) {
		this.toWarehouseCode = toWarehouseCode;
	}

	public String getOabCounty() {
		return oabCounty;
	}

	public void setOabCounty(String oabCounty) {
		this.oabCounty = oabCounty;
	}

	public String getOabStateName() {
		return oabStateName;
	}

	public void setOabStateName(String oabStateName) {
		this.oabStateName = oabStateName;
	}

	public String getOabCity() {
		return oabCity;
	}

	public void setOabCity(String oabCity) {
		this.oabCity = oabCity;
	}

	public String getOabDistrict() {
		return oabDistrict;
	}

	public void setOabDistrict(String oabDistrict) {
		this.oabDistrict = oabDistrict;
	}

	public String getSmCode() {
		return smCode;
	}

	public void setSmCode(String smCode) {
		this.smCode = smCode;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getOabLastname() {
		return oabLastname;
	}

	public void setOabLastname(String oabLastname) {
		this.oabLastname = oabLastname;
	}

	public String getOabFirstname() {
		return oabFirstname;
	}

	public void setOabFirstname(String oabFirstname) {
		this.oabFirstname = oabFirstname;
	}

	public String getOabName() {
		return oabName;
	}

	public void setOabName(String oabName) {
		this.oabName = oabName;
	}

	public String getOabCompany() {
		return oabCompany;
	}

	public void setOabCompany(String oabCompany) {
		this.oabCompany = oabCompany;
	}

	public String getOabPostcode() {
		return oabPostcode;
	}

	public void setOabPostcode(String oabPostcode) {
		this.oabPostcode = oabPostcode;
	}

	public String getOabStreetAddress1() {
		return oabStreetAddress1;
	}

	public void setOabStreetAddress1(String oabStreetAddress1) {
		this.oabStreetAddress1 = oabStreetAddress1;
	}

	public String getOabStreetAddress2() {
		return oabStreetAddress2;
	}

	public void setOabStreetAddress2(String oabStreetAddress2) {
		this.oabStreetAddress2 = oabStreetAddress2;
	}

	public String getOabPhone() {
		return oabPhone;
	}

	public void setOabPhone(String oabPhone) {
		this.oabPhone = oabPhone;
	}

	public String getOabEmail() {
		return oabEmail;
	}

	public void setOabEmail(String oabEmail) {
		this.oabEmail = oabEmail;
	}

	public String getGrossWt() {
		return grossWt;
	}

	public void setGrossWt(String grossWt) {
		this.grossWt = grossWt;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public int getIdType() {
		return idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Float getShippingFeeEstimate() {
		return shippingFeeEstimate;
	}

	public void setShippingFeeEstimate(Float shippingFeeEstimate) {
		this.shippingFeeEstimate = shippingFeeEstimate;
	}

	public String getShipperCountry() {
		return shipperCountry;
	}

	public void setShipperCountry(String shipperCountry) {
		this.shipperCountry = shipperCountry;
	}

	public String getShipperPhone() {
		return shipperPhone;
	}

	public void setShipperPhone(String shipperPhone) {
		this.shipperPhone = shipperPhone;
	}

	public String getShipperAddress() {
		return shipperAddress;
	}

	public void setShipperAddress(String shipperAddress) {
		this.shipperAddress = shipperAddress;
	}

	public List<ProductDeatil> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<ProductDeatil> orderProducts) {
		this.orderProducts = orderProducts;
	}

}
