package com.zebone.auditlog.service;

/**
 * 
 * 审计日志数据库接口
 * 
 * @author lilin
 * @version [版本号, 2015年5月27日]
 */
public interface AuditlogDbService {
    /**
     * 保存日志到数据库
     */
    void saveLogToDb();
}
