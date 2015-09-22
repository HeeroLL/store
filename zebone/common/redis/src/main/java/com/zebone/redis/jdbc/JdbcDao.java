package com.zebone.redis.jdbc;

import java.util.List;
import java.util.Map;


/**
 * 
 * 查询关系型数据库接口
 * 
 * @author lilin
 * @version [版本号, 2015年4月28日]
 */
public interface JdbcDao {
    /**
     * 根据sql和id获取对应的value
     * 
     * @param sql sql
     * @param id id
     * @return value
     */
    String getStringValue(String sql, String id);
    
    /**
     * 根据sql和id获取对应的对象
     * 
     * @param sql sql
     * @param id id
     * @param c class
     * @return object
     */
    Object getObjectValue(String sql, String id, Class<?> c);
    
    /**
     * 根据sql和参数批量获取数据
     *
     * @param sql sql
     * @param args 参数
     * @return List
     */
    List<Map<String,String>> getMapList(String sql, Object... args);
}
