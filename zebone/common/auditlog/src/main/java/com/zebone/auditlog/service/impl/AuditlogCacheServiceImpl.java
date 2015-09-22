package com.zebone.auditlog.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zebone.auditlog.service.AuditlogCacheService;
import com.zebone.auditlog.vo.AuditLog;
import com.zebone.redis.RedisService;
import com.zebone.util.UUIDUtil;
/**
 * 
 * 审计日志接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年5月27日]
 */
@Service("AuditlogCacheService")
public class AuditlogCacheServiceImpl implements AuditlogCacheService {
    /**
     * Redis服务接口
     */
    @Resource
    private RedisService<AuditLog> redisService;
    
    /**
     * 事件类型map
     */
    @Resource
    private Map<String, String> eventTypeMap;
    
    /**
     * 操作类型map
     */
    @Resource
    private Map<String, String> optTypeMap;
    
    /**
     * 保存日志到缓存
     * 
     * @param auditLog 日志信息
     */
    @Override
    public void saveLogToCache(AuditLog auditLog) {
        auditLog.setLogId(UUIDUtil.getUuid());
        auditLog.setEventType(eventTypeMap.get(auditLog.getEventTypeId()));
        auditLog.setOptType(optTypeMap.get(auditLog.getOptTypeId()));
        redisService.lpush(AuditLog.QUEUE_KEY, auditLog);
    }
}
