package com.isell.ei.shop.meilishuo.bean;

import java.util.List;

/**
 * 美丽说物流公司信息
 * 
 * @author wangpeng
 * @version [版本号, 2016-01-06]
 */
public class MeilishuoLogisticsCompanies {
	
	/**
	 * 物流公司
	 */
	private List<MeilishuoLogisticsCompany> company_data;
	
	/**
	 * 物流公司总数
	 */
	private Integer total_num;

	public List<MeilishuoLogisticsCompany> getCompany_data() {
		return company_data;
	}

	public void setCompany_data(List<MeilishuoLogisticsCompany> company_data) {
		this.company_data = company_data;
	}

	public Integer getTotal_num() {
		return total_num;
	}

	public void setTotal_num(Integer total_num) {
		this.total_num = total_num;
	}

}
