package com.isell.ei.logistics.chongqing.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class GoodsDtcFlow {
	@XmlElement(name="SKU_INFO")
	private List<GoodsSkuInfo> skuInfo;

	public List<GoodsSkuInfo> getSkuInfo() {
		return skuInfo;
	}

	public void setSkuInfo(List<GoodsSkuInfo> skuInfo) {
		this.skuInfo = skuInfo;
	}
	
}
