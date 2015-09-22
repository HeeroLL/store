package com.zebone.store.dao;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.store.vo.DocStorageLog;
/**
 * 文档存储日志数据接口层
 */
@Mapper
public interface DocStorageLogMapper {

	/**
	 * @author caixl
	 * @date Aug 6, 2013
	 * @description TODO 插入操作记录
	 * @param docStorageLog
	 * @return int 
	 */
	int insert(DocStorageLog docStorageLog);
    
}