package com.zebone.btp.app.personnel.service;

import java.util.List;
import com.zebone.btp.app.personnel.vo.Personnel;
import com.zebone.btp.app.personnel.vo.PersonnelMhoR;
import com.zebone.btp.core.util.Pagination;
/**
 * 工作人员信息业务层接口
 * @author LinBin
 * 2012-11-24
 */
public interface PersonnelService {

	/**
	 * 通过医疗机构id获得该结构下的工作人员，结果不包括下属机构的工作人员
	 * @author LinBin
	 * 2012-11-24
	 * @param mhoId
	 * @return List<PersonnelMhoR>
	 */
	List<PersonnelMhoR> findPersonByMhoId(String mhoId);
	
	/**
	 * 分页查询工作人员，包含各种条件。结果不包括下属机构的工作人员
	 * @author LinBin
	 * 2012-11-24
	 * @param PersonnelMhoR
	 * @return List<PersonnelMhoR>
	 */
	Pagination<PersonnelMhoR> personnelQueryPage(Pagination<PersonnelMhoR> page,PersonnelMhoR personnelMhoR);
	
	/**
	 * 保存工作人员基本信息
	 * @author LinBin
	 * 2012-11-24
	 * @param Personnel
	 * @return boolean
	 */
	boolean savePersonnel(Personnel personnel);
	
	/**
	 * 更新工作人员基本信息
	 * @author LinBin
	 * 2012-11-24
	 * @param Personnel
	 * @return boolean
	 */
	boolean updatePersonnel(Personnel personnel);
	
	/**
	 * 根据工作人员id逻辑删除医疗工作人员
	 * @author LinBin
	 * 2012-11-24
	 * @param personnelIds
	 * @return boolean
	 */
	boolean deletePersonnel(List<String> personnelIds);
	
	/**
	 * 根据工作人员ID查询医疗工作人员具体信息,包括角色，和所属医疗机构
	 * @author LinBin
	 * 2012-11-26
	 * @param personnelId
	 * @return Personnel
	 */
	@Deprecated
	Personnel getPersonnelInfoById(String personnelId);
	
	/**
	 * 根据登录名返回医疗人员信息。只查询为删除的和没有停用的
	 * @param loginName 登录名
	 * @return 医疗人员信息
	 * @author 宋俊杰
	 */
	public Personnel getPersonnelByLoginName(String loginName);
	
	/**
	 * 根据工作人员id查询工作人员与医疗机构关系信息 
	 * @param personnelId
	 * @return
	 * @author 宋俊杰
	 */
	public  List<PersonnelMhoR> getPersonnelMhoRInfo(String personnelId);

	/**
	 * 账号停用启用转换
	 * LinBin
	 * TODO
	 * Nov 29, 2012
	 * @param list
	 * @return boolean
	 */
	boolean isAccountEnableModify(List<Personnel> list);

	/**
	 * 校验账号是否存在
	 * LinBin
	 * TODO
	 * Nov 29, 2012
	 * @param loginName
	 * @param personnelId
	 * @return boolean
	 */
	boolean checkLoginName(String loginName,String personnelId);

	/**
	 * 初始化密码
	 * LinBin
	 * Nov 30, 2012
	 * @param list
	 * @return boolean
	 */
	boolean passwordInitialization(List<Personnel> list);

	/**
	 * 加载个人基本信息，根据工作人员id查询医疗工作人员具体信息(只查基本信息，不包括关联机构，角色)
	 * LinBin
	 * Dec 10, 2012
	 * @param personnelId
	 * @return Personnel
	 */
	Personnel getPersonnelInfoOnly(String personnelId);

	/**
	 * 个人信息修改表单页面,信息保存
	 * LinBin
	 * Dec 10, 2012
	 * @param personnel
	 * @return boolean
	 */
	boolean saveOneselfModify(Personnel personnel);
	
	/**
	 * 更新工作人员的桌面皮肤信息。
	 * @param skin 皮肤名称
	 * @param personnelId 人员id
	 * @return
	 */
	void updatePersonnelSkin(String skin , String personnelId);
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
