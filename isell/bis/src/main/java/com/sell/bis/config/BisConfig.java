package com.sell.bis.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.sell.bis.auth.bean.AccessSystemInfo;

/**
 * 系统配置类
 * 
 * @author lilin
 * @version [版本号, 2015年7月23日]
 */
@Repository("config")
public class BisConfig {
    /**
     * 接入系统map集合
     */
    private Map<String, AccessSystemInfo> accessSysMap;
    
    /**
     * 接入系统映射map集合
     */
    private Map<String, String> sysMappingMap;
    
    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        // 系统加载时初始化信息 TODO 以后从数据库中读取
        accessSysMap = new HashMap<String, AccessSystemInfo>();
        accessSysMap.put("lootooker", new AccessSystemInfo("lootooker", "lootookerPrivateKEY", false));
        
        sysMappingMap = new HashMap<String, String>();
        sysMappingMap.put("lootooker" + "sendOrderStatus", "testURL");
        sysMappingMap.put("lootooker" + "sendShipOrder", "testURL");
    }

    public Map<String, AccessSystemInfo> getAccessSysMap() {
        return accessSysMap;
    }
}
