package com.isell.ei.logistics.ecm.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.bis.auth.bean.RequestParameter;
import com.isell.core.web.JsonData;
import com.isell.ei.logistics.ecm.service.EcmService;
import com.isell.ei.logistics.ecm.vo.EcmCommodities;
import com.isell.ei.logistics.ecm.vo.EcmParam;
import com.isell.ei.logistics.ecm.vo.EcmResponse;

/**
 * 费舍尔ECM接口Controller
 * 
 * @author lilin
 * @version [版本号, 2015年7月20日]
 */
@Controller
@RequestMapping("logistics/ecm")
public class EcmController {
    /**
     * 费舍尔ECM服务接口
     */
    @Resource
    private EcmService ecmService;
    
    /**
     * ECM回调服务（订单生产状态回传）<br>
     * 这个是ECM的回调接口，需要继续回调对应系统相应接口<br>
     * 需要做权限认证
     * 
     * @param param 参数
     * @param jsonObj json参数
     * @return 返回值
     */
    @ResponseBody
    @RequestMapping("sendOrderStatus")
    public EcmResponse sendOrderStatus(EcmParam param, @RequestParam("JSON_OBJ")String jsonObj) {
        param.setJsonObj(jsonObj);
        return ecmService.sendOrderStatus(param);
    }
    
    /**
     * ECM回调服务（订单批量发货）<br>
     * 这个是ECM的回调接口，需要继续回调对应系统相应接口<br>
     * 需要做权限认证
     * 
     * @param param 参数
     * @param jsonObj json参数
     * @return 返回值
     */
    @ResponseBody
    @RequestMapping("sendShipOrder")
    public EcmResponse sendShipOrder(EcmParam param, @RequestParam("JSON_OBJ")String jsonObj) {
        param.setJsonObj(jsonObj);
        return ecmService.sendShipOrder(param);
    }
    
    /**
     * 向ECM推送商品
     * 
     * @param ecmOrders 订单列表
     * @return 封装后的ECM返回的处理结果
     */
    @ResponseBody
    @RequestMapping("sendCommodity")
    public JsonData sendCommodity(@RequestBody EcmCommodities param) {
        JsonData jsonData = new JsonData();
        EcmResponse response = ecmService.sendCommodity(param);
        jsonData.setSuccess("1000".equals(response.getRowset().getResultCode()));
        jsonData.setMsg(response.getRowset().getResultMsg());
        jsonData.setData(response);
        return jsonData;
    }
    
    /**
     * 向ECM推送订单
     * 
     * @param ecmOrders 订单列表
     * @return 封装后的ECM返回的处理结果
     */
    @ResponseBody
    @RequestMapping("pushSaleOrder")
    public JsonData pushSaleOrder(RequestParameter param) {
        JsonData jsonData = new JsonData();
        EcmResponse response = ecmService.pushSaleOrder(param);
        jsonData.setSuccess("1000".equals(response.getRowset().getResultCode()));
        jsonData.setMsg(response.getRowset().getResultMsg());
        jsonData.setData(response);
        return jsonData;
    }
}
