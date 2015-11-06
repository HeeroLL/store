package com.isell.ws.ningbo.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 物流单查询结果信息
 * 
 * @author lilin
 * @version [版本号, 2015年11月3日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Message")
@XmlType(propOrder = {})
public class OrderSearchResult {
    /**
     * T/F
     */
    @XmlElement(name = "Result")
    private String result;
    
    /**
     * 若为F，显示失败信息
     */
    @XmlElement(name = "ResultMsg")
    private String resultMsg;
    
    /**
     * body部分
     */
    @XmlElement(name = "Body")
    private OrderSearchBody body;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public OrderSearchBody getBody() {
        return body;
    }

    public void setBody(OrderSearchBody body) {
        this.body = body;
    }
}
