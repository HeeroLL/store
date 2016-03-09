package com.isell.service.order.dao;

import java.util.List;
import com.isell.service.order.vo.CoolProductCustomsHz;
import org.apache.ibatis.annotations.Param;

/**
 * TODO
 * @author 
 */
public interface CoolProductCustomsHzMapper{   
    /**
     * 根据主键查询
     */
    public CoolProductCustomsHz getCoolProductCustomsHzById(@Param("id")Integer id); 

    /**
     * 查询出所有记录
     */
    public List<CoolProductCustomsHz> findAllCoolProductCustomsHz();    
    
    /**
     * 保存
     */
    public int saveCoolProductCustomsHz(CoolProductCustomsHz coolProductCustomsHz);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoolProductCustomsHz(CoolProductCustomsHz coolProductCustomsHz);
    
    /**
     * 根据主键删除
     */
    public int deleteCoolProductCustomsHz(@Param("id")Integer id);
}

