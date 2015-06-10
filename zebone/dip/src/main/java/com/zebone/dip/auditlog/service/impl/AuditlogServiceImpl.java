package com.zebone.dip.auditlog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zebone.auditlog.dao.AuditLogMapper;
import com.zebone.auditlog.vo.AuditLog;
import com.zebone.btp.core.mybatis.page.PageConfig;
import com.zebone.btp.core.mybatis.page.PageInfo;
import com.zebone.dip.auditlog.service.AuditlogService;

/**
 * 
 * 审计日志服务接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年6月1日]
 */
@Service("auditlog")
public class AuditlogServiceImpl implements AuditlogService {
    
    /**
     *  审计日志数据库操作接口
     */
    @Resource
    private AuditLogMapper auditLogMapper;
    
    /**
     * 查询审计日志信息
     * 
     * @param page 分页信息
     * @param auditLog 查询条件
     * @return 分页后的审计日志信息
     */
    @Override
    public PageInfo<AuditLog> findAuditlogList(PageConfig page, AuditLog auditLog) {
        PageInfo<AuditLog> pageInfo = new PageInfo<AuditLog>(page.getPage(), page.getRows());
        pageInfo.setRows(auditLogMapper.findAuditlogList(pageInfo.getRowBounds(), auditLog));
        pageInfo.setTotal(auditLogMapper.getTotalCount(auditLog));
        
        return pageInfo;
    }
}
