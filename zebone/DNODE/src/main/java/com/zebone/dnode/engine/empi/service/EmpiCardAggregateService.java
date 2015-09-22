package com.zebone.dnode.engine.empi.service;

import org.springframework.stereotype.Service;




/**
 * 
 * @author Administrator
 *
 */
@Service
public interface EmpiCardAggregateService {
     
	/** 聚合生成就诊卡注册与注销报表信息**/
	 void doMedicalCardAggregate();
}
