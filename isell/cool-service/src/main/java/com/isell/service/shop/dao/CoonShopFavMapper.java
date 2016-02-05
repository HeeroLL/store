package com.isell.service.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.isell.core.mybatis.Mapper;
import com.isell.service.shop.vo.CoonShopFav;

/**
 * 酷店收藏表mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-12] 
 */
@Mapper
public interface CoonShopFavMapper{   
    /**
     * 根据主键查询
     */
    public CoonShopFav getCoonShopFavById(@Param("id")String id); 

    /**
     * 查询出所有记录
     */
    public List<CoonShopFav> findAllCoonShopFav();    
    
    /**
     *  根据会员主键以及酷店主键查询
     */
    public List<CoonShopFav> findCoonShopFavByIdAndSId(@Param("mId")String mId, @Param("sId")String sId); 
    
    /**
     * 分页查询酷店收藏列表信息
     */
    public List<CoonShopFav> findCoonShopFavListPage(RowBounds rowBounds,CoonShopFav coonShopFav);  
    
    /**
     * 查询收藏的数量
     * @param coonShopFav
     * @return 数量
     */
    public int getCoonShopFavCount(CoonShopFav coonShopFav);  
    
    /**
     * 保存
     */
    public int saveCoonShopFav(CoonShopFav coonShopFav);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoonShopFav(CoonShopFav coonShopFav);
    
    /**
     * 根据主键删除
     */
    public int deleteCoonShopFav(@Param("id")String id);
}

