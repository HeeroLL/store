package com.zebone.empi.oneCard;

import java.util.Date;

/**
 * 推送失败日志对象
 *
 * @author 杨英
 * @version 2014-6-12 下午3:42
 */
public class PullFailLog {

    private String id;
    //推送消息类型 0 卡注销  1 卡等变更
    private String pullType;
    //推送内容
    private String pullContent;
    private Date createTime;
    //推送成功标志位  0 未推送  1 推送成功
    private String pullStatus;
    //推送队列名称
    private String pullQueue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPullType() {
        return pullType;
    }

    public void setPullType(String pullType) {
        this.pullType = pullType;
    }

    public String getPullContent() {
        return pullContent;
    }

    public void setPullContent(String pullContent) {
        this.pullContent = pullContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPullStatus() {
        return pullStatus;
    }

    public void setPullStatus(String pullStatus) {
        this.pullStatus = pullStatus;
    }

    public String getPullQueue() {
        return pullQueue;
    }

    public void setPullQueue(String pullQueue) {
        this.pullQueue = pullQueue;
    }
}
