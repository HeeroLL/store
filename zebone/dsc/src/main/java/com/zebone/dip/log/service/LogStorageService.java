package com.zebone.dip.log.service;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 接口描述：日志记录接口
 * @author: caixl
 * @date： 日期：Sep 10, 2013
 * @version 1.0
 */
@WebService
public interface LogStorageService {
	/**
	 * 方法描述: 存储日志信息
	 * @author caixl
	 * @date Sep 10, 2013
	 * @param logxml 日志信息
	 * void
	 */
	void saveLog(@WebParam(name="logxml")String logxml);
}