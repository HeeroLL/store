package com.zebone.btp.app.module.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zebone.btp.app.authorize.dao.BtpModuleMhoMapper;
import com.zebone.btp.app.authorize.dao.BtpRoleModuleRMapper;
import com.zebone.btp.app.authorize.vo.BtpModuleMho;
import com.zebone.btp.app.authorize.vo.BtpRoleModuleR;
import com.zebone.btp.app.module.dao.BtpModuleMapper;
import com.zebone.btp.app.module.service.BtpModuleService;
import com.zebone.btp.app.module.vo.BtpModule;
import com.zebone.btp.core.util.ChineseToPinYin;
import com.zebone.btp.core.util.Pagination;
/**
 * 模块管理的业务实现层
 * @author 蔡祥龙
 * 2012-11-23
 */
@Service("btpModuleService")
public class BtpModuleServiceImpl implements BtpModuleService {
	@Resource
	private BtpModuleMapper btpModuleMapper;
	@Resource
	private BtpModuleMhoMapper btpModuleMhoMapper;
	@Resource
	private BtpRoleModuleRMapper btpRoleModuleRMapper;

	/**
	 * 保存菜单相关信息
	 * @author 蔡祥龙
	 * 2012-11-23
	 * @param btpModule 模块对象
	 * @return 保存是否成功标志
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int saveBtpModuleInfo(BtpModule btpModule) {
		List<BtpModule> list = btpModuleMapper.getOrderNoByPModuleId(btpModule.getParentModuleId());
		if(list!=null && list.size()>0){
			Integer str = list.get(0).getOrderNo();
			btpModule.setOrderNo(str + 1);
		}else{
			btpModule.setOrderNo(1);
		}
		String moduleNameSpell = ChineseToPinYin.chineseToPinyin(btpModule.getModuleName());
		btpModule.setModuleNameSpell(moduleNameSpell);
		return btpModuleMapper.insert(btpModule);
	}

	/**
	 * 更新菜单相关信息
	 * @author 蔡祥龙
	 * 2012-11-23
	 * @param btpModule 模块对象
	 * @return 更新是否成功标志
	 */
	@Override
	public int updateBtpModuleInfo(BtpModule btpModule) {
		return btpModuleMapper.updateById(btpModule);
	}

	/**
	 * 根据模块id获取该模块的信息
	 * @author 蔡祥龙
	 * 2012-11-23
	 * @param moduleId 模块id
	 * @return 模块对象
	 */
	@Override
	public BtpModule getBtpModuleById(String moduleId) {
		return btpModuleMapper.findById(moduleId);
	}

	/**
	 * 根据条件查询模块列表
	 * @author 蔡祥龙
	 * 2011-11-27
	 * @param btpModule 查询条件
	 * @return 模块列表信息
	 */
	@Override
	public Pagination<BtpModule> searchBtpModule(BtpModule btpModule) {
		Pagination<BtpModule> page = new Pagination<BtpModule>();
		page.setData(btpModuleMapper.searchListBtpModule(btpModule));
		page.setTotalCount(btpModuleMapper.searchTotalCountBtpModule(btpModule));
		return page;
	}

	/**
	 * 获取所有的模块的相关信息
	 * @author 蔡祥龙
	 * 2012-11-26
	 * @return 模块信息的集合
	 */
	@Override
	public List<BtpModule> getBtpModuleInfos() {
		return btpModuleMapper.findAllBtpModuleInfo();
	}

	/**
	 * @author caixianglong
	 * @date 2012-11-28
	 * @description 删除模块相关信息
	 * @param moduleId 菜单主键
	 * @return int 删除是否成功标志
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int removeModuleInfo(String moduleId) {
		List<BtpModuleMho> list1 = btpModuleMhoMapper.getBtpModuleMhoByModuleId(moduleId);
		List<BtpRoleModuleR> list2 = btpRoleModuleRMapper.getBtpRoleModuleRByModuleId(moduleId);
		int result = 0;
		if(list1 != null && list1.size()>0){
			result = 2;
			if(list2 != null && list2.size()>0){
				result = 3;
			}
		}else{
			if(list2 != null && list2.size()>0){
				result = 4;
			}
		}
		if(result == 0){
			result = btpModuleMapper.removeBtpModuleByModuleId(moduleId);
		}
		return result;
	}

	/**
	 * @author 蔡祥龙
	 * @date 2012-11-29
	 * @description 模块排序
	 * @param btpModule 排序的相关参数
	 * @return int 排序成功标志
	 */
	@Override
	public int moduleOrderByorderNo(BtpModule btpModule) {
		if(btpModule.getTypeCode() == 1){
			btpModule.setOrderNo(btpModule.getOrderNo()-1);
		}
		List<BtpModule> list = btpModuleMapper.getModuleByOrderNo(btpModule);
		int mod = list.get(0).getOrderNo();
		list.get(0).setOrderNo(list.get(1).getOrderNo());
		list.get(1).setOrderNo(mod);
		btpModuleMapper.updateById(list.get(0));
		btpModuleMapper.updateById(list.get(1));
		return 1;
	}
	
	/**
	 * 得到一个父模块下面的所有子模块。
	 * @param parentId 如果为空，则查询出所有的顶级模块。
	 * @return
	 */
	public List<BtpModule> getModuleByParentId(String parentId){
		return this.btpModuleMapper.getModuleByParentId(parentId);
	}
	
	/**
	 * 根据角色id 得到顶级的模块列表。
	 * @param roleIdList id列表.如果为null，查询所有的。
	 * @param parentId 父模块id，如果为1000，查询顶级模块
	 * @return
	 * @author 宋俊杰
	 */
	public List<BtpModule> getModuleByRoleIdAndParentId(List<String> roleIdList,String parentId){
		return this.btpModuleMapper.getModuleByRoleIdAndParentId(roleIdList,parentId);
	}
}
