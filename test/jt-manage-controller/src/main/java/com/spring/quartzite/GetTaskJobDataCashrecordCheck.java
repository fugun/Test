package com.spring.quartzite;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.pojo.DataCashrecordInfo;
import com.pojo.Merchant;
import com.pojo.MhtotaldataInfo;
import com.pojo.TotaldataInfo;
import com.service.StatisticsService;
import com.vo.OutDateUtl;

public class GetTaskJobDataCashrecordCheck {
	@Autowired
	private   StatisticsService  settinService;
	private static final Logger log = Logger.getLogger(GetTaskJobDataCashrecordCheck.class);
	//定时器
	public void work() {
		log.debug("提现统计开始_执行");
		
		try{
			totalDate();
		}catch(Exception e){
			e.printStackTrace();
			log.error("提现统计定时器执行异常"+e);
		}finally{
			log.debug("提现统计结束_执行");
		}
  
    }  
	
	  

	   //提现统计表信息
		public void totalDate() {
			//提现隔日统计
			DataCashrecordInfo dci = new DataCashrecordInfo();
			OutDateUtl date = new OutDateUtl();
			dci.setCreateDate(Integer.valueOf(date.outDate(-1)));
			//System.out.println(dci.getCreateDate());
			dci.setCreateTime(new Date());
			
			List<DataCashrecordInfo> list = settinService.findDataCashrecordInfo(dci);
			for(DataCashrecordInfo dcif : list){
				dcif.setCreateTime(dci.getCreateTime());
				//System.out.println(dcif.toString());
				//查询表里是存在当数据
					int count = settinService.findDataCashrecordInfoCount(dcif);	
					if(count>0){
						// 更新						
						settinService.updateDataCashrecordInfo(dcif);						
					}else{						
						dci.setState(0);						
						settinService.addDataCashrecordInfo(dcif);
					}
			}

		}
		
}

