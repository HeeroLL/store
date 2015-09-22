package com.zebone.ehrview.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zebone.ehrview.vo.HealthMenuItem;
import com.zebone.ehrview.vo.ResidentInfo;
import com.zebone.ehrview.vo.ServiceMenuItem;
import com.zebone.ehrview.vo.SpecialDocument;

/**
 * 为HomeController提供相应数据服务
 * @author YinCM
 * @since 2013-8-27
 */
@Service
public interface HomeService {
	/**
	 * 获取首页患者基本信息模块
	 * @param card_no
     * @param card_type
	 * @return
	 */
	public ResidentInfo getPatientBasicInfo(String name, String card_no, String card_type, String orgCode);
	
	/**
	 * 获取健康服务活动菜单
	 * @return 菜单item列表
	 */
	public List<ServiceMenuItem> getServiceMenuItems(String empiId);
	
	/**
	 * 获取专项档案列表
	 * @return
	 */
	public List<SpecialDocument> getSpecialDoc(String empiId);
	
	/**
	 * 根据dataCode获取首页信息
     * @param empiId
     * @param dataCode
	 * @return
	 */
	public Map<String,String> getHealthHomeInfo(String empiId, String dataCode);
	
	/**
	 * 获取健康和疾病问题菜单选项
	 * @return
	 */
	public List<HealthMenuItem> getHealthMenuItems(String empiId);
}
