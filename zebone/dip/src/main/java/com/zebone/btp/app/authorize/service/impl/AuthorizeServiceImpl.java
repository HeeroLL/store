package com.zebone.btp.app.authorize.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zebone.btp.app.authorize.dao.BtpModuleMhoMapper;
import com.zebone.btp.app.authorize.dao.BtpRoleModuleRMapper;
import com.zebone.btp.app.authorize.service.AuthorizeService;
import com.zebone.btp.app.authorize.vo.BtpModuleMho;
import com.zebone.btp.app.authorize.vo.BtpRoleModuleR;
import com.zebone.btp.app.authorize.vo.ModuleRoleVo;
import com.zebone.btp.app.module.dao.BtpModuleMapper;
import com.zebone.btp.app.module.vo.BtpModule;
import com.zebone.btp.app.role.dao.RoleMapper;
import com.zebone.btp.app.role.vo.Role;
/**
 * 授权管理的业务实现层
 * @author 蔡祥龙
 * 2012-11-24
 */
@Service("authorizeService")
public class AuthorizeServiceImpl implements AuthorizeService{
	@Resource
	private BtpRoleModuleRMapper btpRoleModuleRMapper;
	@Resource
	private BtpModuleMhoMapper btpModuleMhoMapper;
	@Resource
	private RoleMapper roleMapper;
	@Resource
	private BtpModuleMapper btpModuleMapper;
	/**
	 * 保存机构与模块的关系数据
	 * @author 蔡祥龙
	 * 2012-11-26
	 * @param btpModuleMho 机构与模块关系对象
	 * @param sId 删除相关角色的菜单参数
	 * @return 保存是否成功标志
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public int saveBtpModuleMho(BtpModuleMho btpModuleMho,String sId) {
		if(!StringUtils.isEmpty(sId)){
			String[] sIds = sId.split(",");
			for(String str : sIds){
				BtpModuleMho s = new BtpModuleMho();
				s.setMhoId(btpModuleMho.getMhoId());
				s.setModuleId(str);
				btpModuleMhoMapper.deleteModuleRoleByBtpModuleMho(s);
			}
		}
		btpModuleMhoMapper.deleteModuleMhoByOrganId(btpModuleMho.getMhoId());
		if(btpModuleMho.getMhoId() != null && !"".equals(btpModuleMho.getMhoId())){
			String[] moduleIds = btpModuleMho.getModuleId().split(",");
			List<BtpModuleMho> list = new ArrayList<BtpModuleMho>();
			for(String moduleId : moduleIds){
				if(!StringUtils.isEmpty(moduleId)){
					BtpModuleMho bmm = new BtpModuleMho();
					bmm.setCreatorId(btpModuleMho.getCreatorId());
					bmm.setMhoId(btpModuleMho.getMhoId());
					bmm.setModuleId(moduleId);
					list.add(bmm);
				}
			}
			if(list.size()>0) {
				btpModuleMhoMapper.insertModuleMhos(list);
			}
		}
		return 1;
	}
	/**
	 * 保存角色与模块的关系数据
	 * @author 蔡祥龙
	 * 2012-11-26
	 * @param btpRoleModuleR 角色与模块关系对象
	 * @return 保存是否成功标志
	 */
	@Transactional(propagation=Propagation.REQUIRED,noRollbackFor=Exception.class)
	@Override
	public int saveBtpRoleModuleR(BtpRoleModuleR btpRoleModuleR) {
		btpRoleModuleRMapper.deleteBtpRoleModuleRByRoleId(btpRoleModuleR.getRoleId());
		if(btpRoleModuleR.getRoleId() != null && !"".equals(btpRoleModuleR.getRoleId())){
			String[] moduleIds = btpRoleModuleR.getModuleId().split(",");
			List<BtpRoleModuleR> list = new ArrayList<BtpRoleModuleR>();
			for(String moduleId : moduleIds){
				if(!StringUtils.isEmpty(moduleId)){
					BtpRoleModuleR brmr = new BtpRoleModuleR();
					brmr.setCreatorId(btpRoleModuleR.getCreatorId());
					brmr.setRoleId(btpRoleModuleR.getRoleId());
					brmr.setModuleId(moduleId);
					list.add(brmr);
				}
			}
			if(list.size()>0) {
				btpRoleModuleRMapper.insertBtpRoleModuleRs(list);
			}
		}
		return 1;
	}
	/**
	 * @author 蔡祥龙
	 * @date 2012-11-30
	 * @description 根据医疗机构id 获取模块信息
	 * @param mhoId 医疗机构id
	 * @return List<BtpModuleMho> 模块与医疗机构的关系对象
	 */
	@Override
	public List<BtpModuleMho> getModuleByMhoId(String mhoId) {
		return btpModuleMhoMapper.getModuleByMhoId(mhoId);
	}
	/**
	 * @author 蔡祥龙
	 * @date 2012-11-30
	 * @description 根据医疗机构id获取模块和角色信息
	 * @param mhoId 医疗机构id
	 * @return ModuleRoleDto 角色与模块的集合对象
	 */
	@Override
	public ModuleRoleVo getModuleRoleByMhoId(String mhoId) {
		Role role = new Role();
		role.setMedicalOrganId(mhoId);
		List<Role> list1 = roleMapper.SearchRoleByRole(role);
		List<BtpModule> list2 = btpModuleMapper.getModuleByMhoId(mhoId);
		ModuleRoleVo moduleRoleDto = new ModuleRoleVo();
		moduleRoleDto.setBtpModules(list2);
		moduleRoleDto.setRoles(list1);
		return moduleRoleDto;
	}
	/**
	 * @author caixianglong
	 * @date 2012-11-30
	 * @description 根据角色id 获取模块相关信息
	 * @param roleId 角色id
	 * @return List<BtpRoleModuleR> 角色关系对象
	 */
	@Override
	public List<BtpRoleModuleR> getModuleByRoleId(String roleId) {
		return btpRoleModuleRMapper.getModuleByRoleId(roleId);
	}
}
