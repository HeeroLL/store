package com.zebone.dnode.task.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;


/**
 * 缓存类
 * @author cz
 *
 */
public class Cache {
	
	@Autowired
	private CacheManager cacheManager;
	
	private String cacheName ;
	
	
	public Cache() {
        this.cacheName = "default";   
	}

	public  Object get(String key){
		ValueWrapper vw = cacheManager.getCache(cacheName).get(key);
		if(vw == null){
			return null;
		}
		return vw.get();
	}
	
	public  void put(String key, Object obj){
		cacheManager.getCache(cacheName).put(key, obj);
	}
	
	public void evict(String key){
		cacheManager.getCache(cacheName).evict(key);
	}
	
}
