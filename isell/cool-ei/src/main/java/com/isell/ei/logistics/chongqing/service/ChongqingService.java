package com.isell.ei.logistics.chongqing.service;

import com.isell.ei.logistics.chongqing.bean.Goods;
import com.isell.ei.logistics.chongqing.bean.Order;
import com.isell.ei.logistics.chongqing.bean.PayInfo;


public interface ChongqingService {

	String sendOrder(Order order);

	String goodsRecord(Goods goods);

	String goodsRecord(PayInfo payInfo);

}
