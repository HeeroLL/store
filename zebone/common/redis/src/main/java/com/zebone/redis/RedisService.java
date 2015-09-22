package com.zebone.redis;

import java.util.List;
import java.util.Map;

/**
 * 
 * Redis服务接口
 * 
 * @author lilin
 * @version [版本号, 2015年4月28日]
 */
public interface RedisService<T> {
    /**
     * 设置k-v数据
     * 
     * @param key key
     * @param value value
     * @return 结果
     */
    String set(final String key, final String value);
    
    /**
     * 设置k-v数据，第二个参数必须实现Serializable接口
     * 
     * @param key key
     * @param value value
     * @return 结果
     */
    String set(final String key, final Object value);
    
    /**
     * 根据key获取String类型value
     * 
     * @param key key
     * @return value
     */
    String getStr(final String key);
    
    /**
     * 根据key获取对象类型value
     * 
     * @param key key
     * @param c 返回对象的Class
     * @return value
     */
    T getObj(final String key, final Class<T> c);
    
    /**
     * 设置hashmap
     * 
     * @param key key
     * @param field 字段名
     * @param value 字段值
     * @return long
     */
    Long hset(final String key, final String field, final String value);
    
    /**
     * 批量设置hashmap
     * 
     * @param key key
     * @param map 字段键值对
     * @return String
     */
    String hmset(final String key, final Map<String, String> map);
    
    /**
     * 获取hashmap中的value
     * 
     * @param key key
     * @param field 字段名
     * @return 字段值
     */
    String hget(final String key, final String field);
    
    /**
     * 批量获取hashmap的值
     * 
     * @param key key
     * @param fields 字段名集合
     * @return map集合
     */
    Map<String, String> hmget(final String key, final String... fields);
    
    /**
     * 获取hashmap的键值
     * 
     * @param key key
     * @return map
     */
    Map<String, String> hgetAll(final String key);
    
    /**
     * 从栈顶入栈
     * 
     * @param key key
     * @param values 值
     * @return long
     */
    Long lpush(final String key, final String... values);
    
    /**
     * 从栈底入栈
     * 
     * @param key key
     * @param values 值
     * @return long
     */
    Long rpush(final String key, final String... values);
    
    /**
     * 从栈顶入栈，第二个参数必须实现Serializable接口
     * 
     * @param key key
     * @param values 值
     * @return long
     */
    Long lpush(final String key, final Object... values);
    
    /**
     * 从栈底入栈，第二个参数必须实现Serializable接口
     * 
     * @param key key
     * @param values 值
     * @return long
     */
    Long rpush(final String key, final Object... values);
    
    /**
     * 从栈顶出栈
     * 
     * @param key key
     * @return String
     */
    String lpopStr(final String key);
    
    /**
     * 从栈底出栈
     * 
     * @param key key
     * @return String
     */
    String rpopStr(final String key);
    
    /**
     * 从栈顶出栈
     * 
     * @param key key
     * @return Object
     */
    T lpopObj(final String key);
    
    /**
     * 从栈底出栈
     * 
     * @param key key
     * @return Object
     */
    T rpopObj(final String key);
    
    /**
     * 从队列左边的start位置取到end位置的数据列表
     * 
     * @param key key
     * @param start 起始索引
     * @param end 结束索引（闭区间）
     * @return List<T>
     */
    List<T> lrangeObj(final String key, final long start, final long end);
    
    /**
     * 从队列左边的start位置取到end位置的数据列表
     * 
     * @param key key
     * @param start 起始索引
     * @param end 结束索引（闭区间）
     * @return List<String>
     */
    List<String> lrangeStr(final String key, final long start, final long end);
}
