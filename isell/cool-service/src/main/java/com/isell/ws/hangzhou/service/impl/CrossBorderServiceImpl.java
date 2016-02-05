package com.isell.ws.hangzhou.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import com.isell.core.pojo.Filing;
import com.isell.core.pojo.OrderFiling;
import com.isell.core.pojo.OrderFilingDetail;
import com.isell.core.util.JaxbUtil;
import com.isell.ws.hangzhou.bean.CBody;
import com.isell.ws.hangzhou.bean.CHead;
import com.isell.ws.hangzhou.bean.CJkfGoodsPurchaser;
import com.isell.ws.hangzhou.bean.CJkfOrderDetail;
import com.isell.ws.hangzhou.bean.CJkfOrderImportHead;
import com.isell.ws.hangzhou.bean.CJkfSign;
import com.isell.ws.hangzhou.bean.CMO;
import com.isell.ws.hangzhou.bean.COrderInfo;
import com.isell.ws.hangzhou.bean.CProductRecord;
import com.isell.ws.hangzhou.bean.CProductRecordDto;
import com.isell.ws.hangzhou.bean.RMO;
import com.isell.ws.hangzhou.bean.StaticObject;
import com.isell.ws.hangzhou.service.CrossBorderService;
import com.isell.ws.hangzhou.ws.ReceivedDeclareServiceImplService;

/**
 * 与海关交互服务类
 * @author 一代魔笛
 *
 */
@WebService(endpointInterface = "com.isell.ws.hangzhou.service.CrossBorderService")  
public class CrossBorderServiceImpl implements CrossBorderService {

	/**
	 * 商品备案
	 * @param filing
	 * @return
	 */
	public RMO productRecord(Filing filing) {
		CJkfSign jkfSign = new CJkfSign();
		jkfSign.setCompanyCode(StaticObject.SHKM_COMPANY_CODE);
		jkfSign.setBusinessNo(filing.getBusinessNo());
		jkfSign.setBusinessType("PRODUCT_RECORD");
		jkfSign.setDeclareType("1");
		CProductRecordDto productRecordDto = new CProductRecordDto();
		productRecordDto.setCompanyCode(filing.geteCommerceCode());
		productRecordDto.setCompanyName(filing.geteCommerceName());
		productRecordDto.setPostTaxNo(filing.getPostTaxNo());
		productRecordDto.setGoodsType(filing.getGoodsType());
		productRecordDto.setGoodsName(filing.getGoodsName());
		productRecordDto.setBarCode(filing.getBarCode());
		productRecordDto.setBrand(filing.getBrand());
		productRecordDto.setGoodsModel(filing.getGoodsModel());
		productRecordDto.setMainElement(filing.getMainElement());
		productRecordDto.setPurpose(filing.getPurpose());
		productRecordDto.setStandards(filing.getStandards());
		productRecordDto.setProductionEnterprise(filing.getProductionEnterprise());
		productRecordDto.setProductionCountry(filing.getProductionCountry());
		productRecordDto.setLicenceKey(filing.getLicenceKey());				
		productRecordDto.setCategoryCode(filing.getCategoryCode());
		productRecordDto.setMaterialAddress(filing.getMaterialAddress());		
		productRecordDto.setDeclareTimeStr(filing.getDeclareTimeStr());
		
		CProductRecord productRecord = new CProductRecord();
		productRecord.setJkfSign(jkfSign);
		productRecord.setProductRecordDto(productRecordDto);
		
		List<CProductRecord> productRecords = new ArrayList<CProductRecord>();
		productRecords.add(productRecord);
		
		CBody body = new CBody();
		body.setProductRecords(productRecords);
		
		CHead head = new CHead();
		head.setBusinessType("PRODUCT_RECORD");
		
		CMO cmo = new CMO();
		cmo.setHead(head);
		cmo.setBody(body);
		
		String param = JaxbUtil.convertToXml(cmo, "utf-8");
		System.out.println(param);
		ReceivedDeclareServiceImplService service = new ReceivedDeclareServiceImplService();
		String result = service.getReceivedDeclareServiceImplPort().checkReceived(param, "PRODUCT_RECORD", "1");
		System.out.println(result);
		RMO rmo = JaxbUtil.converyToJavaBean(result, RMO.class);		
		return rmo;
	}
	
	/**
	 * 订单申报
	 * @return
	 */
	public RMO orderRecord(OrderFiling orderFiling){
		CHead head = new CHead();
		head.setBusinessType("IMPORTORDER");
		
		CJkfSign jkfSign = new CJkfSign();
		jkfSign.setCompanyCode(StaticObject.SHKM_COMPANY_CODE);
		jkfSign.setBusinessNo(orderFiling.getBusinessNo());
		jkfSign.setBusinessType("IMPORTORDER");
		jkfSign.setDeclareType("1");
		
		CJkfOrderImportHead orderImportHead = new CJkfOrderImportHead();
		orderImportHead.setCompanyCode(StaticObject.SHKM_COMPANY_CODE);
		orderImportHead.setCompanyName(StaticObject.SHKM_COMPANY_NAME);
		orderImportHead.setIeFlag(orderFiling.getIeFlag());
		orderImportHead.setPayType(orderFiling.getPayType());
		orderImportHead.setPayCompanyCode(StaticObject.SHKM_PAY_COMPANY_CODE);
		orderImportHead.setPayNumber(orderFiling.getPayNumber());
		orderImportHead.setOrderTotalAmount(orderFiling.getOrderTotalAmount().toString());
		orderImportHead.setOrderGoodsAmount(orderFiling.getOrderGoodsAmount().toString());
		orderImportHead.setOrderNo(orderFiling.getOrderNo());
		orderImportHead.setOrderTaxAmount(orderFiling.getOrderTaxAmount().toString());
		if(orderFiling.getFeeAmount() != null){
			orderImportHead.setFeeAmount(orderFiling.getFeeAmount().toString());
		}		
		orderImportHead.seteCommerceCode(StaticObject.SHKM_COMPANY_CODE);
		orderImportHead.seteCommerceName(StaticObject.SHKM_COMPANY_NAME);
		orderImportHead.setTradeTime(orderFiling.getTradeTime());
		orderImportHead.setCurrCode(orderFiling.getCurrCode());
		if(orderFiling.getTotalAmount() != null){
			orderImportHead.setTotalAmount(orderFiling.getTotalAmount().toString());
		}		
		orderImportHead.setConsigneeTel(orderFiling.getConsigneeTel());
		orderImportHead.setConsignee(orderFiling.getConsignee());
		orderImportHead.setConsigneeAddress(orderFiling.getConsigneeAddress());
		orderImportHead.setTotalCount(Integer.toString(orderFiling.getTotalCount()));
		orderImportHead.setPostMode(orderFiling.getPostMode());
		orderImportHead.setSenderCountry(orderFiling.getSenderCountry());
		orderImportHead.setSenderName(orderFiling.getSenderName());
		orderImportHead.setPurchaserId(orderFiling.getPurchaserId());
		orderImportHead.setLogisCompanyCode(orderFiling.getLogisCompanyCode());
		orderImportHead.setLogisCompanyName(orderFiling.getLogisCompanyName());
		orderImportHead.setRate(orderFiling.getRate());
		orderImportHead.setZipCode(orderFiling.getZipCode());
		orderImportHead.setNote(orderFiling.getNote());
		orderImportHead.setWayBills(orderFiling.getWayBills());
		
		orderImportHead.setUserProcotol(orderFiling.getUserProcotol());
		
		List<CJkfOrderDetail> orderDetails = new ArrayList<CJkfOrderDetail>();
		int i=1;
		for(OrderFilingDetail detail : orderFiling.getDetails()){
			CJkfOrderDetail orderDetail = new CJkfOrderDetail();
			orderDetail.setGoodsOrder(i++);
			orderDetail.setGoodsName(detail.getGoodsName());
			orderDetail.setCodeTs(detail.getCodeTs());
			orderDetail.setOriginCountry(detail.getOriginCountry());
			orderDetail.setUnitPrice(detail.getUnitPrice());
			orderDetail.setGoodsCount(detail.getGoodsCount());
			orderDetail.setGoodsUnit(detail.getGoodsUnit());
			orderDetail.setGrossWeight(detail.getGrossWeight());
			orderDetail.setGoodsModel(detail.getGoodsModel());
			orderDetails.add(orderDetail);
		}						
		
		CJkfGoodsPurchaser goodsPurchaser = new CJkfGoodsPurchaser();
		goodsPurchaser.setId(orderFiling.getPurchaserId());
		goodsPurchaser.setName(orderFiling.getPurchaserName());
		goodsPurchaser.setTelNumber(orderFiling.getPurchaserTelNumber());
		goodsPurchaser.setPaperType(orderFiling.getPurchaserPaperType());
		goodsPurchaser.setPaperNumber(orderFiling.getPurchaserPaperNumber());
		goodsPurchaser.setAddress(orderFiling.getPurchaserAddress());
		goodsPurchaser.setEmail(orderFiling.getPurchaserEmail());
				
		COrderInfo orderInfo = new COrderInfo();
		orderInfo.setJkfSign(jkfSign);
		orderInfo.setJkfOrderImportHead(orderImportHead);
		orderInfo.setJkfOrderDetails(orderDetails);
		orderInfo.setJkfGoodsPurchaser(goodsPurchaser);

		List<COrderInfo> orderInfos = new ArrayList<COrderInfo>();
		orderInfos.add(orderInfo);
		
		CBody body = new CBody();
		body.setOrderInfos(orderInfos);
		
		CMO cmo = new CMO();
		cmo.setHead(head);
		cmo.setBody(body);
		
		String param = JaxbUtil.convertToXml(cmo, "utf-8");
		System.out.println(param);
		ReceivedDeclareServiceImplService service = new ReceivedDeclareServiceImplService();
		String result = service.getReceivedDeclareServiceImplPort().checkReceived(param, "IMPORTORDER", "1");
		System.out.println(result);
		RMO rmo = JaxbUtil.converyToJavaBean(result, RMO.class);		
		return rmo;
	}

}
