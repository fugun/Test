package com.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.MenuMapper;
import com.mapper.RoleMapper;
import com.mapper.UserMapper;
import com.pojo.MenuInfo;
import com.pojo.MenuRoleInfo;
import com.pojo.RoleInfo;
import com.pojo.SysLogInfo;
import com.pojo.UserInfo;
import com.pojo.UserRoleInfo;
import com.util.service.BaseService;
import com.util.service.RedisService;
import com.vo.layUIResult;
@Service
public class RoleService extends BaseService<RoleInfo>{
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private SysLogService sysLogService;
	@Resource 
	private RedisService redisService;
	private static final Logger log = Logger.getLogger(RoleService.class);

		//返回值为了前台的分页条，两个数据：记录总数，当前页数据
		@SuppressWarnings("static-access")
		public Map<String,Object> list(Integer page, Integer rows,String rolename,String start,String end){
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//调用分页，标识分页开启，拦截器规定，这个开启后的第一条执行查询的SQL语句会被分页！！！
				PageHelper.startPage(page, rows);
				RoleInfo roleInfo = new RoleInfo();
				roleInfo.setRoleName(rolename);
				//sdf.parse("2005-12-15 15:30:23")
				if(start!=null&&!"".equals(start)){
					roleInfo.setCreated(sdf.parse(start));
				}
				if(end!=null && !"".equals(end)){
					roleInfo.setUpdated(sdf.parse(end));
				}
			   
				List<Map<String,Object>> roleList = roleMapper.list(roleInfo);		
				//List<UserInfo> pageuserList = userMapper.userlist();
				PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(roleList);
				//写入缓存
				//String ITEM_KEY = "ITEM_" + page+rows;
				//缓存商品，会设置自动过期时间，30天过期
				//redisService.set(ITEM_KEY,new JSONArray(userList).toString());//存储数据
				//redisService.expire(ITEM_KEY, 60*60*24*30);
				//System.out.println(redisService.get(ITEM_KEY));
				return new layUIResult().result(pageInfo.getTotal(),roleList);
					
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
				return null;
			}
			
		}
		
		//修改角色
		public Map<String, Object> updateRole(Integer id, String rolename,HttpServletRequest request ){
			try {
				RoleInfo roleInfo = new RoleInfo();
				roleInfo.setRoleName(rolename);
				// 查询名字是否存在
				RoleInfo roleList = roleMapper.findByAccount(roleInfo);
			    if(roleList!=null){
			    	return layUIResult.resultErr(201, "修改角色失败-角色名已存在");
			    }
			    roleInfo.setId(id);
			    roleInfo.setUpdated(new Date());
			    roleMapper.updateRole(roleInfo);
			    // 只记录保存成功的日志
			    SysLogInfo sysLogInfo = new SysLogInfo();
			    sysLogInfo.setDescription("修改角色：内容-rolename:"+rolename+ "id:" + id);
			    sysLogService.InsertSysLog(sysLogInfo,request);
				new layUIResult();				
				return layUIResult.ok();
			} catch (Exception e) {
				//todo log
				e.printStackTrace();
				return layUIResult.resultErr(201, "修改角色失败");
			}
			//finally{
				// 写日志
				
			//}
		}			
		//新增角色
		public Map<String, Object> addRole( String rolename,HttpServletRequest request ){
			try {
				RoleInfo roleInfo = new RoleInfo();
				roleInfo.setRoleName(rolename);
				// 查询名字是否存在
				RoleInfo roleList = roleMapper.findByAccount(roleInfo);
			    if(roleList!=null){
			    	return layUIResult.resultErr(201, "新增角色失败-角色名已存在");
			    }
			    roleInfo.setDeleteStatus(1);
			    roleInfo.setStatus(1);
			    roleInfo.setCreated(new Date());
			    roleMapper.addRole(roleInfo);
			   // 只记录保存成功的日志
			    SysLogInfo sysLogInfo = new SysLogInfo();
			    sysLogInfo.setDescription("新增角色：内容-rolename:"+rolename);
			    sysLogService.InsertSysLog(sysLogInfo,request);
				new layUIResult();				
				return layUIResult.ok();
			} catch (Exception e) {
				//todo log
				e.printStackTrace();
				return layUIResult.resultErr(201, "新增角色失败");
			}
		}
		//删除角色
		public Map<String, Object> deleteRole(String id,HttpServletRequest request){
			RoleInfo roleInfo = new RoleInfo();
			try {
				String[] ids = id.split(",");
				for(int i = 0;i<ids.length;i++){
					roleInfo.setId(Integer.valueOf(ids[i]));
					roleInfo.setDeleteStatus(2); // 2删除状态
					roleInfo.setUpdated(new Date());
					roleMapper.deleteRole(roleInfo);
				}
				// 只记录保存成功的日志
			    SysLogInfo sysLogInfo = new SysLogInfo();
			    sysLogInfo.setDescription("删除角色：内容-id:"+id);
			    sysLogService.InsertSysLog(sysLogInfo,request);
				new layUIResult();				
				return layUIResult.ok();
			} catch (Exception e) {
				//todo log
				e.printStackTrace();
				log.error(e.getMessage());
				return layUIResult.resultErr(201, "删除角色失败");
			}
		}

		//修改角色状态
		public Map<String, Object> updateRoleStatus(Integer id, Integer status,HttpServletRequest request ){
			try {
				RoleInfo roleInfo = new RoleInfo();
				
			    roleInfo.setId(id);
			    roleInfo.setStatus(status);
			    roleInfo.setUpdated(new Date());
			    roleMapper.updateRoleStatus(roleInfo);
			 // 只记录保存成功的日志
			    SysLogInfo sysLogInfo = new SysLogInfo();
			    sysLogInfo.setDescription("修改角色状态：内容-id:"+id +",状态："+ status);
			    sysLogService.InsertSysLog(sysLogInfo,request);
				new layUIResult();				
				return layUIResult.ok();
			} catch (Exception e) {
				//todo log
				e.printStackTrace();
				return layUIResult.resultErr(201, "修改角色失败");
			}
		}
		
		
		//finduserRole_user_role
		//返回值为了前台的分页条，两个数据：记录总数，当前页数据
		@SuppressWarnings("static-access")
		public Map<String,Object> finduserRole_user_role(Integer page, Integer rows,String username,String start,String end,String rolename){
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//调用分页，标识分页开启，拦截器规定，这个开启后的第一条执行查询的SQL语句会被分页！！！
				PageHelper.startPage(page, rows);
				UserInfo userInfo = new UserInfo();
				userInfo.setLoginName(username);
				//sdf.parse("2005-12-15 15:30:23")
				if(start!=null&&!"".equals(start)){
					userInfo.setCreated(sdf.parse(start));
				}
				if(end!=null && !"".equals(end)){
					 userInfo.setEnaDate(sdf.parse(end));
				}
			    // 查询用户
				List<Map<String,Object>> userList = userMapper.list(userInfo);		
				PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(userList);
				//List<UserInfo> pageuserList = userMapper.userlist();
				if(userList!=null && userList.size()>0){
					for(int i=0;i<userList.size();i++){
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("userId", Integer.valueOf(userList.get(i).get("id").toString()));
						map.put("roleName", rolename);
						List<Map<String,Object>>  role_user_role = roleMapper.finduserRole_user_role(map);
						if(role_user_role!=null && role_user_role.size()>0 ){
							//uri.id as userroleId,uri.userId ,uri.roleId,role.roleName
							// 拼接用户是否己添加到组
							userList.get(i).put("userroleId", role_user_role.get(0).get("userroleId"));
							userList.get(i).put("userId",role_user_role.get(0).get("userId"));
							userList.get(i).put("roleId",role_user_role.get(0).get("roleId"));
							userList.get(i).put("roleName",role_user_role.get(0).get("roleName"));
							// 拼接状态为1 
							userList.get(i).put("roleStatus",1);
						}else{
							// 拼接状态为1 
							userList.get(i).put("roleStatus",2);
						}
							
					}
				}
				//写入缓存
				//String ITEM_KEY = "ITEM_" + page+rows;
				//缓存商品，会设置自动过期时间，30天过期
				//redisService.set(ITEM_KEY,new JSONArray(userList).toString());//存储数据
				//redisService.expire(ITEM_KEY, 60*60*24*30);
				//System.out.println(redisService.get(ITEM_KEY));
				return new layUIResult().result(pageInfo.getTotal(),userList);
					
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
				return null;
			}
			
		}
		
		//删除或添加角色
		public Map<String, Object> addUserRoleInfo(Integer id, Integer status,Integer userRoleId ,Integer roleId,HttpServletRequest request){
			try {
				UserRoleInfo userRoleInfo = new UserRoleInfo();
				if(status==1){ // 添加
					
					userRoleInfo.setUserId(id);
					userRoleInfo.setRoleId(roleId);
					
					roleMapper.UserRoleInfoAdd(userRoleInfo);
				}else{ // 删除
					userRoleInfo.setId(userRoleId);// 权限组id 删除
					roleMapper.UserRoleInfoDelete(userRoleInfo);
				}
				// 只记录保存成功的日志
			    SysLogInfo sysLogInfo = new SysLogInfo();
			    sysLogInfo.setDescription("删除或添加角色：内容-id:"+id +",状态："+ status +"备注：status 为1就是新增 否则删除"+ "角色组Id:" +userRoleId +"角色ID" + roleId);
			    sysLogService.InsertSysLog(sysLogInfo,request);
				new layUIResult();				
				return layUIResult.ok();
			} catch (Exception e) {
				//todo log
				e.printStackTrace();
				return layUIResult.resultErr(201, "操作角色组失败");
			}
		}	
		//批量添加到角色组
		public Map<String, Object> addUserRoleInfo(String id,Integer roleId,HttpServletRequest request){
			UserRoleInfo userRoleInfo = new UserRoleInfo();
			try {
				String[] ids = id.split(",");
				for(int i = 0;i<ids.length;i++){
					String[] userInfoList = ids[i].split("_");
					if(Integer.valueOf(userInfoList[1])==2){ // 未添加
						userRoleInfo.setUserId(Integer.valueOf(userInfoList[0]));
						userRoleInfo.setRoleId(roleId);
						
						roleMapper.UserRoleInfoAdd(userRoleInfo);
					}
					
				}
				// 只记录保存成功的日志
			    SysLogInfo sysLogInfo = new SysLogInfo();
			    sysLogInfo.setDescription("批量添加到角色组：内容-id:"+id +",角色Id："+ roleId);
			    sysLogService.InsertSysLog(sysLogInfo,request);
				new layUIResult();				
				return layUIResult.ok();
			} catch (Exception e) {
				//todo log
				e.printStackTrace();
				log.error(e.getMessage());
				return layUIResult.resultErr(201, "批量操作角色组失败");
			}
		}
		
		//finduserRole_user_role
		//返回值为了前台的分页条，两个数据：记录总数，当前页数据
		@SuppressWarnings("static-access")
		public Map<String,Object> findroleMenu_Menu_role(Integer page, Integer rows,String menuname,String start,String end,String rolename){
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//调用分页，标识分页开启，拦截器规定，这个开启后的第一条执行查询的SQL语句会被分页！！！
				PageHelper.startPage(page, rows);
				MenuInfo menuInfo = new MenuInfo();
				menuInfo.setName(menuname);
				//sdf.parse("2005-12-15 15:30:23")
				if(start!=null&&!"".equals(start)){
					menuInfo.setCreated(sdf.parse(start));
				}
				if(end!=null && !"".equals(end)){
					menuInfo.setUpdated(sdf.parse(end));
				}
			    // 查询菜单
				List<Map<String,Object>> menuList = menuMapper.list(menuInfo);		
				PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(menuList);
				//List<UserInfo> pageuserList = userMapper.userlist();
				if(menuList!=null && menuList.size()>0){
					for(int i=0;i<menuList.size();i++){
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("menuId", Integer.valueOf(menuList.get(i).get("id").toString()));
						map.put("roleName", rolename);
						List<Map<String,Object>>  role_menu_role = roleMapper.findroleMenu_Menu_role(map);
						if(role_menu_role!=null && role_menu_role.size()>0 ){
							//uri.id as userroleId,uri.userId ,uri.roleId,role.roleName
							// 拼接用户是否己添加到组
							menuList.get(i).put("rolemenuId", role_menu_role.get(0).get("rolemenuId"));
							menuList.get(i).put("menuId",role_menu_role.get(0).get("menuId"));
							menuList.get(i).put("roleId",role_menu_role.get(0).get("roleId"));
							menuList.get(i).put("roleName",role_menu_role.get(0).get("roleName"));
							// 拼接状态为1 
							menuList.get(i).put("roleStatus",1);
						}else{
							// 拼接状态为1 
							menuList.get(i).put("roleStatus",2);
						}
							
					}
				}
				//写入缓存
				//String ITEM_KEY = "ITEM_" + page+rows;
				//缓存商品，会设置自动过期时间，30天过期
				//redisService.set(ITEM_KEY,new JSONArray(userList).toString());//存储数据
				//redisService.expire(ITEM_KEY, 60*60*24*30);
				//System.out.println(redisService.get(ITEM_KEY));
				return new layUIResult().result(pageInfo.getTotal(),menuList);
					
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
				return null;
			}
			
		}
		
		//添加或删除菜单组 状态
		public Map<String, Object> addMenuRoleInfo(Integer id, Integer status,Integer menuRoleId ,Integer roleId,HttpServletRequest request){
			try {
				MenuRoleInfo menuRoleInfo = new MenuRoleInfo();
				if(status==1){ // 添加
					
					menuRoleInfo.setMenuId(id);
					menuRoleInfo.setRoleId(roleId);
					
					roleMapper.menuRoleInfoAdd(menuRoleInfo);
				}else{ // 删除
					menuRoleInfo.setId(menuRoleId);// 权限组id 删除
					roleMapper.menuRoleInfoDelete(menuRoleInfo);
				}
				// 只记录保存成功的日志
			    SysLogInfo sysLogInfo = new SysLogInfo();
			    sysLogInfo.setDescription("添加或删除菜单组：内容-id:"+id +",状态："+ status +"备注：status 为1就是新增 否则删除"+ "菜单组Id:" +menuRoleId +"角色ID" + roleId);
			    sysLogService.InsertSysLog(sysLogInfo,request);
				new layUIResult();				
				return layUIResult.ok();
			} catch (Exception e) {
				//todo log
				e.printStackTrace();
				return layUIResult.resultErr(201, "操作角色组失败");
			}
		}	
		//批量添加到菜单组
		public Map<String, Object> addMenuRoleInfo(String id,Integer roleId,HttpServletRequest request){
			MenuRoleInfo menuRoleInfo = new MenuRoleInfo();
			try {
				String[] ids = id.split(",");
				for(int i = 0;i<ids.length;i++){
					String[] menuInfoList = ids[i].split("_");
					if(Integer.valueOf(menuInfoList[1])==2){ // 未添加
						menuRoleInfo.setMenuId(Integer.valueOf(menuInfoList[0]));
						menuRoleInfo.setRoleId(roleId);
						
						roleMapper.menuRoleInfoAdd(menuRoleInfo);
					}
					
				}
				// 只记录保存成功的日志
			    SysLogInfo sysLogInfo = new SysLogInfo();
			    sysLogInfo.setDescription("批量添加到菜单组：内容-id:"+id +",角色Id："+ roleId);
			    sysLogService.InsertSysLog(sysLogInfo,request);
				new layUIResult();				
				return layUIResult.ok();
			} catch (Exception e) {
				//todo log
				e.printStackTrace();
				log.error(e.getMessage());
				return layUIResult.resultErr(201, "批量操作角色组失败");
			}
		}		
				
}		


