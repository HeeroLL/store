package com.zebone.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zebone.redis.core.RedisOperations;
import com.zebone.redis.jdbc.JdbcDao;
import com.zebone.util.PropertyConfigurer;
import com.zebone.util.CollectionUtil;

/**
 * 
 * Redis服务接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年4月28日]
 */
public class RedisServiceImpl<T> implements RedisService<T> {
    
    /**
     * 服务是否开启
     */
    private boolean serviceEnabled;
    
    /**
     * Redis操作API接口
     */
    private RedisOperations<T> redisOperations;
    
    /**
     * 查询关系型数据库接口
     */
    private JdbcDao jdbcDao;
    
    /**
     * 读取properties文件配置类
     */
    private PropertyConfigurer propertyConfigurer;
    
    /**
     * 设置k-v数据
     * 
     * @param key key
     * @param value value
     * @return 结果
     */
    @Override
    public String set(String key, String value) {
        if (!serviceEnabled) {
            return null;
        }
        return redisOperations.set(key, value);
    }
    
    /**
     * 设置k-v数据，第二个参数必须实现Serializable接口
     * 
     * @param key key
     * @param value value
     * @return 结果
     */
    @Override
    public String set(String key, Object value) {
        if (!serviceEnabled) {
            return null;
        }
        return redisOperations.set(key, value);
    }
    
    /**
     * 根据key获取String类型value<br>
     * 如需查询数据库key的规则必须是"字典项名"+"."+"id"
     * 
     * @param key key
     * @return value
     */
    @Override
    public String getStr(String key) {
        if (!serviceEnabled) {
            String sql = propertyConfigurer.getProperty(getSqlFromKey(key));
            String id = getIdFromKey(key);
            return jdbcDao.getStringValue(sql, id);
        }
        String value = redisOperations.getStr(key);
        // 缓存中存在直接返回
        if (value != null) {
            return value;
        }
        String sql = propertyConfigurer.getProperty(getSqlFromKey(key));
        String id = getIdFromKey(key);
        // 缓存中不存在则读取关系数据库
        value = jdbcDao.getStringValue(sql, id);
        // 更新缓存
        set(key, value);
        
        return value;
    }
    
    /**
     * 根据key获取对象类型value
     * 
     * @param key key
     * @param c 返回对象的Class
     * @return value
     */
    @SuppressWarnings("unchecked")
    @Override
    public T getObj(String key, Class<T> c) {
        if (!serviceEnabled) {
            String sql = propertyConfigurer.getProperty(getSqlFromKey(key));
            String id = getIdFromKey(key);
            return (T)jdbcDao.getObjectValue(sql, id, c);
        }
        T value = redisOperations.getObj(key);
        // 缓存中存在则直接返回
        if (value != null) {
            return value;
        }
        String sql = propertyConfigurer.getProperty(getSqlFromKey(key));
        String id = getIdFromKey(key);
        // 缓存中不存在则读取关系数据库
        value = (T)jdbcDao.getObjectValue(sql, id, c);
        // 更新缓存
        set(key, value);
        
        return value;
    }
    
    /**
     * 设置hashmap
     * 
     * @param key key
     * @param field 字段名
     * @param value 字段值
     * @return long
     */
    @Override
    public Long hset(String key, String field, String value) {
        if (!serviceEnabled) {
            return null;
        }
        return redisOperations.hset(key, field, value);
    }
    
    /**
     * 批量设置hashmap
     * 
     * @param key key
     * @param map 字段键值对
     * @return String
     */
    @Override
    public String hmset(final String key, final Map<String, String> map) {
        if (!serviceEnabled) {
            return null;
        }
        return redisOperations.hmset(key, map);
    }
    
    /**
     * 获取hashmap的value
     * 
     * @param key key
     * @param field 字段名
     * @param value 字段值
     * @return long
     */
    @Override
    public String hget(String key, String field) {
        if (!serviceEnabled) {
            return jdbcDao.getStringValue(propertyConfigurer.getProperty(key), field);
        }
        String value = redisOperations.hget(key, field);
        // 缓存中存在则直接返回
        if (value != null) {
            return value;
        }
        // 缓存中不存在则读取关系数据库
        value = jdbcDao.getStringValue(propertyConfigurer.getProperty(key), field);
        // 更新缓存
        hset(key, field, value);
        
        return value;
    }
    
    /**
     * 批量获取hashmap的值
     * 
     * @param key key
     * @param fields 字段名集合
     * @return map集合
     */
    @Override
    public Map<String, String> hmget(String key, String... fields) {
        if (!serviceEnabled) {
            return getMapFromList(jdbcDao.getMapList(propertyConfigurer.getProperty(key), new Object[]{fields}));
        }
        List<String> value = redisOperations.hmget(key, fields);
        Map<String, String> resultMap = new HashMap<String, String>();
        // 缓存中存在则直接返回
        if (!CollectionUtil.isEmptyCollection(value)) {
            // 组装结果集
            for (int i = 0; i < fields.length; i++) {
                resultMap.put(fields[i], value.get(i));
            }
            return resultMap;
        }
        String sql = propertyConfigurer.getProperty(key);
        List<String> argList = new ArrayList<String>();
        for (String field : fields) {
            argList.add(field);
        }
        // 对sql做个处理
        StringBuilder builder = new StringBuilder();
        int index = 0;
        builder.append(" in (");
        for (String field : fields) {
            if (index++ != 0) {
                builder.append(',');
            }
            builder.append('\'');
            builder.append(field);
            builder.append('\'');
        }
        builder.append(')');
        sql = sql.replace("=?", builder.toString());
        // 缓存中不存在则读取关系数据库
        resultMap = getMapFromList(jdbcDao.getMapList(sql));
        // 更新缓存
        hmset(key, resultMap);
        
        return resultMap;
    }
    
    /**
     * 获取hashmap的键值
     * 
     * @param key key
     * @return map
     */
    @Override
    public Map<String, String> hgetAll(String key) {
        if (!serviceEnabled) {
            return getMapFromList(jdbcDao.getMapList(propertyConfigurer.getProperty(key)));
        }
        String fullLaodKey = key + ".init";
        // 访问读取标示：是否已全量加载
        if ("true".equals(redisOperations.getStr(fullLaodKey))) {
            Map<String, String> resultMap = redisOperations.hgetAll(key);
            // 缓存中存在则直接返回
            if (resultMap != null && !resultMap.isEmpty()) {
                return resultMap;
            }
        }
        String sql = propertyConfigurer.getProperty(key);
        // 对sql做个处理
        if (sql.indexOf("=?") != -1) {
            if (sql.indexOf("where") != -1) {
                sql = sql.substring(0, sql.indexOf("where"));
            }
            else if (sql.indexOf("WHERE") != -1) {
                sql = sql.substring(0, sql.indexOf("WHERE"));
            }
        }
        
        // 缓存中不存在则读取关系数据库
        Map<String, String> resultMap = getMapFromList(jdbcDao.getMapList(sql));
        // 更新缓存
        hmset(key, resultMap);
        // 更新已全量加载标识
        redisOperations.set(fullLaodKey, "true");
        
        return resultMap;
    }
    
    /**
     * 从栈顶入栈
     * 
     * @param key key
     * @param values 值
     * @return long
     */
    @Override
    public Long lpush(String key, String... values) {
        if (!serviceEnabled) {
            return null;
        }
        return redisOperations.lpush(key, values);
    }
    
    /**
     * 从栈底入栈
     * 
     * @param key key
     * @param values 值
     * @return long
     */
    @Override
    public Long rpush(String key, String... values) {
        if (!serviceEnabled) {
            return null;
        }
        return redisOperations.rpush(key, values);
    }
    
    /**
     * 从栈顶入栈，第二个参数必须实现Serializable接口
     * 
     * @param key key
     * @param values 值
     * @return long
     */
    @Override
    public Long lpush(String key, Object... values) {
        if (!serviceEnabled) {
            return null;
        }
        return redisOperations.lpush(key, values);
    }
    
    /**
     * 从栈底入栈，第二个参数必须实现Serializable接口
     * 
     * @param key key
     * @param values 值
     * @return long
     */
    @Override
    public Long rpush(String key, Object... values) {
        if (!serviceEnabled) {
            return null;
        }
        return redisOperations.rpush(key, values);
    }
    
    /**
     * 从栈顶出栈
     * 
     * @param key key
     * @return String
     */
    @Override
    public String lpopStr(String key) {
        if (!serviceEnabled) {
            return null;
        }
        return redisOperations.lpopStr(key);
    }
    
    /**
     * 从栈底出栈
     * 
     * @param key key
     * @return String
     */
    @Override
    public String rpopStr(String key) {
        if (!serviceEnabled) {
            return null;
        }
        return redisOperations.rpopStr(key);
    }
    
    /**
     * 从栈顶出栈
     * 
     * @param key key
     * @return Object
     */
    @Override
    public T lpopObj(String key) {
        if (!serviceEnabled) {
            return null;
        }
        return redisOperations.lpopObj(key);
    }
    
    /**
     * 从栈底出栈
     * 
     * @param key key
     * @return Object
     */
    @Override
    public T rpopObj(String key) {
        if (!serviceEnabled) {
            return null;
        }
        return redisOperations.rpopObj(key);
    }
    
    /**
     * 从队列左边的start位置取到end位置的数据列表
     * 
     * @param key key
     * @param start 起始索引
     * @param end 结束索引（闭区间）
     * @return List<T>
     */
    @Override
    public List<T> lrangeObj(String key, long start, long end) {
        if (!serviceEnabled) {
            return null;
        }
        return redisOperations.lrangeObj(key, start, end);
    }
    
    /**
     * 从队列左边的start位置取到end位置的数据列表
     * 
     * @param key key
     * @param start 起始索引
     * @param end 结束索引（闭区间）
     * @return List<String>
     */
    @Override
    public List<String> lrangeStr(String key, long start, long end) {
        if (!serviceEnabled) {
            return null;
        }
        return redisOperations.lrangeStr(key, start, end);
    }
    
    /**
     * 获取最后一个'.'之前的字符串
     * 
     * @param key key
     * @return sqlkey
     */
    private String getSqlFromKey(String key) {
        return key.substring(0, key.lastIndexOf('.'));
    }
    
    /**
     * 获取最后一个'.'之后的字符串
     * 
     * @param key key
     * @return id
     */
    private String getIdFromKey(String key) {
        return key.substring(key.lastIndexOf('.') + 1);
    }
    
    /**
     * 从list中获取map
     * 
     * @param mapList mapList
     * @return map
     */
    private Map<String, String> getMapFromList(List<Map<String, String>> mapList) {
        Map<String, String> resultMap = new HashMap<String, String>();
        for (Map<String, String> map : mapList) {
            resultMap.putAll(map);
        }
        return resultMap;
    }
    
    public void setServiceEnabled(boolean serviceEnabled) {
        this.serviceEnabled = serviceEnabled;
    }
    
    public void setRedisOperations(RedisOperations<T> redisOperations) {
        this.redisOperations = redisOperations;
    }
    
    public void setPropertyConfigurer(PropertyConfigurer propertyConfigurer) {
        this.propertyConfigurer = propertyConfigurer;
    }
    
    public void setJdbcDao(JdbcDao jdbcDao) {
        this.jdbcDao = jdbcDao;
    }
}
