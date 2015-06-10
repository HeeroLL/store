package com.zebone.register.dao;

import com.zebone.btp.core.mybatis.Mapper;

import java.util.Map;

/**
 * 维护WEB服务地址DAO
 *
 * @author 杨英
 * @version 2013-10-24 下午02:51
 */
@Mapper
public interface StoreServiceMapper {

    /**
     * 根据服务类型和系统编码获取WEB服务地址
     * @param oMap
     * @return
     */
    String getWebUrl( Map<String,String> oMap);
}
