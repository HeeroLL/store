package com.isell.service.shop.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 酷店表vo
 * 
 * @author lilin
 * @version [版本号, 2015年9月25日]
 */
public class CoonShop {
    /**
     * 主键id
     */
    private String id;
    
    /**
     * 用户id
     */
    private String userId;
    
    /**
     * 用户编码
     */
    private String code;
    
    /**
     * 酷店名称
     */
    private String name;
    
    /**
     * 酷店logo
     */
    private String logo;
    
    /**
     * 酷店公告
     */
    private String annInfo;
    
    /**
     * 酷店招牌
     */
    private String img;
    
    /**
     * 酷店二维码
     */
    private String qrCode;
    
    /**
     * 创建时间
     */
    private Date createtime;
    
    /**
     * 昨日收入
     */
    private BigDecimal yIncome;
    
    /**
     * 未提现金额
     */
    private BigDecimal nwdAmount;
    
    /**
     * 已提现金额
     */
    private BigDecimal wdAmount;
    
    /**
     * 累计金额
     */
    private BigDecimal allAmount;
    
    /**
     * 1.上下展示 2.左右展示
     */
    private Byte showWay;
    
    /**
     * 是否显示海报
     */
    private Boolean showBanner;
    
    /**
     * 酷店等级（外键）
     */
    private String level;
    
    /**
     * 成交订单数
     */
    private Integer turnoverOrders;
    
    /**
     * 展示模板
     */
    private Byte showModel;
    
    /**
     * 待确认佣金
     */
    private BigDecimal tbdAmount;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLogo() {
        return logo;
    }
    
    public void setLogo(String logo) {
        this.logo = logo;
    }
    
    public String getAnnInfo() {
        return annInfo;
    }
    
    public void setAnnInfo(String annInfo) {
        this.annInfo = annInfo;
    }
    
    public String getImg() {
        return img;
    }
    
    public void setImg(String img) {
        this.img = img;
    }
    
    public String getQrCode() {
        return qrCode;
    }
    
    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
    
    public Date getCreatetime() {
        return createtime;
    }
    
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    
    public BigDecimal getyIncome() {
        return yIncome;
    }
    
    public void setyIncome(BigDecimal yIncome) {
        this.yIncome = yIncome;
    }
    
    public BigDecimal getNwdAmount() {
        return nwdAmount;
    }
    
    public void setNwdAmount(BigDecimal nwdAmount) {
        this.nwdAmount = nwdAmount;
    }
    
    public BigDecimal getWdAmount() {
        return wdAmount;
    }
    
    public void setWdAmount(BigDecimal wdAmount) {
        this.wdAmount = wdAmount;
    }
    
    public BigDecimal getAllAmount() {
        return allAmount;
    }
    
    public void setAllAmount(BigDecimal allAmount) {
        this.allAmount = allAmount;
    }
    
    public Byte getShowWay() {
        return showWay;
    }
    
    public void setShowWay(Byte showWay) {
        this.showWay = showWay;
    }
    
    public Boolean getShowBanner() {
        return showBanner;
    }
    
    public void setShowBanner(Boolean showBanner) {
        this.showBanner = showBanner;
    }
    
    public String getLevel() {
        return level;
    }
    
    public void setLevel(String level) {
        this.level = level;
    }
    
    public Integer getTurnoverOrders() {
        return turnoverOrders;
    }
    
    public void setTurnoverOrders(Integer turnoverOrders) {
        this.turnoverOrders = turnoverOrders;
    }
    
    public Byte getShowModel() {
        return showModel;
    }
    
    public void setShowModel(Byte showModel) {
        this.showModel = showModel;
    }
    
    public BigDecimal getTbdAmount() {
        return tbdAmount;
    }
    
    public void setTbdAmount(BigDecimal tbdAmount) {
        this.tbdAmount = tbdAmount;
    }
}