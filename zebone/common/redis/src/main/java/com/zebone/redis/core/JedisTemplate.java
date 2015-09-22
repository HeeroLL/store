package com.zebone.redis.core;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.ShardedJedis;
import redis.clients.util.Pool;

import com.zebone.util.Jdkserializers;

/**
 * 
 * Jedis客户端工具类
 * 
 * @author lilin
 * @version [版本号, 2015年2月3日]
 */
@SuppressWarnings("unchecked")
public class JedisTemplate<T> implements RedisOperations<T> {
    /**
     * UTF-8字符集
     */
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    
    /**
     * redis连接池
     */
    private Pool<ShardedJedis> pool;
    
    /**
     * 设置k-v数据
     * 
     * @param key key
     * @param value value
     * @return 结果
     */
    @Override
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
    @Override
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
    @Override
    public String getStr(final String key) {
        return (String)execute(new JedisCallback() {
            @Override
            public Object doInRedis(ShardedJedis jedis) {
                return jedis.get(key);
            }
        });
    }
    
    /**
     * 根据key获取对象类型value
     * 
     * @param key key
     * @return value
     */
    @Override
    public T getObj(final String key) {
        return (T)execute(new JedisCallback() {
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
    @Override
    public Long hset(final String key, final String field, final String value) {
        return (Long)execute(new JedisCallback() {
            @Override
            public Object doInRedis(ShardedJedis jedis) {
                return jedis.hset(key, field, value);
            }
        });
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
        return (String)execute(new JedisCallback() {
            @Override
            public Object doInRedis(ShardedJedis jedis) {
                return jedis.hmset(key, map);
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
    @Override
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
    @Override
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
    @Override
    public Map<String, String> hgetAll(final String key) {
        return (Map<String, String>)execute(new JedisCallback() {
            @Override
            public Object doInRedis(ShardedJedis jedis) {
                return jedis.hgetAll(key);
            }
        });
    }
    
    /**
     * 从栈顶入栈
     * 
     * @param key key
     * @param values 值
     * @return long
     */
    @Override
    public Long lpush(final String key, final String... values) {
        return (Long)execute(new JedisCallback() {
            @Override
            public Object doInRedis(ShardedJedis jedis) {
                return jedis.lpush(key, values);
            }
        });
    }
    
    /**
     * 从栈底入栈
     * 
     * @param key key
     * @param values 值
     * @return long
     */
    @Override
    public Long rpush(final String key, final String... values) {
        return (Long)execute(new JedisCallback() {
            @Override
            public Object doInRedis(ShardedJedis jedis) {
                return jedis.rpush(key, values);
            }
        });
    }
    
    /**
     * 从栈顶入栈，第二个参数必须实现Serializable接口
     * 
     * @param key key
     * @param values 值
     * @return long
     */
    @Override
    public Long lpush(final String key, final Object... values) {
        return (Long)execute(new JedisCallback() {
            @Override
            public Object doInRedis(ShardedJedis jedis) {
                byte[][] objArr = new byte[values.length][];
                for (int i = 0; i < values.length; i++) {
                    objArr[i] = Jdkserializers.toSerialization(values[i]);
                }
                return jedis.lpush(key.getBytes(UTF_8), objArr);
            }
        });
    }
    
    /**
     * 从栈底入栈，第二个参数必须实现Serializable接口
     * 
     * @param key key
     * @param values 值
     * @return long
     */
    @Override
    public Long rpush(final String key, final Object... values) {
        return (Long)execute(new JedisCallback() {
            @Override
            public Object doInRedis(ShardedJedis jedis) {
                byte[][] objArr = new byte[values.length][];
                for (int i = 0; i < values.length; i++) {
                    objArr[i] = Jdkserializers.toSerialization(values[i]);
                }
                return jedis.rpush(key.getBytes(UTF_8), objArr);
            }
        });
    }
    
    /**
     * 从栈顶出栈
     * 
     * @param key key
     * @return String
     */
    @Override
    public String lpopStr(final String key) {
        return (String)execute(new JedisCallback() {
            @Override
            public Object doInRedis(ShardedJedis jedis) {
                return jedis.lpop(key);
            }
        });
    }
    
    /**
     * 从栈底出栈
     * 
     * @param key key
     * @return String
     */
    @Override
    public String rpopStr(final String key) {
        return (String)execute(new JedisCallback() {
            @Override
            public Object doInRedis(ShardedJedis jedis) {
                return jedis.rpop(key);
            }
        });
    }
    
    /**
     * 从栈顶出栈
     * 
     * @param key key
     * @return Object
     */
    @Override
    public T lpopObj(final String key) {
        return (T)execute(new JedisCallback() {
            @Override
            public Object doInRedis(ShardedJedis jedis) {
                return Jdkserializers.fromSerialization(jedis.lpop(key.getBytes(UTF_8)));
            }
        });
    }
    
    /**
     * 从栈底出栈
     * 
     * @param key key
     * @return Object
     */
    @Override
    public T rpopObj(final String key) {
        return (T)execute(new JedisCallback() {
            @Override
            public Object doInRedis(ShardedJedis jedis) {
                return Jdkserializers.fromSerialization(jedis.rpop(key.getBytes(UTF_8)));
            }
        });
    }
    
    /**
     * 从队列左边的start位置取到end位置的数据列表
     * 
     * @param key key
     * @param start 起始索引
     * @param end 结束索引（闭区间）
     * @return List<Object>
     */
    @Override
    public List<T> lrangeObj(final String key, final long start, final long end) {
        return (List<T>)execute(new JedisCallback() {
            @Override
            public Object doInRedis(ShardedJedis jedis) {
                List<byte[]> byteList = jedis.lrange(key.getBytes(UTF_8), start, end);
                List<T> objList = new ArrayList<T>(byteList.size());
                for (byte[] value : byteList) {
                    objList.add((T)Jdkserializers.toSerialization(value));
                }
                return objList;
            }
        });
    }
    
    /**
     * 从队列左边的start位置取到end位置的数据列表
     * 
     * @param key key
     * @param start 起始索引
     * @param end 结束索引（闭区间）
     * @return List<Object>
     */
    @Override
    public List<String> lrangeStr(final String key, final long start, final long end) {
        return (List<String>)execute(new JedisCallback() {
            @Override
            public Object doInRedis(ShardedJedis jedis) {
                return jedis.lrange(key, start, end);
            }
        });
    }
    
    /**
     * 执行redis命令
     * 
     * @param callback 回调函数，执行redis命令
     * @return 执行结果
     */
    private Object execute(JedisCallback callback) {
        ShardedJedis jedis = null;
        try {
            jedis = pool.getResource();
            return callback.doInRedis(jedis);
        }
        finally {
            pool.returnResource(jedis);
        }
    }
    
    public void setPool(Pool<ShardedJedis> pool) {
        this.pool = pool;
    }
    
    /**
     * 
     * 内部类，定义redis回调接口
     * 
     * @author lilin
     * @version [版本号, 2015年4月27日]
     */
    private interface JedisCallback {
        /**
         * 回调函数，做实际处理
         * 
         * @param jedis redis连接
         * @return 返回处理结果
         */
        Object doInRedis(ShardedJedis jedis);
    }
    
}
