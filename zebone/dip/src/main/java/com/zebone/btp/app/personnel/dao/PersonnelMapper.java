package com.zebone.btp.app.personnel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import com.zebone.btp.app.personnel.vo.Personnel;
import com.zebone.btp.app.personnel.vo.PersonnelMhoR;
import com.zebone.btp.app.role.vo.RoleAccountR;
import com.zebone.btp.core.mybatis.Mapper;
/**
 * 工作人员信息数据层
 * @author LinBin
 *
 */
@Mapper
public interface PersonnelMapper {
	
	/**
	 * 通过医疗机构id获得该结构下的工作人员，结果不包括下属机构的工作人员
	 * @author LinBin
	 * 2012-11-23
	 * @param mhoId
	 * @return List<PersonnelMhoR>
	 */
	List<PersonnelMhoR> findPersonByMhoId(String mhoId);
	
	/**
	 * 通过PersonnelMhoR对象获得工作人员，包含各种条件。结果不包括下属机构的工作人员
	 * @author LinBin
	 * 2012-11-23
	 * @param PersonnelMhoR
	 * @param rowBounds
	 * @return List<PersonnelMhoR>
	 */
	List<PersonnelMhoR> findPersonByPersonMhoR(RowBounds rowBounds,PersonnelMhoR personnelMhoR);
	
	/**
	 * 通过PersonnelMhoR对象获得工作人员总数，包含各种条件。结果不包括下属机构的工作人员
	 * @author LinBin
	 * 2012-11-23
	 * @param PersonnelMhoR
	 * @return int
	 */
	int findPersonCountByPersonMhoR(PersonnelMhoR personnelMhoR);
	
	/**
	 * 保存工作人员基本信息
	 * @author LinBin
	 * 2012-11-23
	 * @param Personnel
	 * @return int
	 */
	int savePersonnel(Personnel Personnel);
	
	/**
	 * 更新工作人员基本信息
	 * @author LinBin
	 * 2012-11-23
	 * @param Personnel
	 * @return int
	 */
	int updatePersonnelById(Personnel personnel);
	
	/**
	 * 保存机构与医疗人员的关系,批量插入
	 * @author LinBin
	 * 2012-11-23
	 * @param personnelMhoR
	 * @return int
	 */
	int savePersonnelMhoR(PersonnelMhoR personnelMhoR);
	
	/**
	 * 根据工作人员id物理删除机构与医疗人员的关系,批量删除
	 * @author LinBin
	 * 2012-11-23
	 * @param personnelId
	 * @return int
	 */
	int deletePersonnelMhoRByPersonnelId(List<String> personnelIds);
	
	/**
	 * 根据工作人员id逻辑删除医疗工作人员
	 * @author LinBin
	 * 2012-11-23
	 * @param personnelIds
	 * @return int
	 */
	int deletePersonnelByPersonnelId(List<String> personnelIds);
	
	/**
	 * 根据工作人员id删除角色与账号的关系
	 * @author LinBin
	 * 2012-11-23
	 * @param personnelIds
	 * @return int
	 */
	int deleteAccountRoleRByPersonnelId(List<String> personnelIds);
	
	/**
	 * 根据工作人员id查询具体信息
	 * @author LinBin
	 * 2012-11-26
	 * @param personnelId
	 * @return Personnel
	 */
	Personnel getPersonnelInfo(String personnelId);
	
	/**
	 * 根据工作人员id查询工作人员与医疗机构关系信息
	 * @author LinBin
	 * 2012-11-26
	 * @param personnelId
	 * @return List<PersonnelMhoR>
	 */
	List<PersonnelMhoR> getPersonnelMhoRInfo(String personnelId);
	
	/**
	 * 根据登录名返回医疗人员信息。 只查询为删除的和没有停用的
	 * @param loginName 登录名
	 * @return 医疗人员信息
	 * @author 宋俊杰
	 */
	Personnel getPersonnelByLoginName(String loginName);

	/**
	 * 账号停用启用转换
	 * LinBin
	 * TODO
	 * Nov 29, 2012
	 * @param list
	 * @return int
	 */
	int isAccountEnableModify(Personnel personnel);

	/**
	 * 校验账号是否存在
	 * LinBin
	 * TODO
	 * Nov 29, 2012
	 * @param loginName
	 * @return Personnel
	 */
	Personnel checkLoginName(String loginName);

	/**
	 * 初始化密码
	 * LinBin
	 * Nov 30, 2012
	 * @param personnel
	 * @return boolean
	 */
	int passwordInitialization(Personnel personnel);

	/**
	 * 保存工作人员与角色的关系
	 * LinBin
	 * Dec 8, 2012
	 * @param roleAccountR
	 * @return int
	 */
	int saveRoleAccountR(RoleAccountR roleAccountR);

	/**
	 * 个人信息修改表单页面,信息保存
	 * LinBin
	 * Dec 10, 2012
	 * @param personnel
	 * @return boolean
	 */
	int saveOneselfModify(Personnel personnel);
	
	/**
	 * 更新工作人员的桌面皮肤信息。
	 * @param skin 皮肤名称
	 * @param personnelId 人员id
	 * @return
	 */
	int updatePersonnelSkin(@Param("skin") String skin , @Param("personnelId")String personnelId);
    /**
     * 
     * @author fxl
     * @date 2012-12-27
     * @description 通过机构码验证机构下是否存在工作人员
     * @param levelCode
     * @return List<PersonnelMhoR>
     */
	List<PersonnelMhoR> getPersonList(String levelCode);
}