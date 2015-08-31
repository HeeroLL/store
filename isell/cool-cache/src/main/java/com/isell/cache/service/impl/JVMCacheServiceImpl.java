package com.isell.cache.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.isell.cache.service.JVMCacheService;

/**
 * JVM缓存服务接口实现类(使用jvm内存做缓存)
 * 
 * @author lilin
 * @version [版本号, 2015年7月30日]
 */
@Service("jvmCacheService")
public class JVMCacheServiceImpl implements JVMCacheService {
    /**
     * 缓存map
     */
    private Map<String, Object> cacheMap = new HashMap<String, Object>();
    
    /**
     * key的有效期map
     */
    private Map<String, Long> expiredMap = new HashMap<String, Long>();
    
    @Override
    public void set(String key, Object value) {
        cacheMap.put(key, value);
    }
    
    @Override
    public void set(String key, Object value, long expired) {
        cacheMap.put(key, value);
        expiredMap.put(key, System.currentTimeMillis() + expired * 1000);
    }
    
    @Override
    public String get(String key) {
        return get(key, String.class);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key, Class<T> classType) {
        Long time = expiredMap.get(key);
        // 超时则删除缓存
        if (time != null && time < System.currentTimeMillis()) {
            del(key);
        }
        return (T)cacheMap.get(key);
    }
    
    @Override
    public void del(String... keys) {
        for (String key : keys) {
            if (cacheMap.remove(key) != null) {
                expiredMap.remove(key);
            }
        }
    }
    
    @Override
    public boolean expire(String key, long expired) {
        if (cacheMap.get(key) == null) {
            return false;
        }
        expiredMap.put(key, System.currentTimeMillis() + expired * 1000);
        return true;
    }
    
    /**
     * 定时任务执行的方法，定时清除过期的key
     */
    public void execute() {
        List<String> delKeyList = new ArrayList<String>();
        for (Entry<String, Long> entry : expiredMap.entrySet()) {
            if (entry.getValue() <= System.currentTimeMillis()) {
                delKeyList.add(entry.getKey());
            }
        }
        String[] keys = new String[delKeyList.size()];
        del(delKeyList.toArray(keys));
    }
}
