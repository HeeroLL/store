package com.isell.ei.logistics.youxinda.service.impl;

import org.springframework.stereotype.Service;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JaxbUtil;
import com.isell.core.util.JsonUtil;
import com.isell.ei.logistics.youxinda.bean.CreateOrderRequest;
import com.isell.ei.logistics.youxinda.bean.CreateOrderResponse;
import com.isell.ei.logistics.youxinda.bean.CreateProductRequest;
import com.isell.ei.logistics.youxinda.bean.CreateProductResponse;
import com.isell.ei.logistics.youxinda.service.YouxindaService;

/**
 * 友信达接口实现类
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-27]
 */
@Service("youxindaService")
public class YouxindaServiceImpl implements YouxindaService {

	/**
	 * 创建产品
	 */
	@Override
	public CreateProductResponse createProduct(CreateProductRequest createProductRequest) {
		// TODO Auto-generated method stub
		
        String xml = JaxbUtil.convertToXml(createProductRequest);
        //xml = xml.replace(" standalone=\"yes\"", "");
        System.out.println(xml);
        
        
        String result = HttpUtils.httpPost("http://test-import.ehaiwaigou.cn/default/product-soap", JsonUtil.writeValueAsString(createProductRequest));  
        System.out.println(result);
        CreateProductResponse response = JaxbUtil.converyToJavaBean(result, CreateProductResponse.class);
		return response;
	}

	/**
	 * 
	 */
	@Override
	public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {
		// TODO Auto-generated method stub
		String xml = JaxbUtil.convertToXml(createOrderRequest);
        //xml = xml.replace(" standalone=\"yes\"", "");
        System.out.println(xml);
		
        String result = HttpUtils.httpPost("", "");  
        CreateOrderResponse response = JaxbUtil.converyToJavaBean(result, CreateOrderResponse.class);
		return response;
	}

}
