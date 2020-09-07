package com.mapper;

import java.util.List;
import java.util.Map;

import com.pojo.MenuInfo;
import com.pojo.RoleInfo;

public interface MenuMapper extends SysMapper<MenuInfo>{
	public List<Map<String,Object>> list(MenuInfo menu);
	public MenuInfo findByAccount(MenuInfo menu);
	public Integer updateMenu(MenuInfo menu);
	public Integer findCount(MenuInfo menu);
	public Integer addMenu(MenuInfo menu);
	public Integer deleteMenu(MenuInfo menu);
	public Integer updateMenuStatus(MenuInfo menu);
}
