package com.pojo;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.po.BasePojoDate;

/**
 * 商户统计表
 * @author Administrator
 *
 */
@Table(name="mhtotaldata")
public class MhtotaldataInfo extends BasePojoDate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	
	//日期
	private int dateInt;
	private  int merchantId; //商户id
	private String merchantName; //商户名称
	private int autCount; //授权总数
	private int netCount; //联网总数
	private int exTaskCount; //执行任务总数
	private float oDIntCount; //当日新增积分总数
	private float oDExIntCount; //当日兑换积总分数
	private float totalIntCount; //总兑换积分数
	private float tatalIncome; //收入总数
	private float notTotalIntCont; //未兑换总积分数
	
	private int oDautCount; // 当日授权总数
	private int oDnetCount;//  当日联网总数
	
	
	
	public int getoDautCount() {
		return oDautCount;
	}
	public void setoDautCount(int oDautCount) {
		this.oDautCount = oDautCount;
	}
	public int getoDnetCount() {
		return oDnetCount;
	}
	public void setoDnetCount(int oDnetCount) {
		this.oDnetCount = oDnetCount;
	}
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
	public int getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public int getAutCount() {
		return autCount;
	}
	public void setAutCount(int autCount) {
		this.autCount = autCount;
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
	public float getNotTotalIntCont() {
		return notTotalIntCont;
	}
	public void setNotTotalIntCont(float notTotalIntCont) {
		this.notTotalIntCont = notTotalIntCont;
	}
	@Override
	public String toString() {
		return "MhtotaldataInfo [id=" + id + ", dateInt=" + dateInt + ", merchantId=" + merchantId + ", merchantName="
				+ merchantName + ", autCount=" + autCount + ", netCount=" + netCount + ", exTaskCount=" + exTaskCount
				+ ", oDIntCount=" + oDIntCount + ", oDExIntCount=" + oDExIntCount + ", totalIntCount=" + totalIntCount
				+ ", tatalIncome=" + tatalIncome + ", notTotalIntCont=" + notTotalIntCont + ", oDautCount=" + oDautCount
				+ ", oDnetCount=" + oDnetCount + "]";
	}
	
	

}
