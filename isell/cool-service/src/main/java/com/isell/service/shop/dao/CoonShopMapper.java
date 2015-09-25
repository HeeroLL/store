package com.isell.service.shop.dao;

import org.apache.ibatis.annotations.Param;

import com.isell.core.mybatis.Mapper;
import com.isell.service.shop.vo.CoonShop;

/**
 * 酷店表Mapper
 * 
 * @author lilin
 * @version [版本号, 2015年9月25日]
 */
@Mapper
public interface CoonShopMapper{   
    /**
     * 根据主键查询酷店信息
     *
     * @param id 主键
     * @return 酷店信息
     */
    CoonShop getCoonShopById(@Param("id")String id); 
    
    /**
     * 保存酷店信息
     *
     * @param coonShop 酷店信息
     * @return 成功保存的条数
     */
    int saveCoonShop(CoonShop coonShop);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     *
     * @param coonShop 酷店信息
     * @return 成功修改的条数
     */
    int updateCoonShop(CoonShop coonShop);
    
    /**
     * 根据主键删除
     *
     * @param id 主键id
     * @return 成功删除的条数
     */
    int deleteCoonShop(@Param("id")String id);
}

