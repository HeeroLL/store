package com.zebone.register.analyze.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zebone.register.analyze.vo.EmpiRegLog;

/**
 * TODO
 * @author 
 */
public interface EmpiRegLogMapper{   
    /**
     * 根据主键查询
     */
    public EmpiRegLog getEmpiRegLogById(@Param("id")String id); 

    /**
     * 查询出所有记录
     */
    public List<EmpiRegLog> findAllEmpiRegLog();    
    
    /**
     * 保存
     */
    public int saveEmpiRegLog(EmpiRegLog empiRegLog);
    
    /**
     * 根据主键更新（参数对象中的主键将作为更新条件）
     */
    public int updateEmpiRegLog(EmpiRegLog empiRegLog);
    
    /**
     * 根据主键删除
     */
    public int deleteEmpiRegLog(@Param("id")String id);
}

