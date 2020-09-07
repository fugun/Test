package com.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.po.BasePojoDate;
@Table(name="user")
public class AppUserInfo extends BasePojoDate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    /**
     * 商户Id
     */
    private int merID;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户密码
     */
    private String password;
    /**
     * token
     */
    private String token;
    /**
     * token有效截止期
     */
    private  Date tokenExpired;

    /**
     * 手机IMEI
     */
    private  String imei;
    /**
     * 手机机型
     */
    private  String model;
    /**
     * 账户有效期
     */
    private  Date validity;
    /**
     * 已提现金额
     */
    private  float grossIncome;
    /**
     * 可提现金额
     */
    private  float withdrawable;
    
    /**
     * 最后登陆时间
     */
    private  Date lastLoginTime;
    /**
     * 用户备注
     */
    private String des;
	/**
     * 删除状态  1正常  2删除
     */
    private int isDel;
    private String  version;
    
    private int createDate;
    
    
    
	public int getCreateDate() {
		return createDate;
	}
	public void setCreateDate(int createDate) {
		this.createDate = createDate;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMerID() {
		return merID;
	}
	public void setMerID(int merID) {
		this.merID = merID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getTokenExpired() {
		return tokenExpired;
	}
	public void setTokenExpired(Date tokenExpired) {
		this.tokenExpired = tokenExpired;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Date getValidity() {
		return validity;
	}
	public void setValidity(Date validity) {
		this.validity = validity;
	}
	public float getGrossIncome() {
		return grossIncome;
	}
	public void setGrossIncome(float grossIncome) {
		this.grossIncome = grossIncome;
	}
	public float getWithdrawable() {
		return withdrawable;
	}
	public void setWithdrawable(float withdrawable) {
		this.withdrawable = withdrawable;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
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
		return "appUserInfo [id=" + id + ", merID=" + merID + ", userName=" + userName + ", password=" + password
				+ ", token=" + token + ", tokenExpired=" + tokenExpired + ", imei=" + imei + ", model=" + model
				+ ", validity=" + validity + ", grossIncome=" + grossIncome + ", withdrawable=" + withdrawable
				+ ", lastLoginTime=" + lastLoginTime + ", des=" + des + ", isDel=" + isDel +",version=" + version+",createDate=" + createDate+"]";
	}
    
    
    
}
