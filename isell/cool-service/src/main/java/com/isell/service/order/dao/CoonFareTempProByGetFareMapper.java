package com.isell.service.order.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.order.vo.CoonFareTempProByGetFare;

import org.apache.ibatis.annotations.Param;

/**
 * TODO
 * 
 * @author
 */
@Mapper
public interface CoonFareTempProByGetFareMapper {
    /**
     * 根据主键查询
     */
    public CoonFareTempProByGetFare getCoonFareTempProById(@Param("id") String id);
    
    /**
     * 查询出所有记录
     */
    public List<CoonFareTempProByGetFare> findAllCoonFareTempPro();
    
    /**
     * 保存
     */
    public int saveCoonFareTempPro(CoonFareTempProByGetFare coonFareTempPro);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoonFareTempPro(CoonFareTempProByGetFare coonFareTempPro);
    
    /**
     * 根据主键删除
     */
    public int deleteCoonFareTempPro(@Param("id") String id);
    
    /**
     * 根据发货地与收货地获取运费
     */
    public CoonFareTempProByGetFare getCoonFareTempProByGetFare(@Param("startAddress") String startAddress,
        @Param("province") String province);
}
