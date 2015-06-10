package com.zebone.auditlog.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.auditlog.vo.AuditLog;
import com.zebone.btp.core.mybatis.LogMapper;

/**
 * 
 * 审计日志数据库操作接口
 * 
 * @author lilin
 * @version [版本号, 2015年4月24日]
 */
@LogMapper
public interface AuditLogMapper {
    
    /**
     * 查询审计日志分页信息
     * 
     * @param rowBounds 分页对象
     * @param auditLog 查询条件
     * @return 体质分页信息
     */
    List<AuditLog> findAuditlogList(RowBounds rowBounds, AuditLog auditLog);
    
    /**
     * 获得审计日志总数
     * 
     * @param auditLog 查询条件
     * @return 审计日志总数
     */
    int getTotalCount(AuditLog auditLog);
    
    /**
     * 保存
     */
    int saveAuditLog(AuditLog auditLog);
}
