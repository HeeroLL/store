package com.isell.service.gztg.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.gztg.vo.Gztg;

import org.apache.ibatis.annotations.Param;

/**
 * TODO
 * @author 
 */
@Mapper
public interface GztgMapper{   
    /**
     * 根据主键查询
     */
    public Gztg getGztgById(@Param("id")Integer id); 

    /**
     * 查询出所有记录
     */
    public List<Gztg> findAllGztg();    
    
    /**
     * 保存
     */
    public int saveGztg(Gztg gztg);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateGztg(Gztg gztg);
    
    /**
     * 根据主键删除
     */
    public int deleteGztg(@Param("id")Integer id);
    /**
     * 根据messageId 查找
     * @return 
     */
    public Gztg getByMessageId(@Param("messageId")String messageId);
}

