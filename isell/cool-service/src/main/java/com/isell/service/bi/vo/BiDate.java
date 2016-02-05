package com.isell.service.bi.vo;

/**
 * 日期表VO
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-09]
 */
public class BiDate{
    /**
     * 
     */
    private Integer id;
    /**
     * 日期，格式：yyyy-MM-dd
     */
    private String date;
    /**
     * 年，格式：yyyy
     */
    private String year;
    /**
     * 季度，格式：第x季度
     */
    private String quarter;
    /**
     * 月，格式：MM （两位，不足的补0）
     */
    private String month;
    /**
     * 日，格式：dd（两位，不足的补0）
     */
    private String day;
    /**
     * 星期日，格式：周x
     */
    private String week;
    /**
     * 时间到秒
     */
    private String longtime;

    /**
     * 
     */
    public Integer getId(){
        return this.id;
    }

    /**
     * 
     */
    public void setId(Integer id){
        this.id = id;
    }    
    /**
     * 日期，格式：yyyy-MM-dd
     */
    public String getDate(){
        return this.date;
    }

    /**
     * 日期，格式：yyyy-MM-dd
     */
    public void setDate(String date){
        this.date = date;
    }    
    /**
     * 年，格式：yyyy
     */
    public String getYear(){
        return this.year;
    }

    /**
     * 年，格式：yyyy
     */
    public void setYear(String year){
        this.year = year;
    }    
    /**
     * 季度，格式：第x季度
     */
    public String getQuarter(){
        return this.quarter;
    }

    /**
     * 季度，格式：第x季度
     */
    public void setQuarter(String quarter){
        this.quarter = quarter;
    }    
    /**
     * 月，格式：MM （两位，不足的补0）
     */
    public String getMonth(){
        return this.month;
    }

    /**
     * 月，格式：MM （两位，不足的补0）
     */
    public void setMonth(String month){
        this.month = month;
    }    
    /**
     * 日，格式：dd（两位，不足的补0）
     */
    public String getDay(){
        return this.day;
    }

    /**
     * 日，格式：dd（两位，不足的补0）
     */
    public void setDay(String day){
        this.day = day;
    }    
    /**
     * 星期日，格式：周x
     */
    public String getWeek(){
        return this.week;
    }

    /**
     * 星期日，格式：周x
     */
    public void setWeek(String week){
        this.week = week;
    }    
    /**
     * 时间到秒
     */
    public String getLongtime(){
        return this.longtime;
    }

    /**
     * 时间到秒
     */
    public void setLongtime(String longtime){
        this.longtime = longtime;
    }    
}