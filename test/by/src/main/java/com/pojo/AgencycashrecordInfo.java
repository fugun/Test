package com.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.po.BasePojoDate;
@Table(name="agencycashrecord")
public class AgencycashrecordInfo extends BasePojoDate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ;
	
	/**
	 * 代理id
	 */
	private int agencyId;
	/**
	 * 商户iD
	 */
	private int mhId;
	/**
	 * 台数
	 */
	
	private int count;
	
	/**
	 * 完成时间
	 */
	private Date doneTime;
	
	/**
	 * 创建时间
	 */
	private int createDate;
	
	/**
	 * 备注
	 */
	
	private String des;
	
	/**
	 * 积分
	 * 
	 */
	
	private float amount;
	/**
	 * 状态
	 * @return
	 */
	private int status;
	
	
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

	public int getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}

	public int getMhId() {
		return mhId;
	}

	public void setMhId(int mhId) {
		this.mhId = mhId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getDoneTime() {
		return doneTime;
	}

	public void setDoneTime(Date doneTime) {
		this.doneTime = doneTime;
	}

	public int getCreateDate() {
		return createDate;
	}

	public void setCreateDate(int createDate) {
		this.createDate = createDate;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "AgencycashrecordInfo [id=" + id + ", agencyId=" + agencyId + ", mhId=" + mhId + ", count=" + count
				+ ", doneTime=" + doneTime + ", createDate=" + createDate + ", des=" + des + ", amount=" + amount + "]";
	}
	
	
}
