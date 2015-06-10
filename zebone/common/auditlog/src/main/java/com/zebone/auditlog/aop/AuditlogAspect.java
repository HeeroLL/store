/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2012-12-5      记录审计日志的切面类
 */
package com.zebone.auditlog.aop;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.BeanUtils;

import com.zebone.auditlog.service.AuditlogCacheService;
import com.zebone.auditlog.vo.AuditLog;
import com.zebone.util.DateUtil;
import com.zebone.util.ReflectUtil;

/**
 * 
 * 记录审计日志的切面类
 * 
 * @author lilin
 * @version [版本号, 2015年4月23日]
 */
public class AuditlogAspect {
    
    /**
     * log4j
     */
    private static final Log log = LogFactory.getLog(AuditlogAspect.class);
    
    /**
     * 需要记录日志的模块的Map，通过spring注入
     */
    @Resource
    private Map<String, AuditLog> logMap;
    
    /**
     * 审计日志接口
     */
    @Resource
    private AuditlogCacheService auditlogCacheService;
    
    /**
     * 记录审计日志
     * 
     * @param joinPoint 切入点，存放代理对象的相关信息
     * @return obj
     * @throws Throwable Throwable
     */
    public Object logging(ProceedingJoinPoint joinPoint)
        throws Throwable {
        // 获得参数
        Object[] args = joinPoint.getArgs();
        // 获得目标对象的类名
        String className = joinPoint.getSignature().getDeclaringType().getName();
        // 获得所执行的方法名
        String methodName = joinPoint.getSignature().getName();
        
        String key = className + '.' + methodName;
        
        AuditLog auditLogConfig = logMap.get(key);
        
        // 为空则表示没有配置日志，则直接调用方法返回
        if (auditLogConfig == null) {
            return joinPoint.proceed(args);
        }
        
        AuditLog auditLog = new AuditLog();
        BeanUtils.copyProperties(auditLogConfig, auditLog);
        
        if (StringUtils.isEmpty(auditLog.getSourceIp())) {
            // TODO: ip为空则从登录信息中获取
            auditLog.setSourceIp("127.0.0.1");
        }
        if (StringUtils.isEmpty(auditLog.getOrgCode())) {
            // TODO: 操作机构ID为空则从登录信息中获取
            auditLog.setOrgCode("1000001");
        }
        if (StringUtils.isEmpty(auditLog.getOrgName())) {
            // TODO: 操作机构名称为空则从登录信息中获取
            auditLog.setOrgName("测试机构");
        }
        if (StringUtils.isEmpty(auditLog.getPersonName())) {
            // TODO: 操作人姓名为空则从登录信息中获取
            auditLog.setPersonName("测试用户");
        }
        if (StringUtils.isEmpty(auditLog.getPersonAccount())) {
            // TODO: 操作人账号为空则从登录信息中获取
            auditLog.setPersonAccount("admin");
        }
        // 操作对象不为空则从第一个参数的该字段获取值
        if (!StringUtils.isEmpty(auditLog.getOptObject()) && args != null && args.length != 0) {
            if (args[0] instanceof String) {
                auditLog.setOptObject((String)args[0]);
            } else {
                auditLog.setOptObject((String)ReflectUtil.getFieldValue(args[0], auditLog.getOptObject()));
            }
        }
        auditLog.setCreateTime(new Date());
        auditLog.setCreateDate(DateUtil.getCurrentDate("yyyyMMdd"));
        auditLog.setResult("正常");
        try {
            return joinPoint.proceed(args);
        } catch (Exception e) {
            log.error(e);
            auditLog.setResult("异常");
            return null;
        } finally {
            auditlogCacheService.saveLogToCache(auditLog);
        }
    }
}
