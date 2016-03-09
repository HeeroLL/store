package com.isell.app.dao.entity;

import java.util.List;

import com.isell.service.shop.vo.CoonBanner;

/**
 * 系统海报表vo
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-23]
 */
public class CoonBannerInfo{
	
	/**
	 * 海报列表
	 */
	private List<CoonBanner> bannerList;

	public List<CoonBanner> getBannerList() {
		return bannerList;
	}

	public void setBannerList(List<CoonBanner> bannerList) {
		this.bannerList = bannerList;
	}
	
}