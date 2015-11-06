package com.isell.ws.zhengzhou.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 消息主体
 * 
 * @author lilin
 * @version [版本号, 2015年10月21日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {})
public class Bodymaster {
    /**
     * 账册号
     */
    @XmlElement(name = "LMSNO")
    private String lmsno;
    
    /**
     * 关联 H2010 账册号
     */
    @XmlElement(name = "MANUALNO")
    private String manualno;
    
    /**
     * 电商代码
     */
    @XmlElement(name = "CBECODE")
    private String cbecode = "D461200044";
    
    /**
     * 电商名称
     */
    @XmlElement(name = "CBENAME")
    private String cbename = "郑州洲州海优电子商务有限公司";
    
    /**
     * 电商平台代码
     */
    @XmlElement(name = "ECPCODE")
    private String ecpcode = "W461200040";
    
    /**
     * 电商平台名称
     */
    @XmlElement(name = "ECPNAME")
    private String ecpname = "郑州洲州海优电子商务有限公司";
    
    /**
     * 收货人地址
     */
    @XmlElement(name = "COLLECTIONUSERADDRESS")
    private String collectionuseraddress;
    
    /**
     * 收货人国家
     */
    @XmlElement(name = "COLLECTIONUSERCOUNTRY")
    private String collectionusercountry = "142";
    
    /**
     * 收货人名称
     */
    @XmlElement(name = "COLLECTIONUSERNAME")
    private String collectionusername;
    
    /**
     * 收货人电话
     */
    @XmlElement(name = "COLLECTIONUSERTELEPHONE")
    private String collectionusertelephone;
    
    /**
     * 订单实际销售价格
     */
    @XmlElement(name = "GOODSVALUE")
    private String goodsvalue;
    
    /**
     * 订单编号
     */
    @XmlElement(name = "ORDERID")
    private String orderid;
    
    /**
     * 订单总价 总费用
     */
    @XmlElement(name = "ORDERSUM")
    private String ordersum;
    
    /**
     * 运费
     */
    @XmlElement(name = "FREIGHT")
    private String freight;
    
    /**
     * 其他费用
     */
    @XmlElement(name = "OTHERFEE")
    private String otherfee;
    
    /**
     * 进口行邮费
     */
    @XmlElement(name = "TAXFEE")
    private String taxfee;
    
    /**
     * 备注
     */
    @XmlElement(name = "REMARK")
    private String remark;
    
    /**
     * 发货人所在国
     */
    @XmlElement(name = "SENDERUSERCOUNTRY")
    private String senderusercountry = "142"; // 中国
    
    /**
     * 发货人的名称
     */
    @XmlElement(name = "SENDERUSERNAME")
    private String senderusername = "艾售国际服务中心";
    
    /**
     * 发货人的地址
     */
    @XmlElement(name = "SENDERUSERADDRESS")
    private String senderuseraddress = "河南郑州保税仓";
    
    /**
     * 发货人电话
     */
    @XmlElement(name = "SENDERUSERTELEPHONE")
    private String senderusertelephone = "4009693356";
    
    /**
     * 证件类型
     */
    @XmlElement(name = "IDTYPE")
    private String idtype = "TOC001";
    
    /**
     * 证件号码
     */
    @XmlElement(name = "CUSTOMERID")
    private String customerid;
    
    /**
     * 进出口标志 I-进口；E-出口
     */
    @XmlElement(name = "IETYPE")
    private String ietype = "I";
    
    /**
     * 操作类型 1-新增；2-修改；3-删除
     */
    @XmlElement(name = "MODIFYMARK")
    private String modifymark;
    
    /**
     * 模式代码：1- 一般模式 ； 2- 保税模式
     */
    @XmlElement(name = "BILLMODE")
    private String billmode = "2";
    
    /**
     * 是否有废旧物品Y/N
     */
    @XmlElement(name = "WASTERORNOT")
    private String wasterornot = "N";
    
    /**
     * 是否带有植物性包装及铺垫材料Y/N
     */
    @XmlElement(name = "BOTANYORNOT")
    private String botanyornot = "N";
    
    /**
     * 缴税单位:英文字符+数字
     */
    @XmlElement(name = "TAXEDENTERPRISE")
    private String taxedenterprise = "4101W68065";
    
    /**
     * 电商检验检疫备案编号
     */
    @XmlElement(name = "CBECODEINSP")
    private String cbecodeinsp = "4100102324";
    
    /**
     * 电商平台检验检疫备案编号
     */
    @XmlElement(name = "ECPCODEINSP")
    private String ecpcodeinsp = "4100102324";
    
    /**
     * 物流企业检验检疫备案编号
     */
    @XmlElement(name = "TREPCODEINSP")
    private String trepcodeinsp = "4100102324";
    
    /**
     * 订单提交时间
     */
    @XmlElement(name = "SUBMITTIME")
    private String submittime;
    
    /**
     * 贸易国别（代码）
     */
    @XmlElement(name = "TRADECOMPANY")
    private String tradecompany = "601";
    
    /**
     * 总费用币制
     */
    @XmlElement(name = "TOTALFEEUNIT")
    private String totalfeeunit = "142";
    
    /**
     * 商品种类数
     */
    @XmlElement(name = "COUNTOFGOODSTYPE")
    private String countofgoodstype = "1";
    
    /**
     * 毛重
     */
    @XmlElement(name = "WEIGHT")
    private String weight = "0.5";
    
    /**
     * 毛重单位
     */
    @XmlElement(name = "WEIGHTUNIT")
    private String weightunit = "035";
    
    /**
     * 净重
     */
    @XmlElement(name = "NETWEIGHT")
    private String netweight = "0.3";
    
    /**
     * 净重单位
     */
    @XmlElement(name = "NETWEIGHTUNIT")
    private String netweightunit = "035";
    
    /**
     * 平台网址
     */
    @XmlElement(name = "PLATFORMURL")
    private String platformurl = "http://www.i-coolshop.com";
    
    /**
     * 发货人所在国（检验检疫代码）
     */
    @XmlElement(name = "COLLUSERCOUNTRYINSP")
    private String collusercountryinsp = "156";
    
    /**
     * 收货人所在国（检验检疫代码）
     */
    @XmlElement(name = "SENDUSERCOUNTRYINSP")
    private String sendusercountryinsp = "156";
    
    /**
     * 支付交易号
     */
    @XmlElement(name = "PAYNUMBER")
    private String paynumber;
    
    /**
     * 支付企业代码
     */
    @XmlElement(name = "PAYENTERPRISECODE")
    private String payenterprisecode;
    
    /**
     * 支付企业名称
     */
    @XmlElement(name = "PAYENTERPRISENAME")
    private String payenterprisename;
    
    /**
     * 其他支付金额
     */
    @XmlElement(name = "OTHERPAYMENT")
    private String otherpayment = "0";
    
    /**
     * 其他支付备注
     */
    @XmlElement(name = "OTHERPAYMENTTYPE")
    private String otherpaymenttype;
    
    /**
     * 许可证号
     */
    @XmlElement(name = "LICENSE_NO")
    private String licenseNo;
    
    /**
     * 报关企业海关备案编号
     */
    @XmlElement(name = "DECLCODE")
    private String declcode = "D461200044";
    
    /**
     * 是否退税 1： 是 2： 否
     */
    @XmlElement(name = "TAXREFUND")
    private String taxrefund;

    public String getLmsno() {
        return lmsno;
    }

    public void setLmsno(String lmsno) {
        this.lmsno = lmsno;
    }

    public String getManualno() {
        return manualno;
    }

    public void setManualno(String manualno) {
        this.manualno = manualno;
    }

    public String getCbecode() {
        return cbecode;
    }

    public void setCbecode(String cbecode) {
        this.cbecode = cbecode;
    }

    public String getCbename() {
        return cbename;
    }

    public void setCbename(String cbename) {
        this.cbename = cbename;
    }

    public String getEcpcode() {
        return ecpcode;
    }

    public void setEcpcode(String ecpcode) {
        this.ecpcode = ecpcode;
    }

    public String getEcpname() {
        return ecpname;
    }

    public void setEcpname(String ecpname) {
        this.ecpname = ecpname;
    }

    public String getCollectionuseraddress() {
        return collectionuseraddress;
    }

    public void setCollectionuseraddress(String collectionuseraddress) {
        this.collectionuseraddress = collectionuseraddress;
    }

    public String getCollectionusercountry() {
        return collectionusercountry;
    }

    public void setCollectionusercountry(String collectionusercountry) {
        this.collectionusercountry = collectionusercountry;
    }

    public String getCollectionusername() {
        return collectionusername;
    }

    public void setCollectionusername(String collectionusername) {
        this.collectionusername = collectionusername;
    }

    public String getCollectionusertelephone() {
        return collectionusertelephone;
    }

    public void setCollectionusertelephone(String collectionusertelephone) {
        this.collectionusertelephone = collectionusertelephone;
    }

    public String getGoodsvalue() {
        return goodsvalue;
    }

    public void setGoodsvalue(String goodsvalue) {
        this.goodsvalue = goodsvalue;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getOrdersum() {
        return ordersum;
    }

    public void setOrdersum(String ordersum) {
        this.ordersum = ordersum;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getOtherfee() {
        return otherfee;
    }

    public void setOtherfee(String otherfee) {
        this.otherfee = otherfee;
    }

    public String getTaxfee() {
        return taxfee;
    }

    public void setTaxfee(String taxfee) {
        this.taxfee = taxfee;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSenderusercountry() {
        return senderusercountry;
    }

    public void setSenderusercountry(String senderusercountry) {
        this.senderusercountry = senderusercountry;
    }

    public String getSenderusername() {
        return senderusername;
    }

    public void setSenderusername(String senderusername) {
        this.senderusername = senderusername;
    }

    public String getSenderuseraddress() {
        return senderuseraddress;
    }

    public void setSenderuseraddress(String senderuseraddress) {
        this.senderuseraddress = senderuseraddress;
    }

    public String getSenderusertelephone() {
        return senderusertelephone;
    }

    public void setSenderusertelephone(String senderusertelephone) {
        this.senderusertelephone = senderusertelephone;
    }

    public String getIdtype() {
        return idtype;
    }

    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getIetype() {
        return ietype;
    }

    public void setIetype(String ietype) {
        this.ietype = ietype;
    }

    public String getModifymark() {
        return modifymark;
    }

    public void setModifymark(String modifymark) {
        this.modifymark = modifymark;
    }

    public String getBillmode() {
        return billmode;
    }

    public void setBillmode(String billmode) {
        this.billmode = billmode;
    }

    public String getWasterornot() {
        return wasterornot;
    }

    public void setWasterornot(String wasterornot) {
        this.wasterornot = wasterornot;
    }

    public String getBotanyornot() {
        return botanyornot;
    }

    public void setBotanyornot(String botanyornot) {
        this.botanyornot = botanyornot;
    }

    public String getTaxedenterprise() {
        return taxedenterprise;
    }

    public void setTaxedenterprise(String taxedenterprise) {
        this.taxedenterprise = taxedenterprise;
    }

    public String getCbecodeinsp() {
        return cbecodeinsp;
    }

    public void setCbecodeinsp(String cbecodeinsp) {
        this.cbecodeinsp = cbecodeinsp;
    }

    public String getEcpcodeinsp() {
        return ecpcodeinsp;
    }

    public void setEcpcodeinsp(String ecpcodeinsp) {
        this.ecpcodeinsp = ecpcodeinsp;
    }

    public String getTrepcodeinsp() {
        return trepcodeinsp;
    }

    public void setTrepcodeinsp(String trepcodeinsp) {
        this.trepcodeinsp = trepcodeinsp;
    }

    public String getSubmittime() {
        return submittime;
    }

    public void setSubmittime(String submittime) {
        this.submittime = submittime;
    }

    public String getTradecompany() {
        return tradecompany;
    }

    public void setTradecompany(String tradecompany) {
        this.tradecompany = tradecompany;
    }

    public String getTotalfeeunit() {
        return totalfeeunit;
    }

    public void setTotalfeeunit(String totalfeeunit) {
        this.totalfeeunit = totalfeeunit;
    }

    public String getCountofgoodstype() {
        return countofgoodstype;
    }

    public void setCountofgoodstype(String countofgoodstype) {
        this.countofgoodstype = countofgoodstype;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeightunit() {
        return weightunit;
    }

    public void setWeightunit(String weightunit) {
        this.weightunit = weightunit;
    }

    public String getNetweight() {
        return netweight;
    }

    public void setNetweight(String netweight) {
        this.netweight = netweight;
    }

    public String getNetweightunit() {
        return netweightunit;
    }

    public void setNetweightunit(String netweightunit) {
        this.netweightunit = netweightunit;
    }

    public String getPlatformurl() {
        return platformurl;
    }

    public void setPlatformurl(String platformurl) {
        this.platformurl = platformurl;
    }

    public String getCollusercountryinsp() {
        return collusercountryinsp;
    }

    public void setCollusercountryinsp(String collusercountryinsp) {
        this.collusercountryinsp = collusercountryinsp;
    }

    public String getSendusercountryinsp() {
        return sendusercountryinsp;
    }

    public void setSendusercountryinsp(String sendusercountryinsp) {
        this.sendusercountryinsp = sendusercountryinsp;
    }

    public String getPaynumber() {
        return paynumber;
    }

    public void setPaynumber(String paynumber) {
        this.paynumber = paynumber;
    }

    public String getPayenterprisecode() {
        return payenterprisecode;
    }

    public void setPayenterprisecode(String payenterprisecode) {
        this.payenterprisecode = payenterprisecode;
    }

    public String getPayenterprisename() {
        return payenterprisename;
    }

    public void setPayenterprisename(String payenterprisename) {
        this.payenterprisename = payenterprisename;
    }

    public String getOtherpayment() {
        return otherpayment;
    }

    public void setOtherpayment(String otherpayment) {
        this.otherpayment = otherpayment;
    }

    public String getOtherpaymenttype() {
        return otherpaymenttype;
    }

    public void setOtherpaymenttype(String otherpaymenttype) {
        this.otherpaymenttype = otherpaymenttype;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getDeclcode() {
        return declcode;
    }

    public void setDeclcode(String declcode) {
        this.declcode = declcode;
    }

    public String getTaxrefund() {
        return taxrefund;
    }

    public void setTaxrefund(String taxrefund) {
        this.taxrefund = taxrefund;
    }
}
