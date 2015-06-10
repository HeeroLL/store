package com.zebone.dip.auditlog.service;

import com.zebone.auditlog.vo.AuditLog;
import com.zebone.btp.core.mybatis.page.PageConfig;
import com.zebone.btp.core.mybatis.page.PageInfo;

/**
 * 
 * 审计日志服务接口
 * 
 * @author lilin
 * @version [版本号, 2015年6月1日]
 */
public interface AuditlogService {
    /**
     * 查询审计日志信息
     * 
     * @param page 分页信息
     * @param auditLog 查询条件
     * @return 分页后的审计日志信息
     */
    PageInfo<AuditLog> findAuditlogList(PageConfig page, AuditLog auditLog);
}
