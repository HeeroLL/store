package com.zebone.ehrview.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zebone.ehrview.vo.HealthProblem;

/**
 * 健康与疾病问题数据服务层
 * @author YinCM
 *
 */
@Service
public interface HealthProblemService {
	
	/**
	 * 获得HealthProblem列表
	 * @param empiId 
	 * @param dataCode
	 * @return
	 */
	public List<HealthProblem> getHealthProblemList(String empiId, String dataCode);
}
