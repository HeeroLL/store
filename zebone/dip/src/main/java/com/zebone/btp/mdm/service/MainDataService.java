package com.zebone.btp.mdm.service;

import java.util.List;

import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.mdm.vo.DBQuery;
import com.zebone.btp.mdm.vo.MDObject;
import com.zebone.btp.mdm.vo.MainDataTypeVO;

/**
 * 主数据 Service层
 * 
 * @author ouyangxin
 * 
 * CreateDate: 2012-11-22
 */
public interface MainDataService {

	MainDataTypeVO findMDTByCode(String code);

	List<MainDataTypeVO> queryMDTList(MainDataTypeVO mainDataType);

	Pagination<MainDataTypeVO> mdtQueryPage(Pagination<MainDataTypeVO> page,
			MainDataTypeVO mainDataType);

	Pagination<MDObject> mtDetailPage(Pagination<MDObject> page, DBQuery dbQuery);
	
	boolean removeMDBatch(DBQuery dbQuery);
	
	MDObject loadMD(DBQuery dbQuery);
	
	boolean operateMDData(DBQuery dbQuery);
	
	boolean operateMDTData(MainDataTypeVO vo);
	
	boolean updateMDTData(MainDataTypeVO vo);
	
	/**
	 * 查询type信息集合
	 * @return
	 */
	List<MainDataTypeVO> queryTypeList();
	
	/**
	 * 新增type自身信息
	 * @param vo
	 * @return
	 */
	boolean operateMDType(MainDataTypeVO vo);
	
	boolean removeMDType(DBQuery dbQuery);
}
