package com.isell.service.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isell.core.mybatis.Mapper;
import com.isell.service.product.vo.CoolProductGroup;

/**
 * 商品组Mapper
 * 
 * @author lilin
 * @version [版本号, 2015年12月25日]
 */
@Mapper
public interface CoolProductGroupMapper {
    /**
     * 根据主键查询
     */
    CoolProductGroup getCoolProductGroupById(@Param("id") Integer id);
    
    /**
     * 根据组id查询商品列表
     *
     * @param groupId 组id
     * @return 商品列表
     */
    List<CoolProductGroup> findCoolProductGroupByGroupId(String groupId);
    
    /**
     * 保存
     */
    int saveCoolProductGroup(CoolProductGroup coolProductGroup);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    int updateCoolProductGroup(CoolProductGroup coolProductGroup);
    
    /**
     * 根据主键删除
     */
    int deleteCoolProductGroup(@Param("id") Integer id);
}
