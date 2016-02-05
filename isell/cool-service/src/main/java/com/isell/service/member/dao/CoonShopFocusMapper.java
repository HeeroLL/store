package com.isell.service.member.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.member.vo.CoonShopFocus;

import org.apache.ibatis.annotations.Param;

/**
 * 酷店关注mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-28] 
 */
@Mapper
public interface CoonShopFocusMapper{   
    /**
     * 根据主键查询
     */
    public CoonShopFocus getCoonShopFocusById(@Param("id")Integer id); 
    
    /**
     * 根据会员主键查询
     */
    public CoonShopFocus getCoonShopFocusBymId(@Param("mId")Integer mId);  

    /**
     * 查询出所有记录
     */
    public List<CoonShopFocus> findAllCoonShopFocus();    
    
    /**
     * 保存
     */
    public int saveCoonShopFocus(CoonShopFocus coonShopFocus);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoonShopFocus(CoonShopFocus coonShopFocus);
    
    /**
     * 根据主键删除
     */
    public int deleteCoonShopFocus(@Param("id")Integer id);
}

