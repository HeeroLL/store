package com.isell.ei.pay.alipay.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * 响应中response节点
 * 
 * @author lilin
 * @version [版本号, 2015年11月2日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class AliResponse {
    /**
     * alipay节点
     */
    private AlipayInfo alipay;

    public AlipayInfo getAlipay() {
        return alipay;
    }

    public void setAlipay(AlipayInfo alipay) {
        this.alipay = alipay;
    }
}
