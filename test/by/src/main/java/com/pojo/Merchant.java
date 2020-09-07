package com.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.po.BasePojoDate;
@Table(name="merchant")
public class Merchant  extends BasePojoDate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    /**
     * 商户名
     */
    private String name;
    /**
     * 支付信息
     */
    private String payInfo;
    /**
     * 备注
     */
    private String des;
    /**
     * 最后登录时间
     */
    private  Date lastLoginTime;
   
    
	/**
     * 删除状态  1正常  2删除
     */
    private int isDel;


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


	public String getPayInfo() {
		return payInfo;
	}


	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}


	public String getDes() {
		return des;
	}


	public void setDes(String des) {
		this.des = des;
	}


	public Date getLastLoginTime() {
		return lastLoginTime;
	}


	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}


	public int getIsDel() {
		return isDel;
	}


	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}


	@Override
	public String toString() {
		return "{id:" + id + ", name:" + name + ", payInfo:" + payInfo + ", des:" + des + ", lastLoginTime:"
				+ lastLoginTime + ", isDel:" + isDel + "}";
	}
    
    
}
