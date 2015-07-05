package com.sell.ei.logistics.sf.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 订单结果查询响应信息
 * 
 * @author lilin
 * @version [版本号, 2015年7月5日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SOrderSearchResponse {
    /** 客户订单id */
    private String orderid;
    
    /** 顺丰运单号 */
    private String mailno;
    
    /** 原寄地区域代码 */
    private String origincode;
    
    /** 目的地区域代码 */
    private String destcode;
    
    /**
     * 筛单结果：  1：人工确认  2：可收派  3：不可以收派
     */
    @XmlElement(name = "filter_result")
    private String filterResult;
    
    /**
     * 如果filter_result=3 时为必填，不可以 收派的原因代码：  1：收方超范围  2：派方超范围  3-：其它原因
     */
    private String remark;
    
    public String getOrderid() {
        return orderid;
    }
    
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
    
    public String getMailno() {
        return mailno;
    }
    
    public void setMailno(String mailno) {
        this.mailno = mailno;
    }
    
    public String getOrigincode() {
        return origincode;
    }
    
    public void setOrigincode(String origincode) {
        this.origincode = origincode;
    }
    
    public String getDestcode() {
        return destcode;
    }
    
    public void setDestcode(String destcode) {
        this.destcode = destcode;
    }
    
    public String getFilterResult() {
        return filterResult;
    }
    
    public void setFilterResult(String filterResult) {
        this.filterResult = filterResult;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
