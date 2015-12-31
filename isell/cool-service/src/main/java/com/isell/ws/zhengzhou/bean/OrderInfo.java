package com.isell.ws.zhengzhou.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 订单信息
 * 
 * @author lilin
 * @version [版本号, 2015年10月21日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {})
public class OrderInfo {
    /**
     * 关联 H2010 项号(6种面膜的的项号是78  面霜的项号是79)
     */
    @XmlElement(name = "GNO")
    private String gno;
    
    /**
     * 海关备案商品编号
     */
    @XmlElement(name = "ITEMNO")
    private String itemno;
    
    /**
     * 商品上架品名
     */
    @XmlElement(name = "SHELFGOODSNAME")
    private String shelfgoodsname;
    
    /**
     * 商品描述
     */
    @XmlElement(name = "DESCRIBE")
    private String describe;
    
    /**
     * 商品货号
     */
    @XmlElement(name = "GOODID")
    private String goodid;
    
    /**
     * 申报品名
     */
    @XmlElement(name = "GOODNAME")
    private String goodname;
    
    /**
     * 规格型号
     */
    @XmlElement(name = "SPECIFICATIONS")
    private String specifications;
    
    /**
     * HS 编码
     */
    @XmlElement(name = "BARCODE")
    private String barcode;
    
    /**
     * 行邮税号
     */
    @XmlElement(name = "TAXID")
    private String taxid;
    
    /**
     * 原产国
     */
    @XmlElement(name = "SOURCEPRODUCERCOUNTRY")
    private String sourceproducercountry = "410"; // 韩国
    
    /**
     * 币制
     */
    @XmlElement(name = "COIN")
    private String coin = "142";
    
    /**
     * 计量单位
     */
    @XmlElement(name = "UNIT")
    private String unit;
    
    /**
     * 申报数量
     */
    @XmlElement(name = "AMOUNT")
    private String amount;
    
    /**
     * 成交单价
     */
    @XmlElement(name = "GOODPRICE")
    private String goodprice;
    
    /**
     * 成交总价
     */
    @XmlElement(name = "ORDERSUM")
    private String ordersum;
    
    /**
     * 是否赠品 N:不是 Y:是
     */
    @XmlElement(name = "FLAG")
    private String FLAG = "N";
    
    /**
     * 检验检疫商品备案编号
     */
    @XmlElement(name = "GOODIDINSP")
    private String goodidinsp;
    
    /**
     * 订单编号
     */
    @XmlElement(name = "ORDERID")
    private String orderid;
    
    /**
     * 货物名称(英文)
     */
    @XmlElement(name = "GOODNAMEENGLISH")
    private String goodnameenglish;
    
    /**
     * 毛重
     */
    @XmlElement(name = "WEIGTH")
    private String weigth;
    
    /**
     * 重量单位代码
     */
    @XmlElement(name = "WEIGHTUNIT")
    private String weightunit;
    
    /**
     * 包装类型代码(检验检疫)
     */
    @XmlElement(name = "PACKCATEGORYINSP")
    private String packcategoryinsp = "4M";
    
    /**
     * 废旧标识
     */
    @XmlElement(name = "WASTERORNOTINSP")
    private String wasterornotinsp;
    
    /**
     * 备注
     */
    @XmlElement(name = "REMARKSINSP")
    private String remarksinsp;
    
    /**
     * 币制（检验检疫代码）
     */
    @XmlElement(name = "COININSP")
    private String coininsp = "156";
    
    /**
     * 计量单位（检验检疫代码）
     */
    @XmlElement(name = "UNITINSP")
    private String unitinsp;
    
    /**
     * 原产国（检验检疫代码）
     */
    @XmlElement(name = "SRCCOUNTRYINSP")
    private String srccountryinsp = "410"; // 韩国
    
    public String getGno() {
        return gno;
    }
    
    public void setGno(String gno) {
        this.gno = gno;
    }
    
    public String getItemno() {
        return itemno;
    }
    
    public void setItemno(String itemno) {
        this.itemno = itemno;
    }
    
    public String getShelfgoodsname() {
        return shelfgoodsname;
    }
    
    public void setShelfgoodsname(String shelfgoodsname) {
        this.shelfgoodsname = shelfgoodsname;
    }
    
    public String getDescribe() {
        return describe;
    }
    
    public void setDescribe(String describe) {
        this.describe = describe;
    }
    
    public String getGoodid() {
        return goodid;
    }
    
    public void setGoodid(String goodid) {
        this.goodid = goodid;
    }
    
    public String getGoodname() {
        return goodname;
    }
    
    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }
    
    public String getSpecifications() {
        return specifications;
    }
    
    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }
    
    public String getBarcode() {
        return barcode;
    }
    
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    
    public String getTaxid() {
        return taxid;
    }
    
    public void setTaxid(String taxid) {
        this.taxid = taxid;
    }
    
    public String getSourceproducercountry() {
        return sourceproducercountry;
    }
    
    public void setSourceproducercountry(String sourceproducercountry) {
        this.sourceproducercountry = sourceproducercountry;
    }
    
    public String getCoin() {
        return coin;
    }
    
    public void setCoin(String coin) {
        this.coin = coin;
    }
    
    public String getUnit() {
        return unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    public String getAmount() {
        return amount;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    public String getGoodprice() {
        return goodprice;
    }
    
    public void setGoodprice(String goodprice) {
        this.goodprice = goodprice;
    }
    
    public String getOrdersum() {
        return ordersum;
    }
    
    public void setOrdersum(String ordersum) {
        this.ordersum = ordersum;
    }
    
    public String getFLAG() {
        return FLAG;
    }
    
    public void setFLAG(String fLAG) {
        FLAG = fLAG;
    }
    
    public String getGoodidinsp() {
        return goodidinsp;
    }
    
    public void setGoodidinsp(String goodidinsp) {
        this.goodidinsp = goodidinsp;
    }
    
    public String getOrderid() {
        return orderid;
    }
    
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
    
    public String getGoodnameenglish() {
        return goodnameenglish;
    }
    
    public void setGoodnameenglish(String goodnameenglish) {
        this.goodnameenglish = goodnameenglish;
    }
    
    public String getWeigth() {
        return weigth;
    }
    
    public void setWeigth(String weigth) {
        this.weigth = weigth;
    }
    
    public String getWeightunit() {
        return weightunit;
    }
    
    public void setWeightunit(String weightunit) {
        this.weightunit = weightunit;
    }
    
    public String getPackcategoryinsp() {
        return packcategoryinsp;
    }
    
    public void setPackcategoryinsp(String packcategoryinsp) {
        this.packcategoryinsp = packcategoryinsp;
    }
    
    public String getWasterornotinsp() {
        return wasterornotinsp;
    }
    
    public void setWasterornotinsp(String wasterornotinsp) {
        this.wasterornotinsp = wasterornotinsp;
    }
    
    public String getRemarksinsp() {
        return remarksinsp;
    }
    
    public void setRemarksinsp(String remarksinsp) {
        this.remarksinsp = remarksinsp;
    }
    
    public String getCoininsp() {
        return coininsp;
    }
    
    public void setCoininsp(String coininsp) {
        this.coininsp = coininsp;
    }
    
    public String getUnitinsp() {
        return unitinsp;
    }
    
    public void setUnitinsp(String unitinsp) {
        this.unitinsp = unitinsp;
    }
    
    public String getSrccountryinsp() {
        return srccountryinsp;
    }
    
    public void setSrccountryinsp(String srccountryinsp) {
        this.srccountryinsp = srccountryinsp;
    }
}
