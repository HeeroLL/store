package com.zebone.btp.app.frame.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zebone.btp.app.frame.vo.ShortcutMenu;

/**
 * 快捷方式业务类
 * @author 宋俊杰
 *
 */
public interface ShortcutMenuService {
	/**
	 * 根据人员ID得到此人得到快捷方式
	 * @param personnelId 人员id
	 * @return
	 */
	public List<ShortcutMenu> getShortcutMenuByPersonnelId(String personnelId);

	/**
	 * 保存一个快捷方式
	 * @param shortcutMenu 需要模块id，人员id和排序号3个参数
	 */
	public void saveShortcutMenu(ShortcutMenu shortcutMenu);

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
	public void deleteByModuleIdAndPersonnelId(String moduleId ,String personnelId);
}
