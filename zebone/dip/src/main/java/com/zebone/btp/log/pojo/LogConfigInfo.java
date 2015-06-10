/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2012-11-26     日志配置信息pojo
 */
package com.zebone.btp.log.pojo;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 日志配置信息pojo
 *
 * @author lilin
 * @version [版本号, 2012-11-26]
 */
public class LogConfigInfo
{
    /**
     * id
     */
    private String id;

    /**
     * 操作标识
     */
    private String opFlag;

    /**
     * 模块id
     */
    private String modelId;

    /**
     * 所在参数索引
     */
    private int paramIndex;

    /**
     * 业务id(主键)字段名
     */
    private String serviceId;

    /**
     * 修改时需要调用的spring的bean的id
     */
    private String updateBeanId;

    /**
     * 修改时需要调用的方法名
     */
    private String updateMethod;

    /**
     * 字段列表
     */
    private List<String> fieldList;

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

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getOpFlag()
    {
        return opFlag;
    }

    public void setOpFlag(String opFlag)
    {
        this.opFlag = opFlag;
    }

    public String getModelId()
    {
        return modelId;
    }

    public void setModelId(String modelId)
    {
        this.modelId = modelId;
    }

    public List<String> getFieldList()
    {
        return fieldList;
    }

    public void setFieldList(List<String> fieldList)
    {
        this.fieldList = fieldList;
    }

    public int getParamIndex()
    {
        return paramIndex;
    }

    public void setParamIndex(int paramIndex)
    {
        this.paramIndex = paramIndex;
    }

    public String getServiceId()
    {
        return serviceId;
    }

    public void setServiceId(String serviceId)
    {
        this.serviceId = serviceId;
    }

    public String getUpdateBeanId()
    {
        return updateBeanId;
    }

    public void setUpdateBeanId(String updateBeanId)
    {
        this.updateBeanId = updateBeanId;
    }

    public String getUpdateMethod()
    {
        return updateMethod;
    }

    public void setUpdateMethod(String updateMethod)
    {
        this.updateMethod = updateMethod;
    }
}
