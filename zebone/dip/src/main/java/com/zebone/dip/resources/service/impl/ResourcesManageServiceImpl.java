package com.zebone.dip.resources.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.dictionary.dao.DipDictionaryMapper;
import com.zebone.dip.md.vo.NameCode;
import com.zebone.dip.resources.dao.ResourcesManageMapper;
import com.zebone.dip.resources.service.ResourcesManageService;
import com.zebone.dip.resources.vo.MdMedicalOrg;
import com.zebone.dip.resources.vo.ResourceDept;
import com.zebone.dip.resources.vo.ResourceFamily;
import com.zebone.dip.resources.vo.ResourceFamilyMember;
import com.zebone.dip.resources.vo.ResourceUser;
import com.zebone.util.DateUtil;

@Service("resourcesManageService")
public class ResourcesManageServiceImpl implements ResourcesManageService {
	
	@Resource
	private ResourcesManageMapper resourcesManageMapper;
	@Resource
	private DipDictionaryMapper dipDictionaryMapper;

	/**
	 * 获取主数据中的医疗机构信息
	 * @return List<MdMedicalOrg>
	 */
	@Override
	public List<MdMedicalOrg> getMasterMedOrg() {
		List<MdMedicalOrg> list = resourcesManageMapper.getMasterMedOrg();
		return list;
	}

	/**
	 * 分页查询科室信息
	 */
	@Override
	public Pagination<ResourceDept> getDeptInfoPage(Pagination<ResourceDept> page, ResourceDept dept) {
		List<ResourceDept> list = resourcesManageMapper.getDeptInfoList(page.getRowBounds(),dept);
		int count = resourcesManageMapper.getDeptInfoCount(dept);
		page.setData(list);
		page.setTotalCount(count);
		return page;
	}

	/**
	 * 分页查询人员信息
	 */
	@Override
	public Pagination<ResourceUser> getUserInfoPage(Pagination<ResourceUser> page, ResourceUser user) {
		List<ResourceUser> list = resourcesManageMapper.getUserInfoList(page.getRowBounds(),user);
		int count = resourcesManageMapper.getUserInfoCount(user);
		page.setData(list);
		page.setTotalCount(count);
		return page;
	}

	/**
	 * 分页查询家庭信息
	 * @param page
	 * @param family
	 * @return
	 */
	@Override
	public Pagination<ResourceFamily> getFamilyInfoPage(Pagination<ResourceFamily> page, ResourceFamily family) {
		List<ResourceFamily> list = resourcesManageMapper.getFamilyInfoList(page.getRowBounds(),family);
		int count = resourcesManageMapper.getFamilyInfoCount(family);
		page.setData(list);
		page.setTotalCount(count);
		return page;
	}

	/**
	 * 通过机构和名称查询科室
	 * @param orgCode
	 * @param name
	 * @return
	 */
	@Override
	public List<ResourceDept> getDeptByOrgCode(String orgCode, String name) {
		List<ResourceDept> list = resourcesManageMapper.getDeptByOrgCode(orgCode,name);
		return list;
	}

	/**
	 * 家庭档案详细信息
	 * @param familyId
	 * @return
	 */
	@Override
	public ResourceFamily getFamilyInfoByFamilyId(String familyId) {
		ResourceFamily family = resourcesManageMapper.getFamilyInfoByFamilyId(familyId);
		//人口数
		int num = 0;
		int num2 = 0;
		if(family.getResidentPopuNumber()!=null&&family.getResidentPopuNumber()!=""){
			num = Integer.parseInt(family.getResidentPopuNumber());
		}
		if(family.getHousePopuNumber()!=null&&family.getHousePopuNumber()!=""){
			num2 = Integer.parseInt(family.getHousePopuNumber());
		}
		family.setResidentPopuNumber(String.valueOf(num+num2));
		//居住类型
		NameCode nameCode = dipDictionaryMapper.findDipDictionaryByTypeAndId("JXV00.00.005",family.getLiveType());
		if(nameCode!=null){
			family.setLiveType(nameCode.getName());
		}
		//住房性质
		NameCode nameCode2 = dipDictionaryMapper.findDipDictionaryByTypeAndId("JXV00.00.006",family.getHousingPropertyCode());
		if(nameCode2!=null){
			family.setHousingPropertyCode(nameCode2.getName());
		}
		//住房采光
		NameCode nameCode3 = dipDictionaryMapper.findDipDictionaryByTypeAndId("JXV00.00.007",family.getHousingLightingCode());
		if(nameCode3!=null){
			family.setHousingLightingCode(nameCode3.getName());
		}
		//房屋类型
		NameCode nameCode4 = dipDictionaryMapper.findDipDictionaryByTypeAndId("JXV00.00.008",family.getHousingCode());
		if(nameCode4!=null){
			family.setHousingCode(nameCode4.getName());
		}
		//厨房
		NameCode nameCode5 = dipDictionaryMapper.findDipDictionaryByTypeAndId("JXV00.00.009",family.getKitchenCode());
		if(nameCode5!=null){
			family.setKitchenCode(nameCode5.getName());
		}
		//厨房排风设施
		NameCode nameCode6 = dipDictionaryMapper.findDipDictionaryByTypeAndId("CV03.00.302",family.getKitchenVentCode());
		if(nameCode6!=null){
			family.setKitchenVentCode(nameCode6.getName());
		}
		//家庭属性代码
		NameCode nameCode7 = dipDictionaryMapper.findDipDictionaryByTypeAndId("JXV00.00.010",family.getFamilyProCode());
		if(nameCode7!=null){
			family.setFamilyProCode(nameCode7.getName());
		}
		//饮水
		NameCode nameCode8 = dipDictionaryMapper.findDipDictionaryByTypeAndId("JXV00.00.011",family.getWaterCode());
		if(nameCode8!=null){
			family.setWaterCode(nameCode8.getName());
		}
		//燃料
		NameCode nameCode9 = dipDictionaryMapper.findDipDictionaryByTypeAndId("JXV00.00.012",family.getFuelCode());
		if(nameCode9!=null){
			family.setFuelCode(nameCode9.getName());
		}
		//家庭厕所
		NameCode nameCode10 = dipDictionaryMapper.findDipDictionaryByTypeAndId("JXV00.00.013",family.getToiletsType());
		if(nameCode10!=null){
			family.setToiletsType(nameCode10.getName());
		}
		//禽畜栏
		NameCode nameCode11 = dipDictionaryMapper.findDipDictionaryByTypeAndId("DE03.00.049.00",family.getLivestockCode());
		if(nameCode11!=null){
			family.setLivestockCode(nameCode11.getName());
		}
		//家用电器
		NameCode nameCode12 = dipDictionaryMapper.findDipDictionaryByTypeAndId("EXV00.00.009",family.getElecDeviceCode());
		if(nameCode12!=null){
			family.setElecDeviceCode(nameCode12.getName());
		}
		//交通工具
		NameCode nameCode13 = dipDictionaryMapper.findDipDictionaryByTypeAndId("JXV00.00.014",family.getVehicleCode());
		if(nameCode13!=null){
			family.setVehicleCode(nameCode13.getName());
		}
		//经济状况
		NameCode nameCode14 = dipDictionaryMapper.findDipDictionaryByTypeAndId("JXV00.00.015",family.getEconStatusCode());
		if(nameCode14!=null){
			family.setEconStatusCode(nameCode14.getName());
		}
		//人均月收入
		NameCode nameCode15 = dipDictionaryMapper.findDipDictionaryByTypeAndId("JXV00.00.016",family.getPerMonthIncomeCode());
		if(nameCode15!=null){
			family.setPerMonthIncomeCode(nameCode15.getName());
		}
		//垃圾处理
		NameCode nameCode16 = dipDictionaryMapper.findDipDictionaryByTypeAndId("EXV00.00.008",family.getGarDisposalCode());
		if(nameCode16!=null){
			family.setGarDisposalCode(nameCode16.getName());
		}
		//污水处理
		NameCode nameCode17 = dipDictionaryMapper.findDipDictionaryByTypeAndId("JXV00.00.017",family.getSewageDiposalCode());
		if(nameCode17!=null){
			family.setSewageDiposalCode(nameCode17.getName());
		}
		//文体设备（多选）
		if(family.getCultSportCode()!=null){
			String[] strs = family.getCultSportCode().split(",");
			String name = "";
			for(String str : strs){
				NameCode nameCode18 = dipDictionaryMapper.findDipDictionaryByTypeAndId("JXV00.00.018",str);
				if(nameCode18!=null){
					name +=nameCode18.getName()+",";
				}
			}
			if(name.length()>1){
				name = name.substring(0, name.length()-1);
			}
			family.setCultSportCode(name);
		}
		
		//家庭类型代码
		NameCode nameCode19 = dipDictionaryMapper.findDipDictionaryByTypeAndId("JXV00.00.019",family.getFamilyTypeCode());
		if(nameCode19!=null){
			family.setFamilyTypeCode(nameCode19.getName());
		}
		//日期转换
		if(family.getFileDate()!=null){
			family.setFileDate(DateUtil.format(family.getFileDate(), "yyyyMMdd", "yyyy-MM-dd"));
		}
		if(family.getInputDate()!=null){
			family.setInputDate(DateUtil.format(family.getInputDate(), "yyyyMMdd", "yyyy-MM-dd"));
		}
		//责任医生
		MdMedicalOrg mmo = resourcesManageMapper.getFamilyDoctorByOrgAndCode(family.getFamilyOrgCode(),family.getDoctorCode());
		if(mmo!=null){
			family.setDoctorCode(mmo.getOrgName());
		}
		//建档医生
		MdMedicalOrg mmo2 = resourcesManageMapper.getFamilyDoctorByOrgAndCode(family.getFamilyOrgCode(),family.getFileDoctorCode());
		if(mmo2!=null){
			family.setFileDoctorCode(mmo2.getOrgName());
		}
		//登记人
		MdMedicalOrg mmo3 = resourcesManageMapper.getFamilyDoctorByOrgAndCode(family.getFamilyOrgCode(),family.getInputUserCode());
		if(mmo3!=null){
			family.setInputUserCode(mmo3.getOrgName());
		}
		//家庭地址
		MdMedicalOrg mmo4 = resourcesManageMapper.getMasterAdmOrg(family.getFamilyAddrCode());
		if(mmo4!=null){
			family.setFamilyAddrCode(mmo4.getOrgName());
		}
		return family;
	}

	/**
	 * 查询家庭成员信息
	 * @param familyId
	 * @return
	 */
	@Override
	public List<ResourceFamilyMember> getFamilyMembers(String familyId) {
		List<ResourceFamilyMember> list = resourcesManageMapper.getFamilyMembers(familyId);
		int order = 1;
		if(list!=null&&list.size()>0){
			for(ResourceFamilyMember member : list){
				member.setOrderNumber(String.valueOf(order));
				order++;
				if(member.getBirthDate()!=null){
					member.setBirthDate(DateUtil.format(member.getBirthDate(), "yyyyMMdd", "yyyy-MM-dd"));
				}
			}
		}
		return list;
	}

	/**
	 * 通过机构名称查询机构
	 * LinBin
	 * Apr 22, 2014
	 * @param name
	 * @return
	 */
	@Override
	public List<MdMedicalOrg> getMasterMedOrgByName(String name) {
		List<MdMedicalOrg> list = resourcesManageMapper.getMasterMedOrgByName(name);
		return list;
	}

}
