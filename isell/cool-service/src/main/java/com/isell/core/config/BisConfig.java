package com.isell.core.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.isell.core.config.dao.AccessSystemMapper;
import com.isell.core.config.dao.SysMappingMapper;
import com.isell.core.config.vo.AccessSystem;
import com.isell.core.config.vo.SysMapping;

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
     * 本地图片路径
     */
    @Value("${img_local}")
    private String imgLocal;
    
    /**
     * 基础域名
     */
    @Value("${base_domain}")
    private String baseDomain;
    
    /**
     * web网站域名
     */
    @Value("${domain}")
    private String domain;
    
    /**
     * wap网站域名
     */
    @Value("${wap_domain}")
    private String wapDomain;
    
    /**
     * 图片域名
     */
    @Value("${img_domain}")
    private String imgDomain;
    
    /**
     * 默认酷店id
     */
    @Value("${default_shopId}")
    private String defaultShopId;
    
    /**
     * 图片上线
     */
    @Value("${img_maxSize}")
    private long imgMaxSize;
    
    /**
     * 用户信息缓存时间
     */
    @Value("${user_cache_time}")
    private long userCacheTime;
    
    /**
     * 短信发送接口
     */
    @Value("${send_sms_url}")
    private String sendSmsUrl;
    
    /**
     * 服务接口
     */
    @Value("${service_domain}")
    private String serviceDomain;
    
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

    public String getBaseDomain() {
        return baseDomain;
    }

    public String getDomain() {
        return domain;
    }

    public String getWapDomain() {
        return wapDomain;
    }

    public String getImgDomain() {
        return imgDomain;
    }

    public String getDefaultShopId() {
        return defaultShopId;
    }

    public String getImgLocal() {
        return imgLocal;
    }

    public long getUserCacheTime() {
        return userCacheTime;
    }

	public String getSendSmsUrl() {
		return sendSmsUrl;
	}

	public long getImgMaxSize() {
		return imgMaxSize;
	}

	public String getServiceDomain() {
		return serviceDomain;
	}

}
