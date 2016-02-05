package com.isell.service.shop.vo;

import java.util.Date;

/**
 * 酷店访问表vo
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-09]
 */
public class CoonShopClick{
    /**
     * 
     */
    private Integer id;
    /**
     * 酷店主键
     */
    private String sid;
    /**
     * 日期，格式：yyyy-MM-dd
     */
    private String ip;
    /**
     * url
     */
    private String url;
    /**
     * 创建时间
     */
    private Date createtime;

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
     * 酷店主键
     */
    public String getSid(){
        return this.sid;
    }

    /**
     * 酷店主键
     */
    public void setSid(String sid){
        this.sid = sid;
    }    
    /**
     * 日期，格式：yyyy-MM-dd
     */
    public String getIp(){
        return this.ip;
    }

    /**
     * 日期，格式：yyyy-MM-dd
     */
    public void setIp(String ip){
        this.ip = ip;
    }    
    /**
     * url
     */
    public String getUrl(){
        return this.url;
    }

    /**
     * url
     */
    public void setUrl(String url){
        this.url = url;
    }    
    /**
     * 创建时间
     */
    public Date getCreatetime(){
        return this.createtime;
    }

    /**
     * 创建时间
     */
    public void setCreatetime(Date createtime){
        this.createtime = createtime;
    }    
}