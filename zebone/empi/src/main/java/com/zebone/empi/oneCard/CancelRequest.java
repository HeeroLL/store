package com.zebone.empi.oneCard;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 卡注销参数对象
 *
 * @author 杨英
 * @version 2014-6-12 上午9:48
 */
@XStreamAlias("request")
public class CancelRequest {
    //卡编号
    private String cardNo;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
