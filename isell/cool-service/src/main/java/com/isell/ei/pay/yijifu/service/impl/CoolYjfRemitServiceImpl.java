package com.isell.ei.pay.yijifu.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.isell.ei.pay.yijifu.bean.CoolYjfRemit;
import com.isell.ei.pay.yijifu.bean.CoolYjfRemitOrder;
import com.isell.ei.pay.yijifu.dao.CoolYjfRemitMapper;
import com.isell.ei.pay.yijifu.dao.CoolYjfRemitOrderMapper;
import com.isell.ei.pay.yijifu.service.CoolYjfRemitService;

/**
 * 易极付跨境汇款申请接口实现类
 * 
 * @author lilin
 * @version [版本号, 2016年2月24日]
 */
@Service("coolYjfRemitService")
public class CoolYjfRemitServiceImpl implements CoolYjfRemitService {
    
    /**
     * 易极付跨境汇款申请Mapper
     */
    @Resource
    private CoolYjfRemitMapper yjfRemitMapper;
    
    /**
     * 易极付跨境汇款订单Mapper
     */
    @Resource
    private CoolYjfRemitOrderMapper yjfRemitOrderMapper;
    
    @Override
    public CoolYjfRemit getCoolYjfRemitByBatchno(String remittranceBatchno) {
        return yjfRemitMapper.getCoolYjfRemitByBatchno(remittranceBatchno);
    }
    
    @Override
    public void updateCoolYjfRemit(CoolYjfRemit coolYjfRemit) {
        yjfRemitMapper.updateCoolYjfRemit(coolYjfRemit);
    }
    
    @Override
    public void saveCoolYjfRemit(CoolYjfRemit coolYjfRemit) {
        yjfRemitMapper.saveCoolYjfRemit(coolYjfRemit);
    }
    
    @Override
    public void saveCoolYjfRemitOrder(CoolYjfRemitOrder coolYjfRemitOrder) {
        yjfRemitOrderMapper.saveCoolYjfRemitOrder(coolYjfRemitOrder);
    }
    
    @Override
    public void updatePayAmountByBatchno(String remittranceBatchno) {
        yjfRemitMapper.updatePayAmountByBatchno(remittranceBatchno);
    }
    
}
