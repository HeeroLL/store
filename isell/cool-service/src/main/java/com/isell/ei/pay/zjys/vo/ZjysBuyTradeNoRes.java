package com.isell.ei.pay.zjys.vo;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 浙江银商购买支付流水号响应参数信息
 * 
 * @author lilin
 * @version [版本号, 2016年1月28日]
 */
public class ZjysBuyTradeNoRes {
    /**
     * 结果  true  false
     */
    @JsonProperty("Result")
    private Boolean result;
    
    /**
     * 错误原因  Result=false时使用
     */
    @JsonProperty("ErrorMsg")
    private String errorMsg;
    
    /**
     * 订单结果集合
     */
    @JsonProperty("Content")
    private List<ZjysOrderInfo> content;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<ZjysOrderInfo> getContent() {
        return content;
    }

    public void setContent(List<ZjysOrderInfo> content) {
        this.content = content;
    }
}
