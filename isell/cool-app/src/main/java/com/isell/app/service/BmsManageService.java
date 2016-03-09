package com.isell.app.service;

import java.math.BigDecimal;
import java.util.List;

import com.isell.app.dao.entity.BillReqParam;
import com.isell.app.dao.entity.GoodsReqParam;
import com.isell.app.dao.entity.OrderDetail;
import com.isell.app.dao.entity.OrderGoods;
import com.isell.app.dao.entity.OrderParam;
import com.isell.app.dao.entity.OrderSumInfo;
import com.isell.app.dao.entity.PulldownEntity;
/**
 * bms业务后台接口定义
 * 
 * @author Administrator
 *
 */
public interface BmsManageService {
	 /**
	  * 查询账单信息列表
	  * 
	  * @param BillReqParam 请求参数
	  * 		1: tradeNo 流水号
	  *         2: totalMin 账单金额下限
	  *         3: totalMax 账单金额上限
	  *         4: begintime 开始时间
	  *         5: endtime 结束时间
	  *         6: start 开始条数
	  *         7: limit 每次最大条数
	  *         8: userid 用户ID
	  * @return
	  */
	 List<OrderDetail> queryBmsMyOrder(BillReqParam billParam);
	 
	 /**
	  * 查询账单信息总条数
	  * 
	  * @param BillReqParam 请求参数
	  * 		1: tradeNo 流水号
	  *         2: totalMin 账单金额下限
	  *         3: totalMax 账单金额上限
	  *         4: begintime 开始时间
	  *         5: endtime 结束时间
	  *         6: userid 用户ID
	  * @return
	  */
	 Integer queryBillCount(BillReqParam billParam);
	 
	 /**
	  * 统计商品的排行榜
	  * 
	  * @param orderParam 请求参数
	  * 		1: goodsName商品名称
	  *         2: begintime 开始时间
	  *         3: endtime 结束时间
	  *         4: userid 用户ID
	  *         5: limit 开始条数
	  *         6: start 用户ID
	  *         7: orderByColumnType 0:金额1:排序
	  *         8: orderByType 倒序还是顺序
	  * @return List<OrderGoods>
	  */
	 List<OrderGoods> sumGoodsInfoList(GoodsReqParam params);
	 
	 /**
	  * 统计商品信息
	  * 
	  * @param orderParam 请求参数
	  * 		1: goodsName商品名称
	  *         2: begintime 开始时间
	  *         3: endtime 结束时间
	  *         4: userid 用户ID
	  * @return List<OrderGoods>
	  */
	 List<OrderGoods> sumAllGoodsInfo(GoodsReqParam params);
	 
	 /**
	  * 统计订单信息
	  * 
	  * @param orderParam 请求参数
	  *         1: begintime 开始时间
	  *         2: endtime 结束时间
	  *         3: userid 用户ID
	  * @return List<OrderSumInfo>
	  */
	 List<OrderSumInfo> sumAllOrderInfo(OrderParam params);
	 
	 /**
	  * 获取酷店的新品数量
	  * 
	  * @param orderParam 请求参数
	  *         1: userid 用户ID
	  * @return Integer
	  */
	 Integer getCountOfShopNewGoods(OrderParam params);
	 
	 /**
	  * 获取未结算订单金额dao接口
	  * 
	  * @param orderParam 请求参数
	  *         1: userid 用户ID
	  * @return BigDecimal
	  */
	 BigDecimal getUnsettleAmount(OrderParam params);
	 
	 /**
	  * 获取品牌下拉信息dao接口
	  * 
	  * @return
	  */
	 List<PulldownEntity> getBrandAsPullDown();
}
