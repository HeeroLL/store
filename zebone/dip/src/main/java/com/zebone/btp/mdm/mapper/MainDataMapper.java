package com.zebone.btp.mdm.mapper;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.btp.mdm.vo.DBQuery;
import com.zebone.btp.mdm.vo.MainDataTypeVO;

@Mapper
public interface MainDataMapper {

	/**
	 * 根据Code查询单个MainDataType
	 */
	MainDataTypeVO findByCode(String code);

	/**
	 *  查询MainDataType列表
	 */
	List<MainDataTypeVO> queryMDTList(MainDataTypeVO mainDataType);

	/**
	 *  查询MainDataType列表 分页
	 */
	List<MainDataTypeVO> queryMDTPageList(RowBounds rowBounds,
			MainDataTypeVO mainDataType);

	/**
	 *  查询MainDataType列表 总数
	 */
	int queryMDT2otal(MainDataTypeVO mainDataType);

	/**
	 *  查询MD列表
	 */
	List<HashMap<String, String>> queryDetailObject(RowBounds rowBounds,
			DBQuery dbQuery);

	/**
	 *  查询MD列表 总数
	 */
	int queryDetailObjectTotal(DBQuery dbQuery);

	/**
	 *  批量删除
	 */
	int removeMDBatch(DBQuery dbQuery);

	/**
	 * 根据ID查询MD
	 */
	List<HashMap<String, String>> queryDetailObjectByID(DBQuery dbQuery);
	
	/**
	 *  新增MD
	 */
	int addMDData(DBQuery dbQuery);
	
	/**
	 *  根据ＩＤ删除MD
	 */
	int deleteMDData(DBQuery dbQuery);
	
	/**
	 *  新增MDT
	 */
	int addMDTData(MainDataTypeVO vo);
	
	/**
	 * 类型表中ID最大字段值
	 */
	String getMaxCode();
	
	/**
	 * 类型表中类型集合
	 */
	List<MainDataTypeVO> queryTypeList();
	
	/**
	 * 类型表中type最大字段值
	 */
	String getMaxTypeValue();
	
	/**
	 *  新增type
	 */
	int addMDType(MainDataTypeVO vo);
	
	/**
	 *  新增主数据
	 */
	int updateMDTByCode(MainDataTypeVO vo);

	/**
	 * 对应的表是否有数据
	 */
	int queryDataExists(DBQuery dbQuery);
	
	/**
	 * 根据ID删除数据
	 */
	int removeMDType(DBQuery dbQuery);
}
