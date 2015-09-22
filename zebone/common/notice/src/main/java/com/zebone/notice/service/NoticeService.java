package com.zebone.notice.service;

/**
 * 
 * 通知服务接口
 * 
 * @author lilin
 * @version [版本号, 2015年5月11日]
 */
public interface NoticeService {
    /**
     * 更新失效的公告通知，供后台定时任务使用
     */
    void updateStatus();
}
