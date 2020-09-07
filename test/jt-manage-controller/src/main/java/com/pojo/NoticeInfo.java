package com.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.po.BasePojoDate;
@Table(name="notice")
public class NoticeInfo extends BasePojoDate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    /**
     * 公告内容
     */
    private String content;
    /**
     * 客服号
     */
    private String weixin1;
    public String getWeixin1() {
		return weixin1;
	}
	public void setWeixin1(String weixin1) {
		this.weixin1 = weixin1;
	}
	
	private String weixin2;
    public String getWeixin2() {
		return weixin2;
	}
	public void setWeixin2(String weixin2) {
		this.weixin2 = weixin2;
	}

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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	@Override
	public String toString() {
		return "NoticeInfo [id=" + id + ", content=" + content + ", weixin1=" + weixin1 + ", weixin2=" +weixin2 
				+ ", des=" + des + "]";
	}
	
   
    

}
