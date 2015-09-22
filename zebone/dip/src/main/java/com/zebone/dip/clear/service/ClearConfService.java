/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Apr 19, 2013		清洗配置管理业务接口层
 */
package com.zebone.dip.clear.service;

import java.util.List;

import com.zebone.dip.clear.vo.ClearCloumn;
import com.zebone.dip.clear.vo.ClearConf;
import com.zebone.dip.clear.vo.TableConf;
import com.zebone.dip.task.vo.Task;

public interface ClearConfService {

	/**
	 * @author caixl
	 * @date Apr 19, 2013
	 * @description TODO get columns of table
	 * @param tableConf
	 * @return List<String>
	 */
	List<String> getColsByTableConfInfo(TableConf tableConf);

	/**
	 * @author caixl
	 * @date Apr 19, 2013
	 * @description TODO get information of ClearConf by tableId 
	 * @param id
	 * @return ClearConf
	 */
	ClearConf getClearConfByTableId(String id);

	/**
	 * @author caixl
	 * @date Apr 19, 2013
	 * @description TODO 获取配置字段相关信息
	 * @param id
	 * @return List<ClearCloumn>
	 */
	List<ClearCloumn> getClearCloumnsByClearId(String id);

	/**
	 * @author caixl
	 * @date Apr 20, 2013
	 * @description TODO 保存清洗表配置任务信息
	 * @param task
	 * @param clearConf
	 * @param clearData
	 * @return int
	 */
	int saveClearInfo(Task task, ClearConf clearConf, String clearData);

	/**
	 * @author caixl
	 * @date Apr 20, 2013
	 * @description TODO 更新清洗表配置任务信息
	 * @param task
	 * @param clearConf
	 * @param clearData
	 * @return int
	 */
	int updateClearInfo(Task task, ClearConf clearConf, String clearData);

	/**
	 * @author caixl
	 * @date Apr 20, 2013
	 * @description TODO 更新清洗配置发布状态
	 * @param id
	 * @return int
	 */
	int updateClearDeployFlag(String id);

	/**
	 * @author caixl
	 * @date Apr 20, 2013
	 * @description TODO 获取配置表 信息
	 * @param id
	 * @return ClearConf
	 */
	ClearConf getClearConfById(String id);

}
