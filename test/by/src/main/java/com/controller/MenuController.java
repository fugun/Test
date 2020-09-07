package com.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pojo.UserInfo;
import com.service.MenuService;

@Controller
public class MenuController {
	@Resource
	private MenuService menuService;
	//打日志log4j，static全局唯一，final一次初始化，不允许修改
	//private static final Logger log = Logger.getLogger(UserController.class);
	// 查询菜单
	@ResponseBody
	@RequestMapping(value = "user/menuJson/find") //page=1&limit=10
	public Map<String,Object> query(Integer page, Integer limit,String menuname,String start,String end){
				
		return menuService.list(page, limit, menuname,start,end);
			
	}
	// 修改菜单
	@ResponseBody
	@RequestMapping(value = "user/update/Menu") 
	public Map<String,Object> updateMenu(Integer id, String menuname ,String url,Integer pid,String iconfont,Integer sort,HttpServletRequest request){	
			return menuService.updateMenu(id, menuname,url,pid,iconfont,sort,request);				
	}		

	// 新增菜单
	@ResponseBody
	@RequestMapping(value = "user/add/Menu") 
	public Map<String,Object> addMenu(String menuname ,String url,Integer pid,String iconfont,Integer sort,HttpServletRequest request){					
			return menuService.addMenu( menuname,url,pid,iconfont,sort, request);				
	}
	// 删除菜单
	@ResponseBody
	@RequestMapping(value = "user/delete/Menu") 
	public Map<String,Object> deleteMenu(String id,HttpServletRequest request){					
			return menuService.deleteMenu(id ,request);				
	}
	// 修改菜单状态
	@ResponseBody
	@RequestMapping(value = "user/update/MenuStatus") 
	public Map<String,Object> updateMenuStatus(Integer id, Integer status,HttpServletRequest request ){	
			return menuService.updateMenuStatus(id, status,request);				
	}
	
	//主页菜单查询
	@ResponseBody
	@RequestMapping(value = "user/menu/All") 
	public Map<String,Object> userMenuAll(HttpServletRequest request){	
		//获取用户信息
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("sysUser");
		
			return menuService.userMenuAll(userInfo.getId(),userInfo.getLoginName());				
	}
}	
