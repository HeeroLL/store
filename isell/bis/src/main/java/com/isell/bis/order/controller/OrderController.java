package com.isell.bis.order.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.config.BisConfig;
import com.isell.core.config.vo.AccessSystem;
import com.isell.core.util.JsonData;
import com.isell.core.util.JsonUtil;
import com.isell.core.util.MessageUtil;
import com.isell.core.util.Record;
import com.isell.service.order.po.CoolDistributionCarInfo;
import com.isell.service.order.po.CoolOrderExternal;
import com.isell.service.order.po.CoolOrderParam;
import com.isell.service.order.po.CoolOrderSelect;
import com.isell.service.order.po.CoolOrderWayBill;
import com.isell.service.order.po.CoolOrderWayBillReturn;
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
    
    private static final Logger log = Logger.getLogger(OrderController.class);
    
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
    
    /**
     * 系统配置类
     */
    @Resource
    private BisConfig config;
    
    // @RequestMapping("testaa")
    // @ResponseBody
    // public JsonData test(@RequestBody CoolOrder order) {
    // JsonData jsonData = new JsonData();
    // // orderImportService.CreateDate();
    // jsonData.setData(orderService.getCoolOrderById(order.getId()));
    // return jsonData;
    // }
    
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
    public JsonData updateCoolOrderCommon(@RequestBody Map<String, Object> param) {
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
    public JsonData updateCoolOrderCheck(@RequestBody Map<String, Object> param) {
        JsonData jsonData = new JsonData();
        // jsonData.setData(orderService.updateCoolOrderCheck(param).getColumns());
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
    public JsonData updateCoolOrderDelivery(@RequestBody Map<String, Object> param) {
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
    public JsonData updateCoolOrderRec(@RequestBody Map<String, Object> param) {
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
    public JsonData deleteCoolOrder(@RequestBody Map<String, Object> param) {
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
        orderService.getCoolOrderListSerial(param, jsonData);
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
    public JsonData getCoolOrderById(Integer id) {
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
     * 取消订单接口
     * 
     * @param jsonObj 订单参数
     * @return 是否取消成功
     */
    @RequestMapping("cancelCoolOrder")
    @ResponseBody
    public JsonData cancelCoolOrder(String jsonObj) {
        CoolOrder order = JsonUtil.readValue(jsonObj, CoolOrder.class);
        JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = orderService.cancelCoolOrder(order).getColumns();
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
        if (id != null) {
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
        } else {
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
        CoolOrderParam param = JsonUtil.readValue(jsonObj, CoolOrderParam.class);
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
     * 获取订单各状态数量接口
     * 
     * @param jsonObj 参数
     * @return 订单信息
     */
    @RequestMapping("getStateCount")
    @ResponseBody
    public JsonData getStateCount(String jsonObj) {
        CoolOrderParam param = JsonUtil.readValue(jsonObj, CoolOrderParam.class);
        JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = orderService.getStateCount(param).getColumns();
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
    public JsonData getSumCoolProductSales(@RequestBody Map<String, Object> param) {
        JsonData jsonData = new JsonData();
        orderService.getSumCoolProductSales(param, jsonData);
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
    public JsonData getSumCoonShopSales(@RequestBody Map<String, Object> param) {
        JsonData jsonData = new JsonData();
        orderService.getSumCoonShopSales(param, jsonData);
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
    public JsonData importCoolOrder(@RequestBody Map<String, Object> param) {
        JsonData jsonData = new JsonData();
        jsonData.setData(orderImportService.saveCoolOrderForImport(param).getColumns());
        return jsonData;
    }
    
    /**
     * 支付单报关
     * 
     * @param orderNo 订单号
     * @return 报关是否成功
     */
    @RequestMapping("sendPayInfo")
    @ResponseBody
    public JsonData sendPayInfo(String orderNo) {
        JsonData jsonData = new JsonData();
        Record record = orderService.sendPayInfo(orderNo);
        jsonData.setSuccess(record.getBoolean("success"));
        jsonData.setMsg(record.getStr("msg"));
        return jsonData;
    }
    
    /**
     * 推送订单信息
     * 
     * @param accessCode 系统接入码
     * @param jsonObj 订单信息
     * @return 订单入库是否成功
     */
    @RequestMapping("pushOrder")
    @ResponseBody
    public JsonData pushOrder(String accessCode, String jsonObj) {
        log.info("get pushOrder ok!accessCode:" + accessCode + ",jsonObj:" + jsonObj);
        JsonData jsonData = new JsonData();
        CoolOrder order = JsonUtil.readValue(jsonObj, CoolOrder.class);
        // 校验参数
        orderService.validateOrder(order);
        
        order.setArrears(1); // 欠费订单
        order.setoType(new Byte("1")); // pc 订单
        order.setOrderType(new Byte("1")); // 一件代发
        order.setState(CoolOrder.ORDER_STATE_0); // 待付款
        AccessSystem sys = config.getAccessSysMap().get(accessCode);
        if (sys != null) {
            order.setSupplier(sys.getShopId()); // 酷店id
            order.setmId(sys.getUserId()); // 用户id
        }
        if (order.getCreatetime() == null) {
            order.setCreatetime(new Date()); // 订单创建时间
            order.setPayTime(new Date(order.getCreatetime().getTime() + 60000)); // 订单支付时间
        }
        if (order.getPayTime() == null) {
            order.setPayTime(new Date(order.getCreatetime().getTime() + 60000)); // 订单支付时间
        }
        Record record = orderService.saveCoolOrder(order);
        jsonData.setSuccess(record.getBoolean("success"));
        return jsonData;
    }
    
    /**
     * 订单对外修改
     * 
     * @author 毛伟杰
     * @param jsonObj
     * @return
     */
    @RequestMapping("modifyOrder")
    @ResponseBody
    public JsonData modifyOrder(String accessCode, String jsonObj) {
        log.info("get modifyOrder ok!accessCode:" + accessCode + ",jsonObj:" + jsonObj);
        JsonData jsonData = new JsonData();
        CoolOrder order = JsonUtil.readValue(jsonObj, CoolOrder.class);
        if (order == null || StringUtils.isBlank(accessCode)) {
            throw new RuntimeException("exception.order.null");
        }
        AccessSystem sys = config.getAccessSysMap().get(accessCode);
        if (sys != null) {
            order.setSupplier(sys.getShopId()); // 酷店id
        }
        boolean flag = orderService.updateOrderPartByOrderNo(order);
        if (!flag)
            jsonData.setMsg("要修改的订单不存在！");
        jsonData.setSuccess(flag);
        return jsonData;
    }
    
    /**
     * 客户系统调用该接口获取订单信息数据
     * 
     * @author 毛伟杰
     * @param accessCode 系统接入码
     * @param jsonObj 订单信息
     * @return 订单信息数据
     */
    @RequestMapping("getOrderInfo")
    @ResponseBody
    public JsonData getOrderInfo(String accessCode, String jsonObj) {
        log.info("get getOrderInfo ok!accessCode:" + accessCode + ",jsonObj:" + jsonObj);
        JsonData jsonData = new JsonData();
        CoolOrderExternal order = JsonUtil.readValue(jsonObj, CoolOrderExternal.class);
        if (order == null || StringUtils.isBlank(order.getOrderNo())) {
            throw new RuntimeException("exception.order.null");
        }
        String shopId = null;
        AccessSystem sys = config.getAccessSysMap().get(accessCode);
        if (sys != null) {
            shopId = sys.getShopId(); // 酷店id
        }
        List<CoolOrderExternal> record = orderService.getOrderExternalByOrderOldNo(shopId, order.getOrderNo());
        jsonData.setSuccess(true);
        jsonData.setRows(record);
        return jsonData;
    }
    
    /**
     * 获取运单信息
     * 
     * @author 毛伟杰
     * @param accessCode 系统接入码
     * @param jsonObj 订单信息
     * @return 订单入库是否成功
     */
    @RequestMapping("getWaybill")
    @ResponseBody
    public JsonData getWaybill(String accessCode, String jsonObj) {
        log.info("get getWaybill ok!accessCode:" + accessCode + ",jsonObj:" + jsonObj);
        JsonData jsonData = new JsonData();
        CoolOrderWayBill order = JsonUtil.readValue(jsonObj, CoolOrderWayBill.class);
        AccessSystem sys = config.getAccessSysMap().get(accessCode);
        if (sys != null) {
            order.setSupplier(sys.getShopId()); // 酷店id
        }
        if (order == null
            || (StringUtils.isBlank(order.getOrderOldno()) && (StringUtils.isBlank(order.getBeginTime())
                || StringUtils.isBlank(order.getEndTime()) || StringUtils.isBlank(order.getTimeType())))) {
            throw new RuntimeException("exception.order.null");
        }
        List<CoolOrderWayBillReturn> records = orderService.getWayBill(order);
        for (CoolOrderWayBillReturn r : records) {
            if (StringUtils.isBlank(r.getSendStyle()))
                r.setSendStyle("还未发货");
            if (StringUtils.isBlank(r.getSendtime()))
                r.setSendtime("");
            if (StringUtils.isBlank(r.getWayBillNo()))
                r.setWayBillNo("没有运单号");
            if (StringUtils.isBlank(r.getCause()))
                r.setCause("");
        }
        jsonData.setSuccess(true);
        jsonData.setRows(records);
        return jsonData;
    }
    
    /**
     * 订单确认收货
     * 
     * @author 毛伟杰
     * @param accessCode 系统接入码
     * @param jsonObj 订单信息
     * @return 订单确认是否成功
     */
    @RequestMapping("confirmOrder")
    @ResponseBody
    public JsonData confirmOrder(String accessCode, String jsonObj) {
        log.info("get confirmOrder ok!accessCode:" + accessCode + ",jsonObj:" + jsonObj);
        JsonData jsonData = new JsonData();
        CoolOrder order = JsonUtil.readValue(jsonObj, CoolOrder.class);
        if (order == null || StringUtils.isBlank(order.getOrderOldno())) {
            throw new RuntimeException(MessageUtil.getMessage("exception.access.param-notnull", "orderOldNo"));
        }
        AccessSystem sys = config.getAccessSysMap().get(accessCode);
        if (sys != null) {
            order.setSupplier(sys.getShopId()); // 酷店id
        }
        boolean flag = orderService.confirmOrder(order);
        if (!flag) {
            jsonData.setMsg("订单不允许确认收货");
        }
        jsonData.setSuccess(flag);
        return jsonData;
    }
    
    /**
     * 订单取消
     * 
     * @author 毛伟杰
     * @param accessCode 系统接入码
     * @param jsonObj 订单信息
     * @return 订单取消是否成功
     */
    @RequestMapping("cancelOrder")
    @ResponseBody
    public JsonData cancelOrder(String accessCode, String jsonObj) {
        log.info("get cancelOrder ok!accessCode:" + accessCode + ",jsonObj:" + jsonObj);
        JsonData jsonData = new JsonData();
        CoolOrder order = JsonUtil.readValue(jsonObj, CoolOrder.class);
        AccessSystem sys = config.getAccessSysMap().get(accessCode);
        if (sys != null) {
            order.setSupplier(sys.getShopId()); // 酷店id
        }
        if (order == null || StringUtils.isBlank(order.getOrderOldno())) {
            throw new RuntimeException(MessageUtil.getMessage("exception.access.param-notnull", "orderOldNo"));
        }
        boolean flag = orderService.cancelOrder(order);
        if (!flag) {
            jsonData.setMsg("订单不允许被取消");
        }
        jsonData.setSuccess(flag);
        return jsonData;
    }
    
    /**
     * 购买支付流水接口
     * 
     * @param orderNo 订单编号集合
     * @return 购买流水结果
     */
    @RequestMapping("buyTradeNo")
    @ResponseBody
    public JsonData buyTradeNo(String orderNo) {
        return orderService.buyTradeNo(orderNo.split(","));
    }
}
