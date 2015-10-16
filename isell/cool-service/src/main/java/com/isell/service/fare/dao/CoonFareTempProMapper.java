package com.isell.service.fare.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isell.core.mybatis.Mapper;
import com.isell.service.fare.vo.CoonFareTempPro;

/**
 * 快递模板明细mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-10-04] 
 */
@Mapper
public interface CoonFareTempProMapper{   
    /**
     * 根据主键查询
     */
    public CoonFareTempPro getCoonFareTempProById(@Param("id")String id); 

    /**
     * 查询出所有记录
     */
    public List<CoonFareTempPro> findAllCoonFareTempPro();    
    
    /**
     * 保存
     */
    public int saveCoonFareTempPro(CoonFareTempPro coonFareTempPro);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoonFareTempPro(CoonFareTempPro coonFareTempPro);
    
    /**
     * 根据主键删除
     */
    public int deleteCoonFareTempPro(@Param("id")String id);
    
    /**
     * 根据省份获取了、快递费用信息
     */
    public List<CoonFareTempPro> getCoonFareTempProByPro(@Param("province")String province);
}

