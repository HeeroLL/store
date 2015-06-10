package com.zebone.btp.app.personnel.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zebone.btp.app.personnel.dao.PersonnelMapper;
import com.zebone.btp.app.personnel.service.PersonnelService;
import com.zebone.btp.app.personnel.vo.Personnel;
import com.zebone.btp.app.personnel.vo.PersonnelMhoR;
import com.zebone.btp.app.role.dao.RoleMapper;
import com.zebone.btp.app.role.vo.Role;
import com.zebone.btp.app.role.vo.RoleAccountR;
import com.zebone.btp.core.util.Pagination;
/**
 * 工作人员信息业务层
 * @author LinBin
 * 2012-11-24
 */
@Service("personnelService")
public class PersonnelServiceImpl implements PersonnelService {
	
	@Resource
	private PersonnelMapper personnelMapper;
	@Resource
	private RoleMapper roleMapper;

	/**
	 * 通过医疗机构id获得该结构下的工作人员，结果不包括下属机构的工作人员
	 * @author LinBin
	 * 2012-11-24
	 * @param mhoId
	 * @return List<PersonnelMhoR>
	 */
	@Override
	public List<PersonnelMhoR> findPersonByMhoId(String mhoId) {
		return personnelMapper.findPersonByMhoId(mhoId);
	}

	/**
	 * 分页查询工作人员，包含各种条件。结果不包括下属机构的工作人员
	 * @author LinBin
	 * 2012-11-24
	 * @param PersonnelMhoR
	 * @return List<PersonnelMhoR>
	 */
	@Override
	public Pagination<PersonnelMhoR> personnelQueryPage(Pagination<PersonnelMhoR> page,
			PersonnelMhoR personnelMhoR) {
		List<PersonnelMhoR> list =personnelMapper.findPersonByPersonMhoR(page.getRowBounds(),personnelMhoR);
		int count = personnelMapper.findPersonCountByPersonMhoR(personnelMhoR);
		page.setData(list);
		page.setTotalCount(count);
		return page;
	}

	/**
	 * 保存工作人员基本信息
	 * @author LinBin
	 * 2012-11-24
	 * @param Personnel
	 * @return boolean
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean savePersonnel(Personnel personnel) {
		List<PersonnelMhoR> personnelMhoRs = personnel.getPersonnelMhoRs();
		List<RoleAccountR> roleAccountRs = personnel.getRoleAccountRs();
		boolean bool = false;
		int result = 0;
		//保存工作人员基本信息
		result = personnelMapper.savePersonnel(personnel);
		
		//保存工作人员与角色的关系
		if(roleAccountRs!=null){
			for(RoleAccountR roleAccountR : roleAccountRs){
				result = personnelMapper.saveRoleAccountR(roleAccountR);
			}
		}
		
		//保存工作人员与医疗机构的关系
		if(personnelMhoRs!=null){
			for(PersonnelMhoR personnelMhoR : personnelMhoRs){
				result = personnelMapper.savePersonnelMhoR(personnelMhoR);
			}
		}
		
		if(result>0){
			bool = true;
		}
		return bool;
	}

	/**
	 * 更新工作人员基本信息
	 * @author LinBin
	 * 2012-11-24
	 * @param Personnel
	 * @return boolean
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean updatePersonnel(Personnel personnel) {
		List<PersonnelMhoR> personnelMhoRs = personnel.getPersonnelMhoRs();
		List<RoleAccountR> roleAccountRs = personnel.getRoleAccountRs();
		boolean bool = false;
		int result = 0;
		
		//更新工作人员基本信息
		result = personnelMapper.updatePersonnelById(personnel);
		List<String> list = new ArrayList<String>();
		list.add(personnel.getPersonnelId());
		
		if(roleAccountRs!=null){
			//人员与角色的关系，一对多的关系，先删除后保存
			result = personnelMapper.deleteAccountRoleRByPersonnelId(list);
			//保存工作人员与角色的关系
			for(RoleAccountR roleAccountR : roleAccountRs){
				result = personnelMapper.saveRoleAccountR(roleAccountR);
			}
		}
		
		if(personnelMhoRs!=null){
			//人员与医疗机构的关系，一对多的关系，先删除后保存
			result = personnelMapper.deletePersonnelMhoRByPersonnelId(list);
			//保存工作人员与医疗机构的关系
			for(PersonnelMhoR personnelMhoR : personnelMhoRs){
				result = personnelMapper.savePersonnelMhoR(personnelMhoR);
			}
		}
		
		if(result>0){
			bool = true;
		}
		return bool;
	}
	
	/**
	 * 根据工作人员id逻辑删除医疗工作人员
	 * @author LinBin
	 * 2012-11-24
	 * @param personnelIds
	 * @return boolean
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean deletePersonnel(List<String> personnelIds) {
		boolean bool = false;
		int result = 0;
		//删除医疗机构与人员的关系
		result = personnelMapper.deletePersonnelMhoRByPersonnelId(personnelIds);
		//删除角色与人员的关系
		result = personnelMapper.deleteAccountRoleRByPersonnelId(personnelIds);
		//删除人员基本信息
		result = personnelMapper.deletePersonnelByPersonnelId(personnelIds);
		if(result>0){
			bool = true;
		}
		return bool;
	}

	/**
	 * 根据工作人员ID查询医疗工作人员具体信息,包括角色，和所属医疗机构
	 * @author LinBin
	 * 2012-11-26
	 * @param personnelId
	 * @return Personnel
	 */
	@Override
	@Deprecated
	public Personnel getPersonnelInfoById(String personnelId){
		//查询医疗人员基本信息
		Personnel personnel = personnelMapper.getPersonnelInfo(personnelId);
		if(personnel!=null){
			//查询医疗机构与人员关系
			List<PersonnelMhoR> personnelMhoRs = personnelMapper.getPersonnelMhoRInfo(personnelId);
			personnel.setPersonnelMhoRs(personnelMhoRs);
			//查询已经拥有的角色
			List<Role> roles = roleMapper.getPersonnelRolesById(personnelId);
			if(roles!=null&&roles.size()>0){
				for(Role o : roles){
					o.setName(o.getName()+"("+o.getMhoName()+")");
					o.setRoleId(o.getRoleId()+":"+o.getMedicalOrganId());
				}
			}
			personnel.setRoles(roles);
			//查询所属医疗机构拥有的所有角色
			List<String> mhoIds = new ArrayList<String>();
			if(personnelMhoRs!=null&&personnelMhoRs.size()>0){
				for(PersonnelMhoR o : personnelMhoRs){
					mhoIds.add(o.getMhoId());
				}
			}
			List<Role> allRoles = roleMapper.getRoles(mhoIds);
			//找出不属于这个人的其他角色
			List<Role> otherRoles = new ArrayList<Role>();
			if(allRoles!=null&&allRoles.size()>0){
				for(Role allRole : allRoles){
					allRole.setName(allRole.getName()+"("+allRole.getMhoName()+")");
					allRole.setRoleId(allRole.getRoleId()+":"+allRole.getMedicalOrganId());
					int flag = 0;
					for(Role role : roles){
						if(allRole.getRoleId().equals(role.getRoleId())){
							flag = 1;
							break;
						}
					}
					if(flag==0){
						otherRoles.add(allRole);
					}
				}
			}
			personnel.setOtherRoles(otherRoles);
		}
		return personnel;
	}
	
	/**
	 * 根据登录名返回医疗人员信息。只查询为删除的和没有停用的
	 * @param loginName 登录名
	 * @return 医疗人员信息
	 * @author 宋俊杰
	 */
	public Personnel getPersonnelByLoginName(String loginName){
		Personnel personnel = this.personnelMapper.getPersonnelByLoginName(loginName);
		return personnel;
	}
	
	/**
	 * 根据工作人员id查询工作人员与医疗机构关系信息
	 * @param personnelId
	 * @return
	 * @author 宋俊杰
	 */
	public  List<PersonnelMhoR> getPersonnelMhoRInfo(String personnelId){
		return this.personnelMapper.getPersonnelMhoRInfo(personnelId);
		
	}

	/**
	 * 账号停用启用转换
	 * LinBin
	 * Nov 29, 2012
	 * @param list
	 * @return boolean
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean isAccountEnableModify(List<Personnel> list) {
		boolean bool = false;
		int result = 0;
		for(Personnel personnel : list){
			result = personnelMapper.isAccountEnableModify(personnel);
		}
		if(result>0){
			bool = true;
		}
		return bool;
	}

	/**
	 * 校验账号是否存在
	 * LinBin
	 * Nov 29, 2012
	 * @param loginName
	 * @param personnelId
	 * @return boolean  true 账号已经存在  false 账号不存在
	 */
	@Override
	public boolean checkLoginName(String loginName,String personnelId) {
		boolean bool = false;
		Personnel personnel = personnelMapper.checkLoginName(loginName);
		if(StringUtils.isEmpty(personnelId)){
			//新建时，已经存在账号
			if(personnel!=null){
				bool = true;
			}
		}else{
			//更新时，除了自己还有相同的账号，账号已经存在
			if(personnel!=null){
				if(!personnelId.equals(personnel.getPersonnelId())){
					bool = true;
				}
			}
			
		}
		return bool;
	}

	/**
	 * 初始化密码
	 * LinBin
	 * Nov 30, 2012
	 * @param list
	 * @return boolean
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean passwordInitialization(List<Personnel> list) {
		boolean bool = false;
		int result = 0;
		for(Personnel personnel : list){
			result = personnelMapper.passwordInitialization(personnel);
		}
		if(result>0){
			bool = true;
		}
		return bool;
	}
	
	/**
	 * 个人基本信息，根据工作人员id查询医疗工作人员具体信息(只查基本信息，不包括关联机构，角色)
	 * @author LinBin
	 * 2012-12-10
	 * @param personnelId
	 * @return Personnel
	 */
	@Override
	public Personnel getPersonnelInfoOnly(String personnelId){
		//查询医疗人员基本信息
		Personnel personnel = personnelMapper.getPersonnelInfo(personnelId);
		return personnel;
	}

	/**
	 * 个人信息修改表单页面,信息保存
	 * LinBin
	 * Dec 10, 2012
	 * @param personnel
	 * @return boolean
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean saveOneselfModify(Personnel personnel) {
		boolean bool = false;
		int result = 0;
		//更新工作人员基本信息
		result = personnelMapper.saveOneselfModify(personnel);
		if(result>0){
			bool = true;
		}
		return bool;
	}

	/**
	 * @see com.zebone.btp.app.personnel.service.PersonnelService#updatePersonnelSkin(java.lang.String, java.lang.String)
	 */
	@Override
	public void updatePersonnelSkin(String skin, String personnelId) {
		this.personnelMapper.updatePersonnelSkin(skin, personnelId);
	}

	/* (non-Javadoc)
	 * @see com.zebone.btp.app.personnel.service.PersonnelService#getPersonList(java.lang.String)
	 */
	@Override
	public List<PersonnelMhoR> getPersonList(String levelCode) {
		// TODO Auto-generated method stub
		return this.personnelMapper.getPersonList(levelCode);
	}
}
