/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Mar 26, 2013		标准数据集业务实现层
 */
package com.zebone.dip.dataset.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.dataset.dao.StDataSetMapper;
import com.zebone.dip.dataset.service.StDataSetService;
import com.zebone.dip.dataset.vo.StDataSet;
@Service("stDataSetService")
public class StDataSetServiceImpl implements StDataSetService {
	@Resource
	private StDataSetMapper stDataSetMapper;
	
	/**
	 * @author caixl
	 * @date Mar 26, 2013
	 * @description TODO 获取标准数据集列表
	 * @param dataset
	 * @param page
	 * @return Pagination<StDataSet>
	 */
	@Override
	public Pagination<StDataSet> datasetInfoPage(StDataSet dataset,
			Pagination<StDataSet> page) {
		List<StDataSet> list = stDataSetMapper.datasetInfoList(page.getRowBounds(),dataset);
		int totalCount = stDataSetMapper.datasetInfoTotalCount(dataset);
		page.setData(list);
		page.setTotalCount(totalCount);
		return page;
	}

	/**
	 * @author caixl
	 * @date Mar 27, 2013
	 * @description TODO 根据标识获取一个标准数据集
	 * @param id
	 * @return StDataSet
	 */
	@Override
	public StDataSet getDatasetById(String id) {
		return stDataSetMapper.findById(id);
	}

	/**
	 * @author caixl
	 * @date Mar 27, 2013
	 * @description TODO 保存标准数据集信息
	 * @param dataset
	 * @return int
	 */
	@Override
	public int saveDatasetInfo(StDataSet dataset) {
		stDataSetMapper.insert(dataset);
		return 1;
	}

	/**
	 * @author caixl
	 * @date Mar 27, 2013
	 * @description TODO 更新标准数据集信息
	 * @param dataset
	 * @return int
	 */
	@Override
	public int updateDatasetInfo(StDataSet dataset) {
		stDataSetMapper.updateById(dataset);
		return 1;
	}

	/**
	 * @author caixl
	 * @date Mar 27, 2013
	 * @description TODO 逻辑删除标准数据集信息
	 * @param id
	 * @return int
	 */
	@Override
	public int removeDatasetByIds(String id) {
		String[] ids = id.split(",");
		for(String datasetId : ids){
			StDataSet dataset = new StDataSet();
			dataset.setId(datasetId);
			dataset.setDelFlag("0");
			stDataSetMapper.updateById(dataset);
		}
		return 1;
	}

}
