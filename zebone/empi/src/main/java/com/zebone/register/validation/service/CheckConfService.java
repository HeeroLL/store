package com.zebone.register.validation.service;



import java.util.List;

import com.zebone.register.validation.domain.CheckConf;



/**
 *  接口描述：校验开关service层接口
  * 项目名称：empi
  * 类名称：    CheckConfService 
  * 类描述： 
  * 创建人：     LinBin 
  * 创建时间：2015年6月8日 上午10:49:40
  * 修改人：     LinBin 
  * 修改时间：2015年6月8日 上午10:49:40
  * 修改备注： 
  * @version
 */

public interface CheckConfService {

	/**
	  * 方法描述: 获取所有文档校验开关的相关的信息
	  * @Title: getCheckConfList 
	  * @author LinBin
	  * @return
	  * @throws
	 */
	List<CheckConf> getCheckConfList();

	/**
	  * 方法描述: 校验开关的相关数据保存
	  * @Title: checkoutSave 
	  * @author LinBin
	  * @param data
	  * @return
	  * @throws
	 */
	int checkoutSave(String data);
	
}
