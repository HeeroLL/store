package com.zebone.dip.log.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.log.dao.DocLogMapper;
import com.zebone.dip.log.dao.LogDetailMapper;
import com.zebone.dip.log.vo.DocLog;
import com.zebone.dip.log.vo.LogDetail;
import com.zebone.dip.log.vo.ParmInfo;

/**
 * 类描述：
 * @author: caixl
 * @date： 日期：Sep 10, 2013
 * @version 1.0
 */
@Service("logService")
public class LogServiceImpl implements LogService {

	@Resource
	private DocLogMapper docLogMapper;
	
	@Resource
	private LogDetailMapper logDetailMapper;
	
	/**
	 * 方法描述: 获取文档类型数据
	 * @author caixl
	 * @date Sep 10, 2013
	 * @return 
	 * List<ParmInfo>
	 */
	@Override
	public List<ParmInfo> getDictInfo() {
		
		return docLogMapper.getDictInfo();
	}

	/**
	 * 方法描述: 匹配医疗机构相关信息
	 * @author caixl
	 * @date Sep 10, 2013
	 * @param name 医疗机构的相关参数（名称、代码）
	 * @param page 分页信息
	 * @return 
	 * Pagination<ParmInfo>
	 */
	@Override
	public Pagination<ParmInfo> getOrganInfo(String name,Pagination<ParmInfo> page) {
		List<ParmInfo> list = docLogMapper.getOrganInfo(name,page.getRowBounds());
		int totalCount = docLogMapper.getOrganCount(name);
		page.setData(list);
		page.setTotalCount(totalCount);
		return page;
	}

	/**
	 * 方法描述: 获取日志信息列表
	 * @author caixl
	 * @date Sep 10, 2013
	 * @param docLog
	 * @param page
	 * @return 
	 * Pagination<DocLog>
	 */
	@Override
	public Pagination<DocLog> getDocLogPage(DocLog docLog,Pagination<DocLog> page) {
		List<DocLog> data = docLogMapper.getDocLogList(docLog,page.getRowBounds());
		int totalCount = docLogMapper.getDocLogCount(docLog);
		page.setData(data);
		page.setTotalCount(totalCount);
		return page;
	}

	/**
	 * 方法描述: 获取错误日志详情
	 * @author caixl
	 * @date Sep 11, 2013
	 * @param logDetail
	 * @return 
	 * LogDetail
	 */
	@Override
	public LogDetail getLogDetailByLogInfo(LogDetail logDetail) {
		List<LogDetail> list = logDetailMapper.getLogDetailInfo(logDetail);
		if(list!=null &&list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
