package com.zebone.btp.app.role.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zebone.btp.app.mho.service.MhoService;
import com.zebone.btp.app.mho.vo.Mho;
import com.zebone.btp.app.personnel.dao.PersonnelMapper;
import com.zebone.btp.app.role.dao.RoleMapper;
import com.zebone.btp.app.role.service.RoleService;
import com.zebone.btp.app.role.vo.Role;
import com.zebone.btp.core.util.Pagination;
/**
 * @author 杨柳
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Resource
	private RoleMapper roleMapper;
	@Resource
	private PersonnelMapper personnelMapper;
	@Autowired
	private MhoService mhoService;
	
	@Override
	public List<Role> SearchRoleByRole(Role role) {
		return roleMapper.SearchRoleByRole(role);
	}
	@Override 
	public int deleteById(String roleId) {
		return roleMapper.deleteById(roleId);
	}
	@Override
	public Role findById(String roleId) {
		Role role = roleMapper.findById(roleId);
		Mho mho = mhoService.findById(role.getMedicalOrganId());
		if(mho != null){
			String mhoName = mho.getMhoName();
			role.setTemp(mhoName);
		}
		return role;
	}
	@Override
	public int insert(Role record) {
		return roleMapper.insert(record);
	}
	@Override
	public int updateById(Role record) {
		return roleMapper.updateById(record);
	}
	@Override
	public Pagination<Role> pageRole(RowBounds rowBounds, String medicalOrganId) {
		//声明分页对象
		Pagination<Role> page = new Pagination<Role>();
		//分页查询所有的角色
		List<Role> list = roleMapper.pageRole(rowBounds, medicalOrganId);
		//遍历所有的角色
		for(int i=0;i<list.size();i++){
			//根据角色的creatorId 去查询工作人员信息 ，然后再把工作人员对应的 姓名 放到 role 对象的 creatorId 里面去
			list.get(i).setCreatorId(personnelMapper.getPersonnelInfo(list.get(i).getCreatorId()).getFullname());
		}
		//把list数据集合放到page分页组件中去
		page.setData(list);
		//查询 角色总数，放到page分页组件中去
		page.setTotalCount(roleMapper.totalCount(medicalOrganId));
		return page;
	}
	@Override
	public int logicDelete(String roleId) {
		return roleMapper.logicDelete(roleId);
	}
	
	/**
	 * 根据医疗人员id查询出此人的角色
	 * @param id
	 * @return
	 * @author 宋俊杰
	 */
	public List<Role> getPersonnelRolesById(String id){
		return this.roleMapper.getPersonnelRolesById(id);
	}
	
	/**
	 * 根据医疗机构id查询角色
	 * @param mhoIds
	 * @return List<Role>
	 * @author 林彬
	 */
	@Override
	public List<Role> getRoles(List<String> mhoIds){
		return this.roleMapper.getRoles(mhoIds);
	}
	/* (non-Javadoc)
	 * @see com.zebone.btp.app.role.service.RoleService#getRoleList(java.lang.String)
	 */
	@Override
	public List<Role> getRoleList(String levelCode) {
		// TODO Auto-generated method stub
		return this.roleMapper.getRoleList(levelCode);
	}

}
