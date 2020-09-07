package com.pojo;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.po.BasePojo;

/**
 * 日志实体类
 * 
 * 
 */
@Table(name="syslog")
public class SysLogInfo extends BasePojo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    /**
     * 用户id
     */
    private int userId;
    
    /**用户名
     * 
     */
    private String userName;
    

	/**
     * 备注
     */
    private String description;
    
    /**
     * 创建时间
     */
    private int createDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCreateDate() {
		return createDate;
	}

	public void setCreateDate(int createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return " [id:" + id + ", userId:" + userId + ", userName:" + userName + ", description:" + description
				+ ", createDate:" + createDate + "]";
	}
    
   
    
}
