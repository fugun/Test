package com.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.po.BasePojo;


/**
 * 用户实体类
 * 
 * 
 */
@Table(name="admin_info")
public class UserInfo extends BasePojo implements Serializable
{
    /**
     * 序列号
     */
    private static final long serialVersionUID = 5729754358787539200L;
    @Id
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    /**
     * 用户唯一编号
     */
    private String userId;
    
    /**
     * 用户名
     */
    private String loginName;
    
    /**
     * 用户别名
     */
    private String aliasName;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     *删除状态 1正常2不可用
     */
    private int deleteStatus;
    
    /**
     * 状态 0：正常 1：停用
     */
    private int status;
    
    /**
     *登录时间
     */
    private Date lastDate;
    
    
    /**
     * 下线时间
     */
    private Date enaDate;
    
    private String email;
    
    private String Session;
    
   

    public UserInfo() {
		
	}
    
	public UserInfo(UserInfo user) {
		super();
	}


	public String getSession() {
		return Session;
	}


	public void setSession(String session) {
		Session = session;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getLoginName() {
		return loginName;
	}


	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public String getAliasName() {
		return aliasName;
	}


	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getDeleteStatus() {
		return deleteStatus;
	}


	public void setDeleteStatus(int deleteStatus) {
		this.deleteStatus = deleteStatus;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public Date getLastDate() {
		return lastDate;
	}


	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}


	public Date getEnaDate() {
		return enaDate;
	}


	public void setEnaDate(Date enaDate) {
		this.enaDate = enaDate;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", userId=" + userId + ", loginName=" + loginName + ", aliasName=" + aliasName
				+ ", password=" + password + ", deleteStatus=" + deleteStatus + ", status=" + status + ", lastDate=" + lastDate
				+  ", enaDate=" + enaDate 
				+ ", email=" + email + "]";
	}
    
   

   
}