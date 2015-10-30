package com.isell.ws.youxinda.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.isell.ws.youxinda.order.OrderInfo;
import com.isell.ws.youxinda.product.ProductInfo;

/**
 * 友信达webservice接口
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-29]
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface YxdWebService {
	
	/**
     * 创建普通产品
     * 
     * @param productInfo 产品信息
     * @return 返回结果
     */
    @WebMethod
    String createProduct(ProductInfo productInfo);
    
    /**
     * 创建订单
     * 
     * @param orderInfo 订单信息
     * @return 返回结果
     */
    @WebMethod
    String createOrder(OrderInfo orderInfo);
    
    /**
     * 查询单个订单信息
     * 
     * @param orderCode 订单信息
     * @return 返回结果
     */
    @WebMethod
    String getOrderByCode(String orderCode);
    
    

}
