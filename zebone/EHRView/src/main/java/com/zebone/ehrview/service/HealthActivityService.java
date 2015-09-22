package com.zebone.ehrview.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zebone.ehrview.vo.AdultHealthExam;
import com.zebone.ehrview.vo.CommonListItem;
import com.zebone.ehrview.vo.Hypertension;
import com.zebone.ehrview.vo.OldersFollowup;

/**
 * 卫生服务活动接口 
 * @author YinCM
 *
 */
@Service
public interface HealthActivityService {
	
	/**
	 * 获取高血压随访列表
	 * @return 高血压随访列表
	 */
	public List<Hypertension> getHypertensionFollowupList(String empiId);
	/**
	 * 获取通用健康活动列表
	 * @param empiId
	 * @param 文档编号 
	 * @return
	 */
	public List<CommonListItem> getCommonList(String empiId, String docType);
	/**
	 * 获取糖尿病随访列表
	 * @return 糖尿病随访列表
	 */
	public List<Hypertension> getDiabetesFollowupList(String empiId);
	
	/**
	 * 获取成年人健康体检列表
	 * @return 成年人健康体检列表
	 */
	public List<AdultHealthExam> getAdultHealthExamList(String empiId);
	
	/**
	 * 获取老年人健康随访列表
	 * @return 老年人健康随访列表
	 */
	public List<OldersFollowup> getOldersFollowupList(String empiId);
	
}
