package com.isell.service.shop.po;

import com.isell.core.mybatis.page.PageConfig;

public class CoonShopShareParam extends PageConfig{
	/**
     * 
     */
    private String id;
    /**
     * 酷店主键
     */
    private String sShop;
    /**
     * 会员主键
     */
    private Integer mId;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getsShop() {
		return sShop;
	}
	public void setsShop(String sShop) {
		this.sShop = sShop;
	}
	public Integer getmId() {
		return mId;
	}
	public void setmId(Integer mId) {
		this.mId = mId;
	}

}
