package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.StatisticsMapper;
import com.pojo.DataCashrecordInfo;
import com.pojo.Merchant;
import com.pojo.MhtotaldataInfo;
import com.pojo.TotaldataInfo;
@Service
public class StatisticsService {
	@Autowired
	public StatisticsMapper statisticsMapper; 
	//手机金额
	public String findMobilePhone(){
		return statisticsMapper.findMobilePhone();
	}
	
	public List<Merchant> findmerchantAll(){
		return statisticsMapper.findmerchantAll();
		
	}
	public MhtotaldataInfo findMhtotaldataInfo(MhtotaldataInfo mt){
		return statisticsMapper.findMhtotaldataInfo(mt);
		
	}
	
	//查出当日统计 当日新增积分总数 ,当日执行任务总数 当日成功兑换积总分数 _ 当日申请兑换积分总数
	public Map<String,Object> findOndate_toBonus_taskCount_toAmountS1_toAmountS0(MhtotaldataInfo mt){
		return statisticsMapper.findOndate_toBonus_taskCount_toAmountS1_toAmountS0(mt);
	}
	 //查出 当日授权总数 ，当日联网总数onAutCount,SUM(u2.u_count3) AS onNetCount
	public Map<String,Object> findOndate__onAutCount_onNetCount_withdrawable(MhtotaldataInfo mt){
		return statisticsMapper.findOndate__onAutCount_onNetCount_withdrawable(mt);
	}
	
	//插入商户表
	public Integer addMhtotaldata(MhtotaldataInfo mt){
		return statisticsMapper.addMhtotaldata(mt);
	}
	//修改商户表
	public Integer updateMhtotaldata(MhtotaldataInfo mt){
		return statisticsMapper.updateMhtotaldata(mt);
	}
	// 查询当商户积分，和当前新增积分（用于校验
	public Map<String,Object> findOndate_withdrawable_toBonus(MhtotaldataInfo mt){
		return statisticsMapper.findOndate_withdrawable_toBonus(mt);
	}
	
	//查询商户统计表求和
	
	public MhtotaldataInfo sumMhtotaldataInfo(TotaldataInfo ttd){
		return statisticsMapper.sumMhtotaldataInfo(ttd);
	}
   //查询总统计表
	public Integer findTotalDatacount(TotaldataInfo ttd){
		return statisticsMapper.findTotalDatacount(ttd);
	}
	
	//插入总计表
	public Integer addTotaldata(TotaldataInfo ttd){
		return statisticsMapper.addTotaldata(ttd);
	}
	//修改总计表
	public Integer updateTotaldata(TotaldataInfo ttd){
		return statisticsMapper.updateTotaldata(ttd);
	}
	
	//提现隔日统计，方便提现
	
	public List<DataCashrecordInfo> findDataCashrecordInfo(DataCashrecordInfo ttd){
		return statisticsMapper.findDataCashrecordInfo(ttd);
	}
	//插入提现隔日统计
	public Integer addDataCashrecordInfo(DataCashrecordInfo mt){
		return statisticsMapper.addDataCashrecordInfo(mt);
	}
	//修改提现隔日统计
	public Integer updateDataCashrecordInfo(DataCashrecordInfo mt){
		return statisticsMapper.updateDataCashrecordInfo(mt);
	}
	
	// 查询数据
	public Integer findDataCashrecordInfoCount(DataCashrecordInfo ttd){
		return statisticsMapper.findDataCashrecordInfoCount(ttd);
	}
}
