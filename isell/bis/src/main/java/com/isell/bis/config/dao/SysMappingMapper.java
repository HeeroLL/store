package com.isell.bis.config.dao;

import java.util.List;

import com.isell.bis.config.vo.SysMapping;
import com.isell.core.mybatis.Mapper;

import org.apache.ibatis.annotations.Param;

/**
 * 系统映射信息Mapper
 * 
 * @author lilin
 * @version [版本号, 2015年7月24日]
 */
@Mapper
public interface SysMappingMapper {
    /**
     * 根据主键查询
     */
    SysMapping getSysMappingById(@Param("id") Integer id);
    
    /**
     * 查询出所有记录
     */
    List<SysMapping> findAllSysMapping();
    
    /**
     * 保存
     */
    int saveSysMapping(SysMapping sysMapping);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    int updateSysMapping(SysMapping sysMapping);
    
    /**
     * 根据主键删除
     */
    int deleteSysMapping(@Param("id") Integer id);
}
