package com.zebone.dip.ws.resource.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zebone.dip.ws.resource.dao.PResourceDeptInfoMapper;
import com.zebone.dip.ws.resource.dao.PResourceDeptLogMapper;
import com.zebone.dip.ws.resource.model.PResourceDeptInfo;
import com.zebone.dip.ws.resource.model.PResourceDeptInfoExample;
import com.zebone.dip.ws.resource.model.PResourceDeptInfoExample.Criteria;
import com.zebone.dip.ws.resource.model.PResourceDeptLog;
import com.zebone.dip.ws.resource.pojo.OfficeRequest;
import com.zebone.dip.ws.resource.pojo.OfficeRequest.RequestOfficeParam;
import com.zebone.dip.ws.resource.pojo.OfficeResponse;
import com.zebone.dip.ws.resource.pojo.OfficeResponse.ResponseOfficeItem;
import com.zebone.dip.ws.resource.pojo.Opt;
import com.zebone.dip.ws.resource.pojo.ResourceResult;
import com.zebone.dip.ws.resource.pojo.ResponseHead;
import com.zebone.dip.ws.resource.service.ResourceDeptService;
import com.zebone.log.ErrorType;
import com.zebone.log.LogicError;
import com.zebone.util.DateUtil;
import com.zebone.util.UUIDUtil;

@Service("resourceDeptService")
public class ResourceDeptServiceImpl implements ResourceDeptService {
	
	private static final Log log = LogFactory.getLog(ResourceDeptServiceImpl.class);
	
	@Resource
	private PResourceDeptInfoMapper pResourceDeptInfoMapper;
	
	@Resource
	private PResourceDeptLogMapper pResourceDeptLogMapper;

	@Override
	public OfficeResponse saveDept(OfficeRequest officeRequest) {
		OfficeResponse officeResponse = new OfficeResponse();
		ResponseHead responseHead = officeResponse.getResponseHead();
		ResponseOfficeItem responseOfficeItem = new ResponseOfficeItem();
		boolean flag = true;
		try {		
			responseHead.setRequestId(PropertyUtils.getNestedProperty(officeRequest, "requestHead.requestId").toString());
			responseHead.setTradeNo(PropertyUtils.getNestedProperty(officeRequest, "requestHead.tradeNo").toString());
			
			RequestOfficeParam requestOfficeParam  = officeRequest.getBody().getOffice();
			BeanUtils.copyProperties(responseOfficeItem, officeRequest.getBody().getOffice());

			PResourceDeptInfo resourceDeptInfo = new PResourceDeptInfo();	
			BeanUtils.copyProperties(resourceDeptInfo, requestOfficeParam);
			resourceDeptInfo.setInputOrgCode(PropertyUtils.getNestedProperty(officeRequest, "requestHead.orgCode").toString());
			resourceDeptInfo.setDeptId(UUIDUtil.getUuid());
			resourceDeptInfo.setCreateTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
			//保存前校验科室是否唯一
			int count = 0;
			count = pResourceDeptInfoMapper.checkDeptUnique(resourceDeptInfo);
			if(count>0){
				//科室已经存在
				flag = false;
				responseHead.setErrorDesc(ErrorType.LOGIC.getErrorDesc()+"--"+LogicError.UNIQUE_NO.getErrorDesc()+"--该机构下，科室编码已存在");
				responseHead.setErrorCode(ErrorType.LOGIC.getErrorCode()+"-"+LogicError.UNIQUE_NO.getErrorCode());
				return officeResponse;
			}
			pResourceDeptInfoMapper.insert(resourceDeptInfo);
	        
			
			PResourceDeptLog pResourceDeptLog = new PResourceDeptLog();
			BeanUtils.copyProperties(pResourceDeptLog, resourceDeptInfo);
			pResourceDeptLog.setId(UUIDUtil.getUuid());
			pResourceDeptLog.setCreateTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
			pResourceDeptLog.setOperType(Opt.ADD.getCode());
			pResourceDeptLogMapper.insert(pResourceDeptLog);
		} catch (IllegalAccessException e) {
		    flag = false;
			log.error(e.getMessage(),e);
		} catch (InvocationTargetException e) {
			flag = false;
			log.error(e.getMessage(),e);
		} catch (NoSuchMethodException e) {
			flag = false;
			log.error(e.getMessage(),e);
		}finally{
			if(flag){
				responseHead.setSucess(ResourceResult.SUCCEED.getCode());
			}else{
				List<ResponseOfficeItem> responseOfficeItemList = officeResponse.getOfficeResponseBody().getResponseOfficeItemList();
				responseHead.setSucess(ResourceResult.FAIL.getCode());
				responseOfficeItemList.add(responseOfficeItem);
			}
		}
		return officeResponse;
	}

	@Override
	public OfficeResponse delDept(OfficeRequest officeRequest) {
		OfficeResponse officeResponse = new OfficeResponse();
		ResponseHead responseHead = officeResponse.getResponseHead();
		ResponseOfficeItem responseOfficeItem = new ResponseOfficeItem();
		boolean flag = true;
		try {

			responseHead.setRequestId(PropertyUtils.getNestedProperty(officeRequest, "requestHead.requestId").toString());
			responseHead.setTradeNo(PropertyUtils.getNestedProperty(officeRequest, "requestHead.tradeNo").toString());
			RequestOfficeParam requestOfficeParam  = officeRequest.getBody().getOffice();
			BeanUtils.copyProperties(responseOfficeItem, officeRequest.getBody().getOffice());

			PResourceDeptInfo resourceDeptInfo = new PResourceDeptInfo();		
			BeanUtils.copyProperties(resourceDeptInfo, requestOfficeParam);
			PResourceDeptInfoExample pResourceDeptInfoExample = new PResourceDeptInfoExample();
			Criteria criteria = pResourceDeptInfoExample.createCriteria();
			criteria.andDeptCodeEqualTo(resourceDeptInfo.getDeptCode());
			criteria.andOrgCodeEqualTo(resourceDeptInfo.getOrgCode());
			List<PResourceDeptInfo> pResourceDeptInfoList = pResourceDeptInfoMapper.selectByExample(pResourceDeptInfoExample);
			
			if(pResourceDeptInfoList != null && pResourceDeptInfoList.size() > 0){
				for(PResourceDeptInfo deptInfo : pResourceDeptInfoList){
					
					PResourceDeptLog pResourceDeptLog = new PResourceDeptLog();
					BeanUtils.copyProperties(pResourceDeptLog, deptInfo);
					pResourceDeptLog.setId(UUIDUtil.getUuid());
					pResourceDeptLog.setCreateTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
					pResourceDeptLog.setOperType(Opt.DEL.getCode());
					pResourceDeptLogMapper.insert(pResourceDeptLog);
					
					pResourceDeptInfoMapper.deleteByPrimaryKey(deptInfo.getDeptId());
				}
			}else{
				flag = false;
				responseHead.setErrorDesc(ErrorType.LOGIC.getErrorDesc()+"--"+LogicError.DELETE_NO.getErrorDesc());
				responseHead.setErrorCode(ErrorType.LOGIC.getErrorCode()+"-"+LogicError.DELETE_NO.getErrorCode());
				return officeResponse;
			}
		} catch (IllegalAccessException e) {
		    flag = false;
			log.error(e.getMessage(),e);
		} catch (InvocationTargetException e) {
			flag = false;
			log.error(e.getMessage(),e);
		} catch (NoSuchMethodException e) {
			flag = false;
			log.error(e.getMessage(),e);
		}finally{
			if(flag){
				responseHead.setSucess(ResourceResult.SUCCEED.getCode());
			}else{
				List<ResponseOfficeItem> responseOfficeItemList = officeResponse.getOfficeResponseBody().getResponseOfficeItemList();
				responseHead.setSucess(ResourceResult.FAIL.getCode());
				responseOfficeItemList.add(responseOfficeItem);
			}
		}
		return officeResponse;
	}

	@Override
	public OfficeResponse searchDept(OfficeRequest officeRequest) {
		OfficeResponse officeResponse = new OfficeResponse();
		ResponseHead responseHead = officeResponse.getResponseHead();
		ResponseOfficeItem responseOfficeItem = new ResponseOfficeItem();
		List<PResourceDeptInfo> pResourceDeptInfoList = null;
		List<ResponseOfficeItem> responseOfficeItemList = null;
		boolean flag = true;
		try {
			responseHead.setRequestId(PropertyUtils.getNestedProperty(officeRequest, "requestHead.requestId").toString());
			responseHead.setTradeNo(PropertyUtils.getNestedProperty(officeRequest, "requestHead.tradeNo").toString());
			RequestOfficeParam requestOfficeParam  = officeRequest.getBody().getOffice();
			BeanUtils.copyProperties(responseOfficeItem, officeRequest.getBody().getOffice());

			PResourceDeptInfo resourceDeptInfo = new PResourceDeptInfo();
			
			BeanUtils.copyProperties(resourceDeptInfo, requestOfficeParam);
		    
			PResourceDeptInfoExample pResourceDeptInfoExample = new PResourceDeptInfoExample();
			Criteria criteria = pResourceDeptInfoExample.createCriteria();
			
			if(StringUtils.isNotEmpty(resourceDeptInfo.getParentDeptCode())){
				criteria.andParentDeptCodeEqualTo(resourceDeptInfo.getParentDeptCode());
			}
			if(StringUtils.isNotEmpty(resourceDeptInfo.getOrgCode())){
				criteria.andOrgCodeEqualTo(resourceDeptInfo.getOrgCode());
			}
			
			pResourceDeptInfoList = pResourceDeptInfoMapper.selectByExample(pResourceDeptInfoExample);	
			responseOfficeItemList = new ArrayList<ResponseOfficeItem>();
			for(PResourceDeptInfo pResourceDeptInfo : pResourceDeptInfoList ){
				ResponseOfficeItem rresponseOfficeItem = new ResponseOfficeItem();
				BeanUtils.copyProperties(rresponseOfficeItem, pResourceDeptInfo);
				responseOfficeItemList.add(rresponseOfficeItem);
			}
			
			/**
			PResourceDeptLog pResourceDeptLog = new PResourceDeptLog();
			BeanUtils.copyProperties(pResourceDeptLog, resourceDeptInfo);
			pResourceDeptLog.setId(UUIDUtil.getUuid());
			pResourceDeptLog.setCreateTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
			pResourceDeptLog.setOperType(Opt.QUERY.getCode());
			pResourceDeptLog.setInputOrgCode(PropertyUtils.getNestedProperty(officeRequest, "requestHead.orgCode").toString());
			pResourceDeptLogMapper.insertSelective(pResourceDeptLog);
			**/
		
		} catch (IllegalAccessException e) {
		    flag = false;
			log.error(e.getMessage(),e);
		} catch (InvocationTargetException e) {
			flag = false;
			log.error(e.getMessage(),e);
		} catch (NoSuchMethodException e) {
			flag = false;
			log.error(e.getMessage(),e);
		}finally{
			if(flag){
				responseHead.setSucess(ResourceResult.SUCCEED.getCode());
				officeResponse.getOfficeResponseBody().setResponseOfficeItemList(responseOfficeItemList);
			}else{
				responseOfficeItemList = officeResponse.getOfficeResponseBody().getResponseOfficeItemList();
				responseHead.setSucess(ResourceResult.FAIL.getCode());
				responseOfficeItemList.add(responseOfficeItem);
			}
		}
		return officeResponse;
	
	
	}

	@Override
	public OfficeResponse updateDept(OfficeRequest officeRequest) {
		OfficeResponse officeResponse = new OfficeResponse();
		ResponseHead responseHead = officeResponse.getResponseHead();
		ResponseOfficeItem responseOfficeItem = new ResponseOfficeItem();
		boolean flag = true;
		try {
			responseHead.setRequestId(PropertyUtils.getNestedProperty(officeRequest, "requestHead.requestId").toString());
			responseHead.setTradeNo(PropertyUtils.getNestedProperty(officeRequest, "requestHead.tradeNo").toString());
			RequestOfficeParam requestOfficeParam  = officeRequest.getBody().getOffice();
			BeanUtils.copyProperties(responseOfficeItem, officeRequest.getBody().getOffice());

			PResourceDeptInfo resourceDeptInfo = new PResourceDeptInfo();
			
			BeanUtils.copyProperties(resourceDeptInfo, requestOfficeParam);
		    
			PResourceDeptInfoExample pResourceDeptInfoExample = new PResourceDeptInfoExample();
			Criteria criteria = pResourceDeptInfoExample.createCriteria();
			criteria.andDeptCodeEqualTo(resourceDeptInfo.getDeptCode());
			criteria.andOrgCodeEqualTo(resourceDeptInfo.getOrgCode());
			
			List<PResourceDeptInfo> pResourceDeptInfoList = pResourceDeptInfoMapper.selectByExample(pResourceDeptInfoExample);
			if(pResourceDeptInfoList != null && pResourceDeptInfoList.size() > 0){
				for(PResourceDeptInfo deptInfo : pResourceDeptInfoList){
					PResourceDeptLog pResourceDeptLog = new PResourceDeptLog();
					BeanUtils.copyProperties(pResourceDeptLog, deptInfo);
					pResourceDeptLog.setId(UUIDUtil.getUuid());
					pResourceDeptLog.setCreateTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
					pResourceDeptLog.setOperType(Opt.UPDATE.getCode());
					pResourceDeptLogMapper.insert(pResourceDeptLog);
								
					resourceDeptInfo.setInputOrgCode(PropertyUtils.getNestedProperty(officeRequest, "requestHead.orgCode").toString());
					pResourceDeptInfoMapper.updateByExampleSelective(resourceDeptInfo, pResourceDeptInfoExample);
				}
			}else{
				flag = false;
				responseHead.setErrorDesc(ErrorType.LOGIC.getErrorDesc()+"--"+LogicError.UPDATE_NO.getErrorDesc());
				responseHead.setErrorCode(ErrorType.LOGIC.getErrorCode()+"-"+LogicError.UPDATE_NO.getErrorCode());
				return officeResponse;
			}
		} catch (IllegalAccessException e) {
		    flag = false;
			log.error(e.getMessage(),e);
		} catch (InvocationTargetException e) {
			flag = false;
			log.error(e.getMessage(),e);
		} catch (NoSuchMethodException e) {
			flag = false;
			log.error(e.getMessage(),e);
		}finally{
			if(flag){
				responseHead.setSucess(ResourceResult.SUCCEED.getCode());
			}else{
				List<ResponseOfficeItem> responseOfficeItemList = officeResponse.getOfficeResponseBody().getResponseOfficeItemList();
				responseHead.setSucess(ResourceResult.FAIL.getCode());
				responseOfficeItemList.add(responseOfficeItem);
			}
		}
		return officeResponse;
	
	}
}
