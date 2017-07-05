package com.mrx.dao.interfaces.user;

import java.sql.SQLException;
import java.util.List;

import com.mrx.entity.user.UserInfo;

public interface  UserInfoDao{

	public UserInfo login(String name, String password);
	
	public void add(UserInfo entity)throws SQLException;

	public List<UserInfo> seacrh(UserInfo entity);
	
	public UserInfo findByName(String name)throws SQLException ; 
	
	public int isDouble(String name);
	
	public void updata(UserInfo entity)throws SQLException;
}
