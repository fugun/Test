package com.po;

import java.io.Serializable;
import java.util.Date;

public class BasePojoDate implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date createTime;
	private Date lastModiftyTime;
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastModiftyTime() {
		return lastModiftyTime;
	}
	public void setLastModiftyTime(Date lastModiftyTime) {
		this.lastModiftyTime = lastModiftyTime;
	}
	
}
