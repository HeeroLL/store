package com.isell.service.custorms.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.custorms.po.CoolProductCustomsZz;

import org.apache.ibatis.annotations.Param;

/**
 * 郑州商品海关备案Mapper
 * 
 * @author lilin
 * @version [版本号, 2015年12月10日]
 */
@Mapper
public interface CoolProductCustomsZzMapper {
    /**
     * 根据主键查询
     */
    CoolProductCustomsZz getCoolProductCustomsZzById(@Param("id") Integer id);
    
    /**
     * 通过规格id获取商品备案信息
     *
     * @param gid 规格id
     * @return 商品备案信息
     */
    CoolProductCustomsZz getCustomsInfoByGid(Integer gid);
    
    /**
     * 查询出所有记录
     */
    List<CoolProductCustomsZz> findAllCoolProductCustomsZz();
    
    /**
     * 保存
     */
    int saveCoolProductCustomsZz(CoolProductCustomsZz coolProductCustomsZz);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    int updateCoolProductCustomsZz(CoolProductCustomsZz coolProductCustomsZz);
    
    /**
     * 根据主键删除
     */
    int deleteCoolProductCustomsZz(@Param("id") Integer id);
}
