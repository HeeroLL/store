package com.zebone.dip.empi.vo;

/**
 * 主索引信息统计对象
 *
 * @author 杨英
 * @version 2014-7-25 上午9:05
 */
public class EmpiCount {

    private String orgCode;
    private String orgName;
    //标识类型
    private String cardType;
    //查询起始时间
    private String startDt;
    //查询截止时间
    private String endDt;
    //统计数
    private String count;

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getStartDt() {
        return startDt;
    }

    public void setStartDt(String startDt) {
        this.startDt = startDt;
    }

    public String getEndDt() {
        return endDt;
    }

    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
