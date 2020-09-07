package com.pojo;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.po.BasePojoDate;
/**
 * 总统计表
 * @author Administrator
 *
 */
@Table(name="totaldata")
public class TotaldataInfo extends BasePojoDate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	
	//日期
	private int dateInt;
	
	private int autCount; //授权总数
	private int addAutCount; //新增授数
	private int netCount; //联网总数
	private int exTaskCount; //执行任务总数
	private int addNetCount;//当日新增联网数
	private float oDIntCount; //当日新增积分总数
	private float oDExIntCount; //当日兑换积总分数
	private float totalIntCount; //总兑换积分数
	private float tatalIncome; //收入总数
	private float notTotalIntCount; //未兑换总积分数
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDateInt() {
		return dateInt;
	}
	public void setDateInt(int dateInt) {
		this.dateInt = dateInt;
	}
	public int getAutCount() {
		return autCount;
	}
	public void setAutCount(int autCount) {
		this.autCount = autCount;
	}
	public int getAddAutCount() {
		return addAutCount;
	}
	public void setAddAutCount(int addAutCount) {
		this.addAutCount = addAutCount;
	}
	public int getNetCount() {
		return netCount;
	}
	public void setNetCount(int netCount) {
		this.netCount = netCount;
	}
	public int getExTaskCount() {
		return exTaskCount;
	}
	public void setExTaskCount(int exTaskCount) {
		this.exTaskCount = exTaskCount;
	}
	public int getAddNetCount() {
		return addNetCount;
	}
	public void setAddNetCount(int addNetCount) {
		this.addNetCount = addNetCount;
	}
	public float getoDIntCount() {
		return oDIntCount;
	}
	public void setoDIntCount(float oDIntCount) {
		this.oDIntCount = oDIntCount;
	}
	public float getoDExIntCount() {
		return oDExIntCount;
	}
	public void setoDExIntCount(float oDExIntCount) {
		this.oDExIntCount = oDExIntCount;
	}
	public float getTotalIntCount() {
		return totalIntCount;
	}
	public void setTotalIntCount(float totalIntCount) {
		this.totalIntCount = totalIntCount;
	}
	public float getTatalIncome() {
		return tatalIncome;
	}
	public void setTatalIncome(float tatalIncome) {
		this.tatalIncome = tatalIncome;
	}
	public float getNotTotalIntCount() {
		return notTotalIntCount;
	}
	public void setNotTotalIntCount(float notTotalIntCount) {
		this.notTotalIntCount = notTotalIntCount;
	}
	@Override
	public String toString() {
		return "TotaldataInfo [id=" + id + ", dateInt=" + dateInt + ", autCount=" + autCount + ", addAutCount="
				+ addAutCount + ", netCount=" + netCount + ", exTaskCount=" + exTaskCount + ", addNetCount="
				+ addNetCount + ", oDIntCount=" + oDIntCount + ", oDExIntCount=" + oDExIntCount + ", totalIntCount="
				+ totalIntCount + ", tatalIncome=" + tatalIncome + ", notTotalIntCount=" + notTotalIntCount + "]";
	}
	
	

}
