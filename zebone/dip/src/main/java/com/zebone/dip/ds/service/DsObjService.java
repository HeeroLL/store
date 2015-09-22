/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Feb 26, 2013		数据源业务层接口
 */
package com.zebone.dip.ds.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.ds.vo.DsObj;

public interface DsObjService {

	/**
	 * @author caixl
	 * @date Feb 26, 2013
	 * @description TODO 查询数据源管理列表
	 * @param rowBounds
	 * @param dsObj 查询条件
	 * @return Pagination<DsObj>
	 */
	Pagination<DsObj> searchListDsObj(RowBounds rowBounds, DsObj dsObj);

	/**
	 * @author caixl
	 * @date Feb 26, 2013
	 * @description TODO 根据数据源标识获取数据源信息
	 * @param id 数据源标识
	 * @return DsObj
	 */
	DsObj getDsObjInfoById(String id);

	/**
	 * @author caixl
	 * @date Feb 26, 2013
	 * @description TODO 保存数据源数据
	 * @param dsObj
	 * @return int
	 */
	int saveDsObjInfo(DsObj dsObj);

	/**
	 * @author caixl
	 * @date Feb 26, 2013
	 * @description TODO 更新数据源数据
	 * @param dsObj
	 * @return int
	 */
	int updateDsObjInfo(DsObj dsObj);

	/**
	 * @author caixl
	 * @date Feb 26, 2013
	 * @description TODO 根据数据源标识删除数据源相关信息
	 * @param id 数据源标识
	 * @return int
	 */
	int removeDsObjById(String id);

	/**
	 * @author caixl
	 * @date Feb 26, 2013
	 * @description TODO 检测数据源是否通畅
	 * @param dsObj 数据源信息
	 * @return int 检测结果标识
	 */
	int detectDsObj(DsObj dsObj);

	/**
	 * @author caixl
	 * @date Mar 8, 2013
	 * @description TODO 获取 所有数据源的信息
	 * @return List<DsObj>
	 */
	List<DsObj> findDsObjAll();

}
