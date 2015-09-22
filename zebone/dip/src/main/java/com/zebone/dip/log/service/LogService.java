package com.zebone.dip.log.service;

import java.util.List;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.log.vo.DocLog;
import com.zebone.dip.log.vo.LogDetail;
import com.zebone.dip.log.vo.ParmInfo;

/**
 * 接口描述：日志查询业务接口层
 * @author: caixl
 * @date： 日期：Sep 10, 2013
 * @version 1.0
 */

public interface LogService {

	/**
	 * 方法描述: 获取文档类型数据
	 * @author caixl
	 * @date Sep 10, 2013
	 * @return 
	 * List<ParmInfo>
	 */
	List<ParmInfo> getDictInfo();

	/**
	 * 方法描述: 匹配医疗机构相关信息
	 * @author caixl
	 * @date Sep 10, 2013
	 * @param name 医疗机构的相关参数（名称、代码）
	 * @param page 分页信息
	 * @return 
	 * Pagination<ParmInfo>
	 */
	Pagination<ParmInfo> getOrganInfo(String name,Pagination<ParmInfo> page);

	/**
	 * 方法描述: 获取日志信息列表
	 * @author caixl
	 * @date Sep 10, 2013
	 * @param docLog
	 * @param page
	 * @return 
	 * Pagination<DocLog>
	 */
	Pagination<DocLog> getDocLogPage(DocLog docLog, Pagination<DocLog> page);

	/**
	 * 方法描述: 获取错误日志详情
	 * @author caixl
	 * @date Sep 11, 2013
	 * @param logDetail
	 * @return 
	 * LogDetail
	 */
	LogDetail getLogDetailByLogInfo(LogDetail logDetail);

}
