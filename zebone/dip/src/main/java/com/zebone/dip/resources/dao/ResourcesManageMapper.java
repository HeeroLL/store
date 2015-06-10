package com.zebone.dip.resources.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.resources.vo.MdMedicalOrg;
import com.zebone.dip.resources.vo.ResourceDept;
import com.zebone.dip.resources.vo.ResourceFamily;
import com.zebone.dip.resources.vo.ResourceFamilyMember;
import com.zebone.dip.resources.vo.ResourceUser;

@Mapper
public interface ResourcesManageMapper {

	/**
	 * 获取主数据中的医疗机构信息
	 * @return List<MdMedicalOrg>
	 */
	List<MdMedicalOrg> getMasterMedOrg();

	List<ResourceDept> getDeptInfoList(RowBounds rowBounds, ResourceDept dept);

	int getDeptInfoCount(ResourceDept dept);

	List<ResourceUser> getUserInfoList(RowBounds rowBounds, ResourceUser user);

	int getUserInfoCount(ResourceUser user);

	ResourceDept getDeptInfoByCode(String departmentCode);

	List<ResourceFamily> getFamilyInfoList(RowBounds rowBounds,ResourceFamily family);

	int getFamilyInfoCount(ResourceFamily family);

	List<ResourceDept> getDeptByOrgCode(@Param("orgCode")String orgCode, @Param("name")String name);

	ResourceFamily getFamilyInfoByFamilyId(String familyId);

	List<ResourceFamilyMember> getFamilyMembers(String familyId);

	MdMedicalOrg getFamilyDoctorByOrgAndCode(@Param("orgCode")String orgCode, @Param("doctorCode")String doctorCode);

	MdMedicalOrg getMasterAdmOrg(String familyAddrCode);

	List<MdMedicalOrg> getMasterMedOrgByName(String name);

}
