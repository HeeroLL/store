package com.isell.service.shop.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.shop.vo.CoonBanner;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

/**
 * 系统海报表mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-23] 
 */
@Mapper
public interface CoonBannerMapper{   
    /**
     * 根据主键查询
     */
    public CoonBanner getCoonBannerById(@Param("id")String id); 

    /**
     * 查询出所有记录
     */
    public List<CoonBanner> findAllCoonBanner();    
    
    /**
     * 分页查询不属于本店铺的海报
     */
    public List<CoonBanner> findCoonBannerListPage(RowBounds rowBounds,String sId);  
    
    /**
     * 保存
     */
    public int saveCoonBanner(CoonBanner coonBanner);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoonBanner(CoonBanner coonBanner);
    
    /**
     * 根据主键删除
     */
    public int deleteCoonBanner(@Param("id")String id);
}

