package com.pojo;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="setting")
public class SettingInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    /**
     * key
     */
    private String setKey;
    /**
     * value
     */
    private String setValue;
    /**
     * 备注
     */
    private String des;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSetKey() {
		return setKey;
	}
	public void setSetKey(String setKey) {
		this.setKey = setKey;
	}
	public String getSetValue() {
		return setValue;
	}
	public void setSetValue(String setValue) {
		this.setValue = setValue;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	@Override
	public String toString() {
		return "SettingInfo [id=" + id + ", setKey=" + setKey + ", setValue=" + setValue + ", des=" + des + "]";
	}
    
    
}
