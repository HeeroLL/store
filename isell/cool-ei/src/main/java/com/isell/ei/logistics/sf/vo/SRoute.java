package com.isell.ei.logistics.sf.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * 路由查询响应报文
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {}) 
public class SRoute {

	/** 路由节点发生的时间 */
	@XmlAttribute(name="accept_time")
	private String acceptTime;
	/** 路由节点发生的地点 */
	@XmlAttribute(name="accept_address")
	private String acceptAddress;
	/** 路由节点具体描述 */
	@XmlAttribute
    private String remark;
	/** 路由节点操作码 */
	@XmlAttribute
    private String opcode;
	
	public String getAcceptTime() {
        return acceptTime;
    }
    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }
    public String getAcceptAddress() {
        return acceptAddress;
    }
    public void setAcceptAddress(String acceptAddress) {
        this.acceptAddress = acceptAddress;
    }
    public String getOpcode() {
        return opcode;
    }
    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }
    public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
