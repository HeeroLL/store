package com.zebone.dip.compare.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import com.zebone.btp.app.mho.vo.Mho;
import com.zebone.btp.security.UserDetails;
import com.zebone.dip.compare.dao.CompareTemplateMapper;
import com.zebone.dip.compare.dao.DictTypeOrgMapper;
import com.zebone.dip.compare.vo.DictInfo;
import com.zebone.dip.compare.vo.MainInfo;
import com.zebone.util.DateUtil;
import com.zebone.util.UUIDUtil;

@Service
public class CompareCommonService {

	@Resource
	private CompareTemplateMapper compareTemplateMapper;
	
	@Resource
	private MDCompareService mdCompareService;
	
	@Resource
	private DictTypeOrgMapper dictTypeOrgMapper;
	/**
	 * 通过模板类型编码生成对应的excel模板
	 * 医疗机构信息   : 1
	 * 医疗机构人员信息   : 2
	 * 行政区划信息  :3
	 * 诊疗项目信息  : 4
	 * 药品信息 : 5
	 * 字典 : 6
	 * @param typeId
	 * @return 按模板中顺序排列，用引号内"**--**" 分隔，如：洛阳镇东序社区卫生服务站**--**10114104**--**10114000
	 */
	public List<String> generateModuleTemplate(String typeId) {
		List<String> resultStr = null;
		if(typeId.trim().equals("1")){
			resultStr = compareTemplateMapper.searchBizMedicalOrgan();
		}else if(typeId.trim().equals("2")){
			resultStr = compareTemplateMapper.searchBizMedicalOrganStaff();
		}else if(typeId.trim().equals("3")){
			resultStr = compareTemplateMapper.searchBizAdministrativeDivision();
		}else if(typeId.trim().equals("4")){
			resultStr = compareTemplateMapper.searchBizDiagnoseTreatmentItem();
		}else if(typeId.trim().equals("5")){
			resultStr = compareTemplateMapper.searchBizDrugInfo();
		}else if(typeId.trim().equals("6")){
			resultStr = compareTemplateMapper.searchDictionaryInfo();
		}
		return resultStr;
	}
	
	/**
	 * 保存主数据对照信息
	 * @param mainInfo
	 * @return
	 */
	public boolean saveMdCompareInfo(MainInfo mainInfo){
		boolean isSuccess=true;
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		List<Mho> mhos = userDetails.getMhoList();
		String levelCode = "";
		if(mhos!=null && mhos.size()>0){
			levelCode = mhos.get(0).getLevelCode();
		}
		try{
			mainInfo.setId(UUIDUtil.getUuid());
			mainInfo.setOrgCode(levelCode);
			mainInfo.setUserCode(userDetails.getPersonnelId());
			mainInfo.setCreateTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
			int result = mdCompareService.saveMdCompare(mainInfo);
			if(result == 2){
				isSuccess=false;
			}
		}catch (Exception e) {
			isSuccess=false;
		}
		return isSuccess;
	}
	 
	
	/**
	 * 保存对照信息
	 * @param dictInfo
	 * @return 
	 * int
	 */
	public boolean saveDictCompareInfo(DictInfo dictInfo) {
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		List<Mho> mhos = userDetails.getMhoList();
		if(mhos!=null && mhos.size()>0){
			dictInfo.setOrgCode(mhos.get(0).getLevelCode());
		}
		dictInfo.setUserCode(userDetails.getPersonnelId());
			String typeCout = dictTypeOrgMapper.getCountTypeByInfo(dictInfo);
			if(StringUtils.isBlank(typeCout)){
				dictInfo.setOrgDictTypeId(UUIDUtil.getUuid());
				dictTypeOrgMapper.saveDictType(dictInfo);
			}else{
				dictInfo.setOrgDictTypeId(typeCout);
			}
			int dictCount = dictTypeOrgMapper.getCountByInfo(dictInfo);
			if(dictCount>0){
				//System.out.println("dictInfo id, type"+dictInfo.getOrgDictId()+dictInfo.getOrgDictTypeId());
				return false;
			}else{
				String id = UUIDUtil.getUuid();
				dictInfo.setOrgDictId(id);
				dictTypeOrgMapper.saveDictInfo(dictInfo);
				dictInfo.setId(UUIDUtil.getUuid());
				dictInfo.setUserTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
				dictTypeOrgMapper.saveCompareInfo(dictInfo);
			}
	
		return true;
	}

}













