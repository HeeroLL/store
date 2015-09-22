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

import com.zebone.dip.ws.resource.dao.PResourceFamilyInfoMapper;
import com.zebone.dip.ws.resource.dao.PResourceFamilyLogMapper;
import com.zebone.dip.ws.resource.dao.PResourceFamilyMembersLogMapper;
import com.zebone.dip.ws.resource.dao.PResourceFamilyMembersMapper;
import com.zebone.dip.ws.resource.model.PResourceFamilyInfo;
import com.zebone.dip.ws.resource.model.PResourceFamilyInfoExample;
import com.zebone.dip.ws.resource.model.PResourceFamilyInfoExample.Criteria;
import com.zebone.dip.ws.resource.model.PResourceFamilyLog;
import com.zebone.dip.ws.resource.model.PResourceFamilyMembers;
import com.zebone.dip.ws.resource.model.PResourceFamilyMembersExample;
import com.zebone.dip.ws.resource.model.PResourceFamilyMembersLog;
import com.zebone.dip.ws.resource.pojo.FamilyRequest;
import com.zebone.dip.ws.resource.pojo.FamilyRequest.FamilyMember;
import com.zebone.dip.ws.resource.pojo.FamilyRequest.Members;
import com.zebone.dip.ws.resource.pojo.FamilyRequest.RequestFamilyParam;
import com.zebone.dip.ws.resource.pojo.FamilyResponse;
import com.zebone.dip.ws.resource.pojo.FamilyResponse.ResponseFamilyItem;
import com.zebone.dip.ws.resource.pojo.Opt;
import com.zebone.dip.ws.resource.pojo.ResourceResult;
import com.zebone.dip.ws.resource.pojo.ResponseHead;
import com.zebone.dip.ws.resource.service.ResourceFamilyService;
import com.zebone.log.ErrorType;
import com.zebone.log.LogicError;
import com.zebone.util.DateUtil;
import com.zebone.util.UUIDUtil;
@Service("resourceFamilyService")
public class ResourceFamilyServiceImpl implements ResourceFamilyService {
	
	private static final Log log = LogFactory.getLog(ResourceFamilyService.class);

	@Resource
	private PResourceFamilyInfoMapper pResourceFamilyInfoMapper;
	
	@Resource
	private PResourceFamilyLogMapper pResourceFamilyLogMapper;
	
	@Resource
	private PResourceFamilyMembersMapper pResourceFamilyMembersMapper;
	
	@Resource
	private PResourceFamilyMembersLogMapper pResourceFamilyMembersLogMapper;

	@Override
	public FamilyResponse saveFamily(FamilyRequest familyRequest) {
		changeDate(familyRequest);
		FamilyResponse familyResponse = new FamilyResponse();
		ResponseHead responseHead = familyResponse.getResponseHead();
		ResponseFamilyItem responseFamilyItem = new ResponseFamilyItem();
		boolean flag = true;
		try {
			responseHead.setRequestId(PropertyUtils.getNestedProperty(familyRequest, "requestHead.requestId").toString());
			responseHead.setTradeNo(PropertyUtils.getNestedProperty(familyRequest, "requestHead.tradeNo").toString());
			
			RequestFamilyParam requestFamilyParam  = familyRequest.getBody().getFamily();
			requestFamilyParam.setOrgCode(PropertyUtils.getNestedProperty(familyRequest, "requestHead.orgCode").toString());
			BeanUtils.copyProperties(responseFamilyItem, requestFamilyParam);
			
			//家庭对象
			PResourceFamilyInfo resourceFamilyInfo = new PResourceFamilyInfo();	
			BeanUtils.copyProperties(resourceFamilyInfo, requestFamilyParam);
			resourceFamilyInfo.setFamiId(UUIDUtil.getUuid());
			resourceFamilyInfo.setCreateTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
			//保存前校验家庭是否唯一
			int count = 0;
			count = pResourceFamilyInfoMapper.checkFamilyUnique(resourceFamilyInfo);
			if(count>0){
				//家庭已经存在
				flag = false;
				responseHead.setErrorDesc(ErrorType.LOGIC.getErrorDesc()+"--"+LogicError.UNIQUE_NO.getErrorDesc()+"--该机构下，家庭编码已存在");
				responseHead.setErrorCode(ErrorType.LOGIC.getErrorCode()+"-"+LogicError.UNIQUE_NO.getErrorCode());
				return familyResponse;
			}
			
			//家庭日志对象
			PResourceFamilyLog pResourceFamilyLog = new PResourceFamilyLog();
			BeanUtils.copyProperties(pResourceFamilyLog, resourceFamilyInfo);
			pResourceFamilyLog.setId(UUIDUtil.getUuid());
			pResourceFamilyLog.setCreateTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
			pResourceFamilyLog.setOperType(Opt.ADD.getCode());
			
			//家庭成员对象
			List<PResourceFamilyMembers> resourceFamilyMembers = new ArrayList<PResourceFamilyMembers>();
			List<PResourceFamilyMembersLog> resourceFamilyMembersLog = new ArrayList<PResourceFamilyMembersLog>();
			List<FamilyMember> familyMembers = requestFamilyParam.getMembers().getFamilyMemberList();
			if(familyMembers!=null&&familyMembers.size()>0){
				for(FamilyMember obj : familyMembers){
					PResourceFamilyMembers member = new PResourceFamilyMembers();
					PResourceFamilyMembersLog memberLog = new PResourceFamilyMembersLog();
					BeanUtils.copyProperties(member, obj);
					member.setFamiId(resourceFamilyInfo.getFamiId());
					member.setId(UUIDUtil.getUuid());
					member.setFamiNo(resourceFamilyInfo.getFamiCode());
					resourceFamilyMembers.add(member);
					
					BeanUtils.copyProperties(memberLog, member);
					memberLog.setLogId(pResourceFamilyLog.getId());
					memberLog.setId(UUIDUtil.getUuid());
					memberLog.setFamiNo(pResourceFamilyLog.getFamiCode());
					resourceFamilyMembersLog.add(memberLog);
				}
			}
			//判断插入时是否有重复数据
			for(int i = 0 ;i<resourceFamilyMembers.size();i++){
				for(int j = i+1 ;j<resourceFamilyMembers.size();j++){
					if(resourceFamilyMembers.get(i).getIdcardType().equals(resourceFamilyMembers.get(j).getIdcardType())
							&&resourceFamilyMembers.get(i).getCardNo().equals(resourceFamilyMembers.get(j).getCardNo())){
						//家庭成员证件号码已经存在
						flag = false;
						responseHead.setErrorDesc(ErrorType.LOGIC.getErrorDesc()+"--"+LogicError.UNIQUE_NO.getErrorDesc()+"--家庭成员证件号码已存在");
						responseHead.setErrorCode(ErrorType.LOGIC.getErrorCode()+"-"+LogicError.UNIQUE_NO.getErrorCode());
						return familyResponse;
					}
				}
			}
			pResourceFamilyInfoMapper.insert(resourceFamilyInfo);
			pResourceFamilyLogMapper.insert(pResourceFamilyLog);
			if(resourceFamilyMembers.size()>0){
				pResourceFamilyMembersMapper.insertList(resourceFamilyMembers);
				pResourceFamilyMembersLogMapper.insertList(resourceFamilyMembersLog);
			}
			
		} catch (Exception e) {
			flag = false;
			log.error(e.getMessage(),e);
		}finally{
			if(flag){
				responseHead.setSucess(ResourceResult.SUCCEED.getCode());
			}else{
				List<ResponseFamilyItem> responseFamilyItemList = familyResponse.getFamilyResponseBody().getResponseFamilyItemList();
				responseHead.setSucess(ResourceResult.FAIL.getCode());
				responseFamilyItemList.add(responseFamilyItem);
			}
		}
		return familyResponse;
	}

	@Override
	public FamilyResponse delFamily(FamilyRequest familyRequest) {
		changeDate(familyRequest);
		FamilyResponse familyResponse = new FamilyResponse();
		ResponseHead responseHead = familyResponse.getResponseHead();
		ResponseFamilyItem responseFamilyItem = new ResponseFamilyItem();
		boolean flag = true;
		try {
			responseHead.setRequestId(PropertyUtils.getNestedProperty(familyRequest, "requestHead.requestId").toString());
			responseHead.setTradeNo(PropertyUtils.getNestedProperty(familyRequest, "requestHead.tradeNo").toString());
			
			RequestFamilyParam requestFamilyParam  = familyRequest.getBody().getFamily();
			requestFamilyParam.setOrgCode(PropertyUtils.getNestedProperty(familyRequest, "requestHead.orgCode").toString());
			BeanUtils.copyProperties(responseFamilyItem, requestFamilyParam);

			PResourceFamilyInfo resourceFamilyInfo = new PResourceFamilyInfo();		
			BeanUtils.copyProperties(resourceFamilyInfo, requestFamilyParam);
			PResourceFamilyInfoExample pResourceFamilyInfoExample = new PResourceFamilyInfoExample();
			Criteria criteria = pResourceFamilyInfoExample.createCriteria();
			criteria.andFamilyOrgCodeEqualTo(resourceFamilyInfo.getFamilyOrgCode());
			criteria.andFamiCodeEqualTo(resourceFamilyInfo.getFamiCode());
			
			
			
			List<PResourceFamilyInfo>  pResourceFamilyInfoList = pResourceFamilyInfoMapper.selectByExample(pResourceFamilyInfoExample);
			if(pResourceFamilyInfoList!=null&&pResourceFamilyInfoList.size()>0){
				//日志
				PResourceFamilyLog pResourceFamilyLog = new PResourceFamilyLog();
				pResourceFamilyLog.setId(UUIDUtil.getUuid());
				pResourceFamilyLog.setCreateTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
				pResourceFamilyLog.setOperType(Opt.DEL.getCode());
				BeanUtils.copyProperties(pResourceFamilyLog, pResourceFamilyInfoList.get(0));
				pResourceFamilyLogMapper.insert(pResourceFamilyLog);
				
				String famid = pResourceFamilyInfoList.get(0).getFamiId();
				PResourceFamilyMembersExample pResourceFamilyMembersExample = new PResourceFamilyMembersExample();
				com.zebone.dip.ws.resource.model.PResourceFamilyMembersExample.Criteria criteria2 = pResourceFamilyMembersExample.createCriteria();
				criteria2.andFamiIdEqualTo(famid);
				List<PResourceFamilyMembers> resourceFamilyMembers = pResourceFamilyMembersMapper.selectByExample(pResourceFamilyMembersExample);
				List<PResourceFamilyMembersLog> resourceFamilyMembersLog = new ArrayList<PResourceFamilyMembersLog>();
				if(resourceFamilyMembers!=null&&resourceFamilyMembers.size()>0){
					for(PResourceFamilyMembers obj : resourceFamilyMembers){
						PResourceFamilyMembersLog memberLog = new PResourceFamilyMembersLog();
						BeanUtils.copyProperties(memberLog, obj);
						memberLog.setLogId(pResourceFamilyLog.getId());
						memberLog.setId(UUIDUtil.getUuid());
						resourceFamilyMembersLog.add(memberLog);
					}
				}
				if(resourceFamilyMembers.size()>0){
					pResourceFamilyMembersLogMapper.insertList(resourceFamilyMembersLog);
				}
				
				//正式数据
				pResourceFamilyMembersMapper.deleteByExample(pResourceFamilyMembersExample);
				pResourceFamilyInfoMapper.deleteByExample(pResourceFamilyInfoExample);
			}else{
				flag = false;
				responseHead.setErrorDesc(ErrorType.LOGIC.getErrorDesc()+"--"+LogicError.DELETE_NO.getErrorDesc());
				responseHead.setErrorCode(ErrorType.LOGIC.getErrorCode()+"-"+LogicError.DELETE_NO.getErrorCode());
				return familyResponse;
			}
			
			
	
		} catch (Exception e) {
		    flag = false;
			log.error(e.getMessage(),e);
		}finally{
			if(flag){
				responseHead.setSucess(ResourceResult.SUCCEED.getCode());
			}else{
				List<ResponseFamilyItem> responseFamilyItemList = familyResponse.getFamilyResponseBody().getResponseFamilyItemList();
				responseHead.setSucess(ResourceResult.FAIL.getCode());
				responseFamilyItemList.add(responseFamilyItem);
			}
		}
		return familyResponse;
	
	}

	@Override
	public FamilyResponse searchFamily(FamilyRequest familyRequest) {
		changeDate(familyRequest);
		FamilyResponse familyResponse = new FamilyResponse();
		ResponseHead responseHead = familyResponse.getResponseHead();
		ResponseFamilyItem responseFamilyItem = new ResponseFamilyItem();
		List<PResourceFamilyInfo> pResourceFamilyInfoList = null;
		List<ResponseFamilyItem> responseFamilyItemList = null;
		boolean flag = true;
		try {
			responseHead.setRequestId(PropertyUtils.getNestedProperty(familyRequest, "requestHead.requestId").toString());
			responseHead.setTradeNo(PropertyUtils.getNestedProperty(familyRequest, "requestHead.tradeNo").toString());
			
			RequestFamilyParam requestFamilyParam  = familyRequest.getBody().getFamily();
			requestFamilyParam.setOrgCode(PropertyUtils.getNestedProperty(familyRequest, "requestHead.orgCode").toString());
			BeanUtils.copyProperties(responseFamilyItem, requestFamilyParam);

			PResourceFamilyInfo resourceFamilyInfo = new PResourceFamilyInfo();
			
			BeanUtils.copyProperties(resourceFamilyInfo, requestFamilyParam);
		    
			PResourceFamilyInfoExample pResourceFamilyInfoExample = new PResourceFamilyInfoExample();
			Criteria criteria = pResourceFamilyInfoExample.createCriteria();
			//瓶装查询条件
			if(StringUtils.isNotEmpty(resourceFamilyInfo.getFamilyOrgCode())){
				criteria.andFamilyOrgCodeEqualTo(resourceFamilyInfo.getFamilyOrgCode());
			}
			if(StringUtils.isNotEmpty(resourceFamilyInfo.getLiveType())){
				criteria.andLiveTypeEqualTo(resourceFamilyInfo.getLiveType());
			}
			if(StringUtils.isNotEmpty(resourceFamilyInfo.getFamilyProCode())){
				criteria.andFamilyProCodeEqualTo(resourceFamilyInfo.getFamilyProCode());
			}
			if(StringUtils.isNotEmpty(resourceFamilyInfo.getFamilyTypeCode())){
				criteria.andFamilyTypeCodeEqualTo(resourceFamilyInfo.getFamilyTypeCode());
			}
			
			pResourceFamilyInfoList = pResourceFamilyInfoMapper.selectByExample(pResourceFamilyInfoExample);	
			responseFamilyItemList = new ArrayList<ResponseFamilyItem>();
			if(pResourceFamilyInfoList!=null&&pResourceFamilyInfoList.size()>0){
				for(PResourceFamilyInfo pResourceFamilyInfo : pResourceFamilyInfoList ){
					ResponseFamilyItem rresponseFamilyItem = new ResponseFamilyItem();
					BeanUtils.copyProperties(rresponseFamilyItem, pResourceFamilyInfo);
					
					//家庭成员
					String famid = pResourceFamilyInfo.getFamiId();
					PResourceFamilyMembersExample pResourceFamilyMembersExample = new PResourceFamilyMembersExample();
					com.zebone.dip.ws.resource.model.PResourceFamilyMembersExample.Criteria criteria2 = pResourceFamilyMembersExample.createCriteria();
					criteria2.andFamiIdEqualTo(famid);
					List<PResourceFamilyMembers> resourceFamilyMembers = pResourceFamilyMembersMapper.selectByExample(pResourceFamilyMembersExample);
					List<FamilyMember> familyMemberList = new ArrayList<FamilyRequest.FamilyMember>();
					if(resourceFamilyMembers!=null&&resourceFamilyMembers.size()>0){
						for(PResourceFamilyMembers obj : resourceFamilyMembers){
							FamilyMember obj2 = new FamilyMember();
							BeanUtils.copyProperties(obj2, obj);
							familyMemberList.add(obj2);
						}
					}
					Members members = new Members();
					members.setFamilyMemberList(familyMemberList);
					rresponseFamilyItem.setMembers(members);
					responseFamilyItemList.add(rresponseFamilyItem);
				}
			}
			
			
//			PResourceFamilyLog pResourceFamilyLog = new PResourceFamilyLog();
//			BeanUtils.copyProperties(pResourceFamilyLog, resourceFamilyInfo);
//			pResourceFamilyLog.setId(UUIDUtil.getUuid());
//			pResourceFamilyLog.setCreateTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
//			pResourceFamilyLog.setOperType(Opt.QUERY.getCode());
//			pResourceFamilyLogMapper.insert(pResourceFamilyLog);
		
		} catch (Exception e) {
		    flag = false;
			log.error(e.getMessage(),e);
		}finally{
			if(flag){
				responseHead.setSucess(ResourceResult.SUCCEED.getCode());
				familyResponse.getFamilyResponseBody().setResponseFamilyItemList(responseFamilyItemList);
			}else{
				responseFamilyItemList = familyResponse.getFamilyResponseBody().getResponseFamilyItemList();
				responseHead.setSucess(ResourceResult.FAIL.getCode());
				responseFamilyItemList.add(responseFamilyItem);
			}
		}
		return familyResponse;
	}

	@Override
	public FamilyResponse updateFamily(FamilyRequest familyRequest) {
		changeDate(familyRequest);
		FamilyResponse familyResponse = new FamilyResponse();
		ResponseHead responseHead = familyResponse.getResponseHead();
		ResponseFamilyItem responseFamilyItem = new ResponseFamilyItem();
		boolean flag = true;
		try {
			responseHead.setRequestId(PropertyUtils.getNestedProperty(familyRequest, "requestHead.requestId").toString());
			responseHead.setTradeNo(PropertyUtils.getNestedProperty(familyRequest, "requestHead.tradeNo").toString());
			
			RequestFamilyParam requestFamilyParam  = familyRequest.getBody().getFamily();
			requestFamilyParam.setOrgCode(PropertyUtils.getNestedProperty(familyRequest, "requestHead.orgCode").toString());
			BeanUtils.copyProperties(responseFamilyItem, requestFamilyParam);

			PResourceFamilyInfo resourceFamilyInfo = new PResourceFamilyInfo();
			
			BeanUtils.copyProperties(resourceFamilyInfo, requestFamilyParam);
		    
			PResourceFamilyInfoExample pResourceFamilyInfoExample = new PResourceFamilyInfoExample();
			Criteria criteria = pResourceFamilyInfoExample.createCriteria();
			criteria.andFamilyOrgCodeEqualTo(resourceFamilyInfo.getFamilyOrgCode());
			criteria.andFamiCodeEqualTo(resourceFamilyInfo.getFamiCode());
			
			List<PResourceFamilyInfo>  pResourceFamilyInfoList = pResourceFamilyInfoMapper.selectByExample(pResourceFamilyInfoExample);
			if(pResourceFamilyInfoList!=null&&pResourceFamilyInfoList.size()>0){
				//家庭日志对象
				PResourceFamilyLog pResourceFamilyLog = new PResourceFamilyLog();
				pResourceFamilyLog.setId(UUIDUtil.getUuid());
				pResourceFamilyLog.setCreateTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
				pResourceFamilyLog.setOperType(Opt.UPDATE.getCode());
				BeanUtils.copyProperties(pResourceFamilyLog, pResourceFamilyInfoList.get(0));
				
				//要更新的家庭成员
				List<FamilyMember> familyMembers = requestFamilyParam.getMembers().getFamilyMemberList();
				String famid = pResourceFamilyInfoList.get(0).getFamiId();
				PResourceFamilyMembersExample pResourceFamilyMembersExample = new PResourceFamilyMembersExample();
				com.zebone.dip.ws.resource.model.PResourceFamilyMembersExample.Criteria criteria2 = pResourceFamilyMembersExample.createCriteria();
				criteria2.andFamiIdEqualTo(famid);
				List<PResourceFamilyMembers> resourceFamilyMembers = pResourceFamilyMembersMapper.selectByExample(pResourceFamilyMembersExample);
				
				List<PResourceFamilyMembers> needUpdateMembers = new ArrayList<PResourceFamilyMembers>();
				List<PResourceFamilyMembers> needUpdateMembersLog = new ArrayList<PResourceFamilyMembers>();
				List<PResourceFamilyMembers> needInsertMembers = new ArrayList<PResourceFamilyMembers>();
				if(familyMembers!=null&&familyMembers.size()>0){
					for(FamilyMember obj : familyMembers){
						int check = 0;
						if(resourceFamilyMembers!=null&&resourceFamilyMembers.size()>0){
							for(PResourceFamilyMembers obj2 : resourceFamilyMembers){
								if(obj.getIdcardType().equals(obj2.getIdcardType())&&obj.getCardNo().equals(obj2.getCardNo())){
									check++;
									needUpdateMembersLog.add(obj2);
									break;
								}
							}
						}
						//处理新增、更新
						PResourceFamilyMembers  members= new PResourceFamilyMembers();
						BeanUtils.copyProperties(members, obj);
						members.setFamiId(famid);
						members.setId(UUIDUtil.getUuid());
						members.setFamiNo(pResourceFamilyInfoList.get(0).getFamiCode());
						if(check>0){
							needUpdateMembers.add(members);
						}else{
							needInsertMembers.add(members);
						}
					}
				}
				//家庭成员日志
				List<PResourceFamilyMembersLog> resourceFamilyMembersLog = new ArrayList<PResourceFamilyMembersLog>();
				for(PResourceFamilyMembers obj : needUpdateMembersLog){
					PResourceFamilyMembersLog obj2 = new PResourceFamilyMembersLog();
					BeanUtils.copyProperties(obj2, obj);
					obj2.setLogId(pResourceFamilyLog.getId());
					obj2.setId(UUIDUtil.getUuid());
					resourceFamilyMembersLog.add(obj2);
				}
				for(PResourceFamilyMembers obj : needInsertMembers){
					PResourceFamilyMembersLog obj2 = new PResourceFamilyMembersLog();
					BeanUtils.copyProperties(obj2, obj);
					obj2.setLogId(pResourceFamilyLog.getId());
					obj2.setId(UUIDUtil.getUuid());
					resourceFamilyMembersLog.add(obj2);
				}
				//判断家庭成员是否重复（数据之间是否有重复）
				for(int i = 0 ;i<familyMembers.size();i++){
					for(int j = i+1 ;j<familyMembers.size();j++){
						if(familyMembers.get(i).getIdcardType().equals(familyMembers.get(j).getIdcardType())
								&&familyMembers.get(i).getCardNo().equals(familyMembers.get(j).getCardNo())){
							//家庭成员证件号码已经存在
							flag = false;
							responseHead.setErrorDesc(ErrorType.LOGIC.getErrorDesc()+"--"+LogicError.UNIQUE_NO.getErrorDesc()+"--家庭成员证件号码已存在");
							responseHead.setErrorCode(ErrorType.LOGIC.getErrorCode()+"-"+LogicError.UNIQUE_NO.getErrorCode());
							return familyResponse;
						}
					}
				}
				//判断家庭成员是否重复（插入数据和数据库中已经存在的是否有冲突）
				for(int i = 0 ;i<needInsertMembers.size();i++){
					for(int j = 0 ;j<resourceFamilyMembers.size();j++){
						if(needInsertMembers.get(i).getIdcardType().equals(resourceFamilyMembers.get(j).getIdcardType())
								&&needInsertMembers.get(i).getCardNo().equals(resourceFamilyMembers.get(j).getCardNo())){
							//家庭成员证件号码已经存在
							flag = false;
							responseHead.setErrorDesc(ErrorType.LOGIC.getErrorDesc()+"--"+LogicError.UNIQUE_NO.getErrorDesc()+"--家庭成员证件号码已存在");
							responseHead.setErrorCode(ErrorType.LOGIC.getErrorCode()+"-"+LogicError.UNIQUE_NO.getErrorCode());
							return familyResponse;
						}
					}
				}
				//家庭成员对象
				pResourceFamilyLogMapper.insert(pResourceFamilyLog);
				//家庭成员日志对象
				pResourceFamilyMembersLogMapper.insertList(resourceFamilyMembersLog);
				//家庭正式数据更新
				pResourceFamilyInfoMapper.updateByExampleSelective(resourceFamilyInfo, pResourceFamilyInfoExample);
				//家庭成员正式数据新增
				if(needInsertMembers.size()>0){
					pResourceFamilyMembersMapper.insertList(needInsertMembers);
				}
				//家庭成员正式数据更新
				if(needUpdateMembers.size()>0){
					for(PResourceFamilyMembers obj : needUpdateMembers){
						PResourceFamilyMembersExample membersExample = new PResourceFamilyMembersExample();
						com.zebone.dip.ws.resource.model.PResourceFamilyMembersExample.Criteria criteria3 = membersExample.createCriteria();
						criteria3.andFamiIdEqualTo(famid);
						criteria3.andIdcardTypeEqualTo(obj.getIdcardType());
						criteria3.andCardNoEqualTo(obj.getCardNo());
						pResourceFamilyMembersMapper.updateByExampleSelective(obj,membersExample);
					}
				}
			}else{
				flag = false;
				responseHead.setErrorDesc(ErrorType.LOGIC.getErrorDesc()+"--"+LogicError.UPDATE_NO.getErrorDesc());
				responseHead.setErrorCode(ErrorType.LOGIC.getErrorCode()+"-"+LogicError.UPDATE_NO.getErrorCode());
				return familyResponse;
			}
			
			
	
		} catch (Exception e) {
		    flag = false;
			log.error(e.getMessage(),e);
		}finally{
			if(flag){
				responseHead.setSucess(ResourceResult.SUCCEED.getCode());
			}else{
				List<ResponseFamilyItem> responseFamilyItemList = familyResponse.getFamilyResponseBody().getResponseFamilyItemList();
				responseHead.setSucess(ResourceResult.FAIL.getCode());
				responseFamilyItemList.add(responseFamilyItem);
			}
		}
		return familyResponse;
	
	}
	
	private void changeDate(FamilyRequest familyRequest){
		RequestFamilyParam requestFamilyParam  = familyRequest.getBody().getFamily();
		if(requestFamilyParam.getFileDate()!=null&&requestFamilyParam.getFileDate()!=""){
			requestFamilyParam.setFileDate(DateUtil.format(requestFamilyParam.getFileDate(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(requestFamilyParam.getInputDate()!=null&&requestFamilyParam.getInputDate()!=""){
			requestFamilyParam.setInputDate(DateUtil.format(requestFamilyParam.getInputDate(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(requestFamilyParam.getMembers()!=null){
			List<FamilyMember> familyMemberList = requestFamilyParam.getMembers().getFamilyMemberList();
			if(familyMemberList!=null&&familyMemberList.size()>0){
				for(FamilyMember obj : familyMemberList){
					if(obj.getBirthDate()!=null&&obj.getBirthDate()!=""){
						obj.setBirthDate(DateUtil.format(obj.getBirthDate(), "yyyy-MM-dd", "yyyyMMdd"));
					}
				}
			}
		}
		
	}

}
