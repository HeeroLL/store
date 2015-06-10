package com.zebone.notice.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zebone.notice.dao.NoticeMapper;
import com.zebone.notice.dao.PublicNoticeMapper;
import com.zebone.notice.service.NoticeService;

/**
 * 
 * 通知服务接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年5月25日]
 */
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
    
    /**
     * 公告mapper
     */
    @Resource
    private PublicNoticeMapper publicNoticeMapper;
    
    /**
     * 通知mapper
     */
    @Resource
    private NoticeMapper noticeMapper;
    
    /**
     * 更新失效的公告通知，供后台定时任务使用
     */
    @Override
    public void updateStatus() {
        publicNoticeMapper.updateStatus();
        noticeMapper.updateStatus();
    }
    
}
