package com.sell.ei.logistics.ecm.vo;

import org.codehaus.jackson.annotate.JsonProperty;

import com.sell.core.base.BaseInfo;

/**
 * 响应消息体
 * 
 * @author lilin
 * @version [版本号, 2015年7月20日]
 */
public class EcmResponseBody extends BaseInfo {
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -1947290316170822923L;
    
    /** 处理结果(1000：成功 1005：全部失败 1015：部分失败 1025：其他异常) */
    private String resultCode;
    
    /** 处理消息 */
    private String resultMsg;
    
    /** 异常信息 */
    @JsonProperty("ERROR")
    private EcmError error;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public EcmError getError() {
        return error;
    }

    public void setError(EcmError error) {
        this.error = error;
    }
}
