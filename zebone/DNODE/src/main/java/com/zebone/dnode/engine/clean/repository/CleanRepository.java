package com.zebone.dnode.engine.clean.repository;

import java.util.List;

import com.zebone.dnode.engine.clean.pojo.ClearCloumn;
import com.zebone.dnode.engine.clean.pojo.ClearConfig;
import com.zebone.dnode.engine.clean.pojo.ClearLog;
import com.zebone.dnode.engine.clean.pojo.TableConfig;

public interface CleanRepository {
    
	/**
	 * 获取表配置
	 * @param tableConfigId 表配置主键
	 * @return
	 */
	TableConfig getTableConfig(String tableConfigId);
	
	/**
	 * 获取清洗配置
	 * @param taskId  任务id
	 * @return
	 */
    ClearConfig	getClearConfig(String taskId);
    
    
    
    /**
     * 获取清洗列配置
     * @param clearConfigId 清洗配置主键
     * @return
     */
    List<ClearCloumn> getClearCloumn(String clearConfigId);
    
    
    
    /**
     * 保存错误清洗日志
     * @param clearLogs
     */
    void saveClearLog(List<ClearLog> clearLogs);
}
