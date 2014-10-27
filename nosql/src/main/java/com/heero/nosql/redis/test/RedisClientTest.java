package com.heero.nosql.redis.test;

import redis.clients.jedis.Jedis;

public class RedisClientTest {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.1.106", 6379);
		String keys = "testKey";

		// 删数据
		jedis.del(keys);
		// 存数据
		jedis.set(keys, "This is a first key.");
		// 取数据
		String value = jedis.get(keys);

		System.out.println(value);
		
		jedis.close();
	}

}
