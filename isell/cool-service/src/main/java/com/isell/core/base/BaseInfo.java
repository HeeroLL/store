package com.isell.core.base;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * 基础vo类，实现一些vo的公共方法
 * 
 * @author lilin
 * @version [版本号, 2015年6月19日]
 */
public abstract class BaseInfo implements Serializable {

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 重写toString
     * 
     * @return String
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    
}
