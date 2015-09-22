package com.zebone.register.validation.service;

import com.zebone.register.validation.domain.CheckConfig;



/**
 * 校验入口service
 * @author 陈阵 
 * @date 2013-7-30 下午2:41:49
 */
public interface CheckService {
	  
	  /**
	   * 校验文档数据的正确性
	   * @param xmlData  传过来的文档数据
	   * @param CheckConfig 校验配置
	   * @author 陈阵 
	   * @date 2013-7-30 下午2:47:20
	   */
      public String check(CheckConfig checkConfig,String xmlData,String ip);
}
