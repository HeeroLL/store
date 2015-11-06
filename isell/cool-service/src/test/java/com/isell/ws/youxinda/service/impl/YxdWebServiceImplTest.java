package com.isell.ws.youxinda.service.impl;

import org.junit.Test;

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
	
	/**
	 * 
	 */
//	@Test
//	public void testCreateOrder() {
//		//fail("Not yet implemented");
//		
//		OrderInfo orderInfo = new OrderInfo();
//		orderInfo.setOrderModel("0");
//		orderInfo.setWarehouseCode("GZBYWLCK");
//		orderInfo.setToWarehouseCode("GZBYWLCK");//目的仓库
//		orderInfo.setOabCounty("CN");//收件人国家
//		orderInfo.setOabStateName("江苏");
//		orderInfo.setOabCity("常州");
//		orderInfo.setOabDistrict("新北区");
//		orderInfo.setSmCode("YTO");//运输方式
//		orderInfo.setReferenceNo("CO201511020001");//交易订单号
//		orderInfo.setOabName("王");
//		orderInfo.setOabCompany("艾售");
//		orderInfo.setOabPostcode("518000");
//		orderInfo.setOabStreetAddress1("新北区软件园");
//		orderInfo.setOabStreetAddress2("太湖东路9-1");
//		orderInfo.setOabPhone("13512551269");
//		orderInfo.setOabEmail("55461@qq.com");
//		orderInfo.setGrossWt("0.15");
//		orderInfo.setCurrencyCode("RMB");
//		orderInfo.setIdType("1");
//		orderInfo.setIdNumber("320402198408273738");
//		orderInfo.setShippingFeeEstimate("10");	
//		orderInfo.setRemark(null);
//		orderInfo.setOrderStatus("1");
//		//order.set
//		
//		
//		List<ProductDeatilType> orderProduct = new ArrayList<ProductDeatilType>();
//		ProductDeatilType p = new ProductDeatilType();
//		float []a = {0.01f};
//		float []b = {0.01f};
//		float []c = {0.01f};
//		float []d = {0.01f};
//		ProductDeatilType [] deatilType = new ProductDeatilType[7];
//		deatilType[0] = new ProductDeatilType("cool666",a ,b , 66);
//		deatilType[1] = new ProductDeatilType("cool6667",c ,d , 77);
////		p.setProductSku("cool666");
////		p.setOpQuantity(16);
////		p.setDealPrice(deatilType);
//		orderProduct.add(p);
////		List<Float> f2 = new ArrayList<Float>();
////		f2.add(Float.valueOf("120"));
////		ProductDeatilType p2 = new ProductDeatilType();
////		p2.setProductSku("cool6667");
////		p2.setOpQuantity(17);
////		p2.setDealPrice(f2);
////		orderProduct.add(p2);
//		
//		orderInfo.setOrderProduct(deatilType);
//		
//		YxdWebServiceImpl impl = new YxdWebServiceImpl();		
//		impl.createOrder(orderInfo);
//	}
	
	@Test
	public void getOrderByCode() {
		//fail("Not yet implemented");
		
		String orderCode = "SOC01570000002";
		
		
		
		YxdWebServiceImpl a = new YxdWebServiceImpl();		
		a.getOrderByCode(orderCode);
	}

}
