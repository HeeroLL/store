package com.zebone.dip.clear.dao;

import java.util.List;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.clear.vo.ClearCloumn;
@Mapper
public interface ClearCloumnMapper {
	int deleteById(String id);

	int insert(ClearCloumn record);

	ClearCloumn findById(String id);

	int updateById(ClearCloumn record);

	/**
	 * @author caixl
	 * @date Apr 19, 2013
	 * @description TODO
	 * @param id
	 * @return List<ClearCloumn>
	 */
	List<ClearCloumn> getClearCloumnsByClearId(String id);

	/**
	 * @author caixl
	 * @date Apr 20, 2013
	 * @description TODO 保存清洗表字段配置信息
	 * @return int
	 */
	int insertClearInfos(List<ClearCloumn> list);

	/**
	 * @author caixl
	 * @date Apr 20, 2013
	 * @description TODO 删除某个清洗表字段配置信息
	 * @return int
	 */
	void deleteByClearId(String id);
}