package com.isell.service.message.vo;

import java.util.Date;

import com.isell.core.mybatis.page.PageConfig;

/**
 * 
 * 消息表VO
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-20]
 *
 */
public class CoolMessage extends PageConfig{
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
    private String belongId;
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
    
    private String role;
    
    private String title;

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
    public String getBelongId() {
		return belongId;
	}

	public void setBelongId(String belongId) {
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}    
}