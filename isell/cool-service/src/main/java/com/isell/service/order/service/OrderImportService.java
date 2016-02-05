package com.isell.service.order.service;

import java.util.Map;

import com.isell.core.util.Record;

/**
 * 订单导入导出接口
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-08]
 */
public interface OrderImportService {
	/**
	 * 导入订单
	 * 
	 * @param param
	 */
	Record saveCoolOrderForImport(Map<String,Object> param);
	
	Record CreateDate();
}
