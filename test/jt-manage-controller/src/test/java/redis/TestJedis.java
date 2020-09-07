package redis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

public class TestJedis {
	@Test //获取redis数据
	public void nodeone(){
		/*
		 * 步骤：
		 * 1、链接到一个redis的节点，host+port
		 * 2、创建一个Jedis对象
		 * 3、操作redis命名，把这些命令就封装成方法
		 */
		Jedis jedis = new Jedis("192.168.1.223", 6379);
		 jedis.set("name","name");
		 String name = jedis.get("name");
		System.out.println(name);
		
		jedis.close();
	}
	
	@Test //分片Shard
	public void shard(){
		//每个info就是一个链接信息封装，把所有链接都放入一个list中
		List<JedisShardInfo> list = new ArrayList<JedisShardInfo>();
		JedisShardInfo info1 = new JedisShardInfo("192.168.163.31", 6379);
		list.add(info1);
		JedisShardInfo info2 = new JedisShardInfo("192.168.163.31", 6380);
		list.add(info2);
		//JedisShardInfo info3 = new JedisShardInfo("192.168.163.31", 6381);
		//list.add(info3);
		
		ShardedJedis jedis = new ShardedJedis( list );
		for(int i=0;i<100;i++){
			jedis.set("text"+i, "tony"+i);
		}
		
		//String s = jedis.get("name");
		//System.out.println(s);
		
		jedis.close();
	}
	
	
	
	
	@Test 
	public void test() throws ParseException{
		Random ro=new Random();
 	    String s = Long.valueOf(new Date().getTime()).toString().substring(2, 5);
 	    String a = Long.valueOf(new Date().getTime()).toString().substring(7, 9);
	    //唯一名字					
		System.out.println("by"+s+ro.nextInt(100)+a);
		
		
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-12-02 00:00:00"));
		
	}
	
	@Test
	public void test2(){
		/**List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> list2 = new ArrayList<Map<String,Object>>();
		JSONArray jArray = new JSONArray();		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("1", 213);
		list.add(map);
		jArray = jArray.fromObject(list);
		 JSONObject json = new JSONObject();
		 json.put("zfb", jArray);
		 
		System.out.println(json.toString());**/

		Integer count = 1000000;
		String nameCount = count.toString();
		String aa = "tc";
		String str = "";
		for(int a = 1;a<nameCount.length();a++){
			str = str+"0";
		}
		for(int i =1;i<count+1;i++){
			if( i<20 && i%10==0){
				str = str.substring(1,str.length());
				
			}else if (i%100==0 && i==100 ){
				if(str.length()>0){
					str = str.substring(1,str.length());
				}else{
					str = "";
				}
				
			}else if(i%1000==0 && i==1000){
				if(str.length()>0){
					str = str.substring(1,str.length());
				}else{
					str = "";
				}
			}else if(i%10000==0 && i==10000){
				if(str.length()>0){
					str = str.substring(1,str.length());
				}else{
					str = "";
				}
			}else if(i%100000==0 && i==100000){
				if(str.length()>0){
					str = str.substring(1,str.length());
				}else{
					str = "";
				}
			}else if(i%1000000==0 && i==1000000){
				if(str.length()>0){
					str = str.substring(1,str.length());
				}else{
					str = "";
				}
			}
			System.out.println(aa+str+i);
			System.out.println(i+"i");
		}
	}
	
	@Test
	public  void test4(){

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE,-1);
		Date date = calendar.getTime();
		String sceneTime = DateFormatUtils.format(date, "yyyyMMdd");
		System.out.println(sceneTime);

	}
}
