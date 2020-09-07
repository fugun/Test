package com.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.po.BasePojoDate;
@Table(name="cashrecord")
public class CashrecordInfo extends BasePojoDate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	//用户id
	private int userID;
	//支付类型
	private int method;
	//提现金额
	private float amount;
	//审核状态
	private int state;
	//审核时间
	private Date doneTime;
	private int createDate;
    /**
     * 备注
     */
    private String des;
    
    
    
    
	public int getCreateDate() {
		return createDate;
	}

	public void setCreateDate(int createDate) {
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getMethod() {
		return method;
	}

	public void setMethod(int method) {
		this.method = method;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getDoneTime() {
		return doneTime;
	}

	public void setDoneTime(Date doneTime) {
		this.doneTime = doneTime;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	@Override
	public String toString() {
		return "CashrecordInfo [id=" + id + ", userID=" + userID + ", method=" + method + ", amount=" + amount
				+ ", state=" + state + ", doneTime=" + doneTime + ", des=" + des + ", createDate=" + createDate +"]";
	}
    
    

}
