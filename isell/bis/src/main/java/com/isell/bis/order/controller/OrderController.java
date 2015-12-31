package com.isell.bis.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.util.JsonData;
import com.isell.core.util.JsonUtil;
import com.isell.service.order.po.CoolDistributionCarInfo;
import com.isell.service.order.po.CoolOrderParam;
import com.isell.service.order.po.CoolOrderSelect;
import com.isell.service.order.po.CoonShopCartInfo;
import com.isell.service.order.po.CoonShopCartParam;
import com.isell.service.order.service.OrderImportService;
import com.isell.service.order.service.OrderService;
import com.isell.service.order.vo.CoolDistributionCar;
import com.isell.service.order.vo.CoolOrder;
import com.isell.service.order.vo.CoonShopcart;
import com.isell.service.product.po.CoolProductInfo;

/**
 * 订单controller
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-04]
 */
@Controller
@RequestMapping("order")
public class OrderController {
	
	/**
	 * 订单服务接口
	 */
	@Resource
	private OrderService orderService;
	
	/**
	 * 订单导入导出接口
	 */
	@Resource
	private OrderImportService orderImportService;
	
	public static void main(String[] args){
		System.out.println("asdf");
	}
	
	@RequestMapping("testaa")
    @ResponseBody
	public JsonData test(@RequestBody CoolOrder order) {
        JsonData jsonData = new JsonData();
        //orderImportService.CreateDate();
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
     * 获取订单列表接口
     *
     * @param jsonObj 订单参数
     * @return 订单信息
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("getOrderList")
    @ResponseBody
    public JsonData getOrderList(String jsonObj) {
    	CoolOrderSelect order = JsonUtil.readValue(jsonObj, CoolOrderSelect.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = orderService.getOrderListPage(order).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        }
        if (resultMap.get("orderList") != null) {
            jsonData.setRows((List<CoolOrder>)resultMap.get("orderList"));
            resultMap.remove("orderList");
        }
        jsonData.setData(resultMap);
        return jsonData;
    }
    
    /**
     * 获取订单详情接口
     *
     * @param jsonObj 订单参数
     * @return 订单信息
     */
	@RequestMapping("getOrderDetail")
    @ResponseBody
    public JsonData getOrderDetail(String jsonObj) {
		CoolOrder order = JsonUtil.readValue(jsonObj, CoolOrder.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = orderService.getOrderDetail(order).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        }
        jsonData.setData(resultMap);
        return jsonData;
    }
	
    /**
     * 删除订单接口
     *
     * @param jsonObj 订单参数
     * @return 是否删除成功
     */
	@RequestMapping("deleteOrder")
    @ResponseBody
	public JsonData deleteOrder(String jsonObj) {
		CoolOrder order = JsonUtil.readValue(jsonObj, CoolOrder.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = orderService.deleteOrder(order).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        }
        jsonData.setData(resultMap);
        return jsonData;
    }
	
	 /**
     * 获取一件代发进货单列表接口
     *
     * @param jsonObj 参数
     * @return 进货单列表
     */
	@SuppressWarnings("unchecked")
	@RequestMapping("getDistributionCartList")
    @ResponseBody
	public JsonData getDistributionCartList(String jsonObj) {
		CoolDistributionCar coolDistributionCar = JsonUtil.readValue(jsonObj, CoolDistributionCar.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = orderService.getDistributionCartListPage(coolDistributionCar).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        }
        if (resultMap.get("cartList") != null) { 
            jsonData.setRows((List<CoolDistributionCarInfo>)resultMap.get("cartList"));
            resultMap.remove("cartList");
        }
        jsonData.setData(resultMap);
        return jsonData;
    }
	
	/**
     * 获取一件代发购买过列表接口
     *
     * @param jsonObj 参数
     * @return 商品列表
     */
	@SuppressWarnings("unchecked")
	@RequestMapping("getBuyList")
    @ResponseBody
	public JsonData getBuyList(String jsonObj) {
		CoolOrderParam coolOrderParam = JsonUtil.readValue(jsonObj, CoolOrderParam.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = orderService.getBuyList(coolOrderParam).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        }
        if (resultMap.get("cartList") != null) { 
            jsonData.setRows((List<CoolProductInfo>)resultMap.get("cartList"));
            resultMap.remove("cartList");
        }
        jsonData.setData(resultMap);
        return jsonData;
    }
	
	/**
     * 确认下单接口
     * 
     * @param jsonObj 参数
     * @return 是否保存成功
     */
	@RequestMapping("saveOrder")
    @ResponseBody
	public JsonData saveOrder(String jsonObj) {
		CoolOrderParam coolOrderParam = JsonUtil.readValue(jsonObj, CoolOrderParam.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = orderService.saveCoolOrder(coolOrderParam).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        }
        jsonData.setData(resultMap);
        return jsonData;
    }
	
	/**
     * 确认收货接口
     * 
     * @param jsonObj 参数
     * @return 是否保存成功
     */
	@RequestMapping("updateOrderRec")
    @ResponseBody
	public JsonData updateOrder(String jsonObj) {
		CoolOrder coolOrder = JsonUtil.readValue(jsonObj, CoolOrder.class);
    	JsonData jsonData = new JsonData();
    	Integer id = coolOrder.getId();
    	if(id != null){
    		Map<String, Object> param = new HashMap<String, Object>();
    		param.put("id", id);
	        Map<String, Object> resultMap = orderService.updateCoolOrderRec(param).getColumns();
	        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
	        resultMap.remove("success");
	        if (resultMap.get("msg") != null) {
	            jsonData.setMsg((String)resultMap.get("msg"));
	            resultMap.remove("msg");
	        }
	        jsonData.setData(resultMap);
    	}else{
    		jsonData.setMsg("参数错误，订单主键不能为空");
    		jsonData.setSuccess(false);
    	}    	
        return jsonData;
    }
	
	/**
     * 保存评价接口
     * 
     * @param jsonObj 参数
     * @return 是否保存成功
     */
	@RequestMapping("saveOrderReview")
    @ResponseBody
	public JsonData saveOrderReview(String jsonObj) {
		CoolOrderParam param = JsonUtil.readValue(jsonObj, CoolOrderParam.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = orderService.saveOrderReview(param).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        }
        jsonData.setData(resultMap);
        return jsonData;
    }
	
	/**
     * 保存购物车接口
     * 
     * @param jsonObj 参数
     * @return 是否保存成功
     */
	@RequestMapping("saveShopCart")
    @ResponseBody
	public JsonData saveShopCart(String jsonObj) {
		CoonShopCartParam param = JsonUtil.readValue(jsonObj, CoonShopCartParam.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = orderService.saveShopCart(param).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        }
        jsonData.setData(resultMap);
        return jsonData;
    }
	
	/**
     * 获取购物车列表接口
     * 
     * @param jsonObj 参数
     * @return 购物车列表
     */
	@SuppressWarnings("unchecked")
	@RequestMapping("getShopCartList")
    @ResponseBody
	public JsonData getShopCartList(String jsonObj) {
		CoonShopcart coonShopcart = JsonUtil.readValue(jsonObj, CoonShopcart.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = orderService.getShopCartList(coonShopcart).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        } 
        if (resultMap.get("shopCartList") != null) {
            jsonData.setRows((List<CoonShopCartInfo>)resultMap.get("shopCartList"));
            resultMap.remove("shopCartList");
        } 
        jsonData.setData(resultMap);
        return jsonData;
    }
	
	/**
     * 删除购物车接口
     * 
     * @param jsonObj 参数
     * @return 是否删除成功
     */
	@RequestMapping("deleteShopCart")
    @ResponseBody
	public JsonData deleteShopCart(String jsonObj) {
		CoonShopcart coonShopcart = JsonUtil.readValue(jsonObj, CoonShopcart.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = orderService.deleteShopCart(coonShopcart).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        } 
        jsonData.setData(resultMap);
        return jsonData;
    }
	
	/**
     * 确认下单接口（不保存订单表）
     * 
     * @param jsonObj 参数
     * @return 订单信息
     */
	@RequestMapping("createOrder")
    @ResponseBody
	public JsonData createOrder(String jsonObj) {
		CoolOrderParam param  = JsonUtil.readValue(jsonObj, CoolOrderParam.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = orderService.getCoolOrderReturn(param).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        } 
        jsonData.setData(resultMap);
        return jsonData;
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
	
	/**
	 * 导入订单
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("importCoolOrder")
    @ResponseBody
	public JsonData importCoolOrder(@RequestBody Map<String,Object> param) {
		JsonData jsonData = new JsonData();
		jsonData.setData(orderImportService.saveCoolOrderForImport(param).getColumns());
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
