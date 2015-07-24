package com.sell.bis.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.sell.bis.config.dao.AccessSystemMapper;
import com.sell.bis.config.dao.SysMappingMapper;
import com.sell.bis.config.vo.AccessSystem;
import com.sell.bis.config.vo.SysMapping;

/**
 * 系统配置类
 * 
 * @author lilin
 * @version [版本号, 2015年7月23日]
 */
@Repository("config")
public class BisConfig {
    
    /**
     * 接入系统Mapper
     */
    @Resource
    private AccessSystemMapper accessSystemMapper;
    
    /**
     * 系统映射信息Mapper
     */
    @Resource
    private SysMappingMapper sysMappingMapper;
    
    /**
     * 接入系统map集合
     */
    private Map<String, AccessSystem> accessSysMap;
    
    /**
     * 接入系统映射map集合
     */
    private Map<String, String> sysMappingMap;
    
    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        List<AccessSystem> accessSystemList = accessSystemMapper.findAllAccessSystem();
        accessSysMap = new HashMap<String, AccessSystem>();
        for (AccessSystem accessSystem : accessSystemList) {
            accessSysMap.put(accessSystem.getAccessCode(), accessSystem);
        }
        
        List<SysMapping> sysMappingList = sysMappingMapper.findAllSysMapping();
        sysMappingMap = new HashMap<String, String>();
        
        for (SysMapping sysMapping : sysMappingList) {
            sysMappingMap.put(sysMapping.getAccessCode() + sysMapping.getServiceCode(), sysMapping.getNotifyUrl());
        }
    }
    
    public Map<String, AccessSystem> getAccessSysMap() {
        return accessSysMap;
    }

    public Map<String, String> getSysMappingMap() {
        return sysMappingMap;
    }
}
