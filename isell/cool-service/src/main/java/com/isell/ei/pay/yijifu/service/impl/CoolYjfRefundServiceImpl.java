package com.isell.ei.pay.yijifu.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.isell.ei.pay.yijifu.bean.CoolYjfRefundMessage;
import com.isell.ei.pay.yijifu.dao.CoolYjfRefundMessageMapper;
import com.isell.ei.pay.yijifu.service.CoolYjfRefundService;

/**
 * 易极付跨境退款接口实现类
 * 
 * @author wangpeng
 * @version [版本号, 2016年2月24日]
 */
@Service("coolYjfRefundService")
public class CoolYjfRefundServiceImpl implements CoolYjfRefundService {
	
	 /**
     * 易极付跨境退款款Mapper
     */
    @Resource
    private CoolYjfRefundMessageMapper coolYjfRefundMessageMapper;

	/**
     * 保存退款信息
     *
     * @param coolYjfRefundMessage 退款信息
     */
	@Override
	public void saveCoolYjfRefundMessage(CoolYjfRefundMessage coolYjfRefundMessage) {
		coolYjfRefundMessageMapper.saveCoolYjfRefundMessage(coolYjfRefundMessage);
	}

}
