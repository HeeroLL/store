package com.zebone.docSub.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 文档订阅返回结果对象
 *
 * @author 杨英
 * @version 2014-8-12 下午1:39
 */
@XStreamAlias("response")
public class DocSubResult {
    //订阅结果 1 正常 2 异常
    private String isSuccess;
    //错误代码  1 参数错误 2 系统错误
    private String errorCode;
    //错误明细
    private String errorMsg;

    public String getSuccess() {
        return isSuccess;
    }

    public void setSuccess(String success) {
        isSuccess = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
