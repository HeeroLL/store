package com.zebone.notice.vo;

import java.util.List;

/**
 * 
 * 通知信息vo
 * 
 * @author lilin
 * @version [版本号, 2015年5月8日]
 */
public class Notice extends NoticeBase {
    
    /**
     * 通知人员集合
     */
    private List<NoticePerson> noticePersonList;
    
    public List<NoticePerson> getNoticePersonList() {
        return noticePersonList;
    }
    
    public void setNoticePersonList(List<NoticePerson> noticePersonList) {
        this.noticePersonList = noticePersonList;
    }
}