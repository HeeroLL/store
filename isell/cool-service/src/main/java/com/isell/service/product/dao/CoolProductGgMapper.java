package com.isell.service.product.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.product.vo.CoolProductGg;

import org.apache.ibatis.annotations.Param;

/**
 * 商品规格mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-04] 
 */
@Mapper
public interface CoolProductGgMapper{   
    /**
     * 根据主键查询
     */
    public CoolProductGg getCoolProductGgById(@Param("id")Integer id); 

    /**
     * 查询出所有记录
     */
    public List<CoolProductGg> findAllCoolProductGg();    
    
    /**
     * 保存
     */
    public int saveCoolProductGg(CoolProductGg coolProductGg);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoolProductGg(CoolProductGg coolProductGg);
    
    /**
     * 根据主键删除
     */
    public int deleteCoolProductGg(@Param("id")Integer id);
}

