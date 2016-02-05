package com.isell.ei.logistics.huitong.service;

import com.isell.ei.logistics.huitong.bean.CreateResponse;
import com.isell.ei.logistics.huitong.bean.Order;
import com.isell.ei.logistics.huitong.bean.QueryResponse;
import com.isell.ei.logistics.huitong.bean.StockResponse;

public interface HuitongService {

	CreateResponse createMail(Order order, String method);

	QueryResponse queryBondOrMail(String eNo, String method);

	StockResponse queryBondOrMail(String eNo);

}
