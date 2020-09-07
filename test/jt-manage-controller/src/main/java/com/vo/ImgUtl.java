package com.vo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

import com.service.UserService;

import sun.misc.BASE64Decoder;

public class ImgUtl {
	private static final Logger log = Logger.getLogger(UserService.class);
	@SuppressWarnings("restriction")
	public Map<String,Object> getImgUrl(String img,String name){
		String[] imgs = img.split(",");
		
		String url= date(name)+".jpg";
		//String path = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\test\\image\\";    	
		//String path = "/testweb/apache-tomcat-8.0.23/files/image/";
		String path = "/web/apache-tomcat-8.0.23/files/image/";
		File headPath = new File(path);//获取文件夹路径
		if(!headPath.exists()){//判断文件夹是否创建，没有创建则创建新文件夹
			headPath.mkdirs();
		}
		Map<String, Object> res = new HashMap<String,Object>();
		 BASE64Decoder decoder = new BASE64Decoder();
	        try {   
	         
	            //Base64解码  
	            byte[] b = decoder.decodeBuffer(imgs[1]);  
	          //  System.out.println("解码完成");
	            for(int i=0;i<b.length;++i) {  
	             
	                if(b[i]<0){  
	                //调整异常数据  
	                    b[i]+=256;  
	                }  
	            }    	           
	            log.debug("开始生成图片");
	            //生成jpeg图片  
	            OutputStream out = new FileOutputStream(path+url);      
	            out.write(b);  
	            out.flush();  
	            out.close();  
	            //返回的是一个url对象
	            res.put("url", url);
	            return res;
	        }   
	        catch (Exception e) {   
	         
	        	log.error(e);
	        	res.put("url", "1");
	        	return res; 
	        }      	
	}
	
	
	@SuppressWarnings("restriction")
	public   Map<String,Object> getFileUrl(String file,String name){
		//System.out.println("file = "+ file);
				String[] imgs = file.split(",");
				//String paths = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\test\\image\\"; 
				//String paths = "/testweb/apache-tomcat-8.0.23/files/file/";
				String paths = "/web/apache-tomcat-8.0.23/files/file/";
				File headPath = new File(paths);//获取文件夹路径
				if(!headPath.exists()){//判断文件夹是否创建，没有创建则创建新文件夹
					headPath.mkdirs();
				}
				String path = paths+name;
				//String path = "D:\\"+name;
				String url= name;
				//LOG.info(path);
				
				Map<String, Object> res = new HashMap<String,Object>();
				 BASE64Decoder decoder = new BASE64Decoder();  
			        try {   	        	
			            //Base64解码  
			        	//System.out.println("开始解码"+imgs[1]);
			            byte[] b = decoder.decodeBuffer(imgs[1]);  
			          // System.out.println("解码完成");
			            for(int i=0;i<b.length;++i) {  
			             
			                if(b[i]<0){  
			                //调整异常数据  
			                    b[i]+=256;  
			                }  
			            }    	           
			            log.debug("开始生成文件apk");
			            //生成apk文件  
			            OutputStream out = new FileOutputStream(path);      
			            out.write(b);  
			            out.flush();  
			            out.close();  
		                //返回的是一个url对象
		                res.put("url", url);
		                return res;
			        }   
			        catch (Exception e) {   
			         
			        	log.error(e);
		            	res.put("url", "1");
		            	return res; 
			        }      	
	}
  //生成唯一名字
    public static String  date(String cityCode) {
    	//Date date = new Date();
    	SimpleDateFormat formatss = new SimpleDateFormat("yyyyMMddHHss");
    	Random rand =new Random(10);
    	String random=""+Math.abs(rand.nextInt());
    	Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
    	String  now_date = formatss.format(curDate);
    	String times = (now_date+cityCode+random).toString();
    	//System.out.println(times);
    	return times;
    }



}
