package com.isell.service.member.vo;

/**
 * 
 * 酷店关注表VO
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-28]
 *
 */
public class CoonShopFocus{
    /**
     * 
     */
    private Integer id;
    /**
     * 会员主键
     */
    private Integer mId;
    /**
     * 酷店主键
     */
    private String sId;
    
    /**
     * 类型 1：关注  0：取消关注
     */
    private String state;

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
     * 会员主键
     */
    public Integer getmId(){
        return this.mId;
    }

    /**
     * 会员主键
     */
    public void setmId(Integer mId){
        this.mId = mId;
    }    
    /**
     * 酷店主键
     */
    public String getsId(){
        return this.sId;
    }

    /**
     * 酷店主键
     */
    public void setsId(String sId){
        this.sId = sId;
    }

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}    
}