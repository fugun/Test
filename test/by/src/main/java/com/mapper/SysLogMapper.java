package com.mapper;


import com.pojo.SysLogInfo;

public interface SysLogMapper extends SysMapper<SysLogInfo>{
	public Integer InsertSysLog (SysLogInfo sysLogInfo);
}
