package com.zebone.empi.oneCard;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 一卡通查询参数对象
 *
 * @author 杨英
 * @version 2014-6-11 上午11:10
 */
@XStreamAlias("request")
public class SearchRequest {

    //卡编号
    private String cardNo;
    //卡类型
    private String cardType;

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
}
