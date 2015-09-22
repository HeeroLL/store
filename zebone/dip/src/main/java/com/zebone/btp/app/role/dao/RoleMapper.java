package com.zebone.btp.app.role.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.app.role.vo.Role;
import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.btp.core.util.Pagination;
/**
 * 
 * @author 杨柳
 * 2012-11-24
 *
 */
@Mapper
public interface RoleMapper {
	/**
	 * 根据角色ID删除角色(物理删除--删除数据)
	 * @param roleId
	 * @return int
	 */
	int deleteById(String roleId);
	/**
	 * 逻辑删除，只是更新IS_DELETE为1
	 * @param roleId
	 * @return	int
	 */
	int logicDelete(String roleId);
	/**
	 * 新增一个角色
	 * @param record
	 * @return int
	 */
	int insert(Role record);
	/**
	 * 根据角色ID去查询角色
	 * @param roleId
	 * @return Role
	 */
	Role findById(String roleId);
	/**
	 * 根据角色的主键去更新角色
	 * @param record
	 * @return int
	 */
	int updateById(Role record);
	/**
	 * 根据角色的（name，isPublicRole，medicalOrganId）多个字段中的一个或几个参数去查询角色
	 * @param role
	 * @return List<Role> 
	 */
	public List<Role> SearchRoleByRole(Role role);
	/**
	 * 根据role对象去分页查询
	 * @param rowBounds
	 * @param role
	 * @return List<Role>
	 */
	public List<Role> pageRole(RowBounds rowBounds,@Param("medicalOrganId")String medicalOrganId);
	/**
	 * 查询总共有多少条数据
	 * @param role
	 * @return int
	 */
	public int totalCount(String medicalOrganId);
	
	/**
	 * 根据医疗人员id查询出此人的角色
	 * @param id
	 * @return
	 * @author 宋俊杰
	 */
	public List<Role> getPersonnelRolesById(String id);
	
	/**
	 * 根据医疗机构id查询角色
	 * @param mhoId
	 * @return List<Role>
	 * @author 林彬
	 */
	List<Role> getRoles(List<String> mhoIds);
	/**
	 * 
	 * @author fxl
	 * @date 2012-12-26
	 * @description 根据机构ID查询机构下角色
	 * @param mhoId
	 * @return List<Role>
	 */
	List<Role> getRoleList(String levelCode);
}