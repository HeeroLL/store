package com.zebone.auditlog.service.impl;

import javax.annotation.Resource;

import com.zebone.auditlog.dao.AuditLogMapper;
import com.zebone.auditlog.service.AuditlogDbService;
import com.zebone.auditlog.vo.AuditLog;
import com.zebone.redis.RedisService;

/**
 * 
 *  审计日志数据库接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年5月29日]
 */
public class AuditlogDbServiceImpl implements AuditlogDbService {
    /**
     * Redis服务接口
     */
    @Resource
    private RedisService<AuditLog> redisService;
    
    
    /**
     * 审计日志数据库操作dao层
     */
    @Resource
    private AuditLogMapper auditLogMapper;
   
    /**
     * 保存日志到数据库
     */
    @Override
    public void saveLogToDb() {
        AuditLog auditLog;
        while ((auditLog = redisService.rpopObj(AuditLog.QUEUE_KEY)) != null) {
            auditLogMapper.saveAuditLog(auditLog);
        }
    }
}
