package com.zebone.btp.app.frame.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zebone.btp.app.frame.vo.ShortcutMenu;
import com.zebone.btp.core.mybatis.Mapper;

/**
 * 快捷方式dao
 * @author 宋俊杰
 *
 */
@Mapper
public interface ShortcutMenuMapper {
	
	/**
	 * 根据人员ID得到此人得到快捷方式
	 * @param personnelId 人员id
	 * @return
	 */
	public List<ShortcutMenu> getShortcutMenuByPersonnelId(String personnelId);

	/**
	 * 保存一个快捷方式
	 * @param shortcutMenu
	 */
	public void insert(ShortcutMenu shortcutMenu);

	/**
	 * 更新排序号。
	 * @param shortcutMenu 需要模块id，人员id和排序号三个参数
	 */
	public void updateOrderNo(ShortcutMenu shortcutMenu);
	
	/**
	 * 根据模块id和人员id删除快捷方式
	 * @param moduleId
	 * @param personnelId
	 */
	public void deleteByModuleIdAndPersonnelId(@Param("moduleId") String moduleId ,@Param("personnelId")String personnelId);
}
