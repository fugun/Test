package com.mapper;

import java.util.List;
import java.util.Map;

import com.pojo.DataCashrecordInfo;
import com.pojo.Merchant;
import com.pojo.MhtotaldataInfo;
import com.pojo.TotaldataInfo;

public interface StatisticsMapper {
	
	// 查询手机金额  参数直接写死在sql里面
	String findMobilePhone();
	
	//查询所有商户信息
	List<Merchant> findmerchantAll();
	// 查询表
	MhtotaldataInfo findMhtotaldataInfo(MhtotaldataInfo mh);
	//查出当日统计 当日新增积分总数 ,当日执行任务总数 当日成功兑换积总分数 _ 当日申请兑换积分总数
	Map<String,Object> findOndate_toBonus_taskCount_toAmountS1_toAmountS0(MhtotaldataInfo mh);
	 //查出 当日授权总数 ，当日联网总数onAutCount,SUM(u2.u_count3) AS onNetCount  _ 用户余额
	Map<String,Object> findOndate__onAutCount_onNetCount_withdrawable(MhtotaldataInfo mh);
	//插入商户表
	Integer addMhtotaldata(MhtotaldataInfo mh);
	Integer updateMhtotaldata(MhtotaldataInfo mh);
	// 查询当商户积分，和当前新增积分 （用于校验
	Map<String,Object> findOndate_withdrawable_toBonus(MhtotaldataInfo mh);
	//查询商户统计表求和
	MhtotaldataInfo sumMhtotaldataInfo(TotaldataInfo ttd);
	Integer findTotalDatacount(TotaldataInfo mh);
	
	
	//插入商户表
	Integer addTotaldata(TotaldataInfo ttd);
	Integer updateTotaldata(TotaldataInfo ttd);
	
	//提现隔日统计
	List<DataCashrecordInfo> findDataCashrecordInfo (DataCashrecordInfo ttd);
	
	//插入提现隔日统计
	Integer addDataCashrecordInfo(DataCashrecordInfo ttd);
	Integer updateDataCashrecordInfo(DataCashrecordInfo ttd);
	//查询表 -提现隔日统计
	Integer findDataCashrecordInfoCount(DataCashrecordInfo ttd);
}
