package com.zebone.dip.compare.service;

import java.util.List;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.compare.vo.MDstdInfo;
import com.zebone.dip.compare.vo.MainInfo;


/**
 * 主数据对照管理业务接口
 */

public interface MDCompareService {

	/**
	 * 获取主数据对照列表信息
	 * @param page
	 * @param mainInfo
	 * @return 
	 * Pagination<MainInfo>
	 */
	Pagination<MainInfo> getListByInfo(Pagination<MainInfo> page,MainInfo mainInfo);

	/**
	 * 获取机构主数据信息
	 * @param mainInfo
	 * @return 
	 * List<MainInfo>
	 */
	List<MainInfo> getOrgMainInfoByName(MainInfo mainInfo);

	/**
	 * 获取某个机构主数据对照信息
	 * @param mainInfo
	 * @return 
	 * MainInfo
	 */
	MainInfo getInfoById(MainInfo mainInfo);

	/**
	 * 获取标准主数据列表信息
	 * @param page
	 * @param mainInfo
	 * @return 
	 * Pagination<MDstdInfo>
	 */
	Pagination<MDstdInfo> getStdListByInfo(Pagination<MDstdInfo> page,MainInfo mainInfo);

	/**
	 * 获取标准主数据匹配列表
	 * @param mainInfo
	 * @return 
	 * List<MainInfo>
	 */
	List<MainInfo> getMainListByName(MainInfo mainInfo);

	/**
	 * 保存主数据对照信息
	 * @param mainInfo
	 * @return 
	 * int
	 */
	int saveMdCompare(MainInfo mainInfo);

	/**
	 * 更新主数据对照信息
	 * @param mainInfo 
	 * void
	 */
	void updateMdCompare(MainInfo mainInfo);

	/**
	 * 删除主数据对照信息
	 * @param mainInfo 
	 * void
	 */
	void removeMdCompare(MainInfo mainInfo);

}
