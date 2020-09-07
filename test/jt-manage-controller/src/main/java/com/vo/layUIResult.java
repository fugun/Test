package com.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class layUIResult {
	private static final Map<String,Object> map = new HashMap<String,Object>();
	public static Map<String,Object> result(long l, List<Map<String,Object>> list) {
		map.clear();
        try {
            	map.put("code", 0); // 
            	map.put("msg", "");
            	map.put("count", l);
            	map.put("data", list);  
            	return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	
	public static Map<String,Object> ok() {
		map.clear();
        try {
            	map.put("code", 200);            	
            	return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	public static Map<String,Object> resultErr(int l, Object obj) {
		map.clear();
        try {
            	map.put("code", l);
            	map.put("msg", obj);
            	map.put("data", "");  
            	return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public static Map<String,Object> ok(Object data) {
		map.clear();	
		 try {
         	map.put("code",200); 
         	map.put("data", data); 
         	return map;
     } catch (Exception e) {
         e.printStackTrace();
         return null;
     }
    }
	
	// 需要返用户名
	public static Map<String,Object> ok(Object data,String name) {
		map.clear();	
		 try {
         	map.put("code",200); 
         	map.put("data", data); 
         	map.put("name", name);
         	return map;
     } catch (Exception e) {
         e.printStackTrace();
         return null;
     }
    }
}
