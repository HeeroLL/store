package com.heero.redis;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 
 * Jedis客户端工具类
 * 
 * @author lilin
 * @version [版本号, 2015年2月3日]
 */
@SuppressWarnings("unchecked")
@Component
public class JedisTemplate {
    /**
     * UTF-8字符集
     */
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    
    /**
     * redis连接池
     */
    @Resource(name = "shardedJedisPool")
    private ShardedJedisPool pool;
    
    /**
     * 设置k-v数据
     * 
     * @param key key
     * @param value value
     * @return 结果
     */
    public String set(final String key, final String value) {
        return (String)execute(new JedisCallback() {
            @Override
            public Object doInRedis(ShardedJedis jedis) {
                return jedis.set(key, value);
            }
        });
    }
    
    /**
     * 设置k-v数据，第二个参数必须实现Serializable接口
     * 
     * @param key key
     * @param value value
     * @return 结果
     */
    public String set(final String key, final Object value) {
        return (String)execute(new JedisCallback() {
            @Override
            public Object doInRedis(ShardedJedis jedis) {
                return jedis.set(key.getBytes(UTF_8), Jdkserializers.toSerialization(value));
            }
        });
    }
    
    /**
     * 根据key获取String类型value
     * 
     * @param key key
     * @return value
     */
    public String getStr(final String key) {
        return (String)execute(new JedisCallback() {
            @Override
            public Object doInRedis(ShardedJedis jedis) {
                return jedis.get(key);
            }
        });
    }
    
    /**
     * 根据key获取value
     * 
     * @param key key
     * @return value
     */
    public Object get(final String key) {
        return execute(new JedisCallback() {
            @Override
            public Object doInRedis(ShardedJedis jedis) {
                return Jdkserializers.fromSerialization(jedis.get(key.getBytes(UTF_8)));
            }
        });
    }
    
    /**
     * 设置hashmap
     * 
     * @param key key
     * @param field 字段名
     * @param value 字段值
     * @return long
     */
    public Long hset(final String key, final String field, final String value) {
        return (Long)execute(new JedisCallback() {
            @Override
            public Object doInRedis(ShardedJedis jedis) {
                return jedis.hset(key, field, value);
            }
        });
    }
    
    /**
     * 获取hashmap中的value
     * 
     * @param key key
     * @param field 字段名
     * @return 字段值
     */
    public String hget(final String key, final String field) {
        return (String)execute(new JedisCallback() {
            @Override
            public Object doInRedis(ShardedJedis jedis) {
                return jedis.hget(key, field);
            }
        });
    }
    
    /**
     * 批量获取hashmap的值
     * 
     * @param key key
     * @param fields 字段名集合
     * @return value集合
     */
    public List<String> hmget(final String key, final String... fields) {
        return (List<String>)execute(new JedisCallback() {
            @Override
            public Object doInRedis(ShardedJedis jedis) {
                return jedis.hmget(key, fields);
            }
        });
    }
    
    /**
     * 获取hashmap的键值
     * 
     * @param key key
     * @return map
     */
    public Map<String, String> hgetAll(final String key) {
        return (Map<String, String>)execute(new JedisCallback() {
            @Override
            public Object doInRedis(ShardedJedis jedis) {
                return jedis.hgetAll(key);
            }
        });
    }
    
    /**
     * 执行redis命令
     * 
     * @param callback 回调函数，执行redis命令
     * @return 执行结果
     */
    public Object execute(JedisCallback callback) {
        ShardedJedis jedis = null;
        try {
            jedis = pool.getResource();
            return callback.doInRedis(jedis);
        }
        finally {
            pool.returnResource(jedis);
        }
    }
}
