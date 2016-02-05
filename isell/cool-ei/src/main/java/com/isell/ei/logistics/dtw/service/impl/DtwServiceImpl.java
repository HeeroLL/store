package com.isell.ei.logistics.dtw.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;
import com.isell.ei.logistics.dtw.bean.GainsItem;
import com.isell.ei.logistics.dtw.bean.GainsRequest;
import com.isell.ei.logistics.dtw.bean.GainsResponse;
import com.isell.ei.logistics.dtw.bean.MultipleItems;
import com.isell.ei.logistics.dtw.bean.MultipleRequest;
import com.isell.ei.logistics.dtw.bean.MultipleResponse;
import com.isell.ei.logistics.dtw.bean.SendItems;
import com.isell.ei.logistics.dtw.bean.SendPersonalResponse;
import com.isell.ei.logistics.dtw.bean.SendPresonalInfo;
import com.isell.ei.logistics.dtw.bean.SendPresonalItems;
import com.isell.ei.logistics.dtw.bean.SendRequest;
import com.isell.ei.logistics.dtw.bean.SendResponse;
import com.isell.ei.logistics.dtw.service.DtwService;

@Service("dtwService")
public class DtwServiceImpl implements DtwService{

	@Override
	public GainsResponse getGainsOrder(GainsRequest c) {
		Map<String,String> map = new HashMap<String,String>();
		List<GainsItem> g = c.getItems();
		map.put("eCommerceCode", c.geteCommerceCode());
		map.put("eCommerceName", c.geteCommerceName());
		map.put("Hawb", c.getHawb());
		map.put("PassKey",c.getKey());
		map.put("Mawb", c.getMawb());
		map.put("Msgid", c.getMsgid());
		map.put("Supplier", c.getSupplier());
		for(int i=0,len=g.size();i<len;i++){
			map.put("Items["+i+"][MsgItem]", g.get(i).getMsgItem());
			map.put("Items["+i+"][Partno]", g.get(i).getPartno());
			map.put("Items["+i+"][PartName]", g.get(i).getPartName());
			map.put("Items["+i+"][Spec]", g.get(i).getSpec());
			map.put("Items["+i+"][InvoiceNo]", g.get(i).getInvoiceNo());
			map.put("Items["+i+"][HsCode]", g.get(i).getHsCode());
			map.put("Items["+i+"][Batch]", g.get(i).getBatch());
			map.put("Items["+i+"][Qty]", g.get(i).getQty());
			map.put("Items["+i+"][Unit]", g.get(i).getUnit());
			map.put("Items["+i+"][Dref1]", g.get(i).getDref1());
			map.put("Items["+i+"][Dref2]", g.get(i).getDref2());
			map.put("Items["+i+"][Dref3]", g.get(i).getDref3());
			map.put("Items["+i+"][Dref4]", g.get(i).getDref4());
			map.put("Items["+i+"][Currency]", g.get(i).getCurrency());
			map.put("Items["+i+"][OriginCountry]", g.get(i).getOriginCountry());
			map.put("Items["+i+"][Amount]", g.get(i).getAmount());
		}
		
		String result = HttpUtils.httpPost(
				"http://logistics.dtw.com.cn:8080/QBT/api/QBRorder",map);
		GainsResponse gr = JsonUtil.readValue(result, GainsResponse.class);
		return gr;
		//return JsonUtil.readValue(result, GainsResponse.class);
	}

	@Override
	public SendResponse sendGoods(SendRequest s) {
		Map<String,String> map = new HashMap<String,String>();
		List<SendItems> g = s.getItems();
		map.put("eCommerceCode", s.geteCommerceCode());
		map.put("eCommerceName", s.geteCommerceName());
		map.put("Msgid", s.getMsgid());
		map.put("payType", s.getPayType());
		map.put("payCompanyCode", s.getPayCompanyCode());
		map.put("payNumber", s.getPayNumber());
		map.put("orderTotalAmount", s.getOrderTotalAmount());
		map.put("orderGoodsAmount", s.getOrderGoodsAmount());
		map.put("orderNo", s.getOrderNo());
		map.put("orderTaxAmount", s.getOrderTaxAmount());
		map.put("Consignee", s.getConsignee());
		map.put("ConsigneeTel", s.getConsigneeTel());
		map.put("ConsigneePro", "江苏");
		map.put("ConsigneeCity", "常州");
		map.put("ConsigneeDistrict", "钟楼区");
		map.put("ConsigneeAdd", s.getConsigneeAdd());
		map.put("ConsigneeCountry","中国");
		map.put("totalCount", s.getTotalCount());
		map.put("totalAmount", s.getTotalAmount());
		map.put("purchaserId", s.getPurchaserId());
		for(int i=0,len=g.size();i<len;i++){
			map.put("Items["+i+"][Supplier]", g.get(i).getSupplier());
			map.put("Items["+i+"][Msgitem]", g.get(i).getMsgitem());
			map.put("Items["+i+"][Partno]", g.get(i).getPartno());
			map.put("Items["+i+"][PartName]", g.get(i).getPartName());
			map.put("Items["+i+"][Spec]", g.get(i).getSpec());
			map.put("Items["+i+"][Mawb]", g.get(i).getMawb());
			map.put("Items["+i+"][Batch]", g.get(i).getBatch());
			map.put("Items["+i+"][Qty]", g.get(i).getQty());
			map.put("Items["+i+"][Unit]", g.get(i).getUnit());
			map.put("Items["+i+"][Dref1]", g.get(i).getDref1());
			map.put("Items["+i+"][Dref2]", g.get(i).getDref2());
			map.put("Items["+i+"][Dref3]", g.get(i).getDref3());
			map.put("Items["+i+"][Dref4]", g.get(i).getDref4());
			map.put("Items["+i+"][Currency]", g.get(i).getCurrency());
			map.put("Items["+i+"][Amount]", g.get(i).getAmount());
		}	
		String result = HttpUtils.httpPost("http://logistics.dtw.com.cn:8080/QBT/api/QBSorder", map);  
	    //  return JsonUtil.readValue(result, SendResponse.class);
		System.out.println(result);
	      return null;
	}

	@Override
	public MultipleResponse multipleOrders(MultipleRequest m)  {
		Map<String,String> map = new HashMap<String,String>();
		List<MultipleItems> mi = m.getItems();
		for(int i=0,len=mi.size();i<len;i++){
			map.put("Items["+i+"][Amount]", mi.get(i).getAmount());
			map.put("Items["+i+"][Batch]",  mi.get(i).getBatch());
			map.put("Items["+i+"][Currency]", mi.get(i).getCurrency());
			map.put("Items["+i+"][Dref1]",  mi.get(i).getDref1());
			map.put("Items["+i+"][Dref2]",  mi.get(i).getDref2());
			map.put("Items["+i+"][Dref3]",  mi.get(i).getDref3());
			map.put("Items["+i+"][Dref4]",  mi.get(i).getDref4());
			map.put("Items["+i+"][Msgitem]", mi.get(i).getMsgitem());
			map.put("Items["+i+"][PartName]",  mi.get(i).getPartName());
			map.put("Items["+i+"][Partno]",  mi.get(i).getPartno());
			map.put("Items["+i+"][Qty]",  mi.get(i).getQty());
			map.put("Items["+i+"][Spec]", mi.get(i).getSpec());
			map.put("Items["+i+"][Supplier]",  mi.get(i).getSupplier());
			map.put("Items["+i+"][Unit]", mi.get(i).getUnit());
		}
		
		map.put("Msgid", m.getMsgid());
		map.put("PassKey", m.getPassKey());
		map.put("Consignee", m.getConsignee());
		map.put("ConsigneeTel", m.getConsigneeTel());
		map.put("ConsigneePro", m.getConsigneePro());
		map.put("ConsigneeCity", m.getConsigneeCity());
		map.put("ConsigneeDistrict", m.getConsigneeDistrict());
		map.put("ConsigneeAdd", m.getConsigneeAdd());
		map.put("ConsigneeCountry",m.getConsigneeCountry());
		map.put("eCommerceCode", m.geteCommerceCode());
		map.put("eCommerceName", m.geteCommerceName());
		map.put("orderNo", m.getOrderNo());
		map.put("orderTaxAmount", m.getOrderTaxAmount());
		map.put("orderGoodsAmount", m.getOrderGoodsAmount());
		map.put("orderTotalAmount", m.getOrderTotalAmount());
		map.put("payCompanyCode", m.getPayCompanyCode());
		map.put("payNumber", m.getPayNumber());
		map.put("payType", m.getPayType());
		map.put("purchaserId", m.getPurchaserId());
		map.put("totalAmount", m.getTotalAmount());
		map.put("totalCount", m.getTotalCount());
		map.put("Shipper", m.getShipper());
		map.put("ShipperAddress", m.getShipperAddress());
		map.put("ShipperCountry", m.getShipperCountry());
		map.put("ShipperPro", m.getShipperPro());
		map.put("ShipperDistrict", m.getShipperDistrict());
		map.put("ShipperMobile", m.getShipperMobile());
		map.put("ShipperZip", m.getShipperZip());
		map.put("ShipperMobile", m.getShipperMobile());
		map.put("logisCompanyCode", m.getLogisCompanyCode());
		map.put("logisCompanyName", m.getLogisCompanyName());
		String result = HttpUtils.httpPost(
				"http://logistics.dtw.com.cn:8080/QBT/api/QBIntegratedOrder",map);
		System.out.println(result);
		return null;
	}

	@Override
	public SendPersonalResponse sendPersonalInfo(SendPresonalInfo s) {
		Map<String,String> map = new HashMap<String,String>();
		List<SendPresonalItems> mi = s.getItems();
		map.put("PassKey",s.getPassKey());
		map.put("Msgid", s.getMsgid());
		map.put("preEntryNumber", s.getPaperNumber());
		map.put("accountBookNo", s.getAccountBookNo());
		map.put("inOutDateStr", s.getInOutDateStr());
		map.put("importType",s.getImportType() );
		map.put("trafName",s.getTrafName() );
		map.put("voyageNo", s.getVoyageNo());
		map.put("eCommerceCode",s.geteCommerceCode() );
		map.put("eCommerceName",s.geteCommerceName() );
		map.put("orderNo", s.getOrderNo());
		map.put("wayBill", s.getWayBill());
		map.put("packNo", s.getPackNo());
		map.put("grossWeight", s.getGrossWeight());
		map.put("netWeight", s.getNetWeight());
		map.put("remark", s.getRemark());
		map.put("declarantNo", s.getDeclarantNo());
		map.put("senderName",s.getSenderName());
		map.put("consignee", s.getConsignee());
		map.put("senderCity", s.getSenderCity());
		map.put("paperType", s.getPaperType());
		map.put("paperNumber", s.getPaperNumber());
		map.put("currCode", s.getCurrCode());
		map.put("mainGName", s.getMainGName());
		map.put("internalAreaCompanyNo", s.getInternalAreaCompanyNo());
		map.put("internalAreaCompanyName", s.getInternalAreaCompanyName());
		map.put("applicationFormNo", s.getApplicationFormNo());
		map.put("senderCountry", s.getSenderCountry());
		map.put("tradeCountry", s.getTradeCountry());
		map.put("worth", s.getWorth());
		for(int i=0,len=mi.size();i<len;i++){
			map.put("Items["+i+"][goodsOrder]", mi.get(i).getGoodsOrder());
			map.put("Items["+i+"][codeTs]", mi.get(i).getCodeTs());
			map.put("Items["+i+"][goodsItemNo]", mi.get(i).getGoodsItemNo());
			map.put("Items["+i+"][goodsName]", mi.get(i).getGoodsName());
			map.put("Items["+i+"][goodsModel]", mi.get(i).getGoodsModel());
			
			map.put("Items["+i+"][originCountry]", mi.get(i).getOriginCountry());
			map.put("Items["+i+"][tradeCurr]", mi.get(i).getTradeCurr());
			map.put("Items["+i+"][tradeTotal]", mi.get(i).getTradeTotal());
			map.put("Items["+i+"][declPrice]", mi.get(i).getDeclPrice());
			map.put("Items["+i+"][declTotalPrice]", mi.get(i).getDeclTotalPrice());
			
			map.put("Items["+i+"][useTo]", mi.get(i).getUseTo());
			map.put("Items["+i+"][declareCount]", mi.get(i).getDeclareCount());
			map.put("Items["+i+"][goodsUnit]", mi.get(i).getGoodsUnit());
			map.put("Items["+i+"][goodsGrossWeight]", mi.get(i).getGoodsGrossWeight());
			map.put("Items["+i+"][firstUnit]", mi.get(i).getFirstUnit());
			map.put("Items["+i+"][firstCount]", mi.get(i).getFirstCount());
			map.put("Items["+i+"][secondUnit]", mi.get(i).getSecondUnit());
		}
		String result = HttpUtils.httpPost(
				"http://logistics.dtw.com.cn:8080/QBT/api/QBPresonal",map);
		System.out.println(result);
		return null;
	}


}
