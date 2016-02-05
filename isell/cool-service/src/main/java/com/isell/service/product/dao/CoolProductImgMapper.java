package com.isell.service.product.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.product.vo.CoolProductImg;

import org.apache.ibatis.annotations.Param;

/**
 * 商品图片表mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-17] 
 */
@Mapper
public interface CoolProductImgMapper{   
    /**
     * 根据主键查询
     */
    public CoolProductImg getCoolProductImgById(@Param("id")Integer id); 

    /**
     * 查询出所有记录
     */
    public List<CoolProductImg> findAllCoolProductImg();    
    
    /**
     * 根据商品主键查询
     */
    public List<CoolProductImg> findCoolProductImgListByGoodsId(Integer goodsId);    
    
    /**
     * 保存
     */
    public int saveCoolProductImg(CoolProductImg coolProductImg);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoolProductImg(CoolProductImg coolProductImg);
    
    /**
     * 根据主键删除
     */
    public int deleteCoolProductImg(@Param("id")Integer id);
}

