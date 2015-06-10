package com.zebone.btp.app.frame.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zebone.btp.app.frame.dao.ShortcutMenuMapper;
import com.zebone.btp.app.frame.service.ShortcutMenuService;
import com.zebone.btp.app.frame.vo.ShortcutMenu;

/**
 * 快捷方式业务实现类
 * 
 * @author 宋俊杰
 * @date 2012-12-13
 */
@Service
public class ShortcutMenuServiceImpl implements ShortcutMenuService {

	@Resource
	private ShortcutMenuMapper shortcutMenuMapper;

	@Override
	public void deleteByModuleIdAndPersonnelId(String moduleId,
			String personnelId) {
		this.shortcutMenuMapper.deleteByModuleIdAndPersonnelId(moduleId,personnelId);
	}

	@Override
	public List<ShortcutMenu> getShortcutMenuByPersonnelId(String personnelId) {
		return this.shortcutMenuMapper.getShortcutMenuByPersonnelId(personnelId);
	}

	@Override
	public void saveShortcutMenu(ShortcutMenu shortcutMenu) {
		this.shortcutMenuMapper.insert(shortcutMenu);
	}

	@Override
	public void updateOrderNo(ShortcutMenu shortcutMenu) {
		this.shortcutMenuMapper.updateOrderNo(shortcutMenu);
	}
}
