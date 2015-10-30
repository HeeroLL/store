package com.isell.ws.zhengzhou.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 消息体
 * 
 * @author lilin
 * @version [版本号, 2015年10月21日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {})
public class Messagebody {
    /**
     * 消息主体
     */
    @XmlElement(name = "BODYMASTER")
    private Bodymaster bodymaster;
    
    /**
     * 消息详情
     */
    @XmlElement(name = "BODYDETAIL")
    private Bodydetail bodydetail;

    public Bodymaster getBodymaster() {
        return bodymaster;
    }

    public void setBodymaster(Bodymaster bodymaster) {
        this.bodymaster = bodymaster;
    }

    public Bodydetail getBodydetail() {
        return bodydetail;
    }

    public void setBodydetail(Bodydetail bodydetail) {
        this.bodydetail = bodydetail;
    }
}
