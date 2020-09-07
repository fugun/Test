package com.service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.vo.layUIResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mapper.UserMapper;
import com.pojo.Merchant;
import com.pojo.SysLogInfo;
import com.pojo.UserInfo;
import com.util.service.BaseService;
import com.util.service.RedisService;
@Service
public class UserService extends BaseService<UserInfo>{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private SysLogService sysLogService;
	@Autowired 
	private RedisService redisService;
	private static final Logger log = Logger.getLogger(UserService.class);
	    //查询用户
		public UserInfo findByAccount(UserInfo user ){
			return userMapper.findByAccount(user);
		}
		//查询所有用户
		//返回值为了前台的分页条，两个数据：记录总数，当前页数据
		@SuppressWarnings("static-access")
		public Map<String,Object> list(Integer page, Integer rows,String username,String start,String end){
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
			   
				List<Map<String,Object>> userList = userMapper.list(userInfo);		
				//List<UserInfo> pageuserList = userMapper.userlist();
				PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(userList);
				//写入缓存
				String ITEM_KEY = "ITEM_" + page+rows;
				//缓存商品，会设置自动过期时间，30天过期
				redisService.set(ITEM_KEY,new JSONArray(userList).toString());//存储数据
				redisService.expire(ITEM_KEY, 60*60*24*30);
				System.out.println(redisService.get(ITEM_KEY));
				return new layUIResult().result(pageInfo.getTotal(),userList);
					
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
				return null;
			}
			
		}
		//修改用户
		public Map<String, Object> updateUser(Integer id, String loginName,String email,HttpServletRequest request){
			try {
				UserInfo userInfo = new UserInfo();
				userInfo.setLoginName(loginName);
				// 查询名字是否存在
				int i = userMapper.findUserCaount(userInfo);
			    if(i>1){
			    	return layUIResult.resultErr(201, "修改用户失败-用户名已存在");
			    }
			
				userInfo.setId(id);
				userInfo.setEmail(email);
				userInfo.setUpdated(new Date());
				userMapper.updateUser(userInfo);
				// 只记录保存成功的日志
			    SysLogInfo sysLogInfo = new SysLogInfo();
			    sysLogInfo.setDescription("修改用户：内容-id:"+id +",登录名字："+ loginName + ",email:"+email );
			    sysLogService.InsertSysLog(sysLogInfo,request);
				new layUIResult();				
				return layUIResult.ok();
			} catch (Exception e) {
				//todo log
				e.printStackTrace();
				return layUIResult.resultErr(201, "修改用户失败");
			}
		}	
		
		//修改用户密码
		public Map<String, Object> UserPassword(Integer id, String password,Integer status ,HttpServletRequest request){
			try {
				UserInfo userInfo = new UserInfo();
				userInfo.setId(id);
				if(!"".equals(password) && password!=null){
					userInfo.setPassword(password);
				}
				if(status!=null && !"".equals(status)){
					userInfo.setStatus(status);
				}
				
				userInfo.setUpdated(new Date());
				userMapper.updateUserPassword(userInfo);
				// 只记录保存成功的日志
			    SysLogInfo sysLogInfo = new SysLogInfo();
			    sysLogInfo.setDescription("修改用户密码：内容-id:"+id +",登录密码："+ password + ",状态:"+ status);
			    sysLogService.InsertSysLog(sysLogInfo,request);
				new layUIResult();				
				return layUIResult.ok();
			} catch (Exception e) {
				//todo log
				e.printStackTrace();
				log.error(e.getMessage());
				return layUIResult.resultErr(201, "修改密码失败");
			}
		}
		
		//新增用户
		public Map<String, Object> addUser(String loginName,String email ,String password,HttpServletRequest request){
			try {
				UserInfo userInfo = new UserInfo();
				userInfo.setLoginName(loginName);
				// 查询名字是否存在				
				UserInfo userList = userMapper.findByAccount(userInfo);
			    if(userList!=null){
			    	return layUIResult.resultErr(201, "新增用户失败-用户名已存在");
			    }			
				userInfo.setEmail(email);
				userInfo.setCreated(new Date());
				userInfo.setStatus(1);
				userInfo.setPassword(password);
				userInfo.setDeleteStatus(1);
				userMapper.addUser(userInfo);
				// 只记录保存成功的日志
			    SysLogInfo sysLogInfo = new SysLogInfo();
			    sysLogInfo.setDescription("新增用户：内容-登录名字："+ loginName + ",email:"+email +",登录密码："+ password);
			    sysLogService.InsertSysLog(sysLogInfo,request);
				new layUIResult();				
				return layUIResult.ok();
			} catch (Exception e) {
				//todo log
				e.printStackTrace();
				log.error(e.getMessage());
				return layUIResult.resultErr(201, "新增用户失败");
			}
		}	
		//删除用户
		public Map<String, Object> deleteUser(String id,HttpServletRequest request){
			UserInfo userInfo = new UserInfo();
			try {
				String[] ids = id.split(",");
				for(int i = 0;i<ids.length;i++){
					userInfo.setId(Integer.valueOf(ids[i]));
					userInfo.setDeleteStatus(2); // 2删除状态
					userInfo.setUpdated(new Date());
					userMapper.deleteUser(userInfo);
				}
				// 只记录保存成功的日志
			    SysLogInfo sysLogInfo = new SysLogInfo();
			    sysLogInfo.setDescription("删除用户：内容-id："+ id );
			    sysLogService.InsertSysLog(sysLogInfo,request);
				new layUIResult();				
				return layUIResult.ok();
			} catch (Exception e) {
				//todo log
				e.printStackTrace();
				log.error(e.getMessage());
				return layUIResult.resultErr(201, "删除用户失败");
			}
		}	
		
		//查询所有帐号
		//返回值为了前台的分页条，两个数据：记录总数，当前页数据
		@SuppressWarnings("static-access")
		public Map<String,Object> findpayaccont(Integer page, Integer rows,String name,String start,String end){
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//调用分页，标识分页开启，拦截器规定，这个开启后的第一条执行查询的SQL语句会被分页！！！
				PageHelper.startPage(page, rows);
				Merchant payInfo = new Merchant();
				payInfo.setName(name);
				//sdf.parse("2005-12-15 15:30:23")
				if(start!=null&&!"".equals(start)){
					payInfo.setCreateTime(sdf.parse(start));
				}
				if(end!=null && !"".equals(end)){
					payInfo.setLastModiftyTime(sdf.parse(end));
				}
			   
				List<Map<String,Object>> userList = userMapper.findPaymentaccountInfo(payInfo);		
				//List<UserInfo> pageuserList = userMapper.userlist();
				PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(userList);
				return new layUIResult().result(pageInfo.getTotal(),userList);
					
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
				return null;
			}
			
		}
		
		//新增
		public Map<String, Object> addPayAccount(String zfbName ,String BankName ,String BranchBankName,String userName,String name,String acPay1,String acPay2,String des ,HttpServletRequest request){
			try {
				JSONArray jArray = new JSONArray();
				Map<String,Object> map  = new HashMap<String,Object>();
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				Merchant payInfo = new Merchant();
				payInfo.setName(name);
				// 查询名字是否存在				
				Merchant payInfoOn = userMapper.findPaymentaccountName(payInfo);
			    if(payInfoOn!=null){
			    	return layUIResult.resultErr(201, "新增商户失败-商户名已存在");
			    }			
			    payInfo.setCreateTime(new Date());
			    payInfo.setIsDel(1); //1为正常
			    
			    map.put("account", acPay1);
			    map.put("userName", zfbName);
			    //map.put("BranchBankName", "");
			    //map.put("BankName", "");
			    list.add(map);
			    Map<String,Object> maps = new HashMap<String,Object>();
			    maps.put("account", acPay2);
			    maps.put("userName", userName);
			    maps.put("BranchBankName", BranchBankName);
			    maps.put("BankName", BankName);
			    list.add(maps);
			   // payInfo.setPayInfo("{\"1\":"+acPay1+",\"2\":"+acPay2+"}");
			    
			    payInfo.setPayInfo(jArray.fromObject(list).toString());
			    payInfo.setDes(des);			    
				userMapper.addPaymerchant(payInfo);
				// 只记录保存成功的日志
			    SysLogInfo sysLogInfo = new SysLogInfo();
			    sysLogInfo.setDescription("新增商户：内容-商户名字："+ name + ",支付帐号:"+acPay1 +","+acPay1+",备注："+ des);
			    sysLogService.InsertSysLog(sysLogInfo,request);
				new layUIResult();				
				return layUIResult.ok();
			} catch (Exception e) {
				//todo log
				e.printStackTrace();
				log.error(e.getMessage());
				return layUIResult.resultErr(201, "新增商户失败");
			}
		}
		
		//修改帐号
		public Map<String, Object> updatePayAccount(Integer id ,String zfbName ,String BankName ,String BranchBankName,String userName,String name,String acPay1,String acPay2,String des ,HttpServletRequest request){
			try {
				JSONArray jArray = new JSONArray();
				Map<String,Object> map  = new HashMap<String,Object>();
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				Merchant payInfo = new Merchant();
				payInfo.setName(name);
				// 查询名字是否存在				
				int i = userMapper.findPaymentaccountCount(payInfo);
			    if(i>1){
			    	return layUIResult.resultErr(201, "修改商户失败-帐号名已存在");
			    }	
			    payInfo.setId(id);
			    map.put("account", acPay1);
			    map.put("userName", zfbName);
			    //map.put("BranchBankName", "");
			    //map.put("BankName", "");
			    list.add(map);
			    Map<String,Object> maps = new HashMap<String,Object>();
			    maps.put("account", acPay2);
			    maps.put("userName", userName);
			    maps.put("BranchBankName", BranchBankName);
			    maps.put("BankName", BankName);
			    list.add(maps);
			   // payInfo.setPayInfo("{\"1\":"+acPay1+",\"2\":"+acPay2+"}");
			    
			    payInfo.setPayInfo(jArray.fromObject(list).toString());
			    payInfo.setDes(des);
				payInfo.setLastModiftyTime(new Date());
				userMapper.updatePaymentaccount(payInfo);
				// 只记录保存成功的日志
			    SysLogInfo sysLogInfo = new SysLogInfo();
			    sysLogInfo.setDescription("修改商户：内容-id:"+id +",商户名："+ name + ",支付帐号:"+acPay1 +","+acPay1+",备注:"+des);
			    sysLogService.InsertSysLog(sysLogInfo,request);
				new layUIResult();				
				return layUIResult.ok();
			} catch (Exception e) {
				//todo log
				e.printStackTrace();
				log.error(e.getMessage());
				return layUIResult.resultErr(201, "修改商户失败");
			}
		}
		
		
		//删除帐号
		public Map<String, Object> deletePayAccount(String id,HttpServletRequest request){
			Merchant payInfo = new Merchant();
			try {
				String[] ids = id.split(",");
				for(int i = 0;i<ids.length;i++){
					payInfo.setId(Integer.valueOf(ids[i]));
					payInfo.setIsDel(2); // 2删除状态
					payInfo.setLastModiftyTime(new Date());
					userMapper.deletepayAccount(payInfo);
				}
				// 只记录保存成功的日志
			    SysLogInfo sysLogInfo = new SysLogInfo();
			    sysLogInfo.setDescription("删除商户：内容-id："+ id );
			    sysLogService.InsertSysLog(sysLogInfo,request);
				new layUIResult();				
				return layUIResult.ok();
			} catch (Exception e) {
				//todo log
				e.printStackTrace();
				log.error(e.getMessage());
				return layUIResult.resultErr(201, "删除商户失败");
			}
		}	
		
		//查询帐号
		public Map<String, Object> payAccountID(Integer id){
			
			try {
				Merchant payInfo = new Merchant();
				payInfo.setId(id);				
				Merchant payInfoOn = userMapper.findPaymentaccountName(payInfo);
				new layUIResult();				
				return layUIResult.ok(payInfoOn);
			} catch (Exception e) {
				//todo log
				e.printStackTrace();
				log.error(e.getMessage());
				return layUIResult.resultErr(201, "查询商户失败");
			}
		}
		
		// 修改登录时间
		public void updateLoginTime(UserInfo user){
			try {							
				 userMapper.updateLoginTime(user);				
			} catch (Exception e) {
				//todo log
				e.printStackTrace();
				log.error(e.getMessage());
				
			}
		}
}
