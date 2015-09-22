/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2012-11-22     审计日志操作实现类
 */
package com.zebone.btp.log.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.zebone.btp.core.util.MessageUtil;
import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.log.LogContext;
import com.zebone.btp.log.dao.LogMapper;
import com.zebone.btp.log.pojo.AuditLogInfo;
import com.zebone.btp.log.pojo.AuditLogInfoExt;
import com.zebone.btp.log.pojo.LogConfigInfo;
import com.zebone.btp.log.service.AuditLogService;
import com.zebone.btp.log.service.BtpLog;
import com.zebone.btp.log.thread.LogQueue;
import com.zebone.btp.security.UserDetails;
import com.zebone.util.ReflectUtil;
import com.zebone.util.UUIDUtil;

/**
 * 审计日志操作实现类
 *
 * @author lilin
 * @version [版本号, 2012-11-22]
 */
@Service("btpLog")
public class AuditLogServiceImpl implements BtpLog, AuditLogService
{
    /**
     * 定义log4j
     */
    private static Logger log = Logger.getLogger(AuditLogServiceImpl.class);

    /**
     * 操作数据库的mapper对象
     */
    @Autowired
    private LogMapper mapper;

    /**
     * 日志队列
     */
    //@Autowired
    private LogQueue logQueue;

    /**
     * 审计日志初始化上下文
     */
    //@Autowired
    private LogContext logContext;

    /**
     * 根据条件分页查询审计日志
     *
     * @param rowBounds 分页信息
     * @param auditLogInfo 查询条件
     * @return page
     */
    public Pagination<AuditLogInfo> searchLog(RowBounds rowBounds, AuditLogInfo auditLogInfo)
    {
        // 声明分页对象
        Pagination<AuditLogInfo> page = new Pagination<AuditLogInfo>();
        List<AuditLogInfo> auditLogInfoList = mapper.searchLog(rowBounds, auditLogInfo);

        page.setData(auditLogInfoList);
        page.setTotalCount(mapper.getTotalCount(auditLogInfo));

        return page;
    }

    /**
     * 删除审计日志主表数据
     *
     * @param logIds 日志id集合
     */
    public void deleteLog(String... logIds)
    {
        if (logIds != null)
        {
            for (String logId : logIds)
            {
                // 先删从表再删主表
                mapper.deleteLogExt(logId);
                mapper.deleteLogById(logId);
            }
        }
    }

    /**
     * 根据日志id查询某条日志的详细信息
     *
     * @param logId 日志id
     * @return 日志信息
     */
    public AuditLogInfo findLogById(String logId)
    {
        return mapper.findLogById(logId);
    }

    /**
     * 记录操作日志(修改的情况)
     *
     * @param id 一种操作的唯一标识，在常量中定义
     * @param serviceId 业务id
     * @param newObj 存放新值的对象
     * @param oldObj 存放旧值的对象
     */
    @Override
    public void log(String id, String serviceId, Object newObj, Object oldObj)
    {
        saveLog(id, serviceId, newObj, oldObj);
    }

    /**
     * 记录操作日志(新增、删除的情况)
     *
     * @param id 一种操作的唯一标识，在常量中定义
     * @param serviceId 业务id
     * @param newObj 存放新值的对象
     */
    @Override
    public void log(String id, String serviceId, Object newObj)
    {
        saveLog(id, serviceId, newObj, null);
    }

    /**
     * 记录操作日志(只需记录是什么操作的情况)
     *
     * @param id 一种操作的唯一标识，在常量中定义
     */
    @Override
    public void log(String id)
    {
        saveLog(id, null, null, null);
    }

    private void saveLog(String id, String serviceId, Object newObj, Object oldObj)
    {
        LogConfigInfo logConfigInfo = getLogConfigInfo(id);
        if (logConfigInfo == null)
        {
            return;
        }

        AuditLogInfo auditLogInfo = getAuditLogInfo(logConfigInfo, newObj, oldObj);
        auditLogInfo.setServiceId(serviceId);

        // 这里只负责将日志存放到缓存队列中，由另一个线程负责将日志信息入库
        logQueue.putLog(auditLogInfo);

//        // 保存主表
//        mapper.saveLog(auditLogInfo);
//
//        if (!CollectionUtils.isEmpty(auditLogInfo.getAuditLogInfoExtList()))
//        {
//            for (AuditLogInfoExt auditLogInfoExt : auditLogInfo.getAuditLogInfoExtList())
//            {
//                // 保存从表
//                mapper.saveLogExt(auditLogInfoExt);
//            }
//        }
    }

    private AuditLogInfo getAuditLogInfo(LogConfigInfo logConfigInfo, Object newObj, Object oldObj)
    {
        // 获取用户登录信息
        UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();

        AuditLogInfo auditLogInfo = new AuditLogInfo();

        // 生成主键
        String logId = UUIDUtil.getUuid();
        auditLogInfo.setLogId(logId);
        auditLogInfo.setActorId(userDetails.getPersonnelId());
        auditLogInfo.setCreateTime(new Date());
        auditLogInfo.setIpAddress(userDetails.getIp());
        if (userDetails.getMhoList() != null && userDetails.getMhoList().size() > 0) {
            auditLogInfo.setLevelCode(userDetails.getMhoList().get(0).getLevelCode());
        }
        auditLogInfo.setModelId(logConfigInfo.getModelId());
        auditLogInfo.setOpFlag(logConfigInfo.getOpFlag());
        // 获取描述信息
        auditLogInfo.setDescription(MessageUtil.getMessage("log." + logConfigInfo.getId()));

        if (!CollectionUtils.isEmpty(logConfigInfo.getFieldList()))
        {
            // 日志模块不影响其他业务模块功能，捕获所有异常，防止程序崩溃
            try
            {
                auditLogInfo.setAuditLogInfoExtList(new ArrayList<AuditLogInfoExt>());

                for (String field : logConfigInfo.getFieldList())
                {
                    AuditLogInfoExt auditLogInfoExt = new AuditLogInfoExt();
                    // 设置外键
                    auditLogInfoExt.setLogId(logId);
                    // 字段名称
                    auditLogInfoExt.setName(field);
                    if (newObj != null)
                    {
                        Object filedValue = ReflectUtil.getFieldValue(newObj, field);
                        auditLogInfoExt.setValue(String.valueOf(filedValue));
                    }

                    if (oldObj != null)
                    {
                        Object filedValue = ReflectUtil.getFieldValue(oldObj, field);
                        auditLogInfoExt.setOldValue(String.valueOf(filedValue));
                    }

                    auditLogInfo.getAuditLogInfoExtList().add(auditLogInfoExt);
                }
            }
            catch (Exception e)
            {
                // 吃异常，只记录日志
                log.error(e);
            }
        }

        return auditLogInfo;
    }

    private LogConfigInfo getLogConfigInfo(String id)
    {
        LogConfigInfo logConfigInfo = logContext.getLogConfigInfo(id);
        if (logConfigInfo == null)
        {
            log.warn("This log id is not been defined in the xml, id is " + id);
        }

        return logConfigInfo;
    }
}
