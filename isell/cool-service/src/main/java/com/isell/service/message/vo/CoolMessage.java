package com.isell.service.message.vo;

import java.util.Date;

/**
 *
 * @author 
 */
public class CoolMessage{
    /**
     * 
     */
    private Integer id;
    /**
     * 
     */
    private Integer userId;
    /**
     * 1公告2物流3订单4商品5财富
     */
    private String type;
    /**
     * 
     */
    private Integer belongId;
    /**
     * 
     */
    private String content;
    /**
     * 
     */
    private Integer click;
    /**
     * 
     */
    private Date sendtime;
    /**
     * 
     */
    private Integer operatorId;

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
    public Integer getUserId(){
        return this.userId;
    }

    /**
     * 
     */
    public void setUserId(Integer userId){
        this.userId = userId;
    }    
    /**
     * 1公告2物流3订单4商品5财富
     */
    public String getType(){
        return this.type;
    }

    /**
     * 1公告2物流3订单4商品5财富
     */
    public void setType(String type){
        this.type = type;
    }    
    /**
     * 
     */
    public Integer getBelongId(){
        return this.belongId;
    }

    /**
     * 
     */
    public void setBelongId(Integer belongId){
        this.belongId = belongId;
    }    
    /**
     * 
     */
    public String getContent(){
        return this.content;
    }

    /**
     * 
     */
    public void setContent(String content){
        this.content = content;
    }    
    /**
     * 
     */
    public Integer getClick(){
        return this.click;
    }

    /**
     * 
     */
    public void setClick(Integer click){
        this.click = click;
    }    
    /**
     * 
     */
    public Date getSendtime(){
        return this.sendtime;
    }

    /**
     * 
     */
    public void setSendtime(Date sendtime){
        this.sendtime = sendtime;
    }    
    /**
     * 
     */
    public Integer getOperatorId(){
        return this.operatorId;
    }

    /**
     * 
     */
    public void setOperatorId(Integer operatorId){
        this.operatorId = operatorId;
    }    
}