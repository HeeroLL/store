package com.zebone.empi.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zebone.empi.dao.EmpiLogMapper;
import com.zebone.empi.dao.ResidentInfoLogMapper;
import com.zebone.empi.service.BizLogService;
import com.zebone.empi.service.ResidentInfoOperationState;
import com.zebone.empi.util.ObjectReflect;
import com.zebone.empi.util.UUIDUtil;
import com.zebone.empi.vo.EmpiLog;
import com.zebone.empi.vo.ResidentInfo;
import com.zebone.empi.vo.ResidentInfoLog;

/**
 * 日志接口实现类
 * @author YinCm
 * @version 2013-7-31 下午10:15:20
 */
@Service
public class BizLogServiceImpl implements BizLogService {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	EmpiLogMapper empiLogMapper;
	
	@Resource
	ResidentInfoLogMapper residentInfoLogMapper;
	
	public EmpiLogMapper getEmpiLogMapper() {
		return empiLogMapper;
	}

	public void setEmpiLogMapper(EmpiLogMapper empiLogMapper) {
		this.empiLogMapper = empiLogMapper;
	}

	@Override
	public void log(EmpiLog log) {
		log.setId(UUIDUtil.getUuid());
		empiLogMapper.insertEmpiLog(log);
	}

	@Override
	public void log(ResidentInfo residentInfo, ResidentInfoOperationState operState) {
		ResidentInfoLog residentInfoLog = new ResidentInfoLog();
		ObjectReflect.copyPrarentToChild(residentInfo, residentInfoLog);
		SimpleDateFormat stf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		residentInfoLog.setId(UUIDUtil.getUuid());
		residentInfoLog.setCreateTime(stf.format(new Date()));
		residentInfoLog.setOperState(operState.getAbbreviation());
		residentInfoLogMapper.insertResidentInfoLog(residentInfoLog);
	}

	
}
