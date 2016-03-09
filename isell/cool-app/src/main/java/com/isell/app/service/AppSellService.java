package com.isell.app.service;

import java.util.List;

import com.isell.app.dao.entity.CenterOrder;
import com.isell.app.dao.entity.CenterOrderParam;
import com.isell.app.dao.entity.Product;
import com.isell.app.dao.entity.SearchParam;

public interface AppSellService {
	/**
	 * 店铺查询订单
	 * @param centerOrderParam
	 * @return
	 */
	List<CenterOrder>querySellMyOrderPage(CenterOrderParam centerOrderParam);
	/**
	 * 商品批量配置新品（上/下）
	 * @param param
	 * @return
	 */
	int batchUpdateGoodsIsNew(SearchParam param);
	/**
	 * 商品批量上架/下架功能
	 * @param param
	 * @return
	 */
	int batchUpdateGoodsIsAdd(SearchParam param);
	/**
	 * 获取已上架/未上架/新品上线商品（搜索）
	 * @param param
	 * @return
	 */
	List<Product>querySellGoodsListSearch(SearchParam param);
	
}
