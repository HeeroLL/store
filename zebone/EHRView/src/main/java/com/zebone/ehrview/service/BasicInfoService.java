package com.zebone.ehrview.service;

import org.springframework.stereotype.Service;

/**
 * 基础信息获取服务
 * @author YinCm
 * @version 2013-8-20 上午10:10:20
 */
@Service
public interface BasicInfoService {
	
	/**
	 * 通过固定格式xml来获取empi信息（xml格式）
	 * @param cardXml
	 * @return
	 */
	public String getEmpiBasicInfo(String cardXml);

    /**
     * 通过自定义的xml参数来获取健康档案信息
     * @param xmlStr
     * @return
     */
    public String getHealthRecordInfo (String xmlStr);
}
