package com.isell.ps.logistics.cq;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.isell.core.util.Coder;
import com.isell.core.util.JaxbUtil;
import com.isell.ei.logistics.chongqing.util.HttpRequest;
import com.isell.ps.logistics.chongqing.bean.DtcFlow;
import com.isell.ps.logistics.chongqing.bean.Goods;
import com.isell.ps.logistics.chongqing.bean.GoodsDtcFlow;
import com.isell.ps.logistics.chongqing.bean.GoodsMesAskInfo;
import com.isell.ps.logistics.chongqing.bean.GoodsMessageBody;
import com.isell.ps.logistics.chongqing.bean.GoodsMessageHead;
import com.isell.ps.logistics.chongqing.bean.MessageBody;
import com.isell.ps.logistics.chongqing.bean.MessageHead;
import com.isell.ps.logistics.chongqing.bean.OrderBack;
import com.isell.ps.logistics.chongqing.bean.OrderInfoFb;
import com.isell.ps.logistics.chongqing.bean.PayInfo;
import com.isell.ps.logistics.chongqing.bean.PayInfoDtcFlow;
import com.isell.ps.logistics.chongqing.bean.PayInfoMesAskInfo;
import com.isell.ps.logistics.chongqing.bean.PayInfoMessageBody;
import com.isell.ps.logistics.chongqing.bean.PayInfoMessageHead;

public class CqTestController {

	
	@Test
	public void orderBack(){
		OrderInfoFb cif = new OrderInfoFb();	
		cif.setMemo("银行预扣失败超过3天，系统自动审批退回。");
		cif.setOpDate(new Date());
		cif.setOrderNo("BMSD-I140704-0000059");
		cif.setOriginalOrderNo("KPGBMSD0000000085");
		cif.setStatusCode("40");
		DtcFlow dtc  = new DtcFlow();
		dtc.setOrderInfoFb(cif);
		MessageBody mb = new MessageBody();
		mb.setDtcFlow(dtc);
		MessageHead mh = new MessageHead();
		mh.setMessageType("ORDER_INFO");
		mh.setMessageId("A8A2C5E52924A114B300842B567E1315");
		mh.setMessageTime(new Date());
		mh.setSenderId("5005210033");
		mh.setReceiverId("CQITC");
		mh.setUserNo("5005210033");
		mh.setPassword("igetmall201455");
		OrderBack dtc2 = new OrderBack();
		dtc2.setMessageBody(mb);
		dtc2.setMessageHead(mh);
		String xml = JaxbUtil.convertToXml(dtc2);
	//	System.out.println(xml);
		String data = Coder.encryptBASE64(xml);
		try {
			data = URLEncoder.encode(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String result = HttpRequest.sendPost("http://service.i-coolshop.com/logistics/cqBackController/backOrder", "data="+data);
		System.out.println(result);//http://service.i-coolshop.com
	}
	
	@Test
	public void testPayInfo(){
		PayInfoMesAskInfo mai = new PayInfoMesAskInfo();
		mai.setCreatedNo("b02f8dce07a8453eabde375e85ea5bec");
		mai.setMessageType("PAYMENT_INFO");
		mai.setWorkNo("payMentInfo");
		mai.setOpDate(new Date());
		mai.setSuccess("1");
		mai.setMemo("支付单入库成功");
		List<PayInfoMesAskInfo> mais = new ArrayList<PayInfoMesAskInfo>();
		mais.add(mai);
		PayInfoDtcFlow pidf = new PayInfoDtcFlow();
		pidf.setMesAskInfo(mais);
		PayInfoMessageBody pimb = new PayInfoMessageBody();
		pimb.setDtcFlow(pidf);
		PayInfoMessageHead mh = new PayInfoMessageHead();
		mh.setMessageType("ORDER_INFO");
		mh.setMessageId("A8A2C5E52924A114B300842B567E1315");
		mh.setMessageTime(new Date());
		mh.setSenderId("5005210033");
		mh.setReceiverId("CQITC");
		mh.setUserNo("5005210033");
		mh.setPassword("igetmall201455");
		PayInfo dtc2 = new PayInfo();
		dtc2.setMessageBody(pimb);
		dtc2.setMessageHead(mh);
		String xml = JaxbUtil.convertToXml(dtc2);
		String data = Coder.encryptBASE64(xml);
		try {
			data = URLEncoder.encode(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String result = HttpRequest.sendPost("http://service.i-coolshop.com/logistics/cqBackController/payInfo", "data="+data);
		System.out.println(result);//http://service.i-coolshop.com
	}
	
	/**
=======
    
    @Test
    public void orderBack() {
        OrderInfoFb cif = new OrderInfoFb();
        cif.setMemo("银行预扣失败超过3天，系统自动审批退回。");
        cif.setOpDate(new Date());
        cif.setOrderNo("BMSD-I140704-0000059");
        cif.setOriginalOrderNo("KPGBMSD0000000085");
        cif.setStatusCode("40");
        DtcFlow dtc = new DtcFlow();
        dtc.setOrderInfoFb(cif);
        MessageBody mb = new MessageBody();
        mb.setDtcFlow(dtc);
        MessageHead mh = new MessageHead();
        mh.setMessageType("ORDER_INFO");
        mh.setMessageId("A8A2C5E52924A114B300842B567E1315");
        mh.setMessageTime(new Date());
        mh.setSenderId("5005210033");
        mh.setReceiverId("CQITC");
        mh.setUserNo("5005210033");
        mh.setPassword("igetmall201455");
        OrderBack dtc2 = new OrderBack();
        dtc2.setMessageBody(mb);
        dtc2.setMessageHead(mh);
        String xml = JaxbUtil.convertToXml(dtc2);
        // System.out.println(xml);
        String data = Coder.encryptBASE64(xml);
        try {
            data = URLEncoder.encode(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String result =
            HttpRequest.sendPost("http://localhost:8080/bis/logistics/cqBackController/backOrder", "data=" + data);
        System.out.println(result);// http://service.i-coolshop.com
    }
    
    @Test
    public void testPayInfo() {
        PayInfoMesAskInfo mai = new PayInfoMesAskInfo();
        mai.setCreatedNo("b02f8dce07a8453eabde375e85ea5bec");
        mai.setMessageType("PAYMENT_INFO");
        mai.setWorkNo("payMentInfo");
        mai.setOpDate(new Date());
        mai.setSuccess("1");
        mai.setMemo("支付单入库成功");
        List<PayInfoMesAskInfo> mais = new ArrayList<PayInfoMesAskInfo>();
        mais.add(mai);
        PayInfoDtcFlow pidf = new PayInfoDtcFlow();
        pidf.setMesAskInfo(mais);
        PayInfoMessageBody pimb = new PayInfoMessageBody();
        pimb.setDtcFlow(pidf);
        PayInfoMessageHead mh = new PayInfoMessageHead();
        mh.setMessageType("ORDER_INFO");
        mh.setMessageId("A8A2C5E52924A114B300842B567E1315");
        mh.setMessageTime(new Date());
        mh.setSenderId("5005210033");
        mh.setReceiverId("CQITC");
        mh.setUserNo("5005210033");
        mh.setPassword("igetmall201455");
        PayInfo dtc2 = new PayInfo();
        dtc2.setMessageBody(pimb);
        dtc2.setMessageHead(mh);
        String xml = JaxbUtil.convertToXml(dtc2);
        String data = Coder.encryptBASE64(xml);
        try {
            data = URLEncoder.encode(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String result =
            HttpRequest.sendPost("http://service.i-coolshop.com/logistics/cqBackController/payInfo", "data=" + data);
        System.out.println(result);// http://service.i-coolshop.com
    }
    
    /**
>>>>>>> .r6444
	 * 
	 */
    @Test
    public void testGoods() {
        GoodsMesAskInfo gmai = new GoodsMesAskInfo();
        gmai.setMemo("1");
        gmai.setMessageType("2");
        gmai.setSuccess("1");
        gmai.setOpDate(new Date());
        GoodsMessageBody gmb = new GoodsMessageBody();
        GoodsDtcFlow dtc = new GoodsDtcFlow();
        dtc.setGoodsMesAskInfo(gmai);
        gmb.setDtcFlow(dtc);
        GoodsMessageHead gmh = new GoodsMessageHead();
        gmh.setActionType("1");
        Goods g = new Goods();
        g.setMessageBody(gmb);
        g.setMessageHead(gmh);
        String xml = JaxbUtil.convertToXml(g);
        String data = Coder.encryptBASE64(xml);
        try {
            data = URLEncoder.encode(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String result =
            HttpRequest.sendPost("http://service.i-coolshop.com/logistics/cqBackController/goodsRecord", "data=" + data);
        System.out.println(result);// http://service.i-coolshop.com
    }
}