package com.isell.service.product.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.product.vo.CoolProductCategory;

import org.apache.ibatis.annotations.Param;

/**
 * 商品分类表mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-17] 
 */
@Mapper
public interface CoolProductCategoryMapper{   
    /**
     * 根据主键查询
     */
    public CoolProductCategory getCoolProductCategoryById(@Param("id")Integer id); 

    /**
     * 查询出所有记录
     */
    public List<CoolProductCategory> findAllCoolProductCategory();    
    
    /**
     * 保存
     */
    public int saveCoolProductCategory(CoolProductCategory coolProductCategory);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoolProductCategory(CoolProductCategory coolProductCategory);
    
    /**
     * 根据主键删除
     */
    public int deleteCoolProductCategory(@Param("id")Integer id);
}

