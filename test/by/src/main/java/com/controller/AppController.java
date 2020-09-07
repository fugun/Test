package com.controller;

import java.util.List;
import java.util.Map;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.AppUserService;
import com.vo.ImgUtl;

@Controller
public class AppController {
	@Resource
	AppUserService appUserService;
	// 新增app帐号
	@ResponseBody
	@RequestMapping(value = "user/add/appAccount") 
	public Map<String,Object> addAppAccount(String userName ,Integer merID,String model,String validity,String des,Integer count,HttpServletRequest request){					
			return appUserService.addAppUser( userName,merID,model,validity,des,count,request);				
	}	
	// 查询app帐号
	@ResponseBody
	@RequestMapping(value = "user/find/appAccount") 
	public Map<String,Object> findAppAccount(Integer page, Integer limit,String name,String start,String end,String merID){					
			return appUserService.findAppUser(page, limit, name,start,end,merID);				
	}
	
	// 删除app帐号
	@ResponseBody
	@RequestMapping(value = "user/delete/appAccount")
	public Map<String,Object> deleteAppAccount(String id,HttpServletRequest request){					
			return appUserService.deleteAppAccount(id,request);				
	}	
	
	// 修改app帐号
	@ResponseBody
	@RequestMapping(value = "user/update/appAccount")
	public Map<String,Object> updateAppAccount(Integer id,String validity,String des ,HttpServletRequest request){					
			return appUserService.updateAppAccount(id,validity ,des,request);				
	}
	
	
	 /** 任务图片上传 返回路径**/      
	@RequestMapping(value = "user/appTask/img" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object>  upload(String img, String name){
		return new ImgUtl().getImgUrl(img, name);
		
	}
	
	// 保存任务name: name,
	@ResponseBody
	@RequestMapping(value = "user/Tadk/saveTask")
	public Map<String,Object> saveTask(String name ,Integer type,String des,String imgUrl ,String bonus,String webUrl ,String contents,HttpServletRequest request){					
			return appUserService.saveTask(name,type,des,imgUrl,bonus,webUrl,contents,request);				
	}
	
	// 查询任务
	@ResponseBody
	@RequestMapping(value = "user/find/appTask") 
	public Map<String,Object> findAppTask(Integer page, Integer limit,String name,String start,String end,Integer status,String bonus){					
			return appUserService.findAppTask(page, limit, name,start,end,status,bonus);				
	}
	
	// 删除任务
	@ResponseBody
	@RequestMapping(value = "user/delete/appTask")
	public Map<String,Object> deleteAppTask(String id,HttpServletRequest request){					
			return appUserService.deleteAppTask(id,request);				
	}	
	// 修改任务name: name,
	@ResponseBody
	@RequestMapping(value = "user/Tadk/updateTask")
	public Map<String,Object> updateTask(Integer id,String name ,Integer type,String des,String imgUrl ,String bonus,String webUrl ,String contents,HttpServletRequest request){					
			return appUserService.updateTask(id,name,type,des,imgUrl,bonus,webUrl,contents,request);				
	}	
	// 修改任务-状态
	@ResponseBody
	@RequestMapping(value = "user/update/taskStatus") 
	public Map<String,Object> updateTaskSatus(String id,Integer status,HttpServletRequest request){					
			return appUserService.updateTaskSatus(id,status,request);			
	}	
	// 查询任务Id
	@ResponseBody
	@RequestMapping(value = "user/find/appTaskId") 
	public Map<String,Object> findAppTaskIdId(Integer id){					
			return appUserService.findAppTaskId(id);				
	}	
	
	// 保存分配任务 记录name: name,
	@ResponseBody
	@RequestMapping(value = "user/add/TaskRecordAddTask")
	public Map<String,Object> TaskRecordAddTask(String userId,String taskId,HttpServletRequest request){					
				return appUserService.TaskRecordAddTask(userId,taskId,request);				
	}
	
	// 查询任务 记录
	@ResponseBody
	@RequestMapping(value = "user/find/taskrecord") 
	public Map<String,Object> findTaskrecord(Integer page, Integer limit,String name,String start,String status,String end,String merID){					
			return appUserService.findTaskrecord(page, limit, name,start,status,end,merID);				
	}	
	//
	
	// 保存公告name: name,
	@ResponseBody
	@RequestMapping(value = "user/notice/saveNotice")
	public Map<String,Object> saveNotice(String content ,String weixin1,String weixin2 ,String des,HttpServletRequest request){					
			return appUserService.saveNotice(content,weixin1,weixin2,des,request);				
	}
	
	// 查询公告
	
	@ResponseBody
	@RequestMapping(value = "user/find/notice") 
	public Map<String,Object> findNotice(Integer page, Integer limit,String name,String start,String end){					
			return appUserService.findNotice(page, limit, name,start,end);				
	}	
	
	// id 查询公告
	@ResponseBody
	@RequestMapping(value = "user/find/NoticeId") 
	public Map<String,Object> findNoticeId(Integer id){					
			return appUserService.findNoticeId(id);				
	}	
	
	// 修改公告
	@ResponseBody
	@RequestMapping(value = "user/update/updaetNotice")
	public Map<String,Object> updaetNotice(Integer id ,String content ,String weixin1,String weixin2,String des,HttpServletRequest request){					
			return appUserService.updaetNotice(id,content,weixin1,weixin2,des,request);				
	}
	// 删除公告
	@ResponseBody
	@RequestMapping(value = "user/delete/notice")
	public Map<String,Object> deleteNotice(String id,HttpServletRequest request){					
			return appUserService.deleteNotice(id,request);				
	}
	
	// 审核列表
	@ResponseBody
	@RequestMapping(value = "user/find/cashrecord")
	public Map<String,Object> findCashrecord(Integer page, Integer limit,String name,String state,String end,String start,String merID,String type){					
			return appUserService.findCashrecord(page, limit, name,state,end, start,merID,type);		
	}
	
	// 获取审核信息
	@ResponseBody
	@RequestMapping(value = "user/find/check_cashApply")
	public Map<String,Object> findCheckCashApply(Integer id){					
			return appUserService.findCheckCashApply(id);				
	}
	
	// 提交审核
	
	@ResponseBody
	@RequestMapping(value = "user/update/check_cashAply")
	public Map<String,Object> updateCheckCashApply(Integer id,Integer state,String des,HttpServletRequest request){					
			return appUserService.updateCheckCashApply(id,state,des,request);				
	}
	
	// 获取批量审核信息
	@ResponseBody
	@RequestMapping(value = "user/find/check_cashApplyAll")
	public Map<String,Object> findCheckCashApplyAll(String  end,String start,Integer type,Integer merID){					
			return appUserService.findCheckCashApplyAll(end ,start,type,merID);				
	}
	
	// 批量审核
	
	@ResponseBody
	@RequestMapping(value = "user/update/check_cashAplyAll")
	public Map<String,Object> updateCheck_cashAplyAll(String list  ,String des,Integer state,HttpServletRequest request){					
			return appUserService.updateCheck_cashAplyAll(list,des,state ,request);				
	}
	
	
	// 系统配置添加
	@ResponseBody
	@RequestMapping(value = "user/add/setting")
	public Map<String,Object> saveSetting(String setKey ,String setValue,String des,HttpServletRequest request){					
			return appUserService.saveSetting(setKey,setValue,des,request);				
	}
	// 查询配置
	@ResponseBody
	@RequestMapping(value = "user/find/setting") 
	public Map<String,Object> findSetting(Integer page, Integer limit,String name,String start,String end){					
			return appUserService.findSetting(page, limit, name,start,end);				
	}	
	
	// id 查询配置
	@ResponseBody
	@RequestMapping(value = "user/find/settingId") 
	public Map<String,Object> findSettingId(Integer id){					
			return appUserService.findSettingId(id);				
	}	
	
	// 修改配置
	@ResponseBody
	@RequestMapping(value = "user/update/updaetSetting")
	public Map<String,Object> updaetSetting(Integer id ,String setKey ,String setValue,String des,HttpServletRequest request){					
			return appUserService.updaetSetting(id,setKey,setValue,des,request);				
	}
	// 删除配置
	@ResponseBody
	@RequestMapping(value = "user/delete/setting")
	public Map<String,Object> deleteSetting(String id,HttpServletRequest request){				
			return appUserService.deleteSetting(id,request);				
	}
	
	 /** 任务apk上传 返回路径**/      
	@RequestMapping(value = "user/appVersionInfo/apk" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object>  appVersionInfo(String file, String name){
		//System.out.println( file);
		return new ImgUtl().getFileUrl(file, name);
		
	}
	// 查询apk版本
	@RequestMapping(value = "user/find/apk" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object>  findApk(){
		return appUserService.findApk();
		
	}
	
	// 更新apk 版本
	
	@RequestMapping(value = "user/update/apk" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object>  updateApk(Integer version ,Integer id,String appUrl,Integer type,HttpServletRequest request){
		return appUserService.updateApk(version,id,appUrl,type,request);
		
	}
	
	// 复制任务
	@ResponseBody
	@RequestMapping(value = "user/add/copyAllTask")
	public Map<String,Object> copyAllTask(String id,HttpServletRequest request){					
			return appUserService.copyAllTask(id,request);				
	}
	
	//商户统计列表
	@ResponseBody
	@RequestMapping(value = "user/find/mhtotaldata")
	public Map<String,Object> findMhtotaldata(Integer page, Integer limit,String merID,String start,String end){					
			return appUserService.findMhtotaldata(page, limit, merID,start,end);				
	}	
	
	//总统计列表
	@ResponseBody
	@RequestMapping(value = "user/find/totaldata")
	public Map<String,Object> findTotaldata(Integer page, Integer limit,String start,String end,String dateInt){					
			return appUserService.findTotaldata(page, limit,start,end ,dateInt);				
	}	
	
	// 批量修改IMEI 
	@ResponseBody
	@RequestMapping(value = "user/update/IMEI")
	public Map<String,Object> updateIMEI(String id ,HttpServletRequest request){					
			return appUserService.updateIMEI(id,request);				
	}	
	
	//求任务平均值
	@ResponseBody
	@RequestMapping(value = "user/json/taskAVG")
	public Map<String,Object> taskAVG(){					
			return appUserService.taskAVG();				
	}	
	
	// 用户统计
	@ResponseBody
	@RequestMapping(value = "user/find/acusertotaldate") 
	public Map<String,Object> findacusertotaldate(Integer page, Integer limit,String name,String start,String end,String merID,String MHName){					
			return appUserService.findacusertotaldate(page, limit, name,start,end,merID,MHName);				
	}	
	
	// 审核信息查询   （隔天）
	@ResponseBody
	@RequestMapping(value = "user/find/datecashrecord")
	public Map<String,Object> findDatecashrecord(Integer page, Integer limit,String start,String merID,String type,Integer state,String startDate,String endDate){					
			return appUserService.findDatecashrecord(page, limit, start,merID,type,state,startDate,endDate);		
	}
	// 获取审核信息（隔天）
	@ResponseBody
	@RequestMapping(value = "user/find/onDatecheck_cashApply")
	public Map<String,Object> findOnDatecheck_cashApply(Integer createDate,Integer method,Integer state,Integer mhId){					
			return appUserService.findOnDatecheck_cashApply(createDate,method,state,mhId);				
	}
	
	// 批量审核	（隔天） 
	@ResponseBody
	@RequestMapping(value = "user/update/onDatecheck_cashApply")
	public Map<String,Object> updateOnDatecheck_cashApply(String list  ,String des,Integer state,Integer method,Integer mhId,Integer createDate ,HttpServletRequest request){					
			return appUserService.updateOnDatecheck_cashApply(list,des,state ,method,mhId,createDate,request);				
	}
	
	// 新增代理审核记录
	@ResponseBody
	@RequestMapping(value = "user/add/agency") 
	public Map<String,Object> addagency(Integer agencyId ,Integer mhId,String des,Integer count,HttpServletRequest request){					
			return appUserService.addagency(agencyId,mhId,des,count,request);			
	}	
	// 查询代理审核记录
	@ResponseBody
	@RequestMapping(value = "user/json/agency") 
	public Map<String,Object> findAgency(Integer page, Integer limit,String start,String end,String mhId,String agencyId,Integer status){					
			return appUserService.findAgency(page, limit,start,end,mhId,agencyId,status);				
	}
	// 查询代理信息
	@ResponseBody
	@RequestMapping(value = "user/find/agencyCheck") 
	public Map<String,Object> findAgencyCheck(Integer id,String status){					
			return appUserService.findAgencyCheck(id,status);				
	}
	
	
	// 修改代理信息
	@ResponseBody
	@RequestMapping(value = "user/update/agencyCheck") 
	public Map<String,Object> updateAgencyCheck(Integer id,String status,String des,HttpServletRequest request){					
			return appUserService.updateAgencyCheck(id,status,des,request);			
	}

}
