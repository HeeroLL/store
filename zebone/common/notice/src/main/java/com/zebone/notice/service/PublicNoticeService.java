package com.zebone.notice.service;

import com.zebone.btp.core.mybatis.page.PageConfig;
import com.zebone.btp.core.mybatis.page.PageInfo;
import com.zebone.notice.vo.PublicNotice;

/**
 * 
 * 公告服务接口
 * 
 * @author lilin
 * @version [版本号, 2015年5月11日]
 */
public interface PublicNoticeService {
    /**
     * 保存或修改公告信息
     * 
     * @param publicNotice 公告信息
     */
    void saveOrUpdatePublicNotice(PublicNotice publicNotice);
    
    /**
     * 删除公告信息
     * 
     * @param nId 公告id
     */
    void deletePublicNotice(String nId);
    
    /**
     * 根据主键查询公告信息
     * 
     * @param nId 公告主键
     * @return 公告信息
     */
    PublicNotice getPublicNoticeById(String nId);
    
    /**
     * 查询公告信息
     * 
     * @param page 分页信息
     * @param publicNotice 查询条件
     * @return 分页后的公告信息
     */
    PageInfo<PublicNotice> findPublicNoticeList(PageConfig page, PublicNotice publicNotice);
}
