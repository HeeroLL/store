package com.star.util.wechat.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * 微信公众平台服务接口
 * 
 * @author lilin
 * @version [版本号, 2016年3月31日]
 */
public interface WeChatPublicPlatformService {
    /**
     * 根据开始时间和结束时间，获取微信公众号平台中“我有场地”部分的数据
     *
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return “我有场地”数据列表
     */
    List<Map<String, Object>> getSiteInfoListByTime(String beginTime, String endTime);
    
    /**
     * 根据开始时间和结束时间，获取微信公众号平台中“我要代理”部分的数据
     *
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return “我要代理”数据列表
     */
    List<Map<String, Object>> getAgentInfoListByTime(String beginTime, String endTime);
}
