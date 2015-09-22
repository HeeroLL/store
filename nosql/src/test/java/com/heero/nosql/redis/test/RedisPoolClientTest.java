package com.heero.nosql.redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis连接池示例
 * 
 * @author lilin
 */
public class RedisPoolClientTest {

	private static JedisPool pool;
	static {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(1024);
		config.setMaxIdle(200);
		config.setMaxWaitMillis(1000);
		config.setTestOnBorrow(true);
		config.setTestOnReturn(true);
		pool = new JedisPool(config, "192.168.1.106", 6379);
	}

	public static void main(String[] args) {
		// 从池中获取一个Jedis对象
		Jedis jedis = pool.getResource();
		String keys = "testKey";

		// 取数据
		String value = jedis.get(keys);

		System.out.println(value);

		// 释放对象池
		pool.returnResource(jedis);
		
		pool.destroy();
	}

}
