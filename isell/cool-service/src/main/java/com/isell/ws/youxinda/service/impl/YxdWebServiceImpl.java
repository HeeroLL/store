package com.isell.ws.youxinda.service.impl;

import java.util.List;

import javax.jws.WebService;
import javax.xml.ws.Holder;

import com.isell.ws.youxinda.order.OrderData;
import com.isell.ws.youxinda.order.OrderInfo;
import com.isell.ws.youxinda.order.ServiceForOrder_Service;
import com.isell.ws.youxinda.product.ErrorType;
import com.isell.ws.youxinda.product.HeaderRequest;
import com.isell.ws.youxinda.product.ProductInfo;
import com.isell.ws.youxinda.product.ServiceForProduct_Service;
import com.isell.ws.youxinda.service.YxdWebService;

/**
 * 友信达webservice接口实现
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-29]
 */
@WebService(endpointInterface = "com.isell.ws.youxinda.service.YxdWebService")  
public class YxdWebServiceImpl implements YxdWebService {

	/**
     * 创建普通产品
     * 
     * @param xml xml数据
     * @return 返回结果
     */
	@Override
	public String createProduct(ProductInfo productInfo) {
		// TODO Auto-generated method stub
		ServiceForProduct_Service service = new ServiceForProduct_Service();
		HeaderRequest headerRequest = new HeaderRequest();
		Holder<String> ask = new Holder<String>();
		Holder<String> message = new Holder<String>();
		Holder<List<ErrorType>> error = new Holder<List<ErrorType>>();
		
		service.getServiceForProductSOAP().createProduct(headerRequest, productInfo, ask, message, error);
		
		System.out.println("ask："+ask.value);
		System.out.println("message："+message.value);
		
		return null;
	}

	/**
     * 创建订单
     * 
     * @param orderInfo 订单信息
     * @return 返回结果
     */
	@Override
	public String createOrder(OrderInfo orderInfo) {
		// TODO Auto-generated method stub
		ServiceForOrder_Service service = new ServiceForOrder_Service();
		com.isell.ws.youxinda.order.HeaderRequest headerRequest = new com.isell.ws.youxinda.order.HeaderRequest();
		Holder<String> ask = new Holder<String>();
		Holder<String> message = new Holder<String>();
		Holder<String> orderCode = new Holder<String>();
		Holder<List<com.isell.ws.youxinda.order.ErrorType>> error = new Holder<List<com.isell.ws.youxinda.order.ErrorType>>();
		Holder<List<com.isell.ws.youxinda.order.ErrorType>> referenceNo = new Holder<List<com.isell.ws.youxinda.order.ErrorType>>();
		
		
		service.getServiceForOrderSOAP().createOrder(headerRequest, orderInfo, ask, message, orderCode, error, referenceNo);
		
		System.out.println("ask："+ask.value);
		System.out.println("message："+message.value);
		System.out.println("error："+error.value.get(0).getErrorMessage());
		return null;
	}

	/**
     * 查询单个订单信息
     * 
     * @param orderCode 订单信息
     * @return 返回结果
     */
	@Override
	public String getOrderByCode(String orderCode) {
		// TODO Auto-generated method stub
		ServiceForOrder_Service service = new ServiceForOrder_Service();
		com.isell.ws.youxinda.order.HeaderRequest headerRequest = new com.isell.ws.youxinda.order.HeaderRequest();
		Holder<String> ask = new Holder<String>();
		Holder<String> message = new Holder<String>();
		Holder<OrderData> data = new Holder<OrderData>();
		Holder<List<com.isell.ws.youxinda.order.ErrorType>> error = new Holder<List<com.isell.ws.youxinda.order.ErrorType>>();
		
		
		service.getServiceForOrderSOAP().getOrderByCode(headerRequest, orderCode, ask, message, data, error);
		
		System.out.println("ask："+ask.value);
		System.out.println("message："+message.value);
		
		return null;
	}

}
