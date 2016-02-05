package com.isell.service.code.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.code.vo.CoolConfig;

import org.apache.ibatis.annotations.Param;

/**
 * TODO
 * @author 
 */
@Mapper
public interface CoolConfigMapper{   
    /**
     * 根据主键查询
     */
    public CoolConfig getCoolConfigById(@Param("id")Integer id); 

    /**
     * 查询出所有记录
     */
    public List<CoolConfig> findAllCoolConfig();    
    
    /**
     * 保存
     */
    public int saveCoolConfig(CoolConfig coolConfig);
    
    /**
     * 
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoolConfig(CoolConfig coolConfig);
    
    /**
     * 根据主键删除
     */
    public int deleteCoolConfig(@Param("id")Integer id);
    
   /**
    * 根据等级取相应的百分比
    * @param devide
    * @return
    */
	public CoolConfig getPercentageByDevide(@Param("devide")String devide);
}

