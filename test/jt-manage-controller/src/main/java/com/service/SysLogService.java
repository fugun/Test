package com.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.SysLogMapper;
import com.pojo.SysLogInfo;
import com.pojo.UserInfo;
import com.util.service.BaseService;
@Service
public class SysLogService extends BaseService<SysLogInfo>{
	@Autowired
	SysLogMapper sysLogMapper ;
	private static final Logger log = Logger.getLogger(SysLogService.class);
	//插入日志
	public void InsertSysLog(SysLogInfo sysLogInfo,HttpServletRequest request){
		// 获取登录用户信息
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("sysUser");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd"); 
		try {
			sysLogInfo.setCreated(new Date());
			sysLogInfo.setUserId(userInfo.getId());
			sysLogInfo.setUserName(userInfo.getLoginName());
			sysLogInfo.setCreateDate(Integer.valueOf(df.format(new Date())));
			sysLogMapper.InsertSysLog(sysLogInfo);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("日志插入异常："+e);
		}
	}			
}
