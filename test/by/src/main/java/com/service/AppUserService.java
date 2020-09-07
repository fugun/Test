package com.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.pojo.SysLogInfo;
import com.pojo.TaskInfo;
import com.pojo.TaskRecordInfo;
import com.pojo.UserInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.AppMapper;
import com.mchange.v1.util.ArrayUtils;
import com.pojo.AgencycashrecordInfo;
import com.pojo.AppUserInfo;
import com.pojo.AppversionInfo;
import com.pojo.CashrecordInfo;
import com.pojo.DataCashrecordInfo;
import com.pojo.MenuRoleInfo;
import com.pojo.Merchant;
import com.pojo.NoticeInfo;
import com.pojo.SettingInfo;
import com.util.service.BaseService;
import com.vo.layUIResult;

import net.sf.ehcache.search.expression.Not;
import net.sf.json.JSONArray;
@Service
public class AppUserService extends BaseService<AppUserInfo>{
	@Autowired
	public AppMapper appMapper;
	@Autowired
	public SysLogService sysLogService;
	private static final Logger log = Logger.getLogger(AppUserService.class);
	//public String httpUrl = "http://192.168.1.141:8080/test/image/";
	//public String httpFileUrl = "http://192.168.1.141:8080/test/image/";
	public String httpUrl = "/files/image/";
	public String httpFileUrl = "/files/file/";
	//新增app用户
	public Map<String, Object> addAppUser(String userName,Integer merID,String model,String validity,String des,Integer count,HttpServletRequest request){
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
		try {
			if(count>10001){
				return layUIResult.resultErr(201, "新增app用户失败!次数不能超过一万!");
			}
			
			AppUserInfo appUser = new AppUserInfo();
			// 批量新增
			String  nameCount = count.toString();
			String str = "";
			if(count>0){
				for(int a = 0;a<nameCount.length();a++){
					str = str+"0";
				}
				for(int i =1;i<count+1;i++){
					if( i<20 && i%10==0){
						str = str.substring(1,str.length());
						
					}else if (i%100==0 && i==100 ){
						str = getSubstring(str);
						
					}else if(i%1000==0 && i==1000){
						str = getSubstring(str);
					}else if(i%10000==0 && i==10000){
						str = getSubstring(str);
					//}else if(i%100000==0 && i==100000){
						//str = getSubstring(str);
					//}else if(i%1000000==0 &&i==1000000){
						//str = getSubstring(str);
					}
					//Random ro=new Random();
			 	    //String s = Long.valueOf(new Date().getTime()).toString().substring(2, 5);
			 	    //String a = Long.valueOf(new Date().getTime()).toString().substring(7, 9);
				    //唯一名字					
					appUser.setUserName(userName+str+i);
					appUser.setMerID(merID);
					appUser.setPassword("123456");
					appUser.setModel(model);
					appUser.setValidity(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(validity));					
					appUser.setCreateTime(new Date());
					appUser.setIsDel(1);//正常
					appUser.setDes(des);
					appUser.setCreateDate(Integer.valueOf(new SimpleDateFormat("yyyyMMdd").format(new Date())));
					appMapper.addAppUser(appUser);
				}
			}
			
			// 只记录保存成功的日志
		    SysLogInfo sysLogInfo = new SysLogInfo();
		    sysLogInfo.setDescription("新增app用户：内容-"+appUser.toString() + ";次数:"+count);
		    sysLogService.InsertSysLog(sysLogInfo,request);
			new layUIResult();				
			return layUIResult.ok();
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			log.error(e.getMessage());
			return layUIResult.resultErr(201, "新增app用户失败");
		}
	}	
	public String getSubstring(String str){
		if(str.length()>0){
			str = str.substring(1,str.length());
		}else{
			str = "";
		}
		return str;
		
	}
	//findAppUser
	//查询所有用户
	//返回值为了前台的分页条，两个数据：记录总数，当前页数据
	@SuppressWarnings("static-access")
	public Map<String,Object> findAppUser(Integer page, Integer rows,String name,String start,String end,String merID){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//调用分页，标识分页开启，拦截器规定，这个开启后的第一条执行查询的SQL语句会被分页！！！
			PageHelper.startPage(page, rows);
			AppUserInfo appUser = new AppUserInfo();
			appUser.setUserName(name);
			//sdf.parse("2005-12-15 15:30:23")
			if(start!=null&&!"".equals(start)){
				appUser.setCreateTime(sdf.parse(start));
			}
			if(end!=null && !"".equals(end)){
				appUser.setLastModiftyTime(sdf.parse(end));
			}
			if(merID!=null && !"".equals(merID)){
				appUser.setMerID(Integer.valueOf(merID));
			} 
		  
			List<Map<String,Object>> appUserList = appMapper.findAppUser(appUser);		
			//List<UserInfo> pageuserList = userMapper.userlist();
			PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(appUserList);
			//写入缓存
			//String ITEM_KEY = "ITEM_" + page+rows;
			//缓存商品，会设置自动过期时间，30天过期
			//redisService.set(ITEM_KEY,new JSONArray(userList).toString());//存储数据
			//redisService.expire(ITEM_KEY, 60*60*24*30);
			//System.out.println(redisService.get(ITEM_KEY));
			return new layUIResult().result(pageInfo.getTotal(),appUserList);
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}
		
	}
	//删除帐号
	public Map<String, Object> deleteAppAccount(String id,HttpServletRequest request){
		AppUserInfo appUser = new AppUserInfo();
		try {
			String[] ids = id.split(",");
			for(int i = 0;i<ids.length;i++){
				appUser.setId(Integer.valueOf(ids[i]));
				appUser.setIsDel(2); // 2删除状态
				appUser.setLastModiftyTime(new Date());
				appMapper.deleteAppAccount(appUser);
			}
			// 只记录保存成功的日志
		    SysLogInfo sysLogInfo = new SysLogInfo();
		    sysLogInfo.setDescription("删除app帐号：内容-id："+ id );
		    sysLogService.InsertSysLog(sysLogInfo,request);
			new layUIResult();				
			return layUIResult.ok();
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			log.error(e.getMessage());
			return layUIResult.resultErr(201, "删除app帐号失败");
		}
	}
	//app帐号
	public Map<String, Object> updateAppAccount(Integer id,String validity,String des ,HttpServletRequest request){
		try {
			AppUserInfo appUser = new AppUserInfo();
			appUser.setId(id);
			appUser.setDes(des);
			appUser.setLastModiftyTime(new Date());
			appUser.setValidity(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(validity));
			appMapper.updateAppAccount(appUser);
			// 只记录保存成功的日志
		    SysLogInfo sysLogInfo = new SysLogInfo();
		    sysLogInfo.setDescription("修改app帐号：内容-id:"+id +",用效期时间："+ validity + ",备注:"+des);
		    sysLogService.InsertSysLog(sysLogInfo,request);
			new layUIResult();				
			return layUIResult.ok();
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			log.error(e.getMessage());
			return layUIResult.resultErr(201, "修改app帐号失败");
		}
	}
	
	//app任务新增
	public Map<String, Object> saveTask(String name ,Integer type,String des,String imgUrl ,String bonus,String webUrl ,String contents,HttpServletRequest request){
		try {
			JSONArray jArray = new JSONArray();
			
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				TaskInfo taskInfo = new TaskInfo();
				taskInfo.setType(type);
				taskInfo.setName(name);
				taskInfo.setDes(des);
				if(!"".equals(imgUrl)&& imgUrl!=null){
					taskInfo.setImgUrl(httpUrl+imgUrl.replace(";", ""));
				}else{
					taskInfo.setImgUrl("");
				}
				
				taskInfo.setBonus(Float.valueOf(bonus));
				taskInfo.setWebUrl(webUrl);
				taskInfo.setIsDel(1);
				String[] contentsT = contents.split(",");
				for(int i=0;i<contentsT.length;i++){
				Map<String,Object> map  = new HashMap<String,Object>();
					map.put("content", contentsT[i]);
					list.add(map);
					
				}
				//taskInfo.setContents("["+contents+"]");
				taskInfo.setContents(jArray.fromObject(list).toString());
				taskInfo.setLastModiftyTime(new Date());
				taskInfo.setStatus(1);
				appMapper.saveTask(taskInfo);
				// 只记录保存成功的日志
			    SysLogInfo sysLogInfo = new SysLogInfo();
			    sysLogInfo.setDescription("新增appTask任务："+taskInfo.toString());
			    sysLogService.InsertSysLog(sysLogInfo,request);
				new layUIResult();				
				return layUIResult.ok();
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			log.error(e.getMessage());
			return layUIResult.resultErr(201, "新增appTask任务失败");
		}
	}
	//修改任务
	public Map<String, Object> updateTask(Integer id,String name ,Integer type,String des,String imgUrl ,String bonus,String webUrl ,String contents,HttpServletRequest request){
		try {
			JSONArray jArray = new JSONArray();
			
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			TaskInfo taskInfo = new TaskInfo();
			taskInfo.setId(id);
			taskInfo.setType(type);
			taskInfo.setName(name);
			taskInfo.setDes(des);
			 if(!"".equals(imgUrl)&& imgUrl!=null&&imgUrl.contains("image")){
				taskInfo.setImgUrl(imgUrl);
			}else if(!"".equals(imgUrl)&& imgUrl!=null){
				taskInfo.setImgUrl(httpUrl+imgUrl);
			}else{
				taskInfo.setImgUrl("");
			}
			taskInfo.setBonus(Float.valueOf(bonus));
			taskInfo.setWebUrl(webUrl);
			String[] contentsT = contents.split(",");
			for(int i=0;i<contentsT.length;i++){
			Map<String,Object> map  = new HashMap<String,Object>();
				map.put("content", contentsT[i]);
				list.add(map);
				
			}
			//taskInfo.setContents("["+contents+"]");
			taskInfo.setContents(jArray.fromObject(list).toString());
			taskInfo.setLastModiftyTime(new Date());
			appMapper.updateTask(taskInfo);
			// 只记录保存成功的日志
		    SysLogInfo sysLogInfo = new SysLogInfo();
		    sysLogInfo.setDescription("修改appTask任务："+taskInfo.toString());
		    sysLogService.InsertSysLog(sysLogInfo,request);
			new layUIResult();				
			return layUIResult.ok();
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			log.error(e.getMessage());
			return layUIResult.resultErr(201, "修改appTask任务失败");
		}
	}	
	//修改任务状态
	public Map<String, Object> updateTaskSatus(String id,Integer status ,HttpServletRequest request){
		try {
			TaskInfo taskInfo = new TaskInfo();
			
			String[] ids = id.split(",");
			for(int i = 0;i<ids.length;i++){
				taskInfo.setId(Integer.valueOf(ids[i]));
			
				
				taskInfo.setStatus(status);
				taskInfo.setLastModiftyTime(new Date());
				appMapper.updateTaskStatus(taskInfo);
			}
			// 只记录保存成功的日志
		    SysLogInfo sysLogInfo = new SysLogInfo();
		    sysLogInfo.setDescription("修改appTask任务状态：di = "+id);
		    sysLogService.InsertSysLog(sysLogInfo,request);
			new layUIResult();				
			return layUIResult.ok();
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			log.error(e.getMessage());
			return layUIResult.resultErr(201, "修改appTask任务失败");
		}
	}			
	//查询所有任务
	//返回值为了前台的分页条，两个数据：记录总数，当前页数据
	@SuppressWarnings("static-access")
	public Map<String,Object> findAppTask(Integer page, Integer rows,String name,String start,String end,Integer status,String bonus){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//调用分页，标识分页开启，拦截器规定，这个开启后的第一条执行查询的SQL语句会被分页！！！
			PageHelper.startPage(page, rows);
			TaskInfo task = new TaskInfo();
			task.setName(name);
			//sdf.parse("2005-12-15 15:30:23")
			if(start!=null&&!"".equals(start)){
				task.setCreateTime(sdf.parse(start));
			}
			if(end!=null && !"".equals(end)){
				task.setLastModiftyTime(sdf.parse(end));
			}
			if(status!=null && status!=0){
				task.setStatus(status);
			}
			if(!"".equals(bonus)&& bonus!=null ){
				if(bonus.split("-").length==1){
					task.setTestbonus(Float.valueOf(bonus.split("-")[0]));
				}else{
					task.setBonus(Float.valueOf(bonus.split("-")[0]));
					task.setTestbonus(Float.valueOf(bonus.split("-")[1]));
				}
				
				
			}
			List<Map<String,Object>> taskList = appMapper.findAppTask(task);		
			//List<UserInfo> pageuserList = userMapper.userlist();
			PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(taskList);		
			return new layUIResult().result(pageInfo.getTotal(),taskList);
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}
		
	}
	//删除任务
	public Map<String, Object> deleteAppTask(String id,HttpServletRequest request){
		TaskInfo task = new TaskInfo();
		try {
			String[] ids = id.split(",");
			for(int i = 0;i<ids.length;i++){
				task.setId(Integer.valueOf(ids[i]));
				task.setIsDel(2); // 2删除状态
				task.setLastModiftyTime(new Date());
				appMapper.deleteAppTask(task);
			}
			// 只记录保存成功的日志
		    SysLogInfo sysLogInfo = new SysLogInfo();
		    sysLogInfo.setDescription("删除任务：内容-id："+ id );
		    sysLogService.InsertSysLog(sysLogInfo,request);
			new layUIResult();				
			return layUIResult.ok();
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			log.error(e.getMessage());
			return layUIResult.resultErr(201, "删除任务失败");
		}
	}	
	// id 查询
	@SuppressWarnings("static-access")
	public Map<String,Object> findAppTaskId(Integer id){
		try {
			
			
			TaskInfo task = new TaskInfo();
			
			task.setId(id);
			List<Map<String,Object>> taskList = appMapper.findAppTask(task);		
			//List<UserInfo> pageuserList = userMapper.userlist();
			PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(taskList);		
			return new layUIResult().result(pageInfo.getTotal(),taskList);
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}
		
	}
	
	//批量任务到记录表
	public Map<String, Object> TaskRecordAddTask(String userId,String taskId,HttpServletRequest request){
		TaskRecordInfo taskRexord = new TaskRecordInfo();
		TaskInfo task = new TaskInfo();
		SimpleDateFormat sf	=new SimpleDateFormat("yyyyMMdd");
		try {
			String[] userIds = userId.split(",");
			String[] taskIds = taskId.split(",");
			for(int i = 0;i<userIds.length;i++){
				for(int a = 0 ;a<taskIds.length;a++){
					taskRexord.setUserID(Integer.valueOf(userIds[i]));
					taskRexord.setTaskID(Integer.valueOf(taskIds[a]));
					//获取任务佣金
					task.setId(Integer.valueOf(taskIds[a]));
					List<Map<String,Object>> taskList = appMapper.findAppTask(task);
					if(taskList.size()>0){
						taskRexord.setBonus(Float.valueOf(taskList.get(0).get("bonus").toString()));
					}else{
						continue; // 跳过
					}
					taskRexord.setStatus(0);
					taskRexord.setCreateDate( Integer.valueOf(sf.format(new Date())));
					taskRexord.setCreateTime(new Date());
					// 加入表
					appMapper.TaskRecordAddTask(taskRexord);
				}

			}
			// 只记录保存成功的日志
		    SysLogInfo sysLogInfo = new SysLogInfo();
		    sysLogInfo.setDescription("批量添加到任务记录：内容-UserId:"+userId +",任务Id："+ taskId);
		    sysLogService.InsertSysLog(sysLogInfo,request);
			new layUIResult();				
			return layUIResult.ok();
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			log.error(e.getMessage());
			return layUIResult.resultErr(201, "批量添加到任务记录失败");
		}
	}	
	
	
	//查询所有任务记录
	//返回值为了前台的分页条，两个数据：记录总数，当前页数据
	@SuppressWarnings("static-access")
	public Map<String,Object> findTaskrecord(Integer page, Integer rows,String name,String start,String status ,String end,String merID){
		Map<String ,Object> map = new HashMap<String,Object>();
		try {
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//调用分页，标识分页开启，拦截器规定，这个开启后的第一条执行查询的SQL语句会被分页！！！
			PageHelper.startPage(page, rows);
			if(status!=null&&!"".equals(status)){  
				
				map.put("status", status);
			}
			map.put("userName", name);
			map.put("start", start);
			map.put("end", end);
			if(merID!=null && !"".equals(merID)){
				map.put("merID", merID);
			}
			List<Map<String,Object>> taskList = appMapper.findTaskRecord(map);		
			//List<UserInfo> pageuserList = userMapper.userlist();
			PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(taskList);		
			return new layUIResult().result(pageInfo.getTotal(),taskList);
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}
		
	}	
			
	//app公告新增
	public Map<String, Object> saveNotice(String content ,String weixin1,String weixin2,String des,HttpServletRequest request){
		try {			
				NoticeInfo noticeInfo = new NoticeInfo();
				noticeInfo.setContent(content);
				
			    noticeInfo.setWeixin1(weixin1);
				noticeInfo.setWeixin2(weixin1);
				noticeInfo.setDes(des);
				noticeInfo.setLastModiftyTime(new Date());
				appMapper.saveNotice(noticeInfo);
				// 只记录保存成功的日志
			    SysLogInfo sysLogInfo = new SysLogInfo();
			    sysLogInfo.setDescription("新增公告："+noticeInfo.toString());
			    sysLogService.InsertSysLog(sysLogInfo,request);
				new layUIResult();				
				return layUIResult.ok();
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			log.error(e.getMessage());
			return layUIResult.resultErr(201, "新增公告失败");
		}
	}	
	
	//查询所有任务记录
	//返回值为了前台的分页条，两个数据：记录总数，当前页数据
	@SuppressWarnings("static-access")
	public Map<String,Object> findNotice(Integer page, Integer rows,String name,String start,String end){
		NoticeInfo noticeInfo = new NoticeInfo();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//调用分页，标识分页开启，拦截器规定，这个开启后的第一条执行查询的SQL语句会被分页！！！
			PageHelper.startPage(page, rows);
			noticeInfo.setContent(name);
			//sdf.parse("2005-12-15 15:30:23")
			if(start!=null&&!"".equals(start)){
				noticeInfo.setCreateTime(sdf.parse(start));
			}
			if(end!=null && !"".equals(end)){
				noticeInfo.setLastModiftyTime(sdf.parse(end));
			}
			List<Map<String,Object>> taskList = appMapper.findNotice(noticeInfo);		
			//List<UserInfo> pageuserList = userMapper.userlist();
			PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(taskList);		
			return new layUIResult().result(pageInfo.getTotal(),taskList);
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}
		
	}
	// id 查询
	@SuppressWarnings("static-access")
	public Map<String,Object> findNoticeId(Integer id){
		try {
			
			
			NoticeInfo notice = new NoticeInfo();
			
			notice.setId(id);
			List<Map<String,Object>> taskList = appMapper.findNotice(notice);		
			//List<UserInfo> pageuserList = userMapper.userlist();
			PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(taskList);		
			return new layUIResult().result(pageInfo.getTotal(),taskList);
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}
		
	}
	
	//app公告修改
	public Map<String, Object> updaetNotice(Integer id,String content ,String weixin1,String weixin2,String des,HttpServletRequest request){
		try {			
				NoticeInfo noticeInfo = new NoticeInfo();
				noticeInfo.setId(id);
				noticeInfo.setContent(content);
				
				noticeInfo.setWeixin1(weixin1);
				noticeInfo.setWeixin2(weixin2);
				noticeInfo.setDes(des);
				noticeInfo.setLastModiftyTime(new Date());
				appMapper.updaetNotice(noticeInfo);
				// 只记录保存成功的日志
			    SysLogInfo sysLogInfo = new SysLogInfo();
			    sysLogInfo.setDescription("修改公告："+noticeInfo.toString());
			    sysLogService.InsertSysLog(sysLogInfo,request);
				new layUIResult();				
				return layUIResult.ok();
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			log.error(e.getMessage());
			return layUIResult.resultErr(201, "新增公告失败");
		}
	}
	
	//删除公告
	public Map<String, Object> deleteNotice(String id,HttpServletRequest request){
		NoticeInfo noticeInfo = new NoticeInfo();
		try {
			String[] ids = id.split(",");
			for(int i = 0;i<ids.length;i++){
				noticeInfo.setId(Integer.valueOf(ids[i]));
				
				appMapper.deleteNotice(noticeInfo);
			}
			// 只记录保存成功的日志
		    SysLogInfo sysLogInfo = new SysLogInfo();
		    sysLogInfo.setDescription("删除公告：内容-id："+ id );
		    sysLogService.InsertSysLog(sysLogInfo,request);
			new layUIResult();				
			return layUIResult.ok();
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			log.error(e.getMessage());
			return layUIResult.resultErr(201, "删除公告失败");
		}
	}
	//查询所有任务记录
	//返回值为了前台的分页条，两个数据：记录总数，当前页数据
	@SuppressWarnings("static-access")
	public Map<String,Object> findCashrecord(Integer page, Integer rows,String name,String state,String end,String start,String  merID,String type){
		Map<String ,Object> map = new HashMap<String,Object>();
		try {
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//调用分页，标识分页开启，拦截器规定，这个开启后的第一条执行查询的SQL语句会被分页！！！
			PageHelper.startPage(page, rows);
			
			map.put("userName", name);
			if(state!=null&&!"".equals(state)){  
				
				map.put("state", state);
			}
			if(!"".equals(merID) && merID!=null){
				map.put("merID", merID);
			}
			if(!"".equals(type) && type!=null){
				map.put("type", type);
			}
			
			map.put("start", start);
			map.put("end", end);
			List<Map<String,Object>> taskList = appMapper.findCashrecord(map);		
			//List<UserInfo> pageuserList = userMapper.userlist();
			PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(taskList);		
			return new layUIResult().result(pageInfo.getTotal(),taskList);
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}
		
	}
	// 获取审核信息
	@SuppressWarnings("static-access")
	public Map<String,Object> findCheckCashApply(Integer id){
		try {
			NoticeInfo notice = new NoticeInfo();
			
			notice.setId(id);
			List<Map<String,Object>> taskList = appMapper.findCheckCashApply(notice);		
			//List<UserInfo> pageuserList = userMapper.userlist();		
			return new layUIResult().ok(taskList);
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}
		
	}
	//审核 
	public Map<String, Object> updateCheckCashApply(Integer id,Integer state,String des,HttpServletRequest request){
		try {			
				CashrecordInfo cashrecordInfo  = new CashrecordInfo();
				AppUserInfo auI = new AppUserInfo();
				cashrecordInfo.setId(id);
				cashrecordInfo.setDoneTime(new Date());
				cashrecordInfo.setDes(des);
				cashrecordInfo.setState(state);
				cashrecordInfo.setCreateDate(Integer.valueOf(new SimpleDateFormat("yyyyMMdd").format(new Date())));
				int i = appMapper.updateCheckCashApply(cashrecordInfo);
				if(i<1){
					return layUIResult.resultErr(201, "审核失败,状态已审核了!");
				}
				if(state==-1){ //审核失败
					try {
						//查询金额
						CashrecordInfo ccI = appMapper.findCashrecordId(cashrecordInfo);					
						auI.setId(ccI.getUserID());
						auI.setWithdrawable(ccI.getAmount());  // 设置加回金额
						auI.setLastModiftyTime(new Date());
						//把金额加回用户
						appMapper.updateAppUserWithdrawable(auI);
					} catch (Exception e) {
						cashrecordInfo.setId(id);
						cashrecordInfo.setDoneTime(new Date());
						cashrecordInfo.setDes("");
						cashrecordInfo.setState(0);
						appMapper.updateCheckCashApplyStauts0(cashrecordInfo);
					}
									
				}else if(state==1){
					try {
						// 已提现金额
						//查询金额
						CashrecordInfo ccI = appMapper.findCashrecordId(cashrecordInfo);					
						auI.setId(ccI.getUserID());
						auI.setGrossIncome(ccI.getAmount()); // 设置加回金额
						auI.setLastModiftyTime(new Date());
						//加已提现金额
						appMapper.updateAppUserGrossIncome(auI);
					} catch (Exception e) {
						cashrecordInfo.setId(id);
						cashrecordInfo.setDoneTime(new Date());
						cashrecordInfo.setDes("");
						cashrecordInfo.setState(0);
						appMapper.updateCheckCashApplyStauts0(cashrecordInfo);
					}
					
				}
				// 只记录保存成功的日志
			    SysLogInfo sysLogInfo = new SysLogInfo();
			    sysLogInfo.setDescription("审核内容："+cashrecordInfo.toString()+","+auI.toString());
			    sysLogService.InsertSysLog(sysLogInfo,request);
				new layUIResult();				
				return layUIResult.ok();
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			log.error(e.getMessage());
			return layUIResult.resultErr(201, "审核失败");
		}
	}
	//
	
	//获取批量审核信息
	@SuppressWarnings("static-access")
	public Map<String,Object> findCheckCashApplyAll(String end ,String start,Integer type,Integer merID){
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("end", end);
			map.put("start", start);
			map.put("type", type);
			map.put("merID",merID);
			List<Map<String,Object>> taskList = appMapper.findCheckCashApplyAll(map);		
			//List<UserInfo> pageuserList = userMapper.userlist();		
			
			return new layUIResult().ok(taskList);
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}
		
	}
	private static Object ob = "aa";
	// a批量审核 
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> updateCheck_cashAplyAll(String list ,String des,Integer state,HttpServletRequest request){
		synchronized(ob){
			String str = null;
			String mags = "审核失败";
			//int strStatus  = 1;
			CashrecordInfo cashrecordInfo  = new CashrecordInfo();
			try {	
				String[] ids = list.split(",");
				for(int i = 0;i<ids.length;i++){
					
					AppUserInfo auI = new AppUserInfo();
					cashrecordInfo.setId(Integer.valueOf(ids[i]));
					cashrecordInfo.setDoneTime(new Date());
					cashrecordInfo.setDes(des);
					cashrecordInfo.setState(state);
					cashrecordInfo.setCreateDate(Integer.valueOf(new SimpleDateFormat("yyyyMMdd").format(new Date())));
					int a = appMapper.updateCheckCashApply(cashrecordInfo);
					if(a<1){
						mags = "审核失败,状态已审核了!";
						System.out.println(str.toString()); //调用异常					
					}
					if(state==-1){ //审核失败
						
							//查询金额
							CashrecordInfo ccI = appMapper.findCashrecordId(cashrecordInfo);					
							auI.setId(ccI.getUserID());
							auI.setWithdrawable(ccI.getAmount());  // 设置加回金额
							auI.setLastModiftyTime(new Date());
							//把金额加回用户
							appMapper.updateAppUserWithdrawable(auI);
								
					}else if(state==1){
						
							// 已提现金额
							//查询金额
							CashrecordInfo ccI = appMapper.findCashrecordId(cashrecordInfo);					
							auI.setId(ccI.getUserID());
							auI.setGrossIncome(ccI.getAmount()); // 设置加回金额
							auI.setLastModiftyTime(new Date());
							//加已提现金额
							appMapper.updateAppUserGrossIncome(auI);
						
					}
				}
				// System.out.println(str.toString());	测试
					
					// 只记录保存成功的日志
				    SysLogInfo sysLogInfo = new SysLogInfo();
				    sysLogInfo.setDescription("审核内容："+cashrecordInfo.toString()+",id = "+list.toString() + "状态="+state);
				    sysLogService.InsertSysLog(sysLogInfo,request);
					new layUIResult();				
					return layUIResult.ok();
			} catch (Exception e) {
				//strStatus =2;
				e.printStackTrace();
				log.error(e.getMessage());
				//throw new RuntimeException("XXXXXXXXXX");   // 事务回滚
				 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
				 return layUIResult.resultErr(201, mags);
			}finally{
				//if(strStatus ==2){
					
				//}
			}
		}
		
	}
	
	//配置新增
	public Map<String, Object> saveSetting(String setKey ,String setValue,String des,HttpServletRequest request){
		try {			
				SettingInfo setting = new SettingInfo();
				setting.setSetKey(setKey);
				int i = appMapper.findSettingName(setting);
				if(i>0){
					return layUIResult.resultErr(201, "新增配置失败,setKey 名字已存在!");
				}
				if(!"".equals(setValue)&&setValue!=null){
					setting.setSetValue(setValue.replace("-", ","));
				}else{
					setting.setSetValue("");
				}
				setting.setDes(des);
				appMapper.saveSetting(setting);
				// 只记录保存成功的日志
			    SysLogInfo sysLogInfo = new SysLogInfo();
			    sysLogInfo.setDescription("新增配置："+setting.toString());
			    sysLogService.InsertSysLog(sysLogInfo,request);
				new layUIResult();				
				return layUIResult.ok();
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			log.error(e.getMessage());
			return layUIResult.resultErr(201, "新增配置失败");
		}
	}
	
	//查询
	//返回值为了前台的分页条，两个数据：记录总数，当前页数据
	@SuppressWarnings("static-access")
	public Map<String,Object> findSetting(Integer page, Integer rows,String name,String start,String end){
		SettingInfo setting = new SettingInfo();
		try {
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//调用分页，标识分页开启，拦截器规定，这个开启后的第一条执行查询的SQL语句会被分页！！！
			PageHelper.startPage(page, rows);
			setting.setSetKey(name);
			
			List<Map<String,Object>> taskList = appMapper.findSetting(setting);		
			//List<UserInfo> pageuserList = userMapper.userlist();
			PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(taskList);		
			return new layUIResult().result(pageInfo.getTotal(),taskList);
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}
		
	}
	// id 查询
	@SuppressWarnings("static-access")
	public Map<String,Object> findSettingId(Integer id){
		try {
			SettingInfo setting = new SettingInfo();
			setting.setId(id);
			List<Map<String,Object>> taskList = appMapper.findSetting(setting);		
			//List<UserInfo> pageuserList = userMapper.userlist();
			PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(taskList);		
			return new layUIResult().result(pageInfo.getTotal(),taskList);
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}
		
	}
	
	//配置修改
	public Map<String, Object> updaetSetting(Integer id,String setKey ,String setValue,String des,HttpServletRequest request){
		try {			
				SettingInfo setting = new SettingInfo();
				setting.setSetKey(setKey);
				int i = appMapper.findSettingName(setting);
				if(i>1){
					return layUIResult.resultErr(201, "修改配置失败,setKey 名字已存在!");
				}
				setting.setId(id);
				if(!"".equals(setValue)&&setValue!=null){
					setting.setSetValue(setValue.replace("-", ","));
				}else{
					setting.setSetValue("");
				}
				setting.setDes(des);
				appMapper.updaetSetting(setting);
				// 只记录保存成功的日志
			    SysLogInfo sysLogInfo = new SysLogInfo();
			    sysLogInfo.setDescription("修改配置："+setting.toString());
			    sysLogService.InsertSysLog(sysLogInfo,request);
				new layUIResult();				
				return layUIResult.ok();
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			log.error(e.getMessage());
			return layUIResult.resultErr(201, "修改公告失败");
		}
	}
	
	//删除配置
	public Map<String, Object> deleteSetting (String id,HttpServletRequest request){
		SettingInfo setting = new SettingInfo();
		try {
			String[] ids = id.split(",");
			for(int i = 0;i<ids.length;i++){
				setting.setId(Integer.valueOf(ids[i]));
				
				appMapper.deleteSetting(setting);
			}
			// 只记录保存成功的日志
		    SysLogInfo sysLogInfo = new SysLogInfo();
		    sysLogInfo.setDescription("删除配置：内容-id："+ id );
		    sysLogService.InsertSysLog(sysLogInfo,request);
			new layUIResult();				
			return layUIResult.ok();
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			log.error(e.getMessage());
			return layUIResult.resultErr(201, "删除配置失败");
		}
	}
	// 查询apk
	public Map<String, Object> findApk (){
		
		try {	
		    List<Map<String,Object>> list = appMapper.findApk();
			new layUIResult();				
			return layUIResult.ok(list);
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			log.error(e.getMessage());
			return layUIResult.resultErr(201, "查询apk失败!");
		}
	}
	
	
	//apk修改
	public Map<String, Object> updateApk(Integer version ,Integer id,String appUrl,Integer type,HttpServletRequest request){
		try {			
				AppversionInfo appApk = new AppversionInfo();
				appApk.setVersion(version);	
				
				 if(!"".equals(appUrl)&& appUrl!=null&&appUrl.contains("file")){
					 appApk.setAppUrl(appUrl);
				 }else if(!"".equals(appUrl)&& appUrl!=null){
					 appApk.setAppUrl(httpFileUrl+appUrl);
				}else{
					 appApk.setAppUrl("");
				}
				
				
				appApk.setType(type);
				appApk.setLastModiftyTime(new Date());
				appApk.setId(id);
				//setting.set
				appMapper.updateApk(appApk);
							
				// 只记录保存成功的日志
			    SysLogInfo sysLogInfo = new SysLogInfo();
			    sysLogInfo.setDescription("apk更新修改："+appApk.toString());
			    sysLogService.InsertSysLog(sysLogInfo,request);
				new layUIResult();				
				return layUIResult.ok();
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			log.error(e.getMessage());
			return layUIResult.resultErr(201, "apk更新失败");
		}
	}
	
	
	//复制任务
	public Map<String, Object> copyAllTask(String id ,HttpServletRequest request){
		TaskInfo taskInfo = new TaskInfo();  // 用于存数据
		TaskInfo taskInfoID = new TaskInfo();  // 用于Id 查询
		try {
			String[] ids = id.split(",");
			
			for(int i = 0;i<ids.length;i++){
				
				taskInfoID.setId(Integer.valueOf(ids[i]));				
				List<Map<String,Object>> taskList = appMapper.findAppTask(taskInfoID);
				if(taskList!=null&& taskList.size()>0){
					taskInfo.setType(Integer.valueOf(taskList.get(0).get("type").toString()));
					taskInfo.setName(taskList.get(0).get("name").toString());
					taskInfo.setDes(taskList.get(0).get("des").toString());
					taskInfo.setImgUrl(taskList.get(0).get("imgUrl").toString());
					taskInfo.setBonus(Float.valueOf(taskList.get(0).get("bonus").toString()));
					taskInfo.setWebUrl(taskList.get(0).get("webUrl").toString());
					taskInfo.setIsDel(1);
					taskInfo.setContents(taskList.get(0).get("contents").toString());
					taskInfo.setLastModiftyTime(new Date());
					taskInfo.setStatus(1);
					appMapper.saveTask(taskInfo);
				}
				
			}
				
				// 只记录保存成功的日志
			    SysLogInfo sysLogInfo = new SysLogInfo();
			    sysLogInfo.setDescription("批量复制任务：id="+id);
			    sysLogService.InsertSysLog(sysLogInfo,request);
				new layUIResult();				
				return layUIResult.ok();
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			log.error(e.getMessage());
			return layUIResult.resultErr(201, "新增appTask任务失败");
		}
	}
	
	
	
	//查询所有商户统计
	//返回值为了前台的分页条，两个数据：记录总数，当前页数据
	@SuppressWarnings("static-access")
	public Map<String,Object> findMhtotaldata(Integer page, Integer rows,String merID,String start ,String end){
		Map<String ,Object> map = new HashMap<String,Object>();
		try {
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//调用分页，标识分页开启，拦截器规定，这个开启后的第一条执行查询的SQL语句会被分页！！！
			PageHelper.startPage(page, rows);
			
			map.put("merID", merID);
			map.put("start", start);
			map.put("end", end);
			
			List<Map<String,Object>> taskList = appMapper.findMhtotaldata(map);		
			//List<UserInfo> pageuserList = userMapper.userlist();
			PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(taskList);		
			return new layUIResult().result(pageInfo.getTotal(),taskList);
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}
		
	}
	//查询总统计
	//返回值为了前台的分页条，两个数据：记录总数，当前页数据
	@SuppressWarnings("static-access")
	public Map<String,Object> findTotaldata(Integer page, Integer rows,String start ,String end,String dateInt){
		Map<String ,Object> map = new HashMap<String,Object>();
		try {
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//调用分页，标识分页开启，拦截器规定，这个开启后的第一条执行查询的SQL语句会被分页！！！
			PageHelper.startPage(page, rows);
			
			
			map.put("start", start);
			map.put("end", end);
			if(!"".equals(dateInt)&&dateInt!=null){
				map.put("dateInt", Integer.valueOf(dateInt.replace("-", "")));
			}
			
			List<Map<String,Object>> taskList = appMapper.findTotaldata(map);		
			//List<UserInfo> pageuserList = userMapper.userlist();
			PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(taskList);		
			return new layUIResult().result(pageInfo.getTotal(),taskList);
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}
		
	}
	// 批量修改IMEI
	public Map<String, Object> updateIMEI(String id ,HttpServletRequest request){
		
		AppUserInfo appUser = new AppUserInfo();  // 用于Id 查询
		try {
			String[] ids = id.split(",");
			
			for(int i = 0;i<ids.length;i++){
				
				appUser.setId(Integer.valueOf(ids[i]));		
				appUser.setImei(null);
				appUser.setLastModiftyTime(new Date());
				appMapper.updateIMEI(appUser);
				
			}
				
				// 只记录保存成功的日志
			    SysLogInfo sysLogInfo = new SysLogInfo();
			    sysLogInfo.setDescription("批量修改INEI：id="+id);
			    sysLogService.InsertSysLog(sysLogInfo,request);
				new layUIResult();				
				return layUIResult.ok();
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			log.error(e.getMessage());
			return layUIResult.resultErr(201, "批量修改INEI任务失败");
		}
	}
	// 查询taskAVG
	public Map<String, Object> taskAVG (){
		
		try {	
		    Map<String,Object> list = appMapper.taskAVG();
			new layUIResult();				
			return layUIResult.ok(list);
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			log.error(e.getMessage());
			return layUIResult.resultErr(201, "查询taskAVG失败!");
		}
	}
	
	
	//返回值为了前台的分页条，两个数据：记录总数，当前页数据
	@SuppressWarnings("static-access")
	public Map<String,Object> findacusertotaldate(Integer page, Integer rows,String name,String start ,String end,String merID,String MHName){
		Map<String ,Object> map = new HashMap<String,Object>();
		try {
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			//调用分页，标识分页开启，拦截器规定，这个开启后的第一条执行查询的SQL语句会被分页！！！
			PageHelper.startPage(page, rows);
			
			map.put("userName", name);
			map.put("MHName", MHName);
			
			if(end!=null && !"".equals(end)){
				map.put("end", end.replace("-", ""));
			}
			if(start!=null && !"".equals(start)){
				map.put("start", start.replace("-", ""));
			}
			
			
			if(merID!=null && !"".equals(merID)){
				map.put("merID", merID);
			}
			List<Map<String,Object>> taskList = appMapper.findacusertotaldate(map);		
			//List<UserInfo> pageuserList = userMapper.userlist();
			PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(taskList);		
			return new layUIResult().result(pageInfo.getTotal(),taskList);
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}
		
	}	
	
	
	//查询隔天审核记录
	
	@SuppressWarnings("static-access")
	public Map<String,Object> findDatecashrecord(Integer page, Integer rows,String start,String  merID,String type,Integer state,String startDate,String endDate){
		Map<String ,Object> map = new HashMap<String,Object>();
		try {
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//调用分页，标识分页开启，拦截器规定，这个开启后的第一条执行查询的SQL语句会被分页！！！
			PageHelper.startPage(page, rows);
			
			if(!"".equals(state) && state!=null){
				map.put("state", state);
			}
			if(!"".equals(merID) && merID!=null){
				map.put("merID", merID);
			}
			if(!"".equals(type) && type!=null){
				map.put("type", type);
			}
			if(!"".equals(start) && start!=null){
				map.put("start", start.replace("-", ""));
			}
			if(!"".equals(startDate) && startDate!=null){
				map.put("startDate", startDate.replace("-", ""));
			}
			if(!"".equals(endDate) && endDate!=null){
				map.put("endDate", endDate.replace("-", ""));
			}
			
			List<Map<String,Object>> taskList = appMapper.findDatacashrecord(map);	
			//List<UserInfo> pageuserList = userMapper.userlist();
			PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(taskList);		
			return new layUIResult().result(pageInfo.getTotal(),taskList);
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}
		
	}
	
	
	//获取隔天审核信息
	@SuppressWarnings("static-access")
	public Map<String,Object> findOnDatecheck_cashApply(Integer createDate,Integer method,Integer state,Integer mhId){
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("createDate", createDate);
			map.put("method", method);
			map.put("mhId", mhId);
			List<Map<String,Object>> taskList = appMapper.findOnDatecheck_cashApply(map);		
			//List<UserInfo> pageuserList = userMapper.userlist();		
			
			return new layUIResult().ok(taskList);
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}
		
	}
	
	private static Object ac = "aa";
	// a隔天审核 
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> updateOnDatecheck_cashApply(String list  ,String des,Integer state,Integer method,Integer mhId,Integer createDate ,HttpServletRequest request){
		synchronized(ac){
			String str = null;
			String mags = "审核失败";
			//int strStatus  = 1;
			CashrecordInfo cashrecordInfo  = new CashrecordInfo();
			try {	
				String[] ids = list.split(",");
				for(int i = 0;i<ids.length;i++){
					
					AppUserInfo auI = new AppUserInfo();
					cashrecordInfo.setId(Integer.valueOf(ids[i]));
					cashrecordInfo.setDoneTime(new Date());
					cashrecordInfo.setDes(des);
					cashrecordInfo.setState(state);
					cashrecordInfo.setCreateDate(Integer.valueOf(new SimpleDateFormat("yyyyMMdd").format(new Date())));
					int a = appMapper.updateCheckCashApply(cashrecordInfo);
					if(a<1){
						mags = "审核失败,状态已审核了!";
						System.out.println(str.toString()); //调用异常					
					}
					if(state==-1){ //审核失败
						
							//查询金额
							CashrecordInfo ccI = appMapper.findCashrecordId(cashrecordInfo);					
							auI.setId(ccI.getUserID());
							auI.setWithdrawable(ccI.getAmount());  // 设置加回金额
							auI.setLastModiftyTime(new Date());
							//把金额加回用户
							appMapper.updateAppUserWithdrawable(auI);
								
					}else if(state==1){
						
							// 已提现金额
							//查询金额
							CashrecordInfo ccI = appMapper.findCashrecordId(cashrecordInfo);					
							auI.setId(ccI.getUserID());
							auI.setGrossIncome(ccI.getAmount()); // 设置加回金额
							auI.setLastModiftyTime(new Date());
							//加已提现金额
							appMapper.updateAppUserGrossIncome(auI);
						
					}
				}
				// 修改隔日数据表
				DataCashrecordInfo  dic = new DataCashrecordInfo();
				dic.setDoneTime(new Date());
				
				dic.setState(state);
				dic.setDes(des);
				dic.setMethod(method);
				dic.setCreateDate(createDate);
				dic.setMhId(mhId);
				appMapper.updaetDataCashrecord(dic);
				// System.out.println(str.toString());	测试
					
					// 只记录保存成功的日志
				    SysLogInfo sysLogInfo = new SysLogInfo();
				    sysLogInfo.setDescription("审核内容："+cashrecordInfo.toString()+",id = "+list.toString() + "状态="+state);
				    sysLogService.InsertSysLog(sysLogInfo,request);
					new layUIResult();				
					return layUIResult.ok();
			} catch (Exception e) {
				//strStatus =2;
				e.printStackTrace();
				log.error(e.getMessage());
				//throw new RuntimeException("XXXXXXXXXX");   // 事务回滚
				 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
				 return layUIResult.resultErr(201, mags);
			}finally{
				//if(strStatus ==2){
					
				//}
			}
		}
		
	}
	
	
	
	
	//代理审核新增
	public Map<String, Object> addagency(Integer agencyId ,Integer mhId,String des,Integer count,HttpServletRequest request){
		try {	
			SettingInfo setting = new SettingInfo();
			AgencycashrecordInfo agency = new AgencycashrecordInfo();
			agency.setAgencyId(agencyId);			
			agency.setCount(count);
			agency.setDes(des);
			agency.setMhId(mhId);
			agency.setStatus(0);
			agency.setCreateDate(Integer.valueOf(new SimpleDateFormat("yyyyMMdd").format(new Date())));
			agency.setCreateTime(new Date());
			setting.setSetKey("agency");
			List<Map<String,Object>>  list = appMapper.findSetting(setting);
			agency.setAmount(Float.valueOf(list.get(0).get("setValue").toString()) * count); // 求出总数
		    appMapper.addagency(agency);
		    // 只记录保存成功的日志
		    SysLogInfo sysLogInfo = new SysLogInfo();
		    sysLogInfo.setDescription("代理审核新增："+agency.toString());
		    sysLogService.InsertSysLog(sysLogInfo,request);
			new layUIResult();				
			return layUIResult.ok();
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			log.error(e.getMessage());
			return layUIResult.resultErr(201, "代理审核新增失败");
		}
	}
	// 查询代理审核记录
	@SuppressWarnings("static-access")
	public Map<String,Object> findAgency(Integer page, Integer rows,String start,String end,String mhId,String agencyId,Integer status){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//调用分页，标识分页开启，拦截器规定，这个开启后的第一条执行查询的SQL语句会被分页！！！
			PageHelper.startPage(page, rows);
			Map<String ,Object> map = new HashMap<String,Object>();
		
			if(start!=null&&!"".equals(start)){
				map.put("start", sdf.parse(start));
			}
			if(end!=null && !"".equals(end)){
				
				map.put("setLastModiftyTime", sdf.parse(end));
			}
			if(mhId!=null && !"".equals(mhId)){
				
				map.put("mhId",mhId);
			} 
			if(agencyId!=null && !"".equals(agencyId)){
				
				map.put("agencyId", agencyId);
			} 			
			if(status!=null && !"".equals(status)){
				map.put("status", status);
			}
			List<Map<String,Object>> agencyList = appMapper.findAgency(map);		
			
			PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(agencyList);
		
			return new layUIResult().result(pageInfo.getTotal(),agencyList);
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}
		
	}
	
	//查询审核信息 --代理
	@SuppressWarnings("static-access")
	public Map<String,Object> findAgencyCheck(Integer id, String status ){
		try {
			
			AgencycashrecordInfo agency = new AgencycashrecordInfo();
			if(status!=null && !"".equals(status)){
				if(Integer.valueOf(status) !=0){
					return layUIResult.resultErr(201, "当前状态不是未审核");
				}
			}
			agency.setId(id);
			List<Map<String,Object>> agencyList = appMapper.findAgencyCheck(agency);	
		
			return new layUIResult().ok(agencyList);
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}
		
	}
	//修改审核信息  --代理
	private static Object ag = "aa";
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> updateAgencyCheck(Integer id,String status ,String des  ,HttpServletRequest request){
		synchronized(ag){
			String mags = "审核失败";
			//int strStatus  = 1;
			AgencycashrecordInfo agency = new AgencycashrecordInfo();
			try {	
				
					if(Integer.valueOf(status)==0){
						mags = "审核失败,审核状态不对!";	
						return layUIResult.resultErr(201, mags);
					}
					agency.setId(id);
					agency.setDoneTime(new Date());
					agency.setDes(des);
					agency.setStatus(Integer.valueOf(status));
					int a = appMapper.updateAgencyCheck(agency);
					if(a<1){
						mags = "审核失败,状态已审核了!";	
						return layUIResult.resultErr(201, mags);
					}
					
					new layUIResult();				
					return layUIResult.ok();
			} catch (Exception e) {
				//strStatus =2;
				e.printStackTrace();
				log.error(e.getMessage());
				//throw new RuntimeException("XXXXXXXXXX");   // 事务回滚
				 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
				 return layUIResult.resultErr(201, mags);
			}finally{
				// 记录的日志
			    SysLogInfo sysLogInfo = new SysLogInfo();
			    sysLogInfo.setDescription("审核内容："+agency.toString()+ "状态="+status);
			    sysLogService.InsertSysLog(sysLogInfo,request);
			}
		}
		
	}
	
}


