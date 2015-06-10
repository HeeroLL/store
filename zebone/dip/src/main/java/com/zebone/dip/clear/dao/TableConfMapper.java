package com.zebone.dip.clear.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.clear.vo.TableConf;
@Mapper
public interface TableConfMapper {
	int deleteById(String id);

	int insert(TableConf record);

	TableConf findById(String id);

	int updateById(TableConf record);

	/**
	 * @author caixl
	 * @date Apr 18, 2013
	 * @description TODO 获取表配置管理列表
	 * @param rowBounds
	 * @param tableConf
	 * @return List<TableConf>
	 */
	List<TableConf> getTableConfList(RowBounds rowBounds, TableConf tableConf);

	/**
	 * @author caixl
	 * @date Apr 18, 2013
	 * @description TODO 获取表配置管理总数
	 * @param tableConf
	 * @return int
	 */
	int getTableConfTotalCount(TableConf tableConf);

	/**
	 * @author caixl
	 * @date Apr 18, 2013
	 * @description TODO 批量删除表配置管理信息
	 * @return int
	 */
	void updateTableConfByIds(List<String> list);

	/**
	 * @author caixl
	 * @date Apr 18, 2013
	 * @description TODO 获取所有清洗表信息
	 * @return List<TableConf>
	 */
	List<TableConf> getAllTableConf();
}