/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2012-11-22     审计日志主表信息pojo
 */
package com.zebone.btp.log.pojo;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 审计日志主表信息pojo
 *
 * @author lilin
 * @version [版本号, 2012-11-22]
 */
public class AuditLogInfo
{
    /**
     * logId 主键
     */
    private String logId;

    /**
     * 操作人id
     */
    private String actorId;

    /**
     * 操作人姓名
     */
    private String fullName;
    /**
     * 操作时间
     */
    private Date createTime;

    /**
     * IP地址
     */
    private String ipAddress;

    /**
     * 医疗机构层级码
     */
    private String levelCode;

    /**
     * 医疗机构名称
     */
    private String mhoName;

    /**
     * 模块id
     */
    private String modelId;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 业务id
     */
    private String serviceId;

    /**
     * 操作标示
     */
    private String opFlag;

    /**
     * 描述
     */
    private String description;

    /**
     * 从表字段集合
     */
    private List<AuditLogInfoExt> auditLogInfoExtList;

    /**
     * 重写toString方法
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String getLogId()
    {
        return logId;
    }

    public void setLogId(String logId)
    {
        this.logId = logId;
    }

    public String getActorId()
    {
        return actorId;
    }

    public void setActorId(String actorId)
    {
        this.actorId = actorId;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public String getIpAddress()
    {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress)
    {
        this.ipAddress = ipAddress;
    }

    public String getLevelCode()
    {
        return levelCode;
    }

    public void setLevelCode(String levelCode)
    {
        this.levelCode = levelCode;
    }

    public String getModelId()
    {
        return modelId;
    }

    public void setModelId(String modelId)
    {
        this.modelId = modelId;
    }

    public String getServiceId()
    {
        return serviceId;
    }

    public void setServiceId(String serviceId)
    {
        this.serviceId = serviceId;
    }

    public String getOpFlag()
    {
        return opFlag;
    }

    public void setOpFlag(String opFlag)
    {
        this.opFlag = opFlag;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<AuditLogInfoExt> getAuditLogInfoExtList()
    {
        return auditLogInfoExtList;
    }

    public void setAuditLogInfoExtList(List<AuditLogInfoExt> auditLogInfoExtList)
    {
        this.auditLogInfoExtList = auditLogInfoExtList;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public String getMhoName()
    {
        return mhoName;
    }

    public void setMhoName(String mhoName)
    {
        this.mhoName = mhoName;
    }

    public String getModuleName()
    {
        return moduleName;
    }

    public void setModuleName(String moduleName)
    {
        this.moduleName = moduleName;
    }
}
