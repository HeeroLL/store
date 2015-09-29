package com.isell.ei.logistics.sf.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 订单主体
 * @author 一代魔笛
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {}) 
public class SOrder {

	/** 客户订单号 */
	@XmlAttribute
	private String orderid;
	/** 顺丰运单号，一个订单只能有一个母单号，如果是子母单的情况，以半角逗号分隔，主单号在第一个位置，如“755123456789,001123456789,002123456789”，对于路由推送注册，此字段为必填。 */
	@XmlAttribute
	private String mailno;
	/** 是否要求返回顺丰运单号：  1：要求  其它为不要求 */
	@XmlAttribute(name="is_gen_bill_no")
	private int is_gen_bill_no;
	/** 寄件方公司名称，如果需要生成电子运单，则为必填。 */
	@XmlAttribute(name="j_company")
	private String j_company;
	/** 寄件方联系人，如果需要生成电子运单，则为必填。 */
	@XmlAttribute(name="j_contact")
	private String j_contact;
	/** 寄件方联系电话，如果需要生成电子运单，则为必填。 */
	@XmlAttribute(name="j_tel")
	private String j_tel;
	/** 寄件方手机 */
	@XmlAttribute(name="j_mobile")
	private String j_mobile;
	/** 寄件方国家/城市代码，如果是跨境件，则此字段为必填。 */
	@XmlAttribute(name="j_shippercode")
	private String j_shippercode;
	/** 寄方国家 */
	@XmlAttribute(name="j_country")
	private String j_country;
	/** 寄件方所在省份 字段填写要求：必须是标准的省名称称谓 如：广东省，如果是直辖市，请直接传北京、上海等。 */
	@XmlAttribute(name="j_province")
	private String j_province;
	/** 寄件方所在城市名称，字段填写要求：必须是标准的城市称谓 如：深圳市。 */
	@XmlAttribute(name="j_city")
	private String j_city;
	/** 寄件人所在县/区，必须是标准的县/区称谓，示例：“福田区”。 */
	@XmlAttribute(name="j_county")
	private String j_county;
	/** 寄件方详细地址，包括省市区，示例：“广东省深圳市福田区新洲十一街万基商务大厦10楼” ，如果需要生成电子运单，则为必填。 */
	@XmlAttribute(name="j_address")
	private String j_address;
	/** 寄方邮编，跨境件必填（中国大陆，港澳台互寄除外）。 */
	@XmlAttribute(name="j_post_code")
	private String j_post_code;
	/** 到件方公司名称 */
	@XmlAttribute(name="d_company")
	private String d_company;
	/** 到件方联系人 */
	@XmlAttribute(name="d_contact")
	private String d_contact;
	/** 到件方联系电话 */
	@XmlAttribute(name="d_tel")
	private String d_tel;
	/** 到件方手机 */
	@XmlAttribute(name="d_mobile")
	private String d_mobile;
	/** 到件方代码，如果是跨境件，则要传这个字段，用于表示到方国家的城市。如果此国家整体是以代理商来提供服务的，则此字段可能需要传国家编码。具体商务沟通中双方约定。 */
	@XmlAttribute(name="d_deliverycode")
	private String d_deliverycode;
	/** 到方国家 */
	@XmlAttribute(name="d_country")
	private String d_country;
	/** 到件方所在省份，必须是标准的省名称称谓 如：广东省，如果是直辖市，请直接传北京、上海等。如果此字段与d_city字段都有值，BSP则直接使用这两个值而不是通过对d_address进行地址识别获取。为避免地址识别不成功的风险，建议传输此字段。 */
	@XmlAttribute(name="d_province")
	private String d_province;
	/** 到件方所在城市名称，必须是标准的城市称谓 如：深圳市，如果是直辖市，请直接传北京（或北京市）、上海（或上海市）等。如果此字段与d_province字段都有值，BSP则直接使用这两个值而不是对d_address进行地址识别获取。为避免地址识别不成功的风险，建议传输此字段。 */
	@XmlAttribute(name="d_city")
	private String d_city;
	/** 到件方所在县/区，必须是标准的县/区称谓，示例：“福田区”。 */
	@XmlAttribute(name="d_county")
	private String d_county;
	/** 到件方详细地址，如果不传输d_province/d_city字段，此详细地址需包含省市信息，以提高地址识别的成功率，示例：“广东省深圳市福田区新洲十一街万基商务大厦10楼”。 */
	@XmlAttribute(name="d_address")
	private String d_address;
	/** 到方邮编，跨境件必填（中国大陆，港澳台互寄除外）。 */
	@XmlAttribute(name="d_post_code")
	private String d_post_code;
	/** 顺丰月结卡号 */
	@XmlAttribute(name="custid")
	private String custid;
	/** 付款方式：  1:寄方付  2:收方付  3:第三方付 */
	@XmlAttribute(name="pay_method")
	private int pay_method;
	/** 快件产品类别 */
	@XmlAttribute(name="express_type")
	private String express_type;
	/** 包裹数，一个包裹对应一个运单号，如果是大于1个包裹，则返回则按照子母件的方式返回母运单号和子运单号。 */
	@XmlAttribute(name="parcel_quantity")
	private int parcel_quantity;
	/** 客户订单货物总长，单位厘米，精确到小数点后3位，包含子母件。 */
	@XmlAttribute(name="cargo_length")
	private String cargo_length;
	/** 客户订单货物总宽，单位厘米，精确到小数点后3位，包含子母件。 */
	@XmlAttribute(name="cargo_width")
	private String cargo_width;
	/** 客户订单货物总高，单位厘米，精确到小数点后3位，包含子母件。 */
	@XmlAttribute(name="cargo_height")
	private String cargo_height;
	/** 订单货物总体积，单位立方厘米，精确到小数点后3位，会用于计抛（是否计抛具体商务沟通中双方约定）。 */
	@XmlAttribute(name="volume")
	private String volume;
	/** 订单货物总重量，包含子母件，单位千克，精确到小数点后3位，如果提供此值，必须>0 。 */
	@XmlAttribute(name="cargo_total_weight")
	private String cargo_total_weight;
	/** 客户订单货物总声明价值，包含子母件，精确到小数点后3位。如果是跨境件，则必填。 */
	@XmlAttribute(name="declared_value")
	private String declared_value;
	/** 货物声明价值币别 默认为CNY */
	@XmlAttribute(name="declared_value_currency")
	private String declared_value_currency = "CNY";
	/** 报关批次 */
	@XmlAttribute(name="customs_batchs")
	private String customs_batchs;
	/** 要求上门取件开始时间，格式：YYYY-MM-DD HH24:MM:SS，示例：2012-7-30 09:30:00。 */
	@XmlAttribute(name="sendstarttime")
	private String sendstarttime;
	/** 是否要求通过是否手持终端通知顺丰收派员收件：  1：要求  其它为不要求 */
	@XmlAttribute(name="is_docall")
	private String is_docall;
	/** 是否要求签回单号：1：要求 其它为不要求 */
	@XmlAttribute(name="need_return_tracking_no")
	private String need_return_tracking_no;
	/** 顺丰签回单服务运单号 */
	@XmlAttribute(name="return_tracking")
	private String return_tracking;
	/** 收件人税号 */
	@XmlAttribute(name="d_tax_no")
	private String d_tax_no;
	/** 税金付款方式：  1：寄付  2：到付 */
	@XmlAttribute(name="tax_pay_type")
	private String tax_pay_type;
	/** 税金结算账号 */
	@XmlAttribute(name="tax_set_accounts")
	private String tax_set_accounts;
	/** 电商原始订单号 */
	@XmlAttribute(name="original_number")
	private String original_number;
	/** 支付工具 */
	@XmlAttribute(name="payment_tool")
	private String payment_tool;
	/** 支付号码 */
	@XmlAttribute(name="payment_number")
	private String payment_number;
	/** 商品编号 */
	@XmlAttribute(name="goods_code")
	private String goods_code;
	/** 头程运单号 */
	@XmlAttribute(name="in_process_waybill_no")
	private String in_process_waybill_no;
	/** 货物品牌 */
	@XmlAttribute(name="brand")
	private String brand;
	/** 货物规格型号 */
	@XmlAttribute(name="specifications")
	private String specifications;
	/** 温度范围类型，当express_type为12医药温控件时必填： 1为冷藏 3为冷冻 */
	@XmlAttribute(name="temp_range")
	private String temp_range;
	/** 客户订单下单人姓名 */
	@XmlAttribute(name="order_name")
	private String order_name;
	/** 客户订单下单人证件类型 */
	@XmlAttribute(name="order_cert_type")
	private String order_cert_type;
	/** 客户订单下单人证件号 */
	@XmlAttribute(name="order_cert_no")
	private String order_cert_no;
	/** 客户订单来源（对于平台类客户，如果需要在订单中区分订单来源，则可使用此字段） */
	@XmlAttribute(name="order_source")
	private String order_source;
	/** 业务模板编码，业务模板指BSP针对客户业务需求配置的一套接口处理逻辑，一个接入编码可对应多个业务模板。 */
	@XmlAttribute(name="template")
	private String template;
	/** 备注 */
	@XmlAttribute(name="remark")
	private String remark;
	/** 包裹货物信息 */
	@XmlElement(name = "Cargo")
	private List<SCargo> cargo;
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
	public int getIs_gen_bill_no() {
		return is_gen_bill_no;
	}
	public void setIs_gen_bill_no(int is_gen_bill_no) {
		this.is_gen_bill_no = is_gen_bill_no;
	}
	public String getJ_company() {
		return j_company;
	}
	public void setJ_company(String j_company) {
		this.j_company = j_company;
	}
	public String getJ_contact() {
		return j_contact;
	}
	public void setJ_contact(String j_contact) {
		this.j_contact = j_contact;
	}
	public String getJ_tel() {
		return j_tel;
	}
	public void setJ_tel(String j_tel) {
		this.j_tel = j_tel;
	}
	public String getJ_mobile() {
		return j_mobile;
	}
	public void setJ_mobile(String j_mobile) {
		this.j_mobile = j_mobile;
	}
	public String getJ_shippercode() {
		return j_shippercode;
	}
	public void setJ_shippercode(String j_shippercode) {
		this.j_shippercode = j_shippercode;
	}
	public String getJ_country() {
		return j_country;
	}
	public void setJ_country(String j_country) {
		this.j_country = j_country;
	}
	public String getJ_province() {
		return j_province;
	}
	public void setJ_province(String j_province) {
		this.j_province = j_province;
	}
	public String getJ_city() {
		return j_city;
	}
	public void setJ_city(String j_city) {
		this.j_city = j_city;
	}
	public String getJ_county() {
		return j_county;
	}
	public void setJ_county(String j_county) {
		this.j_county = j_county;
	}
	public String getJ_address() {
		return j_address;
	}
	public void setJ_address(String j_address) {
		this.j_address = j_address;
	}
	public String getJ_post_code() {
		return j_post_code;
	}
	public void setJ_post_code(String j_post_code) {
		this.j_post_code = j_post_code;
	}
	public String getD_company() {
		return d_company;
	}
	public void setD_company(String d_company) {
		this.d_company = d_company;
	}
	public String getD_contact() {
		return d_contact;
	}
	public void setD_contact(String d_contact) {
		this.d_contact = d_contact;
	}
	public String getD_tel() {
		return d_tel;
	}
	public void setD_tel(String d_tel) {
		this.d_tel = d_tel;
	}
	public String getD_mobile() {
		return d_mobile;
	}
	public void setD_mobile(String d_mobile) {
		this.d_mobile = d_mobile;
	}
	public String getD_deliverycode() {
		return d_deliverycode;
	}
	public void setD_deliverycode(String d_deliverycode) {
		this.d_deliverycode = d_deliverycode;
	}
	public String getD_country() {
		return d_country;
	}
	public void setD_country(String d_country) {
		this.d_country = d_country;
	}
	public String getD_province() {
		return d_province;
	}
	public void setD_province(String d_province) {
		this.d_province = d_province;
	}
	public String getD_city() {
		return d_city;
	}
	public void setD_city(String d_city) {
		this.d_city = d_city;
	}
	public String getD_county() {
		return d_county;
	}
	public void setD_county(String d_county) {
		this.d_county = d_county;
	}
	public String getD_address() {
		return d_address;
	}
	public void setD_address(String d_address) {
		this.d_address = d_address;
	}
	public String getD_post_code() {
		return d_post_code;
	}
	public void setD_post_code(String d_post_code) {
		this.d_post_code = d_post_code;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public int getPay_method() {
		return pay_method;
	}
	public void setPay_method(int pay_method) {
		this.pay_method = pay_method;
	}
	public String getExpress_type() {
		return express_type;
	}
	public void setExpress_type(String express_type) {
		this.express_type = express_type;
	}
	public int getParcel_quantity() {
		return parcel_quantity;
	}
	public void setParcel_quantity(int parcel_quantity) {
		this.parcel_quantity = parcel_quantity;
	}
	public String getCargo_length() {
		return cargo_length;
	}
	public void setCargo_length(String cargo_length) {
		this.cargo_length = cargo_length;
	}
	public String getCargo_width() {
		return cargo_width;
	}
	public void setCargo_width(String cargo_width) {
		this.cargo_width = cargo_width;
	}
	public String getCargo_height() {
		return cargo_height;
	}
	public void setCargo_height(String cargo_height) {
		this.cargo_height = cargo_height;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getCargo_total_weight() {
		return cargo_total_weight;
	}
	public void setCargo_total_weight(String cargo_total_weight) {
		this.cargo_total_weight = cargo_total_weight;
	}
	public String getDeclared_value() {
		return declared_value;
	}
	public void setDeclared_value(String declared_value) {
		this.declared_value = declared_value;
	}
	public String getDeclared_value_currency() {
		return declared_value_currency;
	}
	public void setDeclared_value_currency(String declared_value_currency) {
		this.declared_value_currency = declared_value_currency;
	}
	public String getCustoms_batchs() {
		return customs_batchs;
	}
	public void setCustoms_batchs(String customs_batchs) {
		this.customs_batchs = customs_batchs;
	}
	public String getSendstarttime() {
		return sendstarttime;
	}
	public void setSendstarttime(String sendstarttime) {
		this.sendstarttime = sendstarttime;
	}
	public String getIs_docall() {
		return is_docall;
	}
	public void setIs_docall(String is_docall) {
		this.is_docall = is_docall;
	}
	public String getNeed_return_tracking_no() {
		return need_return_tracking_no;
	}
	public void setNeed_return_tracking_no(String need_return_tracking_no) {
		this.need_return_tracking_no = need_return_tracking_no;
	}
	public String getReturn_tracking() {
		return return_tracking;
	}
	public void setReturn_tracking(String return_tracking) {
		this.return_tracking = return_tracking;
	}
	public String getD_tax_no() {
		return d_tax_no;
	}
	public void setD_tax_no(String d_tax_no) {
		this.d_tax_no = d_tax_no;
	}
	public String getTax_pay_type() {
		return tax_pay_type;
	}
	public void setTax_pay_type(String tax_pay_type) {
		this.tax_pay_type = tax_pay_type;
	}
	public String getTax_set_accounts() {
		return tax_set_accounts;
	}
	public void setTax_set_accounts(String tax_set_accounts) {
		this.tax_set_accounts = tax_set_accounts;
	}
	public String getOriginal_number() {
		return original_number;
	}
	public void setOriginal_number(String original_number) {
		this.original_number = original_number;
	}
	public String getPayment_tool() {
		return payment_tool;
	}
	public void setPayment_tool(String payment_tool) {
		this.payment_tool = payment_tool;
	}
	public String getPayment_number() {
		return payment_number;
	}
	public void setPayment_number(String payment_number) {
		this.payment_number = payment_number;
	}
	public String getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}
	public String getIn_process_waybill_no() {
		return in_process_waybill_no;
	}
	public void setIn_process_waybill_no(String in_process_waybill_no) {
		this.in_process_waybill_no = in_process_waybill_no;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public String getTemp_range() {
		return temp_range;
	}
	public void setTemp_range(String temp_range) {
		this.temp_range = temp_range;
	}
	public String getOrder_name() {
		return order_name;
	}
	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}
	public String getOrder_cert_type() {
		return order_cert_type;
	}
	public void setOrder_cert_type(String order_cert_type) {
		this.order_cert_type = order_cert_type;
	}
	public String getOrder_cert_no() {
		return order_cert_no;
	}
	public void setOrder_cert_no(String order_cert_no) {
		this.order_cert_no = order_cert_no;
	}
	public String getOrder_source() {
		return order_source;
	}
	public void setOrder_source(String order_source) {
		this.order_source = order_source;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    public List<SCargo> getCargo() {
        return cargo;
    }
    public void setCargo(List<SCargo> cargo) {
        this.cargo = cargo;
    }
	

}
