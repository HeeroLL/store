package com.isell.service.product.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.product.vo.CoolProductFilling;

import org.apache.ibatis.annotations.Param;

/**
 * 海关备案表mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-04] 
 */
@Mapper
public interface CoolProductFillingMapper{   
    /**
     * 根据主键查询
     */
    public CoolProductFilling getCoolProductFillingById(@Param("id")Integer id); 

    /**
     * 查询出所有记录
     */
    public List<CoolProductFilling> findAllCoolProductFilling();    
    
    /**
     * 保存
     */
    public int saveCoolProductFilling(CoolProductFilling coolProductFilling);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoolProductFilling(CoolProductFilling coolProductFilling);
    
    /**
     * 根据主键删除
     */
    public int deleteCoolProductFilling(@Param("id")Integer id);
}

