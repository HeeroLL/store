package com.isell.ps.wpwl.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.config.BisConfig;
import com.isell.core.util.JsonData;
import com.isell.core.util.JsonUtil;
import com.isell.core.util.Record;
import com.isell.ei.logistics.kuaidi100.service.KuaidiService;
import com.isell.ps.wpwl.vo.WpwlOrderInfo;
import com.isell.ps.wpwl.vo.WpwlOrderItem;
import com.isell.service.order.service.OrderService;
import com.isell.service.order.vo.CoolOrder;
import com.isell.service.order.vo.CoolOrderItem;
import com.isell.service.shop.service.CoonShopService;
import com.isell.service.shop.vo.CoonShop;

/**
 * 与杭州沃朴物联科技有限公司（防伪）商定的订单相关HTTP服务
 * 
 * @author lilin
 * @version [版本号, 2015年9月24日]
 */
@Controller
@RequestMapping("wpwl/order")
public class WpwlOrderController {
    /**
     * 订单接口
     */
    @Resource
    private OrderService orderService;
    
    /**
     * 快递100查询接口
     */
    @Resource
    private KuaidiService kuaidiService;
    
    /**
     * 酷店接口
     */
    @Resource
    private CoonShopService coonShopService;
    
    /**
     * 基础配置信息
     */
    @Resource
    private BisConfig config;
    
    /**
     * 获取订单信息接口
     * 
     * @param jsonObj 订单参数
     * @return 订单信息
     */
    @RequestMapping("getOrderByOrderNo")
    @ResponseBody
    public JsonData getOrderByOrderNo(String jsonObj) {
        CoolOrder order = JsonUtil.readValue(jsonObj, CoolOrder.class);
        order = orderService.getCoolOrderDetailByOrderNo(order.getOrderNo());
        if (order == null) {
            throw new RuntimeException("exception.order.null");
        }
        // 组装封装结果
        JsonData jsonData = new JsonData();
        jsonData.setData(getWpwlOrderInfoDetail(order));
        
        return jsonData;
    }
    
    /**
     * 获取订单基本信息接口
     * 
     * @param jsonObj 订单参数
     * @return 订单信息
     */
    @RequestMapping("getOrderBaseInfo")
    @ResponseBody
    public JsonData getOrderBaseInfo(String jsonObj) {
        CoolOrder order = JsonUtil.readValue(jsonObj, CoolOrder.class);
        order = orderService.getCoolOrderDetailByOrderNo(order.getOrderNo());
        if (order == null) {
            throw new RuntimeException("exception.order.null");
        }
        // 组装封装结果
        JsonData jsonData = new JsonData();
        WpwlOrderInfo wpwlOrderInfo = new WpwlOrderInfo();
        wpwlOrderInfo.setOrderNo(order.getOrderNo());
        wpwlOrderInfo.setAddress(order.getLocationP() + order.getLocationC() + order.getLocationA());
        wpwlOrderInfo.setCreatetime(order.getCreatetime());
        wpwlOrderInfo.setCustomerName(order.getLinkman());
        wpwlOrderInfo.setTotal(order.getTotal());
        if (order.getState() == 3 || order.getState() == 4) {
            wpwlOrderInfo.setReceiptStatus(true);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            wpwlOrderInfo.setReceiptTime(sdf.format(order.getUpdatetime()));
        } else {
            wpwlOrderInfo.setReceiptStatus(false);
        }
        jsonData.setData(wpwlOrderInfo);
        
        return jsonData;
    }
    
    /**
     * 验证订单号和手机号
     * 
     * @param jsonObj 订单参数
     * @return 订单信息
     */
    @RequestMapping("validateOrder")
    @ResponseBody
    public JsonData validateOrder(String jsonObj) {
        CoolOrder order = JsonUtil.readValue(jsonObj, CoolOrder.class);
        String mobile = order.getMobile();
        order = orderService.getCoolOrderDetailByOrderNo(order.getOrderNo());
        if (order == null || !order.getMobile().equals(mobile)) {
            throw new RuntimeException("exception.order.null");
        }
        // 组装封装结果
        JsonData jsonData = new JsonData();
        jsonData.setData(getWpwlOrderInfoDetail(order));
        
        return jsonData;
    }
    
    /**
     * 
     * @param jsonObj
     * @return
     */
    @RequestMapping("getposturl")
    @ResponseBody
    public JsonData getposturl(String jsonObj) {
        JsonData jsonData = new JsonData();
        jsonData.setSuccess(false);
        CoolOrder order = JsonUtil.readValue(jsonObj, CoolOrder.class);
        
        order = orderService.getCoolOrderDetailByOrderNo(order.getOrderNo());
        if (order == null) {
            throw new RuntimeException("exception.order.null");
        }
        if (order.getPsfs().length() > 0) {
            jsonData.setSuccess(true);
            String url =
                KuaidiService.WAP_URL + "?type=" + kuaidiService.queryPostTypeByName(order.getPsfs()) + "&postid="
                    + order.getPsCode();
            jsonData.setData(url);
        }
        return jsonData;
    }
    
    /**
     * 收货接口
     * 
     * @param jsonObj 订单参数
     * @return 订单信息
     */
    @RequestMapping("receiveOrder")
    @ResponseBody
    public JsonData receiveOrder(String jsonObj) {
        CoolOrder order = JsonUtil.readValue(jsonObj, CoolOrder.class);
        String mobile = order.getMobile();
        order = orderService.getCoolOrderDetailByOrderNo(order.getOrderNo());
        if (order == null || !order.getMobile().equals(mobile)) {
            throw new RuntimeException("exception.order.null");
        }
        // 组装封装结果
        JsonData jsonData = new JsonData();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", order.getId());
        Record record = orderService.updateCoolOrderRec(paramMap);
        jsonData.setSuccess(record.getBoolean("success"));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("receiptTime", record.get("receiptTime"));
        jsonData.setData(map);
        jsonData.setMsg(record.getStr("msg"));
        
        return jsonData;
    }
    
    public WpwlOrderInfo getWpwlOrderInfoDetail(CoolOrder order) {
        WpwlOrderInfo wpwlOrderInfo = new WpwlOrderInfo();
        wpwlOrderInfo.setId(String.valueOf(order.getId()));
        wpwlOrderInfo.setOrderNo(order.getOrderNo());
        wpwlOrderInfo.setAddress(order.getLocationP() + order.getLocationC() + order.getLocationA());
        wpwlOrderInfo.setCreatetime(order.getCreatetime());
        wpwlOrderInfo.setCustomerName(order.getLinkman());
        wpwlOrderInfo.setTotal(order.getTotal());
        if (order.getState() == 3 || order.getState() == 4) {
            wpwlOrderInfo.setReceiptStatus(true);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            wpwlOrderInfo.setReceiptTime(sdf.format(order.getUpdatetime()));
        } else {
            wpwlOrderInfo.setReceiptStatus(false);
        }
        
        String baseurl = "http://";
        String code = null;
        if (order.getSupplier() != null) {
            CoonShop shop = coonShopService.getCoonShopById(order.getSupplier());
            if (shop != null) {
                code = shop.getCode();
            }
        }
        if (code == null) {
            code = config.getDefaultShopId();
        }
        
        baseurl += code;
        baseurl += ".m." + config.getBaseDomain();
        
        if (order.getItemList() != null) {
            wpwlOrderInfo.setItems(new ArrayList<WpwlOrderItem>());
            for (CoolOrderItem item : order.getItemList()) {
                WpwlOrderItem wpwlOrderItem = new WpwlOrderItem();
                wpwlOrderItem.setName(item.getName());
                wpwlOrderItem.setLogo(config.getImgDomain() + item.getLogo());
                wpwlOrderItem.setCount(item.getCount());
                wpwlOrderItem.setPrice(item.getPrice());
                wpwlOrderItem.setUrl(baseurl + "/product/" + item.getgId());
                wpwlOrderItem.setAttribute(new ArrayList<String>());
                wpwlOrderItem.getAttribute().add("规格:" + item.getGg());
                
                wpwlOrderInfo.getItems().add(wpwlOrderItem);
            }
        }
        return wpwlOrderInfo;
    }
    
    /**
     * 获取订单编号信息接口
     * 
     * @param jsonObj 订单参数
     * @return 订单编号
     */
    @RequestMapping("getOrderNoByPsCode")
    @ResponseBody
    public JsonData getOrderNoByPsCode(String jsonObj) {
        CoolOrder order = JsonUtil.readValue(jsonObj, CoolOrder.class);
        Map<String, Object> resultMap = orderService.getOrderNoByPsCode(order.getPsCode()).getColumns();
        // 组装封装结果
        JsonData jsonData = new JsonData();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        jsonData.setData(resultMap);
        return jsonData;
    }
}
