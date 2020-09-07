package com.spring.quartzite;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.pojo.Merchant;
import com.pojo.MhtotaldataInfo;
import com.service.StatisticsService;

public class Test  extends Thread{
	
	
	
	
	@Resource
	StatisticsService  settinService;
	private static final Logger log = Logger.getLogger(Test.class);
	// 通过构造方法给线程名字赋值
    public Test(String name) {
       super(name);// 给线程名字赋值
    }
    // 为了保持数据的一致，数据要静态
     static int tick ;
     public static List<Merchant> list;
     public static List<Merchant> getList() {
		return list;
	}
	public static void setList(List<Merchant> list) {
		Test.list = list;
		tick = list.size();
	}

	
    // 创建一个静态钥匙
     static Object ob = "aa";//值是任意的
    // 重写run方法
    @Override
    public void run() {
    	MhtotaldataInfo mhto = new MhtotaldataInfo();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	log.debug("开始时间"+(new Date().getTime()));
      while (tick > 0) {
    	  try {
		        synchronized (ob) {// 这个很重要，必须使用一个锁， // 进去的人会把钥匙拿在手上，出来后才把钥匙拿让出来
		         
		          if (tick > 0) {
		        		  Merchant item = list.remove(tick-1); // 取一个删除一个
			        	  log.debug(item.getId());
			        	  mhto.setMerchantId(item.getId()); //商户id
			        	  mhto.setMerchantName(item.getName()); //商户名字
			        	  // 查出昨日总数
			        	 // mhto.setDateInt(Integer.valueOf(sdf.format(new Date()))-1);  //昨天
			        	  //MhtotaldataInfo onMhto  =  settinService.findMhtotaldataInfo(mhto); 
			        	  
			        	  
			        	 
			        	  //mhto.setDateInt(Integer.valueOf(sdf.format(new Date())));  //当天
			        	   // 查出当日统计 当日新增积分总数 ,当日执行任务总数 当日成功兑换积总分数
			        	 // Map<String,Object> map = settinService.findOndate_int_task_onInt(mhto);		        	 
			        	 //查出 当日授权总数 ，当日联网总数，当日收入总数
			        	  
			            tick--;
		        	  
		        	  
		          } else {
		        	  log.debug("没有商户数据了完了");
		        	  log.debug("结束时间"+(new Date().getTime()));
		          }
		      }        
		       //sleep(1000);//休息一秒
        } catch ( Exception e) {
          e.printStackTrace();
          tick =0;
        }
      
      }
    }
}
