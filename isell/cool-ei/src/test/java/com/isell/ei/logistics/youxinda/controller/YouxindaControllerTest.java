package com.isell.ei.logistics.youxinda.controller;

import org.junit.Test;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;
import com.isell.ei.logistics.youxinda.bean.Body;
import com.isell.ei.logistics.youxinda.bean.CreateOrder;
import com.isell.ei.logistics.youxinda.bean.CreateOrderRequest;
import com.isell.ei.logistics.youxinda.bean.CreateProduct;
import com.isell.ei.logistics.youxinda.bean.CreateProductRequest;
import com.isell.ei.logistics.youxinda.bean.HeaderRequest;
import com.isell.ei.logistics.youxinda.bean.OrderInfo;
import com.isell.ei.logistics.youxinda.bean.ProductInfo;

public class YouxindaControllerTest {

	@Test
	public void testCreateProduct() {
		//fail("Not yet implemented");
		
		CreateProductRequest createProductRequest = new CreateProductRequest();		
		
		Body body = new Body();
		CreateProduct createProduct = new CreateProduct();
		HeaderRequest headerRequest = new HeaderRequest();
		
		ProductInfo productInfo = new ProductInfo();
		productInfo.setSkuNo("1");
		productInfo.setSkuName("马油");
		productInfo.setSkuEnName("mayou");
		productInfo.setSkuCategory(2);
		productInfo.setUOM(7);
		productInfo.setBarcodeType(0);
		productInfo.setNetWeight(new Float(1));
		productInfo.setWeight(new Float(1.5));
		productInfo.setIsFlash(1);
		productInfo.setProductDeclaredValue(new Float(99.99));
		productInfo.setProductLink("http://www.i-coolshop.com");
		productInfo.setHsGoodsName("mayouh");
		productInfo.setOriginCountry("CN");
		productInfo.setBrand("品牌");
		productInfo.setManufactory("小酷儿");
		
		createProduct.setProductInfo(productInfo);
		createProduct.setHeaderRequest(headerRequest);
		body.setCreateProduct(createProduct);
		createProductRequest.setBody(body);
		
		String result = HttpUtils.httpPost("http://localhost:18080/bis/logistics/youxinda/createProduct",
	                JsonUtil.writeValueAsString(createProductRequest)); 
        System.out.println(result);
	}
	
	@Test
	public void testCreateOrder(){
		CreateOrderRequest request = new CreateOrderRequest();
		Body body = new Body();
		CreateOrder createOrder = new CreateOrder();
		HeaderRequest headerRequest = new HeaderRequest();
		OrderInfo orderInfo = new OrderInfo();
		
		
		
		
		createOrder.setOrderInfo(orderInfo);
		createOrder.setHeaderRequest(headerRequest);
		body.setCreateOrder(createOrder);
		request.setBody(body);
		
		String result = HttpUtils.httpPost("http://localhost:18080/bis/logistics/youxinda/createOrder",
                JsonUtil.writeValueAsString(request)); 
		System.out.println(result);		
	}

}
