package com.sell.ei.logistics.ecm.vo;

import java.util.Date;

import com.sell.core.base.BaseInfo;

/**
 * 商品接口参数<br/>
 * SCM接口:获取商品信息接口（sendCommodity）
 * 
 * @author lilin
 * @version [版本号, 2015年7月20日]
 */
public class EcmCommodity extends BaseInfo {
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -233981414983133426L;
    
    /** 仓库代码（对应ECM系统仓库编码） */
    private String warehouseCode = "FLHZ01";
    
    /** 商品编码（8位商家在杭州海关备案企业编号+4位流水号） */
    private String commodityCode;
    
    /** 商品名称 */
    private String commodityName;
    
    /** 商品条形码 */
    private String commodityBarcode;
    
    /** 商品规格（商品最小包装规格 1包、2罐等，填中文，编码对应unit字段） */
    private String commoditySpec;
    
    /** 是否套装的标识 */
    private String isSet;
    
    /** 商品数量 */
    private Integer qty;
    
    /** 买家留言 */
    private String buyerMessage;
    
    /** 成交单价，商品实际成交的单价 */
    private Double tradePrice;
    
    /** 成交总价 */
    private Double tradeTotal;
    
    /** 申报单价，商品实际支付的单价 */
    private Double declPrice;
    
    /** 申报总价 */
    private Double declTotalPrice;
    
    /** 行邮税号，货主商品在海关备案的行邮税号 */
    private String codeTs;
    
    /** 厂商货号 */
    private String commodityArtNo;
    
    /** 商品生产日期 */
    private Date commodityMadeTime;
    
    /** 保质期(客户系统默认空) */
    private Integer durabilityPeriod;
    
    /** 品牌ID */
    private Integer brandId;
    
    /** 品牌名称 */
    private String brandName;
    
    /** 单价 */
    private Double price;
    
    /** 市场价 */
    private Double marketPrice;
    
    /** 控制批号标志， N(None)F(Full)T(TraceI-InboundTrace)O(OutboundTrace) */
    private String lotControl;
    
    /** 控制序列号标志，N(None)F(Full)T(TraceI-InboundTrace)O(OutboundTrace) */
    private String serialcontrol;
    
    /** 类别ID */
    private Integer typeId;
    
    /** 类别名称 */
    private String typeName;
    
    /** 单位(商品规格的单位对应编码)必须对照口岸标准格式填写 */
    private String unit;
    
    /** 重量(单位：kg) */
    private Double weight;
    
    /** 装箱规格(客户系统默认空) */
    private String boxSpec;
    
    /** 备注 */
    private String remark;
    
    /** 商品生产地址 */
    private String madeAddr;
    
    /** 商品原产地名称(必须对照口岸标准格式填写) */
    private String tradeCountryName;
    
    /** 商品原产地代码(必须对照口岸标准格式填写) */
    private String tradeCountryCode;

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityBarcode() {
        return commodityBarcode;
    }

    public void setCommodityBarcode(String commodityBarcode) {
        this.commodityBarcode = commodityBarcode;
    }

    public String getCommoditySpec() {
        return commoditySpec;
    }

    public void setCommoditySpec(String commoditySpec) {
        this.commoditySpec = commoditySpec;
    }

    public String getIsSet() {
        return isSet;
    }

    public void setIsSet(String isSet) {
        this.isSet = isSet;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public Double getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(Double tradePrice) {
        this.tradePrice = tradePrice;
    }

    public Double getTradeTotal() {
        return tradeTotal;
    }

    public void setTradeTotal(Double tradeTotal) {
        this.tradeTotal = tradeTotal;
    }

    public Double getDeclPrice() {
        return declPrice;
    }

    public void setDeclPrice(Double declPrice) {
        this.declPrice = declPrice;
    }

    public Double getDeclTotalPrice() {
        return declTotalPrice;
    }

    public void setDeclTotalPrice(Double declTotalPrice) {
        this.declTotalPrice = declTotalPrice;
    }

    public String getCodeTs() {
        return codeTs;
    }

    public void setCodeTs(String codeTs) {
        this.codeTs = codeTs;
    }

    public String getCommodityArtNo() {
        return commodityArtNo;
    }

    public void setCommodityArtNo(String commodityArtNo) {
        this.commodityArtNo = commodityArtNo;
    }

    public Date getCommodityMadeTime() {
        return commodityMadeTime;
    }

    public void setCommodityMadeTime(Date commodityMadeTime) {
        this.commodityMadeTime = commodityMadeTime;
    }

    public Integer getDurabilityPeriod() {
        return durabilityPeriod;
    }

    public void setDurabilityPeriod(Integer durabilityPeriod) {
        this.durabilityPeriod = durabilityPeriod;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getLotControl() {
        return lotControl;
    }

    public void setLotControl(String lotControl) {
        this.lotControl = lotControl;
    }

    public String getSerialcontrol() {
        return serialcontrol;
    }

    public void setSerialcontrol(String serialcontrol) {
        this.serialcontrol = serialcontrol;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getBoxSpec() {
        return boxSpec;
    }

    public void setBoxSpec(String boxSpec) {
        this.boxSpec = boxSpec;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMadeAddr() {
        return madeAddr;
    }

    public void setMadeAddr(String madeAddr) {
        this.madeAddr = madeAddr;
    }

    public String getTradeCountryName() {
        return tradeCountryName;
    }

    public void setTradeCountryName(String tradeCountryName) {
        this.tradeCountryName = tradeCountryName;
    }

    public String getTradeCountryCode() {
        return tradeCountryCode;
    }

    public void setTradeCountryCode(String tradeCountryCode) {
        this.tradeCountryCode = tradeCountryCode;
    }
}
