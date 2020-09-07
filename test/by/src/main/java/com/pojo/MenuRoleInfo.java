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
@Table(name="rolemenu_info")
public class MenuRoleInfo {
	@Id
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    
    /**
     * roleId
     */
    private int roleId;
    

	/**
     * 菜单id
     */
    private int menuId;


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


	public int getMenuId() {
		return menuId;
	}


	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}


	@Override
	public String toString() {
		return "MenuRoleInfo [id=" + id + ", roleId=" + roleId + ", menuId=" + menuId + "]";
	}


	
    
}
