package com.controller;

//图片上传返回json结构
public class PicUploadResult {
	private Integer error=0;	//0正常，1异常
	private String url;			//图片相对访问地址：http://image.jt.com/images/2017/04/08/128272.jpg
	private String height;
	private String width;
	public Integer getError() {
		return error;
	}
	public void setError(Integer error) {
		this.error = error;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	
}
