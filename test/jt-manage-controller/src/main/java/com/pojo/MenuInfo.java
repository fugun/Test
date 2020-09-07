package com.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.po.BasePojo;


/**
 * 菜单实体类
 * 
 * 
 */
@Table(name="menu_info")
public class MenuInfo extends BasePojo implements Serializable
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
    private String name;
    
    /**
     * 路径
     */
    private String url;
    

	/**
     * 图标
     */
    private String iconfont;
    
    /**
     * 父Id
     */
    private int pid;
    
    /**
     * 排序
     */
    private int sort;
    
    /**
     * 状态 1：正常 2：停用
     */
    private int status;
    
    /**
     *状态  1 正常  2 删除
     */
    private int deleteStatus;
    
 
   

    public MenuInfo() {
		
	}
    
	public MenuInfo(MenuInfo user) {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIconfont() {
		return iconfont;
	}

	public void setIconfont(String iconfont) {
		this.iconfont = iconfont;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
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
		return " {id:" + id + ", name:" + name + ", url:" + url + ", iconfont:" + iconfont + ", pid:" + pid
				+ ", sort:" + sort + ", status:" + status + ", deleteStatus:" + deleteStatus + "}";
	}
	
	
}