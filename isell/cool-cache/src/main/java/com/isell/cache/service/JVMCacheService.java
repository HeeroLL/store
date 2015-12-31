package com.isell.cache.service;

/**
 * JVM缓存服务接口(使用jvm内存做缓存)
 * 
 * @author lilin
 * @version [版本号, 2015年7月30日]
 */
public interface JVMCacheService {
    
    /**
     * 一分钟
     */
    long MINUTE = 60;
    
    /**
     * 一小时
     */
    long HOUR = MINUTE * 60;
    
    /**
     * 一天
     */
    long DAY = HOUR * 24;
    
    /**
     * 一周
     */
    long WEEK = DAY * 7;
    
    /**
     * 在缓存中设置值
     * 
     * @param key key
     * @param value value
     */
    void set(String key, Object value);
    
    /**
     * 在缓存中设置一个会过期的值（测试版）
     * 
     * @param key key
     * @param value value
     * @param expired 有效期，单位秒
     */
    void set(String key, Object value, long expired);
    
    /**
     * 获取String类型的value
     * 
     * @param key key
     * @return value
     */
    String get(String key);
    
    /**
     * 获取指定类型的value
     *
     * @param key key
     * @param classType class类型
     * @return value
     */
    <T> T get(String key, Class<T> classType);
    
    /**
     * 批量删除key 如果存在
     *
     * @param keys key
     */
    void del(String... key);
    
    /**
     * 设置key的有效期
     *
     * @param key key
     * @param expired 有效期，单位秒
     * @return 设置是否成功
     */
    boolean expire(String key, long expired);
}
