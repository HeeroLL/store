package com.zebone.empi.oneCard;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 卡等变更参数对象
 *
 * @author 杨英
 * @version 2014-6-13 上午8:32
 */
@XStreamAlias("request")
public class LevelChangeRequest {
    //卡号
    private String cardNo;
    //卡类型 须为一级标识
    private String cardType;
    //提升的等级
    private String starLevel;

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

    public String getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(String starLevel) {
        this.starLevel = starLevel;
    }
}
