package com.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.RoleService;
@Controller
public class RoleController {
	@Resource
	private RoleService roleService;
	//打日志log4j，static全局唯一，final一次初始化，不允许修改
	//private static final Logger log = Logger.getLogger(UserController.class);
	// 查询角色
	@ResponseBody
	@RequestMapping(value = "user/roleJson/find") //page=1&limit=10
	public Map<String,Object> query(Integer page, Integer limit,String rolename,String start,String end){
		
		return roleService.list(page, limit, rolename,start,end);
			
	}
	// 修改角色
	@ResponseBody
	@RequestMapping(value = "user/update/Role") 
	public Map<String,Object> updateRole(Integer id, String rolename ,HttpServletRequest request ){	
			return roleService.updateRole(id, rolename,request);				
	}		

	// 新增角色
	@ResponseBody
	@RequestMapping(value = "user/add/Role") 
	public Map<String,Object> addRole(String rolename,HttpServletRequest request){					
			return roleService.addRole( rolename,request);				
	}
	// 删除用户
	@ResponseBody
	@RequestMapping(value = "user/delete/Role") 
	public Map<String,Object> deleRole(String id,HttpServletRequest request){					
			return roleService.deleteRole(id,request);				
	}
	// 修改角色状态
	@ResponseBody
	@RequestMapping(value = "user/update/RoleStatus") 
	public Map<String,Object> updateRole(Integer id, Integer status, HttpServletRequest request){	
			return roleService.updateRoleStatus(id, status,request);				
	}
	
	// 查询关系用户信息
	@ResponseBody
	@RequestMapping(value = "user/userRole_user_role/find") //page=1&limit=10
	public Map<String,Object> finduserRole_user_role(Integer page, Integer limit,String username,String start,String end,String rolename){
		
		return roleService.finduserRole_user_role(page, limit, username,start,end,rolename);
			
	}	
	// 添加到角色组-- 删除角色组
	@ResponseBody
	@RequestMapping(value = "user/add/userRoleInfo") 
	public Map<String,Object> addUserRoleInfo(Integer id, Integer status,Integer userRoleId,Integer roleId,HttpServletRequest request ){	
			return roleService.addUserRoleInfo(id, status,userRoleId,roleId,request);				
	}	
	
	// 批量添加到角色组
	@ResponseBody
	@RequestMapping(value = "user/add/userRoleInfoAll") 
	public Map<String,Object> addUserRoleInfo(String id ,Integer roleId,HttpServletRequest request){	
			return roleService.addUserRoleInfo(id,roleId,request);				
	}
	
	// 查询关系菜单信息
	@ResponseBody
	@RequestMapping(value = "user/roleMenu_Menu_role/find") //page=1&limit=10
	public Map<String,Object> findroleMenu_Menu_role(Integer page, Integer limit,String menuname,String start,String end,String rolename){
		
		return roleService.findroleMenu_Menu_role(page, limit, menuname,start,end,rolename);
			
	}
	// 添加到菜单组-- 删除菜单组
	@ResponseBody
	@RequestMapping(value = "user/add/menuRoleInfo") 
	public Map<String,Object> addMenuRoleInfo(Integer id, Integer status,Integer menuRoleId,Integer roleId ,HttpServletRequest request){	
			return roleService.addMenuRoleInfo(id, status,menuRoleId,roleId,request);				
	}	
	
	// 批量添加到菜单组
	@ResponseBody
	@RequestMapping(value = "user/add/menuRoleInfoAll") 
	public Map<String,Object> addMenuRoleInfo(String id ,Integer roleId,HttpServletRequest request){	
			return roleService.addMenuRoleInfo(id,roleId,request);				
	}	
}
