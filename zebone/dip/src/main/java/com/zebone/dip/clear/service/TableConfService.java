/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Apr 18, 2013		表配置业务接口层
 */
package com.zebone.dip.clear.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.clear.vo.ClearConf;
import com.zebone.dip.clear.vo.TableConf;

public interface TableConfService {

	/**
	 * @author caixl
	 * @date Apr 18, 2013
	 * @description TODO 获取表配置管理列表
	 * @param rowBounds
	 * @param tableConf
	 * @return Pagination<TableConf>
	 */
	Pagination<TableConf> taskInfoList(RowBounds rowBounds, TableConf tableConf);

	/**
	 * @author caixl
	 * @date Apr 18, 2013
	 * @description TODO 根据标识获取配置表信息
	 * @param id
	 * @return TableConf
	 */
	TableConf getTableConfById(String id);

	/**
	 * @author caixl
	 * @date Apr 18, 2013
	 * @description TODO 保存表配置管理信息
	 * @param tableConf
	 * @return int
	 */
	int saveTableConfInfo(TableConf tableConf);

	/**
	 * @author caixl
	 * @date Apr 18, 2013
	 * @description TODO 更新表配置管理信息
	 * @param tableConf
	 * @return int
	 */
	int updateTableConfInfo(TableConf tableConf);

	/**
	 * @author caixl
	 * @date Apr 18, 2013
	 * @description TODO 根据标识 删除 表配置相关信息
	 * @param id
	 * @return int
	 */
	int removeTableConfByIds(String id);

	/**
	 * @author caixl
	 * @date Apr 18, 2013
	 * @description TODO 获取所有清洗表信息
	 * @return List<TableConf>
	 */
	List<TableConf> getAllTableConf();

}
