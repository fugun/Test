package com.mapper;

import java.util.List;
import java.util.Map;

import com.pojo.AppUserInfo;
import com.pojo.AppversionInfo;
import com.pojo.CashrecordInfo;
import com.pojo.DataCashrecordInfo;
import com.pojo.NoticeInfo;
import com.pojo.SettingInfo;
import com.pojo.TaskInfo;
import com.pojo.TaskRecordInfo;

public interface AppMapper extends SysMapper<AppUserInfo>{
	public Integer addAppUser(AppUserInfo appuserInfo);
	public List<Map<String,Object>> findAppUser (AppUserInfo appUser);
	public Integer deleteAppAccount (AppUserInfo appUser);
	public Integer updateAppAccount (AppUserInfo appUser);
	public Integer saveTask (TaskInfo taskInfo);
	public Integer updateTask (TaskInfo taskInfo);
	public Integer updateTaskStatus(TaskInfo taskInfo);
	public List<Map<String,Object>> findAppTask (TaskInfo taskInfo);
	public Integer deleteAppTask (TaskInfo taskInfo);
	// 插入记录表
	public Integer TaskRecordAddTask(TaskRecordInfo taskRecordInfo);
	public List<Map<String,Object>> findTaskRecord (Map<String,Object> map);
	//公告
	public Integer saveNotice(NoticeInfo noticeInfo);
	public List<Map<String,Object>> findNotice (NoticeInfo noticeInfo);
	public Integer updaetNotice(NoticeInfo noticeInfo);
	public Integer deleteNotice (NoticeInfo noticeInfo);
	
	//审核
	public List<Map<String,Object>> findCashrecord (Map<String,Object> map);
	public List<Map<String,Object>> findCheckCashApply (NoticeInfo noticeInfo);
	
	public List<Map<String,Object>> findCheckCashApplyAll (Map<String,Object> map);
	//修改审核状态
	public Integer updateCheckCashApply(CashrecordInfo cf);
	
	// 审核抛异常改回之前状态0
	public Integer updateCheckCashApplyStauts0(CashrecordInfo cf);
	
	public CashrecordInfo findCashrecordId(CashrecordInfo cf);
	
	// 审核失败把用户金额加回去
	public Integer updateAppUserWithdrawable (AppUserInfo appUser);
	// 审核 成功加用户已提现金额
	public Integer updateAppUserGrossIncome (AppUserInfo appUser);
	//添加配置信息
	public Integer saveSetting (SettingInfo setting);
	public List<Map<String,Object>> findSetting (SettingInfo setting);
	public Integer updaetSetting(SettingInfo setting);
	public Integer deleteSetting (SettingInfo setting);
	public Integer findSettingName (SettingInfo setting);
	
	// apk 查询
	
	public List<Map<String,Object>> findApk ();
	public Integer updateApk (AppversionInfo apk);
	
	
	// 商户统计查询
	public List<Map<String,Object>> findMhtotaldata (Map<String,Object> map);
	// 总统计查询
	public List<Map<String,Object>> findTotaldata (Map<String,Object> map);
	
	//批量修改IMEI
	public Integer updateIMEI(AppUserInfo appUser);
	public Map<String,Object> taskAVG ();
	
	//用户统计
	public List<Map<String,Object>> findacusertotaldate (Map<String,Object> map);
	
	
	//隔日审核查询列表
	public List<Map<String,Object>> findDatacashrecord (Map<String,Object> map);
	//获取审核信息
	public List<Map<String,Object>> findOnDatecheck_cashApply (Map<String,Object> map);
	
	// 隔日数据表修改
	public Integer updaetDataCashrecord(DataCashrecordInfo dci);
}
