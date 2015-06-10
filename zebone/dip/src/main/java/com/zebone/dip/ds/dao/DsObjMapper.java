package com.zebone.dip.ds.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.ds.vo.DsObj;
@Mapper
public interface DsObjMapper {
	int deleteById(String id);

	int insert(DsObj record);

	DsObj findById(String id);

	List<DsObj> findAllInfo();
	
	int updateById(DsObj record);

	/**
	 * @author caixl
	 * @date Feb 26, 2013
	 * @description TODO 获取数据源列表信息
	 * @param rowBounds
	 * @param dsObj 查询条件
	 * @return List<DsObj>
	 */
	List<DsObj> searchListDsObj(RowBounds rowBounds, DsObj dsObj);

	/**
	 * @author caixl
	 * @date Feb 26, 2013
	 * @description TODO 获取数据源的总数量
	 * @param dsObj 查询条件
	 * @return int
	 */
	int searchTotalCountDsObj(DsObj dsObj);

	/**
	 * @author caixl
	 * @date Feb 26, 2013
	 * @description TODO 根据数据源标识获取数据源相关信息
	 * @param id 数据源标识
	 * @return DsObj
	 */
	DsObj getDsObjInfoById(String id);

	/**
	 * @author caixl
	 * @date Feb 26, 2013
	 * @description TODO 保存数据源信息
	 * @param dsObj
	 * @return int
	 */
	int saveDsObjInfo(DsObj dsObj);

	/**
	 * @author caixl
	 * @date Feb 26, 2013
	 * @description TODO 更新数据源信息
	 * @param dsObj
	 * @return int
	 */
	int updateDsObjInfo(DsObj dsObj);

	/**
	 * @author caixl
	 * @date Feb 26, 2013
	 * @description TODO
	 * @param list
	 * @return int
	 */
	int removeDsObjByids(List<String> list);
}