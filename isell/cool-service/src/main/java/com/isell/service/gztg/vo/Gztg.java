package com.isell.service.gztg.vo;

import java.util.Date;

/**
 *
 * @author 
 */
public class Gztg{
    /**
     * 
     */
    private Integer id;
    /**
     * 
     */
    private String name;
    /**
     * 
     */
    private String messageid;
    /**
     * 
     */
    private String orderid;
    /**
     * 
     */
    private Date uploadtime;

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
     * 
     */
    public String getName(){
        return this.name;
    }

    /**
     * 
     */
    public void setName(String name){
        this.name = name;
    }    
    /**
     * 
     */
    public String getMessageid(){
        return this.messageid;
    }

    /**
     * 
     */
    public void setMessageid(String messageid){
        this.messageid = messageid;
    }    
    /**
     * 
     */
    public String getOrderid(){
        return this.orderid;
    }

    /**
     * 
     */
    public void setOrderid(String orderid){
        this.orderid = orderid;
    }    
    /**
     * 
     */
    public Date getUploadtime(){
        return this.uploadtime;
    }

    /**
     * 
     */
    public void setUploadtime(Date uploadtime){
        this.uploadtime = uploadtime;
    }    
}