package com.isell.ei.pay.zjys.vo;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 浙江银商购买支付流水号订单参数信息
 * 
 * @author lilin
 * @version [版本号, 2016年1月28日]
 */
public class ZjysOrderInfo {
    /**
     * 外部商户订单号
     */
    @JsonProperty("out_trade_no")
    private String outTrandNo;
    
    /**
     * 用户真实姓名
     */
    @JsonProperty("real_name")
    private String realName;
    
    /**
     * 订单金额
     */
    private BigDecimal amount;
    
    /**
     * 身份证号
     */
    @JsonProperty("id_num")
    private String idNum;
    
    /**
     * 手机号
     */
    private String telephone;
    
    /*********** 以下是响应信息 ***********/
    
    /**
     * 交易流水号
     */
    @JsonProperty("WaterNumber")
    private String waterNumber;
    
    /**
     * 报关结果 -1:不需报关 0:未报关 1:报关成功 2:报关失败
     */
    private Integer customsState;
    
    /**
     * 结果
     */
    @JsonProperty("Result")
    private String result;

    public String getOutTrandNo() {
        return outTrandNo;
    }

    public void setOutTrandNo(String outTrandNo) {
        this.outTrandNo = outTrandNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getWaterNumber() {
        return waterNumber;
    }

    public void setWaterNumber(String waterNumber) {
        this.waterNumber = waterNumber;
    }

    public Integer getCustomsState() {
        return customsState;
    }

    public void setCustomsState(Integer customsState) {
        this.customsState = customsState;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
