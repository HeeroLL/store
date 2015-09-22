package com.zebone.register.analyze.service;

import java.util.List;

import com.zebone.register.analyze.vo.AnalyzeDataInfo;


/**
 * 业务数据存储服务
 * @author songjunjie
 * @version 2013-8-18 下午03:08:47
 */
public interface BizDataStorageService {
	public void saveToDataBase(List<AnalyzeDataInfo> analyzeDataList,String docOpeCode);
}
