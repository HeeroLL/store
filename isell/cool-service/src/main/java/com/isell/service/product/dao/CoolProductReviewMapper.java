package com.isell.service.product.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.product.po.CoolProductReviewInfo;
import com.isell.service.product.vo.CoolProductReview;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

/**
 * 商品评价表mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-17] 
 */
@Mapper
public interface CoolProductReviewMapper{   
    /**
     * 根据主键查询
     */
    public CoolProductReview getCoolProductReviewById(@Param("id")Integer id); 

    /**
     * 查询出所有记录
     */
    public List<CoolProductReview> findAllCoolProductReview(); 

    /**
     * 分页查询商品评价信息
     * 
     * @param rowBounds
     * @param coolProductReview
     * @return
     */
    public List<CoolProductReviewInfo> getCoolProductReviewInfoListPage(RowBounds rowBounds,CoolProductReview coolProductReview);
    
    /**
     * 根据查询条件查询评论数量
     * 
     * @param coolProductReview
     * @return 评论数量
     */
    public int getCountReview(CoolProductReview coolProductReview);
    
    /**
     * 保存
     */
    public int saveCoolProductReview(CoolProductReview coolProductReview);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoolProductReview(CoolProductReview coolProductReview);
    
    /**
     * 根据主键删除
     */
    public int deleteCoolProductReview(@Param("id")Integer id);
}

