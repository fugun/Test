package com.spring.quartzite;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.pojo.Merchant;
import com.pojo.MhtotaldataInfo;
import com.pojo.TotaldataInfo;
import com.service.StatisticsService;
import com.vo.OutDateUtl;

public class GetTaskJobTotaldataCheck {
	@Autowired
	private   StatisticsService  settinService;
	private static final Logger log = Logger.getLogger(GetTaskJobTotaldataCheck.class);
	//定时器
	public void work() {
		log.debug("总统计校验开始_执行");
		
		try{
			totalDate();
		}catch(Exception e){
			e.printStackTrace();
			log.error("总统计校验定时器执行异常"+e);
		}finally{
			log.debug("总统计校验结束_执行");
		}
  
    }  
	
	  

	//总统计表信息
		public void totalDate() {
			// 查出所有商户表信息求和
			TotaldataInfo ttd = new TotaldataInfo();
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			OutDateUtl date = new OutDateUtl();
			ttd.setDateInt(Integer.valueOf(Integer.valueOf(date.outDate(-1))));		
			MhtotaldataInfo mht = settinService.sumMhtotaldataInfo(ttd);
			
			ttd.setAutCount(mht.getAutCount());
			ttd.setNetCount(mht.getNetCount());
			ttd.setExTaskCount(mht.getExTaskCount());
			ttd.setoDExIntCount(mht.getoDExIntCount());
			ttd.setTotalIntCount(mht.getTotalIntCount());
			ttd.setTatalIncome(mht.getTatalIncome());
			ttd.setNotTotalIntCount(mht.getNotTotalIntCont());
			ttd.setoDIntCount(mht.getoDIntCount());
			ttd.setAddNetCount(mht.getoDnetCount());
			ttd.setAddAutCount(mht.getoDautCount());
			
			ttd.setLastModiftyTime(new Date());
			//查询表里是存在当数据
			int count = settinService.findTotalDatacount(ttd);		
			if(count>0){
				// 更新
				settinService.updateTotaldata(ttd);
				
			}else{
				//插入
				ttd.setCreateTime(new Date());
				settinService.addTotaldata(ttd);
			}
			
		}
		
}

