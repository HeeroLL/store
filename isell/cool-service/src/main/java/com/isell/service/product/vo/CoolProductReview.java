package com.isell.service.product.vo;

import java.util.Date;

import com.isell.core.mybatis.page.PageConfig;

/**
 * 
 * 商品评价表VO
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-17]
 */
public class CoolProductReview extends PageConfig{
    /**
     * 
     */
    private Integer id;
    /**
     * 会员主键
     */
    private Integer mId;
    /**
     * 
     */
    private String content;
    /**
     * 综合评分
     */
    private Integer score;
    /**
     * 商品主键
     */
    private Integer gId;
    /**
     * 
     */
    private Date createtime;
    /**
     * 
     */
    private Boolean anonymous;
    /**
     * 服务态度
     */
    private Integer scoreP;
    /**
     * 描述相符
     */
    private Integer scoreB;
    /**
     * 发货速度
     */
    private Integer scoreD;
    /**
     * 物流速度
     */
    private Integer scoreL;
    /**
     * 订单主键
     */
    private Integer oId;
    /**
     * 会员名称
     */
    private String mName;
    /**
     * 规格主键
     */
    private Integer ggId;
    
    /**
     * 图片路径
     */
    private String imgs;

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
    public Integer getmId(){
        return this.mId;
    }

    /**
     * 
     */
    public void setmId(Integer mId){
        this.mId = mId;
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
    public Integer getScore(){
        return this.score;
    }

    /**
     * 
     */
    public void setScore(Integer score){
        this.score = score;
    }    
    /**
     * 
     */
    public Integer getgId(){
        return this.gId;
    }

    /**
     * 
     */
    public void setgId(Integer gId){
        this.gId = gId;
    }    
    /**
     * 
     */
    public Date getCreatetime(){
        return this.createtime;
    }

    /**
     * 
     */
    public void setCreatetime(Date createtime){
        this.createtime = createtime;
    }    
    /**
     * 
     */
    public Boolean getAnonymous(){
        return this.anonymous;
    }

    /**
     * 
     */
    public void setAnonymous(Boolean anonymous){
        this.anonymous = anonymous;
    }    
    /**
     * 
     */
    public Integer getScoreP(){
        return this.scoreP;
    }

    /**
     * 
     */
    public void setScoreP(Integer scoreP){
        this.scoreP = scoreP;
    }    
    /**
     * 
     */
    public Integer getScoreB(){
        return this.scoreB;
    }

    /**
     * 
     */
    public void setScoreB(Integer scoreB){
        this.scoreB = scoreB;
    }    
    /**
     * 
     */
    public Integer getScoreD(){
        return this.scoreD;
    }

    /**
     * 
     */
    public void setScoreD(Integer scoreD){
        this.scoreD = scoreD;
    }    
    /**
     * 
     */
    public Integer getScoreL(){
        return this.scoreL;
    }

    /**
     * 
     */
    public void setScoreL(Integer scoreL){
        this.scoreL = scoreL;
    }    
    /**
     * 
     */
    public Integer getoId(){
        return this.oId;
    }

    /**
     * 
     */
    public void setoId(Integer oId){
        this.oId = oId;
    }    
    /**
     * 
     */
    public String getmName(){
        return this.mName;
    }

    /**
     * 
     */
    public void setmName(String mName){
        this.mName = mName;
    }    
    /**
     * 
     */
    public Integer getGgId(){
        return this.ggId;
    }

    /**
     * 
     */
    public void setGgId(Integer ggId){
        this.ggId = ggId;
    }

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}    
}