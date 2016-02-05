package com.isell.service.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.isell.core.mybatis.Mapper;
import com.isell.service.order.po.CoolDistributionCarInfo;
import com.isell.service.order.vo.CoolDistributionCar;

/**
 * 一件代发进货单表Mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-16]
 */
@Mapper
public interface CoolDistributionCarMapper{   
    /**
     * 根据主键查询
     */
    public CoolDistributionCar getCoolDistributionCarById(@Param("id")Integer id); 
    
    /**
     * 根据查询条件查询
     */
    public List<CoolDistributionCar> getCoolDistributionCarList(CoolDistributionCar coolDistributionCar); 

    /**
     * 查询出所有记录
     */
    public List<CoolDistributionCar> findAllCoolDistributionCar();    
    
    /**
     * 分页查询进货单列表信息
     */
    public List<CoolDistributionCarInfo> findCoolDistributionCarInfoListPage(RowBounds rowBounds,CoolDistributionCar coolDistributionCar);  
    
    /**
     * 保存
     */
    public int saveCoolDistributionCar(CoolDistributionCar coolDistributionCar);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoolDistributionCar(CoolDistributionCar coolDistributionCar);
    
    /**
     * 根据主键删除
     */
    public int deleteCoolDistributionCar(@Param("id")Integer id);
}

