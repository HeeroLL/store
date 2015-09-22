package com.zebone.auditlog.service;

import com.zebone.auditlog.vo.AuditLog;

/**
 * 
 * 审计日志接口
 * 
 * @author lilin
 * @version [版本号, 2015年5月27日]
 */
public interface AuditlogCacheService {
    /**
     * 保存日志到缓存
     * 
     * @param auditLog 日志信息
     */
    void saveLogToCache(AuditLog auditLog);
}
