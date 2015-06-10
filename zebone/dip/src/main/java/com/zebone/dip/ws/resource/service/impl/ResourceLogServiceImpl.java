package com.zebone.dip.ws.resource.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zebone.dip.ws.resource.dao.PResourceLogMapper;
import com.zebone.dip.ws.resource.model.PResourceLog;
import com.zebone.dip.ws.resource.service.ResourceLogService;

@Service("resourceLogService")
public class ResourceLogServiceImpl implements ResourceLogService {
    
	@Resource
	private PResourceLogMapper pResourceLogMapper;

	@Override
	public int saveResourceLog(PResourceLog log) {
		// TODO Auto-generated method stub
		return pResourceLogMapper.insertSelective(log);
	}
	
	
}
