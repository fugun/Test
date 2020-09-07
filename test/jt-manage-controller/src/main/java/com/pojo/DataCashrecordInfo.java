package com.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.po.BasePojoDate;
@Table(name="datacashrecord")
public class DataCashrecordInfo extends BasePojoDate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
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
    private int count ;
    private String mhName;  // 商户名字
    private int mhId;  // 商户id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getMhName() {
		return mhName;
	}
	public void setMhName(String mhName) {
		this.mhName = mhName;
	}
	public int getMhId() {
		return mhId;
	}
	public void setMhId(int mhId) {
		this.mhId = mhId;
	}
	@Override
	public String toString() {
		return "DataCashrecordInfo [id=" + id + ", method=" + method + ", amount=" + amount + ", state=" + state
				+ ", doneTime=" + doneTime + ", createDate=" + createDate + ", des=" + des + ", count=" + count
				+ ", mhName=" + mhName + ", mhId=" + mhId + "]";
	}
	
    
	

}
