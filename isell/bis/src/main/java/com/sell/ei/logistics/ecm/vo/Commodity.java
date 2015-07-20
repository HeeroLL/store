package com.sell.ei.logistics.ecm.vo;

import com.sell.core.base.BaseInfo;

/**
 * 商品接口参数<br/>
 * SCM接口:获取商品信息接口（sendCommodity）
 * 
 * @author lilin
 * @version [版本号, 2015年7月20日]
 */
public class Commodity extends BaseInfo {
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
    
    /** 商品规格（商品最小包装规格 1包、2罐等） */
    private String commoditySpec;
    
    /** 厂商货号 */
    private String commodityArtNo;
    
    /** 商品生产日期 */
    private String commodityMadeTime;
    
    /** 保质期(客户系统默认空) */
    private String durabilityPeriod;
    
    /** 品牌ID */
    private String brandId;
    
    /** 品牌名称 */
    private String brandName;
    
    /** 单价 */
    private String price;
    
    /** 市场价 */
    private String marketPrice;
    
    /** 控制批号标志， N(None)F(Full)T(TraceI-InboundTrace)O(OutboundTrace) */
    private String lotControl;
    
    /** 控制序列号标志，N(None)F(Full)T(TraceI-InboundTrace)O(OutboundTrace) */
    private String serialcontrol;
    
    /** 类别ID */
    private String typeId;
    
    /** 类别名称 */
    private String typeName;
    
    /** 单位(商品规格的单位对应编码)必须对照口岸标准格式填写 */
    private String unit;
    
    /** 重量(单位：kg) */
    private String weight;
    
    /** 装箱规格(客户系统默认空) */
    private String boxSpec;
    
    /** 商品备注 */
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
    
    public String getCommoditySpec() {
        return commoditySpec;
    }
    
    public void setCommoditySpec(String commoditySpec) {
        this.commoditySpec = commoditySpec;
    }
    
    public String getCommodityArtNo() {
        return commodityArtNo;
    }
    
    public void setCommodityArtNo(String commodityArtNo) {
        this.commodityArtNo = commodityArtNo;
    }
    
    public String getCommodityMadeTime() {
        return commodityMadeTime;
    }
    
    public void setCommodityMadeTime(String commodityMadeTime) {
        this.commodityMadeTime = commodityMadeTime;
    }
    
    public String getDurabilityPeriod() {
        return durabilityPeriod;
    }
    
    public void setDurabilityPeriod(String durabilityPeriod) {
        this.durabilityPeriod = durabilityPeriod;
    }
    
    public String getBrandId() {
        return brandId;
    }
    
    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }
    
    public String getBrandName() {
        return brandName;
    }
    
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    
    public String getPrice() {
        return price;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }
    
    public String getMarketPrice() {
        return marketPrice;
    }
    
    public void setMarketPrice(String marketPrice) {
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
    
    public String getTypeId() {
        return typeId;
    }
    
    public void setTypeId(String typeId) {
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
    
    public String getWeight() {
        return weight;
    }
    
    public void setWeight(String weight) {
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
