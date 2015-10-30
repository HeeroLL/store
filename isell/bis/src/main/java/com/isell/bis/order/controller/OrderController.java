package com.isell.bis.order.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.util.JsonData;
import com.isell.service.order.po.CoolOrderParam;
import com.isell.service.order.service.OrderService;
import com.isell.service.order.vo.CoolOrder;

/**
 * 订单controller
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-04]
 */
@Controller
@RequestMapping("order")
public class OrderController {
	
	@Resource
	private OrderService orderService;
	
	public static void main(String[] args){
		System.out.println("asdf");
	}
	
	@RequestMapping("testaa")
    @ResponseBody
	public JsonData test(@RequestBody CoolOrder order) {
        JsonData jsonData = new JsonData();
        jsonData.setData(orderService.getCoolOrderById(order.getId()));
        return jsonData;
    }
	
	/**
	 * 确认订单（未保存到数据库）
	 * 
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("createCoolOrder")
    @ResponseBody
	public JsonData createCoolOrder(@RequestBody CoolOrderParam param) {
        JsonData jsonData = new JsonData();
        jsonData.setData(orderService.getCoolOrderReturn(param).getColumns());
        return jsonData;
    }
	
	/**
	 * 保存订单
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("saveCoolOrder")
    @ResponseBody
	public JsonData saveCoolOrder(@RequestBody CoolOrderParam param) {
        JsonData jsonData = new JsonData();
        jsonData.setData(orderService.saveCoolOrder(param).getColumns());
        return jsonData;
    }
	
	/**
	 * 修改订单（通用）
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("updateCoolOrderCommon")
    @ResponseBody
	public JsonData updateCoolOrderCommon(@RequestBody Map<String,Object> param) {
        JsonData jsonData = new JsonData();
        jsonData.setData(orderService.updateCoolOrderCommon(param).getColumns());
        return jsonData;
    }
	
	
	
	/**
	 * 修改订单（支付成功）[暂时不用]
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("updateCoolOrderCheck")
    @ResponseBody
	public JsonData updateCoolOrderCheck(@RequestBody Map<String,Object> param) {
        JsonData jsonData = new JsonData();
        //jsonData.setData(orderService.updateCoolOrderCheck(param).getColumns());
        return jsonData;
    }
	
	/**
	 * 修改订单（订单发货）
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("updateCoolOrderDelivery")
    @ResponseBody
	public JsonData updateCoolOrderDelivery(@RequestBody Map<String,Object> param) {
        JsonData jsonData = new JsonData();
        jsonData.setData(orderService.updateCoolOrderDelivery(param).getColumns());
        return jsonData;
    }
	
	/**
	 * 修改订单（订单签收）
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("updateCoolOrderRec")
    @ResponseBody
	public JsonData updateCoolOrderRec(@RequestBody Map<String,Object> param) {
        JsonData jsonData = new JsonData();
        jsonData.setData(orderService.updateCoolOrderRec(param).getColumns());
        return jsonData;
    }
	
	/**
	 * 删除订单
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("deleteCoolOrder")
    @ResponseBody
	public JsonData deleteCoolOrder(@RequestBody Map<String,Object> param) {
        JsonData jsonData = new JsonData();
        jsonData.setData(orderService.deleteCoolOrder(param).getColumns());
        return jsonData;
    }
	
	/**
	 * 查询订单流水
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("getCoolOrderListSerial")
    @ResponseBody
	public JsonData getCoolOrderListSerial(@RequestBody CoolOrder param) {
        JsonData jsonData = new JsonData();
		orderService.getCoolOrderListSerial(param,jsonData);
        return jsonData;
    }
	
	/**
	 * 获取根据id获取订单信息
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("getCoolOrderById")
	@ResponseBody
	public JsonData getCoolOrderById(Integer id){
	    JsonData jsonData = new JsonData();
	    jsonData.setData(orderService.getCoolOrderDetailById(id));
        return jsonData;
	   // return null;
	}
	
	/**
	 * 商品销量统计
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("getSumCoolProductSales")
    @ResponseBody
	public JsonData getSumCoolProductSales(@RequestBody Map<String,Object> param) {
        JsonData jsonData = new JsonData();
        orderService.getSumCoolProductSales(param,jsonData);
	    return jsonData;
    }
	
	/**
	 * 统计店铺销量排名
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("getSumCoonShopSales")
    @ResponseBody
	public JsonData getSumCoonShopSales(@RequestBody Map<String,Object> param) {
        JsonData jsonData = new JsonData();
        orderService.getSumCoonShopSales(param,jsonData);
	    return jsonData;
    }
	
//	/**
//     * 导出订单
//     * 
//     * @param param
//     * @return
//     */
//    @RequestMapping("exportCoolOrderList")
//    @ResponseBody
//    public JsonData exportCoolOrderList(@RequestBody CoolOrderSelect param) {
//        JsonData jsonData = new JsonData();
//        orderService.exportCoolOrderList(param,jsonData);
//        return jsonData;
//    }
//	
//	/**
//	 * 统计订单数
//	 * 
//	 * @param param
//	 * @return
//	 */
//	@RequestMapping("getSumCoolOrderNumber")
//    @ResponseBody
//	public JsonData getSumCoolOrderNumber(@RequestBody Map<String,Object> param) {
//        JsonData jsonData = new JsonData();
//        orderService.getSumCoolOrderNumber(param,jsonData);
//	    return jsonData;
//    }
//	
//	/**
//	 * 统计销售额
//	 * 
//	 * @param param
//	 * @return
//	 */
//	@RequestMapping("getSumCoolOrderSales")
//    @ResponseBody
//	public JsonData getSumCoolOrderSales(@RequestBody Map<String,Object> param) {
//        JsonData jsonData = new JsonData();
//        orderService.getSumCoolOrderSales(param,jsonData);
//	    return jsonData;
//    }
//	
	
//	
//	/**
//	 * 导入订单（银科金典）
//	 * 
//	 * @param param
//	 * @return
//	 */
//	@RequestMapping("importCoolOrderYkjd")
//    @ResponseBody
//	public JsonData importCoolOrderYkjd(@RequestBody Map<String,Object> param) {
//        JsonData jsonData = new JsonData();
//        jsonData.setData(orderService.saveCoolOrderYkjd(param));        
//	    return jsonData;
//    }
	
	
	
}
