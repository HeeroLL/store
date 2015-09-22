/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2012-11-22     审计日志从表信息pojo
 */
package com.zebone.btp.log.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 审计日志从表信息pojo
 *
 * @author  lilin
 * @version  [版本号, 2012-11-22]
 */
public class AuditLogInfoExt
{
    /**
     * logId 外键
     */
    private String logId;

    /**
     * 字段名
     */
    private String name;

    /**
     * 新值
     */
    private String value;

    /**
     * 旧值
     */
    private String oldValue;

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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getOldValue()
    {
        return oldValue;
    }

    public void setOldValue(String oldValue)
    {
        this.oldValue = oldValue;
    }
}
