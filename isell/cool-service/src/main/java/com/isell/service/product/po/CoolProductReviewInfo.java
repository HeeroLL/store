package com.isell.service.product.po;

import java.util.Date;

/**
 * 
 * 商品评价信息
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-27]
 */
public class CoolProductReviewInfo {
	
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
     * 
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
     * 
     */
    private Integer scoreP;
    /**
     * 
     */
    private Integer scoreB;
    /**
     * 
     */
    private Integer scoreD;
    /**
     * 
     */
    private Integer scoreL;
    /**
     * 
     */
    private Integer oId;
    /**
     * 会员名称
     */
    private String mName;
    /**
     * 
     */
    private Integer ggId;
    
    /**
     * 会员图片路径
     */
    private String logo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getmId() {
		return mId;
	}

	public void setmId(Integer mId) {
		this.mId = mId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getgId() {
		return gId;
	}

	public void setgId(Integer gId) {
		this.gId = gId;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Boolean getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(Boolean anonymous) {
		this.anonymous = anonymous;
	}

	public Integer getScoreP() {
		return scoreP;
	}

	public void setScoreP(Integer scoreP) {
		this.scoreP = scoreP;
	}

	public Integer getScoreB() {
		return scoreB;
	}

	public void setScoreB(Integer scoreB) {
		this.scoreB = scoreB;
	}

	public Integer getScoreD() {
		return scoreD;
	}

	public void setScoreD(Integer scoreD) {
		this.scoreD = scoreD;
	}

	public Integer getScoreL() {
		return scoreL;
	}

	public void setScoreL(Integer scoreL) {
		this.scoreL = scoreL;
	}

	public Integer getoId() {
		return oId;
	}

	public void setoId(Integer oId) {
		this.oId = oId;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public Integer getGgId() {
		return ggId;
	}

	public void setGgId(Integer ggId) {
		this.ggId = ggId;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

}
