package com.heero.nosql.redis.test;

import java.util.LinkedList;
import java.util.List;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 客户端实现一致性hash的redis连接池示例
 * 
 * @author lilin
 */
public class ShardedJedisPoolClientTest {

    private static ShardedJedisPool pool;
    static {

        JedisShardInfo jedisShardInfo1 = new JedisShardInfo("192.168.1.106", 6379);
        // JedisShardInfo jedisShardInfo2 = new JedisShardInfo("192.168.1.107",6379);

        List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
        list.add(jedisShardInfo1);
        // list.add(jedisShardInfo2);

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(1024);
        config.setMaxIdle(200);
        config.setMaxWaitMillis(1000);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);

        pool = new ShardedJedisPool(config, list);
    }

    public static void main(String[] args) {
        // 从池中获取一个Jedis对象
        ShardedJedis jedis = pool.getResource();
        String keys = "testKey";

        // 取数据
        String value = jedis.get(keys);

        System.out.println(value);

        // 释放对象池
        pool.returnResource(jedis);
        
        pool.destroy();
    }

}
