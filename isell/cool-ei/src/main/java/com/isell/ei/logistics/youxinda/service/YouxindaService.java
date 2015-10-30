package com.isell.ei.logistics.youxinda.service;

import com.isell.ei.logistics.youxinda.bean.CreateOrderRequest;
import com.isell.ei.logistics.youxinda.bean.CreateOrderResponse;
import com.isell.ei.logistics.youxinda.bean.CreateProductRequest;
import com.isell.ei.logistics.youxinda.bean.CreateProductResponse;

/**
 * 友信达接口
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-27]
 */
public interface YouxindaService {
	
	/**
	 * 创建商品
	 * 
	 * @param createProductRequest
	 * @return 响应信息
	 */
	CreateProductResponse createProduct(CreateProductRequest createProductRequest);
	
	/**
	 * 创建订单
	 * 
	 * @param createOrderRequest
	 * @return 响应信息
	 */
	CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);

}
