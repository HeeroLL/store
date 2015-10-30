package com.isell.ei.logistics.youxinda.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.util.JsonData;
import com.isell.ei.logistics.youxinda.bean.CreateOrderRequest;
import com.isell.ei.logistics.youxinda.bean.CreateOrderResponse;
import com.isell.ei.logistics.youxinda.bean.CreateProductRequest;
import com.isell.ei.logistics.youxinda.bean.CreateProductResponse;
import com.isell.ei.logistics.youxinda.service.YouxindaService;

/**
 * 友信达接口Controller
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-27]
 */
@RequestMapping("logistics/youxinda")
@Controller
public class YouxindaController {
	
	/**
	 * 友信达接口
	 */
	@Resource
	private YouxindaService youxindaService;
	
	/**
     * 创建产品
     *
     * @param createProductRequest 请求信息
     * @return 封装后的友信达返回信息
     */
    @RequestMapping("createProduct")
    @ResponseBody
    public JsonData createProduct(@RequestBody CreateProductRequest createProductRequest) {
    	CreateProductResponse createProductResponse = youxindaService.createProduct(createProductRequest);
        JsonData jsonData = new JsonData();
        jsonData.setData(createProductResponse);
        return jsonData;
    }
    
    /**
     * 创建订单
     *
     * @param createOrderRequest 请求信息
     * @return 封装后的友信达返回信息
     */
    @RequestMapping("createOrder")
    @ResponseBody
    public JsonData createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
    	CreateOrderResponse createOrderResponse = youxindaService.createOrder(createOrderRequest);
        JsonData jsonData = new JsonData();
        jsonData.setData(createOrderResponse);
        return jsonData;
    }

}
