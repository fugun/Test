package com.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.po.BasePojo;


/**
 * 角色实体类
 * 
 * 
 */
@Table(name="role_info")
public class RoleInfo extends BasePojo implements Serializable
{
    /**
     * 序列号
     */
    private static final long serialVersionUID = 5729754358787539200L;
    @Id
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    /**
     * 编号
     */
    private String roleName;
    
    /**
     * 路径
     */
    private int status;
    

	/**
     * 删除状态
     */
    private int deleteStatus;
    
   
   

    public RoleInfo() {
		
	}
    
	public RoleInfo(RoleInfo user) {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(int deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	@Override
	public String toString() {
		return "RoleInfo [id=" + id + ", roleName=" + roleName + ", status=" + status + ", deleteStatus=" + deleteStatus
				+ "]";
	}

	
	
}