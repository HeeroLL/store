package com.zebone.btp.app.authorize.dao;

import java.util.List;

import com.zebone.btp.app.authorize.vo.BtpRoleModuleR;
import com.zebone.btp.core.mybatis.Mapper;

/**
 * 模块与角色关系的数据访问层接口
 * @author 蔡祥龙
 * 2012-11-23
 */
@Mapper
public interface BtpRoleModuleRMapper {
	/**
	 * 删除某个角色所能操作的功能模块的关系数据
	 * @author 蔡祥龙
	 * 2012-11-26
	 * @param roleId 角色id
	 * @return 是否删除成功标志
	 */
	int deleteBtpRoleModuleRByRoleId(String roleId);
	/**
	 * 保存角色所能操作的功能模块的关系对象的集合
	 * @author 蔡祥龙
	 * 2012-11-26
	 * @param list 角色与模块关系对象的集合
	 * @return 保存是否成功标志
	 */
	int insertBtpRoleModuleRs(List<BtpRoleModuleR> list);
	/**
	 * 
	 * @author caixianglong
	 * @date 2012-11-28
	 * @description 根据模块id获取角色与模块关系对象的集合
	 * @param moduleId 模块id
	 * @return List<BtpRoleModuleR> 角色与模块关系对象的集合
	 */
	List<BtpRoleModuleR> getBtpRoleModuleRByModuleId(String moduleId);
	/**
	 * @author caixianglong
	 * @date 2012-11-30
	 * @description 根据角色id获取角色与模块关系的对象
	 * @param roleId 角色id
	 * @return List<BtpRoleModuleR> 角色与模块关系的对象
	 */
	List<BtpRoleModuleR> getModuleByRoleId(String roleId);
}