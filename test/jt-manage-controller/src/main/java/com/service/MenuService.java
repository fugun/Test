package com.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.MenuMapper;
import com.mapper.RoleMapper;
import com.pojo.MenuInfo;
import com.pojo.SysLogInfo;
import com.util.service.BaseService;
import com.vo.layUIResult;
@Service
public class MenuService extends BaseService<MenuInfo>{
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private SysLogService sysLogService;
	private static final Logger log = Logger.getLogger(MenuService.class);
	//查询所有菜单
	//返回值为了前台的分页条，两个数据：记录总数，当前页数据
	@SuppressWarnings("static-access")
	public Map<String,Object> list(Integer page, Integer rows,String menuname,String start,String end){
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
		   
			List<Map<String,Object>> menuList = menuMapper.list(menuInfo);		
			PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(menuList);
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
	//修改菜单
	public Map<String, Object> updateMenu(Integer id, String menuname ,String url,Integer pid,String iconfont,Integer sort ,HttpServletRequest request){
		try {
			MenuInfo menuInfo = new MenuInfo();
			menuInfo.setName(menuname);
			// 查询名字是否存在
			//int i= menuMapper.findCount(menuInfo);
		    // if(i>1){
		    //	return layUIResult.resultErr(201, "修改菜单-菜单名已存在");
		    // }
		    menuInfo.setId(id);
		    menuInfo.setUrl(url);
		    menuInfo.setPid(pid);
		    menuInfo.setIconfont(iconfont);
		    menuInfo.setSort(sort);
		    menuInfo.setUpdated(new Date());
		    menuMapper.updateMenu(menuInfo);
		    // 只记录保存成功的日志
		    SysLogInfo sysLogInfo = new SysLogInfo();
		    sysLogInfo.setDescription("修改菜单：内容-id:"+id +",菜单名字："+ menuname + ",路径:"+url + ",pid:" +pid +",图标:"+iconfont +",排序:"+sort);
		    sysLogService.InsertSysLog(sysLogInfo,request);
			new layUIResult();				
			return layUIResult.ok();
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			return layUIResult.resultErr(201, "修改菜单失败");
		}
	}			
	//新增菜单
	public Map<String, Object> addMenu( String menuname ,String url,Integer pid,String iconfont,Integer sort ,HttpServletRequest request){
		try {
			MenuInfo menuInfo = new MenuInfo();
			menuInfo.setName(menuname);
			// 查询名字是否存在
			MenuInfo menuList = menuMapper.findByAccount(menuInfo);
		    if(menuList!=null){
		    	return layUIResult.resultErr(201, "新增角色失败-角色名已存在");
		    }
		    menuInfo.setUrl(url);
		    menuInfo.setPid(pid);
		    menuInfo.setIconfont(iconfont);
		    menuInfo.setSort(sort);
		    menuInfo.setDeleteStatus(1);
		    menuInfo.setStatus(1);
		    menuInfo.setCreated(new Date());
		    menuMapper.addMenu(menuInfo);
		    // 只记录保存成功的日志
		    SysLogInfo sysLogInfo = new SysLogInfo();
		    sysLogInfo.setDescription("新增菜单：内容-菜单名字："+ menuname + ",路径"+url + ",pid:" +pid +",图标:"+iconfont +",排序:"+sort);
		    sysLogService.InsertSysLog(sysLogInfo,request);
		    new layUIResult();				
			return layUIResult.ok();
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			return layUIResult.resultErr(201, "新增角色失败");
		}
	}
	//删除菜单
	public Map<String, Object> deleteMenu(String id,HttpServletRequest request){
		MenuInfo menuInfo = new MenuInfo();
		try {
			String[] ids = id.split(",");
			for(int i = 0;i<ids.length;i++){
				menuInfo.setId(Integer.valueOf(ids[i]));
				menuInfo.setDeleteStatus(2); // 2删除状态
				menuInfo.setUpdated(new Date());
				menuMapper.deleteMenu(menuInfo);
			}
			 // 只记录保存成功的日志
		    SysLogInfo sysLogInfo = new SysLogInfo();
		    sysLogInfo.setDescription("删除菜单：内容-id："+ id );
		    sysLogService.InsertSysLog(sysLogInfo,request);
			new layUIResult();				
			return layUIResult.ok();
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			log.error(e.getMessage());
			return layUIResult.resultErr(201, "删除菜单失败");
		}
	}
	//修改菜单
	public Map<String, Object> updateMenuStatus(Integer id, Integer status ,HttpServletRequest request){
		try {
			MenuInfo menuInfo = new MenuInfo();
			
			menuInfo.setId(id);
			menuInfo.setStatus(status);
			menuInfo.setUpdated(new Date());
		    menuMapper.updateMenuStatus(menuInfo);
		    // 只记录保存成功的日志
		    SysLogInfo sysLogInfo = new SysLogInfo();
		    sysLogInfo.setDescription("修改菜单：内容-菜单id："+ id + ",状态："+status);
		    sysLogService.InsertSysLog(sysLogInfo,request);
			new layUIResult();				
			return layUIResult.ok();
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			return layUIResult.resultErr(201, "修改菜单失败");
		}
	}
	//排序菜单
	public Map<String, Object> userMenuAll(Integer id,String loginName){ 
		try {		
			List <MenuInfo> list = new ArrayList<MenuInfo>();
			//根据用户id 获取色角组
			List <MenuInfo> menInfoMap = roleMapper.getmenuInfo(id);
			// 重新排序map
			for(MenuInfo menuInfo1: menInfoMap){ 
				if(menuInfo1.getPid()==0){
					list.add(menuInfo1);	// 获取1级菜单
					//menInfoMap.remove(menuInfo1); //清除
					for(MenuInfo menuInfo2: menInfoMap){
						if(menuInfo2.getPid()==menuInfo1.getId()){
							list.add(menuInfo2); //2级菜单
							///menInfoMap.remove(menuInfo2); //清除
							foraa(list,menInfoMap,menuInfo2);
						}
					}
						
					
				
				}
			}
			new layUIResult();				
			return layUIResult.ok(list,loginName);
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
			return layUIResult.resultErr(201, "获取用户菜单");
		}
	}	
	
	//循环方法
	public void foraa(List <MenuInfo> list,List <MenuInfo> menInfoMap,MenuInfo menuInfo2){
		if(menuInfo2.getUrl()==null || "".equals(menuInfo2.getUrl())){
			for(MenuInfo menuInfo3: menInfoMap){
				if(menuInfo3.getPid()==menuInfo2.getId()){
					list.add(menuInfo3); //3级菜单
					//menInfoMap.remove(menuInfo3); //清除
					// 调用自己
					foraa(list, menInfoMap, menuInfo3);
				}
			}
		}
	}
	
}		
