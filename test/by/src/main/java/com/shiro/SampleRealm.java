package com.shiro;

import java.util.Date;

import javax.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.session.Session;

import com.pojo.UserInfo;
import com.service.UserService;
 
 
public class SampleRealm extends JdbcRealm {
 
	@Resource
	private UserService sysUserService;
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//System.out.println("------doGetAuthenticationInfo-------");
		/**
		 * js --> sysUser/login --> Subject.login(UsernamepasswordToken) --> CustomJdbcRealm
		 */
		UserInfo in = new UserInfo();
		
		UsernamePasswordToken usernamePasswordToken =(UsernamePasswordToken) token;
		//获取登录账号
		String account=usernamePasswordToken.getUsername();
		 in.setLoginName(account);
		UserInfo sysUser=sysUserService.findByAccount(in);
		if(sysUser==null) {
			throw new UnknownAccountException();
		}
		//判断用户名和密码是否正确
		if(!sysUser.getPassword().equals(new String (usernamePasswordToken.getPassword()))) {
			throw new IncorrectCredentialsException();
		}
		//判断用户是否被禁用
		if(sysUser.getStatus()==2) {
			throw new LockedAccountException();
		}
		
		//用户登录成功,将用户保存到session中
		Session session= SecurityUtils.getSubject().getSession();
		session.setAttribute("sysUser", sysUser);
		// 修改登录时间
		sysUser.setLastDate(new Date());
		sysUserService.updateLoginTime(sysUser);
		//参数1:Subject.getPrincipal()获取到的就是参数一传入的内容
		//参数2:shiro进行用户信息比对的时候使用的密码
		//参数3:realm名称,getName方法自动生成一个realm的名称
		return new SimpleAuthenticationInfo(account,sysUser.getPassword(),getName());
	}
}
