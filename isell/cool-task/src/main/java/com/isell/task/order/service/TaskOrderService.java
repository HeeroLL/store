package com.isell.task.order.service;

/**
 * 订单服务接口
 * 
 * @author lilin
 * @version [版本号, 2015年8月31日]
 */
public interface TaskOrderService {
    /**
     * 批量取消订单
     */
    void cancelOrder();
    
    /**
     * 批量确认收货
     */
    void signOrder();
    
    /**
     * 返还佣金
     */
	void backCommission();
	
	/**
	 * 宁波优贝批量获取运单号
	 */
	void getKJB2CLogisticsInfo();
	
	/**
	 * 拼多多订单推送
	 */
	void pushPinduoduoOrder();
	
	/**
	 * 拼多多订单下载
	 */
	void downloadPinduoduoOrder();
	
	/**
	 * 卡乐猫订单推送
	 */
	void upKalemaoWayBillNo();
}
