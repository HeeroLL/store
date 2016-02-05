package com.isell.service.order.po;

import java.util.List;

import com.isell.service.order.vo.CoonShopcart;

public class CoonShopCartParam {
	
	/**
	 * 购物车
	 */
	private List<CoonShopcart> shopCartList;

	public List<CoonShopcart> getShopCartList() {
		return shopCartList;
	}

	public void setShopCartList(List<CoonShopcart> shopCartList) {
		this.shopCartList = shopCartList;
	}
}
