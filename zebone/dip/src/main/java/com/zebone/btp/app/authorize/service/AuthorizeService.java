package com.zebone.btp.app.authorize.service;

import java.util.List;

import com.zebone.btp.app.authorize.vo.BtpModuleMho;
import com.zebone.btp.app.authorize.vo.BtpRoleModuleR;
import com.zebone.btp.app.authorize.vo.ModuleRoleVo;

/**
 * 授权管理的业务接口层
 * @author 蔡祥龙
 * 2012-11-24
 */
public interface AuthorizeService {

	/**
	 * 保存机构与模块的关系数据
	 * @author 蔡祥龙
	 * 2012-11-26
	 * @param btpModuleMho 机构与模块关系对象
	 * @param sId 删除相关角色的菜单参数
	 * @return 保存是否成功标志
	 */
	int saveBtpModuleMho(BtpModuleMho btpModuleMho,String sId);
	/**
	 * 保存角色与模块的关系数据
	 * @author 蔡祥龙
	 * 2012-11-26
	 * @param btpRoleModuleR 角色与模块关系对象
	 * @return 保存是否成功标志
	 */
	int saveBtpRoleModuleR(BtpRoleModuleR btpRoleModuleR);
	/**
	 * @author 蔡祥龙
	 * @date 2012-11-30
	 * @description 根据医疗机构id 获取模块信息
	 * @param mhoId 医疗机构id
	 * @return List<BtpModuleMho> 模块与医疗机构的关系对象
	 */
	List<BtpModuleMho> getModuleByMhoId(String mhoId);
	/**
	 * @author 蔡祥龙
	 * @date 2012-11-30
	 * @description 根据医疗机构id获取模块和角色信息
	 * @param mhoId 医疗机构id
	 * @return ModuleRoleDto 角色与模块的集合对象
	 */
	ModuleRoleVo getModuleRoleByMhoId(String mhoId);
	/**
	 * @author caixianglong
	 * @date 2012-11-30
	 * @description 根据角色id 获取模块相关信息
	 * @param roleId 角色id
	 * @return List<BtpRoleModuleR> 角色关系对象
	 */
	List<BtpRoleModuleR> getModuleByRoleId(String roleId);
}
