package com.zebone.dip.resources.service;

import java.util.List;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.resources.vo.MdMedicalOrg;
import com.zebone.dip.resources.vo.ResourceDept;
import com.zebone.dip.resources.vo.ResourceFamily;
import com.zebone.dip.resources.vo.ResourceFamilyMember;
import com.zebone.dip.resources.vo.ResourceUser;

public interface ResourcesManageService {

	/**
	 * 获取主数据中的医疗机构信息
	 * @return List<MdMedicalOrg>
	 */
	List<MdMedicalOrg> getMasterMedOrg();

	/**
	 * 分页查询科室信息
	 * @param page
	 * @param nameOrCode
	 * @return
	 */
	Pagination<ResourceDept> getDeptInfoPage(Pagination<ResourceDept> page,ResourceDept dept);

	/**
	 * 分页查询人员信息
	 * @param page
	 * @param user
	 * @return
	 */
	Pagination<ResourceUser> getUserInfoPage(Pagination<ResourceUser> page,ResourceUser user);

	/**
	 * 分页查询家庭信息
	 * @param page
	 * @param family
	 * @return
	 */
	Pagination<ResourceFamily> getFamilyInfoPage(Pagination<ResourceFamily> page, ResourceFamily family);

	/**
	 * 通过机构和名称查询科室
	 * @param orgCode
	 * @param name
	 * @return
	 */
	List<ResourceDept> getDeptByOrgCode(String orgCode, String name);

	/**
	 * 家庭档案详细信息
	 * @param familyId
	 * @return
	 */
	ResourceFamily getFamilyInfoByFamilyId(String familyId);

	/**
	 * 查询家庭成员信息
	 * @param familyId
	 * @return
	 */
	List<ResourceFamilyMember> getFamilyMembers(String familyId);

	/**
	 * 通过机构名称查询机构
	 * LinBin
	 * Apr 22, 2014
	 * @param name
	 * @return
	 */
	List<MdMedicalOrg> getMasterMedOrgByName(String name);

}
