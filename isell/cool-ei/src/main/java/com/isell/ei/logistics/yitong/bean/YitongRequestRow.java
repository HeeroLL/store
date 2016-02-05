package com.isell.ei.logistics.yitong.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.isell.core.util.DateUtil;
import com.isell.ei.logistics.yitong.service.YitongService;

/**
 * 易通订单请求行信息
 * 
 * @author lilin
 * @version [版本号, 2015年12月3日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class YitongRequestRow {
    /**
     * 行id
     */
    @XmlAttribute(name = "id")
    private String id;
    
    /**
     * 校验码
     */
    @XmlElement(name = "CheckData")
    private String checkData;
    
    /**
     * 申报日期 yyyy-mm-dd
     */
    private String declareDate = DateUtil.getCurrentDate("yyyy-MM-dd");
    
    /**
     * 客户编号
     */
    @XmlElement(name = "StoCustomerID")
    private String stoCustomerID = YitongService.STOCUSTOMERID;
    
    /**
     * 电商平台代码 海关备案代码
     */
    private String ecpCode = "3117964017";
    
    /**
     * 电商平台名称 海关备案名称
     */
    private String ecpName = "上海艾售电子商务有限公司";
    
    /**
     * 订单号
     */
    private String orderNo;
    
    /**
     * 申通运单编号
     */
    @XmlElement(name = "LogisticsNo")
    private String logisticsNo = "";
    
    /**
     * 申通跟踪单号
     */
    @XmlElement(name = "TrackingNo")
    private String trackingNo = "";
    
    /**
     * 国外发件人
     */
    private String shipper = "Mr Wang";
    
    /**
     * 国外发件地址
     */
    private String shipperAddress = "Korea";
    
    /**
     * 国外发件电话
     */
    private String shipperTelephone;
    
    /**
     * 发件人所在国(海)
     */
    private String shipperCountryCus = "142";
    
    /**
     * 国内发件人
     */
    @XmlElement(name = "Consignor")
    private String consignor;
    
    /**
     * 国内发件人电话
     */
    @XmlElement(name = "TelephoneNum")
    private String telephoneNum;
    
    /**
     * 国内发件地址
     */
    @XmlElement(name = "ConsignorAdd")
    private String consignorAdd;
    
    /**
     * 收件人
     */
    private String consignee;
    
    /**
     * 证件类型
     */
    private String idType = "TOC001";
    
    /**
     * 证件号码
     */
    private String customerId;
    
    /**
     * 收件人电话
     */
    private String consigneeTelephone;
    
    /**
     * 收件省份
     */
    @XmlElement(name = "Province")
    private String province;
    
    /**
     * 收件市
     */
    @XmlElement(name = "City")
    private String city;
    
    /**
     * 收件区
     */
    @XmlElement(name = "Zone")
    private String zone;
    
    /**
     * 收件地址
     */
    private String consigneeAddress;
    
    /**
     * 收件人所在国(检)
     */
    private String consigneeCountryCiq = "156";
    
    /**
     * 收件人所在国(海)
     */
    private String consigneeCountryCus = "142";
    
    /**
     * 毛量(Kg)
     */
    private String weight = "0.75";
    
    /**
     * 净重(Kg)
     */
    private String netWeight = "0.5";
    
    /**
     * 件数 大于 0 的整数，为空时数据库默认为 1
     */
    private String packNum;
    
    /**
     * 商品数量
     */
    private Integer quantity;
    
    /**
     * 进出口标识 I-进口,E-出口
     */
    private String ieType = "I";
    
    /**
     * 备注
     */
    private String note = "";
    
    /**
     * 总单号
     */
    private String totalLogisticsNo = "ISELL201601180001";//"HDFC15W4125120";
    
    /**
     * 货物品名
     */
    private String goodsName;
    
    /**
     * 装运港/指运港
     */
    private String destinationPort;
    
    /**
     * 包装类别代码(海)
     */
    private String packageTypeCus = "2";
    
    /**
     * 包装类别代码(检)
     */
    private String packageTypeCiq = "4M";
    
    /**
     * 运输方式代码(检)
     */
    private String transportationMethod = "2";
    
    /**
     * 运输工具代码(检)
     */
    private String shipCodeInsp = "20";
    
    /**
     * 贸易国别
     */
    private String tradeCountry = "142";
    
    /**
     * 进/出境日期 yyyy-mm-dd
     */
    private String jcbOrderTime = "2015-11-28";
    
    /**
     * 进/出境口岸 按海关要求申报
     */
    private String jcbOrderPort = "4612";
    
    /**
     * 运输方式代码 按海关要求申报
     */
    private String transferType = "2";
    
    /**
     * 进/出境口岸(检) 检疫机构代码 （综保区检验检疫代码）
     */
    private String jcbOrderPortInsp = "410900";
    
    /**
     * 运输工具名称(关) 航班号
     */
    private String shipName = "HDFC15W4125120";
    
    /**
     * 运输工具名称(检) 按国检要求申报
     */
    private String shipNameInsp = "船舶";
    
    /**
     * 检疫申报口岸
     */
    private String applyPortInsp = "410900";
    
    /**
     * 检疫起运/抵运国代码
     */
    private String transferRegionInsp = "156";
    
    /**
     * 电商企业国检备案编号
     */
    private String cbeCodeInsp = "3100679042";
    
    /**
     * 币制代码
     */
    private String coinInsp = "156";
    
    /**
     * 电商企业代码
     */
    private String cbeCode = "3117964017";
    
    /**
     * 电商企业名称
     */
    private String cbeName = "上海艾售电子商务有限公司";
    
    /**
     * 海关申报口岸代码
     */
    private String declarePort = "4612";
    
    /**
     * 操作标识 1 新增，2 修改系统默认为1 最多允许三次修改
     */
    private String modifyMark;
    
    /**
     * 集货/备货标识 1 集货，2 备货
     */
    private String stockFlag = "2";
    
    /**
     * 商品集合
     */
    @XmlElementWrapper(name = "goodsItem")
    @XmlElement(name = "item")
    private List<YitongRequestItem> goodsItem;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getCheckData() {
        return checkData;
    }
    
    public void setCheckData(String checkData) {
        this.checkData = checkData;
    }
    
    public String getDeclareDate() {
        return declareDate;
    }
    
    public void setDeclareDate(String declareDate) {
        this.declareDate = declareDate;
    }
    
    public String getStoCustomerID() {
        return stoCustomerID;
    }
    
    public void setStoCustomerID(String stoCustomerID) {
        this.stoCustomerID = stoCustomerID;
    }
    
    public String getEcpCode() {
        return ecpCode;
    }
    
    public void setEcpCode(String ecpCode) {
        this.ecpCode = ecpCode;
    }
    
    public String getEcpName() {
        return ecpName;
    }
    
    public void setEcpName(String ecpName) {
        this.ecpName = ecpName;
    }
    
    public String getOrderNo() {
        return orderNo;
    }
    
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    
    public String getLogisticsNo() {
        return logisticsNo;
    }
    
    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
    }
    
    public String getTrackingNo() {
        return trackingNo;
    }
    
    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }
    
    public String getShipper() {
        return shipper;
    }
    
    public void setShipper(String shipper) {
        this.shipper = shipper;
    }
    
    public String getShipperAddress() {
        return shipperAddress;
    }
    
    public void setShipperAddress(String shipperAddress) {
        this.shipperAddress = shipperAddress;
    }
    
    public String getShipperTelephone() {
        return shipperTelephone;
    }
    
    public void setShipperTelephone(String shipperTelephone) {
        this.shipperTelephone = shipperTelephone;
    }
    
    public String getShipperCountryCus() {
        return shipperCountryCus;
    }
    
    public void setShipperCountryCus(String shipperCountryCus) {
        this.shipperCountryCus = shipperCountryCus;
    }
    
    public String getConsignor() {
        return consignor;
    }
    
    public void setConsignor(String consignor) {
        this.consignor = consignor;
    }
    
    public String getTelephoneNum() {
        return telephoneNum;
    }
    
    public void setTelephoneNum(String telephoneNum) {
        this.telephoneNum = telephoneNum;
    }
    
    public String getConsignorAdd() {
        return consignorAdd;
    }
    
    public void setConsignorAdd(String consignorAdd) {
        this.consignorAdd = consignorAdd;
    }
    
    public String getConsignee() {
        return consignee;
    }
    
    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }
    
    public String getIdType() {
        return idType;
    }
    
    public void setIdType(String idType) {
        this.idType = idType;
    }
    
    public String getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    public String getConsigneeTelephone() {
        return consigneeTelephone;
    }
    
    public void setConsigneeTelephone(String consigneeTelephone) {
        this.consigneeTelephone = consigneeTelephone;
    }
    
    public String getProvince() {
        return province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getZone() {
        return zone;
    }
    
    public void setZone(String zone) {
        this.zone = zone;
    }
    
    public String getConsigneeAddress() {
        return consigneeAddress;
    }
    
    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }
    
    public String getConsigneeCountryCiq() {
        return consigneeCountryCiq;
    }
    
    public void setConsigneeCountryCiq(String consigneeCountryCiq) {
        this.consigneeCountryCiq = consigneeCountryCiq;
    }
    
    public String getConsigneeCountryCus() {
        return consigneeCountryCus;
    }
    
    public void setConsigneeCountryCus(String consigneeCountryCus) {
        this.consigneeCountryCus = consigneeCountryCus;
    }
    
    public String getWeight() {
        return weight;
    }
    
    public void setWeight(String weight) {
        this.weight = weight;
    }
    
    public String getNetWeight() {
        return netWeight;
    }
    
    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }
    
    public String getPackNum() {
        return packNum;
    }
    
    public void setPackNum(String packNum) {
        this.packNum = packNum;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public String getIeType() {
        return ieType;
    }
    
    public void setIeType(String ieType) {
        this.ieType = ieType;
    }
    
    public String getNote() {
        return note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    
    public String getTotalLogisticsNo() {
        return totalLogisticsNo;
    }
    
    public void setTotalLogisticsNo(String totalLogisticsNo) {
        this.totalLogisticsNo = totalLogisticsNo;
    }
    
    public String getGoodsName() {
        return goodsName;
    }
    
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    
    public String getDestinationPort() {
        return destinationPort;
    }
    
    public void setDestinationPort(String destinationPort) {
        this.destinationPort = destinationPort;
    }
    
    public String getPackageTypeCus() {
        return packageTypeCus;
    }
    
    public void setPackageTypeCus(String packageTypeCus) {
        this.packageTypeCus = packageTypeCus;
    }
    
    public String getPackageTypeCiq() {
        return packageTypeCiq;
    }
    
    public void setPackageTypeCiq(String packageTypeCiq) {
        this.packageTypeCiq = packageTypeCiq;
    }
    
    public String getTransportationMethod() {
        return transportationMethod;
    }
    
    public void setTransportationMethod(String transportationMethod) {
        this.transportationMethod = transportationMethod;
    }
    
    public String getShipCodeInsp() {
        return shipCodeInsp;
    }
    
    public void setShipCodeInsp(String shipCodeInsp) {
        this.shipCodeInsp = shipCodeInsp;
    }
    
    public String getTradeCountry() {
        return tradeCountry;
    }
    
    public void setTradeCountry(String tradeCountry) {
        this.tradeCountry = tradeCountry;
    }
    
    public String getJcbOrderTime() {
        return jcbOrderTime;
    }
    
    public void setJcbOrderTime(String jcbOrderTime) {
        this.jcbOrderTime = jcbOrderTime;
    }
    
    public String getJcbOrderPort() {
        return jcbOrderPort;
    }
    
    public void setJcbOrderPort(String jcbOrderPort) {
        this.jcbOrderPort = jcbOrderPort;
    }
    
    public String getTransferType() {
        return transferType;
    }
    
    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }
    
    public String getJcbOrderPortInsp() {
        return jcbOrderPortInsp;
    }
    
    public void setJcbOrderPortInsp(String jcbOrderPortInsp) {
        this.jcbOrderPortInsp = jcbOrderPortInsp;
    }
    
    public String getShipName() {
        return shipName;
    }
    
    public void setShipName(String shipName) {
        this.shipName = shipName;
    }
    
    public String getShipNameInsp() {
        return shipNameInsp;
    }
    
    public void setShipNameInsp(String shipNameInsp) {
        this.shipNameInsp = shipNameInsp;
    }
    
    public String getApplyPortInsp() {
        return applyPortInsp;
    }
    
    public void setApplyPortInsp(String applyPortInsp) {
        this.applyPortInsp = applyPortInsp;
    }
    
    public String getTransferRegionInsp() {
        return transferRegionInsp;
    }
    
    public void setTransferRegionInsp(String transferRegionInsp) {
        this.transferRegionInsp = transferRegionInsp;
    }
    
    public String getCbeCodeInsp() {
        return cbeCodeInsp;
    }
    
    public void setCbeCodeInsp(String cbeCodeInsp) {
        this.cbeCodeInsp = cbeCodeInsp;
    }
    
    public String getCoinInsp() {
        return coinInsp;
    }
    
    public void setCoinInsp(String coinInsp) {
        this.coinInsp = coinInsp;
    }
    
    public String getCbeCode() {
        return cbeCode;
    }
    
    public void setCbeCode(String cbeCode) {
        this.cbeCode = cbeCode;
    }
    
    public String getCbeName() {
        return cbeName;
    }
    
    public void setCbeName(String cbeName) {
        this.cbeName = cbeName;
    }
    
    public String getDeclarePort() {
        return declarePort;
    }
    
    public void setDeclarePort(String declarePort) {
        this.declarePort = declarePort;
    }
    
    public String getModifyMark() {
        return modifyMark;
    }
    
    public void setModifyMark(String modifyMark) {
        this.modifyMark = modifyMark;
    }
    
    public String getStockFlag() {
        return stockFlag;
    }
    
    public void setStockFlag(String stockFlag) {
        this.stockFlag = stockFlag;
    }
    
    public List<YitongRequestItem> getGoodsItem() {
        return goodsItem;
    }
    
    public void setGoodsItem(List<YitongRequestItem> goodsItem) {
        this.goodsItem = goodsItem;
    }
    
}
