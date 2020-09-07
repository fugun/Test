package com.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.po.BasePojoDate;
@Table(name="task")
public class TaskInfo extends BasePojoDate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    /**
     * 任务名字
     */
    private String name;
    /**
     * 任务类型
     */
    private int type;
    /**
     * 字符说明
     */
    private String contents;
    /**
     * 任务路径
     */
    private String imgUrl;
    /**
     * 任务路径外网地址
     */
    private  String webUrl;

    /**
     * 任务佣金
     */
    private  float bonus;
    
    // 用设置佣金
    
    private  float testbonus;
    /**
     * 备注
     */
    private String des;
	/**
     * 删除状态  1正常  2删除
     */
    private int isDel;
    //任务状态
    private int status;
    
    
	
	public float getTestbonus() {
		return testbonus;
	}
	public void setTestbonus(float testbonus) {
		this.testbonus = testbonus;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	public float getBonus() {
		return bonus;
	}
	public void setBonus(float bonus) {
		this.bonus = bonus;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	@Override
	public String toString() {
		return "TaskInfo [id=" + id + ", name=" + name + ", type=" + type + ", contents=" + contents + ", imgUrl="
				+ imgUrl + ", webUrl=" + webUrl + ", bonus=" + bonus + ", des=" + des + ", isDel=" + isDel + ", status=" + status +  "]";
	}
    
    

}
