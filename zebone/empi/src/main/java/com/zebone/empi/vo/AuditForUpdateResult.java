package com.zebone.empi.vo;

/**
 * 实名信息更新审核返回结果对象
 *
 * @author 杨英
 * @version 2014-8-2 上午10:46
 */
public class AuditForUpdateResult {
    //结果 1 成功 2 失败
    private String isSuccess;
    //错误代码
    private String errorCode;
    //错误明细
    private String errorMsg;

    public String getSuccess() {
        return isSuccess;
    }

    public void setSuccess(String success) {
        isSuccess = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
