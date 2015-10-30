package com.isell.ws.youxinda.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.isell.ws.youxinda.order.OrderInfo;
import com.isell.ws.youxinda.order.ProductDeatilType;

public class YxdWebServiceImplTest {

//	@Test
//	public void testCreateProduct() {
//		//fail("Not yet implemented");
//		
//		ProductInfo p = new ProductInfo();
//		p.setSkuNo("cool6667");
//		p.setSkuName("马油");
//		p.setSkuEnName_0020("mayou");
//		p.setSkuCategory(2);
//		p.setUOM("140");
//		p.setBarcodeType(0);
//		p.setNetWeight(new Float(1.3));
//		p.setWeight(new Float(1.6));
//		p.setIsFlash("1");
//		p.setProductDeclaredValue(new Float(99.99));
//		p.setProductLink("http://www.baidu.com");
//		p.setHsGoodsName("韩国mayou");
//		p.setOriginCountry("KR");
//		p.setBarcodeDefine("品牌");
//		p.setBrand("艾售精品");
//		p.setManufactory("艾售");
//		p.setSpecificationsAndModels("500g");
//		
//		
//		
//		YxdWebServiceImpl a = new YxdWebServiceImpl();		
//		a.createProduct(p);
//	}
	
	@Test
	public void testCreateOrder() {
		//fail("Not yet implemented");
		
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrderModel("0");
		orderInfo.setWarehouseCode("GZBYWLCK");
		orderInfo.setToWarehouseCode("GZBYWLCK");//目的仓库
		orderInfo.setOabCounty("CN");//收件人国家
		orderInfo.setOabStateName("江苏");
		orderInfo.setOabCity("常州");
		orderInfo.setOabDistrict("新北区");
		orderInfo.setSmCode("YTO");//运输方式
		orderInfo.setReferenceNo("CO201510300001");//交易订单号
		orderInfo.setOabName("王");
		orderInfo.setOabCompany("艾售");
		orderInfo.setOabPostcode("518000");
		orderInfo.setOabStreetAddress1("新北区软件园");
		orderInfo.setOabStreetAddress2("太湖东路9-1");
		orderInfo.setOabPhone("13512551269");
		orderInfo.setOabEmail("55461@qq.com");
		orderInfo.setGrossWt("0.15");
		orderInfo.setCurrencyCode("RMB");
		orderInfo.setIdType("1");
		orderInfo.setIdNumber("421087159905297651");
		orderInfo.setShippingFeeEstimate("10");
		orderInfo.setRemark(null);
		orderInfo.setOrderStatus("1");
		//order.set
		
		
		List<ProductDeatilType> orderProduct = new ArrayList<ProductDeatilType>();
		ProductDeatilType p = new ProductDeatilType();
		p.setProductSku("cool66655");
		p.setOpQuantity(12);
		orderProduct.add(p);
		
		orderInfo.setOrderProduct(orderProduct);
		
		YxdWebServiceImpl a = new YxdWebServiceImpl();		
		a.createOrder(orderInfo);
	}
	
//	@Test
//	public void getOrderByCode() {
//		//fail("Not yet implemented");
//		
//		String orderCode = "cool004";
//		
//		
//		
//		YxdWebServiceImpl a = new YxdWebServiceImpl();		
//		a.getOrderByCode(orderCode);
//	}

}
