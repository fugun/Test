package com.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 角色 组实体类
 * 
 * 
 */
@Table(name="userRole_info")
public class UserRoleInfo {
	@Id
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    
    /**
     * roleId
     */
    private int roleId;
    

	/**
     * 用户id
     */
    private int userId;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	

	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "UserRoleInfo [id=" + id + ", roleId=" + roleId + ", userId=" + userId + "]";
	}
    
}
