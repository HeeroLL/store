package com.isell.ei.pay.yijifu.service;

import com.isell.ei.pay.yijifu.bean.CoolYjfRemit;
import com.isell.ei.pay.yijifu.bean.CoolYjfRemitOrder;

/**
 * 易极付跨境汇款申请接口
 * 
 * @author lilin
 * @version [版本号, 2016年2月24日]
 */
public interface CoolYjfRemitService {
    /**
     * 根据批次号查询
     *
     * @param remittranceBatchno 批次号
     * @return 汇款申请信息
     */
    CoolYjfRemit getCoolYjfRemitByBatchno(String remittranceBatchno);
    
    /**
     * 更新汇款信息
     *
     * @param coolYjfRemit 汇款申请信息
     */
    void updateCoolYjfRemit(CoolYjfRemit coolYjfRemit);
    
    /**
     * 保存汇款信息
     *
     * @param coolYjfRemit 汇款申请信息
     */
    void saveCoolYjfRemit(CoolYjfRemit coolYjfRemit);
    
    /**
     * 保存汇款订单信息
     *
     * @param coolYjfRemitOrder 汇款订单信息
     */
    void saveCoolYjfRemitOrder(CoolYjfRemitOrder coolYjfRemitOrder);
    
    /**
     * 更新批次总金额
     *
     * @param remittranceBatchno 批次号
     */
    void updatePayAmountByBatchno(String remittranceBatchno);
}
