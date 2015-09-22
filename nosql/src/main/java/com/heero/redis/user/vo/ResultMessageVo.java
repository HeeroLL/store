package com.heero.redis.user.vo;

/**
 * 登录结果信息
 * 
 * @author lilin
 */
public class ResultMessageVo {
    /**
     * 是否登录成功
     */
    private boolean success;
    
    /**
     * 错误消息
     */
    private String errMsg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
