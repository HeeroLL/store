package com.isell.ei.logistics.ecm.vo;

import com.isell.core.base.BaseInfo;

/**
 * ecmError
 * 
 * @author lilin
 * @version [版本号, 2015年7月20日]
 */
public class EcmError extends BaseInfo {
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 7042870216503395522L;
    
    /** 错误编码 */
    private String code;
    
    /** 错误信息 */
    private String errorMsg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
