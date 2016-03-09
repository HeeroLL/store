package com.isell.app.dao;

import java.math.BigDecimal;
import java.util.List;

import com.isell.app.dao.entity.BillReqParam;
import com.isell.app.dao.entity.GoodsReqParam;
import com.isell.app.dao.entity.OrderDetail;
import com.isell.app.dao.entity.OrderGoods;
import com.isell.app.dao.entity.OrderParam;
import com.isell.app.dao.entity.OrderSumInfo;
import com.isell.app.dao.entity.PulldownEntity;
import com.isell.core.mybatis.Mapper;

@Mapper
public interface BmsManageMapper {
	/**
	 * 获取账单的dao接口
	 * @param orderParam
	 * @return
	 */
	List<OrderDetail> queryBmsMyOrder(BillReqParam orderParam);

	/**
	 * 获取订单总的条数dao接口
	 * 
	 * @param orderParam
	 * @return
	 */
	Integer queryBillCount(BillReqParam orderParam);
	 /**
	  * 统计商品的排行榜dao接口
	  * 
	  * @param orderParam 请求参数
	  * @return
	  */
	 List<OrderGoods> sumGoodsInfoList(GoodsReqParam params);
	 
	 /**
	  * 统计商品信息dao接口
	  * 
	  * @param orderParam 请求参数
	  * @return
	  */
	 List<OrderGoods> sumAllGoodsInfo(GoodsReqParam params);
	 
	 /**
	  * 统计订单信息dao接口
	  * 
	  * @param orderParam 请求参数
	  * @return
	  */
	 List<OrderSumInfo> sumAllOrderInfo(OrderParam params);
	 
	 /**
	  * 店里面的新品的数量dao接口
	  * 
	  * @param orderParam 请求参数
	  * @return
	  */
	 Integer getCountOfShopNewGoods(OrderParam params);
	 
	 /**
	  * 获取未结算订单金额dao接口
	  * 
	  * @param orderParam 请求参数
	  * @return
	  */
	 BigDecimal getUnsettleAmount(OrderParam params);
	 
	 /**
	  * 获取品牌下拉信息dao接口
	  * 
	  * @param orderParam 请求参数
	  * @return
	  */
	 List<PulldownEntity> getBrandAsPullDown();
}
