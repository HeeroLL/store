package com.isell.ei.pay.yijifu.bean;

import java.math.BigDecimal;

/**
 * 易极付订单信息同步
 * 
 * @author lilin
 * @version [版本号, 2016年2月19日]
 */
public class YijifuOrderInfo {
    /**
     * 商户外部订单号(唯一)
     */
    private String detailOrderSerialNo;
    
    /**
     * 交易编码
     */
    private String tradeCode = "122030";
    
    /**
     * 贸易类型
     */
    private String tradeType = "GOODS_TRADE";
    
    /**
     * 明细金额
     */
    private BigDecimal detailOrderAmount;
    
    /**
     * 明细币种
     */
    private String detailOrderCurrency = "CNY";
    
    /**
     * 是否为保税货物
     */
    private String verificationFlag = "Y";
    
    /**
     * 买家真实姓名
     */
    private String buyerRealName;
    
    /**
     * 买家身份证号
     */
    private String buyerIDNumber;
    
    /**
     * 物流信息
     */
    private YijifuLogisticInfo logisticInfo;
    
    /**
     * 商品信息
     */
    private YijifuGoodsClause goodsClause;

    public String getDetailOrderSerialNo() {
        return detailOrderSerialNo;
    }

    public void setDetailOrderSerialNo(String detailOrderSerialNo) {
        this.detailOrderSerialNo = detailOrderSerialNo;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public BigDecimal getDetailOrderAmount() {
        return detailOrderAmount;
    }

    public void setDetailOrderAmount(BigDecimal detailOrderAmount) {
        this.detailOrderAmount = detailOrderAmount;
    }

    public String getDetailOrderCurrency() {
        return detailOrderCurrency;
    }

    public void setDetailOrderCurrency(String detailOrderCurrency) {
        this.detailOrderCurrency = detailOrderCurrency;
    }

    public String getVerificationFlag() {
        return verificationFlag;
    }

    public void setVerificationFlag(String verificationFlag) {
        this.verificationFlag = verificationFlag;
    }

    public String getBuyerRealName() {
        return buyerRealName;
    }

    public void setBuyerRealName(String buyerRealName) {
        this.buyerRealName = buyerRealName;
    }

    public String getBuyerIDNumber() {
        return buyerIDNumber;
    }

    public void setBuyerIDNumber(String buyerIDNumber) {
        this.buyerIDNumber = buyerIDNumber;
    }

    public YijifuLogisticInfo getLogisticInfo() {
        return logisticInfo;
    }

    public void setLogisticInfo(YijifuLogisticInfo logisticInfo) {
        this.logisticInfo = logisticInfo;
    }

    public YijifuGoodsClause getGoodsClause() {
        return goodsClause;
    }

    public void setGoodsClause(YijifuGoodsClause goodsClause) {
        this.goodsClause = goodsClause;
    }
}
