package com.zebone.notice.dao;

import java.util.List;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.notice.vo.NoticePerson;

/**
 * 
 * 通知人员Mapper
 * 
 * @author lilin
 * @version [版本号, 2015年5月8日]
 */
@Mapper
public interface NoticePersonMapper{   

    /**
     * 根据通知id获取通知人列表
     *
     * @param nId 通知id
     * @return 通知人列表
     */
    List<NoticePerson> findNoticePersonByNId(String nId);    
    
    /**
     * 保存通知人员信息
     *
     * @param noticePerson 通知人员信息
     * @return 保存成功的条数
     */
    int saveNoticePerson(NoticePerson noticePerson);
    
    /**
     * 根据通知id批量删除通知人员信息
     *
     * @param nId 通知id
     * @return 删除成功的条数
     */
    int deleteNoticePerson(String nId);
}

