package com.zebone.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 
 * 继承PropertyPlaceholderConfigurer定义自己的PropertyConfigurer<br>
 * 主要思想是通过PropertyPlaceholderConfigurer的mergeProperties方法获取spring加载完成的键值对
 * 
 * @author lilin
 * @version [版本号, 2015年4月28日]
 */
public class PropertyConfigurer extends PropertyPlaceholderConfigurer {
    /**
     * 自定义properties
     */
    private Properties props;
    
    /**
     * 获取spring加载时的properties
     * 
     * @return properties
     * @throws IOException IOException
     */
    @Override
    public Properties mergeProperties()
        throws IOException {
        props = super.mergeProperties();
        return props;
    }
    
    /**
     * 根据Key获取value
     * 
     * @param key key
     * @return value
     */
    public String getProperty(String key) {
        return props.getProperty(key);
    }
    
    /**
     * 根据Key获取value，如果value为空则返回defaultValue
     * 
     * @param key key
     * @param defaultValue 默认值
     * @return value
     */
    public String getProperty(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }
}
