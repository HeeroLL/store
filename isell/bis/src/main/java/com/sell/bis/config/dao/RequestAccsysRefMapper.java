package com.sell.bis.config.dao;

import java.util.List;

import com.sell.bis.config.vo.RequestAccsysRef;
import com.sell.core.mybatis.Mapper;

import org.apache.ibatis.annotations.Param;

/**
 * 请求与接入系统关系Mapper
 * 
 * @author lilin
 * @version [版本号, 2015年7月24日]
 */
@Mapper
public interface RequestAccsysRefMapper {
    /**
     * 根据主键查询
     */
    RequestAccsysRef getRequestAccsysRefById(@Param("id") Integer id);
    
    /**
     * 根据请求标识查询接入系统信息
     *
     * @param requestId 请求标识
     * @return 接入系统信息
     */
    RequestAccsysRef getRequestAccsysRefByRequestId(String requestId);
    
    /**
     * 查询出所有记录
     */
    List<RequestAccsysRef> findAllRequestAccsysRef();
    
    /**
     * 保存
     */
    int saveRequestAccsysRef(RequestAccsysRef... requestAccsysRef);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    int updateRequestAccsysRef(RequestAccsysRef requestAccsysRef);
    
    /**
     * 根据主键删除
     */
    int deleteRequestAccsysRef(@Param("id") Integer id);
}
