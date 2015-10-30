package com.isell.ws.zhengzhou.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 郑州海关订单请求信息
 * 
 * @author lilin
 * @version [版本号, 2015年10月21日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CBECMESSAGE")
@XmlType(propOrder = {})
public class Cbecmessage {
    /**
     * 消息头
     */
    @XmlElement(name = "MESSAGEHEAD")
    private Messagehead messagehead;
    
    /**
     * 消息体
     */
    @XmlElement(name = "MESSAGEBODY")
    private Messagebody messagebody;

    public Messagehead getMessagehead() {
        return messagehead;
    }

    public void setMessagehead(Messagehead messagehead) {
        this.messagehead = messagehead;
    }

    public Messagebody getMessagebody() {
        return messagebody;
    }

    public void setMessagebody(Messagebody messagebody) {
        this.messagebody = messagebody;
    }
}
