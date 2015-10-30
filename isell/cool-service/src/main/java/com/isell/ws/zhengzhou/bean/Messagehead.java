package com.isell.ws.zhengzhou.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 消息头
 * 
 * @author lilin
 * @version [版本号, 2015年10月21日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {})
public class Messagehead {
    /**
     * messageid
     */
    @XmlElement(name = "MESSAGEID")
    private String messageid;
    
    /**
     * messagetype
     */
    @XmlElement(name = "MESSAGETYPE")
    private String messagetype = "IEPT302";
    
    /**
     * senderid
     */
    @XmlElement(name = "SENDERID")
    private String senderid = "1102013201";
    
    /**
     * receiverid
     */
    @XmlElement(name = "RECEIVERID")
    private String receiverid = "0100";
    
    /**
     * sendtime
     */
    @XmlElement(name = "SENDTIME")
    private String sendtime;
    
    /**
     * seqno
     */
    @XmlElement(name = "SEQNO")
    private String seqno;
    
    /**
     * 模式代码 1： 一般模式 2： 保税模式
     */
    @XmlElement(name = "BILLMODE")
    private String billmode = "2";
    
    public String getMessageid() {
        return messageid;
    }
    
    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }
    
    public String getMessagetype() {
        return messagetype;
    }
    
    public void setMessagetype(String messagetype) {
        this.messagetype = messagetype;
    }
    
    public String getSenderid() {
        return senderid;
    }
    
    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }
    
    public String getReceiverid() {
        return receiverid;
    }
    
    public void setReceiverid(String receiverid) {
        this.receiverid = receiverid;
    }
    
    public String getSendtime() {
        return sendtime;
    }
    
    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }
    
    public String getSeqno() {
        return seqno;
    }
    
    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }
    
    public String getBillmode() {
        return billmode;
    }
    
    public void setBillmode(String billmode) {
        this.billmode = billmode;
    }
}
