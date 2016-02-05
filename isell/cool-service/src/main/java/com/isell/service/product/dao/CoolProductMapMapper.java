package com.isell.service.product.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.product.vo.CoolProductMap;

import org.apache.ibatis.annotations.Param;

/**
 * 商品映射表Mapper
 * 
 * @author lilin
 * @version [版本号, 2016年1月14日]
 */
@Mapper
public interface CoolProductMapMapper {
    /**
     * 根据主键查询
     */
    CoolProductMap getCoolProductMapById(@Param("id") Integer id);
    
    /**
     * 根据查询条件查询商品映射
     *
     * @param query 查询条件
     * @return 商品映射信息
     */
    CoolProductMap getCoolProductMap(CoolProductMap query);
    
    /**
     * 查询出所有记录
     */
    List<CoolProductMap> findAllCoolProductMap();
    
    /**
     * 保存
     */
    int saveCoolProductMap(CoolProductMap coolProductMap);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    int updateCoolProductMap(CoolProductMap coolProductMap);
    
    /**
     * 根据主键删除
     */
    int deleteCoolProductMap(@Param("id") Integer id);
}
