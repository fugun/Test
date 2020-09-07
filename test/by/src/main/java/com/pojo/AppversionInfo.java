package com.pojo;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.po.BasePojoDate;

public class AppversionInfo extends BasePojoDate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    /**
     * 版本号
     */
    private int version;
    /**
     * appUrl
     */
    private String appUrl;
    
     // 备注
    private String des;
	/**
     * 0.建议更新   1.强制更新
		
     */
    private int Type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getAppUrl() {
		return appUrl;
	}
	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	@Override
	public String toString() {
		return "AppversionInfo [id=" + id + ", version=" + version + ", appUrl=" + appUrl + ", des=" + des + ", Type="
				+ Type + "]";
	}
    
    
    
}
