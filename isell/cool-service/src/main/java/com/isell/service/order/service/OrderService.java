package com.isell.service.order.service;

import java.util.List;
import java.util.Map;

import com.isell.core.mybatis.page.PageInfo;
import com.isell.core.util.JsonData;
import com.isell.core.util.Record;
import com.isell.service.order.po.CoolOrderParam;
import com.isell.service.order.po.CoolOrderSelect;
import com.isell.service.order.vo.CoolOrder;

/**
 * 订单服务接口
 * 
 * @author lilin
 * @version [版本号, 2015年9月24日]
 */
public interface OrderService {
    /**
     * 根据主键获取订单
     *
     * @param id id
     * @return 订单信息
     */
    CoolOrder getCoolOrderById(Integer id);
    
    /**
     * 根据订单编号获取订单
     *
     * @param orderNo 订单编号
     * @return 订单信息
     */
    CoolOrder getCoolOrderByOrderNo(String orderNo);
    
    /**
     * 根据主键获取订单及详情
     *
     * @param id id
     * @return 订单信息及详情
     */
    CoolOrder getCoolOrderDetailById(Integer id);
    
    /**
     * 根据订单编号获取订单及详情
     *
     * @param orderNo 订单编号
     * @return 订单信息及详情
     */
    CoolOrder getCoolOrderDetailByOrderNo(String orderNo);
    
    /**
     * 根据物流单号查询订单详情
     * 
     * @param psCode 物流单号
     * @return 订单信息及详情
     */
    CoolOrder getCoolOrderDetailByPsCode(String psCode);
    
    /**
     * 根据条件查询订单列表
     *
     * @param param 查询条件
     * @return 符合条件的订单列表
     */
    List<CoolOrder> getCoolOrderList(CoolOrder param);
    
    /**
     * 取消订单
     *
     * @param ids 订单主键集合
     */
    void cancelOrder(Integer... ids);
    
    /**
     * 确认订单
     *
     * @param param 订单参数
     * @return 订单、商品信息
     */
    Record getCoolOrderReturn(CoolOrderParam param);
    
    /**
     * 保存订单
     *
     * @param param 订单参数
     * @return 
     */
    Record saveCoolOrder(CoolOrderParam param);
    
    /**
     * 修改订单（通用）
     * 
     * @param param
     * @return Record
     */
    Record updateCoolOrderCommon(Map<String,Object> param);
    
    /**
     * 修改订单（支付成功）
     * 
     * @param param
     * @return Record
     */
    Record updateCoolOrderCheck(Map<String,Object> param);
    
    /**
     * 支付宝付款更新订单
     * 
     * @param param
     * @return Record
     */
    Record updateCoolOrderZfb(Map<String,Object> param);
    
    /**
     * 修改订单（订单发货）0 自提，1 圆通，2 费舍尔，10 宁波艾购保税仓，11 宁波优贝保税仓，20 郑州海关
     * 
     * @param param
     * @return Record
     */
    Record updateCoolOrderDelivery(Map<String,Object> param);
    
    /**
     * 修改订单（签收）
     * 
     * @param param
     * @return Record
     */
    Record updateCoolOrderRec(Map<String,Object> param);
    
    /**
     * 删除订单
     * 
     * @param param
     * @return Record
     */
    Record deleteCoolOrder(Map<String,Object> param);
    
    /**
     * 分页查询订单列表
     *
     * @param orderSelect 查询条件
     * @return 分页信息
     */
    PageInfo<CoolOrder> getCoolOrderListPage(CoolOrderSelect orderSelect);
    
    
    /**
     * 查询订单流水
     * 
     * @param param
     * @param jsonData
     */
    void getCoolOrderListSerial(CoolOrder order,JsonData jsonData);
    
    /**
     * 通用更新
     * 
     * @param param
     * @return Record
     */
    void updateOrder(CoolOrder param);
    
    /**
	 * 统计商品销量
	 * 
	 * @param param
	 */
	void getSumCoolProductSales(Map<String,Object> param,JsonData jsonData);
	
	/**
	 * 统计店铺销量排名
	 * 
	 * @param param
	 */
	void getSumCoonShopSales(Map<String,Object> param,JsonData jsonData);
	
	/**
	 * 导入订单
	 * 
	 * @param param
	 */
	Record saveCoolOrderForImport(Map<String,Object> param);
	
//	 /**
//     * 导出订单
//     * 
//     * @param param
//     * @param jsonData
//     */
//    void exportCoolOrderList(CoolOrderSelect orderSelect,JsonData jsonData);
//	
//	/**
//	 * 统计订单数
//	 * 
//	 * @param param
//	 */
//	void getSumCoolOrderNumber(Map<String,Object> param,JsonData jsonData);
//	
//	/**
//	 * 统计销售额
//	 * 
//	 * @param param
//	 */
//	void getSumCoolOrderSales(Map<String,Object> param,JsonData jsonData);
//	
	
//	
//	/**
//     * 导入订单（银科金典）
//     * 
//     * @param param
//     * @return Record
//     */
//    Record saveCoolOrderYkjd(Map<String,Object> param);

}
