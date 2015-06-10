package com.zebone.empi.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 实名信息更新申请返回结果对象
 *
 * @author 杨英
 * @version 2014-7-21 上午10:08
 */
@XStreamAlias("response")
public class ApplyUpdateRes {
    //申请结果 1 成功 2 失败
    private String isSuccess;
    //错误代码  01 该请求卡存在未处理的申请  02 身份证号未注册 03 结构错误 04 格式错误 05 系统异常
    private String errorCode;
    //错误明细
    private String errorMsg;
    private String successMsg;
    @XStreamAlias("items")
    private UpdateApplyItem updateApplyItem = new UpdateApplyItem();

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

    public String getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }

    public UpdateApplyItem getUpdateApplyItem() {
        return updateApplyItem;
    }

    public void setUpdateApplyItem(UpdateApplyItem updateApplyItem) {
        this.updateApplyItem = updateApplyItem;
    }

    public static class UpdateApplyItem {
        //请求实名更新的身份证号
        private String cardNo;

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }
    }
}
