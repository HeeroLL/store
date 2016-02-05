package com.isell.ei.logistics.dtw.service;

import com.isell.ei.logistics.dtw.bean.GainsRequest;
import com.isell.ei.logistics.dtw.bean.GainsResponse;
import com.isell.ei.logistics.dtw.bean.MultipleRequest;
import com.isell.ei.logistics.dtw.bean.MultipleResponse;
import com.isell.ei.logistics.dtw.bean.SendPersonalResponse;
import com.isell.ei.logistics.dtw.bean.SendPresonalInfo;
import com.isell.ei.logistics.dtw.bean.SendRequest;
import com.isell.ei.logistics.dtw.bean.SendResponse;

public interface DtwService {

	GainsResponse getGainsOrder(GainsRequest cdr);

	SendResponse sendGoods(SendRequest sr);

	MultipleResponse multipleOrders(MultipleRequest multipleRequest);

	SendPersonalResponse sendPersonalInfo(SendPresonalInfo sendPresonalInfo);

}
