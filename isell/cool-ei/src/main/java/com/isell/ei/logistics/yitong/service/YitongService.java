package com.isell.ei.logistics.yitong.service;

import com.isell.ei.logistics.yitong.bean.YitongRequestRows;
import com.isell.ei.logistics.yitong.bean.YitongResponse;

/**
 * 易通服务接口
 * 
 * @author lilin
 * @version [版本号, 2015年12月4日]
 */
public interface YitongService {
    /**
     * 通知URL
     */
    String URL = "http://114.215.196.207:8080/hnsto/servlet/EPortBillinfoInterfaceRSA"; // http://sto.m2b.help:48002/hnsto/servlet/EPortBillinfoInterfaceRSA
    
    /**
     * 客户编号
     */
    String STOCUSTOMERID = "91294"; // 90000
    
    /**
     * 发送订单消息给郑州汇通
     *
     * @param yitongRequestRows 订单信息
     * @return 易通响应信息
     */
    YitongResponse sendOrder(YitongRequestRows yitongRequestRows);
}
