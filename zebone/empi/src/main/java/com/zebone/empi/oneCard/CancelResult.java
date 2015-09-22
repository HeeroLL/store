package com.zebone.empi.oneCard;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 卡注销返回结果对象
 *
 * @author 杨英
 * @version 2014-6-12 上午9:49
 */
@XStreamAlias("response")
public class CancelResult {
    //卡注销结果 1 正常 2 异常
    private String isSuccess;
    //错误代码  1 重复注销 2 系统错误
    private String errorCode;
    //错误明细
    private String errorMsg;

    @XStreamAlias("items")
    private CancelItem cancelItem = new CancelItem();

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

    public CancelItem getCancelItem() {
        return cancelItem;
    }

    public void setCancelItem(CancelItem cancelItem) {
        this.cancelItem = cancelItem;
    }

    public static class CancelItem {
        //卡号
        private String cardNo;

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }
    }

}
