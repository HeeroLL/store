package com.isell.ei.pay.alipay.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 报关回执信息
 * 
 * @author lilin
 * @version [版本号, 2015年10月30日]
 */
@XmlRootElement(name = "alipay")
@XmlAccessorType(XmlAccessType.FIELD)
public class SendOrderResponse {
    /**
     * 请求是否成功
     */
    @XmlElement(name = "is_success")
    private String success;
    
    /**
     * 签名方式
     */
    @XmlElement(name = "sign_type")
    private String signType;
    
    /**
     * 签名
     */
    private String sign;
    
    /**
     * 错误代码
     */
    private String error;
    
    /**
     * response
     */
    private AliResponse response;
    
    public String getSuccess() {
        return success;
    }
    
    public void setSuccess(String success) {
        this.success = success;
    }
    
    public String getSignType() {
        return signType;
    }
    
    public void setSignType(String signType) {
        this.signType = signType;
    }
    
    public String getSign() {
        return sign;
    }
    
    public void setSign(String sign) {
        this.sign = sign;
    }
    
    public String getError() {
        return error;
    }
    
    public void setError(String error) {
        this.error = error;
    }

    public AliResponse getResponse() {
        return response;
    }

    public void setResponse(AliResponse response) {
        this.response = response;
    }
}
