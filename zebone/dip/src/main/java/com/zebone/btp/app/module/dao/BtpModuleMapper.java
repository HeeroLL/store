package com.zebone.btp.app.module.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.app.module.vo.BtpModule;
import com.zebone.btp.core.mybatis.Mapper;
/**
 * 模块管理的数据访问层
 * @author 蔡祥龙
 * 2012-11-23
 */
@Mapper
public interface BtpModuleMapper {
	/**
	 * 保存模块对象信息
	 * @author 蔡祥龙
	 * 2012-11-24
	 * @param record 模块对象
	 * @return 是否保存成功标志
	 */
	int insert(BtpModule record);
	/**
	 * 根据模块id获取模块相关信息
	 * @author 蔡祥龙
	 * 2012-11-23
	 * @param moduleId 模块id
	 * @return 模块对象
	 */
	BtpModule findById(String moduleId);
	/**
	 * 更新模块相关信息
	 * @author 蔡祥龙
	 * 2012-11-23
	 * @param record 模块列表相关信息
	 * @return 更新是否成功
	 */
	int updateById(BtpModule record);
	/**
	 * 根据条件和页面参数查出相关模块的列表信息
	 * @author 蔡祥龙
	 * 2012-11-23
	 * @param btpModule 页面相关搜索条件
	 * @return 模块列表信息
	 */
	List<BtpModule> searchListBtpModule(BtpModule btpModule);
	/**
	 * 根据条件搜索出满足条件的模块的总数量
	 * @author 蔡祥龙
	 * 2012-11-23
	 * @param btpModule 页面相关搜索条件
	 * @return 满足的要求的模块总数
	 */
	int searchTotalCountBtpModule(BtpModule btpModule);
	/**
	 * 获取所有的模块的相关信息
	 * @author 蔡祥龙
	 * 2012-11-26
	 * @return 模块信息的集合
	 */
	List<BtpModule> findAllBtpModuleInfo();
	/**
	 * 
	 * @author caixianglong
	 * @date 2012-11-27
	 * @description 跟据父id获取相关模块的排序号
	 * @param parentModuleId 父id
	 * @return List<String> 排序号集合
	 */
	List<BtpModule> getOrderNoByPModuleId(String parentModuleId);
	/**
	 * @author caixl
	 * @date 2012-11-28
	 * @description 根据模块id逻辑删除相关模块的信息
	 * @param moduleId 模块id
	 * @return int 删除是否成功
	 */
	int removeBtpModuleByModuleId(String moduleId);
	/**
	 * @author 蔡祥龙
	 * @date 2012-11-29
	 * @description 获取需要排序的两个模块
	 * @param btpModule 条件参数
	 * @return List<BtpModule> 模块对象的集合
	 */
	List<BtpModule> getModuleByOrderNo(BtpModule btpModule);
	/**
	 * @author Administrator蔡祥龙
	 * @date 2012-11-30
	 * @description 根据医疗机构获取模块信息
	 * @param mhoId 医疗机构id
	 * @return List<BtpModule> 模块对象的集合
	 */
	List<BtpModule> getModuleByMhoId(String mhoId);
	
	/**
	 * 得到一个父模块下面的所有子模块。
	 * @param parentId 如果为空，则查询出所有的顶级模块。
	 * @return
	 * @author 宋俊杰
	 */
	List<BtpModule> getModuleByParentId(String parentId);
	
	/**
	 * 根据角色id 得到顶级的模块列表。
	 * @param roleIdList id列表
	 * @param parentId 父模块id，如果为1000，查询顶级模块
	 * @return
	 * @author 宋俊杰
	 */
	List<BtpModule> getModuleByRoleIdAndParentId(@Param("roleIdList")List<String> roleIdList,@Param("parentId")String parentId);
}