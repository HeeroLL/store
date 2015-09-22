package com.zebone.dnode.engine.validation.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zebone.dip.log.service.DocExtInfoService;
import com.zebone.dip.log.service.LogServiceClient;
import com.zebone.dip.log.vo.DocExtendInfo;
import com.zebone.dip.log.vo.Error;
import com.zebone.dip.log.vo.Syslog;
import com.zebone.dnode.engine.validation.domain.CheckLog;
import com.zebone.dnode.engine.validation.domain.CheckMain;
import com.zebone.dnode.engine.validation.domain.CheckResult;
import com.zebone.dnode.engine.validation.dto.ErrorMsg;
import com.zebone.dnode.engine.validation.dto.ErrorMsgDetail;
import com.zebone.dnode.engine.validation.dto.ValidationResult;
import com.zebone.dnode.engine.validation.mapper.CheckDataMapper;
import com.zebone.dnode.engine.validation.repository.CheckDataRepository;
import com.zebone.dnode.engine.validation.service.CheckDataService;
import com.zebone.util.DateUtil;
import com.zebone.util.UUIDUtil;

/**
 * 整个校验模块的 service实现类
 * @author 陈阵 
 * @date 2013-7-30 下午2:57:12
 */
@Service("checkDataService")
public class CheckDataServiceImpl implements CheckDataService {
	
	private static final Log logger = LogFactory.getLog(CheckDataServiceImpl.class);
	
	@Resource
	private CheckDataMapper checkDataMapper;
	
	@Resource
	private CheckDataRepository checkDataRepository;
	
	@Resource
	private LogServiceClient logServiceClient;

	@Override
	public void saveCheckMain(CheckMain checkMain) {
		// TODO Auto-generated method stub
		checkDataMapper.saveCheckMain(checkMain);
	}

	@Override
	public void saveCheckLog(CheckLog checkLog) {
		// TODO Auto-generated method stub
		checkDataMapper.saveCheckLog(checkLog);
	}

	@Override
	public void saveValidationResult(ValidationResult validationResult, Long execTime) {
		// TODO Auto-generated method stub

		ErrorMsg errorMsg = validationResult.getErrorMsg();
		List<ErrorMsgDetail> errorMsgDetail = validationResult.getErrorMsgDetial();
		
		/**校验主表  **/
		CheckMain checkMain = new CheckMain();
    	String checkMainId = UUIDUtil.getUuid();
    	checkMain.setId(checkMainId);
    	checkMain.setStartTime(errorMsg.getStartTime());
    	checkMain.setEndTime(errorMsg.getEndTime());
    	checkMain.setDocCode(errorMsg.getDocCode());
    	checkMain.setDocOrgCode(errorMsg.getDocOrgCode());
    	checkMain.setDocTypeCode(errorMsg.getDocTypeCode());
    	checkMain.setDocXml(errorMsg.getDocXml());
    	checkMain.setCheckFlag("1");
    	checkMain.setDocVersion(errorMsg.getDocVersion());
		if(!validationResult.isSucess()){
	    	checkDataMapper.saveCheckMain(checkMain);

		}	
    	/** 校验明细表 **/
    	if(!errorMsgDetail.isEmpty()){
    		List<CheckLog> checkLogList = new ArrayList<CheckLog>();
    		for(ErrorMsgDetail emd : errorMsgDetail){
    			String errorException = emd.getErrorException();
    			String errorDesc = emd.getErrorDesc();
    			if(errorDesc.length() > 500){
    				errorDesc = errorDesc.substring(0,500);
    			}
    			if(StringUtils.isNotEmpty(errorException) && errorException.length() > 500){
    				errorException = errorException.substring(0,500);
    			}
    			
    			CheckLog checkLog = new CheckLog();
		    	checkLog.setId(UUIDUtil.getUuid());
		    	checkLog.setMainId(checkMainId);
		    	checkLog.setErrorType(emd.getErrorType());
		    	checkLog.setErrorSubType(emd.getErrorSubType());
		    	checkLog.setErrorDesc(errorDesc);
		    	checkLog.setErrorException(errorException);
		    	checkLog.setDocXpath(emd.getDocXpath());
		    	checkLog.setStartTime(emd.getStartTime());
		    	checkLog.setEndTime(emd.getEndTime());
		    	checkLogList.add(checkLog);
    		}	
    		checkDataRepository.saveBatchCheckLog(checkLogList);
    	}    
    	wsSaveValidationResult(validationResult, execTime);
	}
    

	
	@Override
	public void saveCheckResult(CheckResult checkResult){
		checkDataMapper.saveCheckResult(checkResult);
	}

	@Override
	public void wsSaveValidationResult(ValidationResult validationResult, long execTime) {
		// TODO Auto-generated method stub
		Syslog sysLog = new Syslog();
		DocExtendInfo docExtendInfo = DocExtInfoService.getExtInfo(validationResult.getDataDocument().asXML());
		ErrorMsg errorMsg = validationResult.getErrorMsg();
		List<ErrorMsgDetail> errorMsgDetail = validationResult.getErrorMsgDetial();
		sysLog.setCategory("1");
		sysLog.setType(Syslog.TYPE_CHECK);
		sysLog.setDocNo(errorMsg.getDocCode());
		sysLog.setDoctype(errorMsg.getDocTypeCode());
		sysLog.setDeptCode(errorMsg.getDocOrgCode());
		sysLog.setLogID(docExtendInfo.getUuid());
		sysLog.setLogTime(DateUtil.getCurrentDate());
		sysLog.setExecTime(execTime);
		if(validationResult.isSucess()){
			sysLog.setStatus("1");
		}else{
			sysLog.setStatus("0");
		}
		
		Error error = new Error();
		if(!errorMsgDetail.isEmpty()){
			StringBuilder errorMsgSb = new StringBuilder();
			for(ErrorMsgDetail emd : errorMsgDetail){
				error.setCode(emd.getErrorType());
				errorMsgSb.append(emd.getDocXpath()).append(":").append(emd.getErrorDesc())
					.append("<br>").append(emd.getErrorException()).append("<br>");
    		}
	        error.setDesc(errorMsgSb.toString());
		} 
		sysLog.setError(error);
		logServiceClient.saveLog(sysLog);
	}


}
