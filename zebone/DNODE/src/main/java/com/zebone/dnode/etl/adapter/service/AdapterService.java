package com.zebone.dnode.etl.adapter.service;

import java.util.Map;

import com.zebone.dnode.etl.adapter.vo.DocConf;

/**
 * 表转换文档接口
 * @author: caixl
 * @date： 日期：Feb 20, 2014
 * @version 1.0
 */

public interface AdapterService {
	void tableToDocByDocXml(DocConf docConf,Map<String,String> mapPara);
}
