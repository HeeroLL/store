package com.isell.service.message.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.isell.core.mybatis.Mapper;
import com.isell.service.message.vo.CoolMessage;

/**
 * TODO
 * @author 
 */
@Mapper
public interface CoolMessageMapper{   
    /**
     * 根据主键查询
     */
    public CoolMessage getCoolMessageById(@Param("id")Integer id); 

    /**
     * 查询出所有记录
     */
    public List<CoolMessage> findAllCoolMessage();    
    
    /**
     * 保存
     */
    public int saveCoolMessage(CoolMessage coolMessage);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateCoolMessage(CoolMessage coolMessage);
    
    /**
     * 根据主键删除
     */
    public int deleteCoolMessage(@Param("id")Integer id);
}

