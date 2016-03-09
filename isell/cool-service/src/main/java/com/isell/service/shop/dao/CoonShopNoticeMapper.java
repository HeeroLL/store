package com.isell.service.shop.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.shop.vo.CoonShopNotice;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

/**
 * 酷店公告表Mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-04] 
 */
@Mapper
public interface CoonShopNoticeMapper{   
    /**
     * 根据主键查询
     */
    public CoonShopNotice getCoonShopNoticeById(@Param("id")Integer id); 

    /**
     * 查询出所有记录
     */
    public List<CoonShopNotice> findAllCoonShopNotice();    
    
    /**
     * 分页查询公告列表
     */
    public List<CoonShopNotice> findCoonShopNoticePage(RowBounds rowBounds,CoonShopNotice coonShopNotice);    
    
    /**
     * 保存
     */
    public int saveCoonShopNotice(CoonShopNotice coonShopNotice);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoonShopNotice(CoonShopNotice coonShopNotice);
    
    /**
     * 根据主键删除
     */
    public int deleteCoonShopNotice(@Param("id")Integer id);
}

