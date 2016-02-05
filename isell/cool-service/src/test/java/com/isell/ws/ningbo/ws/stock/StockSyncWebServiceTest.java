package com.isell.ws.ningbo.ws.stock;

import org.junit.Test;

import com.isell.core.util.JaxbUtil;
import com.isell.ws.ningbo.bean.StockSyncMsg;
import com.isell.ws.ningbo.bean.StockSyncResult;
import com.isell.ws.ningbo.service.YoubeiService;

public class StockSyncWebServiceTest {

	@Test
	public void test() {
		StockSyncWebServiceSoap serviceSoap = new StockSyncWebService().getStockSyncWebServiceSoap12();
		StockSyncMsg msg = new StockSyncMsg();
		msg.setShopId(YoubeiService.SHOPID.toString());
		msg.setTime("2015-10-01 00:00:00");
		String xml = JaxbUtil.convertToXml(msg);
        xml = xml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
        System.out.println(xml);
		String result = serviceSoap.getStock(YoubeiService.ERPKEY, YoubeiService.ERPSECRET, xml);
		System.out.println(result);
		
		StockSyncResult syncResult = JaxbUtil.converyToJavaBean(result, StockSyncResult.class);
		System.out.println(syncResult.getResult());
	}

}
