package com.zebone.dip.ws.resource.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zebone.dip.ws.resource.dao.PResourceUserInfoMapper;
import com.zebone.dip.ws.resource.dao.PResourceUserLogMapper;
import com.zebone.dip.ws.resource.model.PResourceUserInfo;
import com.zebone.dip.ws.resource.model.PResourceUserInfoExample;
import com.zebone.dip.ws.resource.model.PResourceUserInfoExample.Criteria;
import com.zebone.dip.ws.resource.model.PResourceUserLog;
import com.zebone.dip.ws.resource.pojo.Opt;
import com.zebone.dip.ws.resource.pojo.ResourceResult;
import com.zebone.dip.ws.resource.pojo.ResponseHead;
import com.zebone.dip.ws.resource.pojo.UserRequest;
import com.zebone.dip.ws.resource.pojo.UserRequest.RequestUserParam;
import com.zebone.dip.ws.resource.pojo.UserResponse;
import com.zebone.dip.ws.resource.pojo.UserResponse.ResponseUserItem;
import com.zebone.dip.ws.resource.service.ResourcesUserService;
import com.zebone.log.ErrorType;
import com.zebone.log.LogicError;
import com.zebone.util.DateUtil;
import com.zebone.util.UUIDUtil;
@Service("resourcesUserService")
public class ResourcesUserServiceImpl implements ResourcesUserService {

	private static final Log log = LogFactory.getLog(ResourcesUserServiceImpl.class);

	@Resource
	private PResourceUserInfoMapper pResourceUserInfoMapper;
	
	@Resource
	private PResourceUserLogMapper pResourceUserLogMapper;
	
	/**
	 * 保存资源人员信息
	 */
	@Override
	public UserResponse saveUser(UserRequest userRequest) {
		changeDate(userRequest);
		UserResponse userResponse = new UserResponse();
		ResponseHead responseHead = userResponse.getResponseHead();
		ResponseUserItem responseUserItem = new ResponseUserItem();
		boolean flag = true;
		try {
			responseHead.setRequestId(PropertyUtils.getNestedProperty(userRequest, "requestHead.requestId").toString());
			responseHead.setTradeNo(PropertyUtils.getNestedProperty(userRequest, "requestHead.tradeNo").toString());
			
			RequestUserParam requestUserParam  = userRequest.getBody().getUser();
			requestUserParam.setInputOrgCode(PropertyUtils.getNestedProperty(userRequest, "requestHead.orgCode").toString());
			BeanUtils.copyProperties(responseUserItem, requestUserParam);
			
			PResourceUserInfo resourceUserInfo = new PResourceUserInfo();	
			BeanUtils.copyProperties(resourceUserInfo, requestUserParam);
			resourceUserInfo.setuId(UUIDUtil.getUuid());
			resourceUserInfo.setCreateTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
			//保存前校验人员是否唯一
			int count = 0;
			count = pResourceUserInfoMapper.checkUserUnique(resourceUserInfo);
			if(count>0){
				//人员已经存在
				flag = false;
				responseHead.setErrorDesc(ErrorType.LOGIC.getErrorDesc()+"--"+LogicError.UNIQUE_NO.getErrorDesc()+"--该机构下，人员编码已存在");
				responseHead.setErrorCode(ErrorType.LOGIC.getErrorCode()+"-"+LogicError.UNIQUE_NO.getErrorCode());
				return userResponse;
			}
			pResourceUserInfoMapper.insert(resourceUserInfo);
			
			PResourceUserLog pResourceUserLog = new PResourceUserLog();
			BeanUtils.copyProperties(pResourceUserLog, resourceUserInfo);
			pResourceUserLog.setId(UUIDUtil.getUuid());
			pResourceUserLog.setCreateTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
			pResourceUserLog.setOperType(Opt.ADD.getCode());
			pResourceUserLogMapper.insert(pResourceUserLog);
			
		} catch (Exception e) {
			flag = false;
			log.error(e.getMessage(),e);
		}finally{
			if(flag){
				responseHead.setSucess(ResourceResult.SUCCEED.getCode());
			}else{
				List<ResponseUserItem> responseUserItemList = userResponse.getUserResponseBody().getResponseUserItemList();
				responseHead.setSucess(ResourceResult.FAIL.getCode());
				responseUserItemList.add(responseUserItem);
			}
		}
		return userResponse;
	}
	
	/**
	 * 删除资源人员信息
	 */
	@Override
	public UserResponse delUser(UserRequest userRequest) {
		changeDate(userRequest);
		UserResponse userResponse = new UserResponse();
		ResponseHead responseHead = userResponse.getResponseHead();
		ResponseUserItem responseUserItem = new ResponseUserItem();
		boolean flag = true;
		try {
			responseHead.setRequestId(PropertyUtils.getNestedProperty(userRequest, "requestHead.requestId").toString());
			responseHead.setTradeNo(PropertyUtils.getNestedProperty(userRequest, "requestHead.tradeNo").toString());
			
			RequestUserParam requestUserParam  = userRequest.getBody().getUser();
			requestUserParam.setInputOrgCode(PropertyUtils.getNestedProperty(userRequest, "requestHead.orgCode").toString());
			BeanUtils.copyProperties(responseUserItem, requestUserParam);

			PResourceUserInfo resourceUserInfo = new PResourceUserInfo();		
			BeanUtils.copyProperties(resourceUserInfo, requestUserParam);
			PResourceUserInfoExample pResourceUserInfoExample = new PResourceUserInfoExample();
			Criteria criteria = pResourceUserInfoExample.createCriteria();
			criteria.andCodeEqualTo(resourceUserInfo.getCode());
			criteria.andMedicalOrganCodeEqualTo(resourceUserInfo.getMedicalOrganCode());
			
			
			
			List<PResourceUserInfo>  pResourceUserInfoList = pResourceUserInfoMapper.selectByExample(pResourceUserInfoExample);
			if(pResourceUserInfoList!=null&&pResourceUserInfoList.size()>0){
				PResourceUserLog pResourceUserLog = new PResourceUserLog();
				pResourceUserLog.setId(UUIDUtil.getUuid());
				pResourceUserLog.setCreateTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
				pResourceUserLog.setOperType(Opt.DEL.getCode());
				BeanUtils.copyProperties(pResourceUserLog, pResourceUserInfoList.get(0));
				pResourceUserLogMapper.insert(pResourceUserLog);
				pResourceUserInfoMapper.deleteByExample(pResourceUserInfoExample);
			}else{
				flag = false;
				responseHead.setErrorDesc(ErrorType.LOGIC.getErrorDesc()+"--"+LogicError.DELETE_NO.getErrorDesc());
				responseHead.setErrorCode(ErrorType.LOGIC.getErrorCode()+"-"+LogicError.DELETE_NO.getErrorCode());
				return userResponse;
			}
			
			
	
		} catch (Exception e) {
		    flag = false;
			log.error(e.getMessage(),e);
		}finally{
			if(flag){
				responseHead.setSucess(ResourceResult.SUCCEED.getCode());
			}else{
				List<ResponseUserItem> responseUserItemList = userResponse.getUserResponseBody().getResponseUserItemList();
				responseHead.setSucess(ResourceResult.FAIL.getCode());
				responseUserItemList.add(responseUserItem);
			}
		}
		return userResponse;
	}
	
	/**
	 * 查询资源人员信息
	 */
	@Override
	public UserResponse searchUser(UserRequest userRequest) {
		changeDate(userRequest);
		UserResponse userResponse = new UserResponse();
		ResponseHead responseHead = userResponse.getResponseHead();
		ResponseUserItem responseUserItem = new ResponseUserItem();
		List<PResourceUserInfo> pResourceUserInfoList = null;
		List<ResponseUserItem> responseUserItemList = null;
		boolean flag = true;
		try {
			responseHead.setRequestId(PropertyUtils.getNestedProperty(userRequest, "requestHead.requestId").toString());
			responseHead.setTradeNo(PropertyUtils.getNestedProperty(userRequest, "requestHead.tradeNo").toString());
			
			RequestUserParam requestUserParam  = userRequest.getBody().getUser();
			requestUserParam.setInputOrgCode(PropertyUtils.getNestedProperty(userRequest, "requestHead.orgCode").toString());
			BeanUtils.copyProperties(responseUserItem, requestUserParam);

			PResourceUserInfo resourceUserInfo = new PResourceUserInfo();
			
			BeanUtils.copyProperties(resourceUserInfo, requestUserParam);
		    
			PResourceUserInfoExample pResourceUserInfoExample = new PResourceUserInfoExample();
			Criteria criteria = pResourceUserInfoExample.createCriteria();
			//瓶装查询条件
//			if(StringUtils.isNotEmpty(resourceUserInfo.getName())){
//				criteria.andNameLike("%"+resourceUserInfo.getName()+"%");
//			}
			if(StringUtils.isNotEmpty(resourceUserInfo.getMedicalOrganCode())){
				criteria.andMedicalOrganCodeEqualTo(resourceUserInfo.getMedicalOrganCode());
			}
			if(StringUtils.isNotEmpty(resourceUserInfo.getDepartmentCode())){
				criteria.andDepartmentCodeEqualTo(resourceUserInfo.getDepartmentCode());
			}
			if(StringUtils.isNotEmpty(resourceUserInfo.getPostCode())){
				criteria.andPostCodeEqualTo(resourceUserInfo.getPostCode());
			}
			if(StringUtils.isNotEmpty(resourceUserInfo.getPositionalTitlesCode())){
				criteria.andPositionalTitlesCodeEqualTo(resourceUserInfo.getPositionalTitlesCode());
			}
			
			pResourceUserInfoList = pResourceUserInfoMapper.selectByExample(pResourceUserInfoExample);	
			responseUserItemList = new ArrayList<ResponseUserItem>();
			for(PResourceUserInfo pResourceUserInfo : pResourceUserInfoList ){
				ResponseUserItem rresponseUserItem = new ResponseUserItem();
				BeanUtils.copyProperties(rresponseUserItem, pResourceUserInfo);
				responseUserItemList.add(rresponseUserItem);
			}
			
//			PResourceUserLog pResourceUserLog = new PResourceUserLog();
//			BeanUtils.copyProperties(pResourceUserLog, resourceUserInfo);
//			pResourceUserLog.setId(UUIDUtil.getUuid());
//			pResourceUserLog.setCreateTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
//			pResourceUserLog.setOperType(Opt.QUERY.getCode());
//			pResourceUserLogMapper.insert(pResourceUserLog);
		
		} catch (Exception e) {
		    flag = false;
			log.error(e.getMessage(),e);
		}finally{
			if(flag){
				responseHead.setSucess(ResourceResult.SUCCEED.getCode());
				userResponse.getUserResponseBody().setResponseUserItemList(responseUserItemList);
			}else{
				responseUserItemList = userResponse.getUserResponseBody().getResponseUserItemList();
				responseHead.setSucess(ResourceResult.FAIL.getCode());
				responseUserItemList.add(responseUserItem);
			}
		}
		return userResponse;
	}
	
	/**
	 * 更新资源人员信息
	 */
	@Override
	public UserResponse updateUser(UserRequest userRequest) {
		changeDate(userRequest);
		UserResponse userResponse = new UserResponse();
		ResponseHead responseHead = userResponse.getResponseHead();
		ResponseUserItem responseUserItem = new ResponseUserItem();
		boolean flag = true;
		try {
			responseHead.setRequestId(PropertyUtils.getNestedProperty(userRequest, "requestHead.requestId").toString());
			responseHead.setTradeNo(PropertyUtils.getNestedProperty(userRequest, "requestHead.tradeNo").toString());
			
			RequestUserParam requestUserParam  = userRequest.getBody().getUser();
			requestUserParam.setInputOrgCode(PropertyUtils.getNestedProperty(userRequest, "requestHead.orgCode").toString());
			BeanUtils.copyProperties(responseUserItem, requestUserParam);

			PResourceUserInfo resourceUserInfo = new PResourceUserInfo();
			
			BeanUtils.copyProperties(resourceUserInfo, requestUserParam);
		    
			PResourceUserInfoExample pResourceUserInfoExample = new PResourceUserInfoExample();
			Criteria criteria = pResourceUserInfoExample.createCriteria();
			criteria.andCodeEqualTo(resourceUserInfo.getCode());
			criteria.andMedicalOrganCodeEqualTo(resourceUserInfo.getMedicalOrganCode());
			
			List<PResourceUserInfo>  pResourceUserInfoList = pResourceUserInfoMapper.selectByExample(pResourceUserInfoExample);
			if(pResourceUserInfoList!=null&&pResourceUserInfoList.size()>0){
				PResourceUserLog pResourceUserLog = new PResourceUserLog();
				pResourceUserLog.setId(UUIDUtil.getUuid());
				pResourceUserLog.setCreateTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
				pResourceUserLog.setOperType(Opt.UPDATE.getCode());
				BeanUtils.copyProperties(pResourceUserLog, pResourceUserInfoList.get(0));
				pResourceUserLogMapper.insert(pResourceUserLog);
				pResourceUserInfoMapper.updateByExampleSelective(resourceUserInfo, pResourceUserInfoExample);
			}else{
				flag = false;
				responseHead.setErrorDesc(ErrorType.LOGIC.getErrorDesc()+"--"+LogicError.UPDATE_NO.getErrorDesc());
				responseHead.setErrorCode(ErrorType.LOGIC.getErrorCode()+"-"+LogicError.UPDATE_NO.getErrorCode());
				return userResponse;
			}
			
			
	
		} catch (Exception e) {
		    flag = false;
			log.error(e.getMessage(),e);
		}finally{
			if(flag){
				responseHead.setSucess(ResourceResult.SUCCEED.getCode());
			}else{
				List<ResponseUserItem> responseUserItemList = userResponse.getUserResponseBody().getResponseUserItemList();
				responseHead.setSucess(ResourceResult.FAIL.getCode());
				responseUserItemList.add(responseUserItem);
			}
		}
		return userResponse;
	
	}
	
	private void changeDate(UserRequest userRequest){
		RequestUserParam requestUserParam  = userRequest.getBody().getUser();
		if(requestUserParam.getBirthday()!=null&&requestUserParam.getBirthday()!=""){
			requestUserParam.setBirthday(DateUtil.format(requestUserParam.getBirthday(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(requestUserParam.getEntryDate()!=null&&requestUserParam.getEntryDate()!=""){
			requestUserParam.setEntryDate(DateUtil.format(requestUserParam.getEntryDate(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(requestUserParam.getQualificationDate()!=null&&requestUserParam.getQualificationDate()!=""){
			requestUserParam.setQualificationDate(DateUtil.format(requestUserParam.getQualificationDate(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(requestUserParam.getGraduationDate()!=null&&requestUserParam.getGraduationDate()!=""){
			requestUserParam.setGraduationDate(DateUtil.format(requestUserParam.getGraduationDate(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(requestUserParam.getFirstWorkDate()!=null&&requestUserParam.getFirstWorkDate()!=""){
			requestUserParam.setFirstWorkDate(DateUtil.format(requestUserParam.getFirstWorkDate(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
	}
	
}
