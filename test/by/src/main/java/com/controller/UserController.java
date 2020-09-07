package com.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.UserService;



@Controller
public class UserController {
	@Resource
	private UserService sysUserService;
	//打日志log4j，static全局唯一，final一次初始化，不允许修改
	//private static final Logger log = Logger.getLogger(UserController.class);
	// 查询用户
	@ResponseBody
	@RequestMapping(value = "user/json/find") //page=1&limit=10
	public Map<String,Object> query(Integer page, Integer limit,String username,String start,String end){
		
		return sysUserService.list(page, limit, username,start,end);
			
	}
	
	// 修改用户
	@ResponseBody
	@RequestMapping(value = "user/update/User") 
	public Map<String,Object> updateUser(Integer id, String loginName,String email,HttpServletRequest request){	
			return sysUserService.updateUser(id, loginName, email,request);				
	}	
	// 修改用户 密码-状态
	@ResponseBody
	@RequestMapping(value = "user/update/UserPassword") 
	public Map<String,Object> updateUser(Integer id,String password,Integer status,HttpServletRequest request){					
			return sysUserService.UserPassword(id,password,status,request);			
	}	
	
	// 新增用户
	@ResponseBody
	@RequestMapping(value = "user/add/user") 
	public Map<String,Object> addUser(String loginName,String email,String password,HttpServletRequest request){					
			return sysUserService.addUser( loginName, email,password,request);				
	}
	// 删除用户
	@ResponseBody
	@RequestMapping(value = "user/delete/user") 
	public Map<String,Object> deleteUser(String id,HttpServletRequest request){					
			return sysUserService.deleteUser(id,request);				
	}	
	
	
	// 查询帐号
	@ResponseBody
	@RequestMapping(value = "user/json/payAccount") //page=1&limit=10
	public Map<String,Object> findpayaccont(Integer page, Integer limit,String name,String start,String end){
		
		return sysUserService.findpayaccont(page, limit, name,start,end);
			
	}	

	// 新增帐号
	@ResponseBody
	@RequestMapping(value = "user/add/payAccount") 
	public Map<String,Object> addPayAccount(String zfbName ,String BankName ,String BranchBankName,String userName,String name,String acPay1,String acPay2,String des ,HttpServletRequest request){					
			return sysUserService.addPayAccount( zfbName,BankName,BranchBankName,userName, name, acPay1,acPay2 ,des,request);				
	}
	// 修改商户
	@ResponseBody
	@RequestMapping(value = "user/update/payAccount") 
	public Map<String,Object> updatePayAccount(Integer id, String zfbName ,String BankName ,String BranchBankName,String userName,String name,String acPay1,String acPay2,String des ,HttpServletRequest request){	
			return sysUserService.updatePayAccount(id,zfbName,BankName,BranchBankName,userName, name, acPay1,acPay2 ,des,request);				
	}
	
	// 删除商户
	@ResponseBody
	@RequestMapping(value = "user/delete/payAccount") 
	public Map<String,Object> deletePayAccount(String id,HttpServletRequest request){					
			return sysUserService.deletePayAccount(id,request);				
	}

	
	// 商户Id 查询
	@ResponseBody
	@RequestMapping(value = "user/find/payAccountID") 
	public Map<String,Object> payAccountID(Integer id){					
			return sysUserService.payAccountID(id);				
	}
	
	
}
