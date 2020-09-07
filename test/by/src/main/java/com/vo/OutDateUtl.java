package com.vo;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

public class OutDateUtl {
	public String outDate(Integer sum){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE,sum);
		Date date = calendar.getTime();		
		return  DateFormatUtils.format(date, "yyyyMMdd");
		
	}
}
