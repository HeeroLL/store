package com.star.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置文件
 * 
 * @author lilin
 * @version [版本号, 2016年4月13日]
 */
@Component
public class ConfigInfo {
    /**
     * 时
     */
    @Value("${config.hour}")
    private Integer hour;
    
    /**
     * 分
     */
    @Value("${config.minute}")
    private Integer minute;
    
    /**
     * 秒
     */
    @Value("${config.second}")
    private Integer second;
    
    /**
     * 开始日期
     */
    @Value("${config.begindate}")
    private String beginDate;
    
    /**
     * 结束日期
     */
    @Value("${config.enddate}")
    private String endDate;

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
