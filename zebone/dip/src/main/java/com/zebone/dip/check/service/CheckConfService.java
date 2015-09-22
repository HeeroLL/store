package com.zebone.dip.check.service;

import java.util.List;

import com.zebone.dip.check.vo.CheckConf;

/**
 * 接口描述：校验开关service层接口
 * @author: caixl
 * @date： 日期：Sep 3, 2013
 * @version 1.0
 */

public interface CheckConfService {

	/**
	 * 方法描述: 获取所有文档校验开关的相关的信息
	 * @author caixl
	 * @date Sep 3, 2013
	 * @return 
	 * List<CheckConf>
	 */
	List<CheckConf> getCheckConfList();

	/**
	 * 方法描述: 校验开关的相关数据保存
	 * @author caixl
	 * @date Sep 3, 2013
	 * @param data 
	 * @return 
	 * int
	 */
	int checkoutSave(String data);
	
}
