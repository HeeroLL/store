package com.zebone.dip.dataset.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.dataset.vo.StDataSet;
@Mapper
public interface StDataSetMapper {
	int deleteById(String id);

	int insert(StDataSet record);

	StDataSet findById(String id);

	int updateById(StDataSet record);

	/**
	 * @author caixl
	 * @date Mar 26, 2013
	 * @description TODO 获取标准数据集的列表
	 * @param rowBounds
	 * @param dataset
	 * @return List<StDataSet>
	 */
	List<StDataSet> datasetInfoList(RowBounds rowBounds, StDataSet dataset);

	/**
	 * @author caixl
	 * @date Mar 26, 2013
	 * @description TODO 获取标准数据集的总数量
	 * @param dataset
	 * @return int
	 */
	int datasetInfoTotalCount(StDataSet dataset);
}