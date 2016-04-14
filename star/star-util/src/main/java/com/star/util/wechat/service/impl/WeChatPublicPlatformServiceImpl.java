package com.star.util.wechat.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.star.util.MyColumnMapRowMapper;
import com.star.util.wechat.service.WeChatPublicPlatformService;

/**
 * 
 * 微信公众平台服务接口实现类
 * 
 * @author lilin
 * @version [版本号, 2016年3月31日]
 */
@Service
public class WeChatPublicPlatformServiceImpl implements WeChatPublicPlatformService {
    /**
     * jdbcTemplate
     */
    @Resource
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Map<String, Object>> getSiteInfoListByTime(String beginTime, String endTime) {
        String sql =
            "SELECT *, FROM_UNIXTIME(instime, '%Y年%m月%d日 %T') AS 日期 FROM sc_site_user WHERE instime > UNIX_TIMESTAMP(?) AND instime <= UNIX_TIMESTAMP(?)";
        return jdbcTemplate.query(sql, new MyColumnMapRowMapper(), beginTime, endTime);
    }
    
    @Override
    public List<Map<String, Object>> getAgentInfoListByTime(String beginTime, String endTime) {
        String sql =
            "SELECT *, FROM_UNIXTIME(instime, '%Y年%m月%d日 %T') AS 日期 FROM sc_agent_user WHERE instime > UNIX_TIMESTAMP(?) AND instime <= UNIX_TIMESTAMP(?)";
        return jdbcTemplate.query(sql, new MyColumnMapRowMapper(), beginTime, endTime);
    }
    
}
