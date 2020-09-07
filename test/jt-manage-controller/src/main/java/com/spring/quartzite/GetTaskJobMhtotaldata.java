package com.spring.quartzite;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.pojo.Merchant;
import com.pojo.MhtotaldataInfo;
import com.service.StatisticsService;
import com.vo.OutDateUtl;

public class GetTaskJobMhtotaldata {
	@Autowired
	private   StatisticsService  settinService;
	private static final Logger log = Logger.getLogger(GetTaskJobMhtotaldata.class);
	//定时器
	public void work() {
		log.debug("开始_执行商户统计");
		
		try{
			mhtotalDate();
		}catch(Exception e){
			e.printStackTrace();
			log.error("商户统计定时器执行异常"+e);
		}finally{
			log.debug("商户统结束_执行");
		}
  
    }  
	
	  

	
	//统计商户表信息
	public void mhtotalDate() {
		List<Merchant> list = settinService.findmerchantAll();
		//System.out.println(list);
		// 查询所有商户
		MhtotaldataUlt mh1 = new MhtotaldataUlt("线程1");
		mh1.setList(list);
		MhtotaldataUlt mh2 = new MhtotaldataUlt("线程2");
		mh2.start();
		mh1.start();
	}
	
	
	
	public  class MhtotaldataUlt  extends Thread{
		// 通过构造方法给线程名字赋值
	    public MhtotaldataUlt(String name) {
	       super(name);// 给线程名字赋值
	    }
	    // 为了保持数据的一致，数据要静态
	      int tick ;
	     public   List<Merchant> list;
	     public  List<Merchant> getList() {
			return list;
		}
		public  void setList(List<Merchant> list) {
			this.list = list;
			tick = list.size();
		}

		
	    // 创建一个静态钥匙
	       Object ob = "aa";//值是任意的
	    // 重写run方法
	    @Override
	    public void run() {
	    	MhtotaldataInfo mhto = new MhtotaldataInfo();
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    	log.debug("开始时间"+(new Date().getTime()));
	    	
	    	//System.out.println("tick="+tick);
	      while (tick > 0) {
	    	  try {
			        synchronized (ob) {// 这个很重要，必须使用一个锁， // 进去的人会把钥匙拿在手上，出来后才把钥匙拿让出来
			         
			          if (tick > 0) {
			        		  Merchant item = list.remove(tick-1); // 取一个删除一个
				        	  log.debug(item.getId());
				        	 // System.out.println(item.getId());
				        	  mhto.setMerchantId(item.getId()); //商户id
				        	  mhto.setMerchantName(item.getName()); //商户名字
				        	  
					        	
				        	  // 查出昨日总数
				        	  OutDateUtl date = new OutDateUtl();
				        	  mhto.setDateInt(Integer.valueOf(date.outDate(-1)));  //昨天
				        	  MhtotaldataInfo onMhto  =  settinService.findMhtotaldataInfo(mhto);
				        	  
			        		  mhto.setDateInt(Integer.valueOf(sdf.format(new Date())));  //当天
				        	   // 查出当日统计 当日新增积分总数 ,当日执行任务总数 当日成功兑换积总分数 _ 当日申请兑换积分总数
				        	  Map<String,Object> toB_cId_toM = settinService.findOndate_toBonus_taskCount_toAmountS1_toAmountS0(mhto);	
				        	  //System.out.println("1："+toB_cId_toM.toString());
				        	  //查出 当日授权总数 ，当日联网总数onAutCount,SUM(u2.u_count3) AS onNetCount ——用户当前余额 withdrawable
				        	  Map<String,Object> onA_onN = settinService.findOndate__onAutCount_onNetCount_withdrawable(mhto);
				        	 // System.out.println("2："+toB_cId_toM.toString());
				        	  // 查出手机金额
				        	  String Mobile = settinService.findMobilePhone();
				        	  //当日授权总数   ，
				        	  mhto.setoDautCount( Integer.valueOf(onA_onN.get("onAutCount").toString()));
				        	 
					        if(onMhto!=null){  
					        	  
					        	  // 昨日总数 +今日新增
					        	  mhto.setAutCount(onMhto.getAutCount()+ Integer.valueOf(onA_onN.get("onAutCount").toString()));//授权总数
					        	  mhto.setNetCount( Integer.valueOf(onA_onN.get("onNetCount").toString()));//联网总数
					        	  mhto.setExTaskCount(onMhto.getExTaskCount() + Integer.valueOf(toB_cId_toM.get("taskCount").toString()));//执行任务总数
					        	  mhto.setTotalIntCount(onMhto.getTotalIntCount()+Float.valueOf(toB_cId_toM.get("toAmountS1").toString()));//总兑换积分数
					        	  mhto.setoDIntCount(Float.valueOf(toB_cId_toM.get("toBonus").toString()));//当日新增积分总数
					        	  mhto.setoDExIntCount(Float.valueOf(toB_cId_toM.get("toAmountS1").toString()));//当日兑换积总分数
					        	 
					        	  mhto.setTatalIncome(onMhto.getTatalIncome() + Integer.valueOf(Mobile)* Integer.valueOf(onA_onN.get("onAutCount").toString()));//收入总数   //手机* 总授权数
					        	 
					        	  mhto.setNotTotalIntCont(Float.valueOf(toB_cId_toM.get("toAmountS0").toString())+ Float.valueOf(onA_onN.get("withdrawable").toString()));//未兑换总积分数 
					        	//联网今新曾    总数-昨日总数
					        	  mhto.setoDnetCount( Integer.valueOf(onA_onN.get("onNetCount").toString())- onMhto.getNetCount());
				        	  }else{
				        		  //联网总数
					        	  mhto.setoDnetCount( Integer.valueOf(onA_onN.get("onNetCount").toString()));
					        	  mhto.setAutCount( Integer.valueOf(onA_onN.get("onAutCount").toString()));//授权总数
					        	  mhto.setNetCount( Integer.valueOf(onA_onN.get("onNetCount").toString()));//联网总数
					        	  mhto.setExTaskCount( Integer.valueOf(toB_cId_toM.get("taskCount").toString()));//执行任务总数
					        	  mhto.setTotalIntCount(Float.valueOf(toB_cId_toM.get("toAmountS1").toString()));//总兑换积分数
					        	  mhto.setoDIntCount(Float.valueOf(toB_cId_toM.get("toBonus").toString()));//当日新增积分总数
					        	  mhto.setoDExIntCount(Float.valueOf(toB_cId_toM.get("toAmountS1").toString()));//当日兑换积总分数
					        	 
					        	  mhto.setTatalIncome(Integer.valueOf(Mobile)* Integer.valueOf(onA_onN.get("onAutCount").toString()));//收入总数   //手机* 总授权数
					        	  mhto.setNotTotalIntCont(Float.valueOf(toB_cId_toM.get("toAmountS0").toString())+ Float.valueOf(onA_onN.get("withdrawable").toString()));//未兑换总积分数 
					        	  mhto.setoDnetCount( Integer.valueOf(onA_onN.get("onNetCount").toString()));
				        	  }
					          mhto.setLastModiftyTime(new Date());
					       // 查询当日表中当天日期是否存在
					  	    mhto.setDateInt(Integer.valueOf(sdf.format(new Date())));
					  	    MhtotaldataInfo ont = settinService.findMhtotaldataInfo(mhto); 
				        	  if(ont!=null){ // update
				        		 settinService.updateMhtotaldata(mhto);
				        	  }else{ //插入
				        		  mhto.setCreateTime(new Date());
				        		  settinService.addMhtotaldata(mhto);
				        	  }
				            tick--;
			        	  
			        	  
			          } else {
			        	  log.debug("没有商户数据了完了");
			        	  log.debug("结束时间"+(new Date().getTime()));
			          }
			      }        
			       // sleep(1000);//休息一秒
	        } catch ( Exception e) {
	          e.printStackTrace();
	          tick =0;
	        }
	      
	      }
	    }
	}
}

