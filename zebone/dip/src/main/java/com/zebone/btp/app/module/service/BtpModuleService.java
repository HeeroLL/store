package com.zebone.btp.app.module.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.app.module.vo.BtpModule;
import com.zebone.btp.core.util.Pagination;
/**
 * 模块管理的业务接口层
 * @author 蔡祥龙
 * 2012-11-23
 */
public interface BtpModuleService {
	/**
	 * 保存菜单相关信息
	 * @author 蔡祥龙
	 * 2012-11-23
	 * @param btpModule 模块对象
	 * @return 保存是否成功标志
	 */
	int saveBtpModuleInfo(BtpModule btpModule);
	/**
	 * 更新菜单相关信息
	 * @author 蔡祥龙
	 * 2012-11-23
	 * @param btpModule 模块对象
	 * @return 更新是否成功标志
	 */
	int updateBtpModuleInfo(BtpModule btpModule);
	/**
	 * 根据模块id获取该模块的信息
	 * @author 蔡祥龙
	 * 2012-11-23
	 * @param moduleId 模块id
	 * @return 模块对象
	 */
	BtpModule getBtpModuleById(String moduleId);
	/**
	 * 根据条件查询模块列表
	 * @author 蔡祥龙
	 * 2011-11-23
	 * @param btpModule 查询条件
	 * @return 模块列表信息
	 */
	Pagination<BtpModule> searchBtpModule(BtpModule btpModule);
	/**
	 * 获取所有的模块的相关信息
	 * @author 蔡祥龙
	 * 2012-11-26
	 * @return 模块信息的集合
	 */
	List<BtpModule> getBtpModuleInfos();
	/**
	 * @author caixianglong
	 * @date 2012-11-28
	 * @description 删除模块相关信息
	 * @param moduleId 菜单主键
	 * @return int 删除是否成功标志
	 */
	int removeModuleInfo(String moduleId);
	/**
	 * @author 蔡祥龙
	 * @date 2012-11-29
	 * @description 模块排序
	 * @param btpModule 排序的相关参数
	 * @return int 排序成功标志
	 */
	int moduleOrderByorderNo(BtpModule btpModule);
	
	/**
	 * 得到一个父模块下面的所有子模块。
	 * @param parentId 如果为空，则查询出所有的顶级模块。
	 * @return
	 */
	List<BtpModule> getModuleByParentId(String parentId);
	
	/**
	 * 根据角色id 得到顶级的模块列表。
	 * @param roleIdList id列表.注意：如果为null，查询所有的。
	 * @param parentId 父模块id，如果为1000，查询顶级模块
	 * @return
	 * @author 宋俊杰
	 */
	public List<BtpModule> getModuleByRoleIdAndParentId(List<String> roleIdList,String parentId);
}
