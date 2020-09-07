package com.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.po.BasePojoDate;

@Table(name="taskrecord")
public class TaskRecordInfo extends BasePojoDate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	
	/**
     * 任务Id
     */
    private int taskID;
    /**
     * 用户Id
     */
    private int userID;
    /**
     * 佣金
     */
    private float bonus;
    /**
     * 任务路径
     */
    private String imgUrl;
    /**
     * 任务路径外网地址
     */
    private  String webUrl;

    
    
    /**
     * 状态
     */
    private int status;  //0-未完成 1-完成 -1失败
    
	/**
     * 任务下发时间
     */
    private int createDate;
    // 任务完成时间
    private Date doneTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTaskID() {
		return taskID;
	}
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public float getBonus() {
		return bonus;
	}
	public void setBonus(float bonus) {
		this.bonus = bonus;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCreateDate() {
		return createDate;
	}
	public void setCreateDate(int createDate) {
		this.createDate = createDate;
	}
	public Date getDoneTime() {
		return doneTime;
	}
	public void setDoneTime(Date doneTime) {
		this.doneTime = doneTime;
	}
	@Override
	public String toString() {
		return "TaskRecordInfo [id=" + id + ", taskID=" + taskID + ", userID=" + userID + ", bonus=" + bonus + ", imgUrl="
				+ imgUrl + ", webUrl=" + webUrl + ", status=" + status + ", createDate=" + createDate + ", doneTime="
				+ doneTime + "]";
	}
    
    
    
	
}
