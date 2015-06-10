package com.zebone.btp.app.authorize.dao;

import java.util.List;

import com.zebone.btp.app.authorize.vo.BtpModuleMho;
import com.zebone.btp.core.mybatis.Mapper;

/**
 * 模块与机构关系的数据访问层
 * @author 蔡祥龙
 * 2012-11-23
 */
@Mapper
public interface BtpModuleMhoMapper {
	/**
	 * 根据医疗机构id删除与模块相关的关系数据
	 * @author 蔡祥龙
	 * 2012-11-26
	 * @param organId
	 * @return
	 */
	int deleteModuleMhoByOrganId(String organId);
	/**
	 * 保存某医疗机构下所能使用的功能模块的相关信息
	 * @author 蔡祥龙
	 * 2012-11-26
	 * @param list 医疗机构与功能模块的关系对象的集合
	 * @return 是否保存成功标志
	 */
	int insertModuleMhos(List<BtpModuleMho> list);
	/**
	 * 
	 * @author 蔡祥龙
	 * @date 2012-11-28
	 * @description 根据模块id获取模块与医疗机构关系对象的集合
	 * @param moduleId 模块id
	 * @return List<BtpModuleMho> 模块与医疗机构关系对象的集合
	 */
	List<BtpModuleMho> getBtpModuleMhoByModuleId(String moduleId);
	/**
	 * @author 蔡祥龙
	 * @date 2012-11-30
	 * @description 根据医疗机构id 获取模块信息
	 * @param mhoId 医疗机构id
	 * @return List<BtpModuleMho> 模块与医疗机构的关系对象
	 */
	List<BtpModuleMho> getModuleByMhoId(String mhoId);
	/**
	 * @author caixianglong
	 * @date 2012-12-08
	 * @description 删除角色与模块的关系
	 * @param s 机构与模块的关系
	 * @return int 删除是否成功标志
	 */
	int deleteModuleRoleByBtpModuleMho(BtpModuleMho s);
}