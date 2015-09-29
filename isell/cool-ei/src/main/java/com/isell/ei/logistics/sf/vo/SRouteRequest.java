package com.isell.ei.logistics.sf.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * 路由查询请求报文
 * 
 * @author lilin
 * @version [版本号, 2015年7月1日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {}) 
public class SRouteRequest {

	/** 查询号类别 */
	@XmlAttribute(name="tracking_type")
	private int trackingType = 1;
	/** 物流查询号 */
	@XmlAttribute(name="tracking_number")
	private String trackingNumber;
	/** 路由查询类别 */
	@XmlAttribute(name="method_type")
    private int methodType = 1;
	
    public int getTrackingType() {
        return trackingType;
    }
    public void setTrackingType(int trackingType) {
        this.trackingType = trackingType;
    }
    public String getTrackingNumber() {
        return trackingNumber;
    }
    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
    public int getMethodType() {
        return methodType;
    }
    public void setMethodType(int methodType) {
        this.methodType = methodType;
    }
	
	
}
