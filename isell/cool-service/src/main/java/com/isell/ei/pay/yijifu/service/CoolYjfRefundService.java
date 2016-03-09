package com.isell.ei.pay.yijifu.service;

import com.isell.ei.pay.yijifu.bean.CoolYjfRefundMessage;

/**
 * 易极付跨境退款款接口
 * 
 * @author wangpeng
 * @version [版本号, 2016年2月24日]
 */
public interface CoolYjfRefundService {    
    
    /**
     * 保存退款信息
     *
     * @param coolYjfRefundMessage 退款信息
     */
    void saveCoolYjfRefundMessage(CoolYjfRefundMessage coolYjfRefundMessage);
}
