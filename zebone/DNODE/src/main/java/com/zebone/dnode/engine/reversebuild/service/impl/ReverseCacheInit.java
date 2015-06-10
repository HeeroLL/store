package com.zebone.dnode.engine.reversebuild.service.impl;

import org.springframework.cache.CacheManager;

public class ReverseCacheInit {
	
	private CacheManager cacheManager;
    
	public void init(){
		
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
	
}
