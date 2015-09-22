package com.zebone.empi.oneCard;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 卡等变更返回结果对象
 *
 * @author 杨英
 * @version 2014-6-13 上午8:32
 */
@XStreamAlias("response")
public class LevelChangeResult {
    //卡等变更结果 1 正常 2 异常
    private String isSuccess;
    //错误代码
    private String errorCode;
    //错误明细
    private String errorMsg;

    @XStreamAlias("items")
    private LevelChangeItem levelChangeItem = new LevelChangeItem();

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

    public LevelChangeItem getLevelChangeItem() {
        return levelChangeItem;
    }

    public void setLevelChangeItem(LevelChangeItem levelChangeItem) {
        this.levelChangeItem = levelChangeItem;
    }

    public static class LevelChangeItem{
        private String empi;
        private String name;
        //变更后的人员等级
        @XStreamAlias("star_level")
        private String starLevel;

        public String getEmpi() {
            return empi;
        }

        public void setEmpi(String empi) {
            this.empi = empi;
        }

        public String getStarLevel() {
            return starLevel;
        }

        public void setStarLevel(String starLevel) {
            this.starLevel = starLevel;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }



}
