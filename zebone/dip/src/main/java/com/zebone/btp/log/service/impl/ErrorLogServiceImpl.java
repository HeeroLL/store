package com.zebone.btp.log.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.zebone.btp.app.mho.vo.Mho;
import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.log.dao.ErrorLogMapper;
import com.zebone.btp.log.pojo.ErrorLog;
import com.zebone.btp.log.service.ErrorLogService;

@Service("errorLogService")
public class ErrorLogServiceImpl implements ErrorLogService {
	private static final Log log = LogFactory.getLog(ErrorLogServiceImpl.class);
	@Resource
	private ErrorLogMapper errorLogMapper;

	/**
	 * @see com.zebone.btp.log.service.ErrorLogService#deleteById(java.lang.String)
	 */
	@Override
	public void deleteById(String errorLogId) {
		this.errorLogMapper.deleteById(errorLogId);

	}

	/**
	 * @see com.zebone.btp.log.service.ErrorLogService#findById(java.lang.String)
	 */
	@Override
	public ErrorLog findById(String errorLogId) {
		return this.errorLogMapper.findById(errorLogId);
	}

	/**
	 * @see com.zebone.btp.log.service.ErrorLogService#save(com.zebone.btp.log.pojo.ErrorLog)
	 */
	@Override
	public void save(ErrorLog errorLog) {
		this.errorLogMapper.insert(errorLog);

	}

	/**
	 * @see com.zebone.btp.log.service.ErrorLogService#updateById(com.zebone.btp.log.pojo.ErrorLog)
	 */
	@Override
	public void updateById(ErrorLog errorLog) {
		this.errorLogMapper.updateById(errorLog);

	}
	
	/**
	 * @see com.zebone.btp.log.service.ErrorLogService#saveInThread(com.zebone.btp.log.pojo.ErrorLog)
	 */
	public void saveInThread(ErrorLog errorLog){
		SaveThrad st = new SaveThrad(errorLog);
		new Thread(st).start();
	}
	
	/**
	 * 保存错误日志线程。
	 * @author thinkpad
	 *
	 */
	private class SaveThrad implements Runnable{
		private ErrorLog errorLog;
		public SaveThrad(ErrorLog errorLog){
			this.errorLog = errorLog;
		}
		@Override
		public void run() {
			try {
				errorLogMapper.insert(errorLog);
			} catch (Exception e) {
				log.error("",e);
			}
		}
		
	}

	/**
	 * @see com.zebone.btp.log.service.ErrorLogService#findErrorInfo(java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date, org.apache.ibatis.session.RowBounds)
	 */
	@Override
	public Pagination<ErrorLog> findErrorInfo(String className, String methodName,String errorinfo, Date beginCreateTime, Date endCreateTime,	Pagination<ErrorLog> pagination) {
		RowBounds  rowBounds = pagination.getRowBounds();
		List<ErrorLog> errorLogList = this.errorLogMapper.findErrorInfo(className, methodName, errorinfo, beginCreateTime, endCreateTime, rowBounds);
		Integer count =	this.errorLogMapper.findErrorInfoCount(className, methodName, errorinfo, beginCreateTime, endCreateTime);
		pagination.setData(errorLogList);
		pagination.setTotalCount(count);
		return pagination;
	}

	/**
	 * @see com.zebone.btp.log.service.ErrorLogService#getErrorInfoById(java.lang.String)
	 */
	public String getErrorInfoById(String errorLogid){
		return this.errorLogMapper.getErrorInfoById(errorLogid);
	}
}
