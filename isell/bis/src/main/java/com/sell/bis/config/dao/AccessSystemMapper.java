package com.sell.bis.config.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sell.bis.config.vo.AccessSystem;
import com.sell.core.mybatis.Mapper;

/**
 * 接入系统Mapper
 * 
 * @author lilin
 * @version [版本号, 2015年7月24日]
 */
@Mapper
public interface AccessSystemMapper {
    /**
     * 根据主键查询
     */
    AccessSystem getAccessSystemById(Integer id);
    
    /**
     * 查询出所有记录
     */
    List<AccessSystem> findAllAccessSystem();    
    
    /**
     * 保存
     */
    int saveAccessSystem(AccessSystem accessSystem);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    int updateAccessSystem(AccessSystem accessSystem);
    
    /**
     * 根据主键删除
     */
    int deleteAccessSystem(@Param("id") Integer id);
}
