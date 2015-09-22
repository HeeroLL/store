package com.zebone.dip.compare.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.compare.vo.MDstdInfo;
import com.zebone.dip.compare.vo.MainInfo;
@Mapper
public interface MainDataOrgMapper {
	/**
	 * 保存机构主数据的对照信息
	 * @param mainInfo
	 * @return 
	 * int
	 */
	int saveMainDataInfo(MainInfo mainInfo);
	/**
	 * 保存机构主数据的对照信息
	 * @param mainInfo
	 * @return 
	 * int
	 */
	int updateMainDataInfo(MainInfo mainInfo);
	/**
	 * 删除机构主数据的对照信息
	 * @param mainInfo
	 * @return 
	 * int
	 */
	int removeMainDataInfo(MainInfo mainInfo);
	/**
	 * 获取主数据对照列表数据
	 * @param rowBounds
	 * @param mainInfo
	 * @return 
	 * List<MainInfo>
	 */
	List<MainInfo> getListByInfo(RowBounds rowBounds, MainInfo mainInfo);
	/**
	 * 获取主数据对照总数
	 * @param mainInfo
	 * @return 
	 * int
	 */
	int getCountByInfo(MainInfo mainInfo);
	/**
	 * 获取机构主数据相关列表信息
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
	 * @param rowBounds
	 * @param mainInfo
	 * @return 
	 * List<MDstdInfo>
	 */
	List<MDstdInfo> getListStd(RowBounds rowBounds, MainInfo mainInfo);
	/**
	 * 获取标准主数据总数
	 * @param mainInfo
	 * @return 
	 * int
	 */
	int getTotalCountStd(MainInfo mainInfo);
	/**
	 * 获取标准主数据匹配列表
	 * @param mainInfo
	 * @return 
	 * List<MainInfo>
	 */
	List<MainInfo> getMainListByName(MainInfo mainInfo);
	/**
	 * 查看该机构主数据在库中是否存在
	 * @param mainInfo
	 * @return 
	 * int
	 */
	int getCountByCode(MainInfo mainInfo);
}