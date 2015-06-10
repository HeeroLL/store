/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2013-11-21     某个控件的选项集合
 */
package com.zebone.util;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 某个控件的选项集合
 * 
 * @author lilin
 * @version [版本号, 2013-11-21]
 */
public class PropertyOptions
{
    /**
     * 实际值
     */
    private String id;
    
    /**
     * 显示值
     */
    private String name;
    
    /**
     * 默认构造函数
     */
    public PropertyOptions()
    {
        
    }
    
    /**
     * 默认构造函数
     * 
     * @param id 实际值
     * @param name 显示值
     */
    public PropertyOptions(String id, String name)
    {
        this.id = id;
        this.name = name;
    }
    
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
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
}
