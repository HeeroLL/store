package com.isell.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isell.app.dao.entity.CenterOrder;
import com.isell.app.dao.entity.CenterOrderParam;
import com.isell.app.dao.entity.SearchParam;
import com.isell.core.mybatis.Mapper;

@Mapper
public interface AppSellMapper {
	/**
	 * 查询卖家订单
	 * @param centerOrderParam
	 * @return
	 */
	public List<CenterOrder>querySellMyOrderPage(CenterOrderParam centerOrderParam);
	/**
	 * 商品批量配置新品（上/下）
	 * @param param
	 * @param goods
	 * @return
	 */
	int batchUpdateGoodsIsNew(@Param("param")SearchParam param, @Param("goods")String[] goods);
	/**
	 * 商品批量上架/下架功能
	 * @param param
	 * @param goods
	 * @return
	 */
	int batchUpdateGoodsIsAdd(SearchParam param);
	/**
	 * 删除店铺商品上架（下架）
	 * @param param
	 * @return
	 */
	int deleteShopGoodsForSell(SearchParam param);
}
