package com.zebone.dip.empi.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 主索引解绑申请返回结果对象
 *
 * @author 杨英
 * @version 2014-8-5 上午9:29
 */
@XStreamAlias("response")
public class ApplyUnbindResult {
    //申请结果 1 成功 2 失败
    private String isSuccess;
    //错误代码  01 该请求卡存在未处理的申请  02 身份证号未注册 03 一级标识相同 04 结构错误 05 格式错误 06 系统异常
    private String errorCode;
    //错误明细
    private String errorMsg;
    private String successMsg;
    
    private String  unbindApplyId;

    @XStreamAlias("items")
    private UnbindItem unbindItem = new UnbindItem();

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

    public String getUnbindApplyId() {
		return unbindApplyId;
	}

	public void setUnbindApplyId(String unbindApplyId) {
		this.unbindApplyId = unbindApplyId;
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

    public UnbindItem getUnbindItem() {
        return unbindItem;
    }

    public void setUnbindItem(UnbindItem unbindItem) {
        this.unbindItem = unbindItem;
    }

    public static class UnbindItem {
        //需要解绑的二级标识号
        private String cardNo;
        //需要解绑的二级标识类型
        private String cardType;
        //需要解绑的二级标识发卡机构
        private String cardOrg;

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getCardType() {
            return cardType;
        }

        public void setCardType(String cardType) {
            this.cardType = cardType;
        }

        public String getCardOrg() {
            return cardOrg;
        }

        public void setCardOrg(String cardOrg) {
            this.cardOrg = cardOrg;
        }
    }
}
