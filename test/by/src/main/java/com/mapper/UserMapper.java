package com.mapper;


import java.util.List;
import java.util.Map;

import com.mapper.SysMapper;
import com.pojo.Merchant;
import com.pojo.UserInfo;

public interface UserMapper extends SysMapper<UserInfo>{
	public UserInfo findByAccount(UserInfo user);
	public Integer findUserCaount(UserInfo user);
	public List<Map<String,Object>> list(UserInfo user);	
	public Integer updateUser(UserInfo user);
	public Integer updateUserPassword(UserInfo user);
	public Integer addUser(UserInfo user);
	public Integer deleteUser(UserInfo user);
	public List<Map<String,Object>> findPaymentaccountInfo(Merchant payInfo);
	public Merchant  findPaymentaccountName(Merchant payInfo);
	public Integer  findPaymentaccountConts(Merchant payInfo);
	public Integer addPaymerchant(Merchant payInfo);
	public Integer findPaymentaccountCount(Merchant payInfo);
	public Integer updatePaymentaccount(Merchant payInfo);
	public Integer updatepayAccountStatus(Merchant payInfo);
	public Integer deletepayAccount(Merchant payInfo);
	
	public void updateLoginTime(UserInfo user);
}
