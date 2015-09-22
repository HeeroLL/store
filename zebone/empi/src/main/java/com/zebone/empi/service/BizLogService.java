package com.zebone.empi.service;

import com.zebone.empi.vo.EmpiLog;
import com.zebone.empi.vo.ResidentInfo;

/**
 * 日志接口
 * @author YinCm
 * @version 2013-7-31 下午10:15:20
 */
public interface BizLogService {
	/**
	 * 记录empiLog日志
	 * @param log
	 */
	public void log(EmpiLog log);
	
	/**
	 * 记录成功日志
	 * @param residentInfoLog, operState：1增加，2更新，3删除
	 */
	public void log(ResidentInfo residentInfo, ResidentInfoOperationState operState);
	
}

