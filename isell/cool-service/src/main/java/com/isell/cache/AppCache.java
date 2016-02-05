package com.isell.cache;

import java.util.HashMap;

import javax.annotation.Resource;

import com.isell.cache.service.JVMCacheService;
import com.isell.core.util.CommonUtils;
import com.isell.core.util.Record;

/**
 * app用户登录缓存
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-09]
 */
public class AppCache {
	
	/**
	 * 失效时间暂时设置15分钟
	 */
	private final static  long LOSE_TIME = 15*60;
	
	/**
	 * JVM缓存服务接口
	 */
	@Resource
	private JVMCacheService jvmCacheService;
	
	/**
	 * 设置缓存，返回标示
	 * @param value
	 * @return
	 */
	public Record set(Object value){
		Record record = new Record();
		String uuid = CommonUtils.uuid();
		jvmCacheService.set(uuid, value, LOSE_TIME);
		
		record.set("cacheId",uuid);
		return record;
	}
	
	/**
	 * 获得缓存信息
	 * @return
	 */
	public Record get(String key){
		Record record = new Record();
		record.set("cacheMap", jvmCacheService.get(key,new HashMap<String,Object>().getClass()));
		return record;
	}
	
	

}
