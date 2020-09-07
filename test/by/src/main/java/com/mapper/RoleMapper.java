package com.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pojo.MenuInfo;
import com.pojo.MenuRoleInfo;
import com.pojo.RoleInfo;
import com.pojo.UserRoleInfo;

public interface RoleMapper extends SysMapper<RoleInfo>{
	public List<Map<String,Object>> list(RoleInfo role);
	public RoleInfo findByAccount(RoleInfo role);
	public Integer findCount(RoleInfo role);
	public Integer updateRole(RoleInfo role);
	public Integer addRole(RoleInfo role);
	public Integer deleteRole(RoleInfo role);
	public Integer updateRoleStatus(RoleInfo role);
	public List<Map<String,Object>>  finduserRole_user_role(Map<String,Object> map); //用户组
	public Integer UserRoleInfoAdd(UserRoleInfo userRole);
	public Integer UserRoleInfoDelete(UserRoleInfo userRole);
	public List<Map<String,Object>>  findroleMenu_Menu_role(Map<String,Object> map); //菜单组
	public Integer menuRoleInfoAdd(MenuRoleInfo menuRole);
	public Integer menuRoleInfoDelete(MenuRoleInfo menuRole);
	
	public List<MenuInfo>  getmenuInfo(@Param("id") Integer id); //用户Id查询用户组
	
}
