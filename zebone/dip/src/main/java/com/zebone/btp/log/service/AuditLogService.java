/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2012-11-27     审计日志业务类（为页面操作提供服务）
 */
package com.zebone.btp.log.service;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.log.pojo.AuditLogInfo;

/**
 * 审计日志业务类（为页面操作提供服务）
 *
 * @author  lilin
 * @version  [版本号, 2012-11-27]
 */
public interface AuditLogService
{
    /**
     * 删除审计日志主表数据
     * @param logIds 日志id集合
     */
    void deleteLog(String... logIds);

    /**
     * 根据日志id查询某条日志的详细信息
     * @param logId 日志id
     * @return 日志信息
     */
    AuditLogInfo findLogById(String logId);

    /**
     * 根据条件分页查询审计日志
     *
     * @param rowBounds 分页信息
     * @param auditLogInfo 查询条件
     * @return page
     */
    Pagination<AuditLogInfo> searchLog(RowBounds rowBounds, AuditLogInfo auditLogInfo);
}
