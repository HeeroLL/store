package com.heero.redis;

import redis.clients.jedis.ShardedJedis;

/**
 * 
 * redis回调接口
 * 
 * @author lilin
 * @version [版本号, 2015年2月3日]
 */
public interface JedisCallback
{
    /**
     * 回调函数，做实际处理
     * 
     * @param jedis redis连接
     * @return 返回处理结果
     */
    Object doInRedis(ShardedJedis jedis);
}
