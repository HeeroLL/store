package com.isell.ei.pay.alipay.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 响应中alipay节点
 * 
 * @author lilin
 * @version [版本号, 2015年11月2日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class AlipayInfo {
    /**
     * 响应码
     */
    @XmlElement(name = "result_code")
    private String resultCode;
    
    /**
     * 支付单号
     */
    @XmlElement(name = "trade_no")    
    private String tradeNo;
    
    /**
     * 支付宝报关流水号
     */
    @XmlElement(name = "alipay_declare_no")
    private String alipayDeclareNo;
    
    /**
     * 详细错误码
     */
    @XmlElement(name = "detail_error_code")
    private String detailErrorCode;
    
    /**
     * 详细错误描述
     */
    @XmlElement(name = "detail_error_des")
    private String detailErrorDes;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getAlipayDeclareNo() {
        return alipayDeclareNo;
    }

    public void setAlipayDeclareNo(String alipayDeclareNo) {
        this.alipayDeclareNo = alipayDeclareNo;
    }

    public String getDetailErrorCode() {
        return detailErrorCode;
    }

    public void setDetailErrorCode(String detailErrorCode) {
        this.detailErrorCode = detailErrorCode;
    }

    public String getDetailErrorDes() {
        return detailErrorDes;
    }

    public void setDetailErrorDes(String detailErrorDes) {
        this.detailErrorDes = detailErrorDes;
    }
}
