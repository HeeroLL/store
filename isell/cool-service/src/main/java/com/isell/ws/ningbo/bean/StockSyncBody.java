package com.isell.ws.ningbo.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 * 库存同步查询body部分
 * 
 * @author lilin
 * @version [版本号, 2015年11月3日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {})
public class StockSyncBody {
	/** 商品集合 */
    @XmlElementWrapper(name ="Goods")
    @XmlElement(name ="Good")
    private List<StockSyncGood> goods;

	public List<StockSyncGood> getGoods() {
		return goods;
	}

	public void setGoods(List<StockSyncGood> goods) {
		this.goods = goods;
	}
}
