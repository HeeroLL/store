package com.zebone.register.validation.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.zebone.register.validation.dao.CheckDataMapper;
import com.zebone.register.validation.domain.CheckLog;
import com.zebone.register.validation.repository.CheckDataRepository;

/**
 * 数据校验模块Repository 实现类
 * @author 陈阵 
 * @date 2013-8-3 上午10:07:51
 */
@Repository("checkDataRepository")
public class CheckDataRepositoryImpl implements CheckDataRepository {
	
   @Resource
   private CheckDataMapper checkDataMapper;

	@Override
	public void saveBatchCheckLog(List<CheckLog> checkLogList) {
		// TODO Auto-generated method stub
		for(CheckLog checkLog : checkLogList){
			checkDataMapper.saveCheckLog(checkLog);
		}	
	}

}
