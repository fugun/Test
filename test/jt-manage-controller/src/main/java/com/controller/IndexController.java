package com.controller;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class IndexController {
	//通用转向，转向WEB-INF/views下的某个html
	@RequestMapping("/user/{login}")
	public String go(@PathVariable String login){
		//System.out.println("jsp");
		return login;
	}
	// 被拦截了
	@RequestMapping(value = "/login_timeout/{pageName}")
	public String timeout(@PathVariable String pageName) {
		System.out.println("被拦截了。。。。。。。");
		return "login";
	}
	// 登录
	@ResponseBody
	@RequestMapping(value = "user/login/index")
	public Map<String,Object> index(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "username") String loginName, @RequestParam(value = "password") String password) {
			Map<String,Object> map  = new HashMap<String,Object>();
		//返回信息
				String message = "";
				if(!"".equals(loginName)&& loginName!=null) {
					message="登录账号不能为空";
				}
				
				if(message=="" && !"".equals(password)&& loginName!=null) {
					message="登录密码不能为空";
				}
				//登录
				if(!"".equals(message)) {
					Subject currentUser=SecurityUtils.getSubject();
					
					//加密密码
					//String md5Password = MD5Utils.hmacSign(password, MD5Utils.digest("task"));
					
					UsernamePasswordToken upToken=
							new UsernamePasswordToken(loginName,password);
					//记住我
					//upToken.setRememberMe(true);
					try {
		                //进入shiro的登录,此时将进入自定义的realm中进行安全验证
						currentUser.login(upToken);
						message="success";
					}catch (UnknownAccountException uae) {
						message="账号不存在";
					}catch (IncorrectCredentialsException ice) {
						message="用户名或者密码错误";
					}catch (LockedAccountException lae) {
						message="账号已被锁定";
					}catch (AuthenticationException e) {
						message="登录异常,请稍后再试!!";
						e.printStackTrace();
					}
				}
				// UserInfo userInfo = (UserInfo) request.getSession().getAttribute("sysUser");
				// System.out.println(userInfo.toString());
				map.put("data", message);
				return map;
	}
}
