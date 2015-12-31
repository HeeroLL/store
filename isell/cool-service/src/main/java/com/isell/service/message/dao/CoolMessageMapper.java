package com.isell.service.message.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.isell.core.mybatis.Mapper;
import com.isell.service.message.vo.CoolMessage;

/**
 * 消息mapper
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-12] 
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
     * 根据查询条件查询
     */
    public List<CoolMessage> findCoolMessageList(CoolMessage coolMessage);   
    
    /**
     * 分页查询
     */
    public List<CoolMessage> findCoolMessageListPage(RowBounds rowBounds, CoolMessage coolMessage);   
    
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

