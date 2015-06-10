package com.zebone.notice.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.notice.vo.PublicNotice;

/**
 * 
 * 公告Mapper
 * 
 * @author lilin
 * @version [版本号, 2015年5月8日]
 */
@Mapper
public interface PublicNoticeMapper {
    /**
     * 根据主键查询公告信息
     * 
     * @param nId 公告主键
     * @return 公告信息
     */
    PublicNotice getPublicNoticeById(String nId);
    
    /**
     * 查询所有公告信息记录
     * 
     * @param rowBounds 分页对象
     * @param publicNotice 查询条件
     * @return 所有有效的公告信息记录
     */
    List<PublicNotice> findPublicNoticeList(RowBounds rowBounds, PublicNotice publicNotice);
    
    /**
     * 获得公告信息总数
     * 
     * @param publicNotice 查询条件
     * @return 公告总数
     */
    int getTotalCount(PublicNotice publicNotice);
    
    /**
     * 保存公告信息
     * 
     * @param publicNotice 公告信息
     * @return 保存成功的条数
     */
    int savePublicNotice(PublicNotice publicNotice);
    
    /**
     * 根据主键更新公告信息
     * 
     * @param publicNotice 公告信息
     * @return 更新成功的条数
     */
    int updatePublicNotice(PublicNotice publicNotice);
    
    /**
     * 根据主键删除公告信息
     * 
     * @param nId 公告信息主键
     * @return 删除成功的条数
     */
    int deletePublicNotice(String nId);
    
    /**
     * 更新失效的公告通知，供后台定时任务使用
     */
    void updateStatus();
}
