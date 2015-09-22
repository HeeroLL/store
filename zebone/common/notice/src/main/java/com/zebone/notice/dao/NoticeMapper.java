package com.zebone.notice.dao;

import java.util.List;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.notice.vo.Notice;

/**
 * 
 * 通知Mapper
 * 
 * @author lilin
 * @version [版本号, 2015年5月8日]
 */
@Mapper
public interface NoticeMapper {
    /**
     * 根据主键查询通知信息
     * 
     * @param nId 通知主键
     * @return 通知信息
     */
    Notice getNoticeById(String nId);
    
    /**
     * 根据人员id查询所有通知信息记录
     * 
     * @param personId 人员id 
     * @return 所有有效的通知信息记录
     */
    List<Notice> findAllNoticeByPersonId(String personId);
    
    /**
     * 保存通知信息
     * 
     * @param notice 通知信息
     * @return 保存成功的条数
     */
    int saveNotice(Notice notice);
    
    /**
     * 根据主键更新通知信息
     * 
     * @param notice 通知信息
     * @return 更新成功的条数
     */
    int updateNotice(Notice notice);
    
    /**
     * 根据主键删除通知信息
     * 
     * @param nId 通知信息主键
     * @return 删除成功的条数
     */
    int deleteNotice(String nId);
    
    /**
     * 更新失效的公告通知，供后台定时任务使用
     */
    void updateStatus();
}
