package com.isell.service.order.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.isell.core.common.StatisticsPo;
import com.isell.core.mybatis.Mapper;
import com.isell.service.order.po.CoolOrderExport;
import com.isell.service.order.po.CoolOrderExternal;
import com.isell.service.order.po.CoolOrderParam;
import com.isell.service.order.po.CoolOrderSelect;
import com.isell.service.order.po.CoolOrderWayBill;
import com.isell.service.order.po.CoolOrderWayBillReturn;
import com.isell.service.order.vo.CoolOrder;
import com.isell.service.order.vo.OrderReturn;
import com.isell.service.product.po.CoolProductInfo;

/**
 * 订单查询mapper
 * 
 * @author lilin
 * @version [版本号, 2015年9月24日]
 */
@Mapper
public interface CoolOrderMapper {
    /**
     * 根据主键查询
     * 
     * @param id 订单主键
     * @return 订单信息
     */
    CoolOrder getCoolOrderById(@Param("id") Integer id);
    
    /**
     * 根据会员主键跟状态获取定单数
     * 
     * @param mId  会员主键
     * @param state  订单状态
     * @return 订单数
     */
    public int getOrderCountBymIdAndState(@Param("mId")Integer mId, @Param("state")String state, @Param("orderType")Integer orderType);
    
    /**
     * 获取定单金额
     * 
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param shopId 酷店主键
     * @return 总金额
     */
    public BigDecimal getSumAmount(@Param("beginTime")String beginTime, @Param("endTime")String endTime, @Param("shopId")String shopId);
    
    /**
     * 获取定单数
     * 
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param shopId 酷店主键
     * @return 订单数
     */
    public int getOrderCount(@Param("beginTime")String beginTime, @Param("endTime")String endTime, @Param("shopId")String shopId);
    
    /**
     * 根据外部订单号查询
     * 
     * @param orderOldNo 外部订单号
     * @param supplier 店铺主键
     * @return 订单信息
     */
    List<CoolOrder> getCoolOrderByOrderOldNoList(@Param("orderOldNo")String orderOldNo, @Param("supplier")String supplier);
    
    /**
     * 获取一件代发购买过的商品列表
     * 
     * @param coolOrderParam 参数
     * @return 商品列表信息
     */
    List<CoolProductInfo> getCoolProductInfoList(RowBounds rowBounds, CoolOrderParam coolOrderParam);
    
    /**
     * 根据订单编号查询
     * 
     * @param orderNo 订单编号
     * @return 订单信息
     */
    CoolOrder getCoolOrderByOrderNo(String orderNo);
    
    /**
     * 根据物流单号查询
     * 
     * @param psCode 物流单号
     * @return 订单信息
     */
    CoolOrder getCoolOrderByPsCode(String psCode);
    
    /**
     * 根据条件查询订单列表
     * 
     * @param param 查询条件
     * @return 符合条件的订单列表
     */
    List<CoolOrder> getCoolOrderList(CoolOrder param);
    
    /**
     * 根据条件分页查询订单列表
     * 
     * @param param 查询条件
     * @return 符合条件的订单列表
     */
    List<CoolOrder> getCoolOrderPageList(RowBounds rowBounds, CoolOrderSelect orderSelect);
    
    /**
     * 根据物流单号查询
     * 
     * @param psCode 物流单号
     * @return
     */
    List<CoolOrder> getOrderByPsCode(@Param("psCode") String psCode);
    
    /**
     * 根据条件分页查询订单数量
     * 
     * @param param 查询条件
     * @return 符合条件的订单列表
     */
    int getCoolOrderPageListCount(CoolOrderSelect orderSelect);
    
    /**
     * 根据条件查询导出订单列表
     * 
     * @param param 查询条件
     * @return 符合条件的订单列表
     */
    List<CoolOrderExport> getCoolOrderListExport(CoolOrderSelect orderSelect);
    
    /**
     * 保存
     */
    int saveCoolOrder(CoolOrder coolOrder);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     * 
     * @param coolOrder 订单信息
     * @return 成功更新的条数
     */
    int updateCoolOrder(CoolOrder coolOrder);
    
    /**
     * 根据主键删除
     * 
     * @param id 订单id
     * @return 成功删除的条数
     */
    int deleteCoolOrder(@Param("id") Integer id);
    

    /**
     * 统计店铺销售额排名
     * 
     * @param param 查询条件
     * @return 符合条件的订单列表
     */
    List<CoolOrder> getSumCoonShopSales(CoolOrderSelect orderSelect);
    
    /**
     * 批量插入
     * @param orderList
     * @return
     */
    public int insertBatch(List<CoolOrder>  orderList);
    
    /**
     * 对外修改订单部分字段
     * @param order
     * @return
     */
	int updateOrderPartByOrderNo(CoolOrder order);
	/**
	 * 对外查询订单详情
	 * @param orderNo
	 * @param orderNo2 
	 * @return
	 */
	List<CoolOrderExternal> getOrderExternalByOrderOldNo(@Param("shopId")String shopId,@Param("orderNo")String orderNo);
	/**
	 * 保存post失败记录
	 * @param orderReturn
	 * @return
	 */
	int saveUnSuccessOrderReturn(OrderReturn orderReturn);
	/**
	 * 
	 * @param orderReturn
	 * @return
	 */
	int checkUnSuccessIsExist(OrderReturn orderReturn);
	/**
	 * 客户系统调用该接口获取运单相关信息
	 * @param order
	 * @param orderOldNos
	 * @return
	 */
	List<CoolOrderWayBillReturn> getWayBill(@Param("order")CoolOrderWayBill order, @Param("orderOldNos")String[] orderOldNos);
	
	/**
	 * 根据酷店主键获取今日购物人次
	 * @param supplier
	 * @return 今日购物人次
	 */
	int getBuyCount(String supplier);

	int updateStateByOlderOldNo(CoolOrder order);
	
	/**
     * 获取最近购物人次列表
     * 
     * @param coolOrderParam 参数
     * @return 最近购物人次
     */
    List<StatisticsPo> getBuyCountPage(RowBounds rowBounds, CoolOrderParam coolOrderParam);
    
//    /**
//     * 统计订单数
//     * 
//     * @param selectType 查询条件
//     * @return
//     */
//    List<StatisticsPo> getSumCoolOrderNumber(@Param("selectType") String selectType, @Param("time") String time);
//    
//    /**
//     * 统计销售额
//     * 
//     * @param selectType 查询条件
//     * @return
//     */
//    List<StatisticsPo> getSumCoolOrderSales(@Param("selectType") String selectType, @Param("time") String time);
    
}
