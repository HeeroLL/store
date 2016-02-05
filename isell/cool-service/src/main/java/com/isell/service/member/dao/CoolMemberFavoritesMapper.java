package com.isell.service.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isell.core.mybatis.Mapper;
import com.isell.service.member.vo.CoolMemberFavorites;

/**
 * 
 * 收藏商品mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-04]
 *
 */
@Mapper
public interface CoolMemberFavoritesMapper{   
    /**
     * 根据主键查询
     */
    public CoolMemberFavorites getCoolMemberFavoritesById(@Param("id")Integer id); 

    /**
     * 查询出所有记录
     */
    public List<CoolMemberFavorites> findAllCoolMemberFavorites();    
    
    /**
     * 根据会员主键查询
     */
    public List<CoolMemberFavorites> findCoolMemberFavoritesListBymId(@Param("mId")Integer mId);  
    
    /**
     * 根据会员主键和商品主键查询
     */
    public CoolMemberFavorites findCoolMemberFavoritesListBymIdAndpId(@Param("mId")Integer mId, @Param("pId")Integer pId);  
    
    /**
     * 保存
     */
    public int saveCoolMemberFavorites(CoolMemberFavorites coolMemberFavorites);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoolMemberFavorites(CoolMemberFavorites coolMemberFavorites);
    
    /**
     * 根据主键删除
     */
    public int deleteCoolMemberFavorites(@Param("id")Integer id);
}

