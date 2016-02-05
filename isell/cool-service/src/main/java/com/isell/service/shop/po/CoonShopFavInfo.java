package com.isell.service.shop.po;

import java.util.List;

import com.isell.service.shop.vo.CoonShopFav;

/**
 * 酷店收藏表vo
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-12]
 */
public class CoonShopFavInfo{
	
	private List<CoonShopFav> favList;

	public List<CoonShopFav> getFavList() {
		return favList;
	}

	public void setFavList(List<CoonShopFav> favList) {
		this.favList = favList;
	}
}