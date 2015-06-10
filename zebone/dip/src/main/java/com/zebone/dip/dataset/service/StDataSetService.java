/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Mar 26, 2013		标准数据集业务接口层
 */
package com.zebone.dip.dataset.service;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.dataset.vo.StDataSet;

public interface StDataSetService {

	/**
	 * @author caixl
	 * @date Mar 26, 2013
	 * @description TODO 获取标准数据集列表
	 * @param dataset
	 * @param page
	 * @return Pagination<StDataSet>
	 */
	Pagination<StDataSet> datasetInfoPage(StDataSet dataset,
			Pagination<StDataSet> page);

	/**
	 * @author caixl
	 * @date Mar 27, 2013
	 * @description TODO 根据标识获取一个标准数据集
	 * @param id
	 * @return StDataSet
	 */
	StDataSet getDatasetById(String id);

	/**
	 * @author caixl
	 * @date Mar 27, 2013
	 * @description TODO 保存标准数据集信息
	 * @param dataset
	 * @return int
	 */
	int saveDatasetInfo(StDataSet dataset);

	/**
	 * @author caixl
	 * @date Mar 27, 2013
	 * @description TODO 更新标准数据集信息
	 * @param dataset
	 * @return int
	 */
	int updateDatasetInfo(StDataSet dataset);

	/**
	 * @author caixl
	 * @date Mar 27, 2013
	 * @description TODO 逻辑删除标准数据集信息
	 * @param id
	 * @return int
	 */
	int removeDatasetByIds(String id);

}
