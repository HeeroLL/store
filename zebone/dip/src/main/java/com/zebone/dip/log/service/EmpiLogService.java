package com.zebone.dip.log.service;

import org.springframework.stereotype.Service;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.log.vo.EmpiResult;
import com.zebone.dip.log.vo.EmpiSearchObj;

@Service
public interface EmpiLogService {
	
	/**
	 * Empi注册更新日志查询
	 * @param empiSearchObj
	 * @return
	 */
	public Pagination<EmpiResult> searchAddUpdateLog(EmpiSearchObj empiSearchObj, Pagination<EmpiResult> page);
	
	/**
	 * Empi查询日志查询
	 * @param empiSearchObj
	 * @return
	 */
	public Pagination<EmpiResult> searchQueryLog(EmpiSearchObj empiSearchObj, Pagination<EmpiResult> page);
}
