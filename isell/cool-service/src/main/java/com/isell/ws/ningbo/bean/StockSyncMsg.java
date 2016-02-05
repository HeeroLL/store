package com.isell.ws.ningbo.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 宁波优贝信息--订单参数
 * 
 * @author wangpeng
 * @version [版本号, 2015年11月3日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Message")
@XmlType(propOrder = {})
public class StockSyncMsg {
    /**
     * 查询起始时间
     */
    @XmlElement(name = "Time")
    private String time;
    
    /**
     * 店铺ID
     */
    @XmlElement(name = "ShopId")
    private String shopId;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
}
