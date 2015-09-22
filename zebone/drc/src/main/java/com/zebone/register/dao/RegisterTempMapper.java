package com.zebone.register.dao;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.register.vo.RegisterTemp;

import java.util.Map;

/**
 * 注册系统主索引匹配失败记录 DAO
 *
 * @author 杨英
 * @version 2013-10-22 下午03:18
 */
@Mapper
public interface RegisterTempMapper {

    /**
     * 插入注册系统主索引匹配失败记录
     * @param registerTemp
     * @return
     */
    int insert(RegisterTemp registerTemp);

    /**
     * 查询注册系统主索引匹配失败记录
     * @param oMap
     * @return
     */
    RegisterTemp getRegisterTempInfo (Map<String,String> oMap);
}
